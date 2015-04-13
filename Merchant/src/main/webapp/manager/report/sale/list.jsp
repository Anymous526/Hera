<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 营销活动高级查询</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/salereport.js"></script>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
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
	<c:import url="/manager/include/top.jsp" />
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm" />

<div id="right">
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style="*float:left; *margin-left:-0.1px;"> 
  <!--内容title--> 
  <div class="R_top"> 
    <ul>
     <li class="off"><a href="/manager/report/saleploy/find.htm">营销报表</a></li> 
     <li class="on">高级查询</li>  
    </ul>
    <div class="R_excel"><a href="javascript:void(0)" onclick="com.report.exp_list()" ><img src="/manager/image/btn/Export.png" alt="导出excel" /></a></div>
  </div>   
  <!--数据表格--> 
  	<form id="myform" action="/manager/report/saleploy/list.htm">
    <div class="M_ss" style="margin-top:8px;">  
     <ul>
      <li>活动主题 <input name="name" value="${myname}" type="text" /> 
       	 会员等级
         <select name="level">
           <option value="0">全部</option>
           <c:forEach var="levels" items="${levels}">
             <option <c:if test="${levels.value == level}">selected="selected"</c:if> value="${levels.value}">${levels.desc}</option>
           </c:forEach>
          </select> 
          
          <c:if test="${!empty children}">
				活动门店  <select name="merchantId">
					<option value="">全部</option>
					<c:forEach var="child" items="${children}">
						<option <c:if test="${child.id == merchant.id}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
					</c:forEach>
				</select>
		  </c:if>
          
      </li> 
      <li> 
          <span>活动时间    <input name="startCreateDate" value="<fmt:formatDate value='${startCreateDate}' pattern="yyyy-MM-dd"/>" id="startCreateDate" type="text" />
          	到<input name="endCreateDate" value="<fmt:formatDate value='${endCreateDate}' pattern="yyyy-MM-dd"/>" id="endCreateDate" type="text" /></span> 
            <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/>
      </li> 
     </ul>
    </div>
    </form>
    <div class="S_table" style="float:none"> 
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title"> 
         <td width="20%">活动主题</td> 
         <td width="20%">营销活动范围</td>  
         <td width="15%">适用会员等级</td> 
         <td width="15%">活动时间</td>
         <td width="15%">预发短信数</td> 
         <td width="15%">已发短信数</td> 
        </tr> 

		<c:forEach items="${list}" var="sale" varStatus="st">
			<tr <c:if test="${st.index%2 != 0}">class="S_t_01"</c:if>>
				<td class="zuo">${sale.name}&nbsp;</td>
				<td>${sale.merchant.name}&nbsp;</td>
				<td>
				<c:choose>
					<c:when test="${! empty sale.memberLevels}">
						<c:forEach items="${sale.memberLevels}" var="levels">
				        ${levels.desc}&nbsp;
						</c:forEach>
					</c:when>
					<c:otherwise>
						全部等级
					</c:otherwise>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${sale.createDate}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;</td>
				<td>${sale.smsCount}&nbsp;</td>
				<td>${sale.sendCount}&nbsp;</td>
			</tr>
		</c:forEach>
        <tr>  
	         <td class="zuo">总计</td> 
	         <td>&nbsp;</td>    
	         <td>&nbsp;</td> 
	         <td>&nbsp;</td> 
	         <td>${smsCount}</td>
	         <td>${sendCount}</td>
        </tr>
        <tr>
         <td align="right" colspan="6">${pagination}</td>
        </tr> 
     </table> 
     <div style="clear:both"></div>
   </div>
   </div>
  </div>      
  </div> 

<c:import url="/manager/include/footer.jsp" />
</body>
</html>
