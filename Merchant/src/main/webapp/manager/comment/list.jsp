<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="grades" value="com.vlives.boss.comment.domain.MerchantComment$MerchantGrade@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>欢迎进入会生活商户管理平台 - 点评管理</title>
<c:import url="/manager/include/head_include.jsp"/>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript" src="/manager/js/comment.js"></script>
<script  type="text/javascript">
  window.addEvent("domready",function(){ 
   new Calendar(
      {startCreateDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
   new Calendar(
      {endCreateDate:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
  ); 
  });
</script>
</head>
<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp"/>
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航  -->
		 <c:import url="/manager/include/left.htm"/>
		<!-- -->
<!--right内容-->
<div id="right">
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all"> 
  <!--快速导航-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />点评管理</div> 
   </div>
  </div>   
  <!--数据一览-->
  <div class="S_body" style="margin-top:-10px; padding-bottom:1px; margin-bottom:5px; *margin-bottom:0px;">  
   <table width="100%" border="0">
    <tr>
    <td colspan="2">
      <!--商家信用-->
      <div class="S_Credit" style=" padding-bottom:0px; *padding-bottom:10px">
       <div class="Credit_title">商家信用</div> 
       <div class="Credit_content" style="background:#fff; height:120px;">
         <img src="/manager/image/buss/01.jpg" width="65" height="65" />
         <ul>
          <li>商家信用：<span>${statistic.creditScore}</span> <img src="/manager/image/icon/08.gif" width="13" height="20" /></li>
          <li>好评率：<span>${statistic.bestPercent}%</span> </li>
          <li class="txt">根据网站统计，您的信用度和好评率较好。</li>
         </ul>
         <!-- 
         <ul style="border-left:1px solid #DDECF9">
        <li class="new" style="height:24px;">好评：<img src="/manager/image/icon/10.jpg" width="43" height="13" /><c:if test="${statistic.threeGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"    style="height: 13px ;width:${statistic.threeGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.threeGradePercent}%)&nbsp;&nbsp; <span>${statistic.threeGrade}</span>条</li>
        <li class="new">中评：<img src="/manager/image/icon/11.jpg" width="43" height="13" /><c:if test="${statistic.twoGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"   style="height: 13px ;width:${statistic.twoGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.twoGradePercent}%)&nbsp;&nbsp; <span>${statistic.twoGrade}</span>条</li>
        <li class="new">差评：<img src="/manager/image/icon/12.jpg" width="43" height="13" /><c:if test="${statistic.oneGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.oneGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.oneGradePercent}%)&nbsp;&nbsp; <span>${statistic.oneGrade}</span>条</li>
       </ul>
        -->
       <ul style="border-left:1px solid #DDECF9">
        <li  class="new" style="height:24px;">
       推荐：<img src="/manager/image/icon/five_star.png" width="86" height="16" /><c:if test="${statistic.fiveGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"    style="height: 13px ;width:${statistic.fiveGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.fiveGradePercent}%)&nbsp;&nbsp; <span>${statistic.fiveGrade}</span>条
      </li>
      <li  class="new" style="height:24px;">
     很好：<img src="/manager/image/icon/four_star.png" width="86" height="16" /><c:if test="${statistic.fourGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.fourGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.fourGradePercent}%)&nbsp;&nbsp; <span>${statistic.fourGrade}</span>条
    </li >
    <li  class="new" style="height:24px;">
        好评：<img src="/manager/image/icon/three_star.png" width="86" height="16" /><c:if test="${statistic.threeGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.threeGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.threeGradePercent}%)&nbsp;&nbsp; <span>${statistic.threeGrade}</span>条
  </li>
  <li  class="new" style="height:24px;"> 中评：<img src="/manager/image/icon/two_star.png" width="86" height="16" /><c:if test="${statistic.twoGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.twoGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.twoGradePercent}%)&nbsp;&nbsp; <span>${statistic.twoGrade}</span>条</li>
<li  class="new"> 差评：<img src="/manager/image/icon/one_star.png" width="86" height="16" /><c:if test="${statistic.oneGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.oneGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.oneGradePercent}%)&nbsp;&nbsp; <span>${statistic.oneGrade}</span>条</li>
</ul>
       </div> 
       <!--  
       <div class="P_Switch">      
        <input name="smsAlert"  id="smsAlert" type="checkbox"  <c:if test="${sendSms}">checked="checked"</c:if>  />&nbsp;&nbsp;启用会员消费短信提醒功能<a href="javaScript:void(0);"  onclick="com.comment.setSendSms();"  ><img src="/manager/image/btn/sure01.png" /></a> <span id="beforeMsg"  >（启用此功能后，如会员在商家消费，系统将会下发短信提醒会员，此项功能自动扣除商家短信）</span>
      </div>
     -->
       </div> 
      
       </td>
    </tr> 
   </table> 
  </div>
  <!--搜索-->
 
  <div class="M_ss" style=" margin-top:5px">  
   <form action="/manager/comment/list.htm" method="post">
    <ul>
      <li style=" padding-top:5px;">评价<select name="grade" >
           <option value="0">全部评价 </option>
           
									<c:forEach items="${grades}" var="grade">
										<option value="${grade.value}" <c:if test="${localGrade == grade.value}">selected="selected"</c:if> >${grade.label}</option>
									</c:forEach>
          </select> 
          <span>评价时间    <input  id="startCreateDate"   name="startCreateDate" type="text"   value="<fmt:formatDate value="${startCreateDate}"  pattern="yyyy-MM-dd"/>"  /> 到<input  id="endCreateDate"  name="endCreateDate" type="text"  value="<fmt:formatDate value="${endCreateDate}"  pattern="yyyy-MM-dd"/>" /></span>
         
            <input type="image" src="/manager/image/btn/S_btn.jpg"     width="64" height="25" class="ss_srk" style="width:64px; height:auto; border:none" />
      </li>  
  
    </ul>
   </form> 
  </div>     
  <!--搜索结果表格-->
  <div class="S_table" style="float:none">
    <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title"> 
         <td align="center" width="20%">评价</td>
         <td>评价内容</td> 
         <td width="10%" >评价时间</td>
         <td width="10%" >操作</td> 
        </tr> 
        <c:forEach items="${list}" var="comment" varStatus="st">
        <tr <c:if test="${st.index%2 ==0}">class="S_t_01"</c:if>   > 
         <td class="zuo">${comment.merchantGrade.label}：${comment.merchantGradeSrc} </td>
         <td> <c:out value="${comment.comments}"  />&nbsp;</td> 
         <td><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td><a href="javascript:void(0)"  onclick="com.comment.replyDialog(${comment.id})" >回复</a>&nbsp; </td>   
        </tr> 
        </c:forEach>
        <tr>
         <td colspan="8" align="right">&nbsp;${pagination}</td>
         </tr> 
     </table> 
  <div style="clear:both"></div>
 </div> 
  </div> 
 </div>
</div>  


<c:import url="/manager/include/footer.jsp"/>
</body>
</html>