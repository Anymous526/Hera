Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = ''; 
	var userName ='';
	var rowNumber;
	var pageSize = 20;// 页数
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var selectedIds = new Array(); // 保存选择结果	
	
	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect : true,
				keepSelections : true,
				selectedIds : selectedIds

			});// 选择框模型
			
	function enableButtons(){  
		 
		Ext.getCmp('alterBtn').enable();// 修改按钮
		Ext.getCmp('deleteBtn').enable();// 删除按钮
		Ext.getCmp('detailBtn').enable();// 详情按钮
		Ext.getCmp('selectRoleBtn').enable();// 选择角色按钮
		Ext.getCmp('selectCityBtn').enable();// 选择角色按钮
		Ext.getCmp('resetPassWordBtn').enable();// 重置密码按钮
		
	}
	
	function disableButtons(){
		
		Ext.getCmp('alterBtn').disable();// 修改按钮
		Ext.getCmp('deleteBtn').disable();// 删除按钮
		Ext.getCmp('detailBtn').disable();// 详情按钮
		Ext.getCmp('selectRoleBtn').disable();// 选择角色按钮
		Ext.getCmp('selectCityBtn').disable();// 选择角色按钮
		Ext.getCmp('resetPassWordBtn').disable();// 重置密码按钮
		
	}

	var singleSelectHandler = function(self, rowIndex, record) {
		
		enableButtons();
		id = record.get('id');
		userName = record.get('userName');
		selectedIds[0] = id;
		rowNumber = rowIndex;
	};

	var singleDeselectHandler = function() {
		 disableButtons();
	};

	selectModel.on('rowselect', singleSelectHandler);
	selectModel.on('rowdeselect', singleDeselectHandler);

	var clearSelections  = function() {
		selectedIds = new Array();
		selectModel.selectedIds = selectedIds;
		selectModel.clearSelections();
	};
	
	var colM = new Ext.grid.ColumnModel([

			new Ext.grid.RowNumberer(), selectModel,


			{
				header : "用户名称 ",
				dataIndex : "userName",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}, {
				header : "真实姓名",
				dataIndex : "realName",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "手机号",
				dataIndex : "mobile",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "电子邮件",
				dataIndex : "email",
				width : 200,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "状态",
				dataIndex : "status",
				width : 50,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			}

	]);

	colM.defaultSortable = true;
	colM.autoExpandColumn = 'userName';

	var ds = new Ext.data.Store({

				remoteSort : true,
				proxy : new Ext.data.HttpProxy({
							url : contextPath + '/user.do?method=list'
						}),
				reader : new Ext.data.JsonReader({
							root : 'root',
							totalProperty : 'totalProperty',
							id : "id"
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'userName',
									mapping : 'userName'
								}, {
									name : 'realName',
									mapping : 'realName'
								},{
									name : 'mobile',
									mapping : 'mobile'
								},{
									name : 'email',
									mapping : 'email'
								},{
									name : 'status',
									mapping : 'status'
								}
						])
			});
	ds.setDefaultSort('id', 'ASC');
	// 这里很关键，如果不加，翻页后搜索条件就变没了，这里的意思是每次数据载入前先把搜索表单值加上去，这样就做到了翻页保留搜索条件了
	ds.on('beforeload', function() {
		disableButtons();
				Ext.apply(this.baseParams, {

								'search_userName_like' : Ext.get('user_name').dom.value,
								'search_realName_like' : Ext.get('real_name').dom.value
//								'search_status' : Ext.get('user_status').dom.value
							
						});
			});

	// dwr预处理编辑操作，调用回调打开编辑窗口
	 
	var getMav = function() { 
		userServ.editUser(id.toString(), method, openEditWin);
	};
	
	// 打开编辑/详情窗口 getRoleEditForm实现在editUser.js
	var openEditWin = function(mav) {		
		var model = mav.model;
		
		win = new Ext.Window({
			height : 280,
			width : 400,
			resizable : false,
			title : "用户详细信息",
			modal : true,
			constrain : false,
			constrainHeader : false,
			closeAction : 'close',
			stateful : true,
			closable : true,
			border : true,
			layout : 'fit'

		});

		win.on('beforeclose', function(){
			ds.reload();
			disableButtons();
		});

		var editForm = getRoleEditForm(id.toString(), mav, method, win);

		win.add(editForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();

	};

	// 删除按钮调用
	function deleteUser(btn) {
		if (btn == 'yes') {

			var handleResult = function(modelAndResult) {
				if (modelAndResult.success) {
					Ext.Msg.alert('结果', modelAndResult.message);
					ds.reload();
					disableButtons();

				} else {
					Ext.Msg.alert('错误', '删除失败');
				}
			};
			Ext.getBody().mask("正在执行删除,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/user.do?method=removeSelected&&id='
								+ id,
						success : function(response,options) {

							data = Ext.decode(response.responseText);
							Ext.MessageBox.show({
										title : data.success ? '成功' : '操作失败',
										msg : data.msg,
										buttons : Ext.MessageBox.OK,
										icon : data.success
												? Ext.MessageBox.INFO
												: Ext.MessageBox.ERROR,
										fn : function() {
											ds.reload();
											disableButtons();
										}

									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '删除失败',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											ds.reload();
											disableButtons();
										}
									});
							Ext.getBody().unmask();
						}

					});

		}
	}

	// 用已选择的角色列表更新用户相关角色
	var resetRole = function(userId,selectedRoleIds,roleWin){
		
		var jsonStr = "{userId:"+userId+","+"length:"+selectedRoleIds.length.toString()+",";
		for(var i=0;i< selectedRoleIds.length;i++){
			jsonStr += "roleId"+i.toString();
			jsonStr += ":";
			jsonStr += selectedRoleIds[i].toString();
			jsonStr += ",";
		}
		jsonStr = jsonStr.substr(0,jsonStr.length-1);
		jsonStr += "}";
		var jsonData = Ext.decode(jsonStr);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/user.do?method=setRoles',
						params : jsonData,
						success : function(response,options) {

							data = Ext.decode(response.responseText);
							Ext.MessageBox.show({
										title : data.success ? '成功' : '操作失败',
										msg : data.msg,
										buttons : Ext.MessageBox.OK,
										icon : data.success
												? Ext.MessageBox.INFO
												: Ext.MessageBox.ERROR,
										fn : function() {
											 roleWin.getEl().unmask();
										}
	
									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '设置角色异常',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											roleWin.getEl().unmask();
										}
									});
							Ext.getBody().unmask();
						}

					});
	};
	
	var openRoleWinFlag = function(selectedRoleIds){		
		userServ.editUser(id.toString(), 'edit',function(mav){
			 var model = mav.model;
			 var spcsFlag = model.spcsFlag;
			 openRoleWin(selectedRoleIds,spcsFlag);
		 });
	};
	
	//打开选择资源窗口
	var openRoleWin = function(selectedRoleIds,spcsFlag) {			
		var roleGrid;//选择资源表格
		var roleWin = new Ext.Window({
					height : 435,
					width : 700,
					resizable : false,
					title : "设置用户拥有的角色",
					modal : true,
					constrain : false,
					constrainHeader : false,
					closeAction : 'close',
					stateful : true,
					closable : true,
					border : true,
					layout : 'fit',
					buttonAlign : 'center',
					buttons : [{
						text : '确定',
						hidden : spcsFlag,
						handler : function() {
							roleWin.getEl().mask();
							resetRole(id, roleGrid.selectedIds, roleWin);
						}
					}, {
						text : '返回',
						handler : function() {
							roleWin.getEl().mask();
							roleWin.close();
						}
					}]
				});		
		roleWin.on('beforeclose', function() {
			ds.reload();
			disableButtons();
		});
		var url = '';
//		if(spcsFlag){			
//			url = contextPath + '/role.do?method=list';
//		}else{			
//			url = contextPath + '/systemRole.do?method=list';
//		}
		url = contextPath + '/role.do?method=list';
		roleGrid = getRoleGrid(selectedRoleIds,url); 
		roleWin.add(roleGrid);
		roleWin.doLayout();
		roleWin.show();
		Ext.QuickTips.init();
	};
	
	// dwr预处理查询权限对应资源，调用回调打开编辑窗口
	var getRole = function() {	
		userServ.getRoles(id,openRoleWinFlag);		
	}
	
	
	//重置密码
	function resetPassword(btn) {
		if (btn == 'yes') {
 
			Ext.getBody().mask("正在重置密码,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/user.do?method=resetPassword&&id=' + id,
						success : function(response, options) {

							data = Ext.decode(response.responseText);
							Ext.MessageBox.show({
										title : data.success ? '成功' : '操作失败',
										msg : data.msg,
										buttons : Ext.MessageBox.OK,
										icon : data.success
												? Ext.MessageBox.INFO
												: Ext.MessageBox.ERROR,
										fn : function() {
											grid.store.reload();
											disableButtons();

										}

									});
							Ext.getBody().unmask();
						},
						failure : function(response, options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '重置密码失败!',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											grid.store.reload();
											disableButtons();

										}
									});
							Ext.getBody().unmask();
						}

					});

		}
	}
	
	var resetCities = function(cityGrid ,cityWin){
		var cityIds = new Array();
		var selections = citySM.getSelections();

		if(selections.length == 0 || cityGrid.getStore().getTotalCount() == 0){
			Ext.Msg.alert('提示','尚未选择城市');
			cityWin.getEl().unmask();
			return;
		}
		for(var i=0;i<selections.length;i++){
			cityIds.push(selections[i].get('id'));
		}
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
				url : contextPath + '/user.do?method=userSetCities',
				params : {
					userId : id,
					cityIds : cityIds
				},
				success : function(response,options) {
					data = Ext.decode(response.responseText);
					Ext.MessageBox.show({
						title : data.success ? '成功' : '操作失败',
						msg : data.msg,
						buttons : Ext.MessageBox.OK,
						icon : data.success
								? Ext.MessageBox.INFO
								: Ext.MessageBox.ERROR,
						fn : function() {
							 cityWin.getEl().unmask();
							 cityWin.close();
						}
					});
					Ext.getBody().unmask();
				},
				failure : function(response,options) {
					Ext.MessageBox.show({
						title : '系统执行失败',
						msg : '无法连接到服务器,或服务器操作异常!',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR,
						fn : function() {
							cityWin.getEl().unmask();
						}
					});
					Ext.getBody().unmask();
				}

			});
	};
	
	var showCityWin = function(){
		var cityGrid = getCityGrid(id);
		var cityWin = new Ext.Window({
			height : 435,
			width : 700,
			resizable : false,
			title : "选择城市范围",
			modal : true,
			constrain : false,
			constrainHeader : false,
			closeAction : 'close',
			stateful : true,
			closable : true,
			border : true,
			layout : 'fit',
			buttonAlign : 'center',
			buttons : [{
				text : '确定',
				handler : function() {
					cityWin.getEl().mask();
					resetCities(cityGrid,cityWin);
				}
			}, {
				text : '返回',
				handler : function() {
					cityWin.getEl().mask();
					cityWin.close();
				}
			}]
		});		
		cityWin.on('beforeclose', function() {
			ds.reload();
			disableButtons();
		});
		cityWin.add(cityGrid);
		cityWin.doLayout();
		cityWin.show();
		Ext.QuickTips.init();
	};
	
	var buttonBar = new Ext.Toolbar({
				id : 'buttonBar',
				width : Ext.lib.Dom.getViewportWidth(),
				items : [{// 顶层工具条
					id : 'alterBtn',
					text : '修改',
					tooltip : '修改所选用户',
					tooltipType : 'title',
					iconCls : 'alter',
					disabled : true,
					handler : function() {
						method = 'edit';
						getMav();

					}
				}, '-', {
					id : 'deleteBtn',
					text : '删除',
					tooltip : '删除所选用户',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('删除用户', '您确定要删除用户\'' +userName
										+ '\'么?', deleteUser);

					}
				}, '-', {
					id : 'detailBtn',
					text : '详情',
					tooltip : '查看所选用户详细信息',
					tooltipType : 'title',
					iconCls : 'detail',
					disabled : true,
					handler : function() {
						method = 'show';
						getMav();

					}
				}, '-', {
					id : 'addBtn',
					text : '增加',
					tooltip : '增加用户',
					tooltipType : 'title',
					iconCls : 'add',
					handler : function() {

						method = 'create';
						getMav();

					}

				},'-', {
					id : 'selectRoleBtn',
					text : '选择角色',
					tooltip : '选择一个用户对应的角色',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						getRole();
					}

				},'-', {
					id : 'selectCityBtn',
					text : '选择城市',
					tooltip : '为用户配置适用城市',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						showCityWin();
					}

				},'-', {
					id : 'resetPassWordBtn',
					text : '重置密码',
					tooltip : '重置该用户密码',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('重置密码', '确定要将' + userName
										+ '的密码设为初始密码?', resetPassword);
					}

				}]
			});
 
