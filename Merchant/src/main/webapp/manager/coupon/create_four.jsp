<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎进入会生活商户管理平台 - 发布优惠券活动</title>
        <link href="/manager/js/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
        <c:import url="/manager/include/head_include.jsp"/>
        <c:import url="/manager/include/formcheck.jsp"/>
        <script src="/manager/js/area/area_mootools.js"></script>
        <script type="text/javascript" src="/manager/js/calendar/js/calendar.js"></script>
        <script type="text/javascript" src="/manager/js/coupon.js"></script>
        <script>
          window.addEvent("domready",function(){ 
              new com.coupon.CreateFourEffect(
                { 
                 <c:if test="${param.province!=null}">
                   "provinceid":${param.province},
                 </c:if>
                 <c:if test="${param.city!=null}">
                   "cityid":${param.city},
                 </c:if>
                <c:if test="${param.district!=null}">
                   "districtid":${param.district},
                 </c:if>
	  			 "end":""
	  			}
	  		  );               
              new Calendar(
                  
	              {startBirthday:'Y-m-d'}, 
	              {direction:0,tweak:{x: 6, y: 0},navigation:2}
	          );
	          new Calendar(
	              {endBirthday:'Y-m-d'}, 
	              {direction:0,tweak:{x: 6, y: 0},navigation:2}
	          );
	          new Calendar(
	              {startCreateDate:'Y-m-d'}, 
	              {direction:0,tweak:{x: 6, y: 0}}
	          );
	          new Calendar(
	              {endCreateDate:'Y-m-d'}, 
	              {direction:0,tweak:{x: 6, y: 0}}
	          );
	          
             new FormCheck('memberSearch', {
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
                                   <img src="/manager/image/btn/step2.png" width="171" height="50" />
                                </td>
                                <td>
                                  <img src="/manager/image/btn/step03.png" width="171" height="50" />
                                </td>
                                <td>
                                  <img src="/manager/image/btn/step4.png" width="171" height="50" />
                                </td>
                            </tr>
                        </table>
                    </div><!--搜索板块-->
                    <div class="M_step_content">
                        <ul>
                             <form id="memberSearch" method="get" action="./select_ploy_user.htm">
		                        <input type="hidden" id="canSubmit" value="${canSubmit}"/>
		                        <input type="hidden" name="tempId" value="${tempCouponPloy.id}"/>
		                        <input id="excludeMemberId" type="hidden" name="excludeMemberId" value=""/>
	                            <li>
	                                <img src="/manager/image/icon/setp.gif" width="25" height="25" /><span>会员选择:</span>
	                                <font>
	                                    （以下条件可任意组合或者不选，如果不选，单击查询按钮后，系统自动查询出所有的会员信息）
	                                </font>
	                                <br/>
	                                <ol>
	                                    <span><strong> <img src="/manager/image/icon/xxi.gif" /></strong> 会员基本信息:</span>&nbsp;&nbsp;*手机号码：<input name="likeMobile" value="${param.likeMobile}" type="text" />
	                                    <font>
	                                        (可输入完整的手机号或者部分号段进行查询)
	                                    </font>
	                                    <br/>
	                                    <i style="margin-left:121px;">
	                                      *会员等级：
	                                      <input id="memberLevel1" name="memberLevel" type="checkbox" class="xianz" value="1" />普通会员 
	                                      <input id="memberLevel2" name="memberLevel" type="checkbox" class="xianz" value="2" /> 银卡会员 
	                                      <input id="memberLevel3" name="memberLevel" type="checkbox" class="xianz" value="3" /> 金卡会员 
	                                      <input id="memberLevel4" name="memberLevel" type="checkbox" class="xianz" value="4" /> 钻石会员 </i>
	                                      &nbsp;&nbsp;<span><a class="more" href="javascript:void(0);"  id="moreSearchBtn">更多条件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
	                                    <br/>
	                                    <span id="moreSearchDiv" style="display:none">
	                                    <i style="margin-left:121px;">性别：
	                                      <select name="gender">
	                                        <option value="0"></option>
	                                        
	                                        <option value="1" <c:if test="${param.gender==1}">selected</c:if>>男</option>
	                                        <option value="2" <c:if test="${param.gender==2}">selected</c:if>>女</option>
	                                      </select>
	                                    </i>
	                                    <br/>
	                                    <i style="margin-left:121px;">生日：<input  id="startBirthday" name="startBirthday" value="${param.startBirthday}" type="text" /> 到 
	                                    <input id="endBirthday" name="endBirthday" value="${param.endBirthday}" type="text" /></i>
	                                    <br/>
	                                    <i style="margin-left:121px;">所属地区：
                                            <select id="province" name="province"></select>
								            <select id="city" name="city"></select>
								            <select id="district" name="district"></select>
                                        </i>
	                                    <br/>
	                                    <i style="margin-left:121px;">加入渠道：
	                                      <j:getStatic var="memberSources" value="com.vlives.boss.member.domain.MemberSource@values()"/>
	                                        <select name="memberSource">
	                                            <option value="0"></option>
	                                            
	                                            <c:forEach items="${memberSources}"var="memberSource" >
	                                              <option value="${memberSource.value}"  <c:if test="${param.memberSource==memberSource.value}">selected</c:if>>${memberSource.desc}</option>
	                                            </c:forEach>
	                                        </select>
	                                    </i>
	                                    <br/>
	                                    <i style="margin-left:121px;">加入时间：
	                                    <input id="startCreateDate" name="startCreateDate" value="${param.startCreateDate}" type="text" /> 到 
	                                    <input id="endCreateDate" name="endCreateDate" value="${param.endCreateDate}" type="text" /></i>
	                                    </span>
	                                </ol>
	                                <ol>
	                                    <span><strong> <img src="/manager/image/icon/xxi.gif"  /></strong> 会员积分情况:</span>&nbsp;&nbsp;*积分范围：
	                                    <input class="validate['digit[0,99999999]']" id="minPoint" name="minPoint" value="${param.minPoint}" type="text" /> 到 
	                                    <input class="validate['digit[0,99999999]']" id="maxPoint" name="maxPoint" value="${param.maxPoint}" type="text" />
	                                </ol>
	                                <ol>
	                                    <span><strong> <img src="/manager/image/icon/xxi.gif"  /></strong> 会员消费情况:</span>&nbsp;&nbsp;*会员活跃度：
	                                    <j:getStatic var="activeRates" value="com.vlives.boss.member.domain.ActiveRate@values()"/>
	                                    <select name="activeRate">
	                                    	 <option value="0"></option>
	                                        <c:forEach items="${activeRates}"var="activeRate" >
	                                        	 <option value="${activeRate.value}" <c:if test="${param.activeRate==activeRate.value}">selected="selected"</c:if>>${activeRate.desc}</option>
	                                        </c:forEach>
	                                    </select>
	                                    <input class="validate['submit']" style="width:auto; height:auto; border:none;" type="image" src="/manager/image/btn/S_btn.jpg" />
	                                </ol> 
	                            </li>
                            </form>
                            <li class="M_t_01">
                                <div class="web_add" style="margin-left:0px; padding-left:10px; margin-bottom:10px;">
                                    您将给以下会员发送短信，共计<b>${pagination.count}</b>条。如果您不想给某些会员发送，您可勾选进行删除操作(你目前删除了<b>${tempCouponPloy.excludeMemberCount}</b>个会员)。
                                    <c:if test="${pagination.count>operator.merchant.merchantSmsAccount.remainCount}">
                                   <br/> 您还剩余短信数为<b>${operator.merchant.merchantSmsAccount.remainCount}</b>条，预计发送数量已经超出短信库存
                                   
                                     您可勾选某些会员进行删除，或者 <b><a href="/manager/saleploy/buy_sms.jsp">点击这里</a></b>申请购买短信。 
                                  </c:if>
                                </div>
                                <table width="0" border="0" cellpadding="0" cellspacing="0">
                                    <tr class="title">
                                        <td style="text-align:center" width="1%">
                                            <input id="allChkBtn" type="checkbox"/>
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
                                        <td colspan="5" class="S_t_turnpage" align="right">
                                          <c:out value="${pagination}" escapeXml="false"></c:out>
                                        </td>
                                    </tr>
                                    </table>
                            </li>
                            <li class="bottom" style="text-align:center">
                               
                                <a href="./create_three_view.htm?tempId=${tempCouponPloy.id}"><img src="/manager/image/btn/step_up.png" /></a>
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
