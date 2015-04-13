//对象实例化，如果已经存在一个对象，则获取，否则新建一个对象
var com = com? com : {};
com.vlives = com.vlives? com.vlives : {};
com.vlives.member = com.vlives.member? com.vlives.member : {};

// 更新用户信息
com.vlives.member.updateUserInfo=function(user){
	var nickName = user.user.petName;
	if (!$chk(nickName)){
		nickName="";
	}
	var avatar = user.user.head;
	if (!$chk(avatar)) {
		avatar = "/images/head.jpg";
	}
	var address = "广东深圳";
	var parent = $('userInfo');
	if ($('previewImg') &&$('cutimage')){
		$('previewImg').src=avatar;
		$('cutimage').value=avatar;
	}
	var userElement = new Element('div', {
		'class' : 'M_Info',
		'id':'topInfo',
		'html' : '<ol style="display:none;"><img src="' + avatar + '" /><div style="display:none;" class="txt_txt"><h1 id="h1NickName">'
				+ nickName + '</h1><h2><span id="shortMobile"><img src="/images/icon/u145.png"/>'
				+ user.user.shortMobile
				+ '</span> | <span>广东 深圳</span></h2></div></ol>'
				+ '<ul><li class="Green"><h1 id="commentCount">0'
				+ '</h1><h2>点评</h2></li>' + '<li class="Blue"><h1 id="memberMerchantCount">'
				+  '0</h1><h2>会员</h2></li>'
				+ '<li class="Yellow"><h1 id="favoriteMerchantCount">0'
				+ '</h1><h2>收藏</h2></li></ul>'
	});
	// parent.setStyle('display','none');
	parent.adopt(userElement);
}
// 如果用户已经登录，获取用户信息，无需用户ID，后台自动获取当前用户ID
com.vlives.member.getUserInfo=function(){
	// 获取登录后的用户信息
	var req = new Request({
		url : '/user/ajax/statistics?rnd=' + com.vlives.base.RndNum(8),
		method : 'get',
		evalScripts : true,
		onSuccess : function(responseText) {
			var user = JSON.decode(responseText);
			$('favoriteMerchantCount').innerHTML=user.favoriteMerchantCount;
			$('memberMerchantCount').innerHTML=user.memberMerchantCount;
			$('commentCount').innerHTML=user.commentCount;
		},
		onFailure : function() {
			return "error"
		}
	}).send();
}

// 获取我的会员列表
com.vlives.member.getMembershipMerchantList=function(){
	var mm = $("memberpage");
	new Boat.UI.Paging({
		url : '/user/ajax/memberMerchants?rnd=' + com.vlives.base.RndNum(8),
		limit : 15,
		startParamName : "pagination.currentPage",
		limitParamName : "pagination.pageSize",
		total : 'pagination.count',
		head : {
			el : mm,
			showNumber : true,
			showText : false
		},
		onAfterLoad : function(data) {
			var merchants = data.merchants;
			// alert(merchants.length);
			if (merchants.length>0) {
				// membershipMerchantsLoaded =true;
				com.vlives.member.insertMerchants('membershipMerchantsUl', merchants);
			}else{
				$('memberpage').style.display="none";
				com.vlives.base.addNoResultDiv('membershipMerchantsUl','<div class="arreo">当前没有商家列表</div>')
			}
		}
	}).load();
}


// 获取我的收藏商家
com.vlives.member.getFavoriteMerchantList=function() {
	var mm = $("favpage");
	new Boat.UI.Paging({
		url : '/user/ajax/favoriteMerchants?rnd=' + com.vlives.base.RndNum(8),
		limit : 15,
		startParamName : "pagination.currentPage",
		limitParamName : "pagination.pageSize",
		total : 'pagination.count',
		head : {
			el : mm,
			showNumber : true,
			showText : false
		},
		onAfterLoad : function(data) {
			var merchants = data.merchants;
			if (merchants.length>0) {
				// favoriteMerchantsLoaded = true;
				com.vlives.member.insertMerchants('favoriteMerchantsUl', merchants);
			}else{
				$('favpage').style.display="none";
				com.vlives.base.addNoResultDiv('favoriteMerchantsUl','<div class="arreo">当前没有商家列表</div>')
			}
		}
	}).load();
}

// 插入我的会员商家
com.vlives.member.insertMerchants=function(parentElementId, merchants) {
	var merchantList = $(parentElementId);
	delAllElement(merchantList);
	for ( var i = 0; i < merchants.length; i++) {
		var liElement = new Element('li');
		merchantList.adopt(liElement);
		var merchantIndexDivElement = new Element('div', {
			'class' : 'number'
		});
		merchantIndexDivElement.set('html', (i + 1));
		liElement.adopt(merchantIndexDivElement);

		var merchantImageDivElement = new Element('div', {
			'class' : 'txt_img'
		});
//		if (merchants[i].head == null) {
//			merchants[i].head = '/images/s_01.gif';
//		}
		merchantImageDivElement.set('html',
				'<a title="点击进入商户主页" href="/merchant/'
						+ merchants[i].id + '/"><img src="'
						+ merchants[i].headForWeb + '" /></a>');
		liElement.adopt(merchantImageDivElement);

		var contentDivElement = new Element('div', {
			'class' : 'txt_txt'
		});
		contentDivElement
				.set(
						'html',
						'<h1><span><a title="点击进入商户主页" href="/merchant/'
								+ merchants[i].id
								+ '/">'
								+ merchants[i].shortName
								+ '</a></span>'
								+ com.vlives.base.getLevelIcon(merchants[i].merchantReferenceStatistic.averageGrade)
								+ '</h1><h2>'
								+ merchants[i].businessAddress
								+ '</h2><h3><dl><dd class="Green">'
								+ merchants[i].merchantReferenceStatistic.commentCount
								+ '<br /><span>评论</span></dd><dd class="Blue">'
								+ merchants[i].memberGroup.memberCount
								+ '<br /><span>会员</span></dd><dd class="Yellow">'
								+ merchants[i].merchantReferenceStatistic.favCount
								+ '<br /><span>收藏</span></dd></dl></h3>');
		liElement.adopt(contentDivElement);
	}
}

com.vlives.member.getCoupons = function() {
	var mm = $("couponspage");
	var coupons;
	
	var dataStr = $('couponFilterForm').toQueryString() + '&rnd=' + RndNum(8);
	
	// $('curstate').value = "0";
	new Boat.UI.Paging({
		url : "/user/ajax/coupons?" + dataStr,
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
			delAllElement($('couponsOl'));

			if (data.coupons.length > 0) {
//				com.vlives.member.insertCouponStatuses(data.statuses);
//				com.vlives.member.insertCouponMerchants(data.merchants);
				com.vlives.member.insertCoupons(data.coupons);
			} else {
				$('searchpage').style.display="none";
				com.vlives.base.addNoResultDiv('couponsSubDiv','<div class="arreo">当前没有优惠券</div>')
			}
		}
	}).load();
}

//com.vlives.member.insertCouponStatuses = function(couponStatuses) {
//	var couponStatusDd = $('couponStatusDd');
//	delAllElement(couponStatusDd);
//	
//	couponStatusDd.adopt('<img src="/images/icon/zt.jpg" />');
//	for ( var i = 0; i < couponStatuses.length; i++) {
//		var couponStatusSpan = '<span><a href="javascript:com.vlives.member.getCouponsByStatus(' + couponStatuses[i].value + ')">'
//			+ couponStatuses[i].desc + '</a></span>';
//		couponStatusDd.adopt(couponStatusSpan);
//	}
//}

//com.vlives.member.insertCouponMerchants = function(couponMerchants) {
//	var couponMerchantDd = $('couponMerchantDd');
//	delAllElement(couponMerchantDd);
//	
//	couponMerchantDd.adopt('<img src="images/icon/sh.jpg" />');
//	for ( var i = 0; i < couponMerchants.length; i++) {
//		var couponMerchantSpan = '<span><a href="javascript:com.vlives.member.getCouponsByMerchant(' + couponMerchants[i].id + ')">'
//			+ couponMerchants[i].shortName + '</a></span>';
//		couponMerchantDd.adopt(couponMerchantSpan);
//	}
//}

com.vlives.member.insertCoupons = function(coupons) {
	var couponsOl = $('couponsOl');
	for ( var i = 0; i < coupons.length; i++) {
		var couponDiv = new Element('div', {
			'class' : 'Quan'
		});

		var imgSrc = coupons[i].couponPloy.merchant.headForWeb;
		if (imgSrc == null || imgSrc == '') {
			imgSrc = "/images/s_01.gif";
		}
		
		var html = '<div class="Q_left"><div class="q_info"><div class="q_info_left">'
			+ '<h1>' + coupons[i].couponPloy.merchant.shortName + '</h1>'
			+ '<h2>' + coupons[i].couponPloy.title + '</h2>'
			+ '<h3>截止日期：' + coupons[i].couponPloy.validEndDate.substr(0, 10) + '</h3>'
			+ '</div><img src="' + imgSrc + '"/></div>'
			+ '<div class="Q_zt"><span>券号：' + coupons[i].code
			+ '</span><em style="">状态：<i>' + coupons[i].couponStatus.desc + '</i></em></div>'
			+ '<div class="Q_zt01">领用日期：' + coupons[i].createDate.substr(0, 10) + '</div></div>'
			+ '<div class="Q_right"><p>' + coupons[i].couponPloy.content + '</p>'
			+ '</div><div style="clear:both"></div></div>';

		couponDiv.set('html', html);
		couponsOl.adopt(couponDiv);
	}
}

com.vlives.member.getCouponsByStatus = function(status, description) {
	$('couponStatus').value = status;
	
	var children = $('couponStatusDd').getChildren('span');
	children.each(function(el){
	    el.setStyle('display', 'none');
	});
	
	$('couponStatusSpan' + status).setStyle('display', '');
	$('couponStatusSpan' + status).className = "now";
	$('couponStatusSpan' + status).set('html',
			description + '<a href="javascript:com.vlives.member.removeCouponStatusSearch('
				+ status + ', \'' + description + '\')"><img src="/images/icon/close.png"/></a>');
	
	com.vlives.member.getCoupons();
}

com.vlives.member.removeCouponStatusSearch = function(status, description) {
	$('couponStatus').value = "";
	
	var children = $('couponStatusDd').getChildren('span');
	children.each(function(el){
	    el.setStyle('display', '');
	});
	
	$('couponStatusSpan' + status).className = "";
	$('couponStatusSpan' + status).set('html',
			'<a href="javascript:com.vlives.member.getCouponsByStatus(' + status + ', \'' + description + '\')">' + description + '</a>');
	
	com.vlives.member.getCoupons();
}

com.vlives.member.getCouponStatusDescription

com.vlives.member.getCouponsByMerchant = function(merchantId) {
	$('couponMerchant').value = status;
	
	com.vlives.member.getCoupons();
}

com.vlives.member.sortCoupons = function(sortingOption, description) {
	// 1：  领用时间排序； 2：截止日期排序
	$('couponSortingOption').value = sortingOption;

	var children = $('sortingDd').getChildren('span');
	children.each(function(el){
	    el.setStyle('display', 'none');
	});
	
	$('sortingSpan' + sortingOption).setStyle('display', '');
	$('sortingSpan' + sortingOption).className = "now";
	$('sortingSpan' + sortingOption).set('html',
			description + '<a href="javascript:com.vlives.member.removeSorting('
				+ sortingOption + ', \'' + description + '\')"><img src="/images/icon/close.png"/></a>');
	
	com.vlives.member.getCoupons();
}

com.vlives.member.removeSorting = function(sortingOption, description) {
	$('couponSortingOption').value = "";
	
	var children = $('sortingDd').getChildren('span');
	children.each(function(el){
	    el.setStyle('display', '');
	});
	
	$('sortingSpan' + sortingOption).className = "";
	$('sortingSpan' + sortingOption).set('html',
			'<a href="javascript:com.vlives.member.sortCoupons(' + sortingOption + ', \'' + description + '\')">' + description + '</a>');
	
	com.vlives.member.getCoupons();
}

//获取用户消费记录
com.vlives.member.getConsumesList=function(type) {
	var mm = $("searchpage");
	var consumes;
	var url;
	if (type == 1) {
		url = '/user/ajax/consumes?rnd=' + com.vlives.base.RndNum(8);
	} else {
		url = '/user/ajax/uncommentedConsumes?rnd=' + RndNum(8);
	}
	// $('curstate').value = "0";
	new Boat.UI.Paging({
		url : url,
		limit : 10,
		startParamName : "pagination.currentPage",
		limitParamName : "pagination.pageSize",
		total : 'pagination.count',
		head : {
			el : mm,
			showNumber : true,
			showText : false
		},
		onAfterLoad : function(data) {
			consumes = data;
			if (consumes.tradeDetails.length>0) {
				com.vlives.member.insertConsumes(consumes.tradeDetails);
			}else{
				$('searchpage').style.display="none";
				com.vlives.base.addNoResultDiv('consumesSubDiv','<div class="arreo"><span style="margin-left:15px;">当前没有消费记录</span></div>')
			}
		}
	}).load();
}

