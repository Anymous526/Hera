
	var com = com ? com : {};

/**
 * 获得鼠标的点击位置
 * @param {} ev
 * @return {}
 */
mousePosition = function(ev) {
	ev = ev || window.event;
	if (ev.pageX || ev.pageY) {
		return {
			x : ev.pageX,
			y : ev.pageY
		};
	}
	return {
		x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}

/**
元素振动
Shake effect by Uvumi Tools
http://tools.uvumi.com/element-shake.html

Function: shake
Param: 
	radius :振动的半径 默认3px
	duration:持续的时间(单位:毫秒),默认500
Example:
	Shake a window.
	(start code)
	$('parametrics').shake()
	(end)
*/
 
Element.implement({
	shake: function(radius,duration){
		radius = radius || 3;
		duration = duration || 500;
		duration = (duration/50).toInt() - 1;
		var parent = this.getParent();
		if(parent != $(document.body) && parent.getStyle('position') == 'static'){
			parent.setStyle('position','relative');
		}
		var position = this.getStyle('position');
		if(position == 'static'){
			this.setStyle('position','relative');
			position = 'relative';
		}
		if(Browser.Engine.trident){
			parent.setStyle('height',parent.getStyle('height'));
		}
		var coords = this.getPosition(parent);
		if(position == 'relative' && !Browser.Engine.presto){
			coords.x -= parent.getStyle('paddingLeft').toInt();
			coords.y -= parent.getStyle('paddingTop').toInt();
		}
		var morph = this.retrieve('morph');
		if (morph){
			morph.cancel();
			var oldOptions = morph.options;
		}
		var morph = this.get('morph',{
			duration:50,
			link:'chain'
		});
		for(var i=0 ; i < duration ; i++){
			morph.start({
				top:coords.y+$random(-radius,radius),
				left:coords.x+$random(-radius,radius)
			});
		}
		morph.start({
			top:coords.y,
			left:coords.x
		}).chain(function(){
			if(oldOptions){
				this.set('morph',oldOptions);
			}
		}.bind(this));
		return this;
	}
});
/**
 * 输入框获得或失去焦点时值得变化
 * 同时失去焦点时在该元素put("isDefaultVlaue")，如果检查时输入框是默认值则设置true，否则设置false
 * 在其它地方获得该元素是否是默认值采用retrieve("isDefaultVlaue")方法
 * @param {} id
 * @param {} defaultValue
 */
com.focusChangeDefault = function(id,defaultValue) {
	defaultValue = defaultValue.trim();
	var el = $(id);
	el.set("value",defaultValue);
	
	el.store("isDefaultVlaue",true);
	el.addEvent("focus",function() {
		var value = this.get("value");
		if(!$chk(value)) {
			return;
		}
		if($chk(defaultValue)&&value.trim()==defaultValue) {
			this.erase("value");
		}
	});
	 el.addEvent("blur",function() {

	 	var value = this.value;
		if(!$chk(value)) {
			this.set("value",defaultValue);
		}
		this.store("isDefaultVlaue",this.get("value").trim()==defaultValue?true:false);
	});
	el.addEvent("keydown",function(event) {
	 	if(event.key=='enter') {
			this.store("isDefaultVlaue",this.get("value").trim()==defaultValue?true:false);
		}
	});
	
}

com.TmeEnum=["01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00"
,"13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"];
/**
 * 加载时间菜单
 * @param {} id select菜单的id编号
 * @param {} config {useNullOp:true,defaultSelected:null}
 */
com.loadTimeMenu= function(id,config) {
	this.config = {useNullOp:true,defaultSelected:null};
	if($chk(config)) {
		this.config.useNullOp = $defined(config.useNullOp)?config.useNullOp:this.config.useNullOp;
		this.config.defaultSelected = $chk(config.defaultSelected)?config.defaultSelected:this.config.defaultSelected;
	}
	var select = $(id);
	
	if(this.config.useNullOp) {
		var nullOp = new Element("option",{"text":""});
		nullOp.set("selected","selected");
		nullOp.inject(select,"bottom");
	}
	
	com.TmeEnum.each(function(time){
		var option = new Element("option",{"value":time,"text":time});
		if($chk(this.config.defaultSelected)&&this.config.defaultSelected==time) {
			option.set("selected","selected");
		}
		option.inject(select,"bottom");
	}.bind(this));
	
}

/**
 * 加载年菜单
 */
com.loadYearMenu= function(id) {
	var select = $(id);
	for(var year = 1970;year<=1995;year++) {
		var option = new Element("option",{"value":year,"text":year});
		option.inject(select,"bottom");
	}
}

/**
 * 加载月菜单
 */
com.loadMonthMenu= function(id) {
	var select = $(id);
	for(var item = 1;item<=12;item++) {
		var option = new Element("option",{"value":item,"text":item});
		option.inject(select,"bottom");
	}
}

/**
 * 加载日期菜单
 */
com.loadDayMenu= function(id) {
	var select = $(id);
	for(var item = 1;item<=31;item++) {
		var option = new Element("option",{"value":item,"text":item});
		option.inject(select,"bottom");
	}
}
/**
 * select 数字加载菜单
 * @param {} selectId 
 * @param {} startNumber
 * @param {} endNumber
 */
com.loadyNumberMenu=function(selectId,startNumber,endNumber,defaultValue) {
	var select = $(selectId);
	for(var item = startNumber;item<=endNumber;item++) {
		var option = new Element("option",{"value":item,"text":item});
		option.inject(select,"bottom");
		if($chk(defaultValue)&&item==defaultValue) {
			option.set("selected","selected");
		}
	}
}

com.setDefaulValueMenu=function(selectId,defaultValue){
	if(!$chk(defaultValue)){
		return;
	}
	var select = $(selectId);
	var options=select.getElements('option');
	options.each(function(item, index){
		if($chk(defaultValue)&&item.get('value')==defaultValue){
			item.set("selected","selected");
		}
	});
}
/**
 * 自动获取frame的高度
 * @param {} frame
 */
com.autoChangeFrameHeight = function(frame) {
	var doc = frame.contentDocument || frame.contentWindow.document;
	var body = $(doc.body);
	var scrollSize = body.getScrollSize();
	frame.height = scrollSize.y;
}
/**
 * 获得银行的下拉菜单
 * @param {} menus
 * @param {} options
 */
com.loadBankMenun=function(menus) {
	new menu.Menu(menus,"/bank/find_all_json.htm",{
 
		onLoadDateAfter:function(selectObj,data,text) {
			 var banks = data.banks;
			 for(var i = 0;i<banks.length;i++) {
				var option = new Element("option",{"value":banks[i].id,"text":banks[i].name});
				option.inject(selectObj,"bottom");
			}
		}
	});
}

/**
 * 左右卷轴效果
 */
com.scrollLives= new Class({
	Implements:[Options,Events],
	options:{
		 showQuantity:2,	//卷轴子元素可显示的最大数量
		 selector:"div",	//卷轴子元素的拾取器
		 leftBtn:null,  	//卷轴的左按钮
		 rightBtn:null, 	//卷轴的右按钮
		 lock:false,    	//卷轴的锁定
		 fx:null,       	//卷轴效果的引用
		 childrens:null,	//卷轴的子元素
		 autoScroll:true,	//是否是自动卷轴
		 pass:false,		//是否是暂停卷轴
		 intervalID : null	//自动卷轴的定时器引用
		 
	},
	initialize:function(el,options) {
		this.el = $(el);
		this.setOptions(options);
		var success = this.initData();
		if(!success)return;
		this.addBtnScrollEvent();
		if(this.options.autoScroll) {
			this.autoRun(true);
			this.createAutoRunEvent();
		}
			
	},
	
	initData:function() {
		this.options.childrens  = this.el.getElements(this.options.selector);
		if(this.options.childrens&&this.options.childrens.length<=this.options.showQuantity) return false;
		this.options.leftBtn = $(this.options.leftBtn);
		this.options.rightBtn = $(this.options.rightBtn);
		return true;
	},
	
	autoRun:function(leftRun) {
		if($chk(this.options.intervalID)) {
			$clear(this.options.intervalID);
		}
		if(leftRun) {
			this.options.intervalID = this.next.periodical(3000,this);
		}
		else {
			this.options.intervalID = this.previous.periodical(3000,this);
		}
	},
	
	createAutoRunEvent : function() {
		this.el.addEvents({
			mouseenter : function() {
				if (this.options.fx) {
					this.options.fx.cancel();
				}
				this.options.pass = true
			}.bind(this),
			mouseleave : function() {
				if (this.options.fx) {
					this.options.fx.resume();
				}
				this.options.pass = false
			}.bind(this)
			
		});
	},
	
	addBtnScrollEvent:function() {
		if(this.options.leftBtn) {
			this.options.leftBtn.addEvent('click',function() {
				if(this.options.autoScroll) {
					this.autoRun(true);
				}
				else {
					this.next();
				}
			}.bind(this));
		}
		if(this.options.rightBtn) {
			this.options.rightBtn.addEvent('click',function() {
				if(this.options.autoScroll) {
					this.autoRun(false);
				}
				else {
					this.previous();
				}
			}.bind(this));
		}
	},
	next:function() {
		if(this.options.pass) return;
	 
		if(this.isLock()) return;
		this.lock(true);
		var first = this.getFirst();
		var itemWidth = first.getStyle('width').toInt();
		this.options.fx = new Fx.Elements([this.el],{
				link:'cancel',
				duration:1500,
				transition : Fx.Transitions.linear,
				onComplete:function(first) {
					this.addLink(first,true);
					this.lock(false);
				}.bind(this,[first])
			}).start({
				'0':{
					marginLeft:[this.el.getStyle("margin-left").toInt(),this.el.getStyle("margin-left").toInt()-itemWidth]
				}
			});
	},
	previous:function() {
		if(this.options.pass) return;
		if(this.isLock()) return;
		this.lock(true);
		this.addLink(this.getLast(),false);
		var scrollFirst = this.getFirst();
		var itemWidth = scrollFirst.getStyle('width').toInt(); 
		this.options.fx = new Fx.Elements([this.el],{
				link:'cancel',
				duration:1500,
				transition : Fx.Transitions.linear,
				onComplete:function() {
					this.lock(false);
				}.bind(this)
			}).start({
				'0':{
					'margin-left':[this.el.getStyle("margin-left").toInt(),this.el.getStyle("margin-left").toInt()+itemWidth]
				}
			});
	
	},
	
	addLink:function(item,last) {
		 if(last) {
		 	item.inject(this.getLast(),'after');
		 	this.el.setStyle('margin-left','0px');
		 }
		 else {
		 	item.inject(this.getFirst(),'before');
		 	this.el.setStyle('margin-left','-'+this.getFirst().getStyle('width').toInt()+'px');
		 }
		this.options.childrens  = this.el.getElements(this.options.selector);
	},
   
	getFirst:function() {
		return this.options.childrens[0];
	},
	getLast:function() {
		return this.options.childrens[this.options.childrens.length-1];
	},
	lock:function(lock) {
		this.options.lock = lock;
	},
	isLock:function() {
		return this.options.lock;
	}
});

 
/**
 * 屏幕效果
 */
Fx.autoMarquee  = new Class({
	Implements:[Options,Events],
	options:{
		innerChildTag:'div',
		maxQuantity:8,//最多子元素
		maxMarqueeHeight:200,//屏幕最大高度
		autointervalTime:5000,//定时换屏的时间间隔 （毫秒）
		selector : "div",     //获得子元素的拾取器
		intervalID:null,      //定时器引用(内部引用)
		fx:null,              //fx实例(内部引用)
		show:true,            //是否换屏(内部引用)
		createChildDom:$empty //加载数据的方法
	},
	initialize:function(el,options) {
		this.el = $(el);
		this.setOptions(options);
		this.autoRun();
	},
	autoRun:function() {
		this.options.intervalID = this.show.periodical(this.options.autointervalTime,this);
		this.createEvent();
	},
	
	checkNeedRemoveLast:function(childrens) {
		var currentQuantity = childrens.length;
		var currentHeight = 0;
		for(var i =0;i<childrens.length;i++) {
			currentHeight+=childrens[i].getStyle("height").toInt();
		}
		if( currentQuantity>=this.options.maxQuantity||currentHeight>this.options.maxMarqueeHeight) {
			return true;
		}
		return false;
	},
	
	show:function() {
		if(!this.options.show) return;
		var innerChild = new Element(this.options.innerChildTag);
		this.fireEvent('createChildDom',innerChild);
		if(!$chk(innerChild.get('html'))) return;
		var childrens = this.el.getChildren(this.options.selector);
		var needRemoveLast = this.checkNeedRemoveLast(childrens);
		innerChild.fade("hide").inject(this.el,'top');
		var height = innerChild.getStyle("height").toInt();
		innerChild.getChildren().hide();
		if(needRemoveLast) {
			var lastChild = childrens.getLast();
			this.options.fx = new Fx.Elements([innerChild,lastChild],{
				link:'cancel',
				duration:1000,
				transition : Fx.Transitions.linear,
				onComplete:function() {
					innerChild.getChildren().show();
					innerChild.tween("opacity", 1);
					innerChild.setStyle("height", "");
					lastChild.destroy();
				}.bind(this,[innerChild,lastChild])
			}).start({
				'0':{
					height:[0,height]
				},
				'1':{
					opacity:[1,0],
					height:[lastChild.getStyle("height").toInt(),0]
				}
			});
		}
		else {
			this.options.fx = new Fx.Elements(innerChild,{
				link:'cancel',
				duration:1000,
				transition : Fx.Transitions.linear,
				onComplete:function() {
					innerChild.getChildren().show();
					innerChild.tween("opacity", 1);
					innerChild.setStyle("height", "");
				}.bind(this,innerChild)
			}).start({
				'0':{
					height:[0,height]
				}
			});
		}	 
	},
	
	createEvent : function() {
		this.el.addEvents({
			mouseenter : function() {
				if (this.options.fx) {
					this.options.fx.cancel();
				}
				this.options.show = false
			}.bind(this),
			mouseleave : function() {
				if (this.options.fx) {
					this.options.fx.resume();
				}
				this.options.show = true
			}.bind(this)
			
		});
	} 
});

/**
 * 悬浮效果
 */
Fx.levitation = new Class({
	Implements:[Options,Events],
	options:{
		where:0,
		top:0,
		autoHidden:true,
		selfAuto:false,
		left:true,
		width:100
	},
	initialize:function(outerDom,innerDom,options) {
		this.outerDom = outerDom;
		this.innerDom = innerDom;
		this.setOptions(options);
		this.createDiv();
		this.addEvent();
	},
	createDiv:function() {
		this.levitation = new Element("div",
			{styles:{
				'left':(0-this.options.width-1)+'px',
				'position': 'absolute'
			}}).inject(document.body);
		this.innerDiv = new Element("div",{styles:{
			'width':this.options.width+'px',
			'float':'left',
			'background':'#F6F6F6',
			'border':'1px solid #d2d2d2'
		}}).inject(this.levitation);
		this.innerDom.inject(this.innerDiv);
		 
		this.outerDiv = new Element("div",{styles:{
			'width':'20px',
			'margin-top':'30px',
			'float':'left',
			'background':'#F6F6F6',
			'border':'1px solid #d2d2d2',
			'border-left':'none'
		}}).inject(this.levitation);
		this.outerDom.inject(this.outerDiv); 
	},
	addEvent:function() {
		this.outerDiv.addEvent('click',function(){
			var hidden = this.levitation.getStyle("left").toInt()<0?true:false;
			this.levitation.set('tween',{duration:1000});
			if(hidden) {
				this.levitation.tween('left','0px')
			}
			else {
				this.levitation.tween('left',(0-this.options.width-1)+'px');
			}
		}.bind(this));
	}
})








 