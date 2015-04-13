<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录会生活商户管理平台 - 登录</title>
<link href="/manager/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="/manager/css/login.css" rel="stylesheet"
	type="text/css" />
	<script src="/manager/js/ie6png.js" type="text/javascript"></script>
	<script type="text/javascript" src="/manager/js/mootools/mootools-1.2.5-core-yc.js"></script>
	<script type="text/javascript" src="/manager/js/mootools/mootools-1.2.5.1-more.js"></script>
	 <c:import url="/manager/include/formcheck.jsp"/>
    <script type="text/javascript" src="/manager/js/boss.js"></script>
     <script>
       window.addEvent("domready",function(){
          new FormCheck('form', {
			display : {
				showErrors:0,
				indicateErrors : 1,
				fadeDuration : 1000
			}
	      });
       });
     </script>

</head>

<body>
  <form id="form" action="logincommit.htm" method="post">
    <input  type="hidden" name="from" value="${param.from}"/>
    <div id="login">
		<ul>
			<li><input class="validate['required']" maxlength="11" style="width:150px" name="mobile" type="text" value="${mobile}"/></li>
			<li><input class="validate['required','length[6,15]']" style="width:150px" name="password" type="password" /></li>
			<li>
			  <input class="validate['required']" maxlength="4" style="width:85px; margin-right:1px;" name="randomValue" type="text" />
			 <img style="margin-top:2px; cursor:pointer" onclick="com.refreshValidatorNumber(this)" src="/manager/common/random_code.htm"/>
			</li>
			<!--
			<li style="height: 25px; color: #fff;"><input name=""
				style="line-height: 0; border: none; vertical-align: middle;"
				type="checkbox" value="" /> 两周内自动登录</li>
			  -->
			<li>
				<dl>
				  <input class="validate['submit']" style="border:none; width:142px; height:25px;" type="image" src="/manager/image/btn/login.jpg"/>
				</dl>
			</li>
			<li>
			    <strong style="color:#f44600">${errorMsg}</strong>
			</li>
			<!-- 
			<li style="height: 40px; line-height: 30px">还没有成为注册商户？ <a href="#">
			<strong> 立即注册 </strong> </a></li>
			 -->
		</ul>
	</div>
  </form>
</body>
</html>
