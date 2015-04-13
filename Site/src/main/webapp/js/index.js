/**
 * 首页DOM操作JS文档 创建时间：2011.07.5 创建人：张建辉
 */

var curShopsArr = [];
var curCount;

function switchTab(i, tabname) {
	var tabs = $(tabname).getElementsByTagName("li");
	for ( var j = 0; j < tabs.length; j++) {
		if (j == i) {
			// tabs[j].addClass("now"); //IE下不行
			tabs[j].className = "now";
			tabs[j].backgroundPosition = "-114px";
		} else {
			tabs[j].className = "old";
			tabs[j].backgroundPosition = "-223px";
			// tabs[j].removeClass("now");
		}
	}
}  

// 添加点评模板
/**
 * parent:父元素的ID
 */
function addWhatsNewLiElement(parent, num, imgurl, username, usertel, typeDesc,
		content, timeFromNow, merchantId, merchantName) {
	var newLi = new Element('li');
	var merchantUrl = "/merchant/"+merchantId+"/";
	newLi.addEvents({
		click : function() {
			window.location=merchantUrl;
		},
		mousemove:function(){
			$('waike0' + num).setStyle('display','block');
			showmarker(num);
			$('waike0' + num).addEvent('click',function(){
				window.location=merchantUrl;
			});
		},
		mouseout:function(){
			$('waike0' + num).setStyle('display','none');
		}
	});
	parent.adopt(newLi);
//	var myFirstElement = new Element('div', {
//		'class' : 'number',
//		'html' : num
//	});
//	newLi.adopt(myFirstElement);
	var mySecondElement = new Element('div', {
		'class' : 'txt_img'
	});
	mySecondElement.set('html', '<img src="' + imgurl + '"/>');
	newLi.adopt(mySecondElement);
	var myThirdElement = new Element('div', {
		'class' : 'txt_txt'
	});

	truncatedContent = truncateString(content, 20);
	truncatedUserName = truncateString(username, 10);
	var truncatedMerchantName = truncateString(merchantName, 12);
	
	htmlContent = '<h1>'
			+ truncatedUserName
			+ ' <span><img src="/images/icon/u145.png" />' + usertel
			+ '</span></h1><h2>在<a href="' + merchantUrl
			+ '" onclick="stopPropagation(event)" title="' + merchantName
			+ '">' + truncatedMerchantName + '</a> ' + timeFromNow + '</h2><h3>'
			+ typeDesc + '<span title="' + content + '">'
			+ truncatedContent + '</span></h3>';

	myThirdElement.set('html', htmlContent);
	newLi.adopt(myThirdElement);
}

// 添加优惠模板
function addPromotionLiElement(parent, num, imgurl, merchantId, merchantName,
		content) {
	var merchantUrl = "/merchant/"+merchantId+"/";
	var newLi = new Element('li');
	newLi.addEvents({
		click : function() {
			window.location=merchantUrl;
		},
		mousemove:function(){
			$('waike0' + num).setStyle('display','block');
			showmarker(num);
			$('waike0' + num).addEvent('click',function(){
				window.location=merchantUrl;
			});
		},
		mouseout:function(){
			$('waike0' + num).setStyle('display','none');
		}
	});
	parent.adopt(newLi);
	var mySecondElement = new Element('div', {
		'class' : 'txt_img'
	});
	mySecondElement.set('html', '<img src="' + imgurl + '"/>');
	newLi.adopt(mySecondElement);
	var myThirdElement = new Element('div', {
		'class' : 'txt_txt'
	});

	var displayContent = truncateString(content, 70);

	// 处理商户名超长
	var displayMerchantName = truncateString(merchantName, 14);
	
	htmlContent = '<h1><a href="' + merchantUrl
			+ '" onclick="stopPropagation(event)" title="'
			+ merchantName + '">' + displayMerchantName
			+ '</a></h1><h5><span title="' + content + '">'
			+ displayContent + '</span></h5> ';

	myThirdElement.set('html', htmlContent);
	newLi.adopt(myThirdElement);
}

