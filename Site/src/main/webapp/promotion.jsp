<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<c:import url="/include/head_include.jsp"/>
<title>${promotion.domain.title}优惠 - 会员.点评.优惠.积分</title>
</head>
<body>

<c:import url="/include/top.jsp"/>

<div class="Level"><img src="/images/icon/icon-311.gif" />
  ${promotion.domain.merchant.area.parent.name} &gt;&gt; ${promotion.domain.merchant.area.name} &gt;&gt; ${promotion.domain.merchant.category.name} &gt;&gt; ${promotion.domain.merchant.shortName}
</div>
<div class="Buss">
<div class="Bu_top"></div>
 <div class="Bu_content">  
 <div class="Comment">  
  <div class="C_content">
    <div class="Bu_head" style="border:none"> 
    <img src="/images/bg/yhxq.jpg" style="margin-bottom:10px" /><br />
    <div class="q_info">
      <div class="q_info_left">
        <h1>${promotion.merchantName}</h1>
        <h2>${promotion.domain.title}</h2>
        <h3>截至日期：${promotion.endDate}</h3>
      </div>
      <img src='${promotion.domain.merchant.headForWeb}' />
      <div class="Q_zt" style="border:none" id="sentCountDiv"><span>${promotion.domain.sentCount}张已经领用</span></div>
      <c:if test="${promotion.downloadable}">
	    <div class="Q_btn"><a href="#" style="margin-top:-30px;" onclick="com.vlives.promotion.download(${promotion.domain.id})">领用</a></div>
	  </c:if>
    </div> 
	<div class="Q_right">
	<p style="float:left; margin-top:-10px;">${promotion.domain.introduction}</p>
	</div>
   </div>
    </div>    
 </div>
  <div class="Bu_right"> 
    <h1>使用提示</h1>
	<h2>1、什么是优惠券</h2>
	<p>优惠券是由商户发布的优惠活动生成，有效期内使用优惠券消费，可以为您带来消费优惠。</p>
	<h2>2、为什么不是所有的优惠券都可以领用</h2>
	<p>优惠券分为2种：<br />
	a、可领用的优惠券：如果您符合商户设定的领用条件，您可以通过领用获得优惠券。<br />
	b、不可领用的优惠券：这种优惠一般为优惠信息，您只要按照商户设定的规则消费，即可享受优惠。
	</p>
	<h2>3、如何使用优惠券</h2>
	<p>领用优惠券后，您会收到一条包含优惠券码的短信，并且在您的优惠券页面中也可以查看到该条优惠券信息。
	在消费支付时，请告知商户收银员您要使用优惠券消费，并提供优惠券码。
	如果优惠券码遗失，也可以告知收银员您的手机号码以及优惠券名字信息。</p>
  </div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
</div> 

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" language="javascript" src="/js/paging.js"></script>
<script type="text/javascript" language="javascript" src="/js/promotion.js"></script>
</body>
</html>