Ext.namespace('Ext.ux');
Ext.ux.ReasonForm = Ext.extend(Ext.Window, {
	
	height : 180,
	width : 280,
	resizable : false,
	title : "商户操作备注信息",
	modal : true,
	constrain : false,
	constrainHeader : false,
	closeAction : 'close',
	stateful : true,
	closable : true,
	border : true,
	layout : 'fit',
	method : '',
	methodName : '',
	pid : '',
	url : '',
	grid : '',
	resultMessageShow : null,
	initComponent : function() {
		this.loadComponent();
		this.items = [this.formPanel];
		this.addListener();
		
		Ext.QuickTips.init();
		Ext.ux.ReasonForm.superclass.initComponent.call(this);
//		if(this.method=='audNotPass'){
		if(this.method=='aud'){
			methodName = '审核不通过';
			this.title = '商户'+methodName;
		}else if(this.method=='suspend'){
			methodName = '冻结' ;
			this.title = '商户'+methodName;
		}else if(this.method=='recovery'){
			methodName = '解冻' ;
			this.title = '商户'+methodName;
		}else if(this.method=='logout'){
			methodName = '注销' ;
			this.title = '商户'+methodName;
		}
	},
	loadComponent : function() {
		var ppForm = this;
		
		Ext.form.Field.prototype.msgTarget = 'side';
		this.formPanel = new Ext.form.FormPanel({
		id : 'formPanel',
		labelWidth : 10, 
		frame : true,
		bodyStyle : 'padding:5px 10px 0',
		width : 280,
		anchor : '100% 100% ',
		monitorValid : true,
		layout : 'form',
		defaults : {
			width : 200
		},
		items : [
			new Ext.form.Label({
				text : '请输入原因:'
			}),
			{
				xtype : 'textarea',
				width : 200,
				height : 60,
				labelSeparator : '',
				id : 'desc',
				name : 'desc',
				value : '',
				allowBlank : true,
				regex : /^[^<>&'"\|\\\r\n]+$/,
				regexText : '不能换行或使用特殊字符',
				maxLength : 180,
				maxLengthText : '字段超过最大长度'
			}],
			buttons : [{
				text : '确定',
				id : 'submitButton',
				formBind : true
			}, {
				text : '返回',
				handler : function() {
					ppForm.formPanel.getEl().mask();
					ppForm.close();
				}
			}]
		});
	},
	
    showMessage : function(message){	
		Ext.MessageBox.show({
			title : '提示',
			msg : message,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.INFO
		});
	},
	resultMessageShow : function(data) {
		var ppForm = this;
		if (data.success) {
			ppForm.showMessage(data.msg);
		} else {
			ppForm.showMessage(data.msg);
		}
	},
	addListener : function() {
		var ppForm = this;
		if(ppForm.method == 'audNotPass'){
			ppForm.method = 'aud';
		}
		Ext.getCmp('submitButton').on('click', function() {
			ppForm.formPanel.getEl().mask('处理中....');
			ppForm.formPanel.form.doAction('submit', {
				url : ppForm.url,
				params : {
					method : ppForm.method,
					id : ppForm.pid
				},
				method : 'post',
				timeout : 120000,
				success : function(form, action) {
					var data = Ext.decode(action.response.responseText);
					if (data.success) {
						ppForm.close();
					}
					ppForm.resultMessageShow(data);
//					ppForm.grid.store.reload();
					ppForm.formPanel.getEl().unmask();

				},
				failure : function(form, action) {
					var data = Ext.decode(action.response.responseText);
					ppForm.resultMessageShow(data);
//					ppForm.grid.store.reload();
					ppForm.formPanel.getEl().unmask();
				}
			});
		});
	}
	
});

Ext.reg('reasonForm', Ext.ux.ReasonForm);