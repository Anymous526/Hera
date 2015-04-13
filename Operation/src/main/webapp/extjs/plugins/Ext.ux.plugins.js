// vim: ts=4:sw=4:nu:fdc=4:nospell
/**
 * Ext.ux.plugins
 * 此脚本为项目中使用的 Ext 插件
 
 */

Ext.namespace('Ext.ux', 'Ext.ux.plugins');

/**
 * Remote Validator Makes remote (server) field validation easier
 * To be used by form fields like TextField, NubmerField, TextArea, ...
 * @author Ing. Jozef Sakáloš <jsakalos@aariadne.com>
 * @copyright (c) 2007, by Ing. Jozef Sakáloš
 * @date 24. November 2007
 * @version $Id: Ext.ux.plugins.js,v 1.1 2011/04/28 20:28:17 zhangwei Exp $
 * 对Jozef的实时异步验证进行改造以实现更好的应用
 */


Ext.ux.plugins.RemoteValidator = {
	init : function(field) {
		// save original functions
		 
		var isValid = field.isValid;
		var validate = field.validate;

		// apply remote validation to field
		Ext.apply(field, {
			
			hasRemoteValidator : true,//此属性表示该域拥有远程校验
			remoteValid : false
			
			// private
			,
			isValid : function(preventMark) {
				if (this.disabled) {
					return true;
				}
				return isValid.call(this, preventMark) && this.remoteValid;
			}

			// private
			,
			validate : function() {

				//ignoreCheckOnBeginning属性用于初始显示时不会有错误的表单，如修改和详情展示
				if (this.rvOptions.ignoreCheckOnBeginning) {
					this.rvOptions.ignoreCheckOnBeginning = false;
					this.remoteValid = true;
					this.clearInvalid();
					return true;
				}

				var clientValid = validate.call(this);
				if (!this.disabled && !clientValid) {
					return false;
				}
				if (this.disabled || (clientValid && this.remoteValid)) {
					this.clearInvalid();
					return true;
				}
				if (!this.remoteValid) {
					this.markInvalid(this.reason);
					return false;
				}
				return false;
			}

			// private - remote validation request
			,
			validateRemote : function() {
				this.rvOptions.params = this.rvOptions.params || {};
				this.rvOptions.params.field = this.name;
				this.rvOptions.params.value = this.getValue();
				Ext.Ajax.request(this.rvOptions);
			}

			// private - remote validation request success handler
			,
			rvSuccess : function(response, options) {
				var o;
				try {
					o = Ext.decode(response.responseText);
				} catch (e) {
					throw this.cannotDecodeText;
				}
				if ('object' !== typeof o) {
					throw this.notObjectText;
				}
				if (true !== o.success) {
					throw this.serverErrorText + ': ' + o.error;
				}
				var names = this.rvOptions.paramNames;
				this.remoteValid = true === o[names.valid];
				this.reason = o[names.reason];
				this.validate();
			}

			// private - remote validation request failure handler
			,
			rvFailure : function(response, options) {
				throw this.requestFailText
			}

			// private - runs from keyup event handler
			,
			filterRemoteValidation : function(e) {
				if (!e.isNavKeyPress()) {
					this.remoteValidationTask.delay(this.remoteValidationDelay);
				}
			}
		});

		// remote validation defaults
		Ext.applyIf(field, {
					remoteValidationDelay : 350,
					reason : '正在验证',
					cannotDecodeText : 'json数据解码失败',
					notObjectText : '服务器端返回不是对象',
					serverErrorText : '服务器端出错',
					requestFailText : '服务器端请求失败'
				});

		// install event handlers on field render
		field.on({
					render : {
						single : true,
						scope : field,
						fn : function() {
							this.remoteValidationTask = new Ext.util.DelayedTask(
									this.validateRemote, this);
							this.el.on('keyup', this.filterRemoteValidation,
									this);
							this.el.on('mouseup', this.filterRemoteValidation,
									this);
						}
					}
				});

		// setup remote validation request options
		field.rvOptions = field.rvOptions || {};
		Ext.applyIf(field.rvOptions, {
					method : 'post',
					scope : field,
					success : field.rvSuccess,
					failure : field.rvFailure,
					paramNames : {
						valid : 'valid',
						reason : 'reason'
					}
				});
	}
};

/* 添加对hasRemoteValidator属性的判断，以实现在远程校验失败时禁用绑定的按钮*/
Ext.override(Ext.FormPanel, {
    bindHandler : function(){ 
//        if(!this.bound){
//            return false;         }
        var valid = true;
        this.form.items.each(function(f){
            if(!f.isValid(true)||(f.hasRemoteValidator&&!f.remoteValid)){
            	 
                valid = false;
                return false;
            }
        });
        if(this.buttons){
            for(var i = 0, len = this.buttons.length; i < len; i++){
                var btn = this.buttons[i];
                if(btn.formBind === true && btn.disabled === valid){
                    btn.setDisabled(!valid); 
                }
            }
        }
        this.fireEvent('clientvalidation', this, valid);
    }
     
});

/*
 * 对中文字符String的长度计算设为两位字母的长度
 * 将原来Ext判断时使用的value.length替换为value.mixedLength
 */
String.prototype.mixedLength = function() {
		return this.replace(/[^\x00-\xff]/g, "**").length;
	}
Ext.override(Ext.form.TextField, {
        validateValue : function(value){
        if(value.length < 1 || value === this.emptyText){              if(this.allowBlank){
                 this.clearInvalid();
                 return true;
             }else{
                 this.markInvalid(this.blankText);
                 return false;
             }
        }
        if(value.mixedLength() < this.minLength){
            this.markInvalid(String.format(this.minLengthText, this.minLength));
            return false;
        }
        if(value.mixedLength() > this.maxLength){
            this.markInvalid(String.format(this.maxLengthText, this.maxLength));
            return false;
        }
        if(this.vtype){
            var vt = Ext.form.VTypes;
            if(!vt[this.vtype](value, this)){
                this.markInvalid(this.vtypeText || vt[this.vtype +'Text']);
                return false;
            }
        }
        if(typeof this.validator == "function"){
            var msg = this.validator(value);
            if(msg !== true){
                this.markInvalid(msg);
                return false;
            }
        }
        if(this.regex && !this.regex.test(value)){
            this.markInvalid(this.regexText);
            return false;
        }
        return true;
    }
});


/* 改变默认处理，让ComboBox在只读状态下不可改变值  */

Ext.form.ComboBox.override({
	
 expand : function(){
        if(this.isExpanded() || !this.hasFocus || this.readOnly){
            return;
        }
        this.list.alignTo(this.wrap, this.listAlign);
        this.list.show();
        this.innerList.setOverflow('auto');         Ext.getDoc().on('mousewheel', this.collapseIf, this);
        Ext.getDoc().on('mousedown', this.collapseIf, this);
        this.fireEvent('expand', this);
    },
    
onViewClick : function(doFocus) {
	if(!this.readOnly){
   var index = this.view.getSelectedIndexes()[0];
        var r = this.store.getAt(index);
        if(r){
            this.onSelect(r, index);
        }}
        if(doFocus !== false){
            this.el.focus();
        }
}


});

/* 增加对列表查询时的错误信息显示 */
Ext.data.JsonReader.override({

 read : function(response){
 	 	 
        var json = response.responseText;
        var o = eval("("+json+")");
        if(!o) {
            throw {message: "JsonReader.read: Json object not found"};
        }
        if (!o.success) {
					Ext.MessageBox.show({
								title : '错误',
								msg : o.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR,
								fn : function() {

								}

							});
        }
        return this.readRecords(o);
    }

})

Ext.ux.plugins.pageSizePlugin = function(){
	Ext.ux.plugins.pageSizePlugin.superclass.constructor.call(this,{
	
				mode : 'local', 
				id : 'menu_pageSizeSelect',
				width : 50,
				fieldLabel : '每页显示',
				store : new Ext.data.SimpleStore({
							fields : ["text", "value"],
							data : [['15', 15], ['20', 20], ['25', 25], ['30', 30]]
						}),

				displayField : 'text',
				valueField : 'value',
				typeAhead : true,
				forceSelection : true,
				triggerAction : 'all',
				allowBlank : false

	});
};

Ext.extend(Ext.ux.plugins.pageSizePlugin,Ext.form.ComboBox,{

		init:function(paging){
			paging.on('render',this.onInitView,this);
		},
		onInitView:function(paging){
			paging.add('-', '每页显示：', this, '条记录');
			this.setValue(paging.pageSize);
			this.on('select',this.onPageSizeChanged,paging);
		},
		onPageSizeChanged:function(combo){
			var pageSize = parseInt(pageSizeCombo.getValue(), 10);
			paging.pageSize = pageSize;
			paging.store.load({
							params : {
								start : 0,
								limit : pageSize
							}
			});
		}
})

// end of file

