<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>System Authority management page</title>
	</head>
	<body> 
 		<script>   
			var contextPath='${ctx}'
		</script>
		<div id='topBarDiv'></div>
		<div id="tableDivAuth"></div>
		<script type='text/javascript' src='${ctx}/dwr/interface/authServ.js'></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/resServ.js'></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/menuServ.js'></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/TreeBuilder.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/editAuth.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/selectResource.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/selectMenus.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/listAuthority.js'></script>
	</body>
</html>
