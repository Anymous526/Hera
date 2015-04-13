var com = com ? com : {};
com.saleploy = com.saleploy ? com.saleploy : {};

/**
 * 创建营销活动动态效果
 */
com.saleploy.DynamicEffect = new Class({
	initialize:function(existCreateMemberSend,existTradeMoneySend) {
		this.existCreateMemberSend = existCreateMemberSend;
		this.existTradeMoneySend = existTradeMoneySend;
		this.merchantMove = new com.saleploy.MerchantMove();
		this.saleTypeChange();
		this.MerchantBoxChange();
	},
	saleTypeChange:function() {
		var saleTypeRadios = $$("input[name=saleType]");
		var form = $("form");
		 saleTypeRadios.each(function(saleTypeRadio,index,saleTypeRadios){
		 	saleTypeRadio.addEvent("click",function(saleTypeRadio,saleTypeRadios){
		 		$("saleTypeMsg").set("html","");
		 		var formChcek = form.retrieve("formCheck");
		 		formChcek.clearAllError();
		 		var timingTimeDiv = $("timingTimeDiv");
		 		timingTimeDiv.setStyle("display","none");
		 		var timingTime = $("timingTime");
		 		timingTime.set("class","calendar");
		 	 
		 		var tradeMinMoneyDiv = $("tradeMinMoneyDiv");
		 		tradeMinMoneyDiv.setStyle("display","none");
		 		var tradeMinMoney = $("tradeMinMoney");
		 		tradeMinMoney.erase("class");
		 		if(saleTypeRadio.get("checked")&&saleTypeRadio.get("id")=="timingBox") {
		 			timingTimeDiv.setStyle("display","");
					timingTime.set("class","calendar validate['required']");
					
					return;
		 		}
		 		if(saleTypeRadio.get("checked")&&saleTypeRadio.get("id")=="createMemberBox") {
		 			if(this.existCreateMemberSend) {
						$("saleTypeMsg").set("html","你已经创建有注册会员发送的活动");
					}
					return;
		 		}
		 		if(saleTypeRadio.get("checked")&&saleTypeRadio.get("id")=="tradeMinMoneyBox") {
		 			tradeMinMoneyDiv.setStyle("display","");
					tradeMinMoney.set("class","validate['required','digit[1,100000]']");
					if(this.existTradeMoneySend) {
						$("saleTypeMsg").set("html","你已经创建有单次消费满额发送的活动");
					}
		 		}
		 		
		 		
			}.bind(this,[saleTypeRadio,saleTypeRadios]));
		}.bind(this));
 
	},
	MerchantBoxChange:function() {
		var box = $("allMerchantBox");
		if(!$chk(box)) return;
		box.addEvent("click",function(){
			var toLefttBtn = $("toLefttBtn");
			var toRightBtn = $("toRightBtn");
			if(box.get("checked")) {
				toLefttBtn.set("src","/manager/image/btn/M_left.png");
				toLefttBtn.removeClass("canMove");
				toRightBtn.set("src","/manager/image/btn/M_right.png");
				toRightBtn.removeClass("canMove");
				$("merchantName").set("value","全部门店");
			}
			else {
				toLefttBtn.set("src","/manager/image/btn/M_left01.png");
				toLefttBtn.addClass("canMove");
				toRightBtn.set("src","/manager/image/btn/M_right01.png");
				toRightBtn.addClass("canMove");
				this.merchantMove.updateMerchantValue();
			}
		}.bind(this));
	}
});


/**
 * 营销活动商户移动效果
 */