// 插入用户消费记录
com.vlives.member.insertConsumes=function(consumes) {
	var Count = $('consumesTable').rows.length;
	for ( var i = Count - 1; i >= 1; i--) {
		$('consumesTable').deleteRow(i);
	}
	var rowCount = 1;
	for ( var i = 0; i < consumes.length; i++) {
		var tr = $('consumesTable').insertRow(rowCount + i);
		var td = tr.insertCell(0);
		td.innerHTML = '<a style="color: black;" title="点击进入商户主页" href="/merchant/'
				+ consumes[i].tradeOrder.merchant.id + '/">'
				+ consumes[i].tradeOrder.merchant.shortName + '</a>';
		td = tr.insertCell(1);
		td.appendChild(document.createTextNode(consumes[i].tradeDate));
		td = tr.insertCell(2);
		td.appendChild(document.createTextNode(consumes[i].amount));
		td = tr.insertCell(3);
		if (consumes[i].tradeOrder == null) {
			td.appendChild(document.createTextNode(0));
		} else {
			td
					.appendChild(document
							.createTextNode(consumes[i].tradeOrder.consumePointDetail.point));
		}
		td = tr.insertCell(4);
		var typeIconID=consumes[i].tradeOrder.type.value;
		var typeDesc = consumes[i].tradeOrder.type.desc;
		var typeDate = consumes[i].tradeDate;
		var typeMoney = consumes[i].amount;
		var typeID = consumes[i].id;
		if (consumes[i].merchantComment != null) {
			var commentID = consumes[i].merchantComment.id;
			var commentContent = consumes[i].merchantComment.comments;
			var merchantGrade = consumes[i].merchantComment.merchantGrade.value;
		}
		html = '<img src="/images/icon/xxd.png" id="score_1" onclick="com.vlives.member.score(1);" style="cursor:pointer;" />'
				+ '<img src="/images/icon/xxd.png" id="score_2" onclick="com.vlives.member.score(2);" style="cursor:pointer;" />'
				+ '<img src="/images/icon/xxd.png" id="score_3" onclick="com.vlives.member.score(3);" style="cursor:pointer;" />'
				+ '<img src="/images/icon/xxd.png" id="score_4" onclick="com.vlives.member.score(4);" style="cursor:pointer;" />'
				+ '<img src="/images/icon/xxd.png" id="score_5" onclick="com.vlives.member.score(5);" style="cursor:pointer;" />'
		if (consumes[i].commented == false) {
			td.innerHTML = '<img src="/images/icon/dp.png"/><a style="color: black;" id="openComment" href=\'javascript:void(0)\' onclick=\'com.vlives.member.openCommentWin("'
					+ typeIconID
					+ '","'	
					+ typeID
					+ '","'
					+ typeDesc
					+ '","'
					+ typeDate
					+ '","'
					+ typeMoney + '");\'>点评</a>';

		} else {
			var isModifyed = consumes[i].merchantComment.modifyed;
			if (isModifyed == false) {
				// 修改点评
				td.innerHTML = '<a style="color: black;" href=\'javascript:void(0)\' onclick=\'com.vlives.member.openModifyCommentWin("'
						+ typeIconID
						+ '","'
						+ commentID
						+ '","'
						+ typeDesc
						+ '","'
						+ typeDate
						+ '","'
						+ typeMoney
						+ '",'
						+ merchantGrade
						+ ',"'
						+ commentContent
						+ '");\'>修改点评</a>';
			} else {
				// 查看点评
				td.innerHTML = '<a style="color: black;" href=\'javascript:void(0)\' onclick=\'com.vlives.member.openShowCommentWin("'
						+ typeIconID
						+ '","'	
						+ commentID
						+ '","'
						+ typeDesc
						+ '","'
						+ typeDate
						+ '","'
						+ typeMoney
						+ '",'
						+ merchantGrade
						+ ',"'
						+ commentContent
						+ '");\'>查看点评</a>';

			}
		}

	}
}

// 获取点评列表
com.vlives.member.getCommentsList=function() {
	var mm = $("commentpage");
	var consumes;
	new Boat.UI.Paging({
		url : '/user/ajax/comments?rnd=' + com.vlives.base.RndNum(8),
		limit : 3,
		startParamName : "pagination.currentPage",
		limitParamName : "pagination.pageSize",
		total : 'pagination.count',
		head : {
			el : mm,
			showNumber : true,
			showText : false
		},
		onAfterLoad : function(data) {
			comments = data.comments;
			if (comments.length>0) {
				com.vlives.member.insertComments(comments);
			}else{
				$('commentpage').style.display="none";
				com.vlives.base.addNoResultDiv('commentsUl','<div class="C_info"><div class="arreo">当前没有点评记录</div></div>')
			}
		}
	}).load();
}

