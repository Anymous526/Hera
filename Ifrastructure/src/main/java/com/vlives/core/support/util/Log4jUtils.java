/**
 * @(#)Log4jUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.vlives.core.support.spring.anno.InitMethod;
import com.vlives.util.ConfigUtils;

/**
 * 配置LOG4J的目录<br/>
 * 基于WEB路径LOG动态目录配置
 * @author  jianguo.xu
 * @version 1.0,2011-7-30
 */
public final class Log4jUtils {
	/**
	 * LOG4J 的配置文件名字
	 */
	private static final String LOG4J_CONFIG_NAME="log4j.properties";
	/**
	 * LOG日志的目录
	 */
	private static final String LOG_PATH_NAME="logs";
	/**
	 * LOG日志的文件名
	 */
	private static final String LOG_FILE_NAME="log";
	@InitMethod(isStatic=true)
	public static void initLog4jConfig() {
		File logPath = getLogPath();
		Properties prop = loadLog4jPro();
		String log4jFileProp = logPath.getAbsolutePath();
		log4jFileProp+=log4jFileProp.endsWith("/")?LOG_FILE_NAME:("/"+LOG_FILE_NAME);
		prop.setProperty("log4j.appender.file.File",log4jFileProp);
		PropertyConfigurator.configure(prop);		
	}
	/**
	 * 得到LOG4J目录的文件
	 * @author jianguo.xu
	 * @return
	 */
	private static File getLogPath() {
		String webRoot = ConfigUtils.WEB_ROOT;
		File file = new File(webRoot);
		File parent = file.getParentFile();
		File log4jPath = new File(parent, LOG_PATH_NAME);
		if(log4jPath.isDirectory()) {
			log4jPath.mkdirs();
		}
		return log4jPath;
	}
	/**
	 * 得到LOG4J的属性配置
	 * @author jianguo.xu
	 * @return
	 */
	private static Properties loadLog4jPro() {
		URL url = Log4jUtils.class.getResource("/");
		String path = url.getPath();
		try {
			InputStream ins =  new FileInputStream(path+LOG4J_CONFIG_NAME);
			Properties prop = new Properties();
			prop.load(ins);
			ins.close(); 
			return prop;
		} catch (Exception e) {
			throw new RuntimeException("load log4j properties error", e);
		}
	}
}
