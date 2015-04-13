<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
	</head>
	<body>
		<script>   
 			var contextPath='${ctx}'
 			topWin = window;
 			while(topWin.parent != topWin.self)   
     		 {   
          		topWin = topWin.parent;   
     		 } 
			topWin.location=contextPath + "/view/security/jsp/login.jsp?login_error=session_expired";
		</script>
	</body>
</html>
