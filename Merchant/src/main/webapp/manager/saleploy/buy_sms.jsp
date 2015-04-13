<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 短信购买</title>
        <c:import url="/manager/include/head_include.jsp"/>
    </head>
    <body>
        <!--top板块-->
        <c:import url="/manager/include/top.jsp"/><!-- -->
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
                <div class="right_all">
                    <!--内容title-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />短信购买
                            </div>
                        </div>
                    </div><!--搜索板块--><!--数据表格-->
                    <div class="S_table" style="margin-top:0;">
                        <div class="M_buy">
                            
                            <img src="/manager/image/adver.jpg" />
                            <div class="buy_txt">
                                <ul>
                                    <li>
                                        想让会员第一时间知道您的促销活动吗？
                                    </li>
                                    <li>
                                        想拉进您同会员之间的距离吗？
                                    </li>
                                    <li>
                                       会生活短信营销祝您一臂之力。
                                    </li>
                                    <br/>
                                    <li style="list-style:none; margin-left:5px; font-weight:normal">
                                        如果您想了解更多详细内容或者有任何疑问、建议或者意见.欢迎拨打客服热线：400-1588-996，会生活竭诚为您服务。
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div style="clear:both">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>