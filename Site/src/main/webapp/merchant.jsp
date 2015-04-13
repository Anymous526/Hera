<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<c:import url="/include/head_include.jsp"/>
<title>${merchant.name} - 会生活 - 会员.点评.优惠.积分</title>
</head>
<body>

<c:import url="/include/top.jsp"/>

<div class="Level"><img src="/images/icon/icon-311.gif" />
  <a href="javascript:com.vlives.merchant.navigate(${merchant.id}, ${merchant.area.parent.id}, '', '')">${merchant.area.parent.name}</a> &gt;&gt;  
  <a href="javascript:com.vlives.merchant.navigate(${merchant.id}, ${merchant.area.parent.id}, ${merchant.area.id}, '')">${merchant.area.name}</a> &gt;&gt;  
  <a href="javascript:com.vlives.merchant.navigate(${merchant.id}, ${merchant.area.parent.id}, ${merchant.area.id}, ${merchant.category.id})">${merchant.category.name}</a> &gt;&gt; 
  ${merchant.shortName}
</div>
<div class="Buss">
 <div class="Bu_top"></div>
 <div class="Bu_content">
 <div class="Comment">  
 <div class="C_content">
  <div class="Bu_head">
   	<c:if test="${merchant.favourPloy == true}">
   		<div class="Bu_title">优惠中</div>
   	</c:if>
   <div class="txt_txt">
       <h1><span>${merchant.shortName}</span></h1>
       <h2>地址：${merchant.businessAddress}<br />电话：${merchant.merchantTelephone}</h2>   
   </div> 
   <div class="Bu_dengji">
    <div id="levelDiv"></div>
	<h2>(${merchant.merchantReferenceStatistic.commentCount}人已点评)</h2>
	<div id="favoriteDiv"></div>
   </div>
  </div>
  <div class="Bu_head"> 
    <img src="/images/bg/sjjs.jpg" /><br />
	<p>${(merchant.info == null || merchant.info == "")?"暂无商户介绍信息":merchant.info} </p>
	<div class="B_tubiao" style="display:none">
	<img src="/images/icon/hui.jpg" /> 正在优惠中 
	<img src="/images/icon/te.jpg" /> 中信银行特约商户
	<img src="/images/icon/ting.jpg" /> 免费停车位
	<img src="/images/icon/wifi.jpg" /> 免费网络
	<img src="/images/icon/24h.jpg" /> 24小时营业
	<img src="/images/icon/fen.jpg" /> 可累积积分
    </div>
  </div>
  <div class="Bu_head" style="display:none"> 
   <img src="/images/bg/tpxx.jpg" /> <span style="color:#F46600">(共有45张图片)</span><br />
   <dl>
	 <div style="clear:both"></div>
   </dl> 
  </div>
  <div class="Bu_head" style="border:none"> 
   <img src="/images/bg/zxdp.jpg" />
   <span style="color:#F46600">(共有${merchant.merchantReferenceStatistic.commentCount}条评论)</span>
   <a href="javascript:com.vlives.merchant.comment()"><img class="dpbtn" src="/images/bg/dp.jpg" /></a><br /> 
   <ul id="commentsUl">
   </ul>
   <div class="manu" id="commentPage"></div>
  </div> 
 </div>
 </div>    
 <div class="Bu_right">
  <div class="Small_map" id="content_0" style="display:block;"></div>
  <div class="Smap_txt"><a href="#"></a> &nbsp; <a href="#"></a></div> 

  <c:forEach var="promotion" items="${promotions}">
  <div class="q_info" >
   <div class="q_info_left">
    <h1>${promotion.merchantName}</h1>
    <h2>${promotion.domain.title}</h2>
    <h3>截止日期：${promotion.endDate}</h3>
   </div>
   <img src='${promotion.domain.merchant.headForWeb}' /> 
   <div class="Q_lyxq">
    <div class="Q_btnblue"><a target="_blank" href="/promotion/${promotion.domain.id}">详情</a></div>
   </div>
  </div>
  </c:forEach>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div> 
</div>
</div>

<!--点评弹出框-->
<div class="Com" style="display:none; " id="divComment">
<input id="hidScore" type="hidden" value="5" />
<input id="hidUnCommentId" type="hidden" value="0" />
 <div class="com_n"> 
   <div class="B_close" id="closeComment"></div>
      <h1><strong class="C_bt">消费记录</strong><span id="spnUnTradeImage"></span>到店<span><span id="spnUnTradeType"></span>评价</span> ，发生于<span id="spnUnCommentDate"></span>，<b>共消费 <span id="spnUnCommentMoney"></span>元</b></h1>
      <h2><span>评 分：</span> <img src="/images/icon/xxd.png" id="score_1" onclick="com.vlives.merchant.score(1);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_2" onclick="com.vlives.merchant.score(2);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_3" onclick="com.vlives.merchant.score(3);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_4" onclick="com.vlives.merchant.score(4);" style="cursor:pointer; "/>
	  <img src="/images/icon/xxd.png" id="score_5" onclick="com.vlives.merchant.score(5);" style="cursor:pointer; "/></h2>
      <h3><span>点 评：</span><textarea id="txtComment" cols="" rows=""></textarea></h3> 
	  <h4><input id="submitComment" type="button" /></h4>
 </div>
</div>

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" language="javascript" src="/js/paging.js"></script>
<script type="text/javascript" language="javascript" src="/js/merchant.js"></script>

<script type="text/javascript" language="javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript" language="javascript" src="/js/baidu.js"></script>
<script type="text/javascript" language="javascript" src="/js/overlay.js"></script>
<script>
var merchantId = ${merchant.id};

window.addEvent('domready',
function() {
	//设置商户评分等级
	com.vlives.merchant.setLevel(${merchant.merchantReferenceStatistic.averageGrade});
	//判断某商户是否被收藏
	com.vlives.merchant.isFavorite(${merchant.id});
	//获取某商户的评论信息
	com.vlives.merchant.getComment(${merchant.id});

	$("closeComment").addEvent("click", function() {
		com.vlives.merchant.closeCommentWin();
	});

	$("submitComment").addEvent("click", function() {
		com.vlives.merchant.submitComment();
	});
}); 

window.addEvent('load',
function(){
	//初始化百度地图信息
	initialize("content_0");
	addShopMarker('${merchant.latitude}', '${merchant.longitude}', ${merchant.category.id});
});
</script>
</body>
</html>
