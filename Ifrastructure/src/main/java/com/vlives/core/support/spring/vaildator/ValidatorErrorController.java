/**
 * @(#)RegisterControler.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.core.support.spring.vaildator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.core.support.spring.view.JsonView;

/**
 * 验证失败后采用此controller进行统一处理
 * @author  jianguo.xu
 * @version 1.0,2011-3-22
 */
@Controller
public class ValidatorErrorController {
	@RequestMapping("/jsonError.htm")
	public ModelAndView errorToJson(HttpServletRequest request) {
		String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", false);
		model.put("desc", errorMsg);
		
		return new ModelAndView(new JsonView(model));
	}
 
  
}	
