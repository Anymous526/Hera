/**
 * @(#)ErrorController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.util.StringUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-4-18
 */
@Controller
public class ErrorController {
	private static final Log LOG = LogFactory.getLog(ErrorController.class);
	
	private static final String ATT_ERROR_URI ="javax.servlet.error.request_uri";
	private static final String ATT_ERROR_QUERY_STRING ="javax.servlet.forward.query_string";
	private static final String ATT_ERROR_CODE_KEY ="javax.servlet.error.status_code";	
	private static final String ATT_ERROR_EXCEPTION_KEY ="javax.servlet.error.exception";
	private static final String ATT_ERROR_MSG_KEY ="javax.servlet.error.message";
	
	@RequestMapping("/error/500.htm")
	public ModelAndView handle500(HttpServletRequest request, HttpServletResponse response){
		log(request);
		return new ModelAndView(new RedirectView("/error/500.jsp?source=1"));
		
	}
	
	@RequestMapping("/error/503.htm")
	public ModelAndView handle503(HttpServletRequest request, HttpServletResponse response){
		log(request);
		return new ModelAndView(new RedirectView("/error/500.jsp?source=1"));
	}
	
	private void log(HttpServletRequest request) {
		LOG.info("error request url : "+getRequestUrl(request));
		LOG.info("error code : "+request.getAttribute(ATT_ERROR_CODE_KEY));
		LOG.info("error exception : "+request.getAttribute(ATT_ERROR_EXCEPTION_KEY));
		LOG.info("error message : "+request.getAttribute(ATT_ERROR_MSG_KEY));
	}
	
	private String getRequestUrl(HttpServletRequest request) {
		String requestUri = (String) request.getAttribute(ATT_ERROR_URI);
		String queryString = (String) request.getAttribute(ATT_ERROR_QUERY_STRING);
		return requestUri+((StringUtils.isNullOrEmpty(queryString))?"":"?"+queryString);
	}
	
	
	@RequestMapping("/error/404.htm")
	public ModelAndView handle404(HttpServletRequest request, HttpServletResponse response){
		LOG.info("request url  not url: "+getRequestUrl(request));
		return new ModelAndView(new RedirectView("/error/404.jsp?source=1"));
	}
}
