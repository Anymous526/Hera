var com = com ? com : {};
com.operator = com.operator ? com.operator : {};


/**
 * 验证操作权限操作员权限是否被选
 * @param {} el
 * @return {Boolean}
 */
validatorOpRole = function(el){
	var roleStr = $("roleStr");
	if(roleStr.get("value").length==0){
		el.errors.push("请选择管理员权限");
        return false;
	}
	return true;
}
/**
 * 验证管理员手机号是否存在
 * @param {} el
 * @return {Boolean}
 */
validatorOpMobileExist = function(el) {
	
	var mobile = el.get("value");
	
	var exist = com.operator.isExistOpMobile(mobile);
	if(exist) {
		el.errors.push("该手机号已经存在");
		 return false;
	}
	return true;
}

com.operator.isExistOpMobile = function(mobile) {
	var exist= false;
	new Request.JSON({
		method:"get",
		async:false,
		url:"/manager/security/operator/exist.htm?mobile="+mobile,
		onComplete:function(json){
			exist=json.success;
		}
	}).send();
	return exist;
}


/**
 * 冻结管理员
 */
com.operator.freeze = function() {
	com.operator.freezeOperator(true);
}
/**
 * 修改管理员
 */
com.operator.update = function(){
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	window.location.href="/manager/security/operator/"+item.get("value")+"/update.htm";
}

/**
 * 冻结或解冻管理员
 * @param {} freeze
 */
com.operator.freezeOperator = function(freeze) {
	var item = com.getCheckBoxFirst("input[class=checkboxItem]");
	if(!$chk(item)) {
		return;
	}
	var url = freeze?"/manager/security/operator/freeze.htm":"/manager/security/operator/unfreeze.htm";
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
 * 解冻管理员
 */
com.operator.unFreeze = function() {
	com.operator.freezeOperator(false);
}
/**
 *  管理员权限多选框变化方法
 */
com.operator.changeRoleItem = new Class({
	initialize:function() {
		this.roleInput = $$("input[name=roleStr]")[0];
		this.checkedRoles = [];
		this.initMap();
		this.initCheckedRole();
		this.addGroupEvent();
		this.addRoleItemEvent();
		this.allSelectedEvent();
	},
	initMap:function() {
		var roles = $$(".role");
		this.itemMap = new Hash();
		for(var i = 0;i<roles.length;i++) {
			var group = roles[i].getElements("input[class=group]");
			var values = roles[i].getElements("input[class=item]");
			this.itemMap.set(group.get("id"),values);
		}
	},
	initCheckedRole : function() {
		var groups = this.itemMap.getKeys();
		var roleStr = this.roleInput.get("value");
		var oldRoleIds = roleStr.length==0?[]:roleStr.split(",");
		for(var i = 0;i<groups.length;i++) {
			var items = this.itemMap.get(groups[i]);
			for(var j = 0;j<items.length;j++) {
				this.reloadOldRole(oldRoleIds,items[j]);
				if(items[j].get("checked"))
					this.checkedRoles.include(items[j]);
			}
		}
		this.roleInput.set("value","");
		this.updateRoleInput();
	},
	
	reloadOldRole : function(oldRoleIds,roleItem) {
		oldRoleIds.each(function(oldRoleId){
			if(oldRoleId == roleItem.get("value"))
				 roleItem.set("checked","checked");
		});
	},
	
	addGroupEvent : function() {
		var groups = this.itemMap.getKeys();
		for(var i = 0;i<groups.length;i++) {
			var group = $(groups[i]);
			var items = this.itemMap.get(groups[i]);
			group.addEvent("click",function(group,items){
				var checked = group.get("checked");
				this.selectGroup(checked,items);
			}.bind(this,[group,items]));
		}
	},
	selectGroup : function(checked,items) {
		for(var i = 0;i<items.length;i++) {
			if(checked) {
				items[i].set("checked","checked");
				this.checkedRoles.include(items[i]);
			}
			else {
				items[i].erase("checked");
				this.checkedRoles.erase(items[i]);
			}
		}
		this.updateRoleInput();
	},
	addRoleItemEvent : function() {
		var groups = this.itemMap.getKeys();
		for(var i = 0;i<groups.length;i++) {
			var group = $(groups[i]);
			var items = this.itemMap.get(groups[i]);
			for(var j = 0;j<items.length;j++) {
				 this.selectItem(items[j],items,group)
			}
		}
	},
	selectItem : function(itemRole,items,group) {
		itemRole.addEvent("click",function(itemRole,items,group) {
			var checked = itemRole.get("checked");
			if(checked) {
				this.checkedRoles.include(itemRole);
			}
			else {
				this.checkedRoles.erase(itemRole);
			}
			var groupNeedSelected = true;
			for(var i = 0;i<items.length;i++) {
				if(!items[i].get("checked")) {
					groupNeedSelected = false;
					break;
				}
			}
			if(groupNeedSelected) {
				group.set("checked","checked");
			}
			else {
				group.erase("checked");
			}
			 
			this.updateRoleInput();
		}.bind(this,[itemRole,items,group]));
	},	
	updateRoleInput : function() {
		var value = "";
		this.checkedRoles.each(function(roleItem){
			value+=roleItem.get("value")+",";
		});
		this.roleInput.value=value;
	},
	
	allSelectedEvent : function() {
		var allCheck = $("allCheck");
		allCheck.addEvent("click",function(){
			var groups = this.itemMap.getKeys();
			for(var i = 0;i<groups.length;i++) {
				var group = $(groups[i]);
				var items = this.itemMap.get(groups[i]);
				if(allCheck.get("checked")) {
					group.set("checked","checked");
					this.selectGroup(true,items);
				}
				else {
					group.erase("checked");
					this.selectGroup(false,items);
				}
			} 
		}.bind(this));
	}
	
});



