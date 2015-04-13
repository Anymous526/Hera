<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员消费查询</title>
<c:import url="/manager/include/head_include.jsp"/>
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
	<c:import url="/manager/include/top.jsp"/>
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm"/>
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
							<img src="/manager/image/icon/07.gif" width="47" height="30" />会员消费查询
						</div>
					</div>
				</div>
				<!--搜索板块-->
				<form action="/manager/member/consume/consume.htm" method="post">
				<div class="M_ss">
					<ul>
						<li>
							会员姓名 <input name="name" value="${myname}" type="text" />
							手机号码 <input name="mobile" value="${mobile}" type="text" />
							会员等级<select name="level">
								<option value="0">全部会员</option>
								<c:forEach items="${levels}" var="lev">
									<option value="${lev.value}" <c:if test="${level == lev}">selected="selected"</c:if> >${lev.desc}</option>
								</c:forEach>
						</select></li>
						<li><span>消费时间 <input name="startTradeDate" value="<fmt:formatDate value='${startTradeDate}' pattern="yyyy-MM-dd"/>" id="startTradeDate" type="text" /> 
						到<input name="endTradeDate" id="endTradeDate" value="<fmt:formatDate value='${endTradeDate}' pattern="yyyy-MM-dd"/>" type="text" />
						</span> <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none" />
						</li>
					</ul>
				</div>
				</form>
				<!--数据表格-->
				<div class="S_table">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td class="zuo">姓名</td>
							<td>手机号</td>
							<td>会员等级</td>
							<td>消费时间</td>
							<td>消费类型</td>
							<td>消费金额 (元)</td>
							<td>可用积分</td>
							<td>总积分</td>
						</tr>
						<c:forEach items="${list}" var="detail" varStatus="st">
							<tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
								<td>${detail.tradeOrder.member.user.name}</td>
								<td>${detail.tradeOrder.member.user.mobile}</td>
								<td>${detail.tradeOrder.member.level.desc}</td>
								<td><fmt:formatDate value="${detail.tradeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${detail.tradeOrder.type.desc}</td>
								<td>${detail.tradeOrder.money}</td>
								<td>${detail.tradeOrder.member.point}</td>
								<td>${detail.tradeOrder.member.totalPoint}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="10" align="right">${pagination}</td>
						</tr>
					</table>
					<div style="clear: both"></div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp"/>
</body>
</html>


