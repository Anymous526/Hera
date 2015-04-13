/**
 * 百度地图的相关操作JS文档 创建时间：2011.07.5 创建人：张建辉
 */


// 定义自定义覆盖物的构造函数
function SquareOverlay(center, length, color, merchant){
	this._show = false;
	this._center = center;  
	this._length = length;  
	this._color = color;
	this._merchant = merchant;
}  

// 继承API的BMap.Overlay
SquareOverlay.prototype = new BMap.Overlay();  
// 实现初始化方法
SquareOverlay.prototype.initialize = function(map){  
	// 保存map对象实例
	this._map = map;
	this._map.addEventListener("zoomstart", function(e)
		{
		});

	// 创建div元素，作为自定义覆盖物的容器
	var div = document.createElement("div");
	div.style.position = "absolute";
	div.style.className="Boss";
	div.style.zindex="9999";
	div.id="messDiv";

	// 获取商户信息
	var favourPloy=this._merchant.favourPloy;
	var stateStr;
	if (favourPloy==true){
		stateStr="优惠中";
	} else {
		stateStr="未优惠";
	}
	var shopImage=this._merchant.headForWeb;
//	if (shopImage==null){
//		shopImage="/images/s_01.gif";
//	}
	var shopname=this._merchant.name;
	var shoptel=this._merchant.merchantTelephone;
	var shopaddress=this._merchant.businessAddress;
	var commentCount=this._merchant.merchantReferenceStatistic.commentCount;
	var favCount=this._merchant.merchantReferenceStatistic.favCount;
	var memberCount=this._merchant.memberGroup.memberCount;
	var averageGrade=this._merchant.merchantReferenceStatistic.averageGrade;
	div.innerHTML="<div class=\"B_zt\">状态：" + stateStr + "</div><div class=\"B_top\"></div><div class=\"B_content\"> <div class=\"B_close\" onclick=\"closeDIV('messDiv');\"></div><div class=\"B_txt\"><div class=\"B_img\"><img src=\"" + shopImage + "\" /></div><div class=\"B_Rtxt\"><h1>" + shopname + "</h1>"
	+"<h2>" + com.vlives.base.getLevelIcon(averageGrade) + "</h2>"+
	"<h2>联系电话：" + shoptel + "</h2>"+
	"<h3>商家地址：" + shopaddress + "</h3></div></div> </div><div class=\"B_info\"><ul><li class=\"dp\"><img src=\"/images/icon/dp.png\" />点评<a href=\"#\">" + commentCount + "</a></li><li><img src=\"/images/icon/sc.png\" />收藏<a href=\"#\">" + favCount + "</a></li><li><img src=\"/images/icon/hy.png\" />会员<a href=\"#\">" + memberCount + "</a></li></ul></div><div class=\"B_bottom\"></div>";
	
	// 可以根据参数设置元素外观
	div.style.width = this._length + "px";  
	div.style.height = this._length + "px";  
	// div.style.background = this._color;
	// 将div添加到覆盖物容器中
	map.getPanes().markerPane.appendChild(div);    
	// 保存div实例
	this._div = div;
	// 需要将div元素作为方法的返回值，当调用该覆盖物的show、
	// hide方法，或者对覆盖物进行移除时，API都将操作此元素。
	return div;
}

// 实现绘制方法
SquareOverlay.prototype.draw = function(){
	if (this._show) {
		return;
	} else {
		this._show = true;
	}

	// 根据地理坐标转换为像素坐标，并设置给容器
	 var bounds=this._map.getBounds();
	 var swPoint=bounds.getSouthWest();
	 var nePoint=bounds.getNorthEast();
	 var swPosition = this._map.pointToOverlayPixel(swPoint);
	 var nePosition = this._map.pointToOverlayPixel(nePoint);
	 var position = this._map.pointToOverlayPixel(this._center);  
	 this._div.style.left = position.x - 150 + "px";  
	 this._div.style.top = position.y - 230 + "px"; 

	 // 移动地图，显示弹出商户信息层
	 var newsw = new BMap.Pixel(position.x-150,position.y);
	 var newne = new BMap.Pixel(position.x+150,position.y-200);
	 var swpoint = this._map.overlayPixelToPoint(newsw);
	 var nepoint = this._map.overlayPixelToPoint(newne);
	 pointarr.push(swpoint);
	 pointarr.push(nepoint);
	 this._map.setViewport(pointarr);
}

// 实现显示方法
SquareOverlay.prototype.show = function(){  
	 if (this._div){  
		 this._div.style.display = "";  
	 }  
} 
// 获取Left值
SquareOverlay.prototype.left = function(){  
	 if (this._div){
		 return this._div.style.left;  
	 }  
}
// 获取Left值
SquareOverlay.prototype.top = function(){  
	if (this._div){  
		 return this._div.style.top;  
	}  
}

// 实现隐藏方法
SquareOverlay.prototype.hide = function(){  
	if (this._div){  
		 this._div.style.display = "none";  
	}  
}

// 添加自定义方法
SquareOverlay.prototype.toggle = function(){  
	if (this._div){  
		this._map.removeOverlay(this);
	}  
}