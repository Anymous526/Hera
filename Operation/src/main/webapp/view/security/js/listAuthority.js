Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = ''; 
	var authName = '';
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
		
		buttonBar.items.items[0].enable();// 修改按钮
		buttonBar.items.items[2].enable();// 删除按钮
		buttonBar.items.items[4].enable();// 详情按钮
		buttonBar.items.items[8].enable();// 选择资源按钮
		buttonBar.items.items[10].enable();// 选择菜单按钮
		
	}
	
	function disableButtons(){
		
		buttonBar.items.items[0].disable();// 修改按钮
		buttonBar.items.items[2].disable();// 删除按钮
		buttonBar.items.items[4].disable();// 详情按钮
		buttonBar.items.items[8].disable();// 选择资源按钮
		buttonBar.items.items[10].disable();// 选择菜单按钮
		
	}
	
	var singleSelectHandler = function(self, rowIndex, record) {
 
		enableButtons();
		id = record.get('id');
		authName = record.get('authName');
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
				header : "ID ",
				dataIndex : "id",
				width : 30,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},

			{
				header : "权限名称 ",
				dataIndex : "authName",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}, {
				header : "权限描述",
				dataIndex : "description",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			} 

	]);

	colM.defaultSortable = true;
	colM.autoExpandColumn = 'description';

	var ds = new Ext.data.Store({

				remoteSort : true,
				proxy : new Ext.data.HttpProxy({
							url : contextPath + '/auth.do?method=list'
						}),
				reader : new Ext.data.JsonReader({
							root : 'root',
							totalProperty : 'totalProperty',
							id : "id"
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'authName',
									mapping : 'authName'
								}, {
									name : 'description',
									mapping : 'description'
								} 

						])
			});
	ds.setDefaultSort('id', 'ASC');
	// 这里很关键，如果不加，翻页后搜索条件就变没了，这里的意思是每次数据载入前先把搜索表单值加上去，这样就做到了翻页保留搜索条件了
	ds.on('beforeload', function() {
		disableButtons();
				Ext.apply(this.baseParams, {

							'search_authName_like' : Ext.get('auth_Name').dom.value,
							'search_description_like' : Ext.get('auth_description').dom.value

						});
			});

	// dwr预处理编辑操作，调用回调打开编辑窗口
	 
	  var getMav = function() {
		authServ.editAuth(id.toString(), method, openEditWin);
	}
	  

	// 打开编辑/详情窗口 getAuthEditForm实现在editAuth.js
	var openEditWin = function(mav) {
		var model = mav.model;

		win = new Ext.Window({
					height : 200,
					width : 450,
					resizable : false,
					title : "权限详细信息",
					modal : true,
					constrain : false,
					constrainHeader : false,
					closeAction : 'close',
					stateful : true,
					closable : true,
					border : true,
					layout : 'fit'

				});

		win.on('beforeclose', function() {
					ds.reload();
					disableButtons();
				});
				
	 
		var editForm = getAuthEditForm(id.toString(), mav, method, win);

		win.add(editForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();

	};

	// 删除按钮调用
	function deleteAuth(btn) {
		if (btn == 'yes') {

			var handleResult = function(modelAndResult) {
				if (modelAndResult.success) {
					Ext.Msg.alert('结果', modelAndResult.message);
					ds.reload();
					disableButtons();

				} else {
					Ext.Msg.alert('错误', '删除失败');
				}
			}
			Ext.getBody().mask("正在执行删除,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/auth.do?method=removeSelected&&id='
								+ id,
						success : function(response) {

							data = Ext.util.JSON.decode(response.responseText);
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
						failure : function(action) {
							//var data = Ext.decode(action.response.responseText);
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

	
	
	// 用已选择的资源列表更新权限相关资源
	var resetResources = function(authId,selectedResIds,resWin){
		
		var jsonStr = "{authId:"+authId+","+"length:"+selectedResIds.length.toString()+",";
		for(var i=0;i< selectedResIds.length;i++){
			jsonStr += "resId"+i.toString();
			jsonStr += ":";
			jsonStr += selectedResIds[i].toString();
			jsonStr += ",";
		}
		jsonStr = jsonStr.substr(0,jsonStr.length-1);
		jsonStr += "}";
		var jsonData = Ext.decode(jsonStr);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/auth.do?method=setResouces',
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
											 resWin.getEl().unmask();
										}
	
									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '设置资源异常',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											resWin.getEl().unmask();
										}
									});
							Ext.getBody().unmask();
						}

					});
	}
	
	//打开选择资源窗口
	var openResWin = function(selectedResIds) {
		var resGrid;//选择资源表格
		var resWin = new Ext.Window({

					height : 435,
					width : 700,
					resizable : false,
					title : "设置权限拥有的资源",
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
							resWin.getEl().mask();
							resetResources(id, resGrid.selectedIds, resWin);
						}
					}, {
						text : '返回',
						handler : function() {
							resWin.getEl().mask();
							resWin.close();
						}
					}]

				});

		resWin.on('beforeclose', function() {
					ds.reload();
					disableButtons();

				});

		resGrid = getResGrid(selectedResIds);// 选择资源功能 getResGrid实现在selectResource.js
		resWin.add(resGrid);
		resWin.doLayout();
		resWin.show();
		Ext.QuickTips.init();

	};
	
	// dwr预处理查询权限对应资源，调用回调打开编辑窗口
	var getRes = function() {
		 
		 authServ.getResources(id,openResWin);

	}
	
	// 用已选择的菜单列表更新权限相关菜单
	var resetMenus = function(authId,selectedMenuIds,resWin){
		
		var jsonStr = "{authId:"+authId+","+"length:"+selectedMenuIds.length.toString()+",";
		for(var i=0;i< selectedMenuIds.length;i++){
			jsonStr += "menuId"+i.toString();
			jsonStr += ":";
			jsonStr += selectedMenuIds[i].toString();
			jsonStr += ",";
		}
		jsonStr = jsonStr.substr(0,jsonStr.length-1);
		jsonStr += "}";
		var jsonData = Ext.decode(jsonStr);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/auth.do?method=setMenus',
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
											 resWin.getEl().unmask();
										}
	
									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '设置菜单异常',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											resWin.getEl().unmask();
										}
									});
							Ext.getBody().unmask();
						}

					});
	}
	
	//打开选择菜单窗口
	var openMenuWin = function(selectedMenuIds) {
		var menuTreePanel;//选择菜单树面板
		var menuWin = new Ext.Window({

					height : 435,
					width : 500,
					resizable : false,
					title : "设置权限拥有的菜单",
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
							menuWin.getEl().mask();
							resetMenus(id, menuTreePanel.selectedIds, menuWin);
						}
					}, {
						text : '返回',
						handler : function() {
							menuWin.getEl().mask();
							menuWin.close();
						}
					}]

				});

		menuWin.on('beforeclose', function() {
					ds.reload();
					disableButtons();

				});

		menuTreePanel = getTreePanel(selectedMenuIds);
		menuWin.add(menuTreePanel);
		menuWin.doLayout();
		menuWin.show();
		Ext.QuickTips.init();

	};
	
	// dwr预处理查询权限对应资源，调用回调打开编辑窗口
	var getMenu = function() {
		 
		 authServ.getMenus(id,openMenuWin);

	}
	var buttonBar = new Ext.Toolbar({
				id : 'buttonBar',
				width : Ext.lib.Dom.getViewportWidth(),
				items : [{// 顶层工具条

					text : '修改',
					tooltip : '修改所选权限',
					tooltipType : 'title',
					iconCls : 'alter',
					disabled : true,
					handler : function() {
						method = 'edit';
						getMav();

					}
				}, '-', {
					text : '删除',
					tooltip : '删除所选权限',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('删除权限', '您确定要删除权限\'' + authName
										+ '\'么?', deleteAuth);

					}
				}, '-', {
					text : '详情',
					tooltip : '查看所选权限详细信息',
					tooltipType : 'title',
					iconCls : 'detail',
					disabled : true,
					handler : function() {
						method = 'show';
						getMav();

					}
				}, '-', {
					text : '新增',
					tooltip : '新增权限',
					tooltipType : 'title',
					iconCls : 'add',
					handler : function() {

						method = 'create';
						getMav();
					}
				}, '-', {
					text : '选择资源',
					tooltip : '选择一个权限对应的资源',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						getRes();
						}

					}, '-', {
					text : '选择菜单',
					tooltip : '选择一个权限对应的菜单',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						getMenu();
					}

				}]
			});

 

	var searchBar = new Ext.Toolbar({
		renderTo:'topBarDiv',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [

				'权限名称: ', {

					xtype : 'textfield',
					fieldLabel : '权限名称',
					id : 'auth_Name',//搜索条的id加下划线，与编辑界面区分
					name : 'auth_Name',
					anchor : '95%',
					maxLength : 32,
					maxLengthText : '权限名称超过最大长度（32位）',
					regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5_]*$/,
					regexText : '权限名称应由数字、26个英文字母、中文汉字或下划线组成'
				}, {
					xtype : 'tbseparator'
				}, '权限描述: ', {

					xtype : 'textfield',
					fieldLabel : '权限描述',
					id : 'auth_description',
					name : 'auth_description',
					anchor : '95%',
					maxLength : 255,
					maxLengthText : '资源串超过最大长度（255位）'
				},  

				{
					text : '搜索',
					iconCls : 'search',
					handler : function() {
						// 先验证搜索条件 
						if (!Ext.getCmp('auth_Name').isValid(false)) {
							return false;
						}
						if (!Ext.getCmp('auth_description').isValid(false)) {
							return false;
						}
						ds.load({
							params : {
								start : 0,
								limit : pageSize,								 
								'search_authName_like' : Ext.get('auth_Name').dom.value,
								'search_description_like' : Ext.get('auth_description').dom.value
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
						
						Ext.get('auth_Name').dom.value = '';
						Ext.getCmp('auth_Name').clearInvalid();
						Ext.get('auth_description').dom.value = '';
						Ext.getCmp('auth_description').clearInvalid();
						ds.reload({
									params : {
										start : 0,
										limit : pageSize
									}
								});
								
						clearSelections() ;
							

					}
				}]
	});

	
	var pageSizeSelection = [['15', 15], ['20', 20], ['25', 25], ['30', 30]];
	var pageSizeCombo = new Ext.form.ComboBox({
				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'auth_pageSizeSelect',
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
				title:'权限信息列表',
				renderTo : "tableDivAuth",
				border : false,
				width : Ext.lib.Dom.getViewportWidth(), 
				layout : 'fit',
				autoScroll : true,
				height : Ext.lib.Dom.getViewHeight() - (Ext.isIE ? 55 : 52), 
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