var getAuthEditForm = function(id, mav, method, win) {

	var model = mav.model;
	
	var forShow = false;// 用于详情界面
	var forCreate = false;// 用于新增界面
	var forUpdate = false;// 用于修改界面

	if (method == 'show') {
		forShow = true;
		forUpdate = false;
		forCreate = false;
		win.setTitle('系统权限详情');
	}
	if (method == 'edit') {
		forShow = false;
		forUpdate = true;
		forCreate = false;
		win.setTitle('修改系统权限');

	}
	if (method == 'create') {
		forShow = false;
		forUpdate = false;
		forCreate = true;
		win.setTitle('新增系统权限');
	}
	

	Ext.form.Field.prototype.msgTarget = 'side';
	var authEditForm = new Ext.form.FormPanel({
		id : 'auth_edit-Form',
		labelWidth : 75, // label settings here cascade unless
		frame : true,
		bodyStyle : 'padding:5px 50px 0',
		width : 400,
		anchor : '100% 100% ',
		monitorValid : true,
		layout : 'form',
		defaults : {
			width : 200
		},
		items : [

		{
					xtype : 'textfield',
					fieldLabel : '权限名称',
					name : 'authName',
					value : model.authName,
					readOnly : forShow,
					allowBlank : false,
					blankText : '权限名称不能为空',
					maxLength : 32,
					maxLengthText : '权限名称超过最大长度',
					regex : /^AUTH_[a-zA-Z0-9_]+$/,
					regexText : '权限名称以AUTH_开头，由数字、26个英文字母或下划线组成',
					cls :forShow?'item-field-readonly': '',
					plugins : [Ext.ux.plugins.RemoteValidator],
					rvOptions : {
						url : contextPath + '/auth.do?method='+(forCreate?'checkNameNew':'checkNameUpdate'),
						params :{id:model.id},
						ignoreCheckOnBeginning : !forCreate
					},
        		listeners : {
        			render : function() { this.validate(); }
        		}
				}, {
					xtype : 'textfield',
					fieldLabel : '权限描述',
					name : 'description',
					value : model.description,
					readOnly : forShow,
					cls :forShow?'item-field-readonly': '',
					allowBlank : true,
					maxLength : 255,
					maxLengthText : '权限描述超过最大长度（255位）'
					}
					],

		buttons : [{
			
			 
			text : '确定',
			formBind : true,
			hidden : method == 'show',
			handler : function() {
				authEditForm.getEl().mask('处理中....');
				authEditForm.form.doAction('submit', {
							url : contextPath + '/auth.do?method='
									+ (method == 'create' ? 'save' : 'update'),
							method : 'post',
							success : function(form, action) {
								var data = Ext
										.decode(action.response.responseText);
								Ext.MessageBox.show({
											title : '成功',
											msg : data.msg,
											buttons : Ext.MessageBox.OK,
											icon : Ext.MessageBox.INFO,
											fn : function() {
												win.close();

											}

										});
								authEditForm.getEl().unmask();

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
								authEditForm.getEl().unmask();
							}
						});
			}
		}, {
			text : '返回',
			handler : function() {
				authEditForm.getEl().mask();
				win.close();
			}
		}]
	});

	if (forUpdate) {
		authEditForm.add(new Ext.form.TextField({
					xtype : 'textfield',
					name : 'id',
					value : model.id,
					hidden : true,
					hideLabel : true,
					hideMode : 'offsets'

				}));
	}

	return authEditForm;

};
