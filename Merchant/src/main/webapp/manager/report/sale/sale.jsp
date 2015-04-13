<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 营销报表</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/salereport.js"></script>
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
     <li class="on">营销报表</li> 
     <li class="off"><a href="/manager/report/saleploy/list.htm">高级查询</a></li>  
    </ul>
    <div class="R_excel"><a href="javascript:void(0)" onclick="com.report.exp()">
    	<img src="/manager/image/btn/Export.png" alt="导出excel" /></a></div>
  </div>   
  <!--数据表格--> 
   <div class="S_table" style="float:none" id="ADcon0">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title"> 
       
        <td width="20%">活动主题</td> 
			<td width="20%">
			<select id="merchantId" name="merchantId" onchange="com.report.select(this)">
				<option value="0">营销活动范围</option>
				<c:if test="${!empty children}">
				<c:forEach var="child" items="${children}">
					<option <c:if test="${child.id == merchantId}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
				</c:forEach>
				</c:if>
			</select></td>
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
				</c:choose></td>
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
	         <td>${smsCount}&nbsp;</td>
	         <td>${sendCount}&nbsp;</td>
        </tr> 
        <tr>
         <td align="right"  colspan="6">${pagination}</td>
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
