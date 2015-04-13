<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels"
	value="com.vlives.boss.member.domain.Level@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员消费高级查询</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/consume.js"></script>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
window.addEvent("domready",function(){
    new Calendar(
      {startTradeDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new Calendar(
      {endTradeDate:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
    );
});
</script>
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
						<li class="off"><a href="/manager/report/memberconsume/month.htm">本月消费</a></li>
						<li class="on">高级查询</li>
					</ul>
					<div class="R_excel">
						<input type="image" onclick="com.report.exp_list()" src="/manager/image/btn/Export.png"/>
					</div>
				</div>
				
					<form id="myform" action="/manager/report/memberconsume/list.htm" method="post">
					<div class="M_ss" style="margin-top: 8px;">
							<ul>
								<li>会员姓名 <input name="name" value="${myname}" type="text" />
									手机号码 <input name="mobile" value="${mobile}" type="text" />
									<c:if test="${!empty children}">
										所属门店  <select name="merchantId">
											<option value="">全部</option>
											<c:forEach var="child" items="${children}">
												<option <c:if test="${child.id == createMerchant.id}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
											</c:forEach>
										</select>
									</c:if></li>
								<li>会员等级 <select name="level">
										<option value="0">全部</option>
										<c:forEach var="levels" items="${levels}">
											<option <c:if test="${levels == level}">selected="selected"</c:if> value="${levels.value}">${levels.desc}</option>
										</c:forEach>
								</select> <span> 消费时间 <input name="startTradeDate" value="<fmt:formatDate value='${startTradeDate}' pattern="yyyy-MM-dd"/>" id="startTradeDate" type="text" /> 
								到<input name="endTradeDate" value="<fmt:formatDate value='${endTradeDate}' pattern="yyyy-MM-dd"/>" id="endTradeDate" type="text" /> </span>
								<input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/></li>
							</ul>
					</div>
					</form>
					<div class="S_table" style="float:none" id="show0">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td width="10%">姓名</td>
							<td width="10%">手机号</td>
							<td width="10%">等级</td>
							<td width="10%">消费方式</td>
							<td width="10%">消费金额 (元)</td>
							<td width="17%">所属门店</td>
							<td width="13%">消费时间</td>
						</tr>
						<c:forEach items="${list}" var="tradeDetail">
							<tr>
								<td class="zuo">${tradeDetail.tradeOrder.member.user.name}&nbsp;</td>
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