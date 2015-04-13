var POP = POP ? POP : {};
POP.DIR = "/manager/js/pop/";
/**
 * 弹出层
 * events:(事件)
 * beforeCreate:创建层之前出发
 * afterCreate：创建层之后触发
 * close:关闭弹出层时触发
 * closeComplete:关闭弹出层后出发
 */
POP.showBox = new Class({
	Implements: [Events, Options],
	options: {
		quickClose:true,		//是否是快速关闭
		fillType:"dom",			//填充内容类型："dom"表示模型,"html"表示html元素
		mask:true,				//是否带有遮罩效果
		positionType:"auto",	//弹出层定位方式:"auto"表示以浏览器作为相对定位，定位置以'leftPer'和'topPer'为准；"abs"表示绝对定位
		leftPer:0.5,	//弹出层靠左位置（0和1之间：0表示最左边，1表示最右边,默认为0.5既居中）,当'positionType'=auto才有效
		topPer:0.5,		//弹出层靠上位置（0和1之间：0表示最上边，1表示最下边,默认为0.5既居中）,当'positionType'=auto才有效
		left:0,			//弹出层靠左位置(绝对定位)当'positionType'=abs才有效
		top:0,			//弹出层靠上位置(绝对定位)当'positionType'=abs才有效
		drag:false,		//是否可拖拽
		opacity:50      //设置遮罩的不透明性质0-100
    },
    initialize:function(id,content,width,height,options) {
    	this.id = id+"_mesWindow";
    	this.content = content;
    	this.width = width;
    	this.height = height;
		this.setOptions(options); 
		this.createBox();
	},
	 
	createBox:function() {
		if(this.checkBoxExist())return;
		this.fireEvent("beforeCreate");
		var bWidth=parseInt(document.body.scrollWidth); 
		var bHeight=parseInt(document.body.scrollHeight); 
		 
		var bodyDom = $$("body")[0];
		if(this.options.mask)
			this.createBackDiv(bWidth,bHeight,bodyDom);
		this.createMessageDiv(bWidth,bodyDom);
		this.createDrag();
		this.fireEvent("afterCreate");
	},
	checkBoxExist:function() {
		var existBox = $(this.id);
		if(!$chk(existBox)){
			return false;
		}
		existBox.shake();
		var oldPopWindow = existBox.retrieve("popwindow");
		oldPopWindow.focuse();
		return true;
	},
	createDrag:function() {
		if(!this.options.drag)return;
		var dropElements = $$("body");
		var dragContainer = $$("html")[0];
		this.dragMove = new Drag.Move(this.messageBox , {
			"droppables":dropElements 
			//"container": dragContainer	
		});
	},
	/**
	 * 设置z-index：目的保证当前的弹出层置顶
	 */
	focuse:function() {
		if(this.messageBox.hasClass("isFocused")) return;
		var boxs = $$("div[id$=_mesWindow]");
		var maxZIndexBox = null;
		boxs.each(function(box) {
			if(box.hasClass("isFocused")) {
				box.removeClass("isFocused");
			}
			if(!$chk(maxZIndexBox)){
				maxZIndexBox = box;
			}
			else {
				if(maxZIndexBox.getStyle("z-index").toInt()<box.getStyle("z-index").toInt()) {
					maxZIndexBox = box;
				}
			}
		});
		var z_index = maxZIndexBox.getStyle("z-index");
		z_index = !$chk(z_index)||z_index=="auto"?0:z_index;
		z_index = z_index.toInt();
		this.messageBox.setStyle("z-index",z_index+2);
		this.messageBox.addClass("isFocused");
	},
	
	/**
	 * 创建遮罩背景
	 * @param {} bWidth
	 * @param {} bHeight
	 * @param {} bodyDom
	 */
	createBackDiv : function(bWidth,bHeight,bodyDom) {
		var styleStr="top:0px;left:0px;position:absolute;width:"+bWidth+"px;height:"+bHeight+"px;";
		styleStr+=(Browser.Engine.trident)?"filter:alpha(opacity=0);":"opacity:0;";
		var backDiv = new Element("div",{id:"back",style:styleStr});
		backDiv.inject(bodyDom);
		 
		backDiv.setStyle("background","#F2F2F2");
		POP.showBackground(backDiv,this.options.opacity);
		 
	},
	/**
	 * 创建弹出层
	 * @param {} bWidth
	 * @param {} bodyDom
	 */
	createMessageDiv : function(bWidth,bodyDom) {
		 var left = this.calLeft(bWidth);
		 var top = this.calTop();
		this.messageBox = new Element("div",{id:this.id,"class":"popBox",style:"left:"+left+"px;top:"+top+"px;position:absolute;width:"+this.width+"px;"});
		if(this.options.fillType=="dom") {
			this.content.inject(this.messageBox);
		}
		else {
			this.messageBox.set("html",this.content);
		}
		this.messageBox.inject(bodyDom);
		this.focuse();
		this.messageBox.addEvent("mousedown",function() {
			this.focuse();
		}.bind(this));
		this.messageBox.store("popwindow",this);
	},
	/**
	 * 计算左边距
	 * @return {}
	 */
	calLeft:function(bWidth) {
		var left=0;
		if(this.options.positionType=="auto") {
			var halfWidth = (this.width/2).toInt();
			left = bWidth*this.options.leftPer-halfWidth;
		}
		else {
			left = this.options.left; 
		}
		left=left<0?0:left;
		left=left+this.width>bWidth?bWidth-this.width:left;
		return left;
	},
	/**
	 * 计算上边距
	 * @return {}
	 */
	calTop:function() {
		var top=0;
		var clientHeight = document.compatMode=="CSS1Compat"?document.documentElement.clientHeight:document.body.clientHeight;
		if(this.options.positionType=="auto") {
			var halfHeight = (this.height/2).toInt();
		 	top = clientHeight*this.options.topPer+this.getScrollPos()-halfHeight;
		 	//console.log(clientHeight,this.getScrollPos(),halfHeight,top,this.height);
		}
		else {
			top = this.options.top; 
		}
		top=top<0?0:top;
		top=top-this.getScrollPos()+this.height>clientHeight?clientHeight-this.height+this.getScrollPos():top;
		//console.log(top);
		return top;
	},
	/**
	     * 在页面中加入<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	     * 之后 document.body.scrollTop 都为0
	     * 故取其top应用以下方法
	     * @type 
	     */
	getScrollPos : function (){
	    var scrollPos; 
	    if (typeof window.pageYOffset != 'undefined') { 
	       scrollPos = window.pageYOffset; 
	    } 
	    else if (typeof document.compatMode != 'undefined' && 
	         document.compatMode != 'BackCompat') { 
	       scrollPos = document.documentElement.scrollTop; 
	    } 
	    else if (typeof document.body != 'undefined') { 
	       scrollPos = document.body.scrollTop; 
	    } 
	    return scrollPos;
	},
	/**
	 * 关闭box
	 */
	close:function() {
		this.fireEvent("close");
	 
		if($chk(this.dragMove)) {
			this.dragMove.detach();
		}
		var backDiv = $("back");
		if($chk(backDiv)) {
			backDiv.dispose();
		}
		var messageDiv = $(this.id);
		if($chk(messageDiv)) {
			var fx  = new Fx.Tween(messageDiv, {
					'duration' : this.options.quickClose?0:1000,
					'ignore' : true,
					'onComplete' : function() {
						if (messageDiv.getStyle('opacity').toInt() == 0) {
							messageDiv.destroy();
							this.fireEvent("closeComplete");
						}
					}.bind(this)
				});
			fx.start('opacity', 0);
		}
	}
});
/**
 * 静态方法关闭box
 */
