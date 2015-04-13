/*
 * @(#)CookieUtils.java
 *
 * Copyright 2010 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.util.web.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vlives.core.web.WebContext;
import com.vlives.util.ConfigUtils;

/**
 * description
 * @author  xujianguo
 */
public class CookieUtils {
	public static final String VIEW_HISTORY_COOKIE_KEY = "client_view_history";
	public static final int COOKIE_AGE = 30 * 24 * 60 * 60;
	public static final String COOKIE_PATH = "/";
	
	private CookieUtils(){}
	
	/**
	* 得到当前request请求的所有cookie
	* @author jianguo.xu
	* @return cookie数组
	*/
	public static Cookie[] getCookies() {
		HttpServletRequest request=WebContext.currentRequest();
		return request==null?null:request.getCookies();
	}
	/**
	* 根据cookie名字取得cookie
	* @author jianguo.xu 
	* @param name cookie名字
	* @return cookie
	*/
	public static Cookie getCookie(String name) {
		Cookie[] cookies = getCookies();
		if(cookies!=null&&cookies.length>0) {
			for(int i = 0;i<cookies.length;i++) {
				Cookie cookie = cookies[i];
				String cookName = cookie.getName();
				if(cookName!=null&&cookName.equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 将cookie写入客户端
	 * @author jianguo.xu
	 * @param cookie
	 */
	public static void writeCookie(Cookie cookie) {
		if(cookie == null) return;
		HttpServletResponse response=WebContext.currentResponse();
		HttpServletRequest request = WebContext.currentRequest();
		if (request != null) {
			String host = request.getHeader("Host");
			if (ConfigUtils.WEBSITE.equals(host))
				cookie.setDomain("."+ConfigUtils.DOMAIN);
		}
		if(response!=null){
			response.addCookie(cookie);
		}
	}
	
	public static void removeCookie(String cookieName,String path){
		HttpServletRequest request = WebContext.currentRequest();
		Cookie[] cookies=request.getCookies();
		if(cookies == null||cookies.length == 0) return;
		 
		for(int i=0;i<cookies.length;i++){
			Cookie cookie=cookies[i];
			if(cookie.getName().equals(cookieName)){
				cookie.setMaxAge(0);
				cookie.setPath(path);
				String host = request.getHeader("Host");
				if (ConfigUtils.WEBSITE.equals(host))
					cookie.setDomain("."+ConfigUtils.DOMAIN);
				WebContext.currentResponse().addCookie(cookie);
				break;
			}
		}
		 
	}
}