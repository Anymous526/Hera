<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels"
	value="com.vlives.boss.member.domain.Level@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员列表</title>
<c:import url="/manager/include/head_include.jsp"/>
<script type="text/javascript" src="/manager/js/level.js"></script>
</head>

<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp" />
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm" />
		<!-- -->
		<!--right内容-->
		<div id="right">
			<div class="turn_btn">
				<a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4"
					height="37" />
				</a>
			</div>
			<div class="right_all" style="min-height: 440px">
				<!--内容title-->
				<div class="c_title_left"></div>
				<div class="c_title_right">
					<div class="c_all">
						<div class="Q_nav">
							<img src="/manager/image/icon/07.gif" width="47" height="30" />会员等级管理
						</div>
					</div>
				</div>
				<!--数据表格-->
				<div class="S_table" style="margin-top: 0px;">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td>等级名称</td>
							<td>积分比例</td>
							<td>折扣比例</td>
							<td>奖励积分</td>
							<td>自动升级</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${merchant.memberUpdateRules}" var="memberRule" varStatus="st">
							<tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
								<td>${memberRule.level.desc}</td>
								<td>
								    <c:if test="${empty merchant.pointRules}">100%</c:if>
									<c:forEach items="${merchant.pointRules}" var="pointRule">
										<c:if test="${pointRule.type.value == 1 && pointRule.paramerOne==memberRule.level.value}">
											${pointRule.paramerTwo}%
										</c:if>
									</c:forEach>
									
								</td>
								<td>
								    <c:if test="${empty merchant.discountRules}">100%</c:if>
									<c:forEach items="${merchant.discountRules}" var="discountRule">
										<c:if test="${discountRule.type.value == 1 && discountRule.paramerOne==memberRule.level.value}">
											${discountRule.paramerTwo}%
										</c:if>
									</c:forEach>
								</td>
								<td>${memberRule.rewardPoint}</td>
								<td>
									<c:forEach items="${memberRule.updateRuleItems}" var="item">
										${item.type.desc}满${item.updatePoint}
										<c:if test="${item.type.value == 1}">元</c:if>
										<c:if test="${item.type.value == 3}">分</c:if>成为${memberRule.nextLevel.desc}<br/>
									</c:forEach>
								</td>
								<td title="${memberRule.desc}"><j:replace value="${memberRule.desc}" length="10"/></td>
								<td><a href="#"onclick="com.level.modify(${memberRule.id})"><img src="/manager/image/btn/S_06.jpg"/></a></td>
							</tr>
						</c:forEach>
					</table>
					<div style="clear: both"></div>
					<font color="red">${msg}</font>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp" />
</body>
</html>
