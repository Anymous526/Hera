<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<c:set var="ctx" value="${pageContext.request.contextPath}"  /> 
		<script>   
			var contextPath='${ctx}'
		</script>
		<link rel="stylesheet" type="text/css" href="${ctx}/extjs/ext-3.2.1/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />
		<!-- ext -->
		<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/adapter/ext/ext-base-debug.js"></script>
		<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/ext-all-debug.js"></script>
		<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/extjs/plugins/Justin.ext.js"></script>
		
		<script type="text/javascript" src="${ctx}/extjs/plugins/Ext.ux.plugins.js"></script>
		
		<script type="text/javascript" src="${ctx}/extjs/plugins/Ext.ux.simpleGrid.js"></script>
		<!-- dwr -->
		<script type='text/javascript' src="${ctx}/libs/dwr/engine.js"></script>
		<script type='text/javascript' src="${ctx}/libs/dwr/util.js"></script>
		<script type='text/javascript' src="${ctx}/errors/sessionout.js"></script>	
		<script>   
			var ctxsession='${ctx}'
		</script>	
	</head>
	<body> 
	</body>
</html>
