/**
 * @(#)UserFilter.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.site.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vlives.boss.user.domain.User;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.Principal;
import com.vlives.core.web.WebApplicationContextUtils;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-12-20
 */
public class UserFilter implements Filter {

//	private String[] excludeUrls;
	private ServletContext ctx;
	private IdentityValidator identityValidator;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Principal principal = identityValidator.currentPrincipal();
		if (principal != null && principal instanceof User) {
			User user = (User)principal;
			httpRequest.setAttribute("principal", user);
		}

		chain.doFilter(httpRequest, httpResponse);
		return;
	}
 
//	private boolean exclude(String url) {
//		if (excludeUrls != null) {
//			for (int i = 0; i < excludeUrls.length; i++)
//				if (url.equals(excludeUrls[i]))
//					return true;
//		}
//		return false;
//	}

	public void init(FilterConfig config) throws ServletException {
		ctx = config.getServletContext();
		identityValidator = (IdentityValidator) WebApplicationContextUtils.getService("userIdentityValidator", ctx);
//		String urlString = config.getInitParameter("excludeUrls").trim();
//		if (!StringUtils.isNullOrEmpty(urlString))
//			excludeUrls = urlString.split(",");
	}

	public void destroy() {
		
	}

}
