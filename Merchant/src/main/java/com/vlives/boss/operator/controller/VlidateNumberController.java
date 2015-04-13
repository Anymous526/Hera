/*
 * @(#)VlidateNumberController.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.operator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vlives.core.security.ValidatorNumberUtils;

/**
 * description
 * @author  fyuan
 * @version 1.0,2011-6-22
 */
@Controller
@RequestMapping("/manager/common")
public class VlidateNumberController {
	/**
	 * 创建验证码
	 * @author jianguo.xu
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/random_code.htm")
	public void createValidateNumber(HttpServletResponse response) throws IOException{
		ValidatorNumberUtils.create(response);
	}
	 
}