// 添加推荐模板
function addPreviousLiElement(parent, num, imgurl, merchantId, merchantName,
		commentCount, favCount, memberCount) {
	var newLi = new Element('li');
	var merchantUrl = "/merchant/"+merchantId+"/";
	newLi.addEvents({
		click : function() {
			window.location=merchantUrl;
		},
		mousemove:function(){
			$('waike0' + num).setStyle('display','block');
			showOthermarker(num);
			$('waike0' + num).addEvent('click',function(){
				window.location=merchantUrl;
			});
		},
		mouseout:function(){
			$('waike0' + num).setStyle('display','none');
		}
	});
	parent.adopt(newLi);
	var mySecondElement = new Element('div', {
		'class' : 'txt_img'
	});
	mySecondElement.set('html', '<img src="' + imgurl + '"/>');
	newLi.adopt(mySecondElement);
	var myThirdElement = new Element('div', {
		'class' : 'txt_txt'
	});

	// 处理商户名超长
	var truncatedMerchantName = truncateString(merchantName, 14);
	myThirdElement.set('html', '<h1><a href="/merchant/' + merchantId
			+ '/" onclick="stopPropagation(event)" title="' + merchantName
			+ '">' + truncatedMerchantName + '</a></h1><h4><dl>' + '<dd class="Green">'
			+ commentCount + '<br /><span>点评</span></dd>' + '<dd class="Blue">'
			+ favCount + '<br /><span>收藏</span></dd>' + '<dd class="Yellow">'
			+ memberCount + '<br /><span>会员</span></dd></dl></h4> ');
	newLi.adopt(myThirdElement);
}

// 批量添加/更新会员
function addWhatsNewList(whatsNew, type) {
	var parent = $('rightlist');
	for ( var i = 0; i < whatsNew.length; i++) {
		var num = i + 1;
		var imgurl = whatsNew[i].member.user.head;
		if (imgurl == null) {
			imgurl = "/images/head.jpg";
		}
		var username = whatsNew[i].member.user.petName;
		if (username == null) {
			username = "会生活会员";
		}
		var usertel = whatsNew[i].member.user.shortMobile;
		var typeDesc = whatsNew[i].typeDesc;
		var content = whatsNew[i].content;
		var timeFromNow = whatsNew[i].timeFromNow;
		var merchantName = whatsNew[i].merchant.shortName;
		var merchantId = whatsNew[i].merchant.id;
		if (type == 1)
			addWhatsNewLiElement(parent, num, imgurl, username, usertel,
					typeDesc, content, timeFromNow, merchantId, merchantName);
	}
}
// 批量添加/更新优惠商家列表
function addPromotionList(promotions) {
	var parent = $('rightlist');
	for ( var i = 0; i < promotions.length; i++) {
		var num = i + 1;
		var imgurl = promotions[i].merchant.headForWeb;
		if (imgurl == null) {
			imgurl = "/images/s_01.gif";
		}
		var merchantId = promotions[i].merchant.id;
		var merchantName = promotions[i].merchant.shortName;
		var content = promotions[i].introduction;
		var commentCount = promotions[i].merchant.merchantReferenceStatistic.commentCount;
		var favCount = promotions[i].merchant.merchantReferenceStatistic.favCount;
		var membercount = promotions[i].merchant.memberGroup.membercount;
		addPromotionLiElement(parent, num, imgurl, merchantId, merchantName,
				content);
	}
}
// 批量添加/更新推荐商家列表
function addShopList(merchants) {
	var parent = $('rightlist');
	for ( var i = 0; i < merchants.length; i++) {
		var num = i + 1;
		var imgurl = merchants[i].headForWeb;
		if (imgurl == null) {
			imgurl = "/images/s_01.gif";
		}
		var merchantId = merchants[i].id;
		var merchantName = merchants[i].shortName;
		var commentCount = merchants[i].merchantReferenceStatistic.commentCount;
		var favCount = merchants[i].merchantReferenceStatistic.favCount;
		var membercount = merchants[i].memberGroup.memberCount;
		addPreviousLiElement(parent, num, imgurl, merchantId, merchantName,
				commentCount, favCount, membercount);
	}
}

// 修改selText的提示语句
function editText(txt) {
	var editElement = $("selText");
	editElement.set('text', txt);
}
function hideDIV(divname) {
	$(divname).style.display = "none";
}

