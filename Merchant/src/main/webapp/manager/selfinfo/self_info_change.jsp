<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>欢迎进入会生活商户管理平台 - 商户资料管理</title>
	<c:import url="/manager/include/head_include.jsp"></c:import>
	<c:import url="/manager/include/formcheck.jsp"></c:import>
	<script src="/manager/js/area/area_mootools.js"></script>
	<script type="text/javascript">
		window.addEvent("domready", function() {
		  <c:if test="${merchant.area==null}">
		    new com.area.AreaMenu(
			"province","city","district"
		   ); 
		  </c:if>
         <c:if test="${merchant.area!=null}">
           new com.area.AreaMenu(
  			  "province","city","district",
  			  {"provinceid":${merchant.area.parent.parent.id},
  			  "cityid":${merchant.area.parent.id},
  			  "districtid":${merchant.area.id}}
  		    );
         </c:if>
			new FormCheck('modifyForm',{
				display : {
					showErrors:1,
					indicateErrors : 1,
					fadeDuration : 1000
				}
			});
		});

		function checkTelephone(phone) {
			var phones = phone.value.trim().split(/,|，/); 
			for(var i=0;i<phones.length;i++) {
				if (!phones[i].test(/^[0-9]{7,13}|\d{3,4}-\d{7,8}$/)) {
					phone.errors.push('请输入正确格式的电话号码，多部电话可使用","隔开');
					return false;
				}
			}
		}

		function checkShortName(el) {
			if(!el.value.trim().test(/^[a-zA-Z0-9\u4E00-\u9FA5()]*$/)) {
				el.errors.push("此字段可由数字、26个英文字母以及中文汉字组成");
				return false;
			}
		}

		function checkEnglishName(el) {
			if(!el.value.trim().test(/^[a-zA-Z0-9., ()&]*$/)) {
				el.errors.push("此字段由数字、26个英文字母及特殊符号(如.&等)组成");
				return false;
			}
		}
		
		function checkBusinessAddress(el) {
			if(!el.value.trim().test(/^[a-zA-Z0-9\u4E00-\u9FA5-,()，、（）]*$/)) {
				el.errors.push("此字段只能输入汉字、字母、数字以及下划线");
				return false;
			}
		}

		function checkCode(el) {
			if(!el.value.trim().test(/^\d{6}$/)) {
				el.errors.push("此字段6位数字");
				return false;
			}
		}

		function checkFax(el) {
			if(!el.value.trim().test(/^\d{6,15}|\d{3,4}-\d{7,8}$/)) {
				el.errors.push("请输入有效号码");
				return false;
			}
		}
	
	</script>
</head>
<body>
<!--top板块-->
<div id="top">
	<c:import url="/manager/include/top.jsp" />
</div>
<!-- -->
<!--中间板块-->
<div id="content">
<!--left导航-->
	<c:import url="/manager/include/left.htm"/>
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
   <form action="/manager/internal/merchant/${ merchant.id}/selfupdate.htm" id="modifyForm" method="post">
      	<input type="hidden" name="_method" value="put"/>
   <div class="S_table" style="margin-top:0;">
       <table width="0" border="0" cellpadding="0" cellspacing="0">
       <tr class="title">
         <td colspan="2" style="text-align:center"> 
         <strong>商户资料</strong></td>
         
         </tr> 
        <tr>
         <td width="23%" class="zuo">商户编号：</td>
         <td width="77%">${merchant.code }</td> 
        </tr> 
        <tr>
         <td class="zuo">中文名称：</td>
         <td>${merchant.name }</td> 
        </tr>
        <tr>
         <td class="zuo">加入时间：</td>
         <td><fmt:formatDate value="${merchant.createDate}" pattern="yyyy-MM-dd"/></td> 
        </tr>
        <c:if test="${merchant.existChildren}">
        <tr>
         <td class="zuo">门店数： </td>
         <td>${fn:length(merchant.memberGroup.merchants)}</td> 
        </tr> 
        </c:if>
        <tr>
        <td width="23%" class="zuo">中文名称简写：</td>
         <td width="77%"  class="sr"> <input id="shortName" name="shortName" class="validate['required','%validateChartLength{1,32}','%checkShortName'] text" type="text" value="${merchant.shortName }" /></td> 
        </tr> 
        
        
        <tr>
         <td class="zuo">所在区域： </td>
         <td>            
			<select id="province"></select>
            <select id="city"></select>
            <select id="district" name="areaId"></select><span style="color: red; vertical-align: middle;"> *注意：如果省、市修改不当，将会造成商户相关pos应用不能正常使用。</span>
		</td>
        </tr> 
        
        <tr>
         <td width="23%" class="zuo">英文名称：</td>
         <td width="77%"  class="sr"> <input name="englishName" id="englishName" class="validate['length[0,64]','%checkEnglishName']" type="text" value="${merchant.englishName }" /></td> 
        </tr>
        <tr>
        	<td class="zuo">营业地址： </td>
         	<td class="sr"> <input name=businessAddress id="businessAddress" class="validate['required','%validateChartLength{1,100}','%checkBusinessAddress'] text" type="text" value="${merchant.businessAddress }"/></td> 
        </tr>
        <tr>
         <td class="zuo">营业地址邮编： </td>
         <td class="sr"> <input name=businessAddressCode id="businessAddressCode" class="validate['%checkCode'] text" type="text" value="${merchant.businessAddressCode }"/></td> 
        </tr> 
        <tr>
         <td class="zuo">商户电话： </td>
         <td class="sr"> <input name="merchantTelephone" id="merchantTelephone" class="validate['length[0,45]','%checkTelephone'] text" type="text" value="${merchant.merchantTelephone }" /></td>
        </tr>    
        <tr>
         <td class="zuo">商户传真： </td>
         <td class="sr"><input name="merchantFax" id="merchantFax" class="validate['length[6,13]','%checkFax'] text" type="text" value="${merchant.merchantFax }" /></td> 
        </tr>     
        <tr>
         <td class="zuo">商户网站： </td>
         <td class="sr"><input name="merchantWeb" id="merchantWeb" class="validate['length[0,50]'] text"  type="text" value="${merchant.merchantWeb }" /></td> 
        </tr> 
        <tr>
         <td class="zuo">联系姓名：  </td>
         <td class="sr"> <input name="contactName" id="contactName" class="validate['required','%checkShortName','%validateChartLength{1,20}'] text" type="text" value="${merchant.contactName }" /></td>
        </tr>    
        <tr>
         <td class="zuo">联系人电话： </td>
         <td class="sr"> <input name="contactTelephone" id="contactTelephone" class="validate['required','%checkFax'] text" type="text" value="${merchant.contactTelephone}" /></td> 
        </tr>  
        <tr>
         <td colspan="2" class="S_t_turnpage" style="text-align:center;"> 
          <input type="image" src="/manager/image/btn/S_btn01.jpg" style="height: auto; width: auto;border: none;padding-right: 10px;padding-bottom: 16px;" align="middle"/>
          <input name="cancel" type="image" onclick="MM_goToURL('parent','/manager/internal/merchant/index.htm');return document.MM_returnValue" src="/manager/image/btn/S_btn03.jpg" />
         </td>
         </tr> 
     </table>
     <div style="clear:both"></div>
   </div> 
   </form>
  </div>      
  </div> 
 </div> 
<c:import url="/manager/include/footer.jsp" />
</body>
</html>
