package com.justinmobile.security.intercept.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.concurrent.SessionInformation;
import org.springframework.security.concurrent.SessionRegistry;
import org.springframework.security.concurrent.SessionRegistryUtils;
import org.springframework.security.providers.dao.UserCache;
import org.springframework.security.ui.logout.LogoutHandler;
import org.springframework.security.userdetails.UserDetails;


/**
 * 简单的将session清空。
 *
 * @author sshwsfc
 */
public class SessionLogoutHandler implements LogoutHandler {

	private SessionRegistry sessionRegistry;
	
	private UserCache userCache;
	
    /**
     * @param request        not used (can be <code>null</code>)
     * @param response       not used (can be <code>null</code>)
     * @param authentication not used (can be <code>null</code>)
     */
    public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {

		// 因为使用了concurrentSessionController 在限制用户登陆,所以登出时移除相应的session信息
		Object principal = authentication.getPrincipal();
		userCache.removeUserFromCache(((UserDetails) principal).getUsername());
		SessionInformation[] sessions = sessionRegistry.getAllSessions(principal, false);
		if (sessions != null && sessions.length > 0) {
			for (SessionInformation sessionInfo : sessions) {
				sessionRegistry.removeSessionInformation(sessionInfo
						.getSessionId());
				sessionInfo.expireNow();

			}
		}
		String sessionId = SessionRegistryUtils.obtainSessionIdFromAuthentication(authentication);
		sessionRegistry.removeSessionInformation(sessionId);
		sessionRegistry.removeSessionInformation(request.getSession().getId());
		request.getSession().invalidate();
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}
    
    
}
