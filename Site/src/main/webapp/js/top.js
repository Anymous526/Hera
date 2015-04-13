var G_TYPE = 0;
var G_SINA_TOKEN = '';
var G_SINA_SECRET = '';
var G_SINA_USERID = '';
var G_SINA_NICKNAME='';  // 昵称
var G_SINA_FIGUREURL = ''; // 头像
var G_QQ_TOKEN = '';
var G_QQ_SECRET = '';
var G_QQ_OPENID = '';

var childWindow;
var waitSec=0;// 等待秒数
var timeHand;

function toQzoneLogin() {
	Mbox.close();

	var x = (window.screen.availWidth - 600) / 2;
	var y = (window.screen.availHeight - 420) / 2;

	childWindow = window.open("/qq_login.htm", "TencentLogin", "width=600, height=420, menubar=0, left=" + x + ", top=" + y + ", scrollbars=1, resizable=1,status=1,titlebar=0,toolbar=0");
}
function toWeiboLogin() {
	Mbox.close();
	
	var x = (window.screen.availWidth - 600) / 2;
	var y = (window.screen.availHeight - 420) / 2;
	
	childWindow = window.open("/include/weiboCall.jsp?opt=1", "WeiboLogin", "width=600, height=420, menubar=0, left=" + x + ", top=" + y + ", scrollbars=1, resizable=1,status=1,titlebar=0,toolbar=0");
}
function closeChildWindow() {
	if (childWindow!=null)
	{ 
		childWindow.close();
	}
}

function openLoginWin(callback) {
	this.cb = callback;
	var nowHtml = $("divLogin");
	Mbox.open({
		url: nowHtml
	});
}

function openBindingWin() {
	var bindingHtml = $("divBinding");
	Mbox.open({
		url: bindingHtml
	})
}

function initLoginWin()
{
	$('mobile').set('disabled', false);
	$('spnCaptcha').set('html', '<a href="javascript:void(0);" onclick="getVerficationCodeForLogin()">获取验证码</a>');	
	$("mobile").set('value', '');
	$("captcha").set('value', '');	
}

window.addEvent('domready',
function() {
	/*
	 * $("btnLogin").addEvent("click", function() { openLoginWin(); });
	 */
	$("closeLogin").addEvent("click",
	function() {
		if(timeHand) {
			window.clearInterval(timeHand);
		}
		initLoginWin();
		Mbox.close();
		
	});
	$("closeBinding").addEvent("click",
	function() {
		if(timeHand) {
			window.clearInterval(timeHand);
		}
		
		Mbox.close();
		$("bindingMobile").set('value', '');
		$("bindingCode").set('value', '');
	});
	/*
	 * $("btnCaptcha").addEvent("click", function() { getCaptcha(); });
	 */
	$("sumbitLogin").addEvent("click",
	function() {
		login();
	}); 
	$("submitBinding").addEvent("click",
	function() {
		loginForBinding();
	}); 
	/*
	 * new FormCheck('form', { display : { showErrors:0, indicateErrors : 1,
	 * fadeDuration : 1000 } });
	 */
});

function jumpToCity(cityId) {
	var cookie = Cookie.write('city_cookie', cityId, {duration: 365, path: '/'});
	window.location.href = "/index";
}

function isMobile(value) {
	if (/^13\d{9}$/.test(value) || (/^15[0-35-9]\d{8}$/.test(value)) || (/^18[05-9]\d{8}$/.test(value))) {
		return true;
	} else {
		return false;
	}
}

function isVerificationCodeValid(value) {
	if (/^\d{6}$/.test(value)) {
		return true;
	} else {
		return false;
	}
} 

// 为登录获取手机验证码
function getVerficationCodeForLogin() {
	var mobile = $("mobile").get('value').trim();
	
	if (!checkMobile($("mobile"), mobile)) {
		return false;
	}
	
	requestVerificationCode(mobile, 1);
	return true;
} 