//	/* 搜索条件：用户状态 */
//	var sList;// 用户状态列表
//	var setStatus = function(jsonStr) {
//		sList = eval(jsonStr);
//	};
//	//通过dwr调用Controller得到资源类型列表
//	var setStautusCombo = function() {
//		userServ.getStatusList({
//					callback : setStatus,
//					async : false
//				});
//
//	}();
//	sList.unshift(['所有', '']);
//	var statusCombo = new Ext.form.ComboBox({
//
//				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
//				id : 'status_list',
//				width : 200,
//				fieldLabel : '用户状态',
//				store : new Ext.data.SimpleStore({
//							fields : ["text", "value"],
//							data : sList
//						}),
//				hiddenName : 'user_status',// 真正存储选择值的隐藏参数名
//				displayField : 'text',
//				valueField : 'value',
//				allowBlank : true,
//				editable : false,
//				typeAhead : true,
//				forceSelection : true,
//				triggerAction : 'all',
//				selectOnFocus : true
//
//			});
			
	var searchBar = new Ext.Toolbar({
		renderTo:'topBarDiv',
		width : Ext.lib.Dom.getViewportWidth(),
		items : ['用户名称: ', {

			xtype : 'textfield',
			id : 'user_name',
			name : 'user_name',
			anchor : '95%',
			maxLength : 20,
			maxLengthText : '用户名称超过最大长度（20位）',
			regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
			regexText : '用户名称应由数字、26个英文字母以及中文汉字组成'
		}, {
			xtype : 'tbseparator'
		}, '真实姓名: ', {

			xtype : 'textfield',
			id : 'real_name',
			name : 'real_name',
			anchor : '95%',
			maxLength : 32,
			maxLengthText : '真实姓名超过最大长度（32位）',
			regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
			regexText : '真实姓名应由数字、26个英文字母以及中文汉字组成'
		},
//		'用户状态: ',  statusCombo,
		{
			xtype : 'tbseparator'
		}, {
			text : '搜索',
			iconCls : 'search',
			handler : function() {
				// 先验证搜索条件
				if (!Ext.getCmp('user_name').isValid(false)) {
					return false;
				}
				if (!Ext.getCmp('real_name').isValid(false)) {
					return false;
				}
//				if (!statusCombo.isValid(false)) {
//					return false;
//				}

				ds.load({
							params : {
								start : 0,
								limit : pageSize,
								'search_userName_like' : Ext.get('user_name').dom.value,
								'search_realName_like' : Ext.get('real_name').dom.value
//								'search_status' : Ext.get('user_status').dom.value
							}
						});
				clearSelections();
			}
		}, {
			xtype : 'tbseparator'
		}, {
			text : '刷新',
			iconCls : 'refresh',
			handler : function() {
				Ext.get('user_name').dom.value = '';
				Ext.getCmp('user_name').clearInvalid();
				Ext.get('real_name').dom.value = '';
				Ext.getCmp('real_name').clearInvalid();
//				Ext.get('user_status').dom.value = '';
//				statusCombo.setValue('');
//				statusCombo.clearInvalid();
				ds.reload({
							params : {
								start : 0,
								limit : pageSize
							}
						});
				clearSelections();

			}
		}]
	});

	var pageSizeSelection = [['15', 15], ['20', 20], ['25', 25], ['30', 30]];
	var pageSizeCombo = new Ext.form.ComboBox({
				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'role_pageSizeSelect',
				width : 50,
				fieldLabel : '每页显示',
				store : new Ext.data.SimpleStore({
							fields : ["text", "value"],
							data : pageSizeSelection
						}),

				displayField : 'text',
				valueField : 'value',
				value : pageSize,
				typeAhead : true,
				forceSelection : true,
				triggerAction : 'all',
				allowBlank : false

			});

	pageSizeCombo.on("select", function() {
				pageSize = parseInt(pageSizeCombo.getValue(), 10);
				paging.pageSize = pageSize;
				ds.load({
							params : {
								start : 0,
								limit : pageSize
							}
						});
			});

	var paging = new Ext.PagingToolbar({ // 底层分页条
		pageSize : pageSize,
		store : ds,
		displayInfo : true,
		beforePageText : '第',
		afterPageText : '页 共{0}页',
		displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
		emptyMsg : "没有记录",
		items : ['-', '每页显示：', pageSizeCombo, '条记录']

	});

	var grid = new Ext.grid.EditorGridPanel({
				title:'用户信息列表',
				renderTo : "tableDivUser",
				border : false,
				width : Ext.lib.Dom.getViewportWidth(),//document.body.clientWidth - 221,// 221是菜单宽度加上各magin长度
				layout : 'fit',
				autoScroll : true,
				height : Ext.lib.Dom.getViewHeight() - (Ext.isIE ? 55 : 52),//calculatedHeight,
				cm : colM,
				store : ds,
				trackMouseOver : true,
				loadMask : {
					msg : '正在加载数据，请稍侯……'
				},
				sm : selectModel,
				bbar : paging			

			});
	removeHearderBox(grid);
	grid.render();
	buttonBar.render('topBarDiv');
	grid.syncSize();
	ds.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	Ext.QuickTips.init();

});