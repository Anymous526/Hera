Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = ''; 
	var roleName ='';
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
		buttonBar.items.items[8].enable();// 选择权限按钮
		
	}
	
	function disableButtons(){
		
		buttonBar.items.items[0].disable();// 修改按钮
		buttonBar.items.items[2].disable();// 删除按钮
		buttonBar.items.items[4].disable();// 详情按钮
		buttonBar.items.items[8].disable();// 选择权限按钮
		
	}

	var singleSelectHandler = function(self, rowIndex, record) {
		
		enableButtons();
		id = record.get('id');
		roleName = record.get('roleName');
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
				header : "角色名称 ",
				dataIndex : "roleName",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}, {
				header : "描述",
				dataIndex : "description",
				width : 300,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			} 

	]);

	colM.defaultSortable = true;
	colM.autoExpandColumn = 'descripion';

	var ds = new Ext.data.Store({

				remoteSort : true,
				proxy : new Ext.data.HttpProxy({
							url : contextPath + '/role.do?method=list'
						}),
				reader : new Ext.data.JsonReader({
							root : 'root',
							totalProperty : 'totalProperty',
							id : "id"
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'roleName',
									mapping : 'roleName'
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

							'search_id' : Ext.get('role_id').dom.value,
							'search_roleName_like' : Ext.get('role_name').dom.value

						});
			});

	// dwr预处理编辑操作，调用回调打开编辑窗口
	 
	  var getMav = function() { roleServ.editRole(id.toString(), method,
			  openEditWin);
	   }
	  

	// 打开编辑/详情窗口 getResEditForm实现在editRec.js
	var openEditWin = function(mav) {
		var model = mav.model;

		win = new Ext.Window({
					height : 200,
					width : 450,
					resizable : false,
					title : "角色详细信息",
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
					disableButtons()
				});
				
		var editForm = getRoleEditForm(id.toString(), mav, method, win);

		win.add(editForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();

	};

	// 删除按钮调用
	function deleteRole(btn) {
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
								+ '/role.do?method=removeSelected&&id='
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

	// 用已选择的资源列表更新权限相关资源
	var resetAuth = function(roleId,selectedAuthIds,authWin){
		
		var jsonStr = "{roleId:"+roleId+","+"length:"+selectedAuthIds.length.toString()+",";
		for(var i=0;i< selectedAuthIds.length;i++){
			jsonStr += "authId"+i.toString();
			jsonStr += ":";
			jsonStr += selectedAuthIds[i].toString();
			jsonStr += ",";
		}
		jsonStr = jsonStr.substr(0,jsonStr.length-1);
		jsonStr += "}";
		var jsonData = Ext.decode(jsonStr);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/role.do?method=setAuthorities',
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
											 authWin.getEl().unmask();
										}
	
									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '设置权限异常',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										fn : function() {
											authWin.getEl().unmask();
										}
									});
							Ext.getBody().unmask();
						}

					});
	}
	
	//打开选择资源窗口
	var openAuthWin = function(selectedAuthIds) {
		var authGrid;//选择资源表格
		var authWin = new Ext.Window({

					height : 435,
					width : 700,
					resizable : false,
					title : "设置角色拥有的权限",
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
							authWin.getEl().mask();
							resetAuth(id, authGrid.selectedIds, authWin);
						}
					}, {
						text : '返回',
						handler : function() {
							authWin.getEl().mask();
							authWin.close();
						}
					}]

				});

		authWin.on('beforeclose', function() {
					ds.reload();
					disableButtons();

				});

		authGrid = getAuthGrid(selectedAuthIds);// 选择资源功能 getAuthGrid实现在selectAuth.js
		authWin.add(authGrid);
		authWin.doLayout();
		authWin.show();
		Ext.QuickTips.init();

	};
	
	// dwr预处理查询权限对应资源，调用回调打开编辑窗口
	var getAuth = function() {
		 
		 roleServ.getAuthorities(id,openAuthWin);

	}
	var buttonBar = new Ext.Toolbar({
				id : 'buttonBar',
				width : Ext.lib.Dom.getViewportWidth(),
				items : [{// 顶层工具条

					text : '修改',
					tooltip : '修改所选角色',
					tooltipType : 'title',
					iconCls : 'alter',
					disabled : true,
					handler : function() {
						method = 'edit';
						getMav();

					}
				}, '-', {
					text : '删除',
					tooltip : '删除所选角色',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('删除角色', '您确定要删除角色\'' +roleName
										+ '\'么?', deleteRole);

					}
				}, '-', {
					text : '详情',
					tooltip : '查看所选角色详细信息',
					tooltipType : 'title',
					iconCls : 'detail',
					disabled : true,
					handler : function() {
						method = 'show';
						getMav();

					}
				}, '-', {
					text : '增加',
					tooltip : '增加角色',
					tooltipType : 'title',
					iconCls : 'add',
					handler : function() {

						method = 'create';
						getMav();

					}

				},'-', {
					text : '选择权限',
					tooltip : '选择一个角色对应的权限',
					tooltipType : 'title',
					iconCls : 'columns',
					disabled : true,
					handler : function() {
						getAuth();
					}

				}]
			});
 
	var searchBar = new Ext.Toolbar({
		renderTo:'topBarDiv',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [

				'角色ID: ', {

					xtype : 'textfield',
					fieldLabel : '角色ID',
					id : 'role_id',//搜索条的id加下划线，与编辑界面区分
					name : 'id',
					anchor : '95%',
					regex : /^[0-9]*$/,
					regexText : '角色ID由数字组成',
					maxLength : 32,
					maxLengthText : '角色ID超过最大长度（32位）'
				}, {
					xtype : 'tbseparator'
				}, '角色名称: ', {

					xtype : 'textfield',
					fieldLabel : '角色名称',
					id : 'role_name',
					name : 'role_name',
					anchor : '95%',
					maxLength : 20,
					maxLengthText : '角色名称超过最大长度（20位）',
					regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
					regexText : '角色名称应由数字、26个英文字母以及中文汉字组成'
				},

				{
					text : '搜索',
					iconCls : 'search',
					handler : function() {
						// 先验证搜索条件 
						if (!Ext.getCmp('role_id').isValid(false)) {
							return false;
						}
						if (!Ext.getCmp('role_name').isValid(false)) {
							return false;
						}
						 
						ds.load({
							params : {
								start : 0,
								limit : pageSize,
								'search_id' : Ext.get('role_id').dom.value,
								'search_roleName_like' : Ext.get('role_name').dom.value


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
						Ext.get('role_id').dom.value = '';
						Ext.getCmp('role_id').clearInvalid();
						Ext.get('role_name').dom.value = '';
						Ext.getCmp('role_name').clearInvalid();
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
				title:'角色信息列表',
				renderTo : "tableDivRole",
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