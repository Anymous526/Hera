<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员详细信息</title>
<c:import url="/manager/include/head_include.jsp"/>
<script type="text/javascript" src="/manager/js/member.js"></script>
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
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px"> 
  <!--内容title-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />会员详细信息管理</div>
   </div>
   
  </div>  
  <!--搜索板块--> 
  <!--数据表格--> 
  <div class="S_table" style="margin-top:0;">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
      <tr class="title">
         <td colspan="2" style="text-align:center"> <a href="/manager/member/info/info.htm"><img src="/manager/image/btn/S_btn07.jpg" /></a>
         <strong>会员详细信息</strong></td>
         </tr> 
       <tr>
         <td width="23%" class="zuo">姓名</td>
         <td width="77%">${member.user.name}</td> 
        </tr> 
        <tr>
         <td class="zuo">手机号码：</td>
         <td class="sr">${member.user.mobile}</td> 
        </tr> 
        <tr>
         <td class="zuo">所属门店：</td>
         <td class="sr">${member.createMerchant.name}</td> 
        </tr> 
        <tr>
         <td class="zuo">会员等级：</td>
         <td>${member.level.desc}</td> 
        </tr> 
        <tr>
         <td class="zuo">可用积分：</td>
         <td>${member.point}分</td> 
        </tr>   
        <tr>
         <td class="zuo">总积分：</td>
         <td>${member.totalPoint}分</td> 
        </tr>     
        <tr>
         <td class="zuo">注册时间：</td>
         <td><fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm"/></td> 
        </tr>  
        <tr>
         <td class="zuo">会员生日：</td>
         <td><fmt:formatDate value="${member.user.birthday}" pattern="yyyy-MM-dd"/></td> 
        </tr>    
        <tr>
         <td class="zuo">邮箱：</td>
         <td>${member.user.email}</td> 
        </tr>     
        <tr>
         <td class="zuo">联系地址：</td>
         <td>${member.user.address }</td> 
        </tr>  
     </table> 
     <div style="clear:both"></div>
   </div>
   
     <div style="clear:both"></div>
   </div>
  </div>      
  </div>
<c:import url="/manager/include/footer.jsp"/>
</body>
</html>


