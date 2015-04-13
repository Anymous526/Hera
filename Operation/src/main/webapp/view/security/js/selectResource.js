var getResGrid = function( ids ) {
	
	/* 全局变量 */
	var id = '';
	var rowNumber;
	var pageSize = 20;// 页数
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var selectedResIds = new Array(); // 保存选择结果
	if(ids!== undefined && ids.length>0 ){
		selectedResIds = ids;
	}

	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect : false,
				keepSelections : true,
				selectedIds : selectedResIds

			});// 选择框模型

	var multiSelectHandler = function(self, rowIndex, record) {

		// 保存选择项id
		var newId = record.get('id')
		// 需剔除重复项

		for (var i = 0; i < selectedResIds.length; i++) {
			if (selectedResIds[i] == newId) {
				return;
			}
		}
		selectedResIds.push(newId);

	};
	
	var multiDselectHandler = function(self, rowIndex, record) {

		// 删除列表中的此项
		for (var i = 0; i < selectedResIds.length; i++) {
			if (selectedResIds[i] == record.get('id')) {
				selectedResIds.splice(i, 1);

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
				header : "资源名称 ",
				dataIndex : "resName",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}, {
				header : "资源类型",
				dataIndex : "type",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			}, {
				header : "资源字符串",
				dataIndex : "filterString",
				width : 180,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			}

	]);

	colM.defaultSortable = true;
	colM.autoExpandColumn = 'filterString';

	var ds = new Ext.data.Store({

				remoteSort : true,
				proxy : new Ext.data.HttpProxy({
							url : contextPath + '/res.do?method=list'
						}),
				reader : new Ext.data.JsonReader({
							root : 'root',
							totalProperty : 'totalProperty',
							id : "id"
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'resName',
									mapping : 'resName'
								}, {
									name : 'type',
									mapping : 'type'
								}, {
									name : 'filterString',
									mapping : 'filterString'
								}

						])
			});
	ds.setDefaultSort('id', 'ASC');
	// 这里很关键，如果不加，翻页后搜索条件就变没了，这里的意思是每次数据载入前先把搜索表单值加上去，这样就做到了翻页保留搜索条件了
	ds.on('beforeload', function() {

		Ext.apply(this.baseParams, {

					'search_id' : Ext.get('res_Id').dom.value,
					'search_resName_like' : Ext.get('res_Name').dom.value,
					'search_filterString_like' : Ext.get('filter_String').dom.value,
					'search_type_like' : Ext.get('res_Type').dom.value

				});
	});
	

	/* 搜索条件：资源类型 */

	var resTypeList;// 资源类型列表
	var setResType = function(jsonStr) {
		resTypeList = eval(jsonStr);
	};
	// 通过dwr调用Controller得到资源类型列表
	var setStautusCombo = function() {
		resServ.getResTypeItemList({
					callback : setResType,
					async : false
				});

	}();
	resTypeList.unshift(['所有', '']);
	var resTypeCombo = new Ext.form.ComboBox({

				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'res_Typelist',
				width : 102,
				fieldLabel : '资源类型',
				store : new Ext.data.SimpleStore({
							fields : ["text", "value"],
							data : resTypeList
						}),
				hiddenName : 'res_Type',// 真正存储选择值的隐藏参数名
				displayField : 'text',
				valueField : 'value',
				value : '',// 默认值
				editable : false,
				typeAhead : true,
				forceSelection : true,
				triggerAction : 'all',
				blankText : '请选择资源类型',
				selectOnFocus : true

			});

	var searchBar = new Ext.Toolbar({
		items : [

				'资源编码: ', {

					xtype : 'textfield',
					fieldLabel : '资源编码',
					id : 'res_Id',// 搜索条的id加下划线，与编辑界面区分
					name : 'id',
					anchor : '95%',
					regex : /^[0-9]*$/,
					regexText : '资源编码由数字组成'
				}, {
					xtype : 'tbseparator'
				}, '资源名称: ', {

					xtype : 'textfield',
					fieldLabel : '资源名称',
					id : 'res_Name',
					name : 'resName',
					anchor : '95%',
					maxLength : 20,
					maxLengthText : '资源名称超过最大长度（20位）',
					regex : /^[a-zA-Z0-9􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿􀎸􀄛􀀭􀃢􀀬􀀬􀀨􀎸􀄛􀄁􀆴􀈪􀀿\u4E00-\u9FA5]*$/,
					regexText : '资源名称应由数字、26个英文字母以及中文汉字组成'
				}, '资源串: ', {

					xtype : 'textfield',
					fieldLabel : '资源串',
					id : 'filter_String',
					name : 'filterString',
					anchor : '95%',
					maxLength : 255,
					maxLengthText : '资源串超过最大长度（255位）'

				}, '类别', resTypeCombo]

	});

	var searchBar2 = new Ext.Toolbar({
		items : [{
			text : '搜索',
			iconCls : 'search',
			handler : function() {
				// 先验证搜索条件
				if (!Ext.getCmp('res_Id').isValid(false)) {
					return false;
				}
				if (!Ext.getCmp('res_Name').isValid(false)) {
					return false;
				}
				if (!Ext.getCmp('filter_String').isValid(false)) {
					return false;
				}
				if (!resTypeCombo.isValid(false)) {
					return false;
				}
				ds.load({
					params : {
						start : 0,
						limit : pageSize,
						'search_id' : Ext.get('res_Id').dom.value,
						'search_resName_like' : Ext.get('res_Name').dom.value,
						'search_filterString_like' : Ext.get('filter_String').dom.value,
						'search_type_like' : Ext.get('res_Type').dom.value

					}
				});
			}
		}, {
			xtype : 'tbseparator'
		}, {
			text : '刷新',
			iconCls : 'refresh',
			handler : function() {
				Ext.get('res_Id').dom.value = '';
				Ext.getCmp('res_Id').clearInvalid();
				Ext.get('res_Name').dom.value = '';
				Ext.getCmp('res_Name').clearInvalid();
				Ext.get('filter_String').dom.value = '';
				Ext.getCmp('filter_String').clearInvalid();
				Ext.get('res_Type').dom.value = '';
				resTypeCombo.clearInvalid();
				ds.reload({
							params : {
								start : 0,
								limit : pageSize
							}
						});

			}
		}]
	})

	var pageSizeSelection = [['15', 15], ['20', 20], ['25', 25], ['30', 30]];
	var pageSizeCombo = new Ext.form.ComboBox({
				mode : 'local',// 默认为remote，需要proxy-MemoryProxy
				id : 'res_pageSizeSelect',
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

	var resGrid = new Ext.grid.EditorGridPanel({

				renderTo : "tableDivAuth",
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
				selectedIds: selectedResIds

			});
	removeHearderBox(resGrid);
	resGrid.render();
	searchBar2.render(resGrid.tbar);
	resGrid.syncSize();
	ds.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
	Ext.QuickTips.init();
	return resGrid;

};