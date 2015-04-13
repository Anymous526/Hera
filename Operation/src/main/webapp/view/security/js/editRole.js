var getRoleEditForm = function(id, mav, method, win) {

	var model = mav.model;

	var forShow = false;// 用于详情界面
	var forCreate = false;// 用于新增界面
	var forUpdate = false;// 用于修改界面

	if (method == 'show') {
		forShow = true;
		forUpdate = false;
		forCreate = false;
		win.setTitle('角色详情');
	}
	if (method == 'edit') {
		forShow = false;
		forUpdate = true;
		forCreate = false;
		win.setTitle('修改角色');

	}
	if (method == 'create') {
		forShow = false;
		forUpdate = false;
		forCreate = true;
		win.setTitle('新增角色');
	}

	Ext.form.Field.prototype.msgTarget = 'side';
	var roleEditForm = new Ext.form.FormPanel({
		id : 'role_edit-Form',
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
			fieldLabel : '角色名称',
			name : 'roleName',
			value : model.roleName,
			readOnly : forShow,
			allowBlank : false,
			blankText : '角色名不能为空',
			maxLength : 20,
			maxLengthText : '角色名称超过最大长度（20位）',
			regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
			regexText : '角色名称应由数字、26个英文字母以及中文汉字组成',
			cls : forShow ? 'item-field-readonly' : '',
			plugins : [Ext.ux.plugins.RemoteValidator],
			rvOptions : {
				url : contextPath + '/role.do?method='
						+ (forCreate ? 'checkNameNew' : 'checkNameUpdate'),
				params : {
					id : model.id
				},
				ignoreCheckOnBeginning : !forCreate
			},
        	listeners : {
        		render : function() { this.validate(); }
        	}
			
		}, {
			id : 'reasonLable',
			xtype : 'textarea',
			width : 200,
			height : 70,
			fieldLabel : '角色描述',
			id : 'description',
			name : 'description',
			value : model.description,
			maxLength : 255,
			maxLengthText : '角色描述超过最大长度（255位）',
			readOnly : forShow,
			cls : forShow ? 'textarea-readonly' : '',
			autoCreate : {
				tag : "textarea",
				cols : 60,
				wrap : 'hard',
				onKeyDown : "if (event.keyCode!=8&&this.value.mixedLength()>=255){event.returnValue=false;while(this.value.mixedLength()>255)this.value = this.value.substring(0,this.value.length-1);}",
				onKeyUp : "if (event.keyCode!=8&&this.value.mixedLength()>=255){while(this.value.mixedLength()>255)this.value = this.value.substring(0,this.value.length-1);}"
			}

		}],

		buttons : [{

			text : '确定',
			formBind : true,
			hidden : method == 'show',
			handler : function() {
				roleEditForm.getEl().mask('处理中....');
				roleEditForm.form.doAction('submit', {
							url : contextPath + '/role.do?method='
									+ (method == 'create' ? 'save' : 'update'),
							method : 'post',
							success : function(form, action) {
								var data = Ext
										.decode(action.response.responseText);
								Ext.MessageBox.show({
											title : data.success ? '成功' : '失败',
											msg : data.msg,
											buttons : Ext.MessageBox.OK,
											icon : data.success
													? Ext.MessageBox.INFO
													: Ext.MessageBox.ERROR,
											fn : function() {
												win.close();

											}

										});
								roleEditForm.getEl().unmask();

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
								roleEditForm.getEl().unmask();
							}
						});
			}
		}, {
			text : '返回',
			handler : function() {
				roleEditForm.getEl().mask();
				win.close();
			}
		}]
	});

	if (forUpdate) {
		roleEditForm.add(new Ext.form.TextField({
					xtype : 'textfield',
					name : 'id',
					value : model.id,
					hidden : true,
					hideLabel : true,
					hideMode : 'offsets'

				}));
	}

	return roleEditForm;

};
