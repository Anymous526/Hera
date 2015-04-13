/**
 * @(#)RegisterControler.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.ValidatorNumberUtils;
import com.vlives.core.security.impl.PasswordVerifier;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.StringUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-22
 */
@Controller
@RequestMapping("/manager")
public class LoginController {
	
	@Resource(name="operatorIdentityValidator")
	private IdentityValidator identityValidator;
	@RequestMapping("/login.htm")
	public ModelAndView login() {
		return new ModelAndView("/manager/login.jsp");
	}
	
	/**
	 * 登录提交
	 * @author jianguo.xu
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logincommit.htm")
	public ModelAndView loginCommit(HttpServletRequest request, HttpServletResponse response) {
		String loginName = HttpParameterParser.getString("mobile");
		String password = HttpParameterParser.getString("password");
		boolean success = ValidatorNumberUtils.validator(HttpParameterParser.getString("randomValue"));
		if(!success) {
			return buildErrorMode(loginName,"验证码错误,请重新输入");
		}
		String from = HttpParameterParser.getString("from");
		
		try {
			identityValidator.login(new PasswordVerifier(
					loginName, password));
		}catch (Exception e) {
			return buildErrorMode(loginName,e.getMessage());
		}
		
		String redirectUrl = StringUtils.isNullOrEmpty(from) ? "/manager/index.htm" : from;
		return new ModelAndView(new RedirectView(redirectUrl));
	}
	private ModelAndView buildErrorMode(String loginName,String errorMsg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorMsg",errorMsg);
		map.put("mobile", loginName);
		return new ModelAndView("/manager/login.htm", map);
	}
	 
	/**
	 * 退出登录
	 * @author jianguo.xu
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout.htm")
	 
	public ModelAndView quit(HttpServletRequest request, HttpServletResponse response) {
		identityValidator.logout();
		return new ModelAndView(new RedirectView("/manager/login.htm"));
	} 
}	