// 定时更新商家信息
function freshmarker() {
	showmarker(curCount);
	curCount += 1;
	if (curCount > curShopsArr.length) {
		curCount = 1;
	}
}
// ajax获取指定地址信息
function ajaxGetData(url, type) {
	var comments;
	delAllElement($('rightlist'));
	addDelayDiv('container', 'delayDIV');
	addDelayDiv('rightlist', 'rightdelay');
	var request = new Request.JSON({
		url : url,
		method : 'get',
		onComplete : function(res, jsonObj) {
			ret = JSON.decode(jsonObj);
			
			if (ret == null) {
				new POP.msgBox("无法获得数据，请稍后重试，或者联系会生活客服。");
				com.vlives.base.deleteDiv('delayDIV');
				com.vlives.base.deleteDiv('rightdelay');
				return false;
			}
			// POP.closeBox();
			curShopsArr = ret;
			com.vlives.base.deleteDiv('delayDIV');
			delAllElement($('rightlist'));
			if (type == 1) {
				markersAdd(ret);
				addWhatsNewList(ret, type);
				com.vlives.base.deleteDiv('rightdelay');
				// 定时弹出商家信息窗口
				// curCount=1;
				// freshmarker();
				// var test=freshmarker.periodical(3500);
			} else if (type == 2) {
				markersAdd(ret);
				addPromotionList(ret, type);
				com.vlives.base.deleteDiv('rightdelay');
			} else if (type == 3) {
				markersOtherAdd(ret);
				addShopList(ret, type);
				com.vlives.base.deleteDiv('rightdelay');
			}
		}
	}).send('rnd=' + RndNum(8));
}
// ajax给指定地址POST信息2011.07.21未完成
function ajaxPostData(url, formname) {
	var request = new Request.JSON({
		url : url,
		method : 'post',
		data : $(formname).toQueryString(),
		onComplete : function(res, jsonObj) {
			ret = JSON.decode(jsonObj);
			if (ret == null) {
				new POP.msgBox("无法获得数据，请稍后重试，或者联系会生活客服。");
				return false;
			}

		}
	}).send('rnd=' + RndNum(8));
}

// 初始化网站
function initializeSite() {
	initialize("container");
	//ajaxGetCount();
	// 每隔一小时更新一次网站统计数据
	// var test=ajaxGetCount.periodical(60*60*1000);
	var bodyWrapVar = $('rightlist');
	// $('WhatsNew').addClass("now");
	editText("看看大家在做什么");
	ajaxGetData('/ajax/whatsnew', 1);
}
// 异步加载地图

function loadScript() {
	var script = document.createElement("script");
	script.src = "http://api.map.baidu.com/api?v=1.2&callback=initializeSite";
	document.body.appendChild(script);
}
/**
 * 添加窗口中Element的相关事件
 */
//window.addEvent('load', function() {
	// loadScript();
//});
window.addEvent("domready", function() {
	var shops;

	initializeSite();

	$('Recommend').addEvent("click", function() {
		switchTab(1, "Tablist");
		editText("推荐排行");
		//delAllElement($('rightlist'));
		ajaxGetData('/ajax/recommend', 3);
	});
	$('Preferential').addEvent("click", function() {
		switchTab(2, "Tablist");
		editText("最新优惠");
		//delAllElement($('rightlist'));
		ajaxGetData('/ajax/promotions', 2);
	});
	$('WhatsNew').addEvent("click", function() {
		switchTab(0, "Tablist");
		editText("看看大家在做什么");
		//delAllElement($('rightlist'));
		ajaxGetData('/ajax/whatsnew', 1);
	});

	// 说说版块控制
	$('shuoDIV').addEvent("click", function() {
		if ($('rightDIV').style.display == "none")
			$('rightDIV').style.display = "block";
		else
			$('rightDIV').style.display = "none";
	});
	// var periodicalFunctionVar =middleIE.periodical(10000);
	// 浏览器滚动时提交建议的侧栏跟着滑动
	window.onscroll = _onScroll;
	function _onScroll() {
		var labeller_layer = $('Sshuo');
		var scrollTop = document.documentElement.scrollTop
				+ document.body.scrollTop + 250;
		labeller_layer.style.top = scrollTop + 'px';
	}
	var lim = new limit();
	lim.txtNote = $("suggestion");
	lim.txtLimit = $("shownum");
	lim.limitCount = 100;
	lim.isbyte = true;
	lim.init();
	// 提交建议的按钮事件
	$('submitSuggestion').addEvent("click", function() {
		if ($('suggestion').value == "") {
			new POP.msgBox('请输入您的建议或问题！');
			return false;
		} else {
			var request = new Request.JSON({
				url : "/ajax/feedback",
				method : 'post',
				onComplete : function(res, jsonObj) {
					ret = JSON.decode(jsonObj);
					if (ret.success == true) {
						$('suggestion').value = "";
						$('rightDIV').style.display = "none";
						new POP.msgBox(ret.msg);
					}
				}
			}).send("template=" + $('suggestion').value + "&rnd=" + RndNum(8));
		}
	});
});