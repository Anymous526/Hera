<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 发布优惠券活动</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <script type="text/javascript" src="/manager/js/coupon.js"></script>
    </head>
    <body>
        <!--top板块-->
        <c:import url="/manager/include/top.jsp"/>
        <!-- -->
        <!--中间板块-->
        <div id="content">
            <!--left导航-->
            <c:import url="/manager/include/left.htm"/> 
            <!-- --><!--right内容-->
              <div id="right">
                <div class="turn_btn">
                    <a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4" height="37" /></a>
                </div>
                <div class="right_all" style=" min-height:440px">
                    <!--快速导航-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />发布营销活动流程
                            </div>
                        </div>
                    </div><!--发布营销活动-->
                    <div class="M_Pub_step">
                        <table>
                            <tr>
                                <td>
                                    <a href="T_Camping.html"><img src="/manager/image/btn/T_step1.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a href="T_Camping02.html"><img src="/manager/image/btn/step2.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a href="T_Camping03.html"><img src="/manager/image/btn/step3.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a href="T_Camping04.html"><img src="/manager/image/btn/step04.png" width="171" height="50" /></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="M_step_content">
                        <ul>
                            <li>
                                <div class="status over">
                                    恭喜您！您的优惠券已经发布成功！
                                </div>
                                <!-- Status Bar Start -->
                                <div class="status warning" style="top:0;">
                                    <p class="closestatus">
                                        &nbsp;
                                    </p>
                                    <p>
                                        
                                        @本次优惠券使用有效期为<fmt:formatDate value="${couponPloy.validStartDate}" pattern="MM月dd日"/>至<fmt:formatDate value="${couponPloy.validEndDate}" pattern="MM月dd日"/>，
                                        <c:if test="${couponPloy.merchant.existChildren}">
                                          适用的门店为 
                                          <c:forEach items="${couponPloy.couponPloyApplyMerchants}" var="appMerchant">
                                              ${appMerchant.merchant.name}、
                                          </c:forEach>
                                          
                                        </c:if>
                                        <br/>
                                        
                                        @您目前剩余的短信数量为${couponPloy.merchant.merchantSmsAccount.remainCount}条。
                                        <span style="float:right; margin-top:-20px; _margin-top:-40px; *margin-top:-45px; ">确认重要信息</span>
                                    </p>
                                </div>
                                <!-- Status Bar End --><!-- Red Status Bar Start --><!-- Red Status Bar End --><!-- Green Status Bar Start -->
                                <div class="status error">
                                    <p>
                                        @一般审核工作需要两个工作日完成。
                                        <br/>
                                        @在审核未通过的情况下您可以修改您的优惠券内容，审核过程一般将在重新提交后两个工作日内完成。
                                        <br/>
                                        @优惠券活动在审核成功后，将不在进行修改操作。
                                        <br/>
                                        @如有任何意见或者建议请拨打4009000900<span style="float:right; margin-top:-40px; _margin-top:-60px; *margin-top:-65px; ">审核须知</span>
                                    </p>
                                </div>
                                <!-- Green Status Bar End --><!-- Blue Status Bar Start -->
                                <div class="status info">
                                    <p>
                                        @一般审核工作需要两个工作日完成。
                                        <br/>
                                        @在审核未通过之前您可以修改您的优惠券内容，审核过程一般将在重新提交后两个工作日内完成。
                                        <br/>
                                        @如有任何意见或者建议请拨打4009000900<span style="float:right; margin-top:-30px; _margin-top:-50px; *margin-top:-55px; ">免责声明</span>
                                    </p>
                                </div>
                            </li>
                            <li class="M_t_01 bottom" style="text-align:center">
                               
                                <a href="./create_one.htm"><img src="/manager/image/btn/goon.png" /></a>
                                 <a href="/manager/coupon/ploy/${couponPloy.id}/detail.htm"><img src="/manager/image/btn/xqing.gif" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>        
        
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