com.saleploy.MerchantMove = new Class({
	initialize:function() {
		this.leftDiv = $$(".left_merchant")[0];
		if(!$chk(this.leftDiv)) return;
		this.rightDiv = $$(".right_merchant")[0];
		this.addLeftEvent();
		this.addRightEvent();
		this.addLeftToRightEvent();
		this.addRightToLeftEvent();
	},
	addLeftEvent:function(){
		var leftMerchants = this.leftDiv.getElements("a");
		leftMerchants.each(function(leftMerchant){
			leftMerchant.addEvent("click",function(leftMerchant){
				this.changeMerchantClass(leftMerchant);
			}.bind(this,leftMerchant));
		}.bind(this));
	},
	addRightEvent:function(){
		var rightMerchants = this.rightDiv.getElements("a");
		rightMerchants.each(function(leftMerchant){
			leftMerchant.addEvent("click",function(leftMerchant){
				this.changeMerchantClass(leftMerchant);
			}.bind(this,leftMerchant));
		}.bind(this));
	},
	changeMerchantClass : function(merchantItem) {
		if(merchantItem.hasClass("down")) {
			merchantItem.removeClass("down");
		}
		else {
			merchantItem.addClass("down");
		}
	},
	addLeftToRightEvent : function() {
		var toRightBtn = $("toRightBtn");
		
		toRightBtn.addEvent("click",function(){
			if(!toRightBtn.hasClass("canMove")) return;
			var leftMerchants = this.leftDiv.getElements("a[class*=down]");
			leftMerchants.each(function(leftMerchant){
				leftMerchant.inject(this.rightDiv);
				this.reloadItems();
			}.bind(this));
		}.bind(this));
	},
	
	addRightToLeftEvent:function(){
		var toLefttBtn = $("toLefttBtn");
		
		toLefttBtn.addEvent("click",function(){
			if(!toLefttBtn.hasClass("canMove")) return;
			var rightMerchants = this.rightDiv.getElements("a[class*=down]");
			rightMerchants.each(function(rightMerchant){
				rightMerchant.inject(this.leftDiv);
				this.reloadItems();
			}.bind(this));
		}.bind(this));
	},
	
	reloadItems:function(){
		var leftMerchants = this.leftDiv.getElements("a");
		for(var i = 0;i<leftMerchants.length;i++) {
			leftMerchants[i].removeClass("down");
			leftMerchants[i].removeClass("M_second");
			if(i%2==1) {
				leftMerchants[i].addClass("M_second");
			}
		}
		var rightMerchants = this.rightDiv.getElements("a");
		for(var i = 0;i<rightMerchants.length;i++) {
			rightMerchants[i].removeClass("down");
			rightMerchants[i].removeClass("M_second");
			if(i%2==1) {
				rightMerchants[i].addClass("M_second");
			}
		}
		this.updateMerchantValue();
	},
	updateMerchantValue : function() {
		var rightMerchants = this.rightDiv.getElements("a");
		var merchantValue = "";
		for(var i = 0;i<rightMerchants.length;i++) {
			merchantValue+=rightMerchants[i].get("text")+",";
		}
		$("merchantName").set("value",merchantValue);
	}
});

com.saleploy.loadMemberLevelCheck = function(){
	 var url = location.href;
	 
	 var values = url.split("?");
	 if(values.length==1) return;
	 var params = values[1].split("&");
	 for(var i = 0;i<params.length;i++) {
	 	var keyValue = params[i].split("=");
 
	 	if(keyValue.length!=2) continue;
	 	var key = keyValue[0];
	 	var value = keyValue[1];
	 	if(key=="memberLevel") {
	 		var checkBox = $("memberLevel"+value);
	 		checkBox.set("checked","checked");
	 	}
	 }
}

/**
 * 创建营销活动时删除会员
 */
