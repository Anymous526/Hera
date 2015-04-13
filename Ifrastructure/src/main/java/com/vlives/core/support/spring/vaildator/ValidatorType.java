/**
 * @(#)ParamType.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.vaildator;

import java.util.regex.Pattern;

import com.vlives.util.DateUtils;

/**
 * 验证类型
 * @author  jianguo.xu
 * @version 1.0,2011-6-11
 */
public enum ValidatorType {
	REQUIRED("^\\S+","{0}不能为空"),
	DIGIT("[-]{0,1}\\d+","{0}必须为数字"),
	DATE("yyyy-MM-dd","{0}日期格式不正确"),
	DATE_TIME("yyyy-MM-dd HH:mm:ss","{0}日期格式不正确"),
	TIME("HH:mm:ss","{0}时间格式不正确"),
	MOBILE("^1\\d{10}","{0}格式不正确");
	private final String regex;	
	private final String errorTemplate;
	private ValidatorType(String regex,String errorTemplate) {
		this.regex = regex;
		this.errorTemplate = errorTemplate;
	}
	public String getRegex() {
		return regex;
	}
	public boolean validator(String input) {
		if(this == DATE||this== DATE_TIME||this == TIME) {
			return validatorDate(input);
		}
		return Pattern.matches(regex, input);
	}
	
	private boolean validatorDate(String input) {
		try {
			DateUtils.parserDate(input, regex);
			return true;
		}catch (RuntimeException e) {
			return false;
		}
		 
	}
	
	public String getErrorMsg (String input) {
		return errorTemplate.replaceAll("\\{0\\}", input);
	}
	
	/*public static void main(String[] args) {
		boolean p= Pattern.matches("[-]{0,1}\\d+", "1111");
		System.out.println(p);
	}*/
	 
}

 
