/**
 * 结行Ext组件库 1.0
 */

Ext.namespace('JustIn', 'JustIn.util', 'JustIn.grid');
/**
 * 可定制字体风格的消息框
 */
JustIn.messager = function() {
	var msgCt;

	function createBox(t, s, style) {

		return [
				'<div class="msg">',
				'<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
				'<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc" style="',
				style,
				'"><h3>',
				t,
				'</h3>',
				s,
				'</div></div></div>',
				'<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
				'</div>'].join('');
	}
	return {
		// 消息提示框 参数为 标题 正文 延迟时间 风格
		msg : function(title, format, delay, style) {
			if (!msgCt) {
				msgCt = Ext.DomHelper.insertFirst(document.body, {
							id : 'msg-div'
						}, true);
			}
			msgCt.alignTo(document, 't-t');
			var s = String.format.apply(String, Array.prototype.slice.call(
							arguments, 1));
			var m = Ext.DomHelper.append(msgCt, {
						html : createBox(title, s, style)
					}, true);
			if (delay == -1) {
				m.slideIn('t');
			}else{
				m.slideIn('t').pause(delay).ghost("t", {
							remove : true
						});
			}
		},

		init : function() {
			var t = Ext.get('exttheme');
			if (!t) { // run locally?
				return;
			}
			var theme = Cookies.get('exttheme') || 'aero';
			if (theme) {
				t.dom.value = theme;
				Ext.getBody().addClass('x-' + theme);
			}
			t.on('change', function() {
						Cookies.set('exttheme', t.getValue());
						setTimeout(function() {
									window.location.reload();
								}, 250);
					});

			var lb = Ext.get('lib-bar');
			if (lb) {
				lb.show();
			}
		}
	};
}();

/**
 * 获取url中变量的便利工具
 * @param {} param
 * @return {}
 */
JustIn.util.getUrlParam = function(param) {
	var params = Ext.urlDecode(location.search.substring(1));
	return param ? params[param] : params;
};

/**
 * 支持翻页的多选 需要具体应用定义selectedIds数组保存所选项
 */
JustIn.grid.CheckboxSelectionModel = Ext.extend(Ext.grid.CheckboxSelectionModel, {

			onRefresh : function() {
				if(this.selectedIds == null) {
					this.selectedIds = new Array();
				}
				var ds = this.grid.store;				
				var s = this.getSelections();
				this.clearSelections(true);
				for (var i = 0, len = s.length; i < len; i++) {
					var r = s[i];
					var index = ds.indexOf(r);
					if (index != -1) {
						this.selectRow(index, true);
					}
				}				
				// 此处扩展为由selectedIds保存的选项重选返回页面的checkbox
				if (this.keepSelections && this.selectedIds) {
					for (var i = 0, len = this.selectedIds.length; i < len; i++) {						
//						var index = ds.indexOfId(this.selectedIds[i]);
						var rec = ds.getById(this.selectedIds[i]);
						var index = ds.indexOf(rec);
						if(index != -1)
							this.selectRow(index, true);							
					}

				}

				if (s.length != this.selections.getCount()) {
					this.fireEvent("selectionchange", this);
				}

			},

			keepSelections : true,
			selectedIds : null
		});
/* 表格转义renderer */ 
 function escapeCell(value,metadata,record,rowIndex,colIndex,store){
 	
	 str=value.replace(/\</g,"&lt;");
	 str=str.replace(/\>/g,"&gt;");
    return str;
}

/*去掉表格头部的选框*/
function removeHearderBox(grid){
	grid.getEl().select('div.x-grid3-hd-checker').removeClass('x-grid3-hd-checker'); 
}
/*添加表格头部的选框*/
function addHearderBox(grid){
	grid.getEl().select('div.x-grid3-hd-checker').addClass('x-grid3-hd-checker'); 
}

/* 验证toolbar所有输入项 */
function validateBar( toolbar ){
	  
	 var result = true;
	 var items = toolbar.items;
	 if(!items||items.length<1){
	 	return true;
	 }
	 for(var item in items){
	 	if (item.isValid){
	 		if (!item.isValid()){
	 			 result = false;
	 			 break;
	 		}
	 	}
	 	
	 }
	return result;
	
	 
}

// 自定义Vtype验证----验证密码是否一致
Ext.apply(Ext.form.VTypes, {
			// 验证方法
			password : function(val, field) {// val指这里的文本框值，field指这个文本框组件
				if (field.password.password_id) {
					// password是自定义的配置参数，一般用来保存另外的组件的id值
					var pwd = Ext.get(field.password.password_id);// 取得user_password的那个id的值
					return (val == pwd.getValue());// 验证
				}
				return true;
			},
			// 验证提示错误提示信息(注意：方法名+Text)
			passwordText : "两次密码输入不一致!"

		}); 
		
/** 限制textarea 输入长度**/		
 function limtInput() {
 	var length = this.value.mixedLength();
 	if(!length){
 		length = this.value.length
 	}
 	var limitedLength = this.limitedLength;
 	if(!limitedLength){
 		alert("limited length is "+limitedLength);
 		return false;
 	}
	if (event.keyCode != 8 && length >= limitedLength) {
		event.returnValue = false;
		this.value = this.value.substring(0, limitedLength - 1);
	}
}
 
 /** 判断中英文字符串长度 */
 JustIn.util.getLength = function(str){
	return str.match(/[^ -~]/g) == null ? str.length : str.length + str.match(/[^ -~]/g).length;
}

Ext.ux.TabCloseMenu = function(){ 
    var tabs, menu, ctxItem; 
    this.init = function(tp){ 
        tabs = tp; 
        tabs.on('contextmenu', onContextMenu); 
    }
    function onContextMenu(ts, item, e){ 
        if(!menu){ // create context menu on first right click 
            menu = new Ext.menu.Menu([{ 
                id: tabs.id + '-close', 
                text: '关闭该页',
                iconCls : 'close',
                handler : function(){ 
                    tabs.remove(ctxItem); 
                } 
            },{ 
                id: tabs.id + '-close-others', 
                text: '关闭其他页', 
                iconCls : 'close',
                handler : function(){ 
                    tabs.items.each(function(item){ 
                        if(item.closable && item != ctxItem){ 
                            tabs.remove(item); 
                        } 
                    }); 
                } 
            },{
                id: tabs.id + '-close-all', 
                text: '关闭所有页', 
                iconCls : 'close',
                handler : function(){ 
                    tabs.items.each(function(item){ 
                        if(item.closable){
                        	 
                        	//item.destroy();
                            tabs.remove(item); 
                            
                            
                        } 
                    }); 
                } 
            },{ 
                id: tabs.id + '-close-cancel', 
                iconCls : 'undo',
                text: '取 消' 
            }]); 
        } 
        ctxItem = item; 
        var items = menu.items; 
        items.get(tabs.id + '-close').setDisabled(!item.closable); 
        var disableOthers = true; 
        tabs.items.each(function(){ 
            if(this != item && this.closable){ 
                disableOthers = false; 
                return false; 
            } 
        }); 
        items.get(tabs.id + '-close-others').setDisabled(disableOthers); 
        menu.showAt(e.getPoint()); 
    } 
}; 