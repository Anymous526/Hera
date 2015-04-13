<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="j" uri="/just"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
      欢迎进入会生活商户管理平台 -首页
    </title>
    <c:import url="/manager/include/head_include.jsp"/>
  </head>

  <body>
    <!--top板块-->
        <c:import url="../manager/include/top.jsp"/>
    <!-- -->
    <!--中间板块-->
    <div id="content">
      <!--left导航-->
      
       <c:import url="../manager/include/left.htm"/>
       
      <!-- -->
<!--right内容-->
<div id="right">
  <div class="turn_btn" >
    <a href="javascript:void(0)"  >
      <img src="/manager/image/dev/turnleft_btn.jpg" width="4" height="37" />
    </a>
  </div>
  <div class="right_all">
    <!--快速导航-->
    <div class="c_title_left">
    </div>
    <div class="c_title_right">
      <div class="c_all">
        <div class="Q_nav">
          <img src="image/icon/07.gif" width="47" height="30" />
          快速导航
        </div>
      </div>
    </div>
    <div class="Q_nav_all">
      <div class="Q_left">
        <img src="image/btn/Q_left01.jpg" width="12" height="115" />
      </div>
      <div class="Q_right">
        <img src="image/btn/Q_right01.jpg" width="12" height="115" />
      </div>
      <div class="Q_content">
        <ul>
          
          <c:forEach items="${operator.rolesByExistRoleGroup}" var="opRole">
            <c:if test="${opRole.role.shortcut}">
            <li>
              <a href="${opRole.role.entryIndex}" title="${opRole.role.name}">
                <img src="${opRole.role.logo}"  width="80" height="80"/>
              </a>
            </li>
            </c:if>
          </c:forEach>
      </ul>
    </div>
</div>
<!--数据一览-->
<div class="Q_nav01">
  <img src="image/icon/09.jpg" width="31" height="27" />
  数据一览
</div>
<div class="S_body">
  <table width="100%" border="0">
    <tr>
      <td width="54%" rowspan="2" class="S_tdpadd">
        <!--商家信用-->
        <div class="S_Credit">
          <div class="Credit_title">
            商家信用
          </div>
          <div class="Credit_content">
            <img src="image/buss/01.jpg" width="65" height="65" />
            <ul>
              <li>
              商家信用：
              <span>
               ${statistic.creditScore}
              </span>
              <img src="image/icon/08.gif" width="13" height="20" />
            </li>
            <li>
            好评率：
            <span>
             ${statistic.bestPercent}%
            </span>
          </li>
          <li class="txt">
          根据网站统计，您的信用度和好评率较好。
        </li>
      </ul>
    </div>
    <div class="S_Praise">
      <ul>
        <li>
       推荐：<img src="/manager/image/icon/five_star.png" width="86" height="16" /><c:if test="${statistic.fiveGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"    style="height: 13px ;width:${statistic.fiveGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.fiveGradePercent}%)&nbsp;&nbsp; <span>${statistic.fiveGrade}</span>条
      </li>
      <li>
     很好：<img src="/manager/image/icon/four_star.png" width="86" height="16" /><c:if test="${statistic.fourGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.fourGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.fourGradePercent}%)&nbsp;&nbsp; <span>${statistic.fourGrade}</span>条
    </li>
    <li>
        好评：<img src="/manager/image/icon/three_star.png" width="86" height="16" /><c:if test="${statistic.threeGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.threeGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.threeGradePercent}%)&nbsp;&nbsp; <span>${statistic.threeGrade}</span>条
  </li>
  <li> 中评：<img src="/manager/image/icon/two_star.png" width="86" height="16" /><c:if test="${statistic.twoGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.twoGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.twoGradePercent}%)&nbsp;&nbsp; <span>${statistic.twoGrade}</span>条</li>
<li> 差评：<img src="/manager/image/icon/one_star.png" width="86" height="16" /><c:if test="${statistic.oneGradePercent*1.72!=0}">&nbsp;<img src="/manager/image/icon/13.jpg"  style="height: 13px ;width:${statistic.oneGradePercent*1.72}px" /></c:if>&nbsp;&nbsp;(${statistic.oneGradePercent}%)&nbsp;&nbsp; <span>${statistic.oneGrade}</span>条</li>
</ul>
</div>
</div>
</td>
<td width="46%">
  <!--会员数提醒-->
  <div class="S_Credit" style="height:130px;">
    <div class="Credit_title">
      会员数提醒
    </div>
    <div class="S_Reminded">
      <ul>
        <li>
        <table width="0" border="0">
          <tr>
            <td>
              <b>
                今日新增
              </b>
            </td>
            <td>
              <span>
                ${memberTodayAddCount}
              </span>
              个
             
            </td>
            <td>
              <b>
                本月新增
              </b>
            </td>
            <td>
              <span>
                ${memberToMonthAddCount}
              </span>
              个
              
            </td>
            <td width="10">
            </td>
            <td>
              <b>
                总数
              </b>
            </td>
            <td>
              <span>
                ${memberTotalCount}
              </span>
              个
            </td>
          </tr>
        </table>
      </li>
      
  </ul>
</div>
<div style="clear:both">
</div>
</div>
</td>
</tr>
<tr>
  <td style=" padding-bottom:5px;">
    <!--会员数提醒-->
    <div class="S_Credit"  >
      <div class="Credit_title">
        交易提醒
      </div>
      <div class="S_Reminded">
        <ul>
          <li>
          <table width="0" border="0">
            <tr>
              <td>
                <b>
                  今日消费
                </b>
              </td>
              <td>
                <span>
                 ${tradeTodayTradeCount}
                </span>
                笔
                
              </td>
              <td width="10">
              </td>
              <td>
                <b>
                  当月消费
                </b>
              </td>
              <td>
                <span>
                 ${tradeToMonthTradeCount}
                </span>
                笔
              </td>
            </tr>
          </table>
        </li>
        <li>
          <table width="0" border="0">
            <tr>
              <td>
                <b>
                  总计消费
                </b>
              </td>
              <td>
                <span>
                 ${tradeTotalCount}
                </span>
                笔
                
              </td>
              <td width="10">
              </td>
              <td>
                <b>
                  总计消费金额
                </b>
              </td>
              <td>
                <span>
                 ${tradeTotalMoney}
                </span>
                元
              </td>
            </tr>
          </table>
        </li>
      </ul>
    </div>
    <div style="clear:both">
    </div>
  </div>
</td>
</tr>
<tr>
  <td style="padding-right:5px;">

    <!--系统公告-->
    <div class="S_Credit" style="height:110px">
      <div class="Credit_title">
        系统公告
      </div>
      <div class="S_Reminded">
        <ul>
          <li style="background:none">
          <table width="0" border="0" style="width:410px">
          <c:forEach var="border" items="${borderList}" varStatus="st">
            <c:if test="${st.index%2==0}">
				<tr>
			</c:if>
              <td width="50%">
                <a href="/manager/platform/border/borderInfo.htm?id=${border.id}">
                <j:replace value="${border.name}" byByteLength="true" length="25" suffix="..."></j:replace>
                </a>
              </td>
            <c:if test="${(st.index)%2==1}">
				</tr>
			</c:if>
			<c:if test="${st.last&&st.count%2==1}">
				</tr>
			</c:if>
          </c:forEach>
          </table>
        </li>
    </ul>
  </div>
  <div style="clear:both">
  </div>
</div>
</td>
<td>
  <!--营销统计-->
  <div class="S_Credit">
    <div class="Credit_title">
      营销统计
    </div>
    <div class="S_Reminded">
      <ul>
        <li>
        <table width="0" border="0">
          <tr>
            <td>
              <b>
                待审核营销活动
              </b>
            </td>
            <td>
              <span>
                ${salePloyTotalCount}
              </span>
              条
            </td>
            <td width="10">
            </td>
            <td>
              <b>
                发送完成活动
              </b>
            </td>
            <td>
              <span>
                ${salePloySuccessCount}
              </span>
              条
              
            </td>
          </tr>
        </table>
      </li>
      <li>
      <table width="0" border="0">
        <tr>
          <td>
            <b>
              已经发送的短信
            </b>
          </td>
          <td>
            <span>
              ${operator.merchant.merchantSmsAccount.consumeCount}
            </span>
            条
           
          </td>
          <td width="10">
          </td>
          <td>
            <b>
              剩余短信
            </b>
          </td>
          <td>
            <span>
              ${operator.merchant.merchantSmsAccount.remainCount}
            </span>
            条
          </td>
        </tr>
      </table>
    </li>
  </ul>
</div>
<div style="clear:both">
</div>
</div>
</td>
</tr>
</table>

</div>
<div sytle="clear:both"></div>
</div>
</div>
</div>

<!-- footer -->
<c:import url="/manager/include/footer.jsp"/>
</body>
</html>
