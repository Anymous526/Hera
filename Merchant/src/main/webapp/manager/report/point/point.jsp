<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 积分报表</title>
<c:import url="/manager/include/head_include.jsp" />
<script type="text/javascript" src="/manager/js/memberpoint.js"></script>
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
<div class="turn_btn">
	<a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4"
		height="37" />
	</a>
</div>
 <div class="right_all" style="*float:left; *margin-left:-0.1px;"> 
  <!--内容title--> 
  <div class="R_top"> 
    <ul>
     <li class="on">积分报表</li> 
     <li class="off"><a href="/manager/report/memberpoint/list.htm">高级查询</a></li>  
    </ul>
    <div class="R_excel"><a href="javascript:void(0)" onclick="com.report.exp()"><img src="/manager/image/btn/Export.png" alt="导出excel" /></a></div>
  </div>   
  <!--数据表格--> 
   <div class="S_table" style="float:none" id="ADcon0">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <td width="8%">姓名</td> 
         <td width="8%">手机号</td> 
         <td width="13%">消费时间</td>
         <td width="18%">
         
			<select id="merchantId" name="merchantId" onchange="com.report.select(this)">
					<option value="">消费门店</option>
					<c:if test="${!empty children}">
					<c:forEach var="child" items="${children}">
						<option <c:if test="${child.id == merchantId}">selected="selected"</c:if> value="${child.id}">${child.name}</option>
					</c:forEach>
					</c:if>
			</select>
		</td> 
		<td width="12%">积分变更类型</td>
		<td width="10%">交易方式</td>
         <td width="10%">当次积分</td> 
         <td width="10%">可用积分</td> 
         <td width="10%">总积分</td>
        </tr>

		<c:forEach items="${list}" var="point">
			<tr>
				<td class="zuo">${point.member.user.name}&nbsp;</td>
				<td>${point.member.user.mobile}&nbsp;</td>
				<td><fmt:formatDate value="${point.tradeOrder.tradeDate}"
						pattern="yyyy-MM-dd HH:mm" />&nbsp;</td>
				<td>${point.tradeOrder.merchant.name}&nbsp;</td>
				<td>${point.type.desc}&nbsp;</td>
				<td>${point.tradeOrder.type.desc}&nbsp;</td>
				<td>${point.point}&nbsp;</td>
				<td>${point.usablePoint}&nbsp;</td>
				<td>${point.totalPoint}&nbsp;</td>
			</tr>
		</c:forEach>

        <tr>
         <td align="right" colspan="10">${pagination}&nbsp;</td>
         
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