POP.closeBox = function() {
	var popBoxs = $$("div[class^=popBox]");
	if(popBoxs.length ==0) return;
	popBoxs.each(function(box) {
		if(box.hasClass("isFocused")) {
			var oldPopWindow = box.retrieve("popwindow");
			oldPopWindow.close();
		}
	});
}
/**
 * 显示背景色
 * @param {} obj
 * @param {} endInt
 */
POP.showBackground = function(obj,endInt) {
	if(Browser.Engine.trident) { 
		obj.filters.alpha.opacity+=1; 
		if(obj.filters.alpha.opacity<endInt){ 
			setTimeout(function(){POP.showBackground(obj,endInt)},5); 
		} 
	}else{ 
		var al=parseFloat(obj.style.opacity);
		if(endInt>0) al+=0.2;
		 obj.style.opacity=al;
        if(al<(endInt/100)) {setTimeout(function(){POP.showBackground(obj,endInt)},5);}
	} 
}
/**
 * 弹出对话框
 * @param {} html
 * @param {} options{autoClose:是否自动关闭对话框}
 */
POP.alert = function(html,options) {
	new POP.msgBox(html,options);
}

/**
 * 确认对话框
 * @param {} html
 * @param {} options
 * 有两个会点方法
 * onConfirmTrue:表示确认时的回调方法(点击确认时代码写这里面)
 * onConfirmFalse:表示取消时的回调方法(点击取消时代码写这里面)
 */
