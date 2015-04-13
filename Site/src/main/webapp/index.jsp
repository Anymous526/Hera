<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<c:import url="/include/head_include.jsp"/>
	<title>会生活 - 会员.点评.优惠.积分</title>
	
  </head>
  <body>

<c:import url="/include/top.jsp"/>

<!--4屏广告-->
<div class="H_img" style="position:relative; z-index:1; ">
<OBJECT codeBase=" http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000  width="980" height="252">
 <PARAM NAME="movie" VALUE="fla/1.swf">
 <PARAM NAME="quality" VALUE="high">
 <PARAM NAME="wmode" VALUE="transparent"> 
  <embed src="/fla/1.swf" quality="high" wmode="transparent" 
  pluginspage=" http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="980" height="252" value="transparent"></embed></OBJECT> 
</div>
<!--4个广告语板块-->
<div class="Explain">
 <ul>
  <li class="icon01"><span>在线商家会员服务</span><br />在线查询消费记录，兑换积分抽奖</li>
  <li class="icon02"><span>发现喜欢的商家</span><br />好友一起玩转身边，分享吃喝玩乐</li>
  <li class="icon03"><span>乐享商家优惠</span><br />与合作商家一起，为会员提供特惠</li>
  <li class="icon04"><span>随时随地消费点评</span><br />真实消费点评，让好商家口碑相传</li>
 </ul>
</div>

<div class="Map"> 
<!--地图上部导航-->
  <div class="Map_top">
   <div class="Map_left"></div>
   <div class="Map_nav">
     <ul id="Tablist">
      <li id="WhatsNew" onclick='switchTab(0,"Tablist");' class="now">看新鲜</li>
      <li id="Recommend" onclick='switchTab(1,"Tablist");' class="old">享推荐</li>
      <li id="Preferential" onclick='switchTab(2,"Tablist");' class="old">找优惠</li>
     </ul>
     <ol style="display:none"><input name="" type="button" /></ol>
   </div>
   <div class="Map_right"></div>
  </div>
  <div class="Map_bottom">
   <div class="Mapb_left"></div>
   <div class="Mapb_nav">
    <div class="beizhu"><b style="padding-left:40px;">餐饮</b>  <b>健身</b>  <b>美容</b> <b>娱乐</b> <b>其他</b></div>
   </div>
   <div class="Mapb_right"></div>
  </div>
   <!--地图板块-->
 <div class="Map_img" id="container"></div> 
 <!--地图右边板块-->
 <div class="Map_info" style="margin-top:-90px;">
  <div class="info_top"></div> 
  <div class="Map_info_top" id="selText">看看大家在干什么?</div>
  <div class="Map_txt">
	  <div class="waike01" id="waike01" name="waike01" onMouseMove="this.style.display='block'" onMouseOut="this.style.display='none'" style="display:none"></div>
	  <div class="waike02" id="waike02" name="waike02" onMouseMove="this.style.display='block'" onMouseOut="this.style.display='none'"  style="display:none"></div>
	  <div class="waike03" id="waike03" name="waike03" onMouseMove="this.style.display='block'" onMouseOut="this.style.display='none'"   style="display:none"></div>
	  <div class="waike04"  id="waike04" name="waike04" onMouseMove="this.style.display='block'" onMouseOut="this.style.display='none'"  style="display:none"></div>
	  <div class="waike05"  id="waike05" name="waike05" onMouseMove="this.style.display='block'" onMouseOut="this.style.display='none'"  style="display:none"></div>
   <ul id="rightlist">
   </ul>
  </div>
  <div class="Map_info_bottom"></div>
  </div>
  <div style="clear:both"></div>
 </div>
</div>

<!--左边说说浮动板块-->
<div class="Sshuo" id="Sshuo">
 <div class="S_left" id="shuoDIV"></div>
 <div class="S_right" style="display:none" id="rightDIV">
  <div class="S_Rtop"></div>
  <div class="S_Rcontent">
   <div class="S_close" onclick="hideDIV('rightDIV')"></div>
   <div class="S_txt"><input style="display:none;" type="text" size=10 id="shownum"/>请提交您遇到的问题，或对我们的建议，请留下您的电子邮箱，我们将尽快给您反馈。
   <div  id="error"></div>
   </div>
   <div class="S_input">
     <textarea name="textarea" cols="" rows="" id="suggestion"></textarea>
   </div>
   <div class="S_btn"><input name="" type="button" id="submitSuggestion"/></div>
  </div>
  <div class="S_Rbottom"></div>
 </div>
</div>

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" language="javascript" src="/js/index.js"></script>
<script type="text/javascript" language="javascript" src="/js/roll.class.js"></script>

<script type="text/javascript" language="javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript" language="javascript" src="/js/baidu.js"></script>
<script type="text/javascript" language="javascript" src="/js/overlay.js"></script>
</body>
</html>