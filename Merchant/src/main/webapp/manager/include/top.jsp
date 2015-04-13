<%@page import="java.util.Date"%>
<%@page import="com.vlives.util.DateUtils"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script type="text/javascript">
	window.addEvent("domready",function(){
		var mobile = com.operaotrCurrentVisitor();
		$("operatorMobile").set("html",mobile);
	});
</script>
<div id="top">
  <div class="logo">
    <img src="/manager/image/icon/01.gif" width="15" height="16" />
    <span class="System">
      <img src="/manager/image/icon/02.gif" width="12" height="15" />
     	 管理员帐号：<span id="operatorMobile"></span>
    </span>
<span class="System" style="margin-left:40px;  background:none">免费咨询热线:400-1588-996</span>
  </div>
  <div class="t_right">
    <div class="t_nav">
      <a href="#">
        意见反馈
      </a>
      &nbsp;
      <a href="#">
        帮助中心
      </a>
      &nbsp;
      <a href="/manager/logout.htm">
        退出
      </a>
    </div>
    <div class="time">
      <img src="/manager/image/icon/03.gif" width="16" height="16" />
      日期：<%=DateUtils.format(new Date(),"yyyy-MM-dd") %>
      <img src="/manager/image/icon/04.gif" width="14" height="14" />
    </div>
  </div>
</div>
