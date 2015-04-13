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
                <div class="right_all" style=" min-height:540px">
                    <!--内容title-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />选择发布优惠券的类型
                            </div>
                        </div>
                    </div>
                    <form method="get" id="createOneForm" action="./create_two.htm">
                      <input id="ployType" type="hidden" name="ployType"/>
                    </form>
                    
                    <!--数据表格-->
                    <div class="S_table">
                        <ul class="Ticket_nav">
                            <li class="T_1">
                                <a href="##" onclick="com.coupon.selectPloyType(1)">
                                    <p>
                                        通过会员信息、会员消费情况等精准筛选出优惠活动对象，将优惠券以短信形式下发到目标会员的手机上。
                                    </p>
                                    <p style="padding-top:28px">
                                        提升店铺销售业绩，提高店铺购买转化率，增加到店消费的人群。
                                    </p>
                                </a>
                            </li>
                            <li class="T_2">
                                <a href="##" onclick="com.coupon.selectPloyType(2)">
                                    <p>
                                        会员一次性消费满足商户规定的金额就送优惠券、折扣券、具体商品或具体服务。
                                    </p>
                                    <p style="padding-top:28px">
                                        提升店铺销售业绩，提高店铺购买转化率，提升销售笔数，增加到店消费的人群。
                                    </p>
                                </a>
                            </li>
                            <li class="T_3">
                                <a href="##" onclick="com.coupon.selectPloyType(3)">
                                    <p>
                                        商家可制定各种优惠活动，吸引更多消费者成为会员。消费者只要注册成为会员就可以得到一定的优惠。
                                    </p>
                                    <p style="padding-top:28px">
                                        注册就有好礼相送，刺激新的用户成为商家的会员。
                                    </p>
                                </a>
                            </li>
                            <li class="T_4">
                                <a href="##" onclick="com.coupon.selectPloyType(4)">
                                    <p>
                                        节假日、会员生日、店庆推广。
                                    </p>
                                    <p style="padding-top:28px">
                                        帮助商家在节假日、会员过生日、店庆推广中有针对性的制定营销活动，提高消费额。
                                    </p>
                                </a>
                            </li>
                            <div style="clear:both">
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
