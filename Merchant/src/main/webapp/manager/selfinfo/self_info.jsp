<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>欢迎进入会生活商户管理平台 - 商户资料详情</title>
 <c:import url="/manager/include/head_include.jsp"/>
 <script type="text/javascript">
 	function modify() {
 		new Request({
 			url : "/manager/internal/merchant/${merchant.id}/selfInfoChange.htm",
 			method:"put",
 			onComplete:function() {
				//alert("del ok");
			}
 		}).send();
 			
 	 }

 	function MM_goToURL() { //v3.0
 		  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
 		  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
 	}
	 
 </script>
</head>

<body>
<!--top板块-->
<div id="top">
 	<c:import url="/manager/include/top.jsp"></c:import>
</div>
<!-- -->
<!--中间板块-->
<div id="content">
<!--left导航-->
 <c:import url="/manager/include/left.htm"></c:import>
<!-- -->
<!--right内容-->
<div id="right" >
 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
 <div class="right_all" style=" min-height:440px;"> 
  <!--内容title-->
  <div class="c_title_left"></div>
  <div class="c_title_right">
   <div class="c_all">
    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />商户资料管理</div>
   </div>
  </div>  
  <!--搜索板块--> 
  <!--数据表格--> 
   <div class="S_table" style="margin-top:0;">
       <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <td colspan="2" style="text-align:center"><input type="image" onclick="MM_goToURL('parent','/manager/internal/merchant/${merchant.id}/');return document.MM_returnValue" src="/manager/image/btn/S_02.jpg" />
         <strong>商户资料</strong></td>
         </tr> 
        <tr>
         <td width="23%" class="zuo">商户编号：</td>
         <td width="77%">${merchant.code }</td> 
        </tr> 
        <tr>
         <td width="23%" class="zuo">中文名称：</td>
         <td width="77%">${merchant.name }</td> 
        </tr> 
        <tr>
         <td width="23%" class="zuo">中文名称简写：</td>
         <td width="77%">${merchant.shortName }</td>
        </tr>
        <c:if test="${merchant.area!=null}">
	        <tr>
	           <td class="zuo">所属城市：</td>
	           <td>${merchant.area.address }</td>
	        </tr>   
        </c:if>
        <tr>
         <td class="zuo">加入时间：</td>
         <td><fmt:formatDate value="${merchant.createDate}" pattern="yyyy-MM-dd"/></td> 
        </tr>
         <c:if test="${ !empty merchant.englishName}">
         	<tr>
	         <td width="23%" class="zuo">英文名称：</td>
	         <td width="77%">${merchant.englishName }</td> 
        	</tr> 
         </c:if>
        
        <tr>
         <td class="zuo">隶属机构：</td>
         <td>${merchant.parentOrganization } </td> 
        </tr> 
        <c:if test="${merchant.existParent}">
          <tr>
	         <td class="zuo">隶属商户： </td>
	         <td>${merchant.parent.name }</td>
	      </tr> 
        </c:if>
        <c:if test="${merchant.existChildren}">
        <tr>
         <td class="zuo">门店数： </td>
         <td>${fn:length(merchant.memberGroup.merchants)}</td> 
        </tr> 
        </c:if>
        <tr>
         <td width="23%" class="zuo">营业地址： </td>
         <td width="77%">${merchant.businessAddress } </td> 
        </tr> 
        <c:if test="${!empty merchant.businessAddressCode}">
        	<tr>
	         <td class="zuo">营业地址邮编： </td>
	         <td>${merchant.businessAddressCode } </td> 
	        </tr> 
        </c:if>
        <c:if test="${ !empty merchant.merchantTelephone}">
	        <tr>
	         <td class="zuo">商户电话： </td>
	         <td>${merchant.merchantTelephone }</td>
	        </tr>   
        </c:if>
       <c:if test="${!empty merchant.merchantFax}">
       		 <tr>
	         <td class="zuo">商户传真： </td>
	         <td>${merchant.merchantFax } </td> 
	        </tr>   
       </c:if>  
       <c:if test="${!empty merchant.merchantWeb}">
       	 <tr>
	         <td class="zuo">商户网站： </td>
	         <td>${merchant.merchantWeb }  </td> 
        </tr> 
       </c:if>  
       
        <tr>
         <td class="zuo">联系姓名：  </td>
         <td>${merchant.contactName }  </td>
        </tr>    
        <tr>
         <td class="zuo">联系人电话： </td>
         <td>${merchant.contactTelephone} </td> 
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
