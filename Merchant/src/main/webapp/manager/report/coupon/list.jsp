<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 电子券报表</title>
<c:import url="/manager/include/head_include.jsp" />

<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript" src="/manager/js/couponreport.js"></script>

<script type="text/javascript" src="/manager/js/mootools/mootools-1.2.5-core-yc.js"></script>
<script type="text/javascript" src="/manager/js/mootools/mootools-1.2.5.1-more.js"></script>
<c:import url="/manager/include/formcheck.jsp"/>

<script type="text/javascript">
	window.addEvent("domready", function() {

		new Calendar({
			startCreateDate : 'Y-m-d'
		}, {
			direction : -1,
			tweak : {
				x : 6,
				y : 0
			}
		});
		new Calendar({
			endCreateDate : 'Y-m-d'
		}, {
			direction : -1,
			tweak : {
				x : 6,
				y : 0
			}
		});
		com.checkBoxToRadio("input[class=checkboxItem]");
	});
	
	window.addEvent("domready",function(){
        new FormCheck('form', {
			display : {
				showErrors:0,
				indicateErrors : 1,
				fadeDuration : 1000
			}
	      });
     });
</script>
</head>
<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp" />
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm" />
		<!--right内容-->
		<div id="right">
			<div class="turn_btn">
				<img src="/manager/image/turnleft_btn.jpg" width="4"
					height="37" />
			</div>
			<div class="right_all" style="*float: left; *margin-left: -0.1px;">
				<!--内容title-->
				<div class="R_top">
					<ul>
						<li class="on">电子券报表</li>
					</ul>
					
					<div class="R_excel">
						<input type="image" onclick="com.couponreport.export_excel();" src="/manager/image/btn/Export.png" alt="导出Excel" />
					</div>
				
				</div>

				<!-- 搜索模块 -->
				<form id="form" action="/manager/report/coupon/list.htm" method="post">
					<div class="M_ss" style="margin-top: 8px;">
						<ul>
							<li><span>从： <input name="startCreateDate" class="validate['required']"
									value="<fmt:formatDate value='${startDate}' pattern="yyyy-MM-dd"/>"
									id="startCreateDate" type="text" /> 到 <input
									name="endCreateDate" id="endCreateDate" class="validate['required']"
									value="<fmt:formatDate value='${endDate}' pattern="yyyy-MM-dd"/>"
									type="text" /> </span> <input type="image" 
								src="/manager/image/btn/S_btn.jpg" class="validate['submit']"
								style="width: 64px; height: auto; border: none" /></li>
						</ul>
					</div>
				</form>
				<!--搜索结束-->


				<!--数据表格-->
				<div class="S_table" id="ADcon0" style="float: none;">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td >活动编号</td>
							<td>活动标题</td>
							<td>统计日期</td>
							<td>当日优惠券消费数</td>
							<td>刷卡交易金额</td>
							<td>现金交易金额</td>
							<td>总金额</td>
						</tr>
						<c:forEach items="${list}" var="couponReport">
							<tr>
								<td>
									<a href="/manager/coupon/coupon/list.htm?couponployid=${couponReport.couponPloy.id}">${ couponReport.couponPloy.id }
									</a>
								</td>
								<td>${ couponReport.couponPloy.title}</td>
								<td>${ couponReport.createDate}</td>
								<td>${ couponReport.couponConsumCount}</td>
								<td>${ couponReport.brushConsumMoney}</td>
								<td>${ couponReport.cashConsumMoney}</td>
								<td>${ couponReport.totalConsumMoney}</td>
							</tr>
						</c:forEach>
						<tr>
							<td align="right" colspan="7">${pagination}</td>
						</tr>
					</table>

				</div>

			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp" />
</body>
</html>
