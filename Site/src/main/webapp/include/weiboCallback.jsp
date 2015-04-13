<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.vlives.core.security.Principal"%>
<%@ page import="com.vlives.core.security.IdentityValidator"%>
<%@ page import="com.vlives.core.web.WebApplicationContextUtils"%>
<%@ page import="com.vlives.site.util.SiteConfig"%>
<%@ page import="weibo4j.http.*"%>
<%@ page import="weibo4j.*"%>
<%
	String verifier = request.getParameter("oauth_verifier");
	if (verifier != null) {
		RequestToken resToken = (RequestToken) session
				.getAttribute("resToken");

		if (resToken != null) {
			System.setProperty("weibo4j.oauth.consumerKey",
					SiteConfig.SINA_CONSUMER_KEY);
			System.setProperty("weibo4j.oauth.consumerSecret",
					SiteConfig.SINA_CONSUMER_SECRET);

			Weibo weibo = new Weibo();
			AccessToken accessToken = weibo.getOAuthAccessToken(
					resToken.getToken(), resToken.getTokenSecret(),
					verifier);
			IdentityValidator identityValidator = (IdentityValidator) WebApplicationContextUtils
					.getService("userIdentityValidator",
							session.getServletContext());
			Principal _user = identityValidator.currentPrincipal();
			if (accessToken != null) {
				String token = accessToken.getToken();
				String secret = accessToken.getTokenSecret();
				long userId = accessToken.getUserId();

				User user = weibo.showUser(String.valueOf(userId));
				String nickname = user.getScreenName();
				String figureurl = String.valueOf(user
						.getProfileImageURL());

				out.println("<script>");
				if (_user != null) {
					out.println("window.opener.com.vlives.member.sinaLogin_user('"
							+ token + "','" + secret + "','" + userId
							+ "','" + nickname + "','" + figureurl
							+ "');");
				} else {
					out.println("window.opener.sinaLogin('" + token
							+ "','" + secret + "','" + userId + "','"
							+ nickname + "','" + figureurl + "');");
				}
				out.println("window.opener.closeChildWindow();");
				out.println("</script>");
			} else {
				out.println("access token request error");
			}

		} else {
			out.println("request token session error");
		}
	} else {
		out.println("verifier String error");
	}
%>
