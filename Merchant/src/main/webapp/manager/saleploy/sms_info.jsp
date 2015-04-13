<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 短信管理</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
    </head>
    <body>
        <!--top板块-->
        <c:import url="/manager/include/top.jsp"/>
        <!-- --><!--中间板块-->
        <div id="content">
            <!--left导航-->
            <c:import url="/manager/include/left.htm"/> 
            
<!--right内容-->
<div id="right">
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" > 
  <!--内容title-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />短信管理</div>
   </div>
  </div>  
  <!--搜索板块--> 
  <!--数据表格--> 
   <div class="S_table" style="margin-top:0;">
       <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <!-- <td colspan="6" style="text-align:center"> -->
         <td colspan="2" style="text-align:center">
           <strong>短信总计情况</strong>
         </td>
         <td colspan="2" style="text-align:center">
           <strong>短信已使用情况</strong>
         </td>
         <td colspan="2" style="text-align:center">
           <strong>短信剩余情况</strong>
         </td>
         </tr> 
        <tr>
         <td width="23%" class="zuo">短信总数：</td>
         <td width="14%">${merchantSmsAccount.totalCount}</td> 
         <td width="14%" class="zuo">已发送条数：</td>
         <td width="14%">${merchantSmsAccount.consumeCount}</td>
         <td width="14%" class="zuo">剩余条数：</td>
         <td width="10%">${merchantSmsAccount.remainCount}</td>
        </tr> 
        <tr>
         <td class="zuo">申请购买的条数：</td>
         <td>${merchantSmsAccount.merchantBuyCount}</td>
         <td colspan="4"></td>
        </tr> 
        <tr>
         <td class="zuo">系统免费赠送的条数：</td>
         <td>${merchantSmsAccount.sysPresenCount}</td> 
         <td colspan="4"></td>
        </tr>       
        <tr><td colspan="6" style="text-align:center"> <a href="/manager/saleploy/buy_sms.jsp"><img src="/manager/image/btn/S_buy.jpg" /></a></td></tr>
     </table>
     <div style="clear:both"></div>
   </div>
  </div>      
  </div> 
 </div> 

<c:import url="/manager/include/footer.jsp"/>
</body>
</html>
