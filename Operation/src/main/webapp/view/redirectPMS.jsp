<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/commons/meta.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>POS设备管理</title>
	</head>
	<body>
		<script>
		jump();
		function jump(){
			window.open('http://192.168.3.111:65001/PManager/login.jsp');
		}
		</script>
		<a href='javascript:jump();' font-size='12'>点击这里跳转到PMS管理主界面</a>
	</body>
</html>
