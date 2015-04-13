<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>System Menu management page</title>
	</head>
	<body> 
 		<script>   
			var contextPath='${ctx}'
		</script>
		<div id='topBarDiv'></div>
		<div id="tableDivMenu"></div>
		<script type='text/javascript' src='${ctx}/dwr/interface/menuServ.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Ext.ux.formPanel.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/editMenu.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/listMenu.js'></script>
	</body>
</html>
