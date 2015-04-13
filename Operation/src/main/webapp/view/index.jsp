<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/commons/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>高阳会生活运营管理平台</title>
		</head>
	<body>
		<script>   
		var contextPath='${pageContext.request.contextPath}';
		var selfservice = false;
		var menuTitle = "会生活运营管理平台菜单";
		</script>		
		<script type='text/javascript' src='${ctx}/dwr/interface/TreeBuilder.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/changePassword.js'></script>
		<script type='text/javascript' src='${ctx}/view/security/js/layout.js'></script>
	</body>
</html>
