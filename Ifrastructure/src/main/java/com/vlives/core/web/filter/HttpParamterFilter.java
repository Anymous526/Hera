package com.vlives.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vlives.core.web.HttpParameterParser;



/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2010-12-20
 */
public class HttpParamterFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpParameterParser.init(httpRequest);
		chain.doFilter(httpRequest, httpResponse);
	 
	}

	 

	public void init(FilterConfig config) throws ServletException {
	 
	}

	public void destroy() {
		
	}
}
