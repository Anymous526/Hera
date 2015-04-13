var getAuthGrid = function(ids) {

	/* 全局变量 */
	var id = '';
	var authName = '';
	var rowNumber;
	var pageSize = 20;// 页数
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var selectedAuthIds = new Array(); // 保存选择结果
	if (ids !== undefined && ids.length > 0) {
		selectedAuthIds = ids;
	}

	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect : false,
				keepSelections : true,
				selectedIds : selectedAuthIds

			});// 选择框模型

	var multiSelectHandler = function(self, rowIndex, record) {

		// 保存选择项id
		var newId = record.get('id')
		// 需剔除重复项

		for (var i = 0; i < selectedAuthIds.length; i++) {
			if (selectedAuthIds[i] == newId) {
				return;
			}
		}
		selectedAuthIds.push(newId);

	};

	var multiDselectHandler = function(self, rowIndex, record) {

		// 删除列表中的此项
		for (var i = 0; i < selectedAuthIds.length; i++) {
			if (selectedAuthIds[i] == record.get('id')) {
				selectedAuthIds.splice(i, 1);

			}
		}
	};

	selectModel.on('rowselect', multiSelectHandler);
	selectModel.on('rowdeselect', multiDselectHandler);

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

		Ext.apply(this.baseParams, {

					'search_authName_like' : Ext.get('auth_Name').dom.value,
					'search_description_like' : Ext.get('auth_description').dom.value

				});
	});

	var searchBar = new Ext.Toolbar({
		items : [

				'权限名称: ', {

					xtype : 'textfield',
					fieldLabel : '权限名称',
					id : 'auth_Name',// 搜索条的id加下划线，与编辑界面区分
					name : 'auth_Name',
					anchor : '95%',
					maxLength : 32,
					maxLengthText : '权限名称超过最大长度（32位）',
					regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5_]*$/,
					regexText : '权限名称称应由数字、26个英文字母、中文汉字或下划线组成'
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
								'search_description_like' : Ext
										.get('auth_description').dom.value

							}
						});
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

	var authGrid = new Ext.grid.EditorGridPanel({

				renderTo : "tableDivRole",
				border : false,
				width : Ext.lib.Dom.getViewportWidth(),
				layout : 'fit',
				autoScroll : true,
				height : Ext.lib.Dom.getViewHeight() - 26,
				cm : colM,
				store : ds,
				trackMouseOver : true,
				loadMask : {
					msg : '正在加载数据，请稍侯……'
				},
				sm : selectModel,
				bbar : paging,
				tbar : searchBar,
				selectedIds: selectedAuthIds

			});
	removeHearderBox(authGrid);
	authGrid.render();
	authGrid.syncSize();
	ds.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	Ext.QuickTips.init();

	return authGrid;

};