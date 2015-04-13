/**
 * 百度地图的相关操作JS文档 创建时间：2011.07.5 创建人：张建辉
 */
var map;
var marker;
var markerArr = [];
var infoWindow;
var mySquare;
var control;
var pointarr = [];
var smallmap;
function initialize(DIV) {
	map = new BMap.Map(DIV);

	var opts = {
		type : BMAP_NAVIGATION_CONTROL_SMALL,
		anchor : BMAP_ANCHOR_TOP_RIGHT
	}
	control = map.addControl(new BMap.NavigationControl(opts));

	var point = new BMap.Point(114.140432, 22.623607);
	// map.setCurrentCity("深圳"); // 设置地图显示的城市 此项是必须设置的
	map.centerAndZoom(point, 11);
	map.enableScrollWheelZoom(false);
	// 禁止键盘操作
	// map.disableKeyboard();
	// 禁止滚轮缩放
	map.disableScrollWheelZoom();
}
function initSearchMap(DIV) {
	smallmap = new BMap.Map(DIV);
	var point = new BMap.Point(114.140432, 22.623607);
	smallmap.setCurrentCity("深圳"); // 设置地图显示的城市 此项是必须设置的
	smallmap.centerAndZoom(point, 11);
	smallmap.enableScrollWheelZoom(false);
	smallmap.disableDragging();
	smallmap.disableScrollWheelZoom();
	smallmap.disableDoubleClickZoom();
	smallmap.disableKeyboard();
	smallmap.removeControl(control);
}
// 商家标注
function addShopMarker(lat, lng, type) {
	var point = new BMap.Point(lng, lat);
	var shoptype = type;
	if (shoptype > 4)
		shoptype = 5;
	switch (shoptype) {
	case 1:
		var label = new BMap.Label("<div class='pp01'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	case 2:
		var label = new BMap.Label("<div class='pp02'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	case 3:
		var label = new BMap.Label("<div class='pp03'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;

	case 4:
		var label = new BMap.Label("<div class='pp04'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	case 5:
		var label = new BMap.Label("<div class='pp05'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	default:
		var label = new BMap.Label("<div class='pp05'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	}
	label.setStyle({
		"border" : "none"
	});
	map.setCenter(point);
	map.setZoom(16);
	map.addOverlay(label);
}
// 编写自定义函数,创建标注
function addMarker(point, index, shoparr) {
	var className;
	var shoptype = 1;
	if (shoparr.merchant.category != null)
		shoptype = shoparr.merchant.category.id;
	if (shoptype > 4)
		shoptype = 5;
	switch (shoptype) {
	case 1:
		var label = new BMap.Label(
				"<div class='pp01'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 2:
		var label = new BMap.Label(
				"<div class='pp02'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 3:
		var label = new BMap.Label(
				"<div class='pp03'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;

	case 4:
		var label = new BMap.Label(
				"<div class='pp04'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 5:
		var label = new BMap.Label(
				"<div class='pp05'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	default:
		var label = new BMap.Label("<div class='pp05'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	}
	label.setStyle({
		"border" : "none"
	});
	map.addOverlay(label);
	label.addEventListener("click", function(e) {
		// 添加自定义覆盖物
		if (mySquare)
			map.removeOverlay(mySquare);
		mySquare = createShopMess(shoparr.merchant, point);
		// mySquare = new SquareOverlay(point, 100, "red", shoparr.merchant);
		map.addOverlay(mySquare);
		//mapSetWidth(point);
		//map.setCenter(point);
		UpdateListCSS(index);
	});
	markerArr.push(label);
}

// 编写自定义函数,创建标注
function searchAddMarker(point, index, shoparr) {
	var iconUrl;
	var shoptype = 1;
	if (shoparr.category != null)
		shoptype = shoparr.category.id;
	if (shoptype > 4)
		shoptype = 5;
	switch (shoptype) {
	case 1:
		var label = new BMap.Label(
				"<div class='pp01'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 2:
		var label = new BMap.Label(
				"<div class='pp02'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 3:
		var label = new BMap.Label(
				"<div class='pp03'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;

	case 4:
		var label = new BMap.Label(
				"<div class='pp04'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 5:
		var label = new BMap.Label(
				"<div class='pp05'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	default:
		var label = new BMap.Label("<div class='pp05'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	}
	label.setStyle({
		"border" : "none"
	});
	smallmap.addOverlay(label);
	markerArr.push(label);
}
// 编写自定义函数,创建享推荐标注
function addOtherMarker(point, index, shoparr) {
	var iconUrl;
	var shoptype = 1;
	if (shoparr.category != null)
		shoptype = shoparr.category.id;
	if (shoptype > 4)
		shoptype = 5;
	switch (shoptype) {
	case 1:
		var label = new BMap.Label(
				"<div class='pp01'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 2:
		var label = new BMap.Label(
				"<div class='pp02'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 3:
		var label = new BMap.Label(
				"<div class='pp03'><div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;

	case 4:
		var label = new BMap.Label(
				"<div class='pp04'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	case 5:
		var label = new BMap.Label(
				"<div class='pp05'></div>", {
					"offset" : new BMap.Size(-30, -60),
					"position" : point
				});
		break;
	default:
		var label = new BMap.Label("<div class='pp05'></div>", {
			"offset" : new BMap.Size(-30, -60),
			"position" : point
		});
		break;
	}
	label.setStyle({
		"border" : "none"
	});
	map.addOverlay(label);
	label.addEventListener("click", function(e) {
		// 添加自定义覆盖物
		// label.setContent("测试");
		if (mySquare)
			map.removeOverlay(mySquare);
		mySquare = createShopMess(shoparr, point);
		// mySquare = new SquareOverlay(point, 100, "red", shoparr);
		map.addOverlay(mySquare);
		//mapSetWidth(point);
		//map.setCenter(point);
		UpdateListCSS(index);
	});
	markerArr.push(label);
}

function getMess() {
	var pi = map.getCenter();
	var zoom = map.getZoom();
	$('testtxt').value = zoom + "," + pi.lng + "," + pi.lat;
}

// 向地图批量添加推荐标注
function markersOtherAdd(shops) {
	if (mySquare)
		map.removeOverlay(mySquare);
	map.clearOverlays();
	markerArr.length=0;
	pointarr.length = 0;
	var lng;
	var lat;
	for ( var i = 0; i < shops.length; i++) {
		lng = shops[i].longitude;
		lat = shops[i].latitude;
		if (lng != null && lat != null) {
			point = new BMap.Point(lng, lat);
			pointarr.push(point);
			addOtherMarker(point, i, shops[i]);
		}
	}
	map.setViewport(pointarr);
}

// 向地图批量添加标注
function markersAdd(shops) {
	if (mySquare)
		map.removeOverlay(mySquare);
	map.clearOverlays();
	markerArr.length=0;
	pointarr.length = 0;
	var lng;
	var lat;
	for ( var i = 0; i < shops.length; i++) {
		lng = shops[i].merchant.longitude;
		lat = shops[i].merchant.latitude;
		if (lng != null && lat != null) {
			point = new BMap.Point(lng, lat);
			pointarr.push(point);
			addMarker(point, i, shops[i]);
		}
	}
	map.setViewport(pointarr);
}
// 向地图批量添加标注
function searchMarkersAdd(shops) {
	if (mySquare)
		smallmap.removeOverlay(mySquare);
	smallmap.clearOverlays();
	markerArr.length=0;
	pointarr.length = 0;
	var lng;
	var lat;
	for ( var i = 0; i < shops.length; i++) {
		lng = shops[i].longitude;
		lat = shops[i].latitude;
		point = new BMap.Point(lng, lat);
		pointarr.push(point);
		searchAddMarker(point, i, shops[i]);
	}
	smallmap.setViewport(pointarr);
}
// 显示某个标注窗口
function showmarker(num) {
	var lng = curShopsArr[num - 1].merchant.longitude;
	var lat = curShopsArr[num - 1].merchant.latitude;
	var point = new BMap.Point(lng, lat);
	UpdateListCSS(num - 1);
	
	if (mySquare)
		map.removeOverlay(mySquare);
	mySquare = createShopMess(curShopsArr[num - 1].merchant, point);
	// mySquare = new SquareOverlay(point, 100, "red",
	// curShopsArr[num - 1].merchant);
	map.addOverlay(mySquare);
	//mapSetWidth(point);
	map.setCenter(point);
	// alert(markerArr[num-1].getPosition().toString);
	//alert(markerArr.length);
	for ( var i = 0; i < markerArr.length; i++) {
		if (i == num - 1) {
			markerArr[i].setZIndex(999);
		} else {
			markerArr[i].setZIndex(888);
		}
	}
	// pointarr.push(point);
	// map.setViewport(pointarr);
}
// 显示某个标注窗口
function showOthermarker(num) {
	var lng = curShopsArr[num - 1].longitude;
	var lat = curShopsArr[num - 1].latitude;
	var point = new BMap.Point(lng, lat);
	UpdateListCSS(num - 1);

	if (mySquare)
		map.removeOverlay(mySquare);
	mySquare = createShopMess(curShopsArr[num - 1], point);
	map.addOverlay(mySquare);
	//mapSetWidth(point);
	map.setCenter(point);
	//alert(markerArr.length);
	for ( var i = 0; i < markerArr.length; i++) {
		if (i == num - 1) {
			markerArr[i].setZIndex(999);
		} else {
			markerArr[i].setZIndex(888);
		}
	}

	// pointarr.push(point);
	// map.setViewport(pointarr);
}

function mapSetWidth(shopPoint) {
	// 移动地图，显示弹出商户信息层
	// var shopPointArr=[];
	// shopPointArr.push(shopPoint);
	var position = map.pointToOverlayPixel(shopPoint);
	var newsw = new BMap.Pixel(position.x - 150, position.y);
	var newne = new BMap.Pixel(position.x + 150, position.y - 260);
	var swpoint = map.overlayPixelToPoint(newsw);
	var nepoint = map.overlayPixelToPoint(newne);
	pointarr.push(swpoint);
	pointarr.push(nepoint);
	map.setViewport(pointarr);
}

// 创建一个商家信息框
function createShopMess(merchant, shoppoint) {
	var favourPloy = merchant.favourPloy;
	var stateStr;
	if (favourPloy == true) {
		stateStr = "优惠中";
	} else {
		stateStr = "未优惠";
	}
	var shopImage = merchant.headForWeb;
//	if (shopImage == null) {
//		shopImage = "/images/s_01.gif";
//	}
	var shopname = merchant.shortName;

	var shopid = merchant.id;
	var shoptel = merchant.merchantTelephone;
	if (shoptel == null || shoptel == 'null') {
		shoptel = "";
	}
	var lenShopTel = truncateString(shoptel, 13);
	var shopaddress = merchant.businessAddress;
	if (shopaddress == null || shopaddress == 'null') {
		shopaddress = "";
	}
	var commentCount = merchant.merchantReferenceStatistic.commentCount;
	var favCount = merchant.merchantReferenceStatistic.favCount;
	var memberCount = merchant.memberGroup.memberCount;
	var averageGrade = merchant.merchantReferenceStatistic.averageGrade;
	var html = "<div class=\"Boss\" id=\"messDiv\"><div class=\"B_zt\">状态："
			+ stateStr
			+ "</div><div class=\"B_top\"></div><div class=\"B_content\"> "
			+ "<div class=\"B_close\" onclick=\"closeDIV('messDiv');\"></div>"
			+ "<div class=\"B_txt\"><div class=\"B_img\"><a href=\"/merchant/"
			+ shopid
			+ "/\" title=\"点击进入商户页面\">"
			+ "<img src=\""
			+ shopImage
			+ "\" /></a></div><div class=\"B_Rtxt\"><h1>"
			+ "<a href=\"/merchant/"
			+ shopid
			+ "/\" title=\"点击进入商户页面\">"
			+ shopname
			+ "</a></h1>"
			+ "<h2>"
			+ com.vlives.base.getLevelIcon(averageGrade)
			+ "</h2>"
			+ "<h2>联系电话：<span title='"
			+ shoptel
			+ "'>"
			+ lenShopTel
			+ "</span></h2>"
			+ "<span'>商家地址："
			+ shopaddress
			+ "</span></div></div> </div><div class=\"B_bottom\"></div></div>";
	mySquare = new BMap.Label(html, {
		"offset" : new BMap.Size(-150, -200),
		"position" : shoppoint
	});
	mySquare.setZIndex(99999);
	mySquare.setStyle({
		"border" : "none"
	});
	return mySquare;
}

// 更新右侧商家列表的样式

function UpdateListCSS(index) {
	var parent = $('rightlist');
	if (parent) {
		var childs = $('rightlist').getChildren();
		childs.each(function(item, index) {
			item.removeClass("now");
		});
		childs[index].addClass("now");
	}
}