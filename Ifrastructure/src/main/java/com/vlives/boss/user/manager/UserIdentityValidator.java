/**
 * @(#)UserIdentityValidator.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.user.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vlives.core.security.AuthenticationProvider;
import com.vlives.core.security.impl.CookieIdentityValidator;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-7-6
 */
@Service("userIdentityValidator")
public class UserIdentityValidator extends CookieIdentityValidator {
	private AuthenticationProvider authenticationProvider;
	/**
	 * 用户会话cookie
	 */
	private static final String PRINCIPALCOOKIE_NAME = "user_principal";
	/**
	 * 用户持久cookie
	 */
	public static final String VISITORCOOKIE_NAME = "user_visitor";
	@Override
	@Resource(name="userManager")
	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
		
	}

	@Override
	public AuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}

	@Override
	public String getPrincipalcookieName() {
		return PRINCIPALCOOKIE_NAME;
	}

	@Override
	public String getvisitorCookieName() {
		return VISITORCOOKIE_NAME;
	}

}
