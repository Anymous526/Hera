<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<div id="left">
  <ul>
    <li class="home">
    菜单导航
  </li>
  <li  class="roleMenu index">
	  <img src="/manager/image/icon/05.gif" width="18" height="15" />
	  <a class="roleGroup"  href="/manager/index.htm">
	    首页
	  </a>
	  <div class="second" style="display:none">
	     <dl>
	       <c:forEach items="${operator.rolesByExistRoleGroup}" var="opRole">
	            <c:if test="${opRole.role.shortcut}">
	            <dd><img height="13" width="11" src="/manager/image/icon/06.gif"/>
	            <a href="${opRole.role.entryIndex}" title="${opRole.role.name}">${opRole.role.name}</a></dd> 
	            </c:if>
	          </c:forEach>
	     </dl>
      </div>
  </li>
  <c:forEach items="${operator.roleGroups}" var="groupMap" varStatus="st">
    <li class="roleMenu">
        <img src="/manager/image/icon/05.gif" width="18" height="15" />
		<a class="roleGroup" href="##">
		  <c:out value="${groupMap.key.desc}"/>
		</a>
		<div class="second" style="display:none">
		  <c:forEach items="${groupMap.value}" var="opRule">
		  <dl>
			<dd>
				<img src="/manager/image/icon/06.gif" width="11" height="13" />
				<a class="roleItem" href="${opRule.role.entryIndex}">
				  <c:out value="${opRule.role.name}"/>
				</a>
			</dd>
		  </dl>
		  </c:forEach>
		</div>
      </li>
    </c:forEach>
 
 
</ul>
</div>

<script>
  window.addEvent("domready",function(){
  com.initMenu();
  com.changeMenu();
    $$(".turn_btn").getFirst().addEvent("click",function(){
      com.showOrHideMenu();
    });
    
  });
</script>
