/**
 * @(#)PasswordVerifier.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security.impl;

import com.vlives.core.security.KeepLoginStatusVerifier;

/**
 * 验证码认证校验器
 * 
 * @author jianguo.xu
 * @version 1.0,2011-2-17
 */
public class VerifyCodeVerifier extends KeepLoginStatusVerifier{
	 
	private final String loginName;
	private final String verifyCode;
	 
 
	public String getVerifyCode() {
		return verifyCode;
	}
	public String getLoginName() {
		return loginName;
	}
	 
	public VerifyCodeVerifier(String loginName, String verifyCode) {
		this(loginName, verifyCode,false);
	}

	public VerifyCodeVerifier(String loginName, String verifyCode,
			boolean keepLoginStatus) {
		this(loginName,verifyCode,keepLoginStatus,DEFAULT_KEEP_LOGIN_DAY);
	}

	public VerifyCodeVerifier(String loginName, String verifyCode,
			boolean keepLoginStatus, int keepLoginDay) {
		super(keepLoginStatus,keepLoginDay);
		this.loginName = loginName;
		this.verifyCode = verifyCode;
	}
	
}
