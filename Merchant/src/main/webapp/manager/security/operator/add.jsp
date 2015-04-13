<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 新增管理员</title>
         <c:import url="/manager/include/head_include.jsp"/>
         <script type="text/javascript" src="/manager/js/operator.js"></script>
         <c:import url="/manager/include/formcheck.jsp"/>
        <script>
          window.addEvent("domready",function(){
             new com.operator.changeRoleItem();
              new FormCheck('form', {
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
            <div class="turn_btn" >
			    <a href="javascript:void(0)"  >
			      <img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" />
			    </a>
			</div>
            <div class="right_all" style=" min-height:440px">
                <!--内容title-->
                <div class="c_title_left">
                </div>
                <div class="c_title_right">
                    <div class="c_all">
                        <div class="Q_nav">
                        </div>
                    </div>
                </div>
                <!--数据表格-->
                <div class="S_table" style="margin-top:-10px; padding-bottom:10px">
                    <div class="S_Credit" style="width:auto; margin:0 auto;">
                        <div class="Praise_title">
                            <div class="P_head_t" style="width:100%">
                                新增管理员
                            </div>
                        </div>
                        <div class="P_Praise">
                          <form id="form" action="./addcommit.htm" method="post">
                            <input type="hidden" name="roleStr" id="roleStr"/>
                            <input type="hidden" name="merchantId" value="${operator.merchant.id}"/>
                            <ul>
                                <li>
                                    <table width="0" border="0">
                                        <tr>
                                            <td width="15%">
                                                <strong>手机号：</strong>
                                            </td>
                                            <td width="85%">
                                                <input class="validate['required','mobile','%validatorOpMobileExist']" name="mobile" type="text" />
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <table width="0" border="0">
                                        <tr>
                                            <td width="15%">
                                                <strong>姓名：</strong>
                                            </td>
                                            <td width="85%">
                                                <input class="validate['required','length[2,10]']" name="name" type="text" />
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <table width="0" border="0">
                                        <tr>
                                            <td width="15%">
                                                <strong>登录密码：</strong>
                                            </td>
                                            <td width="85%">
                                                <input class="validate['required','length[6,15]']" name="password" type="password" />
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <table width="0" border="0">
                                        <tr>
                                            <td width="15%">
                                                <strong>重复登录密码：</strong>
                                            </td>
                                            <td width="85%">
                                                <input class="validate['confirm:password']" name="confirmPassword" type="password" />
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li style="height:auto">
                                    <table width="0" border="0">
                                        <tr>
                                            <td width="15%">
                                                <strong>管理员权限：</strong>
                                            </td>
                                            <td width="85%" class="other">
                                                <input id = "allCheck" type="checkbox" class="validate['%validatorOpRole']"/>全选
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="15%">
                                            </td>
                                            <td width="85%" class="other">
                                                <c:forEach items="${roles}" var="groupMap" varStatus="st">
                                                  <dl class="role">
                                                    <dt>
                                                        <input class="group" id="group${groupMap.key.value}" name="" type="checkbox" value="${groupMap.key.value}" />
                                                        <c:out value="${groupMap.key.desc}"/>
                                                    </dt>
                                                    <dd>
                                                        <c:forEach items="${groupMap.value}" var="role">
                                                        <input class="item" name="" type="checkbox" value="${role.id}" />${role.name}
                                                        <br/>
                                                        </c:forEach>
                                                    </dd>
                                                  </dl>
												</c:forEach>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <table width="0" border="0">
                                        <tr>
                                        
                                        <td colspan="2" class="S_t_turnpage" style="text-align:center;">
		                                    <input class="validate['submit']"  type="image" src="/manager/image/btn/S_btn01.jpg" />
		                                </td>
                                             
                                        </tr>
                                    </table>
                                </li>
                            </ul>
                           </form>
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
