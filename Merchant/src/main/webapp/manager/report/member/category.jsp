<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员分类统计</title>
<c:import url="/manager/include/head_include.jsp" />
</head>
<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp" />
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm" />
		<!--right内容-->
		<div id="right">
			<div class="turn_btn">
				<a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4"
					height="37" /> </a>
			</div>
			<div class="right_all" style="*float:left; *margin-left:-0.1px;">
				<!--内容title-->
				<div class="R_top">
					<ul>
						<li class="off"><a href="/manager/report/memberinfo/today.htm">今日新增</a></li>
						<li class="off"><a href="/manager/report/memberinfo/month.htm">本月新增</a></li>
						<li class="on">会员分类</li>
						<li class="off"><a href="/manager/report/memberinfo/list.htm">高级查询</a></li>
					</ul>
				</div>

				<!-- 今日新增 -->
				<div class="S_table" style="float:none;" id="show0">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td align="center">统计类型</td>
							<td>普通会员</td>
							<td>银卡会员</td>
							<td>金卡会员</td>
							<td>钻石会员</td>
						</tr>
						<tr>
						<td class="zuo">今日新增</td>
						<c:forEach var="today" items="${today}">
							
							<td>
							<c:if test="${today.member_level == 1}">${today.count}</c:if>
							<c:if test="${today.member_level == 2}">${today.count}</c:if>
							<c:if test="${today.member_level == 3}">${today.count}</c:if>
							<c:if test="${today.member_level == 4}">${today.count}</c:if>
							</td>

						</c:forEach>
						<c:if test="${fn:length(today) == 0}"><td>0</td><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(today) == 1}"><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(today) == 2}"><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(today) == 3}"><td>0</td></c:if>
						</tr>
						
						<tr class="S_t_01">
							<td class="zuo">本月新增</td>
							<c:forEach var="month" items="${month}" >
							  <td>
							    <c:if test="${month.member_level == 1}">${month.count}</c:if>
							    <c:if test="${month.member_level == 2}">${month.count}</c:if>
							    <c:if test="${month.member_level == 3}">${month.count}</c:if>
							    <c:if test="${month.member_level == 4}">${month.count}</c:if>
							  </td>
							</c:forEach>
						<c:if test="${fn:length(month) == 0}"><td>0</td><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(month) == 1}"><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(month) == 2}"><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(month) == 3}"><td>0</td></c:if>

						</tr>
						<tr>
							<td class="zuo">百分比</td>
							<c:forEach var="list" items="${list}">
							<td>
							  ${list}%
							</td>
							</c:forEach>
						
						<c:if test="${fn:length(list) == 0}"><td>0</td><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(list) == 1}"><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(list) == 2}"><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(list) == 3}"><td>0</td></c:if>
						</tr>
						<tr class="S_t_01">
							<td class="zuo">总数</td>
							<c:forEach var="all" items="${all}" >
							  <td>
							    <c:if test="${all.member_level == 1}">${all.count}</c:if>
							    <c:if test="${all.member_level == 2}">${all.count}</c:if>
							    <c:if test="${all.member_level == 3}">${all.count}</c:if>
							    <c:if test="${all.member_level == 4}">${all.count}</c:if>
							  </td>
							</c:forEach>
						<c:if test="${fn:length(all) == 0}"><td>0</td><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(all) == 1}"><td>0</td><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(all) == 2}"><td>0</td><td>0</td></c:if>
						<c:if test="${fn:length(all) == 3}"><td>0</td></c:if>
						</tr>
					</table>
					<div style="clear: both"></div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp" />
</body>
</html>