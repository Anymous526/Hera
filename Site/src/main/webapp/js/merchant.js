var com = com ? com : {};
com.vlives = com.vlives ? com.vlives : {};
com.vlives.merchant = com.vlives.merchant ? com.vlives.merchant : {};

com.vlives.merchant.setLevel = function(grade) {
	var html = '<h1>' + com.vlives.base.getLevelIcon(grade) + '</h1>';
	$('levelDiv').set('html', html);
}

// 检测收藏
com.vlives.merchant.isFavorite = function(merchantId) {
	if (com.vlives.base.isLogin() == false) {
		com.vlives.merchant.showSetFavorite();
		return ;
	}

	var req = new Request({ 
		url: '/user/ajax/merchant/favorite?rand=' + Math.random() * 5,
		method: 'get',
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse[merchantId] == false) {
				com.vlives.merchant.showSetFavorite(merchantId);
			} else {
				com.vlives.merchant.showCancelFavorite(merchantId);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send("merchantId=" + parseInt(merchantId));
}

com.vlives.merchant.showCancelFavorite = function() {
	$('favoriteDiv').set('html', '<h4><a href="#" onclick="com.vlives.merchant.cancelFavorite(' + merchantId + ')"></a></h4>');
}

com.vlives.merchant.showSetFavorite = function() {
	$('favoriteDiv').set('html', '<h3><a href="#" onclick="com.vlives.merchant.setFavorite(' + merchantId + ')"></a></h3>');
}

// 设置收藏
com.vlives.merchant.setFavorite = function(merchantId) {
	if (com.vlives.base.isLogin() == false) {
		var callback = "com.vlives.merchant.setFavorite(" + merchantId + ");"; // 设置回调函数
		openLoginWin(callback);
		return ;
	}
	var req = new Request({
		url: '/user/ajax/merchant/favorite',
		method: 'post',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success) {
				com.vlives.merchant.showCancelFavorite(merchantId);
			} else {
				new POP.msgBox(objResponse.msg);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send("merchantId=" + parseInt(merchantId));
} 

// 取消收藏
com.vlives.merchant.cancelFavorite = function() {
	var req = new Request({
		url: '/user/ajax/merchant/favorite',
		method: 'delete',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success) {
				com.vlives.merchant.showSetFavorite();
				$('spnFavCount').set('text', objResponse.msg); 
			} else {
				new POP.msgBox(objResponse.msg);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send("merchantId=" + parseInt(merchantId));
}

// 获得点评
com.vlives.merchant.getComment = function(merchantId) {
	var mm = $("commentPage");
	new Boat.UI.Paging(
			{
				url : '/merchant/ajax/' + merchantId + '/comments?rand=' + Math.random() * 5,
				limit : 5,
				startParamName : "pagination.currentPage",
				limitParamName : "pagination.pageSize",
				total : 'pagination.count',
				head : {
					el : mm,
					showNumber : true,
					showText : false
				},
				onAfterLoad : function(data) {
					if (data.success == true) {
						if (data.comments.length > 0) {
							com.vlives.merchant.insertComments(data.comments);
						}else{
							$('commentPage').style.display="none";
							com.vlives.base.addNoResultDiv('commentsUl','<div class="C_info"><div class="arreo">当前没有点评记录</div></div>')
						}
					}
				}
			}).load();
}

com.vlives.merchant.insertComments = function(comments) {
	var commentsList = $('commentsUl');
	com.vlives.base.delAllElement(commentsList);
	
	for ( var i = 0; i < comments.length; i++) {
		var liElement = new Element('li');
		commentsList.adopt(liElement);

		var userAvatar = "/images/head.jpg";
		if (comments[i].member.user.head != null && comments[i].member.user.head != '') {
			userAvatar = comments[i].member.user.head;
		}
		var nickname = comments[i].member.user.shortMobile;
		if (comments[i].member.user.petName != null && comments[i].member.user.petName != '') {
			nickname = com.vlives.base.truncateString(nickname, 8);
		}

		var div = new Element(
				'div',
				{
					'class' : 'C_head',
					'html' : '<img src="' + userAvatar + '" /><h1>' + nickname + '</h1>'
				});
		liElement.adopt(div);

		var currency = '元';
		if (comments[i].tradeDetail.tradeOrder.type.value == 3) {
			currency = '积分';
		}
		var type = comments[i].tradeDetail.tradeOrder.type.value;
		var typeDesc;
		var typeImg;
		switch (parseInt(type)) {
		case 1:
			typeDesc = "刷卡";
			typeImg = '<img src="/images/icon/dx.png" />';
			break;
		case 2:
			typeDesc = "现金";
			typeImg = '<img src="/images/icon/xj.png" />';
			break;
		case 3:
			typeDesc = "积分";
			typeImg = '<img src="/images/icon/jf.png" />';
			break;
		default:
			typeDesc = "刷卡";
			typeImg = '<img src="/images/icon/dx.png" />';
			break;
		}
		
		var html = '<h3><div class="h_content"><p>' + comments[i].comments +'</p>';
		if (comments[i].reply && comments[i].reply != 'null') {
			html += '<b><strong>商家回复：</strong>' + comments[i].reply + '</b>';
		}
		html += '</div></h3>';
		html += '<h1>' + comments[i].tradeDetail.tradeOrder.tradeDate + '<span>' + typeImg + typeDesc + '消费评价</span>'
			+ '共消费 <b>' + comments[i].tradeDetail.amount + currency + '</b> 已评价 '
			+ com.vlives.base.getLevelIcon(comments[i].merchantGrade.value)+ '</h1>' 

		var commentDiv = new Element('div', {
			'class' : 'C_info',
			'html' : html
		});
		liElement.adopt(commentDiv);
	}
}
 

com.vlives.merchant.openCommentWin = function() {
	var nowHtml = $("divComment");
	Mbox.open({
		url: nowHtml
	});
}

com.vlives.merchant.closeCommentWin = function() {
	Mbox.close();
	$("txtComment").set('value', '');
} 

// 获得未点评的消费记录
com.vlives.merchant.getUncommentedConsume = function(id) {
	var req = new Request({
		url: '/user/ajax/merchant/lastestUncommentedConsume',
		method: 'get',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success == true) {
				com.vlives.merchant.showUncommentedConsume(objResponse);
				com.vlives.merchant.openCommentWin();
			} else {
				new POP.msgBox("目前没有您要点评的信息！");
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send("merchantId="+id);
} 

// 显示未点评信息
com.vlives.merchant.showUncommentedConsume = function(objComment) {
	objComment.tradeDetails.forEach(function(_item, index) {
		$("hidUnCommentId").set("value", _item.id);
		$("spnUnCommentDate").set("text", _item.tradeDate);
		$("spnUnCommentMoney").set("text", _item.amount);
		$("spnUnTradeType").set("text", _item.tradeOrder.type.desc);
		
		
		var tempImage='';
		switch(parseInt(_item.tradeOrder.type.value))
		{
			case 1: // 刷卡交易
				tempImage='<img src="/images/icon/dx.png" />';
				break;
			case 2:  // 现金交易
				tempImage='<img src="/images/icon/xj.png" />';
				break;
			case 3: // 积分交易
				tempImage='<img src="/images/icon/jf.png" />';
				break;
			default:
				tempImage="";
				break
		} 
		
		$("spnUnTradeImage").set("html", tempImage);
	});
} 

// 提交点评
com.vlives.merchant.submitComment = function() {
	var id = $("hidUnCommentId").get("value");
	var content = $("txtComment").get("value");
	var score = $("hidScore").get("value");
	if (content.length < 1) {
		new POP.msgBox("请填写点评！");
		$("txtComment").focus();
		return false;
	}
	if (content.length > 280) {
		new POP.msgBox("点评内容大于140文字了！");
		$("txtComment").focus();
		return false;
	}

	var params = [];
	params.push('detailId=' + id);
	params.push('merchantGrade=' + score);
	params.push('comments=' + content);
	var parameter = params.join('&');
	var req = new Request({
		url: '/user/ajax/merchant/comment',
		method: 'post',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success == true) {
				new POP.msgBox("点评成功");

				com.vlives.merchant.closeCommentWin();
				com.vlives.merchant.getComment(merchantId); // 重新加载点评信息
				getUncommentedConsumeCount();  //更新未点评数字
			} else {
				new POP.msgBox(objResponse.msg);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send(parameter);
}

com.vlives.merchant.comment = function() {
	if (com.vlives.base.isLogin() == false) { // $("callback").set("value","getUnComment()");
		var callback = "com.vlives.merchant.getUncommentedConsume(" + merchantId + "); com.vlives.merchant.isFavorite(" + merchantId + ");"; // 设置回调函数
		openLoginWin(callback);
		return ;
	}

	com.vlives.merchant.getUncommentedConsume(merchantId);
} 

// 打分
com.vlives.merchant.score = function(value) {
	$("hidScore").set("value", value);
	for (var i = 1; i <= 5; i++) {
		$("score_" + i).set("src", "/images/icon/xxd1.png");
	}
	for (var i = 1; i <= value; i++) {
		$("score_" + i).set("src", "/images/icon/xxd.png");
	}
}

//搜索商户相关信息
com.vlives.merchant.navigate = function(merchantId, pArea, area, categoryId){
	if(categoryId!=""){
		window.location.href = "/search?categorySingle="+categoryId
								+"&searchArea="+area;
	}else if(area!=""){
		window.location.href = "/search?searchArea="+area;
	}
	else if(pArea!=""){
		window.location.href = "/search?searchArea="+pArea;
	}
}


