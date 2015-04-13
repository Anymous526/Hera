<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>System User management page</title>
	</head>
	<body> 
 		<script>   
			var contextPath='${ctx}'
		</script>
		<div id='topBarDiv'></div>
		<div id="tableDivUser"></div>
		<script type='text/javascript' src='${ctx}/dwr/interface/userServ.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/editUser.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/selectRole.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/selectCity.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/listUser.js'></script>
	</body>
</html>
