<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 修改密码</title>
         <c:import url="/manager/include/head_include.jsp"/>
         <c:import url="/manager/include/formcheck.jsp"/>
         <script>
           window.addEvent("domready",function(){
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
                <div class="right_all" style=" min-height:440px">
                    <!--内容title-->
                    <div class="c_title_left">
                    </div>
                    <div class="c_title_right">
                        <div class="c_all">
                            <div class="Q_nav">
                                <img src="/manager/image/icon/07.gif" width="47" height="30" />密码修改
                            </div>
                        </div>
                    </div><!--搜索板块--><!--数据表格-->
                    <div class="S_table" style="margin-top:0;">
                        <form id="form" action="./" method="post">
                        <table width="0" border="0" cellpadding="0" cellspacing="0">
                            <input type="hidden" name="_method" value="put"/>
                            <tr class="title">
                                <td colspan="2" style="text-align:center">
                                    <strong>管理员帐号</strong>
                                </td>
                            </tr>
                            <tr>
                                <td width="23%" class="zuo">
                                    管理员帐号:
                                </td>
                                <td width="77%" class="sr">
                                    ${operator.mobile}
                                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <strong style="color:red">${errorMsg}</strong>
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="zuo">
                                    姓名：
                                </td>
                                <td class="sr">
                                    <input class="validate['required','length[2,20]']" name="name" type="text" value="${operator.name}"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="zuo">
                                    原密码：
                                </td>
                                <td class="sr">
                                    <input class="validate['required','length[6,15]']" name="oldPassword" type="password" />
                                </td>
                            </tr>
                            <tr>
                                <td class="zuo">
                                    新密码：
                                </td>
                                <td class="sr">
                                    <input class="validate['required','length[6,15]']" name="newPassword" type="password" />
                                </td>
                            </tr>
                            <tr>
                                <td class="zuo">
                                    重复新密码：
                                </td>
                                <td class="sr">
                                    <input class="validate['confirm:newPassword']" name="confirmNewPassword" type="password" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="S_t_turnpage" style="text-align:center;">
                                    <input class="validate['submit']"  type="image" src="/manager/image/btn/S_btn01.jpg" />
                                    
                                </td>
                            </tr>
                        </table>
                        </form>
                        <div style="clear:both">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
