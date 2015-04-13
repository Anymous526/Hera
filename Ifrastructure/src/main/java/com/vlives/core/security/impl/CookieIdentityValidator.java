/**
 * @(#)CookieIdentityValidatorImpl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vlives.core.security.AuthenticationException;
import com.vlives.core.security.AuthenticationProvider;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.KeepLoginStatusVerifier;
import com.vlives.core.security.Principal;
import com.vlives.core.security.Verifier;
import com.vlives.core.web.WebContext;
import com.vlives.util.DateUtils;
import com.vlives.util.DateUtils.TimeUnit;
import com.vlives.util.security.Base64Utils;
import com.vlives.util.web.cookie.CookieUtils;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-2-17
 */
public abstract class CookieIdentityValidator implements IdentityValidator {
	private static final Log LOG = LogFactory
			.getLog(CookieIdentityValidator.class);
	public abstract void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider);
	
	public abstract AuthenticationProvider getAuthenticationProvider();
 
	public abstract String getPrincipalcookieName();
	
	public abstract String getvisitorCookieName();
	
	/**
	 * 持久cookie name
	 */
	//public static final String VISITORCOOKIE_NAME = "visitor";
	
	/**
	 * 自动登录cookie name
	 */
	public static final String AUTO_LOGIN_COOKIE_NAME="auto_login";
	
	/**
	 * 会话cookie 超时时间(以秒为单位)
	 */
	private static final int PRINCIPALCOOKIE_MAX_AGE = 3600 * 12;
	/**
	 * 持久cookie 超时时间(以秒为单位)
	 */
	private static final int VISITORCOOKIE_MAX_AGE = 3600 * 24 * 30;
	private static final String SPLIT = "&";
	private static final String ENCODE = "utf-8";

	private String getSHAParam(Serializable id, Long lastLoginTime) {
		return id.toString() + lastLoginTime.toString();
	}

	/**
	 * 通过调用CookieUtils和MD5Utils产生会话cookie
	 * 
	 * @author jianguo.xu
	 * @param principal
	 *            用户对象
	 * @return 创建的cookie
	 */
	private Cookie createPrincipalCookie(Principal principal) {
		StringBuffer cookValue = new StringBuffer();
		Long lastLoginTime = principal.getLastLoginTime();
		String shaParam = getSHAParam(principal.getIdentity(), lastLoginTime);
		cookValue.append(principal.getIdentity().toString());
		cookValue.append(SPLIT + lastLoginTime.toString());
		cookValue.append(SPLIT
				+ Base64Utils.urlEncoding(DigestUtils.shaHex(shaParam)));
		Cookie cookie = new Cookie(getPrincipalcookieName(), cookValue.toString());
		cookie.setPath("/");
		return cookie;
	}
	/**
	 * 通过调用CookieUtils产生自动登录的持久cookie
	 * 
	 * @author jianguo.xu
	 * @param principal
	 *            用户对象
	 * @return 创建的cookie
	 */
	private Cookie createAutoLoginCookie(Verifier verifier,Principal principal) {
		CookieUtils.removeCookie(getvisitorCookieName(), "/");
		if(!(verifier instanceof KeepLoginStatusVerifier)) return null; 
		KeepLoginStatusVerifier vf = (KeepLoginStatusVerifier)verifier;
		if(!vf.isKeepLoginStatus()||vf.getKeepLoginMaxTime()==0) return null;
		String value = principal.getIdentity().toString()+SPLIT+principal.getLoginName();
		Cookie cookie = new Cookie(AUTO_LOGIN_COOKIE_NAME, Base64Utils.urlEncoding(value));
		cookie.setMaxAge(vf.getKeepLoginMaxTime());
		cookie.setPath("/");
		return cookie;
	}

	/**
	 * 通过调用CookieUtils产生持久cookie
	 * 
	 * @param registerName
	 *            用户名
	 * 
	 * @return 创建的cookie
	 */
	private Cookie createVisitorCookie(String registerName) {
		try {
			registerName = URLEncoder.encode(registerName, ENCODE);
			Cookie cookie = new Cookie(getvisitorCookieName(), registerName);
			cookie.setMaxAge(VISITORCOOKIE_MAX_AGE);
			cookie.setPath("/");
			return cookie;
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
			return null;
		}
	}
	
	

	/**
	 * 通过调用CookieUtils查找持久cookie，
	 * 
	 * @return 找到返回true，否则返回false
	 */
	public boolean isVisited() {
		return currentVisitor() != null;
	}

	/**
	 * 参照currentPrincipal方法的逻辑，如果用户已登录返回 true ,否则返回null
	 * 
	 * @return
	 */
	public boolean isLogined() {
		return currentPrincipal() != null;
	}

	/**
	 * 通过调用CookieUtils查找会话cookie，<br>
	 * 如果找到，取得cookie中id，调用验证提供者的get方法，得到用户，<br>
	 * 再根据cookie摘要与生成摘要对比，如果一致再判断时间戳距离当前时间的间隔是否在合适范围，<br>
	 * 如果是返回认证用户，否则返回null
	 * 
	 * @return true 表示是已登录 false 表示没有登录
	 */
	public Principal currentPrincipal() {
		Principal principal = getRequestPrincipal();
		if(principal!=null) return principal;
		principal = getByPrincipalCookie();
		if(principal!=null) return principal;
		principal = getByAutoLogin();
		if(principal!=null) {
			CookieUtils.writeCookie(createPrincipalCookie(principal));
			WebContext.currentRequest().setAttribute(getPrincipalcookieName(), principal);
		}
		return principal;
	}
	
	/**
	 * 通过调用CookieUtils查找会话cookie，<br>
	 * 如果找到，取得cookie中id，调用验证提供者的get方法，得到用户，<br>
	 * 再根据cookie摘要与生成摘要对比，如果一致再判断时间戳距离当前时间的间隔是否在合适范围，<br>
	 * 如果是返回认证用户，否则返回null
	 * 
	 * @return true 表示是已登录 false 表示没有登录
	 */
	private Principal getByPrincipalCookie() {
		Cookie cookie = CookieUtils.getCookie(getPrincipalcookieName());
		if (cookie == null)
			return null;
		String cookieValue = cookie.getValue();
		String[] values = cookieValue.split(SPLIT);
		Long id = new Long(values[0]);
		String cookieSHAValue = values[2];
		Principal principal = getAuthenticationProvider().get(id);
		if (principal == null)
			return null;
		Long lastLoginTime = principal.getLastLoginTime();
		String shaParam = getSHAParam(principal.getIdentity(), lastLoginTime);
		String shaValue = Base64Utils.urlEncoding(DigestUtils.shaHex(shaParam));
		if (!shaValue.equals(cookieSHAValue))
			return null;
		Date nowDate = new Date();
		Date maxAccessDate = DateUtils.add(new Date(lastLoginTime.longValue()),
				TimeUnit.SECONDS, PRINCIPALCOOKIE_MAX_AGE);
		return maxAccessDate.compareTo(nowDate) > 0 ? principal : null;
	}
	
	/**
	 * 根据保持登录状态的持久cookie得到认证用户
	 * @author jianguo.xu
	 * @return
	 */
	private Principal getByAutoLogin() {
		Cookie cookie = CookieUtils.getCookie(AUTO_LOGIN_COOKIE_NAME);
		if(cookie == null) return null;
		String encodingValue = cookie.getValue();
		if(encodingValue == null) return null;
		String decodeValue = Base64Utils.decodeing(encodingValue);
		try {
			Long id = new Long(decodeValue.split(SPLIT)[0]);
			Principal pricipal = getAuthenticationProvider().get(id);
			if(pricipal == null) return null;
			if(!pricipal.getLoginName().equals(decodeValue.split(SPLIT)[1]))
				return null;
			return pricipal;
		}catch (Exception e) {
			LOG.error(e);
			return null;
		}
		
	}
	
	
	private Principal getRequestPrincipal() {
		HttpServletRequest request = WebContext.currentRequest();
		if (request == null) return null;
		return (Principal) request.getAttribute(getPrincipalcookieName());
	}

	/**
	 * 通过调用CookieUtils查找持久cookie,
	 * 
	 * @return 找到返回cookie中用户名，否则返回null
	 */
	public String currentVisitor() {
		Object visitorObj = WebContext.currentRequest().getAttribute(getvisitorCookieName());
		if (visitorObj != null)return (String) visitorObj;
		try {
			Cookie cookie = CookieUtils.getCookie(getvisitorCookieName());
			if (cookie == null) return null;
			return URLDecoder.decode(cookie.getValue(), ENCODE);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.toString());
			return null;
		}
	}

	public void logout() {
		CookieUtils.removeCookie(getPrincipalcookieName(), "/");
		CookieUtils.removeCookie(AUTO_LOGIN_COOKIE_NAME, "/");
		CookieUtils.removeCookie(getvisitorCookieName(), "/");
		WebContext.currentRequest().setAttribute(getPrincipalcookieName(), null);
		WebContext.currentRequest().setAttribute(getvisitorCookieName(), null);
	}
	
	/**
	 * 通过调用验证提供者进行登录，如果登录成功，将会话cookie和持久cookie写入客户端 
	 * @param verifier
	 *            用户名
	 * @return 返回登录后的认证主体
	 * @throws AuthenticationException
	 */
	public Principal login(Verifier verifier) throws AuthenticationException {
		Principal principal = getAuthenticationProvider()
				.authenticate(verifier);
		if(principal==null) {
			throw new AuthenticationException("登录失败");
		}
		writeLoginCookie(principal, verifier);
		return principal;
	}
	
	private void writeLoginCookie(Principal principal,Verifier verifier) {
		CookieUtils.writeCookie(createPrincipalCookie(principal));
		CookieUtils.writeCookie(createVisitorCookie(principal.getLoginName()));
		CookieUtils.writeCookie(createAutoLoginCookie(verifier, principal));
		WebContext.currentRequest().setAttribute(getPrincipalcookieName(), principal);
		WebContext.currentRequest().setAttribute(getvisitorCookieName(), principal.getLoginName());
		
	}
	
	 

}
