/**
 * @(#)KeepVerifier.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security;
/**
 * 提供保持登录状态的校验器
 * @author  jianguo.xu
 * @version 1.0,2011-7-7
 */
public abstract class KeepLoginStatusVerifier implements Verifier {
	/**
	 * 默认保持登录状态的天数
	 */
	protected static final int DEFAULT_KEEP_LOGIN_DAY= 14;
	private boolean keepLoginStatus;
	private  int keepLoginMaxTime;
	protected KeepLoginStatusVerifier(boolean keepLoginStatus,
			int keepLoginDay) {
		this.keepLoginStatus = keepLoginStatus;
		this.keepLoginMaxTime = keepLoginDay*3600*24;
	}
	public static int getDefaultKeepLoginDay() {
		return DEFAULT_KEEP_LOGIN_DAY;
	}
	public boolean isKeepLoginStatus() {
		return keepLoginStatus;
	}
	public int getKeepLoginMaxTime() {
		return keepLoginMaxTime;
	}
	
}
