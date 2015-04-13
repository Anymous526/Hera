var com = com ? com : {};
com.coupon = com.coupon ? com.coupon : {};
/**
 * 选择优惠券活动的类型
 * @param {} type
 */
com.coupon.selectPloyType = function(type){
	var form = $("createOneForm");
	var typeInput = $("ployType");
	typeInput.set("value",type);
	form.submit();
}
/**
 * 验证电子券的有效截止日期
 * @param {} el
 * @return {Boolean}
 */
function validatorValidEndDate(el) { 
	var startDate = $("validStartDate").get("value");
	return validatorEndDate(startDate,el);
}
/**
 * 修改时验证电子券的有效截止日期与发送截止日期
 * @param {} el
 * @return {Boolean}
 */
function validatorValidAndSendEndDate(el,flag) { 
	if(flag){
		var startDate = $("validStartDate").get("value");
		var success = validatorEndDate(startDate,el);
		if(success) {
			var sendEndDate = $("sendEndDate").get("value");
			var endDate = el.get("value");
			if(com.compareDate(sendEndDate,endDate)>0) {
				el.errors.push("券使用有效截止期必须大于活动发券截止期:"+$("sendEndDate").get("value"));
	        	return false;
			}
		}
	}else{
		var startDate = $("sendStartDate").get("value");
		var success = validatorEndDate(startDate,el);
		if(success) {
			var validEndDate = $("validEndDate").get("value");
			var endDate = el.get("value");
			if(com.compareDate(endDate,validEndDate)>0) {
				el.errors.push("活动发券截止期必须小于券使用有效截止期:"+$("validEndDate").get("value"));
	        	return false;
			}
		}
	}

	return success;
}
/**
 * 验证电子券活动的发放截止日期
 * @param {} el
 * @return {Boolean}
 */
function validatorSendEndDate(el) { 
	var startDate = $("sendStartDate").get("value");
	
	var success = validatorEndDate(startDate,el);
	if(success) {
		var couponValidEndDate = $("validEndDate").get("text");
		var endDate = el.get("value");
		
		if(com.compareDate(endDate,couponValidEndDate)>0) {
			el.errors.push("活动发券截止期必须小于券使用有效截止期:"+$("validEndDate").get("text"));
        	return false;
		}
	}
	return success;
}

function validatorEndDate(startDate,el) {
	if(!$chk(startDate)) return true;
	var endDate = el.get("value");
	if(endDate.trim().length==0) {
		el.set("value"," ");
		el.errors.push("请输入有效截止期");
        return false;
	}

	if(com.compareDate(startDate,endDate)>=0) {
		el.errors.push("结束日期必须大于开始日期");
        return false;
	}
	return true;
}

/**
 * 创建电子券活动的第二步骤
 * @type 
 */
com.coupon.CreateTwoEffect = new Class({
	Implements: [ Events, Options ],
	options: {
		couponValue:"",
		applyMerchantIds:"",
		maxSendCountValue:"",
		ployType:0
	},
	initialize:function(options) {
		this.setOptions(options);
		this.merchantMove = new com.coupon.MerchantMove();
		this.initParam();
		this.MerchantBoxChange();
		this.addCouponTypeEvent();
		this.addMaxSendCountEvent();
	},
	/**
	 * 初始化UI页面的参数
	 */
	initParam:function(){
		var couponType = $("couponType");
		this.changeCouponType(couponType.get("value"),this.options.couponValue);
		if($chk(this.options.applyMerchantIds)) {
			var ids = this.options.applyMerchantIds.split(",");
			 
			var leftMerchants = this.merchantMove.leftDiv.getElements("a");
			var filteredArray = leftMerchants.filter(function(item){
				for(var i =0;i<ids.length;i++) {
				 
					if(item.get("id")=="appMerchant_"+ids[i]) {
						return true;
					}
				}
				return false;
			});
			filteredArray.each(function(leftMerchant){
				leftMerchant.inject(this.merchantMove.rightDiv);
				this.merchantMove.reloadItems();
			}.bind(this));
		}
	},
	
	addMaxSendCountEvent:function(){
		var maxSendCountSelect = $("maxSendCountSelect");
		if(!$chk(maxSendCountSelect))return;
		this.maxSendCountChange($chk(this.options.maxSendCountValue),this.options.maxSendCountValue);
		var options = maxSendCountSelect.getElements("option");
		for(var i = 0;i<options.length;i++) {
			var op = options[i];
			if($chk(this.options.maxSendCountValue)&&op.get("value")==1) {
				op.set("selected","selected");
			}
		}
		maxSendCountSelect.addEvent("change",function() {
			this.maxSendCountChange(maxSendCountSelect.get("value")==1,"");
		}.bind(this));
	},
	maxSendCountChange:function(show,maxSendCountValue){
		var maxSendCount = $("maxSendCount");
		 
		if(show) {
			maxSendCount.show();
			maxSendCount.set("value",maxSendCountValue);
			$("maxSendCountText").show();
			maxSendCount.addClass("validate['required','digit[1,9999]']");
		}
		else {
			maxSendCount.hide();
			$("maxSendCountText").hide();
			maxSendCount.set("value","");
			maxSendCount.erase("class");
		}
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
				$("applyMerchantIds").set("value","");
			}
			else {
				toLefttBtn.set("src","/manager/image/btn/M_left01.png");
				toLefttBtn.addClass("canMove");
				toRightBtn.set("src","/manager/image/btn/M_right01.png");
				toRightBtn.addClass("canMove");
				this.merchantMove.updateMerchantValue();
			}
		}.bind(this));
	},
	addCouponTypeEvent:function() {
		var couponType = $("couponType");
		couponType.addEvent("change",function() {
			this.changeCouponType(couponType.get("value"),"");
		}.bind(this));
	},
	changeCouponType:function(typeValue,couponValue) {
		var couponValueDiv = $("couponValueDiv");
		couponValueDiv.erase("html");
		if(typeValue==1) {
			var typeDescInput= new Element("input",{name:"couponValue",'class':"validate['required','digit[0,10000]']","value":couponValue});
			typeDescInput.inject(couponValueDiv);
			 couponValueDiv.appendText(" 元");
		}
		else if(typeValue==2) {
			var typeDescInput= new Element("input",{name:"couponValue",'class':"validate['required','digit[0,100]']","value":couponValue});
			typeDescInput.inject(couponValueDiv);
			couponValueDiv.appendText(" 折(输入1到100的数字,如88代表8.8折)");
		}
		 
	}
	
});
/**
 * 营销活动商户移动效果
 */
com.coupon.MerchantMove = new Class({
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
			merchantValue+=rightMerchants[i].get("id").split("_")[1]+",";
		}
		$("applyMerchantIds").set("value",merchantValue);
	}
});

/**
 * 创建优惠券第三步的JS效果
 */
com.coupon.CreateThreeEffect = new Class({
	initialize:function() {
		this.initData();
		this.addDisplayInWebEvent();
	},
	
	initData:function() { 	
	},
 
	addDisplayInWebEvent:function() {
		var displayInWeb = $("displayInWeb");
		var displayInWebDiv = $("displayInWebDiv");
		var agreeDownloadRadio=$("agreeDownload");
		var unAgreeDownloadRadio=$("unAgreeDownload");
		var maxReceiveCountByEveryUser = $("maxReceiveCountByEveryUser");
		displayInWeb.addEvent("click",function() {
			if(this.get("checked")) {
				displayInWebDiv.show();
			}
			else {
				displayInWebDiv.hide();
				unAgreeDownloadRadio.set("checked","checked");
				agreeDownloadRadio.erase("checked");
				maxReceiveCountByEveryUser.setStyle("display","none");
			}
		});
		unAgreeDownloadRadio.addEvent("click",function(){
			if(this.get("checked")) {
				maxReceiveCountByEveryUser.setStyle("display","none");
				maxReceiveCountByEveryUser.erase("class");
				maxReceiveCountByEveryUser.set("value","");
				agreeDownloadRadio.erase("checked");
			}
		});
		agreeDownloadRadio.addEvent("click",function(){
			if(this.get("checked")) {
				maxReceiveCountByEveryUser.erase("style");
				maxReceiveCountByEveryUser.addClass("validate['required','digit[1,5]']");
				maxReceiveCountByEveryUser.set("value","1");
				unAgreeDownloadRadio.erase("checked");
			}
		});
		
	}
});

/**
 * 创建第四步的特效
 */
com.coupon.CreateFourEffect = new Class({
	Implements: [ Events, Options ],
	options: {
		"provinceid":null,
	  	"cityid":null,
	  	"districtid":null
	},
	initialize:function(options) {
		this.setOptions(options);
		this.loadSearchParam();
		new com.coupon.delPloyMemberEvent();
		this.addCreateCouponPloyEvent();
		this.addMoreSearchEvent();
	},
	addCreateCouponPloyEvent:function() {
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
			form.set("action","/manager/coupon/create/create_commit.htm");
			form.set("method","post");
			form.submit();
		});
	},
	addMoreSearchEvent:function() {
		var moreSearchBtn = $("moreSearchBtn");
		var moreSearchDiv = $("moreSearchDiv");
		moreSearchBtn.addEvent("click",function() {
			moreSearchDiv.toggle();
		});
	},
	
	loadSearchParam : function(){
		 
		if($chk(this.options.districtid)) {
			new com.area.AreaMenu("province","city","district",{ 
				"provinceid":this.options.provinceid,
				"cityid":this.options.cityid,
				"districtid":this.options.districtid,
			  
			 	addSelect:true
			});
		}
		else if($chk(this.options.cityid)) {
			new com.area.AreaMenu("province","city","district",{ 
				"provinceid":this.options.provinceid,
				"cityid":this.options.cityid,
			 	remindarea:"district",
			 	addSelect:true
			});
		}
		else if($chk(this.options.provinceid)) {
			
			new com.area.AreaMenu("province","city","district",{ 
				"provinceid":this.options.provinceid,
			 	remindarea:"city",
			 	addSelect:true
			});
		}
		else {
			new com.area.AreaMenu("province","city","district",{
			 	remindarea:"province",
			 	addSelect:true
			});
		}
		
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
	
});



/**
 * 创建营销活动时删除会员
 */
com.coupon.delPloyMemberEvent = new Class({
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
