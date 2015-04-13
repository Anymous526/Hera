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
   <div class="Map_top" id="consumesTop" style="height:43px; overflow:hidden;">
      <div class="Map_left"></div>
      <div class="Map_nav01">
        <ul>
          <li class="now" id="consumesLi"><a href="/user/consumes">我的消费</a></li>
          <li id="commentsLi"><a href="/user/comments">我的点评</a></li> 
        </ul> 
      </div>
      <div class="Map_right"></div>
    </div>
  <!--[我的消费]-->
  <div class="Con_list" id="consumesDiv"> 
    
    <div class="C_content" style="border:none;" id="consumesSubDiv">
     <table border="0" id="consumesTable">
      <tr>
        <td class="title">消费商家</td>
		<td>消费时间</td>
        <td>消费金额</td>
        <td>消费积分</td>
        <td>备注</td>
      </tr>
	 </table>
	 <div class="manu" id="searchpage"></div>
	</div>
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
	  <li class="now" id="consumesNavLi"><a href="/user/consumes">我的消费</a></li>
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

<div class="Com" style="display:none; " id="divComment">
<input id="hidScore" type="hidden" value="5" />
<input id="hidUnCommentId" type="hidden" value="0" />
 <div class="com_n"> 
   <div class="B_close" id="closeComment" onclick="Mbox.close();"></div>
      <h1><strong class="C_bt">消费记录</strong><img name="imgurl" id="imgurl" src="" />到店<span id="DescHtml"></span> ，发生于<span id="spnUnCommentDate"></span>，<b>共消费 <span id="spnUnCommentMoney"></span>元</b></h1>
      <h2><span>评 分：</span> <img src="/images/icon/xxd.png" id="score_1" onclick="com.vlives.member.score(1);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_2" onclick="com.vlives.member.score(2);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_3" onclick="com.vlives.member.score(3);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_4" onclick="com.vlives.member.score(4);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_5" onclick="com.vlives.member.score(5);" style="cursor:pointer; "/></h2>
      <h3><span>点 评：</span><textarea id="txtComment" cols="" rows=""></textarea>  </h3> 
	  <h4><input id="submitComment" type="button" onclick="com.vlives.member.submitComment();" /></h4>
	  <input type="hidden" id="commentType" value="1"/>
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
	//获取我的消费列表
	com.vlives.member.getConsumesList(2);
});
</script>
</body>
</html>


