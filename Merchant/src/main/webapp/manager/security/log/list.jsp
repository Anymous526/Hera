<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 日志查询</title>
        <c:import url="/manager/include/head_include.jsp"/>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
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
	                            <img src="/manager/image/icon/07.gif" width="47" height="30" />管理员日志
	                        </div>
	                    </div>
	                </div><!--搜索板块-->
	                <div class="M_ss">
	                    <ul>
	                        <li>
	                            <form action="/manager/security/log/list.htm" method="post">
	                            管理员帐号<input name="mobile" type="text" value="${param.mobile}"/>
	                            <span>
	                            加入时间 <input id="startCreateDate" name="startCreateDate" type="text" value="${param.startCreateDate}"/>
	                            到<input id="endCreateDate" name="endCreateDate" type="text" value="${param.endCreateDate}"/>
	                            </span>
	                            <input type="image" src="/manager/image/btn/S_btn.jpg" class="ss_srk" style="width:64px; height:auto; border:none"/>
	                            </form>
	                        </li>
 
	                    </ul>
	                </div>
	                <!--数据表格-->
	                <div class="S_table">
	                    <table width="0" border="0" cellpadding="0" cellspacing="0">
	                        <tr class="title">
	                            
	                            <td width="20%">
	                                管理员帐号
	                            </td>
	                            <td width="20%">
	                                姓名
	                            </td>
	                            <td width="20%">
	                                操作时间
	                            </td>
	                            <td  >
	                               操作类型
	                            </td>
	                             
	                        </tr>
	                        
	                        <c:forEach items="${logs}" var="log" varStatus="st">
	                          <tr <c:if test="${st.index%2 !=0}">class="S_t_01"</c:if>>
	                            
	                            <td>
	                                ${log.operator.mobile}
	                            </td>
	                            <td>
	                                 ${log.operator.name}
	                            </td>
	                            
	                            <td>
	                              <fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd HH:mm"/>
	                            </td>
	                            <td>
	                                ${log.operatorType.desc}
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

 