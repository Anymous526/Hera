Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = ''; 
	var resName ='';
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
		resName = record.get('resName');
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
		singleDeselectHandler();
				Ext.apply(this.baseParams, {

							'search_id' : Ext.get('res_Id').dom.value,
							'search_resName_like' : Ext.get('res_Name').dom.value,
							'search_filterString_like' : Ext.get('filter_String').dom.value,
							'search_type_like' : Ext.get('res_Type').dom.value

						});
			});

	// dwr预处理编辑操作，调用回调打开编辑窗口
	 
	  var getMav = function() { resServ.editRes(id.toString(), method,
			  openEditWin);
	   }
	  

	// 打开编辑/详情窗口 getResEditForm实现在editRec.js
	var openEditWin = function(mav) {
		var model = mav.model;

		win = new Ext.Window({
					height : 180,
					width : 430,
					resizable : false,
					title : "资源详细信息",
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
				
		//getResEditForm在editRes.js
		var editForm = getResEditForm(id.toString(), mav, method, win);

		win.add(editForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();

	};

	// 删除按钮调用
	function deleteRes(btn) {
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
								+ '/res.do?method=removeSelected&&id='
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
										msg : '删除失败',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR,
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
					tooltip : '修改所选资源',
					tooltipType : 'title',
					iconCls : 'alter',
					disabled : true,
					handler : function() {
						method = 'edit';
						getMav();

					}
				}, '-', {
					text : '删除',
					tooltip : '删除所选资源',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('删除资源', '您确定要删除资源\'' +resName
										+ '\'么?', deleteRes);

					}
				}, '-', {
					text : '详情',
					tooltip : '查看所选资源详细信息',
					tooltipType : 'title',
					iconCls : 'detail',
					disabled : true,
					handler : function() {
						method = 'show';
						getMav();

					}
				}, '-', {
					text : '增加',
					tooltip : '增加资源',
					tooltipType : 'title',
					iconCls : 'add',
					handler : function() {

						method = 'create';
						getMav();

					}

				}]
			});

 

	/* 搜索条件：资源类型 */
			
	var resTypeList;// 资源类型列表
	var setResType = function(jsonStr) {
		resTypeList = eval(jsonStr);
	};
	//通过dwr调用Controller得到资源类型列表
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
				width : 80,
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
		renderTo:'topBarDiv',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [

				'资源ID: ', {

					xtype : 'textfield',
					fieldLabel : '资源ID',
					width : 100,
					id : 'res_Id',//搜索条的id加下划线，与编辑界面区分
					name : 'id',
					anchor : '95%',
					regex : /^[0-9]*$/,
					regexText : '资源ID由数字组成'
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
				 
				}, '资源类型:', resTypeCombo,

				{
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
						clearSelections();
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
						resTypeCombo.setValue('');
						resTypeCombo.clearInvalid();
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

	var grid = new Ext.grid.EditorGridPanel({
				title:'资源信息列表',
				renderTo : "tableDivRec",
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