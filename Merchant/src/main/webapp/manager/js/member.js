var com = com ? com : {};
com.member = com.member ? com.member : {};

com.member.freeze = function() {
	com.member.freezeMember(true);
}

com.member.freezeMember = function(freeze) {
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	var url = freeze?"/manager/member/info/freeze.htm":"/manager/member/info/unfreeze.htm";
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

com.member.unFreeze = function() {
	com.member.freezeMember(false);
}

com.member.modify = function(){
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	window.location.href = "/manager/member/info/"+item.get("value")+"/modify.htm";
}

com.member.view = function(id){
	
	window.location.href = "/manager/member/info/"+id+"/view.htm";
}

com.member.add = function() {
	window.location.href = "/manager/member/info/add.htm";
}

com.member.logout = function() {
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	new POP.msgBox("如果删除该会员,该用户将无法再成为你的会员！",{
		title:"警 告",
		confirm:true,
		onConfirmTrue:function() {
			var url = "/manager/member/info/"+item.get("value")+"/logout.htm";
			var request = new Request.JSON({
				url:url,
				async:true,
				method:"put",
				onComplete:function(data,text) {
					POP.alert(data.msg);
					if(data.success) {
						window.location.href = "/manager/member/info/info.htm";
					}
				}
			}).send();
		}
	});
	 
	
}
