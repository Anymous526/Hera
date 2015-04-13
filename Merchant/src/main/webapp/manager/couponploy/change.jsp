<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="coupontype" value="com.vlives.boss.coupon.domain.CouponType@values()"/>
<j:getStatic var="ploytypes" value="com.vlives.boss.coupon.domain.CouponPloyType@values()"/>
<j:getStatic var="couponploystatuses" value="com.vlives.boss.coupon.domain.CouponPloy$CouponPloyStatus@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入会生活商户管理平台</title>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<c:import url="/manager/include/head_include.jsp"/>
<c:import url="/manager/include/formcheck.jsp"/>
<link href="/manager/css/bomb.css" rel="stylesheet" type="text/css" />
<script src="/manager/js/ie6png.js" type="text/javascript"></script>
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript" src="/manager/js/couponploy.js"></script>
<script type="text/javascript" src="/manager/js/coupon.js"></script>
<script type="text/javascript" src="/manager/js/preview.js"></script>
<script  type="text/javascript">
  window.addEvent("domready",function(){ 
   new com.keypress("ployContent",54);
   new Calendar(
      {validStartDate:'Y-m-d'}, 
      {direction:1,tweak:{x: 6, y: 0}}
    );
   new Calendar(
      {validEndDate:'Y-m-d'}, 
      {direction:1,tweak: {x: 6, y: 0}}
  );
   new Calendar(
           {sendStartDate:'Y-m-d'}, 
           {direction:1,tweak:{x: 6, y: 0}}
         );
         new Calendar(
           {sendEndDate:'Y-m-d'}, 
           {direction:1,tweak:{x: 6, y: 0}}
         );
   new FormCheck('myform', {
		display : {
			showErrors:1,
			indicateErrors : 1,
			fadeDuration : 1000
		}
   });

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
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px"> 
  <!--快速导航-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />修改优惠券</div> 
   </div>
  </div>      
  <!--发布营销活动-->  
   <div class="M_step_content">
    <form id="myform" action="/manager/coupon/ploy/${couponPloy.id}/save.htm" method="post">
    <ul>
      <li><img src="/manager/image/icon/setp.gif" width="25" height="25" />
        <span>优惠券标题: <font><input class="validate['required','%validateChartLength{2,14}']" name="title" id="title" type="text" value="${couponPloy.title}" /> </font></span> 
      </li>
		<li class="M_t_01"><img src="/manager/image/icon/setp.gif" width="25"height="25" /> 
			<span>活动发送日期:</span> 
		          从<input id="sendStartDate" name="sendStartDate" value="<fmt:formatDate value="${couponPloy.sendStartDate}" pattern="yyyy-MM-dd"/>" class="validate['required']" type="text" /> 
			到 <input id="sendEndDate" name="sendEndDate" value="<fmt:formatDate value="${couponPloy.sendEndDate}" pattern="yyyy-MM-dd"/>" class="validate['%validatorValidAndSendEndDate{false}']" type="text" /> 
			<font style="margin-left: 10px"> (从这个时间起通过审核的短信开始发送) </font>
		</li>
      <li><img src="/manager/image/icon/setp.gif" width="25" height="25" />
        <span>优惠券有效期:</span>
                    从<input class="validate['required']" name="validStartDate" type="text" id="validStartDate" value="<fmt:formatDate value='${couponPloy.validStartDate}' pattern="yyyy-MM-dd"/>"/> 
                    到 <input class="validate['%validatorValidAndSendEndDate{true}']" name="validEndDate" type="text" id="validEndDate" value="<fmt:formatDate value='${couponPloy.validEndDate}' pattern="yyyy-MM-dd"/>"/>
        <font style="margin-left: 10px"> (使用优惠卷的生效时间和失效时间)</font>
      </li>

		<li class="M_t_01">
        <img src="/manager/image/icon/setp.gif" style="float:left" width="25" height="25" />
        <span>使用券的门店:<font>
          <c:forEach items="${couponPloy.couponPloyApplyMerchants}" var="ployMer" varStatus="st">
            &nbsp;&nbsp;${ployMer.merchant.name}
          </c:forEach>
        </font></span >
      </li>
      <li><img src="/manager/image/icon/setp.gif" width="25" height="25" />
        <span>优惠券类型: <font>${couponPloy.couponType.desc}</font></span>  
      </li> 
      <li>
	    <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>优惠详情:</span> 
	    <font style="margin-left:13px;">
         <label id="surplus">你还可以输入<b>54</b>个字</label>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="com.coupon.showTemplate()">单击此处选择优惠券</a></font><br />
	    <textarea class="validate['required','%validatorIllegalWord','length[1,54]']" name="content" id="ployContent" style="margin-left:113px;" cols="" rows=""
	    onKeyUp="com.keypress(ployContent,54)">${couponPloy.content}</textarea>
	  </li> 
      <li class="M_t_01 bottom" style="text-align:center">
        <a style="vertical-align: middle; margin: 4px 0px;" href="/manager/coupon/ploy/list.htm"><img style="padding:3px 0px 0px 0px;" src="/manager/image/btn/cancel.png"/></a>
        
        <input style="width:auto;height:auto;border: 0px;" type="image" src="/manager/image/btn/bc_sh.jpg" />
      </li>
    </ul>
    </form>
   </div>
  </div> 
 </div>
</div>  
	<c:import url="/manager/include/footer.jsp"/>
</body>
</html>