POP.confirm = function(html,options) {
	var inOptions = {};
	inOptions.confirm=true,
	inOptions.onConfirmTrue = options.onConfirmTrue;
	inOptions.onConfirmFalse = options.onConfirmFalse;
	new POP.msgBox(html,inOptions);
}

POP.msgBox = new Class({
	Implements: [Events, Options],
	options: {
		event:null,
		target:null,
		title:"操作提示",
		confirmClose:true,
		drag:true,
		autoClose:false, //是否制动关闭
		confirm:false,	//是否是确认对话框
		onConfirmTrue:$empty,//确认对话框中点击取消时的回调函数
		onConfirmFalse:$empty,//确认对话框中点击取消时的回调函数
		onCloseComplete:$empty
    },
    initialize:function(content,options) {
    	this.id = $random(0, 9999);
    	this.content = content;
    	this.setOptions(options);
    	this.includeStyle();
    	this.createMsgDom();
    	 
	},
	includeStyle:function() {
		var head = document.getElementsByTagName("head")[0];
		head = $(head);
		var cssStyle = head.getElement("link[href$=pop.css]");
		if($chk(cssStyle)) return;
		cssStyle = new Element("link",{"type":"text/css","rel":"stylesheet","href":POP.DIR+"css/pop.css"});
		cssStyle.inject(head,"top");
	},
	createMsgDom:function() {
		var dom = new Element("div",{"class":"drift"});
		var header = new Element("div",{"class":"d_title"});
		header.inject(dom);
		
		var titleSpan = new Element("span",{"html":this.options.title});
		titleSpan.inject(header);
		
		var closeBtn = new Element("img",{"src":POP.DIR+"img/close.gif","width":18,"height":17,"alt":"关闭"});
		closeBtn.inject(header);
		var body = new Element("div",{"class":"d_content","html":this.content});
		body.inject(dom);
		var showBox = null;
		if($chk(this.options.event)) {
			var event = new Event(this.options.event);
			showBox = new POP.showBox(this.id,dom,300,100,
				{mask:true,drag:this.options.drag,opacity:0,positionType:"abs",
				left:event.page.x+10,top:event.page.y-10,
				onCloseComplete:function(){
						this.fireEvent("closeComplete");
					}.bind(this)
				});
		}
		else if($chk(this.options.target)) {
			var targetDom=$(this.options.target);
			var size = targetDom.getCoordinates();
			if(size.left>0&&size.top>0) {
				showBox = new POP.showBox(this.id,dom,300,100,
					{mask:true,drag:this.options.drag,opacity:0,positionType:"abs",
					left:size.left+size.width+10,top:size.top+size.height-10,
					onCloseComplete:function(){
						this.fireEvent("closeComplete");
					}.bind(this)}
				);
			}
		}
		else {
			showBox = new POP.showBox(this.id,dom,300,100,
				{mask:true,drag:this.options.drag,opacity:0,
				onCloseComplete:function(){
					this.fireEvent("closeComplete");
				}.bind(this)
			});
		}
		closeBtn.addEvent("click",function(){showBox.close();});
		
		if(this.options.autoClose) {
			setTimeout(function(){showBox.close()},1000); 
		}
		this.createConfirmDom(dom,showBox);
	},
	createConfirmDom:function(dom,showBox) {
		if(this.options.confirm) {
			var footer = new Element("div",{"class":"d_footer"});
			footer.inject(dom);
			var confirmBtn = new Element("img",{"src":POP.DIR+"img/confirm.gif","width":46,"height":22,"alt":"确定"});
			confirmBtn.addEvent("click",function() {
				if(this.options.confirmClose) {
					showBox.close();
				}
				this.fireEvent('confirmTrue');
			}.bind(this));
			confirmBtn.inject(footer);
			var span = new Element("span",{"html":"&nbsp;&nbsp;&nbsp;"});
			span.inject(footer);
			var cancelBtn = new Element("img",{"src":POP.DIR+"img/cancel.gif","width":46,"height":22,"alt":"取消"});
			cancelBtn.addEvent("click",function() {
			 	showBox.close();
				this.fireEvent('confirmFalse');
			}.bind(this));
			cancelBtn.inject(footer);
		}
	}
	
});

