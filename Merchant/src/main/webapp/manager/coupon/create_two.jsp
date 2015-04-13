<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 发布优惠券活动</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <c:import url="/manager/include/formcheck.jsp"/>
        <link href="/manager/css/bomb.css" rel="stylesheet" type="text/css" />
       
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
        <script type="text/javascript" src="/manager/js/coupon.js"></script>
        <script type="text/javascript" src="/manager/js/preview.js"></script>
        <script>
          window.addEvent("domready",function(){
        	new com.keypress("ployContent",36);
            new com.coupon.CreateTwoEffect({
              <c:if test="${tempCouponPloy != null}">
                   "couponValue":'${tempCouponPloy.couponValue}',
              </c:if>
              <c:if test="${tempCouponPloy.applyMerchantIds != null}">
                   "applyMerchantIds":'${tempCouponPloy.applyMerchantIds}',
              </c:if>
              <c:if test="${tempCouponPloy.maxSendCount != null}">
                   "maxSendCountValue":'${tempCouponPloy.maxSendCount}',
              </c:if>
              "ployType":${ployType}
            });
            new Calendar(
              {validStartDate:'Y-m-d'}, 
              {direction:1,tweak:{x: 6, y: 0}}
            );
            new Calendar(
              {validEndDate:'Y-m-d'}, 
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
                    <a href="javascript:void(0);"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a>
                </div>
                <div class="right_all" style=" min-height:440px">
                    <!--快速导航-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />发布优惠券活动流程
                            </div>
                        </div>
                    </div>
                    <!--发布营销活动-->
                    <div class="M_Pub_step">
                        <table>
                            <tr>
                                <td>
                                   <img src="/manager/image/btn/T_step01.png" width="171" height="50" />
                                </td>
                                <td>
                                   <img src="/manager/image/btn/step2.png" width="171" height="50" />
                                </td>
                                <td>
                                  <img src="/manager/image/btn/step3.png" width="171" height="50" />
                                </td>
                                <td>
                                  <img src="/manager/image/btn/step4.png" width="171" height="50" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="M_step_content">
                      <form id="form" method="post" action="./create_three.htm">
                        <input type="hidden" name="ployType" value="${ployType}"/>
                        <input type="hidden" name="tempId" value="${tempCouponPloy.id}"/>
                        <input type="hidden" id="applyMerchantIds" name="applyMerchantIds" value="${tempCouponPloy.applyMerchantIds}"/>
                        <ul>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>优惠券标题:</span>
                                <input class="validate['required','%validateChartLength{2,14}']" name="title" id="title" value="${tempCouponPloy.title}" type="text" />
                                <font>
                                    （POS机商户当前可用券列表展示<a href="javascript:void(0);" onclick="com.coupon.showTitle()">点击这里</a>
                                    预览，限7个字。  如：10元代金券）
                                </font>
                            </li>
                            <li class="M_t_01">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>优惠券有效期:</span>
                                <input id="validStartDate" readonly name="validStartDate" value="<fmt:formatDate value="${tempCouponPloy.validStartDate}" pattern="yyyy-MM-dd"/>" class="validate['required']" type="text"/> 到 
                                <input id="validEndDate" readonly name="validEndDate" value="<fmt:formatDate value="${tempCouponPloy.validEndDate}" pattern="yyyy-MM-dd"/>" class="validate['%validatorValidEndDate']" type="text" />
                                <font>
                                    （使用优惠券的生效时间和失效时间）
                                </font>
                            </li>
 
                            <c:if test="${merchant.existValidChildren}">
	                            <li id="allMerchantDiv">
	                                <img src="/manager/image/icon/setp.gif" style="float:left" width="25" height="25" />
	                                <span style="float:left">使用券的门店:</span>
	                                &nbsp;<i class="choose_door">(选定哪些门店的POS机可以验证优惠券)</i><br/>
	                                &nbsp;<i><input id="allMerchantBox" style="border:none; width:auto; height:auto;" type="checkbox"/> 选择所有店面 </i><br />
	                                <dl style="margin-left:138px; _margin-left:69px;">
	                                    <dd class="M_top">
	                                        <img src="/manager/image/bg/wx.png" width="104" height="30" />
	                                    </dd>
	                                    <h1 class="left_merchant">
	                                        <a href="javascript:void(0);" id="appMerchant_${merchant.id}" title="${merchant.name}">
	                                        <j:replace value="${merchant.name}" length="22" byByteLength="true" suffix=".."/> 
	                                        </a>
		                                    <c:forEach items="${merchant.validChildrens}" var="child" varStatus="st">
		                                      <a href="javascript:void(0);" id="appMerchant_${child.id}" title="${child.name}" <c:if test="${st.index%2==0}">class="M_second"</c:if>>
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
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>优惠券类型:</span>
                                <j:getStatic var="couponTypes" value="com.vlives.boss.coupon.domain.CouponType@values()"/>
                                <select id="couponType" name="couponType">
                                  <c:forEach items="${couponTypes}" var="type">
                                    <option value="${type.value}" <c:if test="${tempCouponPloy!=null&&tempCouponPloy.couponType.value==type.value}">selected</c:if>>${type.desc} </option>
                                  </c:forEach>
                                </select>
                                <i id="couponValueDiv"></i>
                                <c:if test="${ployType==2||ployType==3}">
                                  &nbsp;&nbsp;赠送<input name="sendCount" value="${tempCouponPloy.sendCount!=null?tempCouponPloy.sendCount:1}" class="validate['required','digit[1,5]']" type="text" /> 张
                                </c:if>
                            </li>
                            <c:if test="${ployType==2}">
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>券发放规则:</span>
                                消费满<input name="minConsumMoney"  value="${tempCouponPloy.minConsumMoney}" class="validate['required','digit[1,9999]']" type="text" />元
                                <font>
                                    （会员消费满足此金额赠送优惠券）
                                </font>
                            </li>
                            </c:if>
                            <c:if test="${ployType==2||ployType==3}">
	                            <li <c:if test="${ployType==2}"> class="M_t_01"</c:if>>
	                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>券发送数量:</span>
	                                <select id="maxSendCountSelect">
	                                    <option value="0">不限量 </option>
	                                    <option value="1">限量</option>
	                                </select>
	                                <input id="maxSendCount" name="maxSendCount" type="text" /><span id="maxSendCountText">张</span>
	                            </li>
                            </c:if>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>优惠详情:</span>
                                &nbsp;&nbsp;<i class="choose_door">以下内容自行编写，也可以参考系统提供的模板，在模板的基础上进行修改。</i>
                                <br/>
                                <font style="margin-left:113px;">
                                   <label id="surplus">你还可以输入<b>36</b>个字</label>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="com.template('ployContent','36','coupon')">单击此处选择优惠券模板</a>
                                </font>
                                <br/>
                                <textarea class="validate['required','%validatorIllegalWord','length[1,36]']" name="content" 
                                id="ployContent" style="margin-left:113px;" cols="" rows="" 
                                onKeyUp="com.keypress(ployContent,36)">${fn:split(tempCouponPloy.content, " 有效期：")[0]}</textarea>
                            </li>
                            <li class="M_t_01 bottom" style="text-align:center">
                                <a href="./create_one.htm"><img src="/manager/image/btn/step_up.png" /></a>
                                <input class="validate['submit']" type="image" src="/manager/image/btn/step_next.png" />
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
