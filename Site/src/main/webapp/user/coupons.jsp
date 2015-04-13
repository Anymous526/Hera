<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<c:import url="/include/head_include.jsp"/>
<title>我的首页 - 会生活 - 会员.点评.优惠.积分-我的会员</title>
</head>
<body>

<!--头部板块-->
<c:import url="/include/top.jsp"/>
<!---->

<form name="couponFilterForm" id="couponFilterForm">
  <input type="hidden" name="couponStatus" id="couponStatus"/>
  <input type="hidden" name="couponMerchant" id="couponMerchant"/>
  <input type="hidden" name="couponSortingOption" id="couponSortingOption"/>
</form>

<div class="Buss">
<div class="Bu_top"></div> 
 <div class="Bu_content"> 
  <div class="Bu_mapleft">
  <!--上部导航--> 
  <div class="Map_top" style="height:43px; overflow:hidden;">
   <div class="Map_left"></div>
   <div class="Map_nav01">
    <ul>
      <li class="notitle" style="background:none">我的优惠券</li>
    </ul>
   </div>
   <div class="Map_right"></div>
  </div>
  <!--[我的优惠券]-->
  <div class="Map_list">
   <div class="Condition" id="couponFilter">
    <dl>
	 <dd id="couponStatusDd"><img src="/images/icon/zt.jpg" />
	 	<span id="couponStatusSpan1"><a href="javascript:com.vlives.member.getCouponsByStatus(1, '未使用')">未使用</a></span>
		<span id="couponStatusSpan2"><a href="javascript:com.vlives.member.getCouponsByStatus(2, '已使用')">已使用</a></span>
		<span id="couponStatusSpan4"><a href="javascript:com.vlives.member.getCouponsByStatus(4, '已过期')">已过期</a></span>
	 </dd>
	 <!-- <dd id="couponMerchantDd"><img src="images/icon/sh.jpg" />
	   <div class="sh_name">
	   <span>银汉斯 <input name="coupon.merchant" id="coupon.merchant" type="checkbox" value="" /></span> 
	   </div>
	 </dd> -->
	 <dd id="sortingDd"><img src="/images/icon/px.jpg" />
       <span id="sortingSpan1"><a href="javascript:com.vlives.member.sortCoupons(1, '领用时间')">领用时间</a></span> 
       <span id="sortingSpan2"><a href="javascript:com.vlives.member.sortCoupons(2, '截止时间')">截止时间</a></span>
	 </dd>
	 <div style="clear:both"></div>
	</dl>
   </div>
   <div id="couponsSubDiv">
    <ol id="couponsOl"> 
    </ol>
   </div>
   <div class="manu" id="couponspage"></div>
  </div>
  </div>
  <!--右边板块--> 
  <div class="Bu_right"> 
    <div class="M_Info" id="userInfo">
	</div>
	<div class="M_Nav">
	 <ul>
	  <li id="merchantsNavLi"><a href="/user/memberMerchants">我的店铺</a></li>
	  <li class="now" id="couponsNavLi"><a href="/user/coupons">我的优惠券</a></li>
	  <li id="consumesNavLi"><a href="/user/consumes">我的消费</a></li>
	  <li id="pointsNavLi"><a href="/user/points">我的积分</a></li>
	  <li id="settingNavLi"><a href="/user/info">我的设置</a></li>
	 </ul>
	</div>
  </div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
</div> 
</div>

<c:import url="/include/footer.jsp"/>
<script src="/js/base.js"></script>
<script src="/js/member.js"></script>
<script>
window.addEvent("domready",function() {
	var user=${user};
	//更新右侧会员信息
	com.vlives.member.updateUserInfo(user);
	//获取用户右侧统计信息
	com.vlives.member.getUserInfo();
	//获取我的优惠券列表
	com.vlives.member.getCoupons();
});
</script>
</body>
</html>
