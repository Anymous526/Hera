/**
 * @(#)Customer.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.vaildator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *验证注解
 * @author  jianguo.xu
 * @version 1.0,2011-4-27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidator {
	/**
	 * 请求参数
	 * @author jianguo.xu
	 * @return
	 */
	String param();
	/**
	 * 请求参数对应的中文名字
	 * @author jianguo.xu
	 * @return
	 */
	String paramName();
	
	/**
	 * 验证类型
	 * @author jianguo.xu
	 * @return
	 */
	ValidatorType[] vaildatorTypes()default{};
	 
	/**
	 * 验证长度
	 * 如果只有一个整数则表示参数的长度和该整数要一样长
	 * @author jianguo.xu
	 * @return
	 */
	int[] length()default {};
	
	int min() default Integer.MIN_VALUE;
	int max() default Integer.MAX_VALUE;
}





