<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>登录系统</title>
	</head>
	<body>
		<div id="login-win">
			<div id="login-tabs">
				<img src="${ctx}/images/banner.jpg" id='banner' style='display: none' />
			</div>
			<div id="loginInfo"></div>
			<div id="message"></div>
		</div>
		<script>
			var contextPath='${pageContext.request.contextPath}';
		</script>			
		<script type="text/javascript" src="${ctx}/view/security/js/login.js"></script>
	</body>
</html>
