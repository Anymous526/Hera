<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/meta.jsp"%>
<html>
	<head>
		<title>您没有权限访问该资源!</title>
	</head>

	<body>
		<script>   
			var contextPath='${ctx}';
			topWin = window;
 			while(topWin.parent != topWin.self)   
     		 {   
          		topWin = topWin.parent;   
     		 } 
			function toLoginPage(){
           		  topWin.location = contextPath+ "/view/security/jsp/login.jsp";
        	}
		</script>
		<div
			style='margin: 100 auto; text-align: center; font: bold 18px; color: #0066CC; vertical-align: middle'>
			对不起,您没有权限访问该资源!请尝试登录已授权的用户！
			<br>
			<br>
			<a href="#" onclick="toLoginPage();">到登录页面</a>
		</div>
	</body>
<html>