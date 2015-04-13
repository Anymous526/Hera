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
  
<title>欢迎进入会生活商户管理平台 - 公告中心</title>
<c:import url="/manager/include/head_include.jsp"/>
<link href="/manager/css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>

<c:import url="/manager/include/top.jsp"/>

<div id="content" style="background:none">
<!--right内容-->
<div id="right" style="margin-left:0px">
  <div class="right_all"> 
  <!--快速导航-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />公告中心</div> 
   </div>
  </div> 
     
  <!--搜索结果表格-->
  <div class="S_table">  
   <h1>${border.name}</h1>
   <h2><span ><fmt:formatDate value="${border.createDate}" pattern="yyyy-MM-dd HH:MM:dd"/>  &nbsp;|</span><span style="margin-left:30px;"><a href="/manager/index.htm">返回首页</a></span></h2>
   <p>
     ${border.content}
   </p>
 </div>
  </div> 
 </div>
</div>  

<c:import url="/manager/include/footer.jsp" />
</body>
</html>
