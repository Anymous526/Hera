/**
 * @(#)PasswordIdentityValidator.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.operator.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vlives.core.security.AuthenticationProvider;
import com.vlives.core.security.impl.CookieIdentityValidator;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-3-10
 */
@Service("operatorIdentityValidator")
public class OperatorIdentityValidator extends CookieIdentityValidator{
	private AuthenticationProvider authenticationProvider;
	/**
	 * 管理员会话cookie
	 */
	public static final String PRINCIPALCOOKIE_NAME = "operator_principal";	
	/**
	 * 管理员持久cookie
	 */
	public static final String VISITORCOOKIE_NAME = "operator_visitor";
	@Override
	@Resource(name="operatorManager")
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
