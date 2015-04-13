<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="j" uri="/just"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 发布营销活动</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <c:import url="/manager/include/formcheck.jsp"/>
        <link href="/manager/css/bomb.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
        <script type="text/javascript" src="/manager/js/saleploy.js"></script>
        <script type="text/javascript" src="/manager/js/preview.js"></script>
        <script>
          window.addEvent("domready",function(){
        	new com.keypress("template",47);
            new Calendar(
              {startDate:'Y-m-d'}, 
              {direction:1,tweak:{x: 6, y: 0}}
            );
            new Calendar(
              {endDate:'Y-m-d'}, 
              {direction: 1,tweak: {x: 6, y: 0}}
            );
            new Calendar(
              {timingTime:'Y-m-d'}, 
              {direction: 1,tweak: {x: 6, y: 0}}
            );
            new FormCheck('form', {
                submitReload:1,
				display : {
					showErrors:1,
					indicateErrors : 1,
					fadeDuration : 1000
				}
		    });
            //com.loadTimeMenu("startTime");
            //com.loadTimeMenu("endTime");
            new com.saleploy.DynamicEffect(${existCreateMemberSend},${existtradeMoneySend});
          });
          
        </script>
    </head>
    <body>
        <!--top板块-->
        <c:import url="/manager/include/top.jsp"/>
        <!-- --><!--中间板块-->
        <div id="content">
            <!--left导航-->
            <c:import url="/manager/include/left.htm"/> 
            <!-- -->
            <!--right内容-->
            <div id="right">
                <div class="turn_btn" >
				    <a href="javascript:void(0)"  >
				      <img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" />
				    </a>
				  </div>
                <div class="right_all" style=" min-height:440px">
                    <!--快速导航-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />发布营销短信流程
                            </div>
                        </div>
                    </div><!--发布营销活动-->
                    <div class="M_Pub_step">
                        <table>
                            <tr>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step01.png" width="171" height="50" /></a></td>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step2.png" width="171" height="50" /></a></td>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step3.png" width="171" height="50" /></a></td>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step4.png" width="171" height="50" /></a></td>
						     </tr>
                        </table>
                    </div>
                    <div class="M_step_content">
                      <form id="form"  action="create_two.htm" method="post">
                        <input type="hidden" name="merchantName" id="merchantName"/>
                        <ul>
                            <li style="height:30px;">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>营销短信标题:</span>
                                <input class="validate['required','length[2,50]']" name="name" type="text" value="${tempSalePloy.name}"/>
                                &nbsp;&nbsp;(如：十一促销全场5折、消费满100立减30)
                            </li>
                            <li class="M_t_01">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" />
                                <span>有效期:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input class="validate['required']" name="startDate" id="startDate" type="text" value="<fmt:formatDate value="${tempSalePloy.validStartDate}" pattern="yyyy-MM-dd"/>"/> 到 
                                <input class="validate['%validatorGrateDate']" name="endDate" id="endDate" type="text" value="<fmt:formatDate value="${tempSalePloy.validEndDate}" pattern="yyyy-MM-dd"/>"/>
                                &nbsp;&nbsp;(活动的有效期：如2011年10月1日到2011年10月7日凭短信到店消费可享受5折优惠)
                            </li>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>活动类型:</span>
						        <input  style="border:none; width:auto; height:auto;" name="saleType" type="radio" value="1" checked="checked"/>自动发送 &nbsp;&nbsp; 
						        <input id="timingBox" style="border:none; width:auto; height:auto;" name="saleType" type="radio" value="2" />定时发送
						        <span id="timingTimeDiv" style="display:none">
					              <input name="timingTime" id="timingTime" type="text"/>
					            </span>
						        <input id="createMemberBox" style="border:none; width:auto; height:auto;" name="saleType" type="radio" value="3" />注册会员发送 &nbsp;&nbsp; 
						        <input id="tradeMinMoneyBox" style="border:none; width:auto; height:auto;" name="saleType" type="radio" value="4" />单次消费满额发送
						        <span id="tradeMinMoneyDiv"  style="display:none">
					              <input name="tradeMinMoney" id="tradeMinMoney" type="text"/>单位(元)
					            </span>
					            &nbsp;&nbsp;<span id="saleTypeMsg" style="color:red"></span>
					        </li>
					        <!-- 
                            <li class="M_t_01">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>可用时段:</span>
                                <select class="validate['%validatorGrateTime']" name="startTime" id="startTime"></select>
                                到
                                <select  name="endTime" id="endTime"></select>
                            </li>
                             -->
                            <c:if test="${merchant.existValidChildren}">
	                            <li id="allMerchantDiv">
	                                <img src="/manager/image/icon/setp.gif" style="float:left" width="25" height="25" />
	                                <span style="float:left">指定适用店面:</span>
	                                &nbsp;<i><input id="allMerchantBox" style="border:none; width:auto; height:auto;" type="checkbox"/> 选择所有店面 </i><br />
	                                <dl>
	                                    <dd class="M_top">
	                                        <img src="/manager/image/bg/wx.png" width="104" height="30" />
	                                    </dd>
	                                    <h1 class="left_merchant">
	                                        <a href="javascript:void(0)">
	                                          <j:replace value="${merchant.name}" length="22" byByteLength="true" suffix=".."/>
	                                        </a>
		                                    <c:forEach items="${merchant.validChildrens}" var="child" varStatus="st">
		                                      <a href="javascript:void(0)" <c:if test="${st.index%2==0}">class="M_second"</c:if>>
		                                      	<j:replace value="${child.name}" length="22" byByteLength="true" suffix=".."/>
		                                      </a>
		                                    </c:forEach>
	                                    </h1>
	                                </dl>
	                                <div class="turn">
	                                    <img id="toLefttBtn" class="canMove" src="/manager/image/btn/M_left01.png" width="32" height="32" />
	                                    <img id="toRightBtn" class="canMove" src="/manager/image/btn/M_right01.png" width="32" height="32" />
	                                </div>
	                                <dl>
	                                    <dd class="M_top">
	                                        <img src="/manager/image/bg/yx.png" width="104" height="30" />
	                                    </dd>
	                                    <h1 class="right_merchant">
	                                         
	                                    </h1>
	                                </dl>
	                                <div style="clear:both">
	                                </div>
	                            </li>
                            </c:if>
                            <li class="M_t_01">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>营销短信内容:</span>
                                <font style="margin-left:13px;">
                                   <label id="surplus">你还可以输入<b>47</b>个字</label>&nbsp;&nbsp;
                                   <a href="javascript:void(0);" onclick="com.template('template','47','saleploy')">单击此处选择短信模板</a>
                                </font>
                                <br/>
                                <textarea style="margin-left:123px" class="validate['required','length[1,47]']" 
                                id="template" onKeyUp="com.keypress(template,47)" name="content" cols="" rows="">${fn:split(tempSalePloy.template, " 有效期：")[0]}</textarea>
                            </li>
                            <li class="bottom" style="text-align:center">
                                <input class="validate['submit'] shang_x" style="width:64px; height:24px" type="image" src="/manager/image/btn/step_next.png" />
                            </li>
                        </ul>
                      </form>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