// 为登录获取手机验证码
function getVerficationCodeForBinding() {
	var mobile = $("bindingMobile").get('value').trim();
	
	if (!checkMobile($("bindingMobile"), mobile)) {
		return false;
	}
	
	requestVerificationCode(mobile, 2);
	return true;
} 

/**
 * 检查手机号码是否合法
 */
function checkMobile(component, mobile) {
	if (mobile == "") {
		new POP.msgBox("请输入手机号！");
		component.focus();
		return false;
	}
	if (!isMobile(mobile)) {
		new POP.msgBox("请输入正确的手机号！");
		component.focus();
		return false;
	}
	return true;
}

/**
 * 检查验证码是否合法
 */
function checkVerificationCode(component, code) {
	if (code == "") {
		new POP.msgBox("请输入验证码！");
		component.focus();
		return false;
	}
	if (!isVerificationCodeValid(code)) {
		new POP.msgBox("请输入6位数字验证码！");
		component.focus();
		return false;
	}
	return true;
}

function openWait()
{
	waitSec=120;
	$('mobile').set('disabled',true);
	timeHand=window.setInterval(showWait, 1000);
}

function openWaitForBinding()
{
	waitSec=120;
	$('bindingMobile').set('disabled',true);
	timeHand=window.setInterval(showWaitForBinding, 1000);
}

function showWait()
{
	waitSec--;
	if (waitSec<1)
	{
		window.clearInterval(timeHand);
		$('mobile').set('disabled',false);
		$('spnCaptcha').set('html', '<a href="javascript:void(0);" onclick="getVerficationCodeForLogin()">获取验证码</a>');	
		return;
	} 	
	$('spnCaptcha').set('html', '<a style="color:#666666; ">请等待('+waitSec+')</a>');
}

function showWaitForBinding()
{
	waitSec--;
	if (waitSec<1)
	{
		window.clearInterval(timeHand);
		$('bindingMobile').set('disabled',false);
		$('spnVerificationCodeForBinding').set('html', '<a href="javascript:void(0);" onclick="getVerficationCodeForBinding()">获取验证码</a>');	
		return;
	} 	
	$('spnVerificationCodeForBinding').set('html', '<a style="color:#666666; ">请等待('+waitSec+')</a>');
}

// 获取手机验证码AJAX请求
function requestVerificationCode(mobile, type) {
	var req = new Request({
		url: '/ajax/verification',
		method: 'post',
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success==true)
			{
				if (type == 1) {
					openWait();
				} else {
					openWaitForBinding();
				}
			} else {
				new POP.msgBox(objResponse.msg); 
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure(); 
		}
	}).send('mobile=' + mobile);
}

// 登录窗口登录
function login() {
	var mobile = $("mobile").get('value').trim();
	var captcha = $("captcha").get('value').trim();
	var save = $("save").get('checked') ? 1 : 0;
	if (!checkMobile($("mobile"), mobile)) {
		return false;
	}
	if (!checkVerificationCode($("captcha"), captcha)) {
		return false;
	}
	
	var params = [];
	params.push('mobile=' + mobile);
	params.push('verifyCode=' + captcha);
	params.push('keepLoginStatus=' + save);
	loginRequest(params);
	return true;
}

// 绑定窗口登录
function loginForBinding() {
	var mobile = $("bindingMobile").get('value').trim();
	var bindingCode = $("bindingCode").get('value').trim();
	var save = $("save").get('checked') ? 1 : 0;
	if (!checkMobile($("bindingMobile"), mobile)) {
		return false;
	}
	if (!checkVerificationCode($("bindingCode"), bindingCode)) {
		return false;
	}
	var params = [];
	params.push('mobile=' + mobile);
	params.push('verifyCode=' + bindingCode);
	params.push('keepLoginStatus=' + save);
	params.push('accountType=' + G_TYPE);
	params.push('oauth_token=' + G_SINA_TOKEN);
	params.push('oauth_token_secret=' + G_SINA_SECRET);
	params.push('userName=' + G_SINA_NICKNAME);
	params.push('nickName=' + G_SINA_NICKNAME);  // 采用name字段名和新浪一致
	params.push('avatar=' + G_SINA_FIGUREURL);  // 采用profile_image_url字段名和新浪一致
	params.push('userid=' + G_SINA_USERID);
	params.push('openid=' + G_QQ_OPENID);
	params.push('token=' + G_QQ_TOKEN);
	params.push('secret=' + G_QQ_SECRET);
				
	loginRequest(params);
	return true;
}


