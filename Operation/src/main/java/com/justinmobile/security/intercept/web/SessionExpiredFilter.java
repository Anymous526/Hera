package com.justinmobile.security.intercept.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;

import com.justinmobile.core.web.BaseAjaxController;

public class SessionExpiredFilter extends BaseAjaxController implements Filter {

	private String ignoreHandlers;

	private static String METHOD_URL = "method=";
	
	private static String[] commonSuffix = new String[] {"swf", "gif", "jpg", "png", "css"};

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String queryString = req.getQueryString();
		String resString = req.getServletPath();
		if (isCommonResources(queryString, resString)) {
			chain.doFilter(request, response);
			return;
		}
		if (StringUtils.isNotBlank(ignoreHandlers)) {
			boolean ignore = isIgnore(resString);
			if (ignore) {
				chain.doFilter(request, response);
			} else {
				checkLogin(request, response, chain, res);
			}
		} else {
			checkLogin(request, response, chain, res);
		}
	}

	private boolean isCommonResources(String queryString, String resString) {
		if( StringUtils.isBlank(queryString) || queryString.indexOf(METHOD_URL) == -1) {
			return true;
		}
		int suffixIndex = resString.lastIndexOf(".");
		if (suffixIndex > 0) {
			String suffix = resString.substring(suffixIndex + 1);
			if (ArrayUtils.contains(commonSuffix, suffix)) {
				return true;
			}
		}
		return false;
	}

	private boolean isIgnore(String queryString) {
		String[] ignoreHandler = ignoreHandlers.split(",");
		boolean ignore = false;
		for (String handler : ignoreHandler) {
			if (queryString.indexOf(handler) != -1) {
				ignore = true;
				break;
			}
		}
		return ignore;
	}

	private void checkLogin(ServletRequest request, ServletResponse response, FilterChain chain, HttpServletResponse res) {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (user == null) {
				res.setHeader("sessionstatus", "true");
				return;
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			System.out.println("session out !");
			e.printStackTrace();
			res.setHeader("sessionstatus", "true");
		}
	}

	public final void init(FilterConfig filterConfig) throws ServletException {
		this.ignoreHandlers = filterConfig.getInitParameter("ignoreHandlers");
	}

}
