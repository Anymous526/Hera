<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>System Role management page</title>
	</head>
	<body> 
 		<script>   
			var contextPath='${ctx}'
		</script>
		<div id='topBarDiv'></div>
		<div id="tableDivRole"></div>
		<script type='text/javascript' src='${ctx}/dwr/interface/roleServ.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/editRole.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/selectAuth.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/listRole.js'></script>
	</body>
</html>