com.saleploy.delSalePloyMember = new Class({
	initialize:function() {
		this.allChkBtn = $("allChkBtn");
		this.checkItems = $$(".allItemBtn");
		 
		this.addAllChkBtnEvent();
		this.delBtnEvent();
	},
	addAllChkBtnEvent : function(){
		this.allChkBtn.addEvent("click",function(){
			 
			for(var i = 0;i<this.checkItems.length;i++) {
				if(this.allChkBtn.get("checked")) {
					this.checkItems[i].set("checked","checked");
				}
				else{
					this.checkItems[i].erase("checked");
				}
			}
			
		}.bind(this));
	},
	delBtnEvent:function(){
		$("delBtn").addEvent("click",function(){
			var minPoint = $("minPoint").get("value").trim();
			if($chk(minPoint)) {
				if(!(/^[0-9]+$/.test(minPoint))) {
					alert("最小积分输入非法");
					$("minPoint").focus();
					return ;
				}
			}
			var maxPoint = $("maxPoint").get("value").trim();
			if($chk(maxPoint)) {
				if(!(/^[0-9]+$/.test(maxPoint))) {
					alert("最大积分输入非法");
					$("maxPoint").focus();
					return ;
				}
				if(minPoint.toInt()>maxPoint.toInt()) {
					alert("最小积分不能小于最大积分");
					$("maxPoint").focus();
					return ;
				}
			}
			if(!this.existCheckedItem()) return;
			this.updateExcludeMember();
			$("memberSearch").submit();;
			
		}.bind(this));
	},
	existCheckedItem : function(){
		for(var i = 0;i<this.checkItems.length;i++) {
			if(this.checkItems[i].get("checked"))
				return true;
		}
		return false;
	},
	
	updateExcludeMember:function(){
		var excludeMemberId = $("excludeMemberId");
		var value = excludeMemberId.get("value");
		for(var i = 0;i<this.checkItems.length;i++) {
			if(this.checkItems[i].get("checked")) {
				value+=this.checkItems[i].get("value")+",";
			}
		}
		excludeMemberId.set("value",value)
	}
});

/**
 * 创建获得的提交按钮
 */
com.saleploy.createPloy=function(){
	$("createPloyBtn").addEvent("click",function(){
	 	
		var minPoint = $("minPoint").get("value").trim();
		if($chk(minPoint)) {
			if(!(/^[0-9]+$/.test(minPoint))) {
				alert("最小积分输入非法");
				$("minPoint").focus();
				return ;
			}
		}
		var maxPoint = $("maxPoint").get("value").trim();
		if($chk(maxPoint)) {
			if(!(/^[0-9]+$/.test(maxPoint))) {
				alert("最大积分输入非法");
				$("maxPoint").focus();
				return ;
			}
			if(minPoint.toInt()>maxPoint.toInt()) {
				alert("最小积分不能小于最大积分");
				$("maxPoint").focus();
				return ;
			}
		}
		
		var canSubmit= $("canSubmit").get("value");
		if(canSubmit=="false"){
			POP.alert("短信数量不够或未选择会员");
			return;
		}
		this.set("disabled","disabled");
		var form = $("memberSearch");
		form.set("action","/manager/saleploycreate/create_success.htm");
		form.set("method","post");
		form.submit();
	});
}


 

/**
 * 注销活动
 * @param {} freeze
 */
com.saleploy.logOutPloy = function() {
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	var url ="/manager/saleploy/logout.htm";
	var request = new Request.JSON({
		url:url,
		async:true,
		method:"put",
		onComplete:function(data,text) {
			POP.alert(data.msg);
			if(data.success) {
				statusTd = item.getParent("tr").getChildren(".statusDesc");
				 
				statusTd.set("text",data.status);
				statusTd = item.getParent("tr").highlight('#F07902');
			}
		}
	}).send("id="+item.get("value"));
}



/**
 * 修改活动
 * @param {} freeze
 */