// 插入获取的点评列表
com.vlives.member.insertComments=function(comments) {
	var commentsList = $('commentsUl');
	delAllElement(commentsList);
	for ( var i = 0; i < comments.length; i++) {
		var liElement = new Element('li');
		commentsList.adopt(liElement);
		var div = new Element(
				'div',
				{
					'class' : 'C_head',
					'html' : '<a title="点击进入商户主页" href="/merchant/'
							+ comments[i].merchant.id
							+ '/"><img src="'
							+ comments[i].merchant.headForWeb
							+ '" /></a><h1><a style="color:#000;" title="点击进入商户主页" href="/merchant/'
							+ comments[i].merchant.id + '/">'
							+ comments[i].merchant.shortName + '</a></h1>'
				});
		liElement.adopt(div);

		var currency = '元';
		if (comments[i].tradeDetail.tradeOrder.type.value == 3) {
			currency = '积分';
		}
		var type = comments[i].tradeDetail.tradeOrder.type.value;
		var type_DESC = comments[i].tradeDetail.tradeOrder.type._name;// :"TYPE_CASH
		var typeIcon;
		switch (parseInt(type)) {
		case 1:
			typeIcon = "/images/icon/dx.png";
			break;
		case 2:
			typeIcon = "/images/icon/xj.png";
			break;
		case 3:
			typeIcon = "/images/icon/jf.png";
			break;
		default:
			typeIcon = "/images/icon/dx.png";
			break;
		}

		var html = '<h1><img src="' + typeIcon
				+ '"/>到店<span>' + comments[i].tradeDetail.tradeOrder.type.desc
				+ '评价</span>,' + '发生于'
				+ comments[i].tradeDetail.tradeOrder.tradeDate + '<b>共消费'
				+ comments[i].tradeDetail.amount + currency + '</b></h1>'
				+ '<h2>' + comments[i].merchantGradeSrc + '</h2>'
				+ '<h3><div class="h_top"></div><div class="h_content"><p>'
				+ comments[i].comments
				+ '</p></div><div class="h_bottom"></div></h3>';
		if (comments[i].reply && comments[i].reply != 'null') {
			html = '<h1><img src="' + typeIcon
					+ '"/>到店<span>'
					+ comments[i].tradeDetail.tradeOrder.type.desc
					+ '评价</span>,' + '发生于'
					+ comments[i].tradeDetail.tradeOrder.tradeDate + '<b>共消费'
					+ comments[i].tradeDetail.amount + currency + '</b></h1>'
					+ '<h2>' + comments[i].merchantGradeSrc + '</h2>'
					+ '<h3><div class="h_top"></div><div class="h_content"><p>'
					+ comments[i].comments + '</p><b><strong>商家回复：</strong>'
					+ comments[i].reply
					+ '</b></div><div class="h_bottom"></div></h3>';
		}
		var commentDiv = new Element('div', {
			'class' : 'C_info',
			'html' : html
		});
		liElement.adopt(commentDiv);
	}
}

// 打开点评窗口
com.vlives.member.openCommentWin=function(typeIconID,typeID, typeDesc, typeDate, typeMoney) {
	var nowHtml = $("divComment");
	$("hidUnCommentId").value = typeID;
	var typeIcon;
	switch (parseInt(typeIconID)) {
	case 1:
		typeIcon = "/images/icon/dx.png";
		break;
	case 2:
		typeIcon = "/images/icon/xj.png";
		break;
	case 3:
		typeIcon = "/images/icon/jf.png";
		break;
	default:
		typeIcon = "/images/icon/dx.png";
		break;
	}
	$('imgurl').set('src',typeIcon);
	$('DescHtml').innerHTML = typeDesc;
	$('spnUnCommentDate').innerHTML = typeDate;
	$('spnUnCommentMoney').innerHTML = typeMoney;
	$('txtComment').value = "";
	$('txtComment').disabled = "";
	$('commentType').value = "1";
	com.vlives.base.show('submitComment');

	Mbox.open({
		url : nowHtml,
		openFrom : 'opencomment',
		title : '',
		useFx : false
	});
}

// 打开查看点评窗口
com.vlives.member.openShowCommentWin=function(typeIconID,typeID, typeDesc, typeDate, typeMoney, merchantGrade, commentContent) {
	var nowHtml = $("divComment");
	$("hidUnCommentId").value = typeID;
	var typeIcon;
	switch (parseInt(typeIconID)) {
	case 1:
		typeIcon = "/images/icon/dx.png";
		break;
	case 2:
		typeIcon = "/images/icon/xj.png";
		break;
	case 3:
		typeIcon = "/images/icon/jf.png";
		break;
	default:
		typeIcon = "/images/icon/dx.png";
		break;
	}
	$('imgurl').src=typeIcon;
	$('DescHtml').innerHTML = typeDesc;
	$('spnUnCommentDate').innerHTML = typeDate;
	$('spnUnCommentMoney').innerHTML = typeMoney;
	$('txtComment').value = commentContent;
	$('txtComment').disabled = "false";
	com.vlives.base.hide('submitComment');
	com.vlives.member.score(merchantGrade);

	Mbox.open({
		url : nowHtml
	});
}

// 打开修改点评窗口
com.vlives.member.openModifyCommentWin=function(typeIconID,typeID, typeDesc, typeDate, typeMoney, merchantGrade, commentContent) {
	var nowHtml = $("divComment");
	$("hidUnCommentId").value = typeID;
	var typeIcon;
	switch (parseInt(typeIconID)){
	case 1:
		typeIcon = "/images/icon/dx.png";
		break;
	case 2:
		typeIcon = "/images/icon/xj.png";
		break;
	case 3:
		typeIcon = "/images/icon/jf.png";
		break;
	default:
		typeIcon = "/images/icon/dx.png";
		break;
	}
	$('imgurl').src=typeIcon;
	$('DescHtml').innerHTML = typeDesc;
	$('spnUnCommentDate').innerHTML = typeDate;
	$('spnUnCommentMoney').innerHTML = typeMoney;
	$('txtComment').value = commentContent;
	$('txtComment').disabled = "";
	com.vlives.base.show('submitComment');
	$('commentType').value = "2";
	com.vlives.member.score(merchantGrade);

	Mbox.open({
		url : nowHtml
	});
}

// 打分
com.vlives.member.score=function (value) {
	$("hidScore").set("value", value);
	for ( var i = 1; i <= 5; i++) {
		$("score_" + i).set("src", "/images/icon/xxd1.png");
	}
	for ( var i = 1; i <= value; i++) {
		$("score_" + i).set("src", "/images/icon/xxd.png");
	}
}

// 提交点评
com.vlives.member.submitComment=function() {
	var id = $("hidUnCommentId").get("value");
	var content = $("txtComment").get("value");
	var score = $("hidScore").get("value");
	var type = $('commentType').value;
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
	params.push('merchantGrade=' + score);
	params.push('comments=' + content);

	var url;
	var method;
	if (type == "1") {
		params.push('detailId=' + id);
		url = '/user/ajax/merchant/comment';
		method = "post";
	} else {
		params.push('commentId=' + id);
		url = '/user/ajax/merchant/comment';
		method = "put";
	}
	var parameter = params.join('&');
	var req = new Request({
		url : url,
		method : method,
		async : false,
		evalScripts : true,
		onSuccess : function(responseText) {
			// alert(responseText);
			var objResponse = JSON.decode(responseText);
			if (objResponse.success == true) {
				Mbox.close();
				new POP.msgBox(objResponse.msg);
				com.vlives.member.getConsumesList(1);
				// member.getConsumesList(2);
				getUncommentedConsumeCount();
				// show('weiDIV');
			} else {
				new POP.msgBox(objResponse.msg);
			}
		},
		onFailure : function() {
			com.vlives.base.ajaxFailure();
		}
	}).send(parameter + "&rnd=" + com.vlives.base.RndNum(8));
}

// 我的积分
com.vlives.member.getPoints=function() {
	var mm = $("Pointspage");
	var points;
	new Boat.UI.Paging({
		url : '/user/ajax/points?rnd=' + com.vlives.base.RndNum(8),
		limit : 15,
		startParamName : "pagination.currentPage",
		limitParamName : "pagination.pageSize",
		total : 'pagination.count',
		head : {
			el : mm,
			showNumber : true,
			showText : false
		},
		onAfterLoad : function(data) {
			points = data.memberPoints;
			if (points.length>0) {
				com.vlives.member.insertPoints(points);
			}else{
				$('Pointspage').style.display="none";
				com.vlives.base.addNoResultDiv('pointsDiv','<div class="arreo"><span style="margin-left:15px;">当前没有积分记录</span></div>')
			}
		}
	}).load();
}
// 更新积分列表
com.vlives.member.insertPoints=function (points) {
	var Count = $('pointsTable').rows.length;
	for ( var i = Count - 1; i >= 1; i--) {
		$('pointsTable').deleteRow(i);
	}
	var rowCount = 1;
	for (i = 0; i < points.length; i++) {
		var tr = $('pointsTable').insertRow(rowCount + i);
		var td = tr.insertCell(0);
		if (points[i].createMerchant == null) {
			td.appendChild(document.createTextNode('未名商户'));
		} else {
			td.innerHTML = '<a title="点击进入商户主页" href="/merchant/'
					+ points[i].createMerchant.id + '/">'
					+ points[i].createMerchant.shortName + '</a>';
		}
		td = tr.insertCell(1);
		td.appendChild(document.createTextNode(points[i].level.desc));
		td = tr.insertCell(2);
		td.appendChild(document.createTextNode(points[i].point));
		td = tr.insertCell(3);
		td.appendChild(document.createTextNode(points[i].totalPoint));
	}
}

// 获取用户设置信息
com.vlives.member.updateUserSetting = function(setting) {
	// $('curUserID').value = setting.id==null?"1002":setting.id;
	$('nickname').value = setting.petName == null ? "" : setting.petName;
	$('name').value = setting.name == null ? "" : setting.name;
	$('gender').value = setting.gender == 'null' ? 0 : setting.gender;
	selValue('gender', setting.gender);
	$('birthday').value = setting.birthday == null ? "" : setting.birthday
			.substr(0, 10);
	$('email').value = setting.email == null ? "" : setting.email;
	$('cardType').value = setting.cardType == null ? 1 : setting.cardType.value;
	selValue('cardType', $('cardType').value);
	$('cardNumber').value = setting.cardNumber == null ? ""
			: setting.cardNumber;

	// 处理手机号码管理
	$("memberMobile_1").innerHTML = setting.mobile;
	
	// 关联账户信息
	if (setting.relationAccounts) {
		var sinaBinding = false;
		var qqBinding = false;
		var sinaAccount;
		var qqAccount;

		for (i = 0; i < setting.relationAccounts.length; i++) {
			if (setting.relationAccounts[i].accountType.value == 1) {
				// 新浪
				sinaBinding = true;
				for (j = 0; j < setting.relationAccounts[i].authenticationEntry.entryItems.length; j++) {
					if (setting.relationAccounts[i].authenticationEntry.entryItems[j].name == "userName") {
						sinaAccount = setting.relationAccounts[i].authenticationEntry.entryItems[j].value;
					}
				}
			}

			if (setting.relationAccounts[i].accountType.value == 2) {
				// QQ
			}
		}
		// 是否已经绑定
		if (sinaBinding) {
			var tips = "已经绑定新浪微博账号："+sinaAccount;
			$('sinaAccountTextDt').innerHTML = "<input class=\"qxbd\" name=\"\" title=\""+tips+"\" type=\"button\" onclick=\"com.vlives.member.cancleSinaBind();\" />";
			$('sinaAccountTextDt').title = tips;
			$('sinaAccountTextDt_1').title = tips;
		} else {
			$('sinaAccountTextDt').innerHTML = "<input class=\"bd\" type=\"button\" id=\"bindSinaButton\" onclick=\"com.vlives.member.bind();\"/>";$('sinaAccountTextDt').title = tips;
			$('sinaAccountTextDt').title = "";
			$('sinaAccountTextDt_1').title = "";
		}
	}
}

// 保存用户头像
com.vlives.member.uploadImage1=function() {
	var req = new Request({
		url :"/user/avatar",
		method : 'post',
		// evalScripts : true,
		headers:{'X-Requested-With': 'XMLHttpRequest',
				'Accept': 'text/javascrīpt, text/html, application/xml, text/xml, */*',
				'enctype':'multipart/form-data'},
		onSuccess : function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success == true) {
				new POP.msgBox(objResponse.head);
			} else {
				new POP.msgBox(objResponse.head);
			}
		},
		onFailure : function() {
			com.vlives.base.ajaxFailure();
		}
	}).send();
}

