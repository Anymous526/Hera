<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels"
	value="com.vlives.boss.member.domain.Level@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员管理</title>
<c:import url="/manager/include/head_include.jsp" />
<c:import url="/manager/include/formcheck.jsp" />
<script type="text/javascript" src="/manager/js/level.js"></script>
<script type="text/javascript">
	window.addEvent("domready",function() {
	 
		new FormCheck('myform', {
			display : {
				showErrors:0,
				indicateErrors : 1,
				fadeDuration : 1000
			}
	      });
	});
	function reset(){
		var myform = $('myform');
		myform.reset();
	}
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
							<img src="/manager/image/icon/07.gif" width="47" height="30" />${memberUpdateRule.level.desc}信息
						</div>
					</div>
				</div>
				<!--数据表格-->
				<form id="myform" action="/manager/member/level/${memberUpdateRule.id}/modify_commit.htm" method="post">
					<input type="hidden" name="level" value="${memberUpdateRule.level.value}"/>
				<div class="S_table" style="margin-top: 0px; padding-bottom: 10px">
					<div class="S_Credit">
						<div class="Praise_title">
							<div class="P_head_t" style="width: 100%">${memberUpdateRule.level.desc}信息内容</div>
						</div>
						<div class="P_Praise">
							<ul>
								<li>
									<table width="0" border="0">
										<tr>
											<td width="15%"><strong>等级名称：</strong>
											</td>
											<td width="85%">${memberUpdateRule.level.desc}</td>
										</tr>
									</table></li>
								<li>
									<table width="0" border="0">
										<tr>
											<td width="15%"><strong>积分比例：</strong>
											</td>
											<c:forEach items="${merchant.pointRules}" var="pointRule">
												<c:if test="${pointRule.type.value == 1 && pointRule.paramerOne==memberUpdateRule.level.value}">
													<c:set var="point" value="${pointRule.paramerTwo}"/>
												</c:if>
											</c:forEach>
											<td width="85%"><input class="validate['required','digit[100,1000]']" name="pointScale" type="text" value="<c:if test="${empty point}">100</c:if>${point}" />%&nbsp;
												（如积分比例是120%，则表示每消费1元，积1.2分；积分比例大于等于100%）</td>
										</tr>
									</table></li>
								<li><table width="0" border="0">
										<tr>
											<td width="15%"><strong>折扣比例：</strong>
											</td>
											<c:forEach items="${merchant.discountRules}" var="discountRule">
												<c:if test="${discountRule.type.value == 1 && discountRule.paramerOne==memberUpdateRule.level.value}">
													<c:set var="discount" value="${discountRule.paramerTwo}"/>
												</c:if>
											</c:forEach>
											<td width="85%"><input class="validate['required','digit[1,100]']" name="discountScale" type="text" value="<c:if test="${empty discount}">100</c:if>${discount}" />%&nbsp;
												（如折扣比例是90%，则表示应消费1元，实际付0.9元；折扣比例小于等于100%）</td>
										</tr>
									</table></li>
								<li><table width="0" border="0">
										<tr>
											<td width="15%"><strong>赠送积分：</strong>
											</td>
											<td width="85%"><input class="validate['digit[0,10000]']" name="rewardPoint" type="text" value="${memberUpdateRule.rewardPoint}" />&nbsp;
												（成为本级会员，则赠送积分）</td>
										</tr>
									</table></li>
								<c:if test="${memberUpdateRule.level.value != 4}">
								<li style="height: auto">
								<table width="0" border="0">
						            <tr> 
						             <td width="15%" valign="top"><strong>升级管理：</strong></td>
						             <td width="85%" class="duoz">
						              	<c:choose>
						              		<c:when test="${empty memberUpdateRule.updateRuleItems}">
								              	<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								                                                      一次消费满 <input class="validate['required','digit[1,100000]']" name="once" type="text" value="<c:if test='${item.type.value==1}'>${item.updatePoint}</c:if>" />元&nbsp;(会员价)&nbsp;   成为&nbsp;&nbsp;<i>${memberUpdateRule.nextLevel.desc}</i><br />
								              	<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								              	总积分满 &nbsp;&nbsp;&nbsp;&nbsp;<input class="validate['required','digit[1,1000000]']" name="total" type="text" value="<c:if test='${item.type.value==2}'>${item.updatePoint}</c:if>" />分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   成为&nbsp;&nbsp;<i>${memberUpdateRule.nextLevel.desc}</i><br />
						              		</c:when>
						              		<c:otherwise>
								              	<c:forEach items="${memberUpdateRule.updateRuleItems}" var="item">
									              	<c:if test='${item.type.value==1}'>
										              	<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
										                                                      一次消费满 <input class="validate['required','digit[1,100000]']" name="once" type="text" value="${item.updatePoint}" />元&nbsp;(会员价)&nbsp;   成为&nbsp;&nbsp;<i>${memberUpdateRule.nextLevel.desc}</i><br />
									              	</c:if>
									              	<c:if test='${item.type.value==3}'>
									              	<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
									              	总积分满 &nbsp;&nbsp;&nbsp;&nbsp;<input class="validate['required','digit[1,1000000]']" name="total" type="text" value="${item.updatePoint}" />分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   成为&nbsp;&nbsp;<i>${memberUpdateRule.nextLevel.desc}</i><br />
									              	</c:if>
								              	</c:forEach>
						              		</c:otherwise>
						              	</c:choose>
						              </td>
						            </tr>
									</table></li>
									</c:if>
								<li style="height: auto;"><table width="0" border="0">
										<tr>
											<td width="15%" valign="top"><strong>备注：</strong>
											</td>
											<td width="85%" class="textare"><textarea class="validate['length[100]']" name="desc"
													cols="" rows="">${memberUpdateRule.desc}</textarea>
											</td>
										</tr>
									</table></li>
								<li>
									<table width="0" border="0">
										<tr>
											<td align="center"><input name="" class="in_none"
												type="image" src="/manager/image/btn/sure.png" /> 
												<a href="/manager/member/level/view.htm"><img src="/manager/image/btn/cancel.png"/></a>
												 
											</td>
										</tr>
									</table></li>
								<li style="height: auto"></li>
							</ul>
						</div>
					</div>
					<div style="clear: both"></div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp" />
</body>
</html>

