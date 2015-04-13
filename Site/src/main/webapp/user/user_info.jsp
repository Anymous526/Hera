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
  
<div class="Buss">
<div class="Bu_top"></div> 
 <div class="Bu_content"> 
  <div class="Bu_mapleft">
  	<!--上部导航--> 

   <div class="Map_top" id="settingTop" style="height:43px; overflow:hidden;">
     <div class="Map_left"></div>
     <div class="Map_nav01">
       <ul>
         <li class="now">我的设置</li>
       </ul>
	 </div>
	 <div class="Map_right"></div>
   </div>
  <!--[我的设置]-->
  <div class="Con_list" id="settingDiv">
  	<div class="head_img"><img id="user_Head" name="user_Head" width="60px" height="60px" src=""><a href="/user/avatar">修改头像</a></div>
   
   <form action="javascript:void(0)" method="post" id="settingform" name="settingform">
   <input id="userId" type="hidden" value="" />
   <ol>
    <li><span>昵称：</span><input class="sr" name="nickname" id="nickname" type="text" /><b>最多6个汉字</b><span style="color:red;display:none;" id="nicknameSpan"></span></li>
	<li><span>真实姓名：</span><input class="sr" name="xingming" id="xingming" type="text" /><span style="color:red;display:none;" id="xingmingSpan"></span></li>
	<li><span>所在地：</span><select name="sheng" id="sheng" class="selcet"><option value="2194">广东</option></select> 
	                        <select name="areaId" id="aredId" class="selcet"><option value="2222">深圳</option></select></li>
	<li><span>性别：</span><select name="gender" id="gender" class="selcet"><option value="0">保密</option><option value="1">男</option><option value="2">女</option></select></li>
	 
	<li><span>生日：</span><input class="sr" name="birthday" id="birthday" onclick="new Calendar(1950, 2011).show(this);" type="text" readonly="readonly" /></li>
	<li><span>常用邮箱：</span><input class="sr" name="email" id="email" type="text" /><span style="color:red;display:none" id="emailSpan"></span></li>  
   <div style="clear:both"></div>
   </ol>
   <ol style=" border:none; margin-top:5px;">
    <li>以下信息，可作为通过客服取回账号的依据</li> 
	<li><span>证件类型：</span><select name="cardType" id="cardType" class="selcet"><option value="1">身份证</option><option value="2">驾驶证</option></select></li> 
	<li><span>证件号码：</span><input class="sr" name="cardNumber" id="cardNumber" type="text" /><span style="color:red;display:none" id="cardNumberSpan"></span></li>  
	<li><span>&nbsp;</span> <input class="sr_btn" value=""  id="saveSettingButton" type="submit" onclick="com.vlives.member.saveInfo();" /></li>  
	<div style=" clear:both"></div>
   </ol>
    </form>
   <ol style=" border:none; margin-top:5px;">
    <li>可以通过合作网站账号登录会生活，与好友分享信息</li>  
	<li style="height:120px;">
	 <div class="E_GGGleft">
     <dl>
      <dd id="memberMobile_1" style="text-align: center;display:none;"></dd>
      <dd id="sinaAccountTextDt_1"><img src="/images/icon/sina.png" />新浪微博</dd> 
	  <dt id="sinaAccountTextDt" ></dt> 
      <dd style="display:none"><img src="/images/icon/txweibo.png" /><a href="#">腾讯微博帐号登录</a></dd>
	  <dt style="display:none"> <input class="qxbd" name="" type="button" /></dt> 
     </dl>
   </div> 
   </li>
   <div style="clear:both"></div>
   </ol>
   </div>
   
  </div>
    <!--右边板块--> 
  <div class="Bu_right"> 
    <div class="M_Info" id="userInfo">
	</div>
	<div class="M_Nav">
	 <ul>
	  <li id="merchantsNavLi"><a href="/user/memberMerchants">我的店铺</a></li>
	  <li id="couponsNavLi"><a href="/user/coupons">我的优惠券</a></li>
	  <li id="consumesNavLi"><a href="/user/consumes">我的消费</a></li>
	  <li id="pointsNavLi"><a href="/user/points">我的积分</a></li>
	  <li class="now" id="settingNavLi"><a href="/user/info">我的设置</a></li>
	 </ul>
	</div>
  </div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
</div> 

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/member.js"></script>
<script type="text/javascript" src="/js/calendar.js"></script>
<script type="text/javascript">
window.addEvent("domready",function() {
	var user=${user};
	//更新右侧会员信息
	com.vlives.member.updateUserInfo(user);
	//获取用户右侧统计信息
	com.vlives.member.getUserInfo();
	//获取我的消费列表
	com.vlives.member.updateUserSetting(user.user);
	
	if (user.user.head==null)
		$('user_Head').src="/images/head.jpg";
	else
		$('user_Head').src=user.user.head;
	
	//设置限制输入
	com.vlives.base.limitInput("nickname", "nicknameSpan", 12);
	com.vlives.base.limitInput("xingming", "xingmingSpan", 20);
	com.vlives.base.limitInput("email", "emailSpan", 50);
});
</script>

</body>
</html>