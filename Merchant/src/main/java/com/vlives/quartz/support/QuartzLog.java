/**
 * @(#)Init.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.quartz.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vlives.quartz.domain.TimingTaskLog.TaskName;


/**
 * 定时任务执行日志记录
 * 在要执行定时任务方法添加该日志记录功能
 * @author MrXu
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QuartzLog {
	TaskName name();
}
