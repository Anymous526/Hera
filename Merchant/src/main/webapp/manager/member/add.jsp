<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 增加会员信息</title>
<c:import url="/manager/include/head_include.jsp"/>
<c:import url="/manager/include/formcheck.jsp"></c:import>
<script type="text/javascript" src="/manager/js/member.js"></script>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<link href="/manager/css/style.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
window.addEvent("domready",function(){
    new Calendar(
      {birthday:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
    );
    new FormCheck('myform',{
		display : {
			showErrors:1,
			indicateErrors : 1,
			fadeDuration : 1000
		}
	});
});
function checkMobile(el) {
	if(!el.value.trim().test(/^\d{11}$/)) {
		el.errors.push("此字段为11位数字");
		return false;
	}
}

function checkIdentity(el) {
	var indentity = el.value.trim();
	if(!(indentity.test(/^\d{15}$/)||indentity.test(/^\d{18}$/)||indentity.test(/^\d{17}[a-zA-Z0-9]$/))) {
		el.errors.push("身份证为15位数字或者18位字符和数字组成");
		return false;
	}
}
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
		<!-- -->
		<!--right内容-->
	<div id="right">
	 <div class="turn_btn"><a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a></div>
	 <div class="right_all" style=" min-height:440px"> 
	  <!--内容title-->
	  <div class="c_title_left"></div>
	  <div class="c_title_right">
	   <div class="c_all">
	    <div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />新增会员</div>
	   </div>
	  </div>  
	  <div style="font-size:12px;color:red;line-height:30px;padding-left:11px;">${msg}</div>
	  <!--搜索板块--> 
	  <!--数据表格--> 
	<form id="myform" action="/manager/member/info/create.htm" method="post">
	  <input type="hidden" name="_method" value="post"/>
	 	 <div class="S_table" style="margin-top:0;">
	     <table width="0" border="0" cellpadding="0" cellspacing="0">
	        <tr>
	         <td class="zuo">姓名：</td>
	         <td class="sr"><input value="${param.name}" class="validate['%validateChartLength{0,20}']" name="name" type="text" /></td> 
	        </tr> 
	        <tr>
	         <td class="zuo">手机号码：</td>
	         <td class="sr"><input name="mobile" value="${param.mobile}" class="validate['required','%checkMobile'] text" /></td> 
	        </tr>
	        <tr>
	         <td class="zuo">初始积分：</td>
	         <td class="sr"><input name="point" value="${param.point==null?0:param.point}" class="validate['digit[0,10000]']" /></td> 
	        </tr>
	        <tr>
	         <td class="zuo">会员等级：</td>
	         <td><select name="level">
	            <c:forEach items="${levels}" var="lev">
					<option value="${lev.value}" <c:if test="${param.level == lev.value}">selected="selected"</c:if> >${lev.desc}</option>
				</c:forEach>
	          </select></td> 
	        </tr> 
	        <tr>
	         <td class="zuo">性别：</td>
	         <td>
	         	<select name="gender">
	         		<option value="0" <c:if test="${param.gender==0}"> selected="selected"</c:if>>保密</option>
		            <option value="1" <c:if test="${param.gender==1}"> selected="selected"</c:if>>男 </option>
		            <option value="2" <c:if test="${param.gender==2}"> selected="selected"</c:if>>女</option> 
	          	</select>
	          </td> 
	        </tr>   
			<tr>
	         <td class="zuo">出生日期：</td>
	         <td class="sr">
	         <span><input name="birthday" value="${param.birthday }" id="birthday" type="text" /> </span></td> 
	        </tr>
			<tr>
	         <td class="zuo">身份证号：</td>
	         <td class="sr"><input value="${param.cardNumber}" class="validate['%checkIdentity'] text" name="cardNumber" type="text" /></td> 
	        </tr>
			<tr>
	         <td class="zuo">邮箱：</td>
	         <td class="sr"><input value="${param.email }" class="validate['%validateChartLength{0,40}','email']" name="email" type="text" /></td> 
	        </tr>
			<tr>
	         <td class="zuo">联系地址：</td>
	         <td class="sr"><input value="${param.address }" class="validate['%validateChartLength{0,30}']" name="address"/> </td>
			</tr> 
	        <tr>
	         <td colspan="2" class="S_t_turnpage" style="text-align:center; padding-bottom:10px"> 
	          <input type="image" src="/manager/image/btn/S_btn01.jpg" style="height: auto; width: auto;border: none;padding-right: 10px;" align="middle" />
	         	<a href="/manager/member/info/info.htm"><img style="vertical-align: middle;" src="/manager/image/btn/S_btn03.jpg"/></a>
	          
	         </td> 
	         </tr> 
	     </table> 
	     <div style="clear:both"></div>
	   </div>
	  </form>
	   <div style="clear:both"></div>
	   </div>
	  </div>      
	  </div>
  	<c:import url="/manager/include/footer.jsp" /> 
</body>
</html>

