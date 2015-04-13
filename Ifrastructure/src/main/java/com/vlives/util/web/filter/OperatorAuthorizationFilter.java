package com.vlives.util.web.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.util.AntUrlPathMatcher;

import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.manager.OperatorIdentityValidator;
import com.vlives.core.security.IdentityValidator;
import com.vlives.core.security.Principal;
import com.vlives.core.web.WebApplicationContextUtils;
import com.vlives.util.StringUtils;



/**
 * 管理员权限过滤钱
 * @author  jianguo.xu
 * @version 1.0,2010-12-20
 */
public class OperatorAuthorizationFilter implements Filter {
	private static final AntUrlPathMatcher urlMatcher = new AntUrlPathMatcher();
	private String[] excludeUrls;
	private String[] excludeAuthorizationUrls;
	private ServletContext ctx;
	private IdentityValidator identityValidator;
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String thisUrl = httpRequest.getRequestURI();
		if ((!thisUrl.endsWith(".jsp") && !thisUrl.endsWith(".htm")&& !thisUrl.endsWith(".html") && !thisUrl
				.endsWith("/"))
				|| exclude(thisUrl)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		Principal principal = identityValidator.currentPrincipal();
		if (principal != null && principal instanceof Operator) {
			Operator operator = (Operator)principal;
			httpRequest.setAttribute(OperatorIdentityValidator.PRINCIPALCOOKIE_NAME,principal);
			if(excludeAuthorization(thisUrl)||operator.isPermissible(thisUrl)) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			}
			else{
				httpResponse.sendError(401,"非法权限");
				return;
			}
		} else {
			String queryString = httpRequest.getQueryString();
			String redirectUrl = queryString == null ? thisUrl : thisUrl + "?"
					+ queryString;
			httpResponse.sendRedirect("/manager/login.htm?from="
					+ URLEncoder.encode(redirectUrl, "utf-8"));
		}
	}
	private boolean exclude(String url) {
		urlMatcher.setRequiresLowerCaseUrl(true);
		if (excludeUrls != null) {
			for (int i = 0; i < excludeUrls.length; i++)
				if (urlMatcher.pathMatchesUrl(excludeUrls[i], url))
					return true;
		}
		return false;
	}
	
	private boolean excludeAuthorization(String url) {
		urlMatcher.setRequiresLowerCaseUrl(true);
		if (excludeAuthorizationUrls != null) {
			for (int i = 0; i < excludeAuthorizationUrls.length; i++)
				if (urlMatcher.pathMatchesUrl(excludeAuthorizationUrls[i], url))
					return true;
		}
		return false;
	}
	
	public void init(FilterConfig config) throws ServletException {
		
		ctx = config.getServletContext();
		identityValidator = (IdentityValidator) WebApplicationContextUtils.getService("operatorIdentityValidator", ctx);
		if (config.getInitParameter("excludeUrls") == null)
			return;
		String urlString = config.getInitParameter("excludeUrls").trim();
		if (!StringUtils.isNullOrEmpty(urlString)) {
			excludeUrls = urlString.split(",");
		}
		excludeAuthorizationUrls = config.getInitParameter("excludeAuthorizationUrls").trim().split(",");
	}

	public void destroy() {
		
	}
 
}
