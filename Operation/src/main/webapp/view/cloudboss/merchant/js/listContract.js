Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var id ='';
	var isDeled = '';
	var model = '';
	var status = '';
	var canEdit = '';
	var file = '';
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
		singleSelect : true,
		keepSelections : true
	});
		
	var clearSelections  = function() {		
		selectedIds = new Array();
		selectModel.selectedIds = selectedIds;
		selectModel.clearSelections();
	};	
		
	var colM = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(), selectModel,
			{
				header : "商户编号",
				dataIndex : "merchantCode",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},
			{
				header : "合同编号",
				dataIndex : "contractNumber",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}
			,{
				header : "合同签订人",
				dataIndex : "contractSigner",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "合同复审员 ",
				dataIndex : "contractAuditor",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "合同签订时间",
				dataIndex : "signDate",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "合同有效期开始日期",
				dataIndex : "validDateBegin",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "合同有效期结束日期",
				dataIndex : "validDateEnd",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "合同文件名称",
				dataIndex : "contractFilePath",
				width : 155,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}
			]);
		
	colM.defaultSortable = true;
	
		var searchBar = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
				items : [
					'合同编号: ', {
					xtype : 'textfield',
					id : 'search_contractNumber',
					name : 'search_contractNumber',
					regex : /^[a-zA-Z0-9-_()]*$/,
					regexText : '合同编号由数字、英文字母以及部分特殊字符(如括弧()横杠"-"或下划线"_"等)组成',
					maxLength: 20,
					maxLengthText:'字段超过最大长度'
				},'商户编号: ', {
					xtype : 'textfield',
					id : 'search_merchantCode',
					name : 'search_merchantCode',
					regex : /^[0-9]{15}$/,
					regexText : '商户编号为15位数字',
					maxLength:'15',
					maxLengthText:'商户编号超过15位'
				},'-',{
					id : 'searchButton',
					text : '搜索',
					iconCls : 'search'
				}, {
					xtype : 'tbseparator'
				}, {
					id : 'refreshButton',
					text : '刷新',
					iconCls : 'refresh'
				}
			]});
		
	//创建工具栏
	var buttonBar = new Ext.Toolbar({
		id : 'buttonBar',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [{// 顶层工具条
			id : 'showDetailButton',
			text : '详情',
			tooltip : '查看所选行详细信息',
			tooltipType : 'title',
			iconCls : 'detail',
			disabled : true
		}, '-',{
			id : 'createButton',
			text : '新增',
			tooltip : '录入新的商户信息',
			tooltipType : 'title',
			iconCls : 'add',
			disabled : false
		}, '-',{// 顶层工具条
			id : 'editButton',
			text : '修改',
			tooltip : '修改商户信息',
			tooltipType : 'title',
			iconCls : 'edit',
			disabled : true
		},'-',
		{
			id: 'contractDownload',
			text: '合同文件下载',
			tooltip : '下载该商户合同文件',
			iconCls: 'save',
			disabled: false
		}
		]
	});
	
	var grid = new Ext.ux.simpleGrid({
		title:'合同信息列表',
		url: contextPath + '/mc.do?method=list',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
		nameMapping : [
			{name : 'id'},
			{name : 'merchantCode'},
			{name : 'contractNumber'},
			{name : 'contractSigner'},
			{name : 'contractAuditor'},
			{name : 'signDate'},
			{name : 'validDateBegin'},
			{name : 'validDateEnd'},
			{name : 'contractFilePath'}
		]
	});
	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value
			}
		});	
		clearSelections();
	};

	var resultMessageShow = function(data) {
		if (data.success) {
			Ext.MessageBox.show({
					title : '提示',
					msg : data.msg,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO,
					fn : function(btn) {
						if (btn == 'ok') {
							grid.store.reload();
							id = '';
							disableButtons();
						}
					}
			});
		} else {
			Ext.MessageBox.show({
					title : '提示',
					msg : data.msg,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO
			});
		}
	};
	
//	var ajaxRequest = function(method){
//		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
//		Ext.Ajax.request({
//			url : contextPath + '/mi.do',
//			params : {
//				method : method,
//				id : id
//			},
//			timeout : 120000,
//			success : function(response, options) {
//				data = Ext.decode(response.responseText);
//				Ext.MessageBox.show({
//							title : '提示',
//							msg : data.msg + '  ',
//							buttons : Ext.MessageBox.OK,
//							icon : Ext.MessageBox.INFO,
//							fn : function() {
//								grid.store.reload();
//								disableButtons();
//							}
//						});
//				Ext.getBody().unmask();
//			},
//			failure : function(response, options) {
//				Ext.MessageBox.show({
//							title : '系统执行失败',
//							msg : '无法连接到服务器,或服务器操作异常!  ',
//							buttons : Ext.MessageBox.OK,
//							icon : Ext.MessageBox.INFO
//						});
//				Ext.getBody().unmask();
//			}
//		});
//	};
	
	var	showForm = function(pid, m, form) {
		var merchantDetails = new Ext.ux.MerchantForm({
			url : contextPath + '/mc.do',
			method : m,
			pid : pid,
			resultMessageShow : resultMessageShow
		});
		merchantDetails.show();
	};
	
	var disableButtons = function(){
		Ext.getCmp('editButton').disable();
		Ext.getCmp('showDetailButton').disable();
		Ext.getCmp('contractDownload').disable();
	};
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
			disableButtons();
			Ext.apply(this.baseParams, {
				'search_contractNumber_like' : Ext.get('search_contractNumber').dom.value,
				'search_merchantCode' : Ext.get('search_merchantCode').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			Ext.getCmp('showDetailButton').enable();
			Ext.getCmp('editButton').enable();
			Ext.getCmp('contractDownload').enable();
			id = record.get('id');
			file = record.get('contractFilePath');
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
			status = '';
			canEdit = '';
			disableButtons();
		});
		
		Ext.getCmp('createButton').on('click', function() {
			showForm('', 'create');
		});
		
		Ext.getCmp('editButton').on('click', function() {
			showForm(id, 'update');
		});
		
		Ext.getCmp('showDetailButton').on('click', function() {
			showForm(id, 'load');
		});	
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('search_contractNumber').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('search_merchantCode').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('search_contractNumber').dom.value = '';
			Ext.getCmp('search_contractNumber').clearInvalid();
			Ext.get('search_merchantCode').dom.value = '';
			Ext.getCmp('search_merchantCode').clearInvalid();
			loadData();	
		});
		
		Ext.getCmp('contractDownload').on('click', function() {
			if(file != ''){
				window.open('../fileDownload/fileDownload.jsp?file=' + file);
			}
		});
	};
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	buttonBar.render('topBarDiv');
	addListener();
	loadData();
	Ext.QuickTips.init();
});