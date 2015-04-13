<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>行业管理</title>
	</head>
	<body>
		
		<script>   
			var contextPath='${ctx}'
		</script>
		<script type='text/javascript' src='${ctx}/view/cloudboss/splitmanager/js/industry.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Ext.ux.form.js'></script>
		<script type="text/javascript" src="${ctx}/dwr/interface/IndustryManager.js"></script>
		
	</body>
</html>