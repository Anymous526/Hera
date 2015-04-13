/**
 * @(#)LoginController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.user.domain.AccountType;
import com.vlives.boss.user.domain.AuthenticationEntry;
import com.vlives.boss.user.domain.AuthenticationEntry.EntryItem;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.manager.RelationAccountVerifier;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.security.AuthenticationException;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.impl.VerifyCodeVerifier;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-6
 */
@Controller("userLoginController")
public class LoginController {
	@Autowired
	private UserManager userManager;
	@Resource(name="userIdentityValidator")
	private IdentityValidator identityValidator;
	/**
	 * 发送验证码
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/send_verifycode.htm",method=RequestMethod.POST)
	public ModelAndView sendVerifyCode() {
		String mobile = HttpParameterParser.getString("mobile");
		try {
			userManager.sendVerifyCode(mobile);
			return new ModelAndView(new JsonView(true, "发送成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	/**
	 * 由于发送验证码的号码与取验证码的号码不一致，所以做个新的发送验证码
	 * @return
	 */
	@RequestMapping(value="/send_verifycodeformobile.htm",method=RequestMethod.POST)
	public ModelAndView sendVerifyCodeForMobile(@ObjectConvertAnno User user) {
		String mobile = HttpParameterParser.getString("mobile");//用户想替换成的手机号码
		String _mobile = HttpParameterParser.getString("_mobile");//用户本身的手机号
		try {
			userManager.sendVerifyCode(user, mobile);
			return new ModelAndView(new JsonView(true, "发送成功"));
		} catch (BusinessException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	/**
	 * 手机登录
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/mobile_login.htm",method=RequestMethod.POST)
	public ModelAndView mobileLogin() {
		String mobile = HttpParameterParser.getString("mobile");
		String nickName = HttpParameterParser.getString("nickName");
		String avatar = HttpParameterParser.getString("avatar");
		String verifyCode = HttpParameterParser.getString("verifyCode");
		boolean keepLoginStatus = HttpParameterParser.getBooleanValue("keepLoginStatus");
		try {
			identityValidator.login(new VerifyCodeVerifier(mobile, verifyCode,keepLoginStatus,30));
			bindRelationAccount(mobile, nickName, avatar);
			return new ModelAndView(new JsonView(true,"登录成功"));
		} catch (Exception e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	
	private void bindRelationAccount(String mobile, String nickName, String avatar) throws BusinessException {
		int accountTypeId = HttpParameterParser.getIntValue("accountType", 0);
		if(accountTypeId==0) return;
		AccountType accountType = AccountType.get(accountTypeId);
		User user = userManager.getByMobile(mobile);
		if (user.getPetName() == null || user.getPetName().isEmpty()) {
			user.setPetName(nickName);
		}
		if (user.getHead() == null || user.getHead().isEmpty()) {
			user.setHead(avatar);
		}
		userManager.bindRelationAccount(user, accountType, createEntry(accountType));
	}
	
	private AuthenticationEntry createEntry(AccountType accountType) {
		AuthenticationEntry authenticationEntry = new AuthenticationEntry();
		if(accountType == AccountType.TYPE_SINA) {
			String oauth_token = HttpParameterParser.getString("oauth_token");
			String oauth_token_secret = HttpParameterParser.getString("oauth_token_secret");
			String userName = HttpParameterParser.getString("userName");
			String userid = HttpParameterParser.getString("userid");
			authenticationEntry.addItem(new EntryItem("oauth_token", oauth_token));
			authenticationEntry.addItem(new EntryItem("oauth_token_secret", oauth_token_secret));
			authenticationEntry.addItem(new EntryItem("userName", userName));
			authenticationEntry.addItem(new EntryItem("userid", userid));
		}
		if(accountType == AccountType.TYPE_QQ) {
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
	 * 关联账户登录
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/relation_login.htm",method=RequestMethod.POST)
	public ModelAndView relationAccountLogin() {
		int accountTypeId = HttpParameterParser.getIntValue("accountType");
		boolean keepLoginStatus = HttpParameterParser.getBooleanValue("keepLoginStatus");
		AccountType accountType = AccountType.get(accountTypeId);
		try {
			identityValidator.login(new RelationAccountVerifier(accountType, createEntry(accountType), keepLoginStatus, 30));
			return new ModelAndView(new JsonView(true,"登录成功"));
		} catch (AuthenticationException e) {
			return new ModelAndView(new JsonView(false,e.getMessage()));
		}
	}
	/**
	 * 判断当前用户是否登录
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/userLogin.htm",method=RequestMethod.GET)
	public ModelAndView isLogin() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("login", identityValidator.isLogined());
		return new ModelAndView(new JsonView(model));
	}
	
	/**
	 * 退出登录
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value="/logout.htm",method=RequestMethod.GET)
	public ModelAndView logout() {
		identityValidator.logout();
		return new ModelAndView(new JsonView(true,"退出成功"));
	}
}
