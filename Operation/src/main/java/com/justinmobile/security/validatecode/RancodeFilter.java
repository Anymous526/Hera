package com.justinmobile.security.validatecode;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 验证码校验过滤器
 * @author DuWei
 *
 */

public class RancodeFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String randCode = req.getParameter("randCode");
		String trueRandCode = String.valueOf(session.getAttribute("trueRandCode"));
		String contextPath = req.getContextPath();

		if (randCode == null || trueRandCode == null
				|| !randCode.toLowerCase().equals(trueRandCode.toLowerCase())) {
			res.sendRedirect(contextPath+"/view/security/jsp/login.jsp?login_error=code_error");
		} else {
			chain.doFilter(request, response);
			 
		}
		System.out.println("rancodeFilter's work is done");
	}
}