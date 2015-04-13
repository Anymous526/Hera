<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()" />
<j:getStatic var="consumeTypes" value="com.vlives.boss.trade.domain.TradeOrder$Type@values()" />
<j:getStatic var="pointTypes" value="com.vlives.boss.member.domain.MemberPointDetail$Type@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 积分高级查询</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/memberpoint.js"></script>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
window.addEvent("domready",function(){
    new Calendar(
      {startConsumeDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new Calendar(
      {endConsumeDate:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
    );
    new Calendar(
      {startCreateDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new Calendar(
      {endCreateDate:'Y-m-d'}, 
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
					height="37" />
				</a>
			</div>
			<div class="right_all" style="*float:left; *margin-left:-0.1px;">
				<!--内容title-->
				<div class="R_top">
					<ul>
						<li class="off"><a
							href="/manager/report/memberpoint/find.htm">积分报表</a>
						</li>
						<li class="on">高级查询
						</li>
					</ul>
					<div class="R_excel">
						<a href="javascript:void(0)" onclick="com.report.exp_list()"><img src="/manager/image/btn/Export.png"
							alt="导出excel" />
						</a>
					</div>
				</div>
				<!--数据表格-->
				<form id="myform" action="/manager/report/memberpoint/list.htm" method="post">
				<div class="M_ss" style="margin-top: 8px;">
					<ul>
						<li>会员姓名 <input name="name" value="${myname}" type="text" />
						手机号码 <input name="mobile" value="${mobile}"
							type="text" /> 会员等级 <select name="level">
										<option value="0">全部</option>
										<c:forEach var="levels" items="${levels}">
											<option <c:if test="${levels == level}">selected="selected"</c:if> value="${levels.value}">${levels.desc}</option>
										</c:forEach>
								</select></li>
								
						<li>
						<c:if test="${!empty children}">
						消费门店
						<select name="merchantId">
							<option value="">全部</option>
							<c:forEach var="child" items="${children}">
								<option <c:if test="${child.id == merchant.id}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
							</c:forEach>
						</select>
						</c:if>
						积分变更方式<select name="pointType">
										<option value="0">全部</option>
										<c:forEach var="type" items="${pointTypes}">
											<option <c:if test="${type == pointType}">selected="selected"</c:if> value="${type.value}">${type.desc}</option>
										</c:forEach>
								</select>
						 交易方式 <select name="type">
						<option value="0">全部</option>
						<c:forEach var="consumeType" items="${consumeTypes}">
							<option <c:if test="${consumeType == type}">selected="selected"</c:if> value="${consumeType.value}">${consumeType.desc}</option>
						</c:forEach>
						</select></li>
						<li><span>注册时间 <input name="startCreateDate" value="<fmt:formatDate value='${startCreateDate}' pattern="yyyy-MM-dd"/>" id="startCreateDate" type="text" /> 到<input
								name="endCreateDate" value="<fmt:formatDate value='${endCreateDate}' pattern="yyyy-MM-dd"/>" id="endCreateDate" type="text" />
						</span> <span>消费时间 <input name="startConsumeDate" value="<fmt:formatDate value='${startConsumeDate}' pattern="yyyy-MM-dd"/>" id="startConsumeDate" type="text" /> 到
						<input name="endConsumeDate" value="<fmt:formatDate value='${endConsumeDate}' pattern="yyyy-MM-dd"/>" id="endConsumeDate" type="text" />
						</span> <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/>
						 </li>
					</ul>
				</div>
				</form>
				<div class="S_table" style="none">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td width="8%">姓名</td>
							<td width="8%">手机号</td>
							<td width="13%">消费时间</td>
							<td width="18%">消费门店</td>
							<td width="12%">积分变更类型</td>
							<td width="10%">交易方式</td>
							<td width="10%">当次积分</td>
							<td width="10%">可用积分</td>
							<td width="10%">总积分</td>
						</tr>
						<c:forEach items="${list}" var="point">
							<tr>
								<td class="zuo">${point.member.user.name}&nbsp;</td>
								<td>${point.member.user.mobile}&nbsp;</td>
						<td><fmt:formatDate value="${point.tradeOrder.tradeDate}"
										pattern="yyyy-MM-dd HH:mm" />&nbsp;</td>
								<td>${point.tradeOrder.merchant.name}&nbsp;</td>
								<td>${point.type.desc}&nbsp;</td>
								<td>${point.tradeOrder.type.desc}&nbsp;</td>
								<td>${point.point}&nbsp;</td>
								<td>${point.usablePoint}&nbsp;</td>
								<td>${point.totalPoint}&nbsp;</td>
							</tr>
						</c:forEach>

						<tr>
							<td align="right" colspan="10">${pagination}</td>

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