function loginRequest(params) {
	var parameter = params.join('&');
	var req = new Request({
		url: '/ajax/session/mobile',
		method: 'post',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.result) {
				Mbox.close();
				showUserInfo(objResponse.user.petName, objResponse.user.shortMobile, objResponse.user.head);

				if (this.cb && this.cb != "") {
					eval(this.cb);
				}
			} else {
				new POP.msgBox(objResponse.message);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send(parameter);
}

// 采用TOKEN直接登录
function sinaLogin(token, secret, userid, nickname, figureurl) {
	var params = [];
	params.push('mobile=' + '');
	params.push('verifyCode=' + '');
	params.push('keepLoginStatus=' + '');
	params.push('accountType=' + '1');
	params.push('oauth_token=' + token);
	params.push('oauth_token_secret=' + secret);
	params.push('userName=' + nickname);
	params.push('userid=' + userid);
	params.push('openid=' + '');
	params.push('token=' + '');
	params.push('secret=' + '');
	// openLoginRequest(params);
	
	var parameter = params.join('&');
	var req = new Request({
		url: '/ajax/session/external',
		method: 'post',
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.result) {
				if (objResponse.binding) {
					showUserInfo(objResponse.user.petName, objResponse.user.shortMobile, objResponse.user.head);
				} else {
					// 未绑定，打开绑定手机窗口
					G_TYPE = 1;
					G_SINA_TOKEN = token;
					G_SINA_SECRET = secret;
					G_SINA_USERID = userid;
					G_SINA_NICKNAME = nickname;  // 昵称
					G_SINA_FIGUREURL = figureurl; // 头像
					// QQ绑定登录时添加QQ的TOKEN信息
					// ----------begin解决取消绑定无法打开手机登陆窗口的问题----------
					if(timeHand) {
						window.clearInterval(timeHand);
					}
					initLoginWin();
					Mbox.close();
					openBindingWin();
				}
			} else {
				new POP.msgBox(objResponse.message);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send(parameter);
	
}

//获取用户未点评数
function getUncommentedConsumeCount() {
	var req = new Request({
		url : '/ajax/user/unCommentedConsumesCount',
		method : 'get',
		evalScripts : true,
		onSuccess : function(responseText) {
			var uncommentedConsume = JSON.decode(responseText);
			if (uncommentedConsume && uncommentedConsume.count > 0) {
				uncommentedConsumeCount = uncommentedConsume.count;
				$('commentsNotice').innerHTML ='<a title="' +uncommentedConsumeCount+ '条未点评" href="/user/uncommentedConsumes">' +uncommentedConsumeCount +'</a>';
				$('commentsNotice').style.display="";
			}
		},
		onFailure : function() {
			com.vlives.base.ajaxFailure();
		}
	}).send('rnd=' + com.vlives.base.RndNum(8));
}

// 更新用户信息
function showUserInfo(nickName, mobile, avatar) {	
	if (!$chk(avatar))
		avatar="/images/head.jpg";
	$('userHead').src = avatar;

	if (!nickName || nickName == "") {
		$('userName').set('text', mobile);
	} else {
		$('userName').set('text', nickName);
	}

	$('top.userInfo').setStyle('display','block');
	$('spnLoginContent').setStyle('display','none');

	getUncommentedConsumeCount();
}

// 未登录时显示的信息
function showLogin()
{
	$('top.userInfo').setStyle('display','none');
	$('spnLoginContent').setStyle('display','block');
	// $("spnLoginContent").set("html", '<div class="D_lutxt">无需注册
	// 直接</div><input class="D_lubutton" name="btnLogin" id="btnLogin"
	// type="button" onclick="memberLogin();" />');
	// $("divSinaLogin").setStyle("display","block");
}

function makeName(mobile)
{
	var name="";
	if (mobile!="")
	{
		name = mobile.substr(7,4);
	} 
	return name;
}

function logout() {
	var req = new Request({
		url: '/ajax/session?rand=' + Math.random() * 5,
		method: 'delete',
		evalScripts: true,
		async: false,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success==true) {
				_goto();
			} else {
				new POP.msgBox(objResponse.msg);
			} 
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send();
}

function _goto()
{
	window.location.href="/";	
}

function openNotice() {
	var closeNotice = Cookie.read("closeNotice");
	if ((closeNotice == null) || (parseInt(closeNotice) != 1)) {
		getNotice();
	}
}
function getNotice() {
	var req = new Request({
		url: '/ajax/anouncement',
		method: 'get',
		evalScripts: true,
		onSuccess: function(responseText) {
			// new POP.msgBox(responseText);
			var objResponse = JSON.decode(responseText);
			if (objResponse.success == true) {
				if (objResponse.anouncements.length > 0) {
					objResponse.anouncements.forEach(function(_item, index) {
						_item.content= _item.content.replace(/<\/?[^>]+>/,''); // 去除HTML
																				// tag
						// _item.content= _item.content.replace(/[ |
						// ]*\n/,'\n'); //去除行尾空白
						// _item.content= _item.content.replace(/\n[\s| |
						// ]*\r/,'\n'); //去除多余空行
						if (_item.content.length>50)
						{
							_item.content=_item.content.substr(0,50)+' ...';
						}
						$('spnNotice').set("html", _item.content);
						$('divNotice').setStyle('display', 'block');
					});
				}
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send("");
}

function closeNotice() {
	$('spnNotice').set("text", "");
	$('divNotice').setStyle('display', 'none');
	var myCookie = Cookie.write('closeNotice', '1', {
		duration: 1
	});
}

// 会员页面更新昵称
function memberUpdateNick(nick) {
	// $('h1NickName').set('text', nick); //更新
	// $('spnLoginContent').set('html', '<a href="/user/memberMerchants">' +
	// nick + '的账号</a> | <a onclick="logout();">退出</a>');
}


function selectcity(){
	if ($('Address').style.display=='none'){
		$('Address').setStyle('display','block');
		// $('topmess').setStyle('display','block');
		$('selCity').set('class','City01');
	}else{
		$('Address').setStyle('display','none');
		$('selCity').set('class','City');
	}
	
}

function closeSelect() {
	$('Address').setStyle('display','none');
	$('selCity').set('class','City');
}

function selectState(cityId, state){
	if (state == 1){
		$('city_'+ cityId).set('html','目前位置');
//		$('city_'+ cityid).href="javascript:closeSelect();";
	} else if (state == 3) {
		$('city_'+ cityId).set('html','即将开通');
	}
}

function outState(cityId, cityName) {
	$('city_'+ cityId).set('html', cityName);
}

// 搜索框的样式更改
// 搜索框清空
function clearText(){
	$('name').value = "";
	$('name').setStyle('color','#000');
	$('name').focus();
}
// 检测搜索框是否填写了内容
function checkText(){
	if ($('name').value == "" || $('name').value == "搜索你感兴趣的商家或地点... ...") {
		new POP.msgBox("请输入您感兴趣的商家或地点");
		$('name').value = "搜索你感兴趣的商家或地点... ...";
		$('name').setStyle('color','#ccc');
		return false;
	}
}
