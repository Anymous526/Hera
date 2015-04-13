var getMenuEditForm = function(id, mav, method, win) {

	var model = mav.model;
	
	var forShow = false;// 用于详情界面
	var forCreate = false;// 用于新增界面
	var forUpdate = false;// 用于修改界面

	if (method == 'show') {
		forShow = true;
		forUpdate = false;
		forCreate = false;
		win.setTitle('菜单详情');
	}
	if (method == 'edit') {
		forShow = false;
		forUpdate = true;
		forCreate = false;
		win.setTitle('修改菜单');

	}
	if (method == 'create') {
		forShow = false;
		forUpdate = false;
		forCreate = true;
		win.setTitle('新增菜单');
	}
	
	var menuList = eval(model.menuItemList);
	menuList.unshift(['无', 0]);
	var menuCombo = new Ext.form.ComboBox({

				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'menulist',
				width : 200,
				fieldLabel : '父菜单',
				store : new Ext.data.SimpleStore({
							fields : ["text", "value"],
							data : menuList
						}),
				hiddenName : 'parentId',// 真正存储选择值的隐藏参数名
				displayField : 'text',
				valueField : 'value',
				value : model.parentId?model.parentId:0,// 默认值
				readOnly : forShow,
				allowBlank : true,
				editable : true,
				typeAhead : true,
				forceSelection : true,
				triggerAction : 'all',
				selectOnFocus : true,
				cls :forShow?'item-field-readonly': ''

			});

	Ext.form.Field.prototype.msgTarget = 'side';
	var menuEditForm = new Ext.form.FormPanel({
		id : 'menu_edit-Form',
		labelWidth : 70, // label settings here cascade unless
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
					id : 'menuName', 
					name : 'menuName',
					fieldLabel : '菜单名称',
					value : model.menuName,
					readOnly : forShow,
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '菜单名称应由数字、26个英文字母以及中文汉字组成',
					allowBlank : false,
					blankText : '菜单名不能为空',
					maxLength : 32,
					maxLengthText : '菜单名称超过最大长度（32位）',
					cls :forShow?'item-field-readonly': '',
        			listeners : {
        				render : function() { this.validate(); }
        			}
				}, {
					xtype : 'textfield',
					fieldLabel : 'URL',
					name : 'url',
					value : model.url,
					readOnly : forShow,
					cls :forShow?'item-field-readonly': '',
					regex :/^\/{1}[a-zA-Z0-9]+[a-zA-Z0-9\/.?&=]*$/,
					regexText : '请按url格式输入',
					allowBlank : true,
					maxLength : 100,
					maxLengthText : 'URL超过最大长度（100位）'
				}, {
					xtype : 'textfield',
					fieldLabel : '显示顺序号',
					name : 'orderNo',
					value : model.orderNo?model.orderNo:0,
					readOnly : forShow,
					cls :forShow?'item-field-readonly': '',
					regex : /^[0-9]*$/,
					regexText : '请输入数字',
					allowBlank : false,
					blankText : '菜单名不能为空(默认为0，置最后)',
					maxLength : 5,
					maxLengthText : '序号超过最大位度（5位）'
				},  menuCombo],

		buttons : [{
			
			 
			text : '确定',
			formBind : true,
			hidden : method == 'show',
			handler : function() {
				menuEditForm.getEl().mask('处理中....');
				menuEditForm.form.doAction('submit', {
							url : contextPath + '/menu.do?method='
									+ (method == 'create' ? 'save' : 'update'),
							method : 'post',
							success : function(form, action) {
								var data = Ext.decode(action.response.responseText);
								Ext.MessageBox.show({
											title :data.success?'成功':'失败',
											msg : data.msg + "  ",
											width :200,
											buttons : Ext.MessageBox.OK,
											icon : data.success?Ext.MessageBox.INFO:Ext.MessageBox.ERROR,
											fn : function() {
												win.close();

											}

										});
								menuEditForm.getEl().unmask();

							},
							failure : function(form, action) {
								var data = Ext
										.decode(action.response.responseText);
								Ext.MessageBox.show({
											title : '错误',
											msg : data.msg + "  ",
											width :200,
											buttons : Ext.MessageBox.OK,
											icon : Ext.MessageBox.ERROR
										});
								menuEditForm.getEl().unmask();
							}
						});
			}
		}, {
			text : '返回',
			handler : function() {
				menuEditForm.getEl().mask();
				win.close();
			}
		}]
	});

	if (forUpdate) {
		menuEditForm.add(new Ext.form.TextField({
					xtype : 'textfield',
					name : 'id',
					value : model.id,
					hidden : true,
					hideLabel : true,
					hideMode : 'offsets'

				}));
	}

	return menuEditForm;

};
