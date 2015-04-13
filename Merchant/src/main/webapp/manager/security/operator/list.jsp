<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 管理员查询</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
       <script type="text/javascript" src="/manager/js/operator.js"></script>
        <script>
          window.addEvent("domready",function(){
              com.checkBoxToRadio("input[class=checkboxItem]");
              new Calendar(
                 {startCreateDate:'Y-m-d'}, 
                 {direction:0,tweak:{x: 6, y: 0}}
               );
               new Calendar(
                 {endCreateDate:'Y-m-d'}, 
                 {direction:0,tweak: {x: 6, y: 0}}
               );
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
	                <a href="javascript:void(0)"  >
				      <img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" />
				    </a>
	            </div>
	            <div class="right_all" style=" min-height:440px">
	                <!--内容title-->
	                <div class="c_title_left">
	                </div>
	                <div class="c_title_right">
	                    <div class="c_all">
	                        <div class="Q_nav">
	                            <img src="/manager/image/icon/07.gif" width="47" height="30" />账户管理
	                        </div>
	                    </div>
	                </div><!--搜索板块-->
	                <div class="M_ss">
	                    <ul>
	                        <li>
	                            <form action="/manager/security/operator/list.htm" method="post">
	                            管理员帐号<input name="mobile" type="text" value="${param.mobile}"/>
	                            <span>
	                            加入时间 <input id="startCreateDate" name="startCreateDate" type="text" value="${param.startCreateDate}"/>
	                            到<input id="endCreateDate" name="endCreateDate" type="text" value="${param.endCreateDate}"/>
	                            </span>
	                            <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/>
	                            </form>
	                        </li>
	                        <li>
	                            <dl>
	                                <a href="./add.htm" >
	                                    <dd class="S_01">
	                                    </dd>
	                                </a>
	                                <a href="javascript:void(0)" onclick="com.operator.update()">
	                                    <dd class="S_02">
	                                    </dd>
	                                </a>
	                                <a href="javascript:void(0)" onclick="com.operator.freeze()">
	                                    <dd class="S_03">
	                                    </dd>
	                                </a>
	                                <a href="javascript:void(0)" onclick="com.operator.unFreeze()">
	                                    <dd class="S_04">
	                                    </dd>
	                                </a>
	                            </dl>
	                        </li>
	                    </ul>
	                </div>
	                <!--数据表格-->
	                <div class="S_table">
	                    <table width="0" border="0" cellpadding="0" cellspacing="0">
	                        <tr class="title">
	                            <td style="text-align:center" width="3%">    
	                            </td>
	                            <td width="10%">
	                                管理员帐号
	                            </td>
	                            <td width="50%">
	                                权限
	                            </td>
	                            <td width="7%">
	                                状态
	                            </td>
	                            <td width="15%">
	                                加入时间
	                            </td>
	                            <td width="15%">
	                                最后登录日期
	                            </td>
	                        </tr>
	                        
	                        <c:forEach items="${operators}" var="operator" varStatus="st">
	                          <tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
	                            <td class="zuo">
	                                <input class="checkboxItem" type="checkbox" value="${operator.id}" />
	                            </td>
	                            <td>
	                                <a href="./${operator.id}/">${operator.mobile}</a>
	                            </td>
	                            <td>
	                                ${operator.roleDesc}
	                            </td>
	                            <td class="statusDesc">
	                                ${operator.status.desc}
	                            </td>
	                            <td>
	                              <fmt:formatDate value="${operator.createDate}" pattern="yyyy-MM-dd HH:mm"/>
	                               
	                            </td>
	                            <td>
	                                <fmt:formatDate value="${operator.lastLoginDate}" pattern="yyyy-MM-dd HH:mm"/>
	                            </td>
	                          </tr>
	                        </c:forEach>
	                        
	                        <tr>
	                             <td colspan="6" align="right"> 
	                               <c:out value="${pagination}" escapeXml="false"></c:out>
	                             </td>
	                        </tr>
	                    </table>
	                    <div style="clear:both">
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	     
	    <c:import url="/manager/include/footer.jsp"/>
	</body>
</html>

 