<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="j" uri="/just"%> 
<j:getStatic var="status" value="com.vlives.boss.pos.domain.Pos$Status@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - POS终端查询</title>
<c:import url="/manager/include/head_include.jsp" />
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript">
window.addEvent("domready",function(){
  new Calendar(
   {fromDate:'Y-m-d'}, 
   {direction: 0,tweak: {x: 6, y: 0}}
 );
 new Calendar(
   {toDate:'Y-m-d'}, 
   {direction: 0,tweak: {x: 6, y: 0}}
 );
});
</script>

</head>
<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp" />
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm" />
       
<!--right内容-->
<div id="right">
 <div class="turn_btn"><a href="#"><img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px"> 
  <!--内容title-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />POS终端查询</div> 
   </div>
  </div>  
  <!--搜索板块-->
  <div class="M_ss">  
  	<form action="/manager/internal/pos/list.htm" method="post" id="searchForm">
    <ul>
      <li>终端编号 <input name="code" type="text" value="${param.code}"/></li>
      <li>终端状态 
          <select name="status">
          <option value=""> </option>
          <c:forEach items="${status}" var="st">
            <option value="${st.value}">${st.desc} </option>
          </c:forEach>
          </select>
		  <span>创建时间    
		  <input value="${param.fromDate}" name="fromDate" id="fromDate" type="text"/> 
		  		到
		  <input value="${param.toDate}" name="toDate" id="toDate" type="text"/>
		  </span>
		  <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/>
      </li> 
    </ul>
    </form>
  </div>  
  <!--数据表格--> 
   <div class="S_table">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <td style="text-align:center">终端编号</td>
         <td>终端名称</td>
         <c:if test="${flag}">
          <td>所属门店</td>
         </c:if>
         <td>状态</td>
         <td>创建时间</td> 
        </tr> 
        <c:forEach var="poses" items="${posList}">
	        <tr>
	         <td class="zuo">${poses.code}</td>
	         <td>POS机</td>   
	         <c:if test="${flag}">
	          <td>${poses.merchant.name}</td> 
	         </c:if>
	         <td>${poses.status.desc}</td>
	         <td><fmt:formatDate value="${poses.createDate}" pattern="yyyy-MM-dd"/></td> 
	        </tr> 
       </c:forEach>
      <tr>
      	<td colspan="5" align="right"> 
          ${pagination}
        </td>
       </tr> 
     </table> 
     <div style="clear:both"></div>
   </div>
  </div>      
  </div> 
 </div>
</div>  

<c:import url="/manager/include/footer.jsp" />

</body>
</html>
