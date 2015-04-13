/**
 * @(#)Principal.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.security;

import java.io.Serializable;

/**
 * 此接口表示需要认证主体的抽象概念，它可以用来表示任何实体
 * @author  jianguo.xu
 * @version 1.0,2011-2-17
 */
public interface Principal {
	/**
	* 得到用户标识
	* @author jianguo.xu
	* @return 用户标识
	*/
	public Serializable getIdentity();
	/**
	* 得到用户最后登录时间 
	* @author jianguo.xu
	* @return 用户最后登录时间 （返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的毫秒数）
	*/
	public Long getLastLoginTime();
	/**
	* 得到登录用户名
	* @author jianguo.xu
	* @return 用户名
	*/
	public String getLoginName();
}
