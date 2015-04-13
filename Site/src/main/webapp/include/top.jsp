<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	com.vlives.boss.user.domain.User user = (com.vlives.boss.user.domain.User)request.getAttribute("principal");

	String displayName = "";
	String avatar = "";
	
	if (user != null) {
		displayName = user.getPetName();
		if (displayName == null || displayName == "") {
			displayName = user.getShortMobile();
		}
	
		avatar = user.getHead();
		if (avatar == null || avatar == "") {
			avatar = "/images/head.jpg";
		}
	}
%>

<div class="Bu_Remarks" id="divNotice" style="display: none;">
	<div class="Bu_R_left"></div>
	<div class="Bu_R_content">
		<span id="spnNotice"></span>
		<h2>
			<a href="javascript:void(0);" onClick="closeNotice();"></a>
		</h2>
	</div>
	<div class="Bu_R_right"></div>
</div>

<!--top共用板块-->
<div class="topall">
	<div class="Top">
		<div class="Logo">
			<a href="/index"><img src="/images/logo.png" />
			</a>
		</div>
		<div class="C_all">
			<div class="City" onclick="selectcity();" id="selCity">${currentCity.city.name}</div>
			<div class="Address" id="Address" style="display: none;">
				<div class="Add_top"></div>
				<div class="Add_content">
					<c:forEach var="entry" items="${cities}">
					<ul>
						<h1>${entry.key}</h1>
						<c:forEach var="city" items="${entry.value}">
							<c:if test="${city.current == true}">
							<li class="now"><a href="javascript:void(0)"
								id="city_${city.city.id}" onMouseOver="selectState(${city.city.id}, 1);"
								onMouseOut="outState(${city.city.id}, '${city.city.name}');");">${city.city.name}</a>
							</li>
							</c:if>
							<c:if test="${city.current == false}">
							<c:if test="${city.status == 1}">
							<li><a href="javascript:void(0)"
								id="city_${city.city.id}" onMouseOver="selectState(${city.city.id}, 2);"
								onMouseOut="outState(${city.city.id}, '${city.city.name}');" onclick="jumpToCity(${city.city.id});">${city.city.name}</a>
							</li>
							</c:if>
							<c:if test="${city.status == 2}">
							<li class="Immediately"><a href="javascript:void(0)"
								id="city_${city.city.id}" onMouseOver="selectState(${city.city.id}, 3);"
								onMouseOut="outState(${city.city.id}, '${city.city.name}');");">${city.city.name}</a>
							</li>
							</c:if>
							</c:if>
						</c:forEach>
					</ul>
					</c:forEach>
				</div>
				<div class="Add_bottom"></div>
			</div>
		</div>
		<!--搜索框-->
		<form id="form" action="/search" method="post"
			onsubmit="return checkText();">
			<div class="Div_search">
				<%
					String name = request.getParameter("name");
					if (name == null || name == "")
						name = "搜索你感兴趣的商家或地点... ...";
				%>
				<input name="name" id="name" style="color: #ccc"
					onclick="clearText()" class="Div_ss" value="<%=name%>" type="text" />
				<input name="searchsubmit" id="searchsubmit" class="Div_btn"
					type="submit" value="" />
			</div>
		</form>
		<div class="M_InfoR" id="top.userInfo" <%=(user == null)? "style='display: none'" : ""%> >
			<ol>
				<a href="/user/memberMerchants" title="我的首页"><img id="userHead"
					name="userHead" src="<%=avatar%>" />
				</a>
				<div class="txt_txt">
					<h1><span id="userName" style="float:left;display:block;"><%=displayName%></span><span class="wei01" id="commentsNotice" style="display:none"></span></h1>
					<h2>
						<span><a href="/user/memberMerchants">我的首页</a> &nbsp;
							&nbsp; <a href="javascript:logout();">退出</a>
						</span>
					</h2>
				</div>
			</ol>
		</div>
		<div class="D_lu" id="spnLoginContent" <%=(user != null)? "style='display: none'" : ""%> >
			<a href="javascript:openLoginWin();" id="btnLogin">会员登录</a> | <a href="/manager/index.htm" target="_blank"> 商家登录</a>
		</div>
	</div>
</div>

<!--登录系统共用板块-->
<div class="Enter" id="divLogin" style="display:none; z-index:9999;">
  <form style="margin:0px; " id="form">
  <div class="E_top"><span>会员登录 Login</span><span class="E_close" id="closeLogin"></span></div>
  <div class="E_content">
   <div class="E_Cleft">
    <dl>
      <dt>推荐通过合作网站登录</dt>
      <dd><img src="/images/icon/sina.png" /><a href="javascript:void(0);" onclick="toWeiboLogin()">新浪微博登录</a></dd>
      <!-- <dd><img src="/images/icon/renren.png" /><a href="#">人人网帐号登录</a></dd>
      <dd><img src="/images/icon/kaixin.png" /><a href="#">开心网帐号登录</a></dd> 
      <dd><img src="/images/icon/txweibo.png" /><a href="javascript:void(0);" onclick='toQzoneLogin()'>腾讯微博帐号登录</a></dd>-->
    </dl>
   </div>
   <div class="E_Cright">
    <dl>
     <dt>通过手机号码登录</dt>
     <dd>
       <span class="Log_name">手机号码：</span>
       <span><input class="Log_01 validate['required']" name="mobile" id="mobile" type="text" /></span>
       <span class="Log_yzm" id="spnCaptcha"><a href="javascript:void(0);" onclick="getVerficationCodeForLogin()">获取验证码</a></span>
     </dd>
     <dd>
       <span class="Log_name">验证码：</span>
       <span><input class="Log_02" name="captcha" id="captcha" type="text" /></span>
       <span class="Log_yzm"><!-- <img src="/images/yzm.jpg" /> --></span> 
     </dd>
     <dd>
       <span class="Log_name"></span>
       <span> <input name="save" id="save" type="checkbox" value="1" /> 记住登录状态</span> 
     </dd>
     <dd style="padding-left:72px; height:auto; margin-top:10px"><input class="Log_btn" name="sumbitLogin" id="sumbitLogin" type="button" /></dd>
    </dl>
   </div>
   <div style="clear:both; "></div>
  </div>
  <div class="E_bottom"></div>
  </form> 
</div>

<!-- 绑定手机号码页面 -->
<div class="Enter" id="divBinding" style="display:none;">
  <div class="E_top"><span>关联手机号码</span><span class="E_close" id="closeBinding"></span></div>
  <div class="E_content01">
    <div class="E_Dright">
    <dl>
     <dt>您的账号尚未关联手机号码，为了您的会员权益，请使用您的手机号码。</dt>
     <dd>
       <span class="Log_name01">手机号码：</span>
       <span><input class="Log_01" id="bindingMobile" type="text" /></span>
       <span class="Log_yzm" id="spnVerificationCodeForBinding"><a href="javascript:void(0);" id="btnCaptcha" onclick="getVerficationCodeForBinding()">获取验证码</a></span></dd>
     <dd>
       <span class="Log_name01">验证码：</span>
       <span><input class="Log_02" id="bindingCode" type="text" /></span></dd>
      <dd style="padding-left:228px; height:auto; margin-top:30px"><input class="Log_btn" id="submitBinding" type="button" /></dd>
    </dl>
   </div>
   <div style="clear:both"></div>
  </div>
  <div class="E_bottom"></div>
</div>