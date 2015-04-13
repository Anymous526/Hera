/**
 * @(#)IdentityValidator.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security;
/**
 * 身份验证接口
 * @author  jianguo.xu
 * @version 1.0,2011-2-17
 */

public interface IdentityValidator {
	/**
	* 判断是否是访问者
	* @author  jianguo.xu
	* @return true 表示是访问者 false 表示不是访问者
	*/
	public boolean isVisited();
	/**
	* 得到当前访问者用户名
	* @author  jianguo.xu
	* @return 当前访问者用户名 没有用户返回null
	*/
	public String currentVisitor();
	/**
	* 判断用户是否登录
	* @author  jianguo.xu
	* @return true 表示是已登录 false 表示没有登录
	*/
	public boolean isLogined();
	/**
	* 得到当前用户
	* @author  jianguo.xu
	* @return 当前用户 没有用户返回null
	*/
	public Principal currentPrincipal();
	/**
	* 验证登录是否成功
	* @author  jianguo.xu
	* @param verifier 
	* @return 返回登录后的认证主体
	*/
	public Principal login(Verifier verifier) throws AuthenticationException;
	
	/**
	 * 退出登录状态
	 * @author  jianguo.xu
	 */
	public void logout();	
	
	/**
	* 设置验证提供者，用于获得用户信息和登录验证
	* @author  jianguo.xu
	* @see com.xhbs.validate.AuthenticationProvider
	*/
	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider);	
	/**
	* 得到验证提供者，用于获得用户信息和登录验证
	* @author  jianguo.xu
	* @return 验证提供者
	* *@see com.xhbs.validate.AuthenticationProvider
	*/
	public AuthenticationProvider getAuthenticationProvider();
}
