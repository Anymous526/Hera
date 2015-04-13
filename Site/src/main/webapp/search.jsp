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
	<title>商家搜索 - 会生活 - 会员.点评.优惠.积分</title>
	<c:import url="/include/head_include.jsp"/>
 </head>
<body>

<c:import url="/include/top.jsp"/>

<!----> 
<!--搜索块--> 
<form id="searchform" name="searchform" action="javascript:void(0)" method="post">
<input name="searchArea" id="searchArea" type="hidden" value="${param.searchArea }"/>
<input name="category" id="category" type="hidden" value="${param.categorySingle }"/>
<input  name="name" class="Div_ss" value="${param.name }"  type="hidden" />
<input name="sort" id="sort" type="hidden" value="sales"  />
<div class="Map" id="leftList" > 
<!-- 列表模式的顶部 -->
  <div class="Map_top" id="list_top" style="height:43px; overflow:hidden;">
   <div class="Map_left"></div>
   <div class="Map_nav">
      <h1>排序方式</h1>
      <div class="Px">
       <div class="Px_dq" id="Px_dq">按推荐排行</div>
       <!--点了出来下拉菜单后Px_more变成Px_more01-->
       <div class="Px_more" id="Px_more"></div>
       
      </div> 
   </div>
   <div class="Map_right"></div>
  </div>
  <!-- 列表模式的顶部结束 -->
  <!-- 地图模式的顶部开始 -->
  <div class="Map_top" id="maplist_top" style="display:none">
   <div class="Map_left"></div>
   <div class="Map_nav" >
      <h1 style="display:none">过滤条件</h1>
   </div>
   <div class="Map_right"></div>
  </div>
  <!-- 地图模式的顶部结束 -->
  <div class="Px_content" id="Px_content" style="display:none">
  	<dl>
  	   <dd id="TopList"><a href="javascript:sortByRecommends()">按推荐排行</a></dd>
       <dd id="CommentList"><a href="javascript:sortByComments()">按点评排行</a></dd>
       <dd id="ProList"><a href="javascript:sortByFavorites()">按收藏排行</a></dd>
       <dd id="MemberList"><a href="javascript:sortByMembers()">按会员排行</a></dd>
    </dl>
  </div>
  <!-- 地图底部 -->
  <div class="Map_bottom" id="maplist_buttom" style="display:none">
   <div class="Mapb_left"></div>
   <div class="Mapb_nav">
    <ul><li></li></ul>
    <ol style="display:none"></ol>
   </div>
   <div class="Mapb_right"></div>
  </div>
  <!-- 地图显示DIV -->
  <div class="Map_img" id="bigMap" style="display:none"></div>
  <!--商家列表板块-->
  <div class="Map_list" id="shop_List">
  <div class="Res_no" id="Res_no" style="display:none">
    <div style="float:left"></div>
	<div class="Wrong">
	 <h1>对不起，没有找到您需要的结果...</h1>
	 <h2>可能的原因：</h2>
	 <ol> 
	  <li><img src="/images/icon/circle01.jpg" />您查询的内容过于具体。</li>
	  <li><img src="/images/icon/circle01.jpg" />您查询的内容我们还没有。</li> 
	 </ol>
	</div> 
   </div>
   <ul id="shopLists">
    
   </ul>
    <div class="manu" id="searchpage"></div>
    
  </div> 
 
 <!--地图右边板块-->
 
 <div class="Map_info" id="rightInfo"> 
  <!--小地图板块-->
   <!--切换模式-->
 <div class="Div_Switch"> 
  <ul>
    <!--这里把class/ now换成old  old01换成now01就可-->
    <li class="now" id="listmodel"><a href="javascript:listModel()">列表模式</a></li>
    <li class="old01" id="mapmodel"><a href="javascript:mapModel()">地图模式</a></li>
  </ul>
 </div> 
  <div id="list_map" style="float:left;">
  <div class="Sj_sstop"></div>
   <div class="Sj_Map" id="smallMap"></div>
  <div class="info_bottom"></div> 
  <div class="Sj_sstop" style="margin-top:10px;"></div>
  </div> 
  <div id="list_map" style="float:left;">
  <!-- 地图右侧列表时需要显示 -->
  <div class="Sj_sstop" id="maplist_right" style="display:none"></div>
  <!--商家类型-->
  <div class="Sj_title" id="shopclick">商家类型</div>
  <div class="Sj_content" id="shoptype">
   <ul id="tList">
   </ul>
  </div>
  <!--商家位置-->
  <div class="Sj_title" id="shopPosition">商家位置</div>
  <div class="Sj_content" id="positionList">
   <ul id="pList">
    
   </ul>
  </div>
  <!--是否优惠-->
  <div class="Sj_title01" id="shopsale">商家活动</div>
  <div class="Sj_content" style="border-bottom:none;display:none" id="salelist" >
   <ul id="sList">
   </ul>
  </div>
  <div class="Map_info_bottom"></div>
 </div> 
 </div>
  <div style="clear:both"></div>
</div> 
</form>

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" language="javascript" src="/js/search.js"></script>
<script type="text/javascript" language="javascript" src="/js/paging.js"></script>

<script type="text/javascript" language="javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript" language="javascript" src="/js/baidu.js"></script>
<script type="text/javascript" language="javascript" src="/js/overlay.js"></script>
</body>
</html>
