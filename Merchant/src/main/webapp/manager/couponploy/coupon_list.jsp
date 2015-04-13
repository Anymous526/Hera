<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="coupontypees" value="com.vlives.boss.coupon.domain.CouponType@values()"/>
<j:getStatic var="couponStatuses" value="com.vlives.boss.coupon.domain.Coupon$CouponStatus@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入会生活商户管理平台 - 优惠券查询</title>
<c:import url="/manager/include/head_include.jsp"/>
<script type="text/javascript" src="/manager/js/comment.js"></script>
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
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />优惠券详情查询</div> 
   </div>
  </div>  
  <!--搜索板块-->
  <form action="/manager/coupon/coupon/list.htm" method="post">
  <div class="M_ss">  
    <ul>
      <li>
      	所属活动编号 <input name="couponployid" type="text" value="${param.couponployid }"/>
      	券编码 <input name="code" type="text" value="${param.code }"/>
      	活动标题 <input name="title" type="text" value="${param.title }"/> 
        </li>
      <li>
      	手机号码<input name="mobile" type="text" value="${param.mobile }"/>
      	券状态
          <select name="couponStatus">
		   		<option value="">全部 </option>
			 	 <c:forEach items="${couponStatuses}" var="status">
					<option value="${status.value}" <c:if test="${param.couponStatus == status.value}">selected="selected"</c:if> >${status.desc}</option>
				 </c:forEach>
          </select>
		   券类型
          <select name="couponType">
		   		<option value="">全部 </option>
			 	 <c:forEach items="${coupontypees}" var="types">
					<option value="${types.value}" <c:if test="${param.couponType == types.value}">selected="selected"</c:if> >${types.desc}</option>
				 </c:forEach>
          </select>
          <input type="image" src="/manager/image/btn/S_btn.jpg"  class="ss_srk" style="width:64px; height:auto; border:none" />
      </li> 
  
    </ul>
  </div>  
  </form>
  <!--数据表格--> 
   <div class="S_table">
     <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title"> 
	     <td>券编码</td>
	     <td>所属活动编号</td> 
         <td>活动标题</td>
         <td>券状态</td>
         <td>券类型</td>
         <td>所属手机号</td>
         <td>领用方式</td>
         <td>券有效期</td> 
        </tr> 
        <c:forEach items="${list}" var="coupon" varStatus="st">
        	<tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
	     		 <td>${coupon.code }</td>
			     <td>${coupon.couponPloy.id }</td> 
		         <td>${coupon.couponPloy.title }</td>
		         <td>${coupon.couponStatus.desc }</td>
		         <td>${coupon.couponPloy.couponType.desc }</td>
		         <td>${coupon.user.mobile }</td> 
		         <td>${coupon.receiveChannel.desc}</td> 
		         <td><fmt:formatDate value='${coupon.couponPloy.validStartDate }' pattern="yyyy-MM-dd"/>——<fmt:formatDate value='${coupon.couponPloy.validEndDate }' pattern="yyyy-MM-dd"/></td>    
	        </tr> 
        </c:forEach> 
         <tr>
			<td colspan="10" align="right">${pagination}</td>
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

