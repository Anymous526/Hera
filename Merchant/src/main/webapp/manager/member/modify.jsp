<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 修改会员基本信息</title>
<c:import url="/manager/include/head_include.jsp" />
<c:import url="/manager/include/formcheck.jsp" />
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script>
window.addEvent("domready",function(){
    new Calendar(
      {birthday:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new FormCheck("myform");
	function reset(){
		var myform = $('myform');
		myform.reset();
	}
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
		<!-- -->
		<!--right内容-->
		<div id="right">
			<div class="turn_btn">
				<a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4"
					height="37" />
				</a>
			</div>
			<div class="right_all" style="min-height: 440px">
				<!--内容title-->
				<div class="c_title_left"></div>
				<div class="c_title_right">
					<div class="c_all">
						<div class="Q_nav"><img src="/manager/image/icon/07.gif" width="47" height="30" />修改会员</div>
					</div>
				</div>
				<!--搜索板块-->
				<!--数据表格-->
				<form id="myform" action="/manager/member/info/${member.id}/modify_commit.htm" method="post">
				<input type="hidden" name="_method" value="put"/>
			    <div class="S_table" style="margin-top:0;">
			     <table width="0" border="0" cellpadding="0" cellspacing="0">
			       <tr class="title">
			        <td width="23%" style="text-align:center">填写类型</td>
         			<td width="77%">输入区块</td> 
			       </tr>
			       <tr>
			         <td class="zuo">手机号码：</td>
			         <td class="sr">${member.user.mobile}</td> 
			        </tr> 
			       <tr>
			         <td class="zuo">姓名：</td>
			         <td class="sr"><input class="validate['required','length[20]']" name="name" value="${member.user.name}" type="text" /></td> 
			        </tr> 
			       <tr>
			         <td class="zuo">性别：</td>
			         <td class="sr">
	      				<select name="gender">
							<option value="0" <c:if test="${member.user.gender==0}"> selected="selected"</c:if>>保密</option>
							<option value="1" <c:if test="${member.user.gender==1}"> selected="selected"</c:if>>男</option>
							<option value="2" <c:if test="${member.user.gender==2}"> selected="selected"</c:if>>女</option>
						</select>
			         </td> 
			        </tr> 	
			       <tr>
			         <td class="zuo">会员生日：</td>
			         <td class="sr"><input name="birthday" id="birthday" value='<fmt:formatDate value="${member.user.birthday}" pattern="yyyy-MM-dd"/>' type="text" /></td> 
			        </tr> 	  
			       <tr>
			         <td class="zuo">联系地址：</td>
			         <td class="sr"><input class="validate['%validateChartLength{0,30}']" name="address" value="${member.user.address}" type="text" /></td> 
			        </tr> 
			       <tr>
			         <td class="zuo">邮箱：</td>
			         <td class="sr"><input class="validate['%validateChartLength{0,40}','email']" name="email" value="${member.user.email}" type="text" /></td> 
			        </tr>     
			        <tr>
			        	<td colspan="2" class="S_t_turnpage" style="text-align:center; padding-top: 3px;"> 
			        		<input type="image" src="/manager/image/btn/S_btn01.jpg" style="width:auto;height:auto;border: 0px;"/>
							<a style="vertical-align: middle; margin: 4px 0px;" href="/manager/member/info/info.htm"><img style="padding:3px 0px 0px 0px;" src="/manager/image/btn/S_btn03.jpg"/></a>
			        	</td>
			        </tr>  
			     </table>
				
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<c:import url="/manager/include/footer.jsp" />
</body>
</html>


