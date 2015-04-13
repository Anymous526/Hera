/**
 * @(#)Init.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置有该注解的方法在系统启动后将被调用 <br/>
 * 如果是是非静态方法，则方法对应的类必须有默认构造函数<br/>
 * 方法上不能带有参数，否则将被忽略掉<br/>
 * @author  jianguo.xu
 * @version 1.0,2011-7-29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InitMethod {
	boolean isStatic();
}