// 保存用户设置
com.vlives.member.saveInfo = function() {
	if ($chk($('email').value) && com.vlives.base.checkEmail($('email').value) == false){
		new POP.msgBox("邮箱格式错误，请输入正确的邮箱。");
		return false;
	}
	if ($('cardType').value == 1) {
		var cardNumber = $('cardNumber').value;
		if (com.vlives.base.isNumber(cardNumber) == false
				|| (com.vlives.base.getStringLength(cardNumber) != 15
				&& com.vlives.base.getStringLength(cardNumber) != 18)) {
			new POP.msgBox("身份证号码必须为15或者18位数字。");
			return false;
		}
	}

	var request = new Request.JSON({
		url : '/user/ajax/info',
		method : 'post',
		onComplete : function(res, jsonObj) {
			ret = JSON.decode(jsonObj);
			if (ret.success == false) {
				new POP.msgBox(ret.msg);
				return false;
			} else {
				var nickName = $('nickname').value;
				$('userName').set('text', nickName);
				new POP.msgBox('用户信息保存成功');
			}

		},
		onFailure : function() {
			com.vlives.base.ajaxFailure();
		}
	}).send($('settingform').toQueryString()
			+ "&rnd=" + com.vlives.base.RndNum(8));
}
// 新浪微博绑定
com.vlives.member.bind=function() {
	childWindow = window.open(
					"/include/weiboCall.jsp?opt=1",
					"WeiboLogin",
					"width=600,height=420,menubar=0,scrollbars=1, resizable=1,status=1,titlebar=0,toolbar=0,location=1");
}
// 新浪登录
com.vlives.member.sinaLogin_user=function(token, secret, sinaUserId, nickname, figureurl) {
	var params = [];
	// params.push('id=' + $('curUserID').value);
	params.push('mobile=' + '');
	params.push('verifyCode=' + '');
	params.push('accountType=' + '1');
	params.push('oauth_token=' + token);
	params.push('oauth_token_secret=' + secret);
	params.push('userName=' + nickname);
	params.push('userid=' + sinaUserId);
	params.push('openid=' + '');
	params.push('token=' + '');
	params.push('secret=' + '');

	var parameter = params.join('&');
	var req = new Request({
		url : '/user/ajax/sina',
		method : 'post',
		evalScripts : true,
		onSuccess : function(responseText) {
			var objResponse = JSON.decode(responseText);
			if (objResponse.success) {
				var tips = "已经绑定新浪微博账号："+nickname;
				$('sinaAccountTextDt').innerHTML = "<input class=\"qxbd\" name=\"\" title=\""+tips+"\" type=\"button\" onclick=\"com.vlives.member.cancleSinaBind();\" />";
				$('sinaAccountTextDt').title = tips;
				$('sinaAccountTextDt_1').title = tips;
			}else{
				new POP.msgBox(objResponse.msg); 
			}
		},
		onFailure : function() {
			alert('微博绑定失败！');
			com.vlives.base.ajaxFailure();
		}
	}).send(parameter);

}

// 取消新浪微博的绑定
com.vlives.member.cancleSinaBind = function(){
	var params = [];
	params.push('accountType=' + '1');
	var parameter = params.join('&');
	var req = new Request(
		{
			url:'/user/ajax/sina',
			method:'delete',
			evalScripts:true,
			onSuccess:function(responseText){
			$('sinaAccountTextDt').innerHTML = "<input class=\"bd\" type=\"button\" id=\"bindSinaButton\" onclick=\"com.vlives.member.bind();\"/>";
			$('sinaAccountTextDt').title = "";
			$('sinaAccountTextDt_1').title = "";
			},
			onFailure:function(){
				com.vlives.base.ajaxFailure();
			}
		}
	).send(parameter);
	
}

com.vlives.member.uploadAvatar = function() {
	var imgPath = $('importFile').value;
	if (imgPath == ""){
		$('debug').set('html','您还没有选择头像文件');
		return false;
	}
	if (!com.vlives.member.isImage(imgPath)){
		$('debug').set('html','暂不支持您选择的文件格式');
		return false;
	}
	
	return true;
}

// 根据文件后缀名判断是否上传文件是图片文件
com.vlives.member.isImage = function(filepath) {
	var imgEx = (filepath.substr(filepath.length - 5)).substr((filepath.substr(filepath.length - 5)).indexOf('.') + 1).toLowerCase();
	var limitImgEx = ".jpg,.bmp,.png,.jpeg,.gif";
	if (limitImgEx.indexOf(imgEx) < 0) {
		return false;
	} else {
		return true;
	}
	
	return true;
}
