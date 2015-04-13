/**
author : xujianguo
version : 1.0,2008-9-9
description : 基于mootools的下拉菜单
params : provinceidName:省份id, cityidName:城市id ,districtIdName:行政区域id ,

用法1 ： new AreaMenu("provinceid","cityid","districtid");
		根据js中设定的默认区域来显示
用法2 :	 new AreaMenu("provinceid","cityid","districtid",{"provinceid":2571,"cityid":2572,"districtid":2573});
		根据options中的自定义区域编号来显示
用法3 :	 new AreaMenu("provinceid","cityid","districtid",{"provinceid":2571,"cityid":2572,"districtid":2573,"remindarea":"province"});
		根据options中的自定义区域编号来显示,同时在哪个区域使用'-请选择-'
用法4 :	new AreaMenu("provinceid","cityid","districtid",{"provinceid":2571,"cityid":2572,"districtid":2573,addSelect:true});
		根据options中的自定义区域编号来显示,会在每个区域上加上'-请选择-'
用法5 :	new AreaMenu("provinceid","cityid","districtid",{"provinceid":2571,"cityid":2572,"districtid":2573,addSelect:true,remindName:" "});
		remindName:自定义请选择的提示符,默认是'-请选择-'
		根据options中的自定义区域编号来显示,会在每个区域上加上'-请选择-'	 
*/
var com = com ? com : {};
com.area = com.area ? com.area : {};
com.area.province;//省份对象
com.area.city;	//城市对象
com.area.district;	//区域对象

com.area.useRemind;	//是否是否请选择提示符
com.area.addSelect;	//是否在所有的下拉框都有请选择提示符
com.area.remindName;	//选择提示符的名字


com.area.AreaMenu = new Class({
	Implements: Options,
	options: {
		provinceid:2,
		cityid:3,
		districtid:4,
		remindarea:"",
		addSelect:false,
		remindName:"-请选择-"
    },
	initialize:function(provinceidName,cityidName,districtIdName,options) {
		this.AREA_ROOT_ID=1;
		com.area.province = $(provinceidName);
		com.area.city = $(cityidName);
		com.area.district = $(districtIdName);
		this.setOptions(options);
		com.area.addSelect = this.options.addSelect;
		com.area.remindName = this.options.remindName;
		this.initArea();
		com.area.province.addEvent("change",function(){
			com.area.fillArea(com.area.city,this.value);
			com.area.fillArea(com.area.district,com.area.city.value);
		});
		com.area.city.addEvent("change",function(){
			com.area.fillArea(com.area.district,this.value);
		});
	},
	initArea:function(){
		com.area.useRemind = false;
		if(this.options.remindarea=="province") {
			com.area.useRemind = true;
			com.area.fillArea(com.area.province,this.AREA_ROOT_ID,0);
			com.area.fillArea(com.area.city,0,0);
			com.area.fillArea(com.area.district,0,0);
		}
		else if(this.options.remindarea=="city") {
			com.area.fillArea(com.area.province,this.AREA_ROOT_ID,this.options.provinceid);
			com.area.useRemind = true;
			com.area.fillArea(com.area.city,this.options.provinceid,0);
			com.area.fillArea(com.area.district,0,0);
		}
		else if(this.options.remindarea=="district") {
			com.area.fillArea(com.area.province,this.AREA_ROOT_ID,this.options.provinceid);
			com.area.fillArea(com.area.city,this.options.provinceid,this.options.cityid);
			com.area.useRemind = true;
			com.area.fillArea(com.area.district,this.options.cityid,0);
		}
		else {
			com.area.fillArea(com.area.province,this.AREA_ROOT_ID,this.options.provinceid);
			com.area.fillArea(com.area.city,this.options.provinceid,this.options.cityid);
			com.area.fillArea(com.area.district,this.options.cityid,this.options.districtid);
		}
	}
});

com.area.fillArea = function(areaSelect,parentid,selectid){
	if(!parentid) parentid = 0;
	areaSelect.set("text","");
	var request = new Request.JSON({
		url:"/manager/platform/area/area_json.htm?id="+parentid,
		async:false,
		method:'get',
		onComplete:function(area,text) {
			 
			if(com.area.useRemind||com.area.addSelect) {
				var remindOp = new Element("option",{"value":0,"text":com.area.remindName});
				remindOp.set("selected","selected");
				remindOp.inject(areaSelect,"bottom");
			}
			if(!area||!area.childrens) return;
			
			var childrens = area.childrens;
			
			for(var i = 0;i<childrens.length;i++) {
				var option = new Element("option",{"value":childrens[i].id,"text":childrens[i].name});
				if(selectid&&selectid==childrens[i].id&&!com.area.useRemind)
					option.set("selected","selected");
				option.inject(areaSelect,"bottom");
			}
		}
	}).send();
}
