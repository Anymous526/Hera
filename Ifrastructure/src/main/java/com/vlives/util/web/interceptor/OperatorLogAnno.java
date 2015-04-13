/**
 * @(#)Customer.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.web.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vlives.boss.operator.domain.OperatorLog.OperatorType;

/**
 * 管理员操作日志的Annotation
 * @author  jianguo.xu
 * @version 1.0,2011-4-27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatorLogAnno {
	/**
	 * 操作类型
	 * @author jianguo.xu
	 * @return
	 */
	OperatorType value();
}





