var com = com ? com : {};
com.couponploy = com.couponploy ? com.couponploy : {};

com.couponploy.changemerchant = function(obj) {
	var form = $("ploysearchForm");
	var merchantInput = $("merchantId");
	merchantInput.set("value",obj.value);
	form.submit();
}

com.couponploy.modifyFind = function(){
	
	var item = com.getCheckBoxFirst("input[class*=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	if(!item.hasClass("canmodify")) {
		POP.alert("审核未通过的活动才能修改");
		return;
	}
	window.location.href = "/manager/coupon/ploy/"+item.get("value")+"/modifyFind.htm";
 
}

com.couponploy.pause = function(){
	com.couponploy.pauseCoupon(true);
}

com.couponploy.restore = function(){
	com.couponploy.pauseCoupon(false);
}

com.couponploy.pauseCoupon = function(ispause) {
	var item = com.getCheckBoxFirst("input[class*=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	var url = ispause?"/manager/coupon/ploy/pause.htm":"/manager/coupon/ploy/restore.htm";
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
	}).send("couponPloyId="+item.get("value"));
}
	


com.couponploy.logout = function(){
	var item = com.getCheckBoxFirst("input[class*=checkboxItem]");
	if(!$chk(item)) {
		return ;
	}
	var url = "/manager/coupon/ploy/logout.htm";
	var request = new Request.JSON({
		url: url ,
		async:true,
		method:"put",
		onComplete: function(date,text) {
			POP.alert(date.msg);
			if(date.success) {
				statusTd = item.getParent("tr").getChildren(".statusDesc");
				statusTd.set("text",date.status);
				statusTd = item.getParent("tr").highlight('#F07902');
			}
		}
	}).send("id="+item.get("value"));
}
