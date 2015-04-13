<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="levels" value="com.vlives.boss.member.domain.Level@values()"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员信息管理</title>
<c:import url="/manager/include/head_include.jsp"/>
<script type="text/javascript" src="/manager/js/member.js"></script>
<link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
<script type="text/javascript" src="/manager/js/import.js"></script>
<script>
window.addEvent("domready",function(){
    new Calendar(
      {startLastConsumeDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new Calendar(
      {endLastConsumeDate:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
    );
    new Calendar(
      {startCreateDate:'Y-m-d'}, 
      {direction:0,tweak:{x: 6, y: 0}}
    );
    new Calendar(
      {endCreateDate:'Y-m-d'}, 
      {direction: 0,tweak: {x: 6, y: 0}}
    );
    com.checkBoxToRadio("input[class=checkboxItem]");
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
						<div class="Q_nav">
							<img src="/manager/image/icon/07.gif" width="47" height="30" />会员信息管理
							    <a href="/manager/member/info/template/download.htm"><img src="/manager/image/btn/S_hymb.jpg" width="113" height="25" /></a>
								<a href="javascript:open();"><img src="/manager/image/btn/S_hydr.jpg" width="90" height="25" /></a>
							
						</div>
					</div>
				</div>
				<!--搜索板块-->
				<form action="/manager/member/info/info.htm" method="post">
				<div class="M_ss">
					<ul>
						<li>会员姓名 <input name="name" value="${myname}" type="text" />手机号码 <input type="text" value="${mobile}" name="mobile" />
							会员等级<select name="level">
									<option value="0">全部会员</option>
									<c:forEach items="${levels}" var="lev">
										<option value="${lev.value}" <c:if test="${level.value == lev.value}">selected="selected"</c:if> >${lev.desc}</option>
									</c:forEach>
							</select>
						</li>
						<li>
						<span>最后消费时间<input name="startLastConsumeDate" value="<fmt:formatDate value='${startLastConsumeDate}' pattern="yyyy-MM-dd"/>" id="startLastConsumeDate" type="text" /> 
						到<input type="text" value="<fmt:formatDate value='${endLastConsumeDate}' pattern="yyyy-MM-dd"/>" id="endLastConsumeDate" name="endLastConsumeDate"/>
						</span>
						<span>注册时间 <input name="startCreateDate" value="<fmt:formatDate value='${startCreateDate}' pattern="yyyy-MM-dd"/>" id="startCreateDate" type="text" /> 
						到<input type="text" value="<fmt:formatDate value='${endCreateDate}' pattern="yyyy-MM-dd"/>" name="endCreateDate" id="endCreateDate" />
						</span> <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none" />
						</li>
						<li>
							<dl>
							 	<a href="javascript:void(0)" onclick ="com.member.add()"><dd class="S_01"> </dd>
							 	</a>
								<a href="javascript:void(0)" onclick ="com.member.modify()"><dd class="S_02"></dd>
								</a>
								<a href="javascript:void(0)" onclick ="com.member.freeze()"><dd class="S_03"></dd>
								</a>
								<a href="javascript:void(0)" onclick ="com.member.unFreeze()"><dd class="S_04"></dd>
								</a>
								<a href="javascript:void(0)" onclick ="com.member.logout()"><dd class="S_07"></dd>
								</a>
							</dl></li>
					</ul>
				</div>
				</form>
				<!--数据表格-->
				<div class="S_table">
					<table width="0" border="0" cellpadding="0" cellspacing="0">
						<tr class="title">
							<td  width="5%" class="zuo">选择</td>
							<td width="10%">手机号</td>
							<td width="8%">姓名</td>
							<td width="8%">会员等级</td>
							<td width="5%">状态</td>
							<td width="8%">总积分</td>
							<td width="13%">注册时间</td>
							<td width="13%">最后消费时间</td>
							<td width="10%">邮箱</td>
							<td width="20%">联系地址</td>
						</tr>
						<c:forEach items="${list}" var="member" varStatus="st">
						<tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
					         <td class="zuo"><input class="checkboxItem" type="checkbox" value="${member.id }" /></td>
					         <td><a style="color:#045185" href="javascript:void(0)" onclick="com.member.view(${member.id})">${member.user.mobile }</a></td>
					         <td>${member.user.name }</td>
					         <td>${member.level.desc }</td>
					         <td class="statusDesc">${member.status.desc }</td> 
					         <td>${member.totalPoint }</td> 
					         <td><fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm"/> </td>  
					         <td>
					         	<c:choose>
					         		<c:when test="${!empty member.lastConsumeDate}">
					         			<fmt:formatDate value="${member.lastConsumeDate}" pattern="yyyy-MM-dd HH:mm"/>
					         		</c:when>
					         		<c:otherwise>
					         			未消费
					         		</c:otherwise>
					         	</c:choose>
					         </td>  
					         <td>${member.user.email }</td>
					         <td title="${member.user.address}"><j:replace value="${member.user.address}"/> </td>
					    </tr>
						</c:forEach> 
						<tr>
							<td colspan="10" align="right">${pagination}</td>
						</tr>
					</table>
					<div style="clear: both"></div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp"/>
</body>
</html>

