var com = com ? com : {};
com.vlives = com.vlives ? com.vlives : {};
com.vlives.promotion = com.vlives.promotion ? com.vlives.promotion : {};

com.vlives.promotion.download = function(id) {
	if (com.vlives.base.isLogin() == false) {
		var callback = "com.vlives.promotion.download(" + id + ");"; // 设置回调函数
		openLoginWin(callback);
		return ;
	}
	var req = new Request({
		url: '/user/ajax/promotion/' + id,
		method: 'post',
		async: false,
		evalScripts: true,
		onSuccess: function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success  == true) {
				new POP.msgBox("领用成功");
				
				$('sentCountDiv').set('html', '<span>'+ objResponse.msg + '张已经领用</span>');
			} else {
				new POP.msgBox(objResponse.msg);
			}
		},
		onFailure: function() {
			com.vlives.base.ajaxFailure();
		}
	}).send();
}

com.vlives.promotion.getPromotions = function() {
	var mm = $("promotionpage");
	
	new Boat.UI.Paging({
		url : "/promotions/ajax",
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
			if (data.promotions.length>0) {
				com.vlives.promotion.insertPromotion(data.promotions);
			} else {
				$('searchpage').style.display="none";
				com.vlives.base.addNoResultDiv('promotionsDiv','<div class="arreo">当前没有优惠券</div>')
			}
		}
	}).load();
}

com.vlives.promotion.insertPromotion = function(promotions) {
	var promotionsDiv = $('promotionsDiv');
	delAllElement(promotionsDiv);
	for ( var i = 0; i < promotions.length; i++) {
		var imgSrc = promotions[i].merchant.headForWeb;
		if (imgSrc == null || imgSrc == '') {
			imgSrc = "/images/s_01.gif";
		}
		
		var promotionDiv = new Element('div', {
			'class' : 'Quan'
		});
		promotionDiv.style.width = "940px";
		
		var html = '<div class="Q_left"><div class="q_info"><div class="q_info_left">'
			+ '<h1>' + promotions[i].merchant.shortName + '</h1>'
			+ '<h2>' + promotions[i].title + '</h2>'
			+ '<h3>截止日期：' + promotions[i].validEndDate + '</h3>'
			+ '</div><img src="' + imgSrc + '"/></div>'
			+ '<div class="Q_zt">';
		if (promotions[i].maxSendCount && promotions[i].maxSendCount > 0) {
			html += '<span style="line-height:24px; padding:4px; font-size:14px">：' + promotions[i].sentCount + '/' + promotions[i].maxSendCount + '</span>';
		}
		if (promotions[i].maxSendCount && promotions[i].maxSendCount > 0 && promotions[i].maxSendCount > promotions[i].sentCount) {
			html += '<a href="#" onclick="com.vlives.promotion.download(' + promotions[i].id + ')">领用</a>'
		}
		html += '</div><div class="Q_right"><p>' + promotions[i].content + '</p>'
			+ '<div class="zk"></div></div><div style="clear:both"></div></div>';
		promotionDiv.set('html', html);

		promotionsDiv.adopt(promotionDiv);
	}
}