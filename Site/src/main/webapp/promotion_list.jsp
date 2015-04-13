<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<c:import url="/include/head_include.jsp"/>
<title>${merchant.name} - 会生活 - 会员.点评.优惠.积分</title>
</head>
<body>

<c:import url="/include/top.jsp"/>

<div class="Buss"> 
<div class="Bu_top"></div> 
 <div class="Bu_content" id="promotionsDiv"> 
  <div class="manu" id="promotionpage"></div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
</div>

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" language="javascript" src="/js/paging.js"></script>
<script type="text/javascript" language="javascript" src="/js/promotion.js"></script>
<script>
window.addEvent('domready', function(){
	com.vlives.promotion.getPromotions();
});
</script>
</body>
</html>