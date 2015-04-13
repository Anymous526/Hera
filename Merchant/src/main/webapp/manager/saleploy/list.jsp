<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 营销活动管理</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
         <c:import url="/manager/include/head_include.jsp"/>
         <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
         <script type="text/javascript" src="/manager/js/saleploy.js"></script>
         <script>
           window.addEvent("domready",function(){
            com.checkBoxToRadio("input[class=checkboxItem]");
             new Calendar(
              {startCreateDate:'Y-m-d'}, 
              {direction: 0,tweak: {x: 6, y: 0}}
            );
            new Calendar(
              {endCreateDate:'Y-m-d'}, 
              {direction: 0,tweak: {x: 6, y: 0}}
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
                <a href="javascript:void(0)"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a>
            </div>
            <div class="right_all" style=" min-height:440px">
                <!--内容title-->
                <div class="c_title_left">
                </div>
                <div class="c_title_right">
                    <div class="c_all">
                        <div class="Q_nav">
                            <img src="/manager/image/icon/07.gif" width="47" height="30" />营销活动管理
                        </div>
                    </div>
                </div><!--搜索板块-->
                <div class="M_ss">
                    <ul>
                        <li>
                          <form action="./list.htm" method="get">
                            活动主题 <input name="name" type="text" value="${param.name}" />
                             <span>活动时间&nbsp;&nbsp;<input id="startCreateDate" name="startCreateDate" type="text" value="${param.startCreateDate}"/>
                            到&nbsp;&nbsp;<input id="endCreateDate" name="endCreateDate" type="text" value="${param.endCreateDate}"/></span>
                            <input  class="ss_srk" style="width:64px; height:25px; border:none"  type="image" src="/manager/image/btn/S_btn.jpg" />
                            </form>
                        </li>
                        <li>                     
                          <dl>
                             <a href="javascript:void(0)" onclick="com.saleploy.logOutPloy()">
                               <dd class="S_07"></dd>    
	                         </a>
                            
                            <a href="javascript:void(0)" onclick="com.saleploy.modifyPloy()">
                                <dd class="S_02">
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
                            <td style="text-align:center" width="2%"></td>
                            <td width="10%">
                                活动主题
                            </td>
                            <td width="26%">
                                营销活动内容
                            </td>
                            <td width="10%">
                                活动类型
                            </td>
                            <td width="8%">
                                状态
                            </td>
                            <td width="8%">
                                创建日期
                            </td>
                            <td width="10%">
                                活动有效期
                            </td>
                            <td width="8%">
                                预发短信
                            </td>
                             <td width="6%">
                                已发短信
                            </td>
                        </tr>
                        <c:forEach items="${salePloys}" var="salePloy" varStatus="st">
                          <tr <c:if test="${st.index%2==1}">class="S_t_01"</c:if>>
                            <td class="zuo">
	                            <input class="checkboxItem" type="checkbox" value="${salePloy.id}" />
	                        </td>
                            <td class="zuo">
                                ${salePloy.name}&nbsp;
                            </td>
                            <td class="templat">
                                ${salePloy.template}&nbsp;
                            </td>
                            <td>
                                ${salePloy.salePloyType.desc}&nbsp;
                            </td>
                            <td class="statusDesc">
                                ${salePloy.status.desc}&nbsp;
                            </td>
                            <td>
                                <fmt:formatDate value="${salePloy.createDate}" pattern="yyyy-MM-dd"/>&nbsp;
                            </td>
                            <td>
                                <fmt:formatDate value="${salePloy.validStartDate}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${salePloy.validEndDate}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                ${salePloy.smsCount}&nbsp;
                            </td>
                            <td>
                                ${salePloy.sendCount}&nbsp;
                            </td>
                          </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="9"  align="right">
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
