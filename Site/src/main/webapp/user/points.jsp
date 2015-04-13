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
   <div class="Map_top" id="pointsTop" style="height:43px; overflow:hidden;">
     <div class="Map_left"></div>
     <div class="Map_nav01">
       <ul>
         <li class="now">我的积分</li>
       </ul>
	 </div>
	 <div class="Map_right"></div>
   </div>
  <!--[我的积分]-->
  <div class="Con_list"  id="pointsDiv"> 
    <table border="0" id="pointsTable">
      <tr>
        <td class="title">商家名称</td>
        <td>会员级别</td>
		<td>可用积分</td>
        <td>累计积分</td>
      </tr>
    </table>
    <div class="manu" id="Pointspage"></div>
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
	  <li class="now" id="pointsNavLi"><a href="/user/points">我的积分</a></li>
	  <li id="settingNavLi"><a href="/user/info">我的设置</a></li>
	 </ul>
	</div>
  </div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
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
	//获取我的积分列表
	com.vlives.member.getPoints();
});
</script>
</body>
</html>
