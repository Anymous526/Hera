<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="j" uri="/just"%>
<j:getStatic var="grades" value="com.vlives.boss.comment.domain.MerchantComment$MerchantGrade@values()"/>   
<div class="Com" id="templateId"  >
 <div class="com_n"> 
   <div class="B_close" onclick="POP.closeBox()"  ></div>
      <h1><strong class="C_bt">消费记录</strong> <img src="/manager/image/icon/dx.png" />会员<b>${merchantComment.member.user.mobile} </b> 到${merchantComment.merchant.shortName}店<span>${merchantComment.tradeDetail.tradeOrder.type.desc}评价</span> ，发生于<fmt:formatDate value="${merchantComment.tradeDetail.tradeDate}" pattern="yyyy-MM-dd HH:mm"/>，<b>共消费${merchantComment.tradeDetail.amount}元</b></h1>
      <h2><span>评 分：</span>${merchantComment.merchantGradeSrc}</h2>
	  <h2><span>评论内容：</span><c:out  value="${merchantComment.comments}"  /></h2>
      <h3><span>回 复：</span>
    <textarea name="replyContent"  id="replyContent"  cols="" rows="" lang="500"  ><c:out  value="${merchantComment.reply}"  /></textarea>  </h3> 
      <!-- 
	  <h5><input name="" type="checkbox" value="" /> 匿名点评 </h5> 
	   -->
	  <h4><input name="" type="button"  onclick="com.comment.send(${merchantComment.id})"  /></h4>
 </div>
</div>