<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="coupontype" value="com.vlives.boss.coupon.domain.CouponType@values()"/>
<j:getStatic var="ploytypes" value="com.vlives.boss.coupon.domain.CouponPloyType@values()"/>
<j:getStatic var="couponploystatuses" value="com.vlives.boss.coupon.domain.CouponPloy$CouponPloyStatus@valusBySelect()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入会生活商户管理平台 - 优惠券活动管理</title>
<c:import url="/manager/include/head_include.jsp"/>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript" src="/manager/js/comment.js"></script>
<script type="text/javascript" src="/manager/js/couponploy.js"></script>
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
   com.checkBoxToRadio("input[class*=checkboxItem]");
  });
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
	 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
	 <div class="right_all"> 
	  <!--内容title-->
	  <div class="c_title_left"></div>
	  <div class="c_title_right">
	   <div class="c_all"> 
		<div class="Q_nav"><div style="float:left"><img src="/manager/image/icon/07.gif" width="47" height="30" />优惠券管理</div>
	    </div>
	   </div>
	  </div>  
	  <!--搜索板块-->
	  <form action="/manager/coupon/ploy/list.htm" method="get" id="ploysearchForm">
	  	<input type="hidden" id="merchantId" name="merchantId" value="${param.merchantId}"/>
	  <div class="M_ss">  
	    <ul>
	      <li>活动状态
	       <select name="ploystatus" style="width:auto;">
	       <option value="">全部 </option>
	            <c:forEach items="${couponploystatuses}" var="status">
					<option value="${status.value}" <c:if test="${param.ploystatus == status.value}">selected="selected"</c:if> >${status.desc}</option>
				 </c:forEach>
	          </select>
		  	优惠券类型 
		  	<select name="coupontype">
		   		<option value="">全部 </option>
			 	 <c:forEach items="${coupontype}" var="types">
					<option value="${types.value}" <c:if test="${param.coupontype == types.value}">selected="selected"</c:if> >${types.desc}</option>
				 </c:forEach>
	          </select>
		  </li>
	      <li>优惠券活动类型
	          <select name="ploytype" style="width:auto;">
	            <option value="">全部 </option>
	            <c:forEach items="${ploytypes}" var="ploytypes">
					<option value="${ploytypes.value}" <c:if test="${param.ploytype == ploytypes.value}">selected="selected"</c:if> >${ploytypes.desc}</option>
				 </c:forEach>
	          </select>
	<span>券有效期：  <input name="startCreateDate" value="${param.startCreateDate}" id="startCreateDate" type="text" /> 
						到<input type="text" value="${param.endCreateDate}" name="endCreateDate" id="endCreateDate" /></span>
	         		<input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none" />
	      </li>
	      <li>
	        <dl>  
	        	<a href="javascript:void(0)" onclick ="com.couponploy.modifyFind(); return false;"><dd class="S_02"> </dd></a>
				<a href="javascript:void(0)" onclick ="com.couponploy.pause()"><dd class="S_08"></dd></a>
				<a href="javascript:void(0)" onclick ="com.couponploy.restore()"><dd class="S_09"></dd></a>
				<a href="javascript:void(0)" onclick ="com.couponploy.logout()"><dd class="S_05"></dd></a>
	        </dl>
	      </li>
	    </ul>
	  </div>  
	  </form>
	  <!--数据表格--> 
	   <div class="S_table">
	     <table width="0" border="0" cellpadding="0" cellspacing="0">
		     <tr class="title"> 
			     <td width="5%" style="text-align:center">选择</td>
			     <td width="8%">活动编号</td>
			     <td width="10%">券标题</td> 
		         <td width="10%">活动状态</td>
		         <td width="10%">活动类型</td>
		         <td width="8%">优惠券类型</td>
		         <c:if test="${operator.merchant.existChildren}">
		         	<td >使用券的门店<br />
				  <select id="merchantId" name="merchantId" onchange="com.couponploy.changemerchant(this)">
				   		<option value="0" >全部</option>
		             <c:forEach items="${operator.merchant.childrenAndMySelf}" var="merchants">
						<option value="${merchants.id}" <c:if test="${param.merchantId == merchants.id}">selected="selected"</c:if> >${merchants.name}</option>
				 	</c:forEach>
		          </select>
				 </td>
		         </c:if>
		         <td width="10%">活动发送时间</td> 
		         <td width="10%">券使用有效期</td> 
		         <td width="7%">已发送数</td>  
		          <td width="7%">验券数</td>   
		        </tr> 
	        <c:forEach items="${list}" var="ploy" varStatus="st">
	        	<tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
	        		<td style="text-align:center">
	        		  <c:if test="${ploy.merchant.id==operator.merchant.id}">
	        		    
	        		    <input class="checkboxItem <c:if test="${ploy.canModify}">canmodify</c:if>" type="checkbox"  value="${ploy.id}" />
	        		  </c:if>
	        		  <c:if test="${ploy.merchant.id!=operator.merchant.id}">
	        		      <input class="checkboxItem" disabled title="父商户创建的活动，无操作权限" type="checkbox" value="${ploy.id}" />
	        		  </c:if>
	        		</td>
		     		<td class="red">${ploy.id }<br /><a href="/manager/coupon/ploy/${ploy.id}/detail.htm">查看详情</a></td>
				     <td>${ploy.title }</td> 
			         <td class="statusDesc">${ploy.couponPloyStatus.desc} 
			         	<c:if test="${ ploy.couponPloyStatus.value==2}">
			         		<a href="#" onclick="POP.alert('${ploy.currentStatusDesc }')"><img src="/manager/image/w.jpg"></a>
			         	</c:if>
			         </td>
			         <td>${ploy.couponPloyType.desc }</td>
			         <td>${ploy.couponType.desc }</td>
			         <c:if test="${operator.merchant.existChildren }">
			         	 <td>${ploy.applyMerchantName }</td>
			         </c:if>
			         <td><fmt:formatDate value='${ploy.sendStartDate }' pattern="MM-dd"/>—<fmt:formatDate value='${ploy.sendEndDate }' pattern="MM-dd"/></td> 
			         <td><fmt:formatDate value='${ploy.validStartDate }' pattern="MM-dd"/>—<fmt:formatDate value='${ploy.validEndDate }' pattern="MM-dd"/></td> 
			         <td>${ploy.sentCount }</td> 
			         <td>${ploy.validateCount }</td>    
			        </tr> 
	        </c:forEach> 
	        <tr>
	          <c:if test="${operator.merchant.existChildren}"><td colspan="11" align="right">${pagination}</td></c:if>
			  <c:if test="${!operator.merchant.existChildren}"><td colspan="10" align="right">${pagination}</td></c:if>
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

