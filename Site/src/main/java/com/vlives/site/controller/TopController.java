/**
 * @(#)LoginController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.border.domain.Border;
import com.vlives.boss.border.manager.BorderManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.AuthenticationEntry.EntryItem;
import com.vlives.boss.user.domain.RelationAccount;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.manager.RelationAccountManager;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.security.AuthenticationException;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.Principal;
import com.vlives.core.security.impl.VerifyCodeVerifier;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.security.Base64Utils;
import com.vlives.util.web.cookie.CookieUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * "top.jsp"
 * 
 * @author li ji
 * @version 1.0, 2011-8-24
 */
@Controller("loginController")
public class TopController {

	@Resource(name = "userIdentityValidator")
	private IdentityValidator identityValidator;

	@Autowired
	private UserManager userManager;

	@Autowired
	private RelationAccountManager relationAccountManager;

	@Autowired
	private BorderManager borderManager;

	@Autowired
	private TradeDetailManager tradeDetailManager;

	private static final String USER_LOGIN_COOKIE = "user_principal";

	private static final String SPLIT = "&";

	private static final int COOKIE_AGE_30_MINUTES = 60 * 30;
	private static final int COOKIE_AGE_2_WEEKS = 60 * 60 * 24 * 14;

	/**
	 * 发送验证码
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/ajax/verification", method = RequestMethod.POST)
	public ModelAndView sendVerificationCode() {
		String mobile = HttpParameterParser.getString("mobile");
		try {
			userManager.sendVerifyCode(mobile);
			return new ModelAndView(new JsonView(true, "发送成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
	}

	/**
	 * 手机登录
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/ajax/session/mobile", method = RequestMethod.POST)
	public ModelAndView loginForMobile() {
		String mobile = HttpParameterParser.getString("mobile");
		String nickName = HttpParameterParser.getString("nickName");
		String avatar = HttpParameterParser.getString("avatar");
		String verifyCode = HttpParameterParser.getString("verifyCode");
		boolean keepLoginStatus = HttpParameterParser
				.getBooleanValue("keepLoginStatus");

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			User user = (User) identityValidator.login(new VerifyCodeVerifier(
					mobile, verifyCode, keepLoginStatus, 30));
			bindRelationAccount(mobile, nickName, avatar);

			params.put("result", true);
			params.put("user", user);
		} catch (BusinessException e) {
			params.put("result", false);
			params.put("message", e.getMessage());
		} catch (AuthenticationException e) {
			params.put("result", false);
			params.put("message", e.getMessage());
		} catch (Exception e) {
			params.put("result", false);
			params.put("message", "服务器出小差了，请稍候重试。");
		}

		return new ModelAndView(new JsonView("top.userInfo", params));
	}

	/**
	 * 关联账户登录
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/ajax/session/external", method = RequestMethod.POST)
	public ModelAndView loginForExternal() {
		int accountTypeId = HttpParameterParser.getIntValue("accountType");
		AccountType accountType = AccountType.get(accountTypeId);

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			RelationAccount relationAccount = relationAccountManager.getBy(
					accountType, createEntry(accountType));
			if (relationAccount == null) {
				// 关联帐号未绑定会生活用户
				params.put("result", true);
				params.put("binding", false);
			} else if (!relationAccount.isEnable()) {
				params.put("result", false);
				params.put("message", "关联帐号被禁用，请联系会生活客服。");
			} else {
				// 关联帐号已经绑定会生活用户
				User user = relationAccount.getUser();
				CookieUtils.writeCookie(createLoginCookie(user, false));

				params.put("result", true);
				params.put("binding", true);
				params.put("user", relationAccount.getUser());
			}
		} catch (Exception e) {
			params.put("result", false);
			params.put("message", "服务器出小差了，请稍候重试。");
		}

		return new ModelAndView(new JsonView("top.externalUserInfo", params));
	}

	/**
	 * 判断当前用户是否登录
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/ajax/session", method = RequestMethod.GET)
	public ModelAndView isLogin() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("login", identityValidator.isLogined());
		return new ModelAndView(new JsonView(model));
	}

	/**
	 * 退出登录
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/ajax/session", method = RequestMethod.DELETE)
	public ModelAndView logout() {
		identityValidator.logout();
		return new ModelAndView(new JsonView(true, "退出成功"));
	}

	/**
	 * 未点评的消费数
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/user/unCommentedConsumesCount")
	public ModelAndView getNotCommentCount(@ObjectConvertAnno User user) {
		long count = tradeDetailManager.getNoCommentCount(user);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("success", true);
		params.put("msg", null);
		params.put("count", count);
		return new ModelAndView(new JsonView("top.uncommentCount", params));
	}

	/**
	 * 网站通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/anouncement", method = RequestMethod.GET)
	public ModelAndView findWeb() throws BusinessException {
		List<Border> anouncements = borderManager.find(1);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("anouncements", anouncements);
		return new ModelAndView(new JsonView(result));
	}

	private void bindRelationAccount(String mobile, String nickName,
			String avatar) throws BusinessException {
		int accountTypeId = HttpParameterParser.getIntValue("accountType", 0);
		if (accountTypeId == 0)
			return;
		AccountType accountType = AccountType.get(accountTypeId);
		User user = userManager.getByMobile(mobile);
		if (user.getPetName() == null || user.getPetName().isEmpty()) {
			user.setPetName(nickName);
		}
		if (user.getHead() == null || user.getHead().isEmpty()) {
			user.setHead(avatar);
		}
		userManager.bindRelationAccount(user, accountType,
				createEntry(accountType));
	}

	private AuthenticationEntry createEntry(AccountType accountType) {
		AuthenticationEntry authenticationEntry = new AuthenticationEntry();
		if (accountType == AccountType.TYPE_SINA) {
			String oauth_token = HttpParameterParser.getString("oauth_token");
			String oauth_token_secret = HttpParameterParser
					.getString("oauth_token_secret");
			String userName = HttpParameterParser.getString("userName");
			String userid = HttpParameterParser.getString("userid");
			authenticationEntry.addItem(new EntryItem("oauth_token",
					oauth_token));
			authenticationEntry.addItem(new EntryItem("oauth_token_secret",
					oauth_token_secret));
			authenticationEntry.addItem(new EntryItem("userName", userName));
			authenticationEntry.addItem(new EntryItem("userid", userid));
		}
		if (accountType == AccountType.TYPE_QQ) {
			String openid = HttpParameterParser.getString("openid");
			String token = HttpParameterParser.getString("token");
			String secret = HttpParameterParser.getString("secret");
			authenticationEntry.addItem(new EntryItem("openid", openid));
			authenticationEntry.addItem(new EntryItem("token", token));
			authenticationEntry.addItem(new EntryItem("secret", secret));
		}
		return authenticationEntry;
	}

	/**
	 * 创建用户登录Cookie
	 * 
	 * @param principal
	 *            用户对象
	 * @return 创建的cookie
	 */
	private Cookie createLoginCookie(Principal principal, boolean keepMeLogin) {
		StringBuffer cookValue = new StringBuffer();
		Long lastLoginTime = principal.getLastLoginTime();
		String shaParam = principal.getIdentity().toString()
				+ lastLoginTime.toString();
		cookValue.append(principal.getIdentity().toString());
		cookValue.append(SPLIT + lastLoginTime.toString());
		cookValue.append(SPLIT
				+ Base64Utils.urlEncoding(DigestUtils.shaHex(shaParam)));
		Cookie cookie = new Cookie(USER_LOGIN_COOKIE, cookValue.toString());
		if (keepMeLogin) {
			cookie.setMaxAge(COOKIE_AGE_30_MINUTES);
		} else {
			cookie.setMaxAge(COOKIE_AGE_2_WEEKS);
		}
		cookie.setPath("/");
		return cookie;
	}
}
