<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>城市选择POS应用</title>
	</head>
	<body>
		<script>   
			var contextPath='${ctx}'
		</script>
		<div id='topBarDiv'></div>
		<div id="tableDiv"></div>           
		<script type='text/javascript' src='${ctx}/view/pos/js/citySelectApp.js'></script>        
		<script type='text/javascript' src='${ctx}/view/pos/js/listCity.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Justin.ext.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Ext.ux.form.js'></script>
	</body>
</html>