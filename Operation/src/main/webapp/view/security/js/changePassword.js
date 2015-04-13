var getPassWordForm = function(loginId, win) {


	Ext.form.Field.prototype.msgTarget = 'side';
	var pwForm = new Ext.FormPanel({
		labelWidth : 100,
		width : 260,
		frame : true,
		bodyStyle : 'padding:5px 10px 0',
		monitorValid : true,
		layout : 'form',
		items : [{
					xtype : 'textfield',
					name : 'loginId',
					fieldLabel : ' 登录ID',
					allowBlank : false,
					value : loginId,
					readOnly : true,
					cls:'item-field-readonly'
				}, {
					xtype : 'textfield',
					inputType : 'password',
					name : 'passwordOld',
					fieldLabel : ' 旧密码',
					allowBlank : false,
					blankText : '密码不能为空',
					maxLength : 32,
					maxLengthText : '密码超过最大长度（32位）',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '密码应由数字、26个英文字母以及中文汉字组成'
				}, {
					xtype : 'textfield',
					inputType : 'password',
					id : 'password',
					name : 'password',
					fieldLabel : ' 新密码',
					allowBlank : false,
					blankText : '密码不能为空',
					maxLength : 32,
					maxLengthText : '密码超过最大长度（32位）',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '密码应由数字、26个英文字母以及中文汉字组成'
				}, {
					xtype : 'textfield',
					inputType : 'password',
					name : 'passwordConfirm',
					fieldLabel : ' 确认新密码',
					allowBlank : false,
					blankText : '密码不能为空',
					maxLength : 32,
					maxLengthText : '密码超过最大长度（32位）',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '密码应由数字、26个英文字母以及中文汉字组成',
					password:{password_id:'password'},  
 					vtype:'password'  
				}],

		buttons : [{

			text : '确定',
			formBind : true,
			handler : function() {
				pwForm.getEl().mask('处理中....');
				pwForm.form.doAction('submit', {
							url : contextPath
									+ '/user.do?method=changePassword',
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
								pwForm.getEl().unmask();

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
								pwForm.getEl().unmask();
							}
						});
			}
		}, {
			text : '返回',
			handler : function() {

				win.close();
			}
		}]
	});

	pwForm.add(new Ext.form.TextField({
				xtype : 'textfield',
				name : 'loginId',
				value : loginId,
				hidden : true,
				hideLabel : true,
				hideMode : 'offsets'

			}));

	return pwForm;

};
