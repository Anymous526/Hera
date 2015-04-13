Ext.namespace('Ext.ux');
Ext.ux.MerchantForm = Ext.extend(Ext.Window, {

	height : 300,
	width :450,
	resizable : true,
	title : "商户合同管理",
	modal : true,
	constrain : false,
	constrainHeader : false,
	closeAction : 'close',
	stateful : true,
	closable : true,
	border : true,
	regFlag : '',
	layout : 'fit',
	method : '',
	pid : '',
	url : '',
	initComponent : function() {
		this.loadData();
		this.loadComponent();
		this.items = [this.formPanel];
		Ext.QuickTips.init();
		this.addListener();
		Ext.ux.MerchantForm.superclass.initComponent.call(this);
		if (this.method == 'create') {
			this.title = "新增合同";
		} else if (this.method == 'update') {
			this.title = "修改合同";
		} else {
			this.title = "合同详细信息";
		}
	},

	loadComponent : function() {
		var ppForm = this;
		Ext.form.Field.prototype.msgTarget = 'side';
		this.formPanel = new Ext.form.FormPanel({
			id : 'formPanel',
			labelWidth : 135,
			border : false,
			bodyStyle : 'padding:5px 15px 5px;background-image:url('
					+ contextPath
					+ '/images/ext_images/background-lightblue.JPG);background-position:center;background-repeat:repeat;',
			monitorValid : true,
			width : 420,
			layout : 'form',
			autoScroll : true,
			fileUpload: true,  
			defaults : {
				width : 245
			},
			
			items : [
//			{
//				xtype : 'textfield',
//				id : 'merchantCode',
//				name : 'merchantCode',
//				fieldLabel : '商户编号',
//				value : '',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				regex : /^[0-9]{15}$/,
//				regexText : '商户编号由15位数字组成',
//				allowBlank : false,
//				blankText : '商户编号不能为空',
//				maxLength : 15,
//				maxLengthText : '商户编号超过最大长度'
//			},
			new Ext.form.ComboBox({
				store : new Ext.data.JsonStore({
					remoteSort : true,
					url : contextPath + '/mi.do?method=listNameAndCode',
					totalProperty : 'totalProperty',
					root : 'root',
					id : "id",
					fields : ['code', 'name']
				}),
				mode : 'remote',
				id : 'merchantCodeCombo',
				fieldLabel : '所属商户',
				hiddenName : 'merchantCode',
				displayField : 'name',
				valueField : 'code',
				value : '',
				editable : false,
				allowBlank : false,
				typeAhead : true,
				triggerAction : 'all',
				forceSelection : true,
				selectOnFocus : true,
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : ppForm.method =='load' ? '' : '请选择...',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly' : '',
				resizable : true,
				minChars : 0,
				pageSize : 10,
				queryParam : 'query'
			}),
			{
				xtype : 'textfield',
				id : 'contractNumber',
				name : 'contractNumber',
				fieldLabel : '合同编号',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9-_()]*$/,
				regexText : '合同编号由数字、英文字母以及部分特殊字符(如括弧()横杠"-"或下划线"_"等)组成',
				allowBlank : false,
				blankText : '合同编号不能为空',
				maxLength : 20,
				maxLengthText : '合同编号超过最大长度',
				plugins : ppForm.method != 'load' 
					? [Ext.ux.plugins.RemoteValidator]
					: '',
				rvOptions : {
					url : contextPath
							+ '/mc.do?method='
							+ (ppForm.method == 'create'
									? 'checkContractNew'
									: 'checkContractUpdate'),
					params : {
						id : ppForm.pid
					},
					ignoreCheckOnBeginning : ppForm.method != 'create'
				}
			},{
				xtype : 'textfield',
				id : 'contractSigner',
				name : 'contractSigner',
				fieldLabel : '合同签订人',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
				regexText : '合同签订人由数字、英文字母及中文汉字组成',
				allowBlank : true,
				maxLength : 20,
				maxLengthText : '字段超过最大长度'
			},{
				xtype : 'textfield',
				id : 'contractAuditor',
				name : 'contractAuditor',
				fieldLabel : '合同复审员',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
				regexText : '合同签订人由数字、英文字母及中文汉字组成',
				allowBlank : true,
				maxLength : 20,
				maxLengthText : '字段超过最大长度'
			},{
				xtype : 'datefield',
				id : 'signDate',
				name : 'signDate',
				fieldLabel : '合同签订时间',
				format : 'Ymd',
				value : '',
				editable : false,
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
//				allowBlank : false
			},{
				xtype : 'datefield',
				id : 'validDateBegin',
				name : 'validDateBegin',
				fieldLabel : '合同有效期开始日期',
				format : 'Ymd',
				value : '',
				editable : false,
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				allowBlank : false,
				listeners : {
					focus : function(){
						var end = Ext.getCmp('validDateEnd').getValue();
						if(end != ''){
							this.setMaxValue(end);
						}
					}
				}		
			},{
				xtype : 'datefield',
				id : 'validDateEnd',
				name : 'validDateEnd',
				fieldLabel : '合同有效期结束日期',
				format : 'Ymd',
				value : '',
				editable : false,
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				allowBlank : false,
				listeners : {
					focus : function(){
						var begin = Ext.getCmp('validDateBegin').getValue();
						if(begin != ''){
							this.setMinValue(begin);
						}
					}
				}		
			},
			{
				id : 'upload',
				name : 'upload',
				inputType : "file",
				fieldLabel : '上传合同文件',
				editable : true,
				xtype : 'textfield',
				disabled : ppForm.method == 'load' ? true : false,
				listeners : {
					render : function(){
						if(ppForm.method == 'load'){
							this.getEl().up('.x-form-item').setDisplayed(false);
						}
					}
				}
						
			}
	        
			],

			buttons : [{
				text : '确定',
				id : 'submitButton',
				formBind :  true,
				hidden : ppForm.method == 'load' ? true : false 
			}, {
				text : '返回',
				handler : function() {
					Ext.getBody().unmask();
					ppForm.formPanel.getEl().mask();
					ppForm.close();
				}
			}]
		});
	},
	loadData : function() {
		var ppForm = this;
		if (ppForm.pid != '') {
			ppForm.loadModel('load', ppForm.pid);
		} else {
			
		}
	},
	
	loadModel : function(method, id) {
		var ppForm = this;
		var hiddenTarget = function(target) {
			target.setValue(null);
			target.allowBlank = true;
			target.clearInvalid();
			target.getEl().up('.x-form-item').setDisplayed(false);
		};
		
		var showTarget = function(target) {
			target.enable();
			target.show();
			target.getEl().up('.x-form-item').setDisplayed(true);
			if(target.getValue() == null || target.getValue() == ''){
				target.markInvalid();
			}
		};
		Ext.getBody().mask("系统正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : ppForm.url,
			params : {
				method : method,
				id : id
			},
			timeout : 40000,
			success : function(response) {
				var text = Ext.decode(response.responseText);
				if(!text.success){
					Ext.MessageBox.show({
						title : '提示',
						msg : text.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.INFO
					});
					Ext.getBody().unmask();
					return;
				}
				var data = Ext.decode(text.msg);
				var rc = new Ext.data.Record(data);
				ppForm.formPanel.getForm().loadRecord(rc);
				Ext.getBody().unmask();
			},
			failure : function(response) {
				Ext.MessageBox.show({
							title : '系统执行失败',
							msg : '无法连接到服务器,或服务器操作异常!',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.INFO
						});
				Ext.getBody().unmask();
			}
		});

	},

	resultMessageShow : function(data) {
		var ppForm = this;
		if (data.success) {
			ppForm.showMessageInfo(data.msg);
		} else {
			ppForm.showMessageInfo(data.msg);
		}
	},
	showMessageInfo : function(message) {
		Ext.MessageBox.show({
			title : '提示',
			msg : message,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.INFO
		});
	},
	addListener : function(){
		var ppForm = this;
		var data ;
		var hiddenTarget = function(target){
			target.setValue(null);
			target.allowBlank = true;
			target.clearInvalid();
			target.getEl().up('.x-form-item').setDisplayed(false);
		}; 
		var showTarget = function(target) {
			target.allowBlank = false;
			target.getEl().up('.x-form-item').setDisplayed(true);
			target.validate();
		};
		var showTargetNoValidate = function(target) {
//			target.allowBlank = false;
			target.getEl().up('.x-form-item').setDisplayed(true);
//			target.validate();
		};
		var validateTarget = function(target){
			target.allowBlank = false;
			target.validate();
		};
		var validateTargetAllowBlank = function(target){
			target.allowBlank = true;
			target.validate();
		};
		var showMessageInfo = function(message) {
			Ext.MessageBox.show({
				title : '提示',
				msg : message,
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
		};
		Ext.getCmp('submitButton').on('click', function() {
			
			ppForm.formPanel.getEl().mask('处理中....');
			ppForm.formPanel.form.doAction('submit', {
						url : ppForm.url,
						params : {
							method : ppForm.method,
							id : ppForm.pid
						},
						method : 'post',
						timeout : 40000,
						success : function(form, action) {
							var data = Ext.decode(action.response.responseText);
							if (data.success) {
								ppForm.close();
							}
							ppForm.resultMessageShow(data);
							ppForm.formPanel.getEl().unmask();

						},
						failure : function(form, action) {
							var data = Ext.decode(action.response.responseText);
							ppForm.resultMessageShow(data);
							ppForm.formPanel.getEl().unmask();
						}

					});
		});
		
	}
});

Ext.reg('merchantForm', Ext.ux.MerchantForm);