<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员消费月报</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/consume.js"></script>
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
						<li class="off"><a href="/manager/report/memberconsume/today.htm">今日消费</a></li>
						<li class="on">本月消费</li>
						<li class="off"><a href="/manager/report/memberconsume/list.htm">高级查询</a></li>
					</ul>
					<div class="R_excel">
						<a href="javascript:void()" onclick="com.report.exp_month()"><img src="/manager/image/btn/Export.png"
							alt="导出excel" /> </a>
					</div>
				</div>

				<!-- 今日新增 -->
				<div class="S_table" style="float:none" id="show0">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td width="10%">姓名</td>
							<td width="10%">手机号</td>
							<td width="10%">等级</td>
							<td width="10%">消费方式</td>
							<td width="10%">消费金额 (元)</td>
							<td width="17%">
							
								<select id="merchantId" name="merchantId" onchange="com.report.selectMonth(this)">
										<option value="">所属门店</option>
										<c:if test="${!empty children}">
										<c:forEach var="child" items="${children}">
											<option <c:if test="${child.id == merchantId}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
										</c:forEach>
										</c:if>
								</select>
							</td>
							<td width="13%">消费时间</td>
						</tr>
						<c:forEach items="${month}" var="tradeDetail">
							<tr>
								<td class="zuo">${tradeDetail.tradeOrder.member.user.name}</td>
								<td>${tradeDetail.tradeOrder.member.user.mobile}</td>
								<td>${tradeDetail.tradeOrder.member.level.desc}</td>
								<td>${tradeDetail.tradeOrder.type.desc}</td>
								<td>${tradeDetail.tradeOrder.money}</td>
								<td>${tradeDetail.tradeOrder.member.createMerchant.name}</td>
								<td><fmt:formatDate value="${tradeDetail.tradeDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
							</tr>
						</c:forEach>
						<tr>
							<td align="right" colspan="7">${pagination}</td>
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