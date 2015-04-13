/**
 * @(#)Cache.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.web.filter.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面缓存 注解
 * @author  jianguo.xu
 * @version 1.0,2011-7-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
	/**
	 * cache name
	 * @author jianguo.xu
	 * @return
	 */
	String cacheName() default "response-cache";
	/**
	 * 清除缓存的Request请求参数
	 * 如果设置为null表示要清除缓存
	 * @author jianguo.xu
	 * @return
	 */
	String except() default "src";
	/**
	 * 是否忽略请求参数
	 * @author jianguo.xu
	 * @return
	 */
	boolean ignoreParams() default false;
	/**
	 * 随机数
	 * @author jianguo.xu
	 * @return
	 */
	int random() default 0;
}
