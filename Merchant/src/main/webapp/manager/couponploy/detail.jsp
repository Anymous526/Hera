<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入会生活商户管理平台 - 优惠券活动管理</title>
<c:import url="/manager/include/head_include.jsp"/>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<link href="/manager/css/style.css" rel="stylesheet" type="text/css" />
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
 <div class="turn_btn"><a href="#"><img src="../image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px"> 
  <!--快速导航-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />优惠券活动详情</div> 
   </div>
  </div>      
  <!--发布营销活动-->  
     <div class="M_step_content">
    <ul class="detiel">  
     <li> </li>  
     <li><div class="status over">优惠券活动详情</div></li>
	 <li><strong style="color:#F46D00">优惠券基本信息</strong>
	   <div class="web_add01" style="margin-left:0px; overflow:hidden">
	    <i class="shang"> <strong>活动编号：</strong>${couponPloy.id }</i>               
	    <i class="shang"> <strong>活动标题：</strong>${couponPloy.title }</i><br />
	    <i class="shang"> <strong>券类型：</strong>${couponPloy.couponType.desc }</i>
	   	<i class="shang"> <strong>活动类型：</strong>${couponPloy.couponPloyType.desc }</i>
	   	<c:if test="${couponPloy.couponPloyType.value==2||couponPloy.couponPloyType.value==3 }">
	   		<i class="shang"> <strong>活动规则：</strong>${couponPloy.couponSendRule.ruleDescription }</i>
	   	</c:if>
	   	<i class="shang"> <strong>券是否在网站上显示：</strong>
	   		<c:choose>
		    	 <c:when test="${couponPloy.displayInWeb}">是</c:when>
		    	<c:otherwise>否</c:otherwise>
	   		 </c:choose>
	   	</i>
		<i class="shang"> <strong>券使用有效期：</strong><fmt:formatDate value='${couponPloy.validStartDate }' pattern="yyyy-MM-dd"/> 到 <fmt:formatDate value='${couponPloy.validEndDate }' pattern="yyyy-MM-dd"/></i>
		<i class="shang"> <strong>创建时间：</strong><fmt:formatDate value='${couponPloy.createDate }' pattern="yyyy-MM-dd"/></i>
		<i class="shang"> <strong>活动允许发送的最大数量：</strong> 
		   	 <c:choose>
		    	 <c:when test="${couponPloy.maxSendCount == null}">无限量</c:when>
		    	<c:otherwise>${couponPloy.maxSendCount}</c:otherwise>
	   		 </c:choose>
		 </i>
		<i class="shang"> <strong>优惠详情：</strong>${couponPloy.content }</i>
		 <i class="shang"> <strong>用户可领用优惠券的最大数量：</strong>${couponPloy.maxReceiveCountByEveryUser }<font>（如果为0表示不能领用）</font>
		 </i>
		 
		<div style="clear:both"></div>
	 </div> 
	 </li>
	 <li><strong style="color:#F46D00">优惠券使用详情</strong>
	   <div class="web_add01" style="margin-left:0px; overflow:hidden">
	     <i class="shang"> <strong>实际发送张数：</strong>${couponPloy.sentCount }</i>
	     <i class="shang"> <strong>券使用张数：</strong>${couponPloy.validateCount }</i><br/>
	     <i class="shang"> <strong>使用率：</strong>${couponPloy.couponUseRate }%<font>（已使用张数/实际发送张数）</font></i>
	     <i class="shang"> <strong>活动发送时间：</strong> <fmt:formatDate value='${couponPloy.sendStartDate }' pattern="yyyy-MM-dd"/> 到 <fmt:formatDate value='${couponPloy.sendEndDate }' pattern="yyyy-MM-dd"/></i>
		 <div style="clear:both"></div>
	   </div>
	 </li>
	 <li><strong style="color:#F46D00">优惠券消费详情</strong>
	   <div class="web_add01" style="margin-left:0px; padding-left:40px;">
	      <i> <strong>本批次优惠券产生的消费总额：</strong>${totalMoney }元<br />
		  <font>（同一批优惠券，通过POS检券，并且在同一个POS上进行检券后的消费，这台POS所记录的检券后的消费额。）</font></i><br />
          <i> <strong>本批次优惠券产生的刷卡总额：</strong>${brushMoney }元<br />
		  <font>（检券后POS联动产生的消费总额中，通过刷卡产生的消费额。）</font></i><br />
	     <i> <strong>本批次优惠券产生的现金总额：</strong>${cashMoney }元<br />
		 <font>（检券后POS联动产生的消费总额中，通过现金产生的消费额。）</font></i><br />
	     <i> <strong> 刷卡总额占比：</strong>${brushrate }%<br />
		 <font>（本批次优惠券产生的刷卡总额/本批次优惠券产生的消费总额）</font></i><br />
	     <i> <strong>现金总额占比：</strong>${cashrate }%<br />
		 <font>（本批次优惠券产生的现金总额/本批次优惠券产生的消费总额）</font></i>
		</div>
	 </li>
	 <div style="clear:both"></div>
    </ul>
   </div>
  </div> 
 </div>
 	</div>
	<c:import url="/manager/include/footer.jsp"/>
</body>
</html>

