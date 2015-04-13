package com.justinmobile.bmp.util;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;

public class SpringSecurityUtils {

	public static final String ANONYMOUS_USER = "anonymousUser";

	/**
	 * 取得当前用户, 返回值为SpringSecurity的User类或其子类, 如果当前用户未登录则返回null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends User> T getCurrentUser() {
	//	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (!(principal instanceof User)) {
			return null;
		}
		return (T) principal;
	}
	
	/**
	 * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		Authentication authentication = getAuthentication();
		String currentUserName = authentication.getName();
		if (authentication == null || authentication.getPrincipal() == null || ANONYMOUS_USER.equals(currentUserName)) {
			return "";
		}
		return currentUserName;
	}
	
	/**
	 * 取得Authentication, 如当前SecurityContext为空时返回null.
	 */
	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}
		return context.getAuthentication();
	}
}
