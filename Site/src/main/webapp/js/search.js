/**
 * 搜索列表页面DOM操作JS文档 创建时间：2011.07.5 创建人：张建辉
 */
var curShopsArr = [];
var curComments;
var curSearchState = 1;
window.addEvent("domready", function() {
	if (!smallmap)
		initSearchMap("smallMap");
	// 单击搜索框时清空搜索框内容
	$('name').addEvent("click", function() {
		if($('name').value == "搜索你感兴趣的商家或地点... ..."){
			$('name').value = "";
		}
		$('name').focus();
	});
	// 检测当单击搜索按钮后搜索框是否填写内容
	$('searchsubmit').addEvent("click", function() {
		if ($('name').value == "" || $('name').value == "搜索你感兴趣的商家或地点... ...") {
			new POP.msgBox("请输入您感兴趣的商家或地点");
			$('name').value = "搜索你感兴趣的商家或地点... ...";
			return false;
		} else {
			pageSearch("/ajax/search", 1);
			// ajaxGetSearchData("/ajax/search",1);
		}
	});

	if($('searchArea').value != "" || $('category').value != ""){
			$('name').value="";
			pageSearch("/ajax/search", 3);
	} else if ($('name').value != "" || $('name').value != "搜索你感兴趣的商家或地点... ...") {
		// 此处进行Ajax搜索动作
		pageSearch("/ajax/search", 1);
		// ajaxGetSearchData("/ajax/search",1);
	}

	// 右侧列表的下拉和收起列表事件--商家类型
	$('shopclick').addEvent("click", function() {
		if ($('shoptype').style.display == "none") {
			$('shoptype').style.display = "";
			$('shopclick').className = "Sj_title";
		} else {
			$('shoptype').style.display = "none";
			$('shopclick').className = "Sj_title01";
		}
	});
	// 右侧列表的下拉和收起列表事件--商家位置
	$('shopPosition').addEvent("click", function() {
		if ($('positionList').style.display == "none") {
			$('positionList').style.display = "";
			$('shopPosition').className = "Sj_title";
		} else {
			$('positionList').style.display = "none";
			$('shopPosition').className = "Sj_title01";
		}
	});
	// 右侧列表的下拉和收起列表事件--商家优惠
	$('shopsale').addEvent("click", function() {
		if ($('salelist').style.display == "none") {
			$('salelist').style.display = "";
			$('shopsale').className = "Sj_title";
		} else {
			$('salelist').style.display = "none";
			$('shopsale').className = "Sj_title01";
		}
	});
	// 商家结果排序方式选择
	$('Px_dq').addEvent("click", function() {
		sortList();
	});
	// 商家结果排序方式选择
	$('Px_more').addEvent("click", function() {
		sortList();
	});
	// 地图模式切换
	$('mapmodel').addEvent("click", function() {
		mapModel();
	});
	// 列表模式切换
	$('listmodel').addEvent("click", function() {
		listModel();
	});
});

// 添加商家类型列表
function addTypeLiElement(divname, typename, id, shopnum, addtype) {
	var parent = $(divname);
	var newLi = new Element('li');
	newLi.set('html', '<b><input onclick="ajaxGet();" name="' + addtype
			+ '" type="checkbox" value="' + id + '" />' + typename
			+ '</b> <span>' + shopnum + '</span>');
	parent.adopt(newLi);

}
function ajaxGet() {
	pageSearch("/ajax/search", 2);
	// ajaxGetSearchData("/ajax/search",2);
}
// 添加左侧商家列表信息
function addShopLiElement(num, imgurl, shopid, shopname, shopaddress,
		averageGrade, membercount, commentCount, favCount) {
	var parent = $('shopLists');
	var newLi = new Element('li');
	parent.adopt(newLi);
	var myFirstElement = new Element('div', {
		'class' : 'number',
		'html' : num
	});
	newLi.adopt(myFirstElement);
	var mySecondElement = new Element('div', {
		'class' : 'txt_img'
	});

	mySecondElement.set('html','<a href="/merchant/' + shopid + '/" title="点击进入商户页面"><img src="' + imgurl + '"/></a>');
	newLi.adopt(mySecondElement);
	var myThirdElement = new Element('div', {
		'class' : 'txt_txt'
	});
	myThirdElement.setStyle('width', '455px');
	myThirdElement.set('html', '<h1><a href="/merchant/' + shopid
			+ '/" title="点击进入商户页面">' + shopname + '</a>' + com.vlives.base.getLevelIcon(averageGrade)
			+ '</h1>' + '<h2>' + shopaddress + '</h2> '
			+ '<h3><dl><dd class="Green">' + commentCount
			+ '<br /><span>点评</span></dd>' + '<dd class="Blue">' + membercount
			+ '<br /><span>会员</span></dd>' + '<dd class="Yellow">' + favCount
			+ '<br /><span>收藏</span></dd>' + '</dl></h3> ');
	newLi.adopt(myThirdElement);

}

// 排序方式下拉
function sortList() {
	if ($('Px_content').style.display == "none") {
		$('Px_content').style.display = "";
		$('Px_more').className = "Px_more01";
	} else {
		$('Px_content').style.display = "none";
		$('Px_more').className = "Px_more";
	}
}

// 按推荐排行
function sortByRecommends() {
	$('Px_dq').innerHTML = "按推荐排行";
	sortList();
	$('sort').value = "sales";
	pageSearch("/ajax/search", 1);
}

// 按点评排行
function sortByComments() {
	$('Px_dq').innerHTML = "按点评排行";
	sortList();
	$('sort').value = "commentcount";
	pageSearch("/ajax/search", 1);
	// ajaxGetSearchData("/ajax/search",2);
}

// 按收藏排行
function sortByFavorites() {
	$('Px_dq').innerHTML = "按收藏排行";
	sortList();
	$('sort').value = "favcount";
	pageSearch("/ajax/search", 1);
	// ajaxGetSearchData("/ajax/search",2);
}
// 按会员排行
function sortByMembers() {
	$('Px_dq').innerHTML = "按会员排行";
	sortList();
	$('sort').value = "membercount";
	pageSearch("/ajax/search", 1);
	// ajaxGetSearchData("/ajax/search",2);
}

// 地图模式切换
function mapModel() {
	$('mapmodel').className = 'now01';
	$('listmodel').className = 'old';
	$('maplist_top').style.display = "";
	$('maplist_buttom').style.display = "";
	$('bigMap').style.display = "";
	$('maplist_right').style.display = "";
	$('list_top').style.display = "none";
	$('shop_List').style.display = "none";
	$('list_map').style.display = "none";
	$('rightInfo').setStyle('margin-top','-90px');
	curSearchState = 2;
	if (!map)
		initialize("bigMap");
	markersOtherAdd(curShopsArr);
}

// 列表模式切换
function listModel() {
	$('mapmodel').className = 'old01';
	$('listmodel').className = 'now';
	$('maplist_top').style.display = "none";
	$('maplist_buttom').style.display = "none";
	$('bigMap').style.display = "none";
	$('maplist_right').style.display = "none";
	$('list_top').style.display = "";
	$('shop_List').style.display = "";
	$('list_map').style.display = "";
	$('rightInfo').setStyle('margin-top','-44px');
	curSearchState = 1;
}
// 更新右侧列表
function updateRightList(comments) {
	// 更新搜索获得的商家类型列表
	var parent = $('tList');
	delAllElement(parent);
	for ( var i = 0; i < comments.categories.length; i++) {
		addTypeLiElement('tList', comments.categories[i].category.name,
				comments.categories[i].category.id,
				comments.categories[i].merchantCount, 'category');
	}
	addClearDiv('tList');

	// 更新搜索获得的商家地区列表
	var parent = $('pList');
	delAllElement(parent);
	for ( var i = 0; i < comments.areas.length; i++) {
		addTypeLiElement('pList', comments.areas[i].area.name,
				comments.areas[i].area.id, comments.areas[i].merchantCount,
				'area');
	}
	addClearDiv('pList');
}
// ajax获取指定地址信息
function ajaxGetSearchData(url, type) {
	var comments;
	var datastr;
	// 加载地图
	curShopsArr.length = 0;
	var parent = $('shopLists');
	delAllElement(parent);
	if (curSearchState == 1) {
		addDelayDiv('shopLists', 'delayDIV');
		addDelayDiv('smallMap', 'mapdelay');
	} else if (curSearchState == 2) {
		addDelayDiv('bigMap', 'delayDIV');
	}
	datastr = $('searchform').toQueryString() + '&rnd=' + RndNum(8);
	// alert(datastr);
	var request = new Request.JSON(
			{
				url : url,
				method : 'get',
				onComplete : function(res, jsonObj) {
					comments = JSON.decode(jsonObj);
					if (comments == null || comments.merchants.length <= 0) {
						//new POP.msgBox("无法获得数据，请换一个关键词搜索，或者联系会生活客服。");
						if (curSearchState == 1) {
							com.vlives.base.deleteDiv('delayDiv');
							com.vlives.base.deleteDiv('mapdelay');
						} else {
							com.vlives.base.deleteDiv('delayDiv');
						}
						return false;
					}
					// 更新商家列表
					for ( var i = 0; i < comments.merchants.length; i++) {
						var num = i + 1;
						var imgurl = comments.merchants[i].headForWeb;
//						if (imgurl == null) {
//							imgurl = "/images/s_01.gif";
//						}
						var shopid = comments.merchants[i].id;
						var shopname = comments.merchants[i].shortName;
						var shopaddress = comments.merchants[i].businessAddress;
						var membercount = comments.merchants[i].memberGroup.memberCount;
						var commentCount = comments.merchants[i].merchantReferenceStatistic.commentCount;
						var favCount = comments.merchants[i].merchantReferenceStatistic.favCount;
						var averageGrade = comments.merchants[i].merchantReferenceStatistic.averageGrade
						addShopLiElement(num, imgurl, shopid, shopname,
								shopaddress, averageGrade, membercount,
								commentCount, favCount);
					}
					addClearDiv('shop_List');
					if (curSearchState == 1) {
						com.vlives.base.deleteDiv('delayDiv');
						com.vlives.base.deleteDiv('mapdelay');
					} else {
						com.vlives.base.deleteDiv('delayDiv');
					}
					curComments = comments;
					curShopsArr = comments.merchants;
					searchMarkersAdd(comments.merchants);
					if (curShopsArr.length > 0 && type == 1) {
						updateRightList(curComments);
					}
					if (map)
						markersOtherAdd(curShopsArr);
				},
				data : datastr
			}).send();
}

var pageSearch = function(url, type) {
	var mm = $("searchpage");
	var datastr = $('searchform').toQueryString();
	url += "?" + datastr + "&rnd=" + RndNum(8);
	var comments;
	// 加载地图
	curShopsArr.length = 0;
	if (curSearchState == 1||curSearchState == 3) {
		addDelayDiv('shopLists', 'delayDIV');
		addDelayDiv('smallMap', 'mapdelay');
	} else if (curSearchState == 2) {
		//addDelayDiv('bigMap', 'delayDIV');
	}
	$('Res_no').style.display="none";
	new Boat.UI.Paging(
			{
				url : url,
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
					comments = data;
					var parent = $('shopLists');
					delAllElement(parent);
					if (curSearchState == 1||curSearchState == 3) {
						com.vlives.base.deleteDiv('delayDiv');
						com.vlives.base.deleteDiv('mapdelay');
					}
					if (comments == null || comments.merchants.length <= 0) {
						//new POP.msgBox("无法获得数据，请换一个关键词搜索，或者联系会生活客服。");
						$('Res_no').style.display="";
						$("searchpage").style.display="none";
						return false;
					}
					// 更新商家列表
					for ( var i = 0; i < comments.merchants.length; i++) {
						var num = i + 1;
						var imgurl = comments.merchants[i].headForWeb;
//						if (imgurl == null) {
//							imgurl = "/images/s_01.gif";
//						}
						var shopid = comments.merchants[i].id;
						var shopname = comments.merchants[i].shortName;
						var shopaddress = comments.merchants[i].businessAddress;
						var membercount = comments.merchants[i].memberGroup.memberCount;
						var commentCount = comments.merchants[i].merchantReferenceStatistic.commentCount;
						var favCount = comments.merchants[i].merchantReferenceStatistic.favCount;
						var averageGrade = comments.merchants[i].merchantReferenceStatistic.averageGrade
						addShopLiElement(num, imgurl, shopid, shopname,
								shopaddress, averageGrade, membercount,
								commentCount, favCount);
					}
					addClearDiv('shop_List');
					if (curSearchState == 1) {
						com.vlives.base.deleteDiv('delayDiv');
						com.vlives.base.deleteDiv('mapdelay');
					}
					curComments = comments;
					curShopsArr = comments.merchants;
					searchMarkersAdd(comments.merchants);
					if (curShopsArr.length > 0 && (type == 1||type == 3)) {
						updateRightList(curComments);
					}
					if (map)
						markersOtherAdd(curShopsArr);
				}
			}).load();
}
