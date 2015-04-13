/**
 * @(#)AuthenticationProvider.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security;

import java.io.Serializable;

/**
 * 身份验证提供者接口
 * @author  jianguo.xu
 * @version 1.0,2011-2-17
 */
public interface AuthenticationProvider {
	/**
	* 根据用户标识返回认证用户
	* @param id 用户标识
	* @return 认证用户 没有返回null
	*/
	public Principal get(Serializable id);

	/**
	 * 验证用户
	 * @param verifier
	 * @return
	 * @throws AuthenticationException
	 */
	public Principal authenticate(Verifier verifier) throws AuthenticationException;
}
