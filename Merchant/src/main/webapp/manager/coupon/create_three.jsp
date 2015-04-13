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
        <c:import url="/manager/include/formcheck.jsp"/>
        <link href="/manager/css/bomb.css" rel="stylesheet" type="text/css" />
        <script src="/manager/js/ie6png.js" type="text/javascript"></script>
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
        <script type="text/javascript" src="/manager/js/coupon.js"></script>
        <script type="text/javascript" src="/manager/js/preview.js"></script>
        <script>
          window.addEvent("domready",function(){
            new com.coupon.CreateThreeEffect();
            new com.keypress("ployContent",54);
            new Calendar(
              {sendStartDate:'Y-m-d'}, 
              {direction:1,tweak:{x: 6, y: 0}}
            );
            new Calendar(
              {sendEndDate:'Y-m-d'}, 
              {direction:1,tweak:{x: 6, y: 0}}
            );
            $("nextBtn").erase("disabled");
            new FormCheck('form', {
                submitReload:1,
				display : {
					showErrors:1,
					indicateErrors : 1,
					fadeDuration : 1000
				},
				onValidateSuccess:function() {
				   $("nextBtn").set("disabled","disabled");
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
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />发布优惠券活动流程
                            </div>
                        </div>
                    </div><!--发布营销活动-->
                    <div class="M_Pub_step">
                        <table>
                            <tr>
                                <td>
                                   <img src="/manager/image/btn/T_step1.png" width="171" height="50" />
                                </td>
                                <td>
                                   <img src="/manager/image/btn/step02.png" width="171" height="50" />
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
                      <form id="form" method="post" action="./create_four.htm">
                        <input type="hidden" name="tempId" id="tempId" value="${tempCouponPloy.id}"/>
                        <input type="hidden" name="title" id="title" value="${tempCouponPloy.title}"/>
                        <input type="hidden" name="merchantName" id="merchantName" value="${tempCouponPloy.merchant.name}"/>
                        <input type="hidden" name="merchantHead" id="merchantHead" value="${tempCouponPloy.merchant.head}"/>
                        <input type="hidden" name="introduction" id="introduction" value="${tempCouponPloy.introduction}"/>
                        <ul>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" />
                                <span>活动发送日期:</span>
                                  <span id="validEndDate" style="display:none"><fmt:formatDate value="${tempCouponPloy.validEndDate}" pattern="yyyy-MM-dd"/></span>
                                 <input id="sendStartDate" readonly name="sendStartDate" value="<fmt:formatDate value="${tempCouponPloy.sendStartDate}" pattern="yyyy-MM-dd"/>" class="validate['required']" type="text"/> 到 
                                <input id="sendEndDate" readonly name="sendEndDate"  value="<fmt:formatDate value="${tempCouponPloy.sendEndDate}" pattern="yyyy-MM-dd"/>"  class="validate['%validatorSendEndDate']" type="text" />
                                <font style="margin-left:10px">
                                    （从这个时间起通过审核的短信开始发送）
                                </font>
                            </li>
                            <li class="M_t_01">
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>发布地点:</span>
                                <i><input id="displayInWeb" name="displayInWeb" style="border:none; width:auto; height:auto;" type="checkbox" value="1" />会生活网站 </i>
                                <font>
                                    （商户发布的优惠券将在会生活网站上展示，用户可下载）
                                </font>
                                <br/>
                                <div id="displayInWebDiv" class="web_add" style="display:none">
                                    <i style="padding-left:5px;">是否允许用户在网站下载优惠券：
                                        <font>
                                            （如果勾选此功能，将按用户的下载数量，扣除商户的短信条数）
                                        </font>
                                    </i>
                                    <br/>
                                    <i><input id="unAgreeDownload" checked name="unAgreeDownload" style="border:none; width:auto; height:auto;" type="radio" value="0" />不允许下载</i>
                                    <br/>
                                    <i><input id="agreeDownload" name="agreeDownload" style="border:none; width:auto; height:auto;" type="radio" value="1" />
                                    允许下载限制张数为 <input id="maxReceiveCountByEveryUser" style="display:none" name="maxReceiveCountByEveryUser" type="text" /></i>
                                    <font style="margin-left:10px">
                                        （每个手机号码最多可以领用的张数）
                                    </font>
                                 </div>
                            </li>
                            <li>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>发送预览:</span>
                                &nbsp;&nbsp;<i class="choose_door">
                                    <font>
                                    <label id="surplus">你还可以输入<b>54</b>个字</label>&nbsp;&nbsp;
                                       <em>（券的有效期，请与上一步选择的保持一致）</em>
                                    </font>
                                </i>
                                <br/>
                                
                                <textarea name="content" id="ployContent" class="validate['required','%validatorIllegalWord','length[1,54]']" style="margin-left:113px;" cols="" rows="" 
                                onKeyUp="com.keypress(ployContent,54)">${tempCouponPloy.content}</textarea>
                                <br/>
                                <i class="choose_door" style="margin-left:113px;">
                                    <font>
                                        <a href="javascript:void(0);" onclick="com.coupon.showMobile()">短信预览 (会员手机接收到的手机预览)</a>
                                    </font>
                                </i>
                                <br/>
                                <i class="choose_door" style="margin-left:113px;">
                                    <font>
                                        <a href="javascript:void(0);" onclick="com.coupon.showWeb()">网站预览 (在网站上显示的优惠信息预览)</a>
                                    </font>
                                </i>
                                <br/>
                                <i class="choose_door" style="margin-left:113px;">
                                    <font>
                                        <a href="javascript:void(0);" onclick="com.coupon.showPos()">POS机预览 (POS端"商户当前可用券"的预览)</a>
                                    </font>
                                </i>
                                <br/>
                            </li>
                            <li class="M_t_01 bottom" style="text-align:center">
                                <a href="./create_two.htm?tempId=${tempCouponPloy.id}"><img src="/manager/image/btn/step_up.png" /></a>
                                <input id="nextBtn" class="validate['submit']" type="image" src="/manager/image/btn/step_next.png" />
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
