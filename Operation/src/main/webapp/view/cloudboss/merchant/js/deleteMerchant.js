Ext.onReady(function() {
	Ext.MessageBox.minWidth = 320;
	Ext.MessageBox.maxWidth = 350;
	
	var id ='';
	var isDeled = '';
	var model = '';
	var status = '';
	var canEdit = '';
	
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
				header : "商户中文名称",
				dataIndex : "name",
				width : 160,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},
			{
				header : "商户中文名称简写",
				dataIndex : "shortName",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			}
			,{
				header : "商户编号",
				dataIndex : "code",
				width : 115,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "商户状态 ",
				dataIndex : "status",
				width : 80,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			},{
				header : "创建时间",
				dataIndex : "createDate",
				width : 80,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "营业地址",
				dataIndex : "businessAddress",
				width : 145,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			},{
				header : "商户电话",
				dataIndex : "merchantTelephone",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			},{
				header : "联系人姓名",
				dataIndex : "contactName",
				width : 80,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "联系电话",
				dataIndex : "contactTelephone",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			}
			]);
		
	colM.defaultSortable = true;
//	colM.autoExpandColumn = 'productNo';
	
		var searchBar = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
				items : [
					'商户名称: ', {
					xtype : 'textfield',
					id : 'search_merchantName',// 搜索条的id加下划线，与编辑界面区分
					name : 'search_merchantName',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
					regexText : '商户名称由字母、数字、汉字组成',
					maxLength: 64,
					maxLengthText:'字段超过最大长度'
				},'商户编号: ', {
					xtype : 'textfield',
					id : 'search_merchantCode',// 搜索条的id加下划线，与编辑界面区分
					name : 'search_merchantCode',
					regex : /^[0-9]{15}$/,
					regexText : '商户编号为15位数字',
					maxLength:'15',
					maxLengthText:'商户编号超过15位'
				},
				'状态: ',
				new Ext.form.ComboBox({	
					id : 'statusCombo',
					store: new Ext.data.SimpleStore({
						data: [
							['TYPE_VALID','有效'],
							['TYPE_FREEZED','冻结'],
							['TYPE_AUD_FAIL','审核不通过']
						],
						fields : ['value', 'desc']
					}),
					mode : 'local',
//					hiddenName : 'status',
					width : 100,
					valueField : 'value',
					displayField : 'desc',
					value : '',
					allowBlank : true,
					editable : false,
					emptyText : '请选择...',
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : false,					
					selectOnFocus : true
				}),
				'-',{
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
		}, '-',
		{
			id : 'removeButton',
			text : '注销',
			tooltip : '注销商户',
			tooltipType : 'title',
			iconCls : 'remove',
			disabled : true,
			handler : function() {
				Ext.MessageBox.confirm('商户注销',
						'确定注销该商户?  ',remove);
			}
		}
		]
	});
	
	var grid = new Ext.ux.simpleGrid({
		title:'商户信息列表',
		url: contextPath + '/mi.do?method=list',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
		nameMapping : [
			{name : 'id'},
			{name : 'name'},
			{name : 'shortName'},
			{name : 'code'},
			{name : 'status'},
			{name : 'createDate'},
			{name : 'businessAddress'},
			{name : 'merchantTelephone'},
			{name : 'merchantWeb'},
			{name : 'contactName'},
			{name : 'contactTelephone'}
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
	
	var ajaxRequest = function(method){
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : contextPath + '/mi.do',
			params : {
				method : method,
				id : id
			},
			timeout : 120000,
			success : function(response, options) {
				data = Ext.decode(response.responseText);
				Ext.MessageBox.show({
							title : '提示',
							msg : data.msg + '  ',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.INFO,
							fn : function() {
								grid.store.reload();
								disableButtons();
							}
						});
				Ext.getBody().unmask();
			},
			failure : function(response, options) {
				Ext.MessageBox.show({
							title : '系统执行失败',
							msg : '无法连接到服务器,或服务器操作异常!  ',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.INFO
						});
				Ext.getBody().unmask();
			}
		});
	};
	
	var remove = function(btn) {
		if (btn == 'yes') {
			var reasonForm = new Ext.ux.ReasonForm({
				method : 'remove',
				url : contextPath + '/mi.do',
				pid : id,
				resultMessageShow : resultMessageShow
			});
			reasonForm.show();
		}
	};	
	
	var	showForm = function(pid, m, form) {
		var merchantDetails = new Ext.ux.MerchantForm({
			url : contextPath + '/mi.do',
			method : m,
			pid : pid,
			resultMessageShow : resultMessageShow
		});
		merchantDetails.show();
	};
	
	var disableButtons = function(){
		Ext.getCmp('showDetailButton').disable();
		Ext.getCmp('removeButton').disable();
	};
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
			disableButtons();
			var queryType = Ext.getCmp('statusCombo').getValue();
			if(queryType == ''){
				queryType = 'TYPE_VFAF';
			}
			Ext.apply(this.baseParams, {
				'merchantName' : Ext.get('search_merchantName').dom.value,
				'merchantCode' : Ext.get('search_merchantCode').dom.value,
				'queryType' : queryType
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
			status = record.get('status');
			Ext.getCmp('showDetailButton').enable();
			if(status == '有效' || status == '冻结' || status == '审核不通过'){
				Ext.getCmp('removeButton').enable();
			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
			status = '';
			canEdit = '';
			disableButtons();
		});
		
		Ext.getCmp('showDetailButton').on('click', function() {
			showForm(id, 'load');
		});	
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('search_merchantName').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('search_merchantCode').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('search_merchantName').dom.value = '';
			Ext.getCmp('search_merchantName').clearInvalid();
			Ext.get('search_merchantCode').dom.value = '';
			Ext.getCmp('search_merchantCode').clearInvalid();
			Ext.getCmp('statusCombo').setValue('');
			Ext.getCmp('statusCombo').clearInvalid();
			loadData();	
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