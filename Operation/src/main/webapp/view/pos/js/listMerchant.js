Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var merchantCode ='';
	var cityName ='';
	var status = '';
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
				sortable : true

			},
			{
				header : "商户中文名称简写",
				dataIndex : "shortName",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}
			,{
				header : "商户编号",
				dataIndex : "code",
				width : 115,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "商户状态 ",
				dataIndex : "status",
				width : 80,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "创建时间",
				dataIndex : "createDate",
				width : 80,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "所在城市",
				dataIndex : "cityName",
				width : 80,
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
				},{
					id : 'bindingApp',
					text : '选择POS应用',
					tooltip : '为指定商户选择POS应用',
					tooltipType : 'title',
					iconCls : 'edit',
					disabled : true
				}
			]});
		
	var grid = new Ext.ux.simpleGrid({
		title:'商户信息列表',
		url: contextPath + '/mi.do?method=list&queryType=TYPE_VALIDANDFREEZED',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 32 : Ext.isIE7 ? 32 : Ext.isIE8 ? 30 : 27),
		nameMapping : [
			{name : 'name'},
			{name : 'shortName'},
			{name : 'code'},
			{name : 'status'},
			{name : 'createDate'},
			{name : 'cityName'}
		],
        viewConfig : {
        	forceFit : true
        }
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

	var disableButtons = function(){
		Ext.getCmp('bindingApp').disable();
	};
	
	var resetApp = function(appGrid ,appWin){

		var appIds = new Array();
		var selections = appSM.getSelections();
		//避免因数据加载异常导致空绑定从而覆盖原有数据的情况
		if(selections.length == 0 && appGrid.getStore().getTotalCount() == 0){
			Ext.Msg.alert('提示','尚未选择数据');
			appWin.getEl().unmask();
			return;
		}
		for(var i=0;i<selections.length;i++){
			appIds.push(selections[i].get('id'));
		}
//		alert(appIds.length);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
				url : contextPath + '/posApp.do?method=merchantSetApps',
				params : {
					merchantCode : merchantCode ,
					appIds : appIds
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
							 appWin.getEl().unmask();
							 appWin.close();
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
							appWin.getEl().unmask();
						}
					});
					Ext.getBody().unmask();
				}

			});
	};
	
	var showAppWin = function(){
		var appGrid = getAppGrid(merchantCode , cityName);
		if(appGrid == null){
			Ext.Msg.alert('提示','应用信息加载失败');
			return;
		}
		var appWin = new Ext.Window({
			height : 435,
			width : 700,
			resizable : false,
			title : "选择商户可使用的POS应用",
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
					appWin.getEl().mask();
					resetApp(appGrid,appWin);
				}
			}, {
				text : '返回',
				handler : function() {
					appWin.getEl().mask();
					appWin.close();
				}
			}]
		});		
		appWin.on('beforeclose', function() {
			loadData();
		});
		appWin.add(appGrid);
		appWin.doLayout();
		appWin.show();
		Ext.QuickTips.init();
	};	
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
			disableButtons();
			Ext.apply(this.baseParams, {
				'merchantName' : Ext.get('search_merchantName').dom.value,
				'merchantCode' : Ext.get('search_merchantCode').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			merchantCode = record.get('code');
			cityName = record.get('cityName');
			status = record.get('status');
			if(status == '有效' || status == '冻结'){
				Ext.getCmp('bindingApp').enable();
			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			merchantCode = '';
			status = '';
			cityName = '';
			disableButtons();
		});
		
		Ext.getCmp('bindingApp').on('click', function() {
			showAppWin();
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
			loadData();	
		});
	};
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	addListener();
	loadData();
	Ext.QuickTips.init();
});