/*
 * @(#)BasDaoAnnotation.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */

package com.justinmobile.bmp.pos.mina.request;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 定义请求类型的
 * @author ThinkPad7
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.METHOD,ElementType.TYPE}) 
public @interface PosRequestCodeAnnotation {
	public PosRequestCode value();
}