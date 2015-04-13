<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商户合同管理</title>
	</head>
	<body>
		<div id="searchFormDivMpp" style="margin: 0 0 0 0;"></div>
		<div id="tableDivMpp" style="margin: 0 0 0 0;"></div>
		<script>   
			var contextPath='${ctx}'
		</script>
		<div id="searchFormDivRec" style="margin: 0 0 0 0;"></div>
		<div id="tableDivRec" style="margin: 0 0 0 0;"></div>
		<div id="topBarDiv" ></div>
		<div id="tableDiv"></div>
		<div id="loadModuleDiv"></div>
		<div id="appDiv"></div>                    
		<script type='text/javascript' src='${ctx}//extjs/plugins/Ext.ux.form.LovCombo.js'></script>
		<script type='text/javascript' src='${ctx}/view/cloudboss/coupon/js/listCouponStatistics.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Justin.ext.js'></script>
		<script type='text/javascript' src='${ctx}/extjs/plugins/Ext.ux.form.js'></script>
	</body>
</html>