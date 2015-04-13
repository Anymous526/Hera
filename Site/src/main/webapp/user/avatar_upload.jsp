<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<link rel="stylesheet" type="text/css" media="screen" href="/js/css/uvumi-crop.css"/>
<c:import url="/include/head_include.jsp"/>
<title>我的首页 - 会生活 - 会员.点评.优惠.积分-我的会员</title>

</head>
<body>

<!--头部板块-->
<c:import url="/include/top.jsp"/>
<!---->

<div class="Buss">
<div class="Bu_top"></div> 
 <div class="Bu_content"> 
  <div class="Bu_mapleft">
  	<!--上部导航--> 
   <div class="Map_top" id="settingTop" style="height:43px; overflow:hidden;">
     <div class="Map_left"></div>
     <div class="Map_nav01">
       <ul>
         <li class="now">我的设置</li>
       </ul>
	 </div>
	 <div class="Map_right"></div>
   </div>
  <!--[我的设置]-->
  <div class="Con_list" id="settingDiv">
  	<div class="com_n" style="border:none; padding-top:30px;"> 
      
      <h1><strong class="C_bt">修改头像</strong><strong style="line-height:30px; font-size:14px;">选择您本地电脑中想要更换的图片</strong></h1> 
	  <h5>

	  <ul class="head_img01">
	    <li class="tou"><img name="curHead" id="curHead" width="60px" height="60px"  src="" />目前头像</li>
		<li class="up"> 
		<form action="/user/avatar" method="post" id="uploadform" name="uploadform" enctype="multipart/form-data">
		   	<input type="file" name="importFile" id="importFile"/><br /><span>支持jpg，jpeg，gif，png和bmp文件<br/>最大文件大小为2mb</span><br />
			<input class="sc01" type="submit" name="Submit" id="Submit" value="" onclick="return com.vlives.member.uploadAvatar();"/><span name="debug" id="debug" style="margin-left:5px;color:red;"></span>
		</form>
		</li>
	  </ul> 
	  </h5> 
	  <div name="cut_box" id="cut_box" style="display:none">
	  <h5>
	    <strong>选择作为头像的图片部分:</strong><br />
		<form action="/user/cropimage" method="post">
		   <div class="up_img" name="up_img" id="up_img"><img id="user_Head" name="user_Head" src=""/><input id="cutimage" name="cutimage" value="" type="hidden" /></div></h5>
		   	<input style="display:none" id="input_top" name="input_top" type="text" value="" readonly="readonly" />
			<input style="display:none" id="input_left" name="input_left" type="text"  value="" readonly="readonly" />
			<input style="display:none" id="input_width" name="input_width" type="text"  value="60" readonly="readonly" />
			<input style="display:none" id="input_height" name="input_height" type="text"  value="60" readonly="readonly" />
			<h4><input class="bc" type="submit" name="cutSubmit" id="cutSubmit" value=""/></h4>
		</form>
		</div>
 	</div>
   </div>
   
  </div>
    <!--右边板块--> 
  <div class="Bu_right"> 
    <div class="M_Info" id="userInfo">
	</div>
	<div class="M_Nav">
	 <ul>
	  <li id="merchantsNavLi"><a href="/user/memberMerchants">我的店铺</a></li>
	  <li id="couponsNavLi"><a href="/user/coupons">我的优惠券</a></li>
	  <li id="consumesNavLi"><a href="/user/consumes">我的消费</a></li>
	  <li id="pointsNavLi"><a href="/user/points">我的积分</a></li>
	  <li class="now" id="settingNavLi"><a href="/user/info">我的设置</a></li>
	 </ul>
	</div>
  </div>
  <div style="clear:both"></div>
 </div>
 <div class="Bu_bottom"></div>
 <div style="clear:both"></div>
</div> 

<c:import url="/include/footer.jsp"/>
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/member.js"></script>
<script type="text/javascript" src="/js/mootools-for-crop.js"> </script>
<script type="text/javascript" src="/js/UvumiCrop-compressed.js"> </script>
<script type="text/javascript" src="/js/iFrameFormRequest.js"> </script>
<script type="text/javascript">
window.addEvent("domready",function() {
	var user = ${user};
	//更新右侧会员信息
	com.vlives.member.updateUserInfo(user);
	//获取用户右侧统计信息
	com.vlives.member.getUserInfo();
	if (user.user.head == null)
		$('curHead').src = "/images/head.jpg";
	else
		$('curHead').src = user.user.head;
	
	var iFrame = new iFrameFormRequest('uploadform',{
		onRequest: function(){
			$('debug').set('html','<img src="/images/icon/loading.gif">Uploading...');
		},
		onComplete: function(response){
			if (response.indexOf("success:(")>=0){
				$('debug').set('html','成功');
				$('cut_box').setStyle('display','block');
				var ret = com.vlives.base.substring(response, "(", ")");
				var html = '<img id="user_Head" name="user_Head" src="' + ret + '"/>'
					+ '<input style="display:none" id="cutimage" name="cutimage" value="' + ret + '" type="hidden" />';
				$('up_img').set('html', html);
				new uvumiCropper('user_Head',{
					mini:{
						x:60,
						y:60
					},
					onComplete:function(top,left,width,height){
						$('input_top').set('value', top);
						$('input_left').set('value', left);
						$('input_width').set('value', width);
						$('input_height').set('value', height);
					},
					keepRatio:true,
					coordinatesOpacity:1
					});
			}else{
				$('cut_box').setStyle('display','none');
				var ret = com.vlives.base.substring(response, "(" ,")");
				if (ret == '1') {
					$('debug').set('html',"图片文件过大");
				} else if (ret == '2') {
					$('debug').set('html',"非法的图片文件");
				} else { 
					$('debug').set('html',"对不起，服务器正在开小差");
				}
			}
			
		}
	});
});
</script>

</body>
</html>