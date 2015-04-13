<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="j" uri="/just"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <script type="text/javascript">
	 function submitForm(){
	 	document.getElementById('searchForm').submit();
 	 }
</script>   
<title>欢迎进入会生活商户管理平台 - 商户门店查询</title>
 <c:import url="/manager/include/head_include.jsp"/>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
      function ccl() {
        var content= document.getElementById("content")
        content.style.background="";
        var lefDiv = document.getElementById("lefDiv");
        lefDiv.style.display="block";
        var right = document.getElementById("right");
       right.style.marginLeft="";
      }
      function ccl2() {
        var content= document.getElementById("content")
        content.style.background="none";
        var lefDiv = document.getElementById("lefDiv");
        lefDiv.style.display="none";
        var right = document.getElementById("right");
       right.style.marginLeft="0";
      }
    </script>
</head>

<body>
<!--top板块-->
        <c:import url="/manager/include/top.jsp"/>
<!-- -->
<!--中间板块-->
<div id="content">
<!--left导航-->
 		<c:import url="/manager/include/left.htm"/>
<!-- -->
<!--right内容-->
<div id="right">
 <div class="turn_btn"><a href="#"><img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px"> 
  <!--内容title-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />门店浏览</div> 
   </div>
  </div>  

  <!--数据表格--> 
   <div class="S_table">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <td width="20%">门店名称</td>
         <td width="8%">POS数量</td>
         <td width="8%">挂靠会员数</td>
         <td width="8%">门店联系人</td> 
         <td width="10%">门店联系人电话</td>
         <td width="25%">所在地址</td> 
         <td width="8%">加入时间</td>   
         <td width="5%">状态</td>   
        </tr> 
       <c:forEach var="merchantDto" items="${merchantDtos}">
        <tr>
         <td class="zuo" title="${merchantDto.merchant.name}">
         <j:replace value="${merchantDto.merchant.name}" length="12" suffix="..." escape="true" />
         </td> 
         <td>${fn:length(merchantDto.merchant.poses)}</td>
         <td>
           ${merchantDto.createMemberCount}
         </td>
         <td>${merchantDto.merchant.contactName}</td> 
         <td>${merchantDto.merchant.contactTelephone}</td>
         <td  title="${merchantDto.merchant.businessAddress}">
         <j:replace value="${merchantDto.merchant.businessAddress}" length="316" suffix="..." escape="true" />
         </td> 
         <td><fmt:formatDate value="${merchantDto.merchant.createDate}" pattern="yyyy-MM-dd"/></td>   
         <td>${merchantDto.merchant.status.desc}</td> 

        </tr> 
       </c:forEach>

     </table> 
     <div style="clear:both"></div>
   </div>
  </div>      
  </div> 
 </div>
</div>  

<!-- footer -->
<c:import url="/manager/include/footer.jsp" />
</body>
</html>
