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
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />修改优惠券成功
                            </div>
                        </div>
                    </div><!--修改成功-->
                    <div class="M_step_content">
                        <ul>
                            <li>
                                <div class="status over">
                                    恭喜！您的优惠券已经修改成功，将重新审核，敬请关注！！
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
                                        @ 一般审核工作需要两个工作日完成。
                                        <br/>
                                        @ 在审核未通过之前您可以修改您的优惠券内容，审核过程一般将在重新提交后两个工作日内完成。
                                        <br/>
                                        @ 优惠券活动在审核成功后，将不在进行修改操作。
                                        <br/>
                                        @ 如有任何意见或者建议请拨打4009000900<span style="float:right; margin-top:-40px; _margin-top:-60px; *margin-top:-65px; ">审核须知</span>
                                    </p>
                                </div>
                                <!-- Green Status Bar End --><!-- Blue Status Bar Start -->
                                <div class="status info">
                                    <p>
                                        @ 对于审核通过后的优惠活动，由于商户围巾描述之处等造成的损失、纠纷商户需要承担责任。
                                        <br/>
                                        @ 对于由于系统原因造成的商户损失，会生活会根据使劲情况，与商户协商解决。
                                        <br/>
                                        @ 会生活保留最终解释权。<span style="float:right; margin-top:-30px; _margin-top:-50px; *margin-top:-55px; ">免责申明</span>
                                    </p>
                                </div>
                            </li>
                            <li class="M_t_01 bottom" style="text-align:center">
                               
                                <!-- <a href="./create_one.htm"><img src="/manager/image/btn/goon.png" /></a> -->
                                 <a href="/manager/coupon/ploy/list.htm"><img src="/manager/image/btn/list.png" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>        
        
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
