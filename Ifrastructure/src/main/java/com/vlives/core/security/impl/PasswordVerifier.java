/**
 * @(#)PasswordVerifier.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security.impl;

import com.vlives.core.security.KeepLoginStatusVerifier;


/**
 * 密码认证校验器
 * 
 * @author jianguo.xu
 * @version 1.0,2011-2-17
 */
public class PasswordVerifier extends KeepLoginStatusVerifier{
	
	private String loginName;
	private String password;
 
	public String getPassword() {
		return password;
	}
 
	public String getLoginName() {
		return loginName;
	}
	public PasswordVerifier(String loginName, String password) {
		this(loginName, password,false);
	}

	public PasswordVerifier(String loginName, String password,
			boolean keepLoginStatus) {
		this(loginName,password,keepLoginStatus,DEFAULT_KEEP_LOGIN_DAY);
	}

	public PasswordVerifier(String loginName, String password,
			boolean keepLoginStatus, int keepLoginDay) {
		super(keepLoginStatus,keepLoginDay);
		this.loginName = loginName;
		this.password = password;
	
	}
	
}
