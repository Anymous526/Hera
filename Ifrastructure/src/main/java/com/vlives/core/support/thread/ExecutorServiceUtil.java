/**
 * @(#)ExecutorServiceUtil.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JDK 1.6新的并发工具类
 * @author  jianguo.xu
 * @version 1.0,2011-9-11
 */
public class ExecutorServiceUtil {
	
	 
	private static ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public static void execute(Runnable command) {
		executorService.execute(command);
	 
	}
 
	
	
	 
}