com.saleploy.modifyPloy = function() {
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	var statusDesc = item.getParent("tr").getChildren(".statusDesc").get("text");
 
	if((statusDesc+"").trim()!="审核未通过") {
		POP.alert("只有审核未通过的活动才能修改");
		return;
	}
	new com.saleploy.loadModifDiv(item);
	 
}

 com.saleploy.loadModifDiv = new Class({
 	Implements: [Options, Events],
	options: {},
	initialize:function(item) {
		this.item = item;
		this.createDialog();
		this.createCloseEvent();
		this.createCommitEvent();
		this.createTextareaKeyupEvent();
	},
 	createDialog:function() {
 		var templte = this.item.getParent("tr").getChildren(".templat").get("text");
 		templte = (templte+"").trim();
 		var parent = new Element("div",{"class":"Feedback"});
		var div1 = new Element("div",{"class":"S_Credit01"}).inject(parent);
		var div2 = new Element("div",{"class":"Credit_title"}).inject(div1);
		var title = new Element("span",{"html":"短信内容"}).inject(div2);
		this.closeBtn = new Element("img",{"src":"/manager/image/btn/close.jpg","style":"*margin-top:-30px"}).inject(div2);
		var div3 = new Element("div",{"class":"S_Reminded"}).inject(div1);
		var ul = new Element("ul").inject(div3);
		var li1 = new Element("li",{"style":"height:auto"}).inject(ul);
		this.textarea = new Element("textarea",{"id":"template","value":templte}).inject(li1);
		
		var font = new Element("font",{"style":"margin-left:160px;"}).inject(li1);
		var label = new Element("label",{"id":"surplus"}).inject(font);
		label.set("html","你还可以输入&nbsp;<b>65</b>&nbsp;个字");
		var li2 = new Element("li",{"class":"tj","style":"background:none"}).inject(ul);
		this.commitBtn = new Element("img",{"src":"/manager/image/btn/tj.jpg"}).inject(li2);
		var div4 = new Element("div",{"style":"clear:both"}).inject(div1);
		this.pop = new POP.showBox("modify",parent,300,150);
		
 	},
 	createCloseEvent : function(){
 		this.closeBtn.addEvent("click",function(){
 			this.pop.close();
 		}.bind(this));
 	},
 	createTextareaKeyupEvent:function() {
 		com.keypress(this.textarea,65);
 		this.textarea.addEvent("keyup",function(){
 			com.keypress(this,65);
 		});
 	},
 	createCommitEvent:function(){
 		var item = this.item;
 		this.commitBtn.addEvent("click",function(){
 			var template = this.textarea.get("value");
 		 
 			if(template.trim().length==0) {
 				alert("请输入修改内容");
 				return;
 			}
 			this.pop.close();
 			var url ="/manager/saleploy/modify.htm";
			var request = new Request.JSON({
				url:url,
				async:true,
				method:"put",
				onComplete:function(data,text) {
					POP.alert(data.msg);
					if(data.success) {
						statusTd = item.getParent("tr").getChildren(".statusDesc");
						statusTd.set("text",data.status);
						templatTd = item.getParent("tr").getChildren(".templat");
						templatTd.set("text",data.template);
						statusTd = item.getParent("tr").highlight('#F07902');
					}
				}
			}).send("id="+item.get("value")+"&template="+template);
 		}.bind(this));
 	}
 });
 
function validatorGrateDate(el) { 
	var startDate = $("startDate").get("value");
	if(!$chk(startDate)) return true;
	var endDate = el.get("value");
	if(endDate.trim().length==0) {
		el.set("value"," ");
		el.errors.push("请输入有效截止期");
        return false;
	}

	if(com.compareDate(startDate,endDate)>=0) {
		el.errors.push("活动结束日期不能小于活动开始日期");
        return false;
	}
	return true;
}
function validatorGrateTime(el) {
	var startTime = el.get("value");
	if(!$chk(startTime)) return true;
	var endTime = $("endTime").get("value");
	if(endTime.trim().length==0) {
		el.errors.push("请输入可用结束时段");
        return false;
	}
	startTime = startTime.split(":")[0].toInt();
	endTime = endTime.split(":")[0].toInt();
	if(startTime>=endTime) {
		el.errors.push("活动开始时段不能小于活动结束时段");
        return false;
	}
	return true;
}

function validatorPoint(el) {
	var minPoint = $("minPoint").get("value");
	var maxPoint  = el.get("value");
	if($chk(minPoint)&&minPoint>maxPoint) {
		el.errors.push("最小积分不能小于最大积分");
        return false;
	}
}
