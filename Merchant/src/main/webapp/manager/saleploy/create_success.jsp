<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 营销活动发布成功</title>
        <c:import url="/manager/include/head_include.jsp"/>
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
                    <a href="#"><img src="../image/turnleft_btn.jpg" width="4" height="37" /></a>
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
                                    <a href="javascript:void(0)"><img src="/manager/image/btn/step1.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a href="javascript:void(0)"><img src="/manager/image/btn/step2.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a  href="javascript:void(0)"><img src="/manager/image/btn/step3.png" width="171" height="50" /></a>
                                </td>
                                <td>
                                    <a href="javascript:void(0)"><img src="/manager/image/btn/step04.png" width="171" height="50" /></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="M_step_content">
                        <ul>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span class="bottom"> 恭喜您！您的营销活动发布成功。</span>
                                <h2>
                                ×您的营销活动已经成功发送，我们会尽快给您审核通过，谢谢使用！ 
                                <br/>
                                ×如有问题或者疑问，欢迎拨打客户服务热线：<b>:400-1588-996</b>。 欲知更多活动发布信息，详情请登录vlives.net查询。
                            </h2>
                            </li>
                            <li class="M_t_01 bottom" style="text-align:center">
                                <a href="./create_one.htm"><img src="/manager/image/btn/goon.png"/></a>
                                <a href="/manager/saleploy/list.htm"><img src="/manager/image/btn/list.png"/></a>
		                         
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
