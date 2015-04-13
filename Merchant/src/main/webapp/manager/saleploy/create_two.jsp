<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 发送预览</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <c:import url="/manager/include/formcheck.jsp"/>
        <script type="text/javascript" src="/manager/js/saleploy.js"></script>
        <script>
          window.addEvent("domready",function(){
        	new com.keypress("template",65);
        	$("nextBtn").erase("disabled");
            new FormCheck('form', {
				display : {
					showErrors:0,
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
        <<c:import url="/manager/include/top.jsp"/>
        <!-- --><!--中间板块-->
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
	                            <td><a href="javascript:void(0)"><img src="/manager/image/btn/step1.png" width="171" height="50" /></a></td>
						        <td><a href="javascript:void(0)"><img src="/manager/image/btn/step02.png" width="171" height="50" /></a></td>
						        <td><a href="javascript:void(0)"><img src="/manager/image/btn/step3.png" width="171" height="50" /></a></td>
						        <td><a href="javascript:void(0)"><img src="/manager/image/btn/step4.png" width="171" height="50" /></a></td>
	                        </tr>
	                    </table>
	                </div>
	                <div class="M_step_content">
	                    <form id="form" action="./create_three.htm" method="post" >
	                      <input type="hidden" name="tempId" value="${tempSalePloy.id}"/>
		                    <ul>
		                        <li>
		                            <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>发送预览:</span>
		                        <font style="margin-left:13px;">
                                   <label id="surplus">你还可以输入<b>65</b>个字</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   (券的有效期请与上一步保持一致)
                                </font>
                                <br/>
		                            <textarea style="margin-left:123px" class="validate['required','%validatorIllegalWord','length[1,65]']" 
		                            id="template" onKeyUp="com.keypress(template,65)" name="template" class="ner" cols="" rows="">${tempSalePloy.template}</textarea>
		                        </li>
		                        <li class="M_t_01 bottom" style="text-align:center">
		                            <a href="./create_one.htm?tempId=${tempSalePloy.id}""><img src="/manager/image/btn/step_up.png" /></a>
		                            <input id="nextBtn" class="validate['submit'] shang_x" style="width:64px; height:24px" type="image" src="/manager/image/btn/step_next.png" />
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
