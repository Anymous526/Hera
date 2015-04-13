<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员新增月报</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/report.js"></script>
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
						<li class="on">本月新增</li>
						<li class="off"><a href="/manager/report/memberinfo/category.htm">会员分类</a></li>
						<li class="off"><a href="/manager/report/memberinfo/list.htm">高级查询</a></li>
					</ul>
					<div class="R_excel">
						<a href="javascript:void(0)" onclick="com.report.exp_month()"><img src="/manager/image/btn/Export.png"
							alt="导出excel" /> </a>
					</div>
				</div>

				<!-- 今日新增 -->
				<div class="S_table" style="float:none" id="show0">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
								<td width="17%">
								<select id="merchantId" name="merchantId" onchange="com.report.selectMonth(this)">
										<option value="">所属门店</option>
										<c:if test="${!empty children}">
										<c:forEach items="${children}" var="child">
											<option <c:if test="${child.id == merchantId}">selected="selected"</c:if>  value="${child.id}">${child.name}</option>
										</c:forEach>
										</c:if>
								</select>
								</td>

							<td width="8%">姓名</td>
							<td width="10%">手机号</td>
							<td width="5%">性别</td>
							<td width="8%">等级</td>
							<td width="8%">总积分</td>
							<td width="13%">注册时间</td>
							<td width="15%">身份证</td>
							<td width="20%">联系地址</td>
						</tr>
						<c:forEach items="${month}" var="member">
							<tr>
								<td class="zuo">${member.createMerchant.name}</td>
								<td>${member.user.name}</td>
								<td>${member.user.mobile}</td>
								<td>${member.user.genderDesc}</td>
								<td>${member.level.desc}</td>
								<td>${member.totalPoint}</td>
								<td><fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${member.user.cardNumber}</td>
								<td title="${member.user.address}"><j:replace value="${member.user.address}" length="10"/></td>
							</tr>
						</c:forEach>
						<tr>
							<td align="right" colspan="9">${pagination}</td>
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