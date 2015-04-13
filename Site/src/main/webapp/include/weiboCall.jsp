<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.vlives.site.util.SiteConfig"%>
<%@ page import="weibo4j.*" %>
<%@ page import="weibo4j.http.*" %>
 
<%
if("1".equals(request.getParameter("opt")))
{
	System.setProperty("weibo4j.oauth.consumerKey", SiteConfig.SINA_CONSUMER_KEY);
	System.setProperty("weibo4j.oauth.consumerSecret",SiteConfig.SINA_CONSUMER_SECRET);
	
	Weibo weibo = new Weibo();
	RequestToken requestToken = weibo.getOAuthRequestToken(SiteConfig.SINA_CALLBACKURL);

	System.out.println("Got request token.");
	System.out.println("Request token: " + requestToken.getToken());
	System.out.println("Request token secret: "
			+ requestToken.getTokenSecret());
	
	
 
	if(requestToken!=null){
		out.println(requestToken.getToken());
		out.println(requestToken.getTokenSecret());
		session.setAttribute("resToken",requestToken);
		response.sendRedirect(requestToken.getAuthorizationURL());

	}else{
		out.println("request error");
		}
}else{
%>
<a href="call.jsp?opt=1">请点击进行OAuth认证</a>   
<%}%>