var getResEditForm = function(id, mav, method, win) {

	var model = mav.model;
	
	var forShow = false;// 用于详情界面
	var forCreate = false;// 用于新增界面
	var forUpdate = false;// 用于修改界面

	if (method == 'show') {
		forShow = true;
		forUpdate = false;
		forCreate = false;
		win.setTitle('资源详情');
	}
	if (method == 'edit') {
		forShow = false;
		forUpdate = true;
		forCreate = false;
		win.setTitle('修改资源');

	}
	if (method == 'create') {
		forShow = false;
		forUpdate = false;
		forCreate = true;
		win.setTitle('新增资源');
	}
	
	var resTypeList = eval(model.resTypeItemList);
	var resTypeCombo = new Ext.form.ComboBox({

				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'resTypelist',
				width : 102,
				fieldLabel : '资源类型',
				store : new Ext.data.SimpleStore({
							fields : ["text", "value"],
							data : resTypeList
						}),
				hiddenName : 'type',// 真正存储选择值的隐藏参数名
				displayField : 'text',
				valueField : 'value',
				value : model.resType, 
				editable : false,
				typeAhead : true,
				forceSelection : true,
				triggerAction : 'all',
				readOnly : forShow,
				allowBlank : false,
				emptyText : '请选择资源类型',
				blankText : '请选择资源类型',
				selectOnFocus : true,
				cls :forShow?'item-field-readonly':'',
				listeners : {
            		render : function() { this.validate(); }
            	}
			});

	Ext.form.Field.prototype.msgTarget = 'side';
	var resEditForm = new Ext.form.FormPanel({
		id : 'res_edit-Form',
		labelWidth : 65, // label settings here cascade unless
		frame : true,
		bodyStyle : 'padding:5px 15px 5px',
		width : 400,
		anchor : '100% 100% ',
		monitorValid : true,
		layout : 'form',
		defaults : {
			width : 280
		},
		items : [

		{
					xtype : 'textfield',
					fieldLabel : '资源名称',
					name : 'resName',
					value : model.resName,
					readOnly : forShow,
					allowBlank : false,
					blankText : '资源名称不能为空',
					maxLength : 20,
					maxLengthText : '资源名称超过最大长度（20位）',
					regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
					regexText : '资源名称应由数字、26个英文字母以及中文汉字组成',
					cls :forShow?'item-field-readonly': '',
					plugins : [Ext.ux.plugins.RemoteValidator],
					rvOptions : {
						url : contextPath + '/res.do?method='+(forCreate?'checkNameNew':'checkNameUpdate'),
						params :{id:model.id},
						ignoreCheckOnBeginning : !forCreate
					},
					listeners : {
            			render : function() { this.validate(); }
            		}
				}, {
					xtype : 'textfield',
					fieldLabel : '资源串',
					name : 'filterString',
					value : model.filterString,
					readOnly : forShow,
					cls :forShow?'item-field-readonly': '',
					allowBlank : false,
					blankText : '资源串不能为空',
					maxLength : 255,
					maxLengthText : '资源串超过最大长度（255位）',
					listeners : {
            			render : function() { this.validate(); }
            		}
				},  resTypeCombo],

		buttons : [{
			
			 
			text : '确定',
			formBind : true,
			hidden : method == 'show',
			handler : function() {
				resEditForm.getEl().mask('处理中....');
				resEditForm.form.doAction('submit', {
							url : contextPath + '/res.do?method='
									+ (method == 'create' ? 'save' : 'update'),
							method : 'post',
							success : function(form, action) {
								var data = Ext.decode(action.response.responseText);
								Ext.MessageBox.show({
											title :data.success?'成功':'失败',
											msg : data.msg,
											buttons : Ext.MessageBox.OK,
											icon : data.success?Ext.MessageBox.INFO:Ext.MessageBox.ERROR,
											fn : function() {
												win.close();

											}

										});
								resEditForm.getEl().unmask();

							},
							failure : function(form, action) {
								var data = Ext
										.decode(action.response.responseText);
								Ext.MessageBox.show({
											title : '错误',
											msg : data.msg,
											buttons : Ext.MessageBox.OK,
											icon : Ext.MessageBox.ERROR
										});
								resEditForm.getEl().unmask();
							}
						});
			}
		}, {
			text : '返回',
			handler : function() {
				resEditForm.getEl().mask();
				win.close();
			}
		}]
	});

	if (forUpdate) {
		resEditForm.add(new Ext.form.TextField({
					xtype : 'textfield',
					name : 'id',
					value : model.id,
					hidden : true,
					hideLabel : true,
					hideMode : 'offsets'

				}));
	}

	return resEditForm;

};
