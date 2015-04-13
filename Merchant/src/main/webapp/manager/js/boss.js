var com = com ? com : {};

/**
 * 得到持久cookie中当前管理员帐号
 * 
 * @return {}
 */
com.operaotrCurrentVisitor = function() {
	return Cookie.read("operator_visitor");
}

/**
 * 管理菜单的显示和隐藏
 */
com.showOrHideMenu = function() {
	var contentDiv = $("content");
	var lefDiv = $("left");
	var rightDiv = $("right");
	var menuBtn = $$(".turn_btn").getFirst().getFirst();

	if (lefDiv.getStyle("display") == "none") {
		// 显示menu
		contentDiv.setStyle("background", "");
		lefDiv.setStyle("display", "block");
		rightDiv.setStyle("margin-left", "");

		menuBtn.set("src", "/manager/image/dev/turnleft_btn.jpg");

	} else {
		contentDiv.setStyle("background", "none");
		lefDiv.setStyle("display", "none");
		rightDiv.setStyle("margin-left", "0");
		menuBtn.set("src", "/manager/image/dev/turnright_btn.jpg");
	}

}
/**
 * 菜单的变换
 */
com.changeMenu = function() {
	var roleGroups = $$("li[class^=roleMenu]");
	roleGroups.each(function(roleGroup) {
		var parentMenu = roleGroup.getFirst("a[class=roleGroup]");
		parentMenu.addEvent("click", function() {
			for ( var i = 0; i < roleGroups.length; i++) {
				roleGroups[i].removeClass("now");
				roleGroups[i].getFirst("div[class=second]").setStyle("display",
						"none");
			}
			roleGroup.addClass("now");
			this.getNext().setStyle("display", "block");
		});
	});
}
/**
 * 左边菜单初始化子菜单被选中
 */
com.initMenu = function() {
	var url = window.location.href;
	if (url.indexOf("/manager/index.htm") > 1) {
		var indexLi = $$("li[class*=index]")[0];
		indexLi.addClass("now");
		var child = indexLi.getElement("div[class=second]");
		child.setStyle("display", "block");
		return;
	}
	var roleItems = $$("a[class=roleItem]");
	roleItems.each(function(role) {
		var link = role.get("href");
		var lastIndex = link.lastIndexOf("/");
		link = link.substring(0, lastIndex + 1);

		if (url.indexOf(link) > 1) {
			role.getParent().addClass("now");
			role.getParent(".second").setStyle("display", "block");
			role.getParent(".roleMenu").addClass("now");
		}
	});
}
/**
 * 把多选框变成单选框
 * 
 * @param {}
 *            selector 多个选框的拾取器
 */
com.checkBoxToRadio = function(selector) {
	var items = $$(selector);
	items.each(function(item) {
		item.addEvent("mousedown", function() {
			var checked = item.get("checked");
			items.each(function(it) {
				it.erase("checked");
			});
			if (checked) {
				item.set("checked", "checked");
			}
		});
	});
}
/**
 * 得到多个选择框第一个被选中的选择框 如果没有则放回null
 * 
 * @param {}
 *            selector 多个选框的拾取器
 */
com.getCheckBoxFirst = function(selector) {
	var items = $$(selector);
	for ( var i = 0; i < items.length; i++) {
		if (items[i].get("checked"))
			return items[i];
	}
	return null;
}

com.refreshValidatorNumber = function(img) {
	var timenow = new Date().getTime();
	img.src = "/manager/common/random_code.htm?d=" + timenow;
}
/**
 * 日期比较
 * DateOne == DateTwo 返回 0
 * DateOne >DateTwo?1:-1
 * @param {} DateOne
 * @param {} DateTwo
 * @return {Number}
 */
com.compareDate=function(DateOne, DateTwo) {
	var OneMonth = DateOne.substring(5, DateOne.lastIndexOf("-"));
	var OneDay = DateOne
			.substring(DateOne.length, DateOne.lastIndexOf("-") + 1);
	var OneYear = DateOne.substring(0, DateOne.indexOf("-"));

	var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf("-"));
	var TwoDay = DateTwo
			.substring(DateTwo.length, DateTwo.lastIndexOf("-") + 1);
	var TwoYear = DateTwo.substring(0, DateTwo.indexOf("-"));
	
	if(Date.parse(OneMonth + "/" + OneDay + "/" + OneYear) == Date
			.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear))
		return 0;	
	return (Date.parse(OneMonth + "/" + OneDay + "/" + OneYear) > Date
			.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear))?1:-1;
}
/**
 * 使element 失效
 * @param {} element
 */
com.disabled =function(element) {
	element = $(element);
	element.set("disabled","disabled");
}

/**
 * 计算长度
 */
com.keypress = function(id, maxLen) {
	var content = $(id).get("value");
	var len;
	if (content.length > maxLen) {
		$(id).set("value", content.substr(0, maxLen));
		len = 0;
	} else {
		len = maxLen - content.length;
	}
	var show = "你还可以输入<b>" + len + "</b>个字";
	$("surplus").set("html", show);
}

/**
 * 活动模板
 */
com.template = function(id,maxLen,flag) {
	var tempalteData_saleploy = [ {
		"key" : "促销通知",
		"value" : "【店名】即日起至10月31日，凭此短信到店消费者，均可免费获得价值XX元商品一份。仅限XX店。"
	}, {
		"key" : "品牌宣传",
		"value" : "【店名】即日起至10月31日，XX商家为答谢新老客户，特举行积分换大奖活动，奖品价值最高可达XX元。"
	}, {
		"key" : "节日祝福",
		"value" : "【店名】亲爱的会员，XX全体员工祝您XX节日快乐！24小时外卖电话:XXX时刻为您服务！"
	} ];
	
    var tempalteData_coupon = [ {
    	"key" : "消费满额优惠",
		"value" : "【店名】优惠大酬宾，凭此短信可享受满XXXX减XXXX元的优惠。"
	}, {
		"key" : "发放代金券",
		"value" : "【店名】为感谢您一直以来的支持，特赠与代金券XXXX元一张。"
	}, {
		"key" : "全单打折",
		"value" : "【店名】优惠大酬宾，凭此短信均可享受满XXXX元打XXXX折优惠。"
	} , {
		"key" : "部分打折",
		"value" : "【店名】优惠大酬宾，凭此短信均可享受XXXX折优惠（酒水除外）。"
	} , {
		"key" : "兑换、特价商品",
		"value" : "【店名】凭此短信可免费兑换原价XXXX元，现XXXX元的XXXX商品一份。"
	} , {
		"key" : "兑换、特价服务",
		"value" : "【店名】凭此短信可免费兑换原价XXXX元，现XXXX元的XXXX服务一次。"
	} ];

    var tempalteData;
    if(flag == "saleploy"){
    	tempalteData = tempalteData_saleploy;
    } else if(flag == "coupon"){
    	tempalteData = tempalteData_coupon
    } else {
    	return;
    }

	new com.showTemplate(id,maxLen,tempalteData);
},

com.showTemplate = new Class({
	initialize : function(id,maxLen,tempalteData) {
		this.tempalteData = tempalteData;
		this.id = id;
		this.maxLen = maxLen;
		this.createTemplate();
		this.addCloseEvent();
		this.addTemplateData();
	},
	createTemplate : function() {
		var root = new Element("div", {
			"class" : "Template"
		});
		var closeDiv = new Element("div", {
			"class" : "Tem_top"
		}).inject(root);
		this.closeLink = new Element("a", {
			"href" : "javascript:void(0);"
		}).inject(closeDiv);
		this.contentDiv = new Element("div", {
			"class" : "Tem_content"
		}).inject(root);
		new Element("div", {
			"class" : "Tem_bottom"
		}).inject(root);
		this.valueContent = new Element("dl").inject(this.contentDiv);
		new Element("div", {
			"style" : "clear:both"
		}).inject(this.contentDiv);
		var inputBtnDiv = new Element("div", {
			"class" : "mb_btn"
		}).inject(this.contentDiv);
		this.saveBtn = new Element("input", {
			"class" : "mb_bc",
			"type" : "button"
		}).inject(inputBtnDiv);
		this.cancelBtn = new Element("input", {
			"class" : "mb_qx",
			"type" : "button"
		}).inject(inputBtnDiv);

		this.pop = new POP.showBox("modify", root, 445, 390);
	},
	addCloseEvent : function() {
		this.closeLink.addEvent("click", function() {
			this.pop.close();
		}.bind(this)), this.cancelBtn.addEvent("click", function() {
			this.pop.close();
		}.bind(this)), this.saveBtn.addEvent("click", function() {
			$(this.id).set("value", this.valueContent.get("text"));
			com.keypress(this.id, this.maxLen);
			this.pop.close();
		}.bind(this));

	},
	addTemplateData : function() {
		var dataKeyDiv = new Element("ul").inject(this.contentDiv, 'top');
		this.tempalteData.each(function(item, index) {

			var li = new Element("li", {
				"id" : "templatekey" + index,
				"html" : "<a href='javascript:void(0);'>" + item.key + "</a>"
			});
			li.inject(dataKeyDiv);
			if (index == 0) {
				li.set("class", "now");
				this.valueContent.set("html", "<dd>" + item.value + "</dd>");
			}
			this.addTempalteChangeEvent(li, item);
		}.bind(this));
	},

	addTempalteChangeEvent : function(li, item) {
		li.addEvent("click", function() {
			this.valueContent.set("html", "<dd>" + item.value + "</dd>");
			this.tempalteData.each(function(it, index) {
				$("templatekey" + index).erase("class");
			});
			li.set("class", "now");
		}.bind(this));
	}
});