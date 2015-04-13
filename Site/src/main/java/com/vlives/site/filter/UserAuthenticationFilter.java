package com.vlives.site.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.vlives.core.security.IdentityValidator;
import com.vlives.core.web.WebApplicationContextUtils;

/**
 * UserAuthenticationFilter用来检测当用户想访问需要用户验证的页面时（"/user" 开头）是否登录。
 * 
 * @author 李季
 *
 */
public class UserAuthenticationFilter implements Filter {
	
	private ServletContext ctx;
	private IdentityValidator identityValidator;

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 检查用户是否登录，如果没有登录，跳转到首页
		if (identityValidator.currentPrincipal() != null) {
			filterChain.doFilter(request, response);
			return;
		} else {
			httpResponse.sendRedirect("/index");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ctx = filterConfig.getServletContext();
		identityValidator = (IdentityValidator) WebApplicationContextUtils.getService("userIdentityValidator", ctx);
	}

}
