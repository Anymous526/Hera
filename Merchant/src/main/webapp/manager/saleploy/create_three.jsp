<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 选择营销活动会员</title>
        <c:import url="/manager/include/head_include.jsp"/>
        <c:import url="/manager/include/formcheck.jsp"/>
        <script src="/manager/js/saleploy.js"></script>
        <script>
          window.addEvent("domready",function(){
             com.saleploy.loadMemberLevelCheck();
             new com.saleploy.delSalePloyMember();
             com.saleploy.createPloy();
             new FormCheck('memberSearch', {
				display : {
					showErrors:0,
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
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step2.png" width="171" height="50" /></a></td>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step03.png" width="171" height="50" /></a></td>
						      <td><a href="javascript:void(0)"><img src="/manager/image/btn/step4.png" width="171" height="50" /></a></td>
                            </tr>
                        </table>
                    </div><!--搜索板块-->
                    <div class="M_step_content">
                        <ul>
                            <li>
                              <form id="memberSearch" action="./create_three_view.htm" method="get">
                                <input type="hidden" id="canSubmit" value="${canSubmit}"/>
                                <input type="hidden" name="tempId" value="${param.tempId}"/>
                                <input id="excludeMemberId" type="hidden" name="excludeMemberId" value="${param.excludeMemberId}"/>
                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>会员选择：</span>
                                <input id="memberLevel1" name="memberLevel" type="checkbox" class="xianz" value="1" />普通会员 
                                <input id="memberLevel2" name="memberLevel" type="checkbox" class="xianz" value="2" /> 银卡会员 
                                <input id="memberLevel3" name="memberLevel" type="checkbox" class="xianz" value="3" /> 金卡会员 
                                <input id="memberLevel4" name="memberLevel" type="checkbox" class="xianz" value="4" /> 钻石会员 
                                <br/>
                                <ol>
                                    <span>最小积分:</span>
                                    <input class="validate['digit[0,99999999]']" id="minPoint" name="minPoint" type="text" value="${param.minPoint}"/>
                                    <span>最大积分:</span>
                                    <input class="validate['digit[0,99999999]','%validatorPoint']" id="maxPoint"  name="maxPoint" type="text" value="${param.maxPoint}"/>
                                    <span>会员活跃度:</span>
                                   <j:getStatic var="activeRates" value="com.vlives.boss.member.domain.ActiveRate@values()"/>
                                    <select name="activeRate">
                                    	 <option value="0"></option>
                                        <c:forEach items="${activeRates}"var="activeRate" >
                                        	 <option value="${activeRate.value}" <c:if test="${param.activeRate==activeRate.value}">selected="selected"</c:if>>${activeRate.desc}</option>
                                        </c:forEach>
                                    </select>
                                    <input class="validate['submit']" style="width:auto; height:auto; border:none;" type="image" src="/manager/image/btn/S_btn.jpg" />
                                </ol><h2>
                                  ×备注：当前可用短信剩余条数为：<b>${operator.merchant.merchantSmsAccount.remainCount}</b>条&nbsp;&nbsp;&nbsp;
                                  ×将发送短信为：<b>${pagination.count}</b>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                  <c:if test="${pagination.count>operator.merchant.merchantSmsAccount.remainCount}">
                                   <br/>×已超出了短信库存,你可删除部分会员或<a href="/manager/saleploy/buy_sms.jsp"><b style="font-weight:normal">点击购买短信</b></a>
                                   <br/>${errorMsg}
                                  </c:if>
                                </h2>
                                </form>
                            </li>
                            <li class="M_t_01">
                                <table width="0" border="0" cellpadding="0" cellspacing="0">
                                    <tr class="title">
                                        <td style="text-align:center" width="1%">
                                            <input id="allChkBtn" type="checkbox" value="" />
                                        </td>
                                        <td>
                                            姓名
                                        </td>
                                        <td>
                                            手机号
                                        </td>
                                        <td>
                                            会员等级
                                        </td>
                                        <td>
                                            会员活跃度
                                        </td>
                                        <td>
                                            总积分
                                        </td>
                                    </tr>
                                    <c:forEach items="${members}" var="member" varStatus="st">
                                    <c:if test="${st.index%2==0}">
                                    <tr class="S_t_01" style="background:#fff;">
                                    </c:if>
                                    <c:if test="${st.index%2==1}">
                                    <tr class="zou">
                                    </c:if>
							         <td class="zuo"><input  class="allItemBtn" type="checkbox" value="${member.id}" /></td>
							         <td>${member.user.name}</td>
							         <td>${member.user.mobile}</td> 
							         <td>${member.level.desc}</td>
							         <td>
							           <c:if test="${member.activeRate!=null}">
							             ${member.activeRate.desc}
							           </c:if>
							           <c:if test="${member.activeRate==null}">
							             活跃用户
							           </c:if>
							           
							         </td> 
							         <td>${member.totalPoint}</td>    
							        </tr>
                                    </c:forEach>
                                    <tr>
                                     
                                    	<td><a id="delBtn" href="##"><img  style="margin:0; cursor:pointer;padding: 0;" src="/manager/image/btn/S_05.jpg" /></a></td>
                                        <td  colspan="5"  align="right"> 
		                                 <c:out value="${pagination}" escapeXml="false"></c:out>
		                               </td>
                                    </tr>
                                </table>
                            </li>
                            <li class="bottom" style="text-align:center">
                               
                                <a href="./create_two.htm?tempId=${param.tempId}"><img src="/manager/image/btn/step_up.png" /></a>
                                <input id="createPloyBtn" type="image" class="shang_x" src="/manager/image/btn/step_next.png" /><strong style="color:red">${errorMsg}</strong>
                              
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/manager/include/footer.jsp"/>
    </body>
</html>
