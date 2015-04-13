var getRoleGrid = function(ids,url) {

	/* 全局变量 */
	var id = '';
	var rowNumber;
	var pageSize = 20;// 页数
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var selectedRoleIds = new Array(); // 保存选择结果
	if (ids != undefined && ids.length > 0) {
		selectedRoleIds = ids;
	}

	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect : false,
				keepSelections : true,
				selectedIds : selectedRoleIds

			});// 选择框模型

	var multiSelectHandler = function(self, rowIndex, record) {

		// 保存选择项id
		var newId = record.get('id')
		// 需剔除重复项

		for (var i = 0; i < selectedRoleIds.length; i++) {
			if (selectedRoleIds[i] == newId) {
				return;
			}
		}
		selectedRoleIds.push(newId);

	};

	var multiDselectHandler = function(self, rowIndex, record) {

		// 删除列表中的此项
		for (var i = 0; i < selectedRoleIds.length; i++) {
			if (selectedRoleIds[i] == record.get('id')) {
				selectedRoleIds.splice(i, 1);

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
							url : url
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

				Ext.apply(this.baseParams, {

							'search_id' : Ext.get('role_id').dom.value,
							'search_roleName_like' : Ext.get('role_name').dom.value

						});
			});

	var searchBar = new Ext.Toolbar({
		items : [

				'角色编码: ', {

					xtype : 'textfield',
					fieldLabel : '角色ID',
					id : 'role_id',//搜索条的id加下划线，与编辑界面区分
					name : 'id',
					anchor : '95%',
					regex : /^[0-9]*$/,
					regexText : '角色编码由数字组成',
					maxLength : 32,
					maxLengthText : '角色名称超过最大长度（32位）'
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

					}
				}]
	});

	var pageSizeSelection = [['15', 15], ['20', 20], ['25', 25], ['30', 30]];
	var pageSizeCombo = new Ext.form.ComboBox({
				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'user_role_pageSizeSelect',
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

	var roleGrid = new Ext.grid.EditorGridPanel({

				renderTo : "tableDivUser",
				border : false,
				width : Ext.lib.Dom.getViewportWidth(),//document.body.clientWidth - 221,// 221是菜单宽度加上各magin长度
				layout : 'fit',
				autoScroll : true,
				height :  Ext.lib.Dom.getViewHeight()-26,//calculatedHeight,
				cm : colM,
				store : ds,
				trackMouseOver : true,
				loadMask : {
					msg : '正在加载数据，请稍侯……'
				},
				sm : selectModel,
				bbar : paging,
				tbar : searchBar,
				selectedIds: selectedRoleIds

			});
	removeHearderBox(roleGrid);
	roleGrid.render();
	roleGrid.syncSize();
	ds.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	Ext.QuickTips.init();
	return roleGrid;

};