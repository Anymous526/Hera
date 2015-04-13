
var menu = menu ? menu : {};
/**
*数据结构 menu = {
	"id":"bussScopeParent", 表示下拉菜单的 id（必填）
	"selectedValue":5,		表示下拉菜单的默认选择值,(可选)如果未定义才使用下拉菜单的第一个值
	"usePrompt":false       是否在该菜单使用提示项(该值是内部生成，外部不需要传)
}
*@param menus 为 menu格式的数组
*@param requestUrl 为后台调用数据的地址 参数为id
*
*/
menu.Menu = new Class({
	Implements: [Options, Events],
	options: {
		rootId:1,    //后台根ID
		usePrompt:false,	//是否使用提示项，默认为false,如果为true，那么下来菜单将多个提示项目，默认情况下提示项是在第一级下拉菜单
		prompt:{
			promptId:null,	//提示项对应的下拉菜单ID，默认是在第一级下拉菜单
			promptValue:0,	//提示项的值
			promptLabel:"-请选择-"//提示项显示的内容
		},
		onMenuChangeBefore:$empty,	//下拉菜单change前的事件
		onMenuChangeAfter:$empty,	//下拉菜单change后的事件
		onLoadDateAfter : $empty	//下拉菜单后台数据加载后的事件(具体下拉菜单的数据填充在该事件中完成),
									//该事件有三个参数[selectObj,data,text]，分别表示:下拉菜单对象,json数据，字符串数据
    },
	initialize:function(menus,requestUrl,options) {
		if(!$chk(menus)||menus.length == 0) return;
		this.menus =menus;
		this.requestUrl = requestUrl;
		this.setOptions(options);
		this.initData();
		this.initMenuData();
		this.addMenuEvent();
	},
	initData:function() {
		if(!this.options.usePrompt) return;
		if(!$chk(this.options.prompt.promptId)) {
			this.options.prompt.promptId = this.menus[0].id;
		}
		var usePrompt = false;
		for(var i = 0;i<this.menus.length;i++) {
			if(usePrompt) {
				//this.menus[i].selectedValue = this.options.prompt.promptValue;
				this.menus[i].usePrompt = true;
				continue;
			}
			if(this.menus[i].id == this.options.prompt.promptId) {
				//this.menus[i].selectedValue = this.options.prompt.promptValue;
				this.menus[i].usePrompt = true;
				usePrompt = true;
			}
			else {
				this.menus[i].usePrompt = false;
			}
		}
	},
	initMenuData : function() {
		var parent = {selectedValue:this.options.rootId};
		this.menus.each(function(menu) {
			this.fillMenu(menu,parent.selectedValue);
			parent = menu;
		}.bind(this));
	},
	/**
	 * 为下拉菜单添加change事件
	 */
	addMenuEvent:function() {
		for(var i = 0;i<this.menus.length-1;i++) {	
			var selectObj = $(this.menus[i].id);
			selectObj.addEvent("change",function(selectObj,i) {
				this.fireEvent('menuChangeBefore', selectObj);
				for(var j = i+1;j<this.menus.length;j++) {
					var parentValue = $(this.menus[j-1].id).value;
					this.fillMenu(this.menus[j],parentValue);
				}
				this.fireEvent('menuChangeAfter', selectObj);
			}.bind(this,[selectObj,i]));
		}
	},
	/**
	 * 填充数据 
	 * @param {} menu menu对象 menu = {"id":"bussScopeParent","selectedValue":5}
	 * @param {} parentValue 父级ID
	 */
	fillMenu :function(menu,parentValue){
		var selectObj = $(menu.id);
		selectObj.set("text","");
		if(!$chk(parentValue)||parentValue==0) return;
		var request = new Request.JSON({
			url:this.requestUrl+"?id="+parentValue,
			async:false,
			onComplete:function(data,text) {
				if(!data) return;
				if(menu.usePrompt) {
					var prompt = new Element("option",{"value":this.options.prompt.promptValue,"text":this.options.prompt.promptLabel});
					prompt.set("selected","selected");
					prompt.inject(selectObj,"bottom");
				}
				this.fireEvent('loadDateAfter', [selectObj,data,text]);
			
				if($chk(menu.selectedValue)) {
					 
					var selectedOp = selectObj.getElement("option[value="+menu.selectedValue+"]");
					if($chk(selectedOp)){
						selectedOp.set("selected","selected");
					}
				}
				else {
					var options = selectObj.getElements("option");
					if(options.length>0)
						menu.selectedValue= options[0].value;
				}
			}.bind(this)
		}).send();
	}	
});