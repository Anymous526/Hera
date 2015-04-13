Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = ''; 
	var menuName = '';
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

	var singleSelectHandler = function(self, rowIndex, record) {

		buttonBar.items.items[0].enable();// 修改按钮
		buttonBar.items.items[2].enable();// 删除按钮
		buttonBar.items.items[4].enable();// 详情按钮
		id = record.get('id');
		menuName = record.get('menuName');
		selectedIds[0] = id;

		rowNumber = rowIndex;
	};

	var singleDeselectHandler = function() {
		buttonBar.items.items[0].disable();// 修改按钮
		buttonBar.items.items[2].disable();// 删除按钮
		buttonBar.items.items[4].disable();// 详情按钮
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
				header : "菜单名 ",
				dataIndex : "menuName",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}, {
				header : "url",
				dataIndex : "url",
				width : 250,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			}, {
				header : "父菜单",
				dataIndex : "parentName",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "显示序号",
				dataIndex : "orderNo",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			} 

	]);

	colM.defaultSortable = true;
	colM.autoExpandColumn = 'url';

	var ds = new Ext.data.Store({

				remoteSort : true,
				proxy : new Ext.data.HttpProxy({
							url : contextPath + '/menu.do?method=list'
						}),
				reader : new Ext.data.JsonReader({
							root : 'root',
							totalProperty : 'totalProperty',
							id : "id"
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'menuName',
									mapping : 'menuName'
								}, {
									name : 'url',
									mapping : 'url'
								}, {
									name : 'parentName',
									mapping : 'parentName'
								}, {
									name : 'orderNo',
									mapping : 'orderNo'
								}  
						])
			});
	ds.setDefaultSort('id', 'ASC');
	// 这里很关键，如果不加，翻页后搜索条件就变没了，这里的意思是每次数据载入前先把搜索表单值加上去，这样就做到了翻页保留搜索条件了
	ds.on('beforeload', function() {
		singleDeselectHandler();
				Ext.apply(this.baseParams, {

							'search_menuName_like' : Ext.get('menu_name').dom.value,
							'search_parentId' : Ext.get('parent_Id').dom.value,
							'search_url_like' : Ext.get('menu_url').dom.value

						});
			});

	// dwr预处理编辑操作，调用回调打开编辑窗口
	 
	  var getMav = function() { menuServ.editMenu(id.toString(), method,
			  openEditWin);
	   }
	  

	// 打开编辑/详情窗口 getResEditForm实现在editRec.js
	var openEditWin = function(mav) {
		var model = mav.model;

		win = new Ext.Window({
					height : 200,
					width : 440,
					resizable : false,
					title : "菜单详细信息",
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
					buttonBar.items.items[0].disable();// 修改按钮
					buttonBar.items.items[2].disable();// 删除按钮
					buttonBar.items.items[4].disable();// 详情按钮
				});
				
		//getMenuEditForm在editMenu.js
		var editForm = getMenuEditForm(id.toString(), mav, method, win);

		win.add(editForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();

	};

	// 删除按钮调用
	function deleteMenu(btn) {
		if (btn == 'yes') {

			var handleResult = function(modelAndResult) {
				if (modelAndResult.success) {
					Ext.Msg.alert('结果', modelAndResult.message);
					ds.reload();
					buttonBar.items.items[0].disable();// 修改按钮
					buttonBar.items.items[2].disable();// 删除按钮
					buttonBar.items.items[4].disable();// 详情按钮

				} else {
					Ext.Msg.alert('错误', '删除失败');
				}
			}
			Ext.getBody().mask("正在执行删除,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/menu.do?method=removeSelected&&id='
								+ id,
						success : function(response,options) {

							data = Ext.decode(response.responseText);
							Ext.MessageBox.show({
										title : data.success ? '成功' : '操作失败',
										msg : data.msg + "  ",
										width : 200,
										buttons : Ext.MessageBox.OK,
										icon : data.success
												? Ext.MessageBox.INFO
												: Ext.MessageBox.ERROR,
										fn : function() {
											ds.reload();
											buttonBar.items.items[0].disable();// 修改按钮
											buttonBar.items.items[2].disable();// 删除按钮
											buttonBar.items.items[4].disable();// 详情按钮

										}

									});
							Ext.getBody().unmask();
						},
						failure : function(response,options) {
							Ext.MessageBox.show({
										title : '操作失败',
										msg : '删除失败!  ',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
										width : 200,
										fn : function() {
											ds.reload();
											buttonBar.items.items[0].disable();// 修改按钮
											buttonBar.items.items[2].disable();// 删除按钮
											buttonBar.items.items[4].disable();// 详情按钮

										}
									});
							Ext.getBody().unmask();
						}

					});

		}
	}

	var buttonBar = new Ext.Toolbar({
				id : 'buttonBar',
				width : Ext.lib.Dom.getViewportWidth(),
				items : [{// 顶层工具条

					text : '修改',
					tooltip : '修改所选菜单',
					tooltipType : 'title',
					iconCls : 'alter',
					disabled : true,
					handler : function() {
						method = 'edit';
						getMav();

					}
				}, '-', {
					text : '删除',
					tooltip : '删除所选菜单',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('删除菜单', '您确定要删除菜单\'' + menuName
										+ '\'么?', deleteMenu);

					}
				}, '-', {
					text : '详情',
					tooltip : '查看所选菜单详细信息',
					tooltipType : 'title',
					iconCls : 'detail',
					disabled : true,
					handler : function() {
						method = 'show';
						getMav();

					}
				}, '-', {
					text : '增加',
					tooltip : '增加菜单',
					tooltipType : 'title',
					iconCls : 'add',
					handler : function() {

						method = 'create';
						getMav();

					}

				} ]
			});

 	var menuCombo = new Ext.form.ComboBox({
				mode : 'remote',
				id : 'menu_list',
				width : 250,
				fieldLabel : '菜单类型',				
				store : new Ext.data.JsonStore({
					remoteSort : true,
					url : contextPath + '/menu.do?method=queryParentMenuItems',
					totalProperty:'totalProperty',
					root : 'root',
					id : "id",
		    		fields: ['id', 'menuName']
				}),
				hiddenName : 'parent_Id',// 真正存储选择值的隐藏参数名
				displayField : 'menuName',
				valueField : 'id',
				value : '',// 默认值
				editable : false,
				typeAhead : true,
				pageSize : 10,
				forceSelection : true,
				triggerAction : 'all',
				selectOnFocus : true,
				allowBlank : true,
				blankText : '所有'
			});

	var searchBar = new Ext.Toolbar({
		renderTo:'topBarDiv',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [

				'菜单名: ', {

					xtype : 'textfield',
					id : 'menu_name',//搜索条的id加下划线，与编辑界面区分
					name : 'menu_name',
					anchor : '95%',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '菜单名称应由数字、26个英文字母以及中文汉字组成',
					maxLength : 32,
					maxLengthText : '菜单名称超过最大长度（32位）'
				}, {
					xtype : 'tbseparator'
				}, 'url: ', {

					xtype : 'textfield',
					id : 'menu_url',
					name : 'menu_url',
					anchor : '95%',
					maxLength : 100,
					maxLengthText : 'URL超过最大长度（100位）',
					regex : /^\/{1}[a-zA-Z0-9]+[a-zA-Z0-9\/.?&=]*$/,
					regexText : '请按url格式输入'
				},  '父菜单：', menuCombo,

				{
					text : '搜索',
					iconCls : 'search',
					handler : function() {
						// 先验证搜索条件 
						if (!Ext.getCmp('menu_name').isValid(false)) {
							return false;
						}
						if (!Ext.getCmp('menu_url').isValid(false)) {
							return false;
						}
						if (!menuCombo.isValid(false)) {
							return false;
						}
						ds.load({
							params : {
								start : 0,
								limit : pageSize,
								'search_menuName_like' : Ext.get('menu_name').dom.value,
								'search_parentId' : Ext.get('parent_Id').dom.value,
								'search_url_like' : Ext.get('menu_url').dom.value
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
						Ext.get('menu_name').dom.value = '';
						Ext.getCmp('menu_name').clearInvalid();
						Ext.get('menu_url').dom.value = '';
						Ext.getCmp('menu_url').clearInvalid();
						Ext.get('parent_Id').dom.value = '';
						menuCombo.clearInvalid();
						menuCombo.setValue('');
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
				id : 'menu_pageSizeSelect',
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
				title:'菜单信息列表',
				renderTo : "tableDivMenu",
				border : false,
				width : Ext.lib.Dom.getViewportWidth(), 
				layout : 'fit',
				autoScroll : true,
				height :  Ext.lib.Dom.getViewHeight() - (Ext.isIE ? 10 : 52), 
				cm : colM,
				store : ds,
				trackMouseOver : true,
				loadMask : {
					msg : '正在加载数据，请稍侯……'
				},
				sm : selectModel,
				bbar : paging
//				tbar : searchBar

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