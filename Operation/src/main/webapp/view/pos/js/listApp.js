Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var id ='';
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
				header : "应用名称",
				dataIndex : "name",
				width : 110,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},
			{
				header : "应用编号",
				dataIndex : "code",
				width : 60,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},
			{
				header : "应用类别",
				dataIndex : "appType",
				width : 60,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}
			,{
				header : "状态",
				dataIndex : "statusDesc",
				width : 60,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "应用属性",
				dataIndex : "propertyDesc",
				width : 60,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "应用描述",
				dataIndex : "comments",
				width : 350,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "创建人 ",
				dataIndex : "createOper",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "创建时间",
				dataIndex : "createTime",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "停用人 ",
				dataIndex : "stopOper",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "停用时间",
				dataIndex : "stopTime",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "停用审核人 ",
				dataIndex : "auditStopOper",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true
			},{
				header : "停用审核时间",
				dataIndex : "auditStopTime",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "审核意见",
				dataIndex : "auditInfo",
				width : 120,
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
					'应用编号: ', {
					xtype : 'textfield',
					id : 'search_code',
					name : 'search_code',
					regex : /^[0-9]*$/,
					regexText : '应用编号为正整数',
					maxLength: 5,
					maxLengthText:'字段超过最大长度'
				},
				'应用名称: ', {
					xtype : 'textfield',
					id : 'search_name',
					name : 'search_name',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
					regexText : '格式有误',
					maxLength: 48,
					maxLengthText:'字段超过最大长度'
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
		items : [{
			id : 'stopButton',
			text : '停用',
			tooltip : '停用该应用',
			tooltipType : 'title',
			iconCls : 'lock',
			disabled : true,
			handler : function(){
				Ext.MessageBox.confirm('应用停用',
						'确定停用该应用?  ',stopApp);
			}
		},{
			id : 'bindingCities',
			text : '选择适用城市',
			tooltip : '为指定POS应用选择适用城市',
			tooltipType : 'title',
			iconCls : 'edit',
			disabled : true,
			handler : function(){
				showCityWin();
			}
			
		}
//		,{
//			id : 'bindingMerchants',
//			text : '选择适用商户',
//			tooltip : '为指定POS应用选择适用商户',
//			tooltipType : 'title',
//			iconCls : 'edit',
//			disabled : true,
//			handler : function(){
//				showMerhcantsWin();
//			}
//		}
		]
	});
	
	var grid = new Ext.ux.simpleGrid({
		title:'应用信息列表',
		url: contextPath + '/posApp.do?method=list',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
		nameMapping : [
			{name : 'id'},
			{name : 'name'},
			{name : 'code'},
			{name : 'location'},
			{name : 'status'},
			{name : 'propertyDesc'},
			{name : 'comments'},
			{name : 'createOper'},
			{name : 'createTime'},
			{name : 'stopOper'},
			{name : 'stopTime'},
			{name : 'auditStopOper'},
			{name : 'auditStopTime'},
			{name : 'auditInfo'}
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

	
	var stopApp = function(btn){
		if(btn == 'no'){
			return;
		}
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : contextPath + '/posApp.do?method=disableApp',
			params : {
				appId : id
			},
			timeout : 10000,
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
	
	
	var disableButtons = function(){
		Ext.getCmp('stopButton').disable();
		Ext.getCmp('bindingCities').disable();
//		Ext.getCmp('bindingMerchants').disable();
	};

	var resetCities = function(cityGrid ,cityWin){
		var cityIds = new Array();
		var selections = citySM.getSelections();
		//避免因数据加载异常导致空绑定从而覆盖原有数据的情况
		if(selections.length == 0 && cityGrid.getStore().getTotalCount() == 0){
			Ext.Msg.alert('提示','尚未选择数据');
			cityWin.getEl().unmask();
			return;
		}
		for(var i=0;i<selections.length;i++){
			cityIds.push(selections[i].get('id'));
		}
//		alert(cityIds);
		var nonModifiableIds = new Array();
		cityGrid.getStore().each(function(record){
			if(record.get('flag') == true){
				nonModifiableIds.push(record.get('id'));
			};
		});
//		alert(nonModifiableIds);
		
		//处理全选反选后，显示的原有选中行在selections中不存在的问题
		for (var i = 0 ; i < cityIds.length ; i++ ){
			for(var j = 0 ; j < nonModifiableIds.length ; j++ ){
				if (cityIds[i] === nonModifiableIds[j]){
					cityIds.splice(i,1); //利用splice函数删除元素，从第i个位置，截取长度为1的元素
				}
			}
		}
		for(var i = 0; i <nonModifiableIds.length; i++){
			cityIds.push(nonModifiableIds[i]);
		}
//		alert("target:"+cityIds);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
				url : contextPath + '/posApp.do?method=appSetCities',
				params : {
					appId : id,
					cityIds : cityIds
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
							 cityWin.getEl().unmask();
							 cityWin.close();
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
							cityWin.getEl().unmask();
						}
					});
					Ext.getBody().unmask();
				}

			});
	};
	
	var showCityWin = function(){
		var cityGrid = getCityGrid(id);
		var cityWin = new Ext.Window({
			height : 435,
			width : 700,
			resizable : false,
			title : "选择POS应用适用的城市范围",
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
					cityWin.getEl().mask();
					resetCities(cityGrid,cityWin);
				}
			}, {
				text : '返回',
				handler : function() {
					cityWin.getEl().mask();
					cityWin.close();
				}
			}]
		});		
		cityWin.on('beforeclose', function() {
			loadData();
		});
		cityWin.add(cityGrid);
		cityWin.doLayout();
		cityWin.show();
		Ext.QuickTips.init();
	};
	
//	var resetMerchants = function(merchantGrid ,merchantsWin){
//		var merchantCodes = new Array();
//		var selections = merchantSM.getSelections();
//		//避免因数据加载异常导致空绑定从而覆盖原有数据的情况
//		if(selections.length == 0 && merchantGrid.getStore().getTotalCount() == 0){
//			Ext.Msg.alert('提示','尚未选择数据');
//			merchantsWin.getEl().unmask();
//			return;
//		}
//		for(var i=0;i<selections.length;i++){
//			merchantCodes.push(selections[i].get('code'));
//		}
////		alert(merchantCodes);
//		var nonModifiableCodes = new Array();
//		merchantGrid.getStore().each(function(record){
//			if(record.get('flag') == true){
//				nonModifiableCodes.push(record.get('code'));
//			};
//		});
////		alert(nonModifiableIds);
//		
//		//处理全选反选后，显示的原有选中行在selections中不存在的问题
//		for (var i = 0 ; i < merchantCodes.length ; i ++ ){
//			for(var j = 0 ; j < nonModifiableCodes.length ; j ++ ){
//				if (merchantCodes[i] === nonModifiableCodes[j]){
//					merchantCodes.splice(i,1); //利用splice函数删除元素，从第i个位置，截取长度为1的元素
//				}
//			}
//		}
//		for(var i = 0; i <nonModifiableCodes.length; i++){
//			merchantCodes.push(nonModifiableCodes[i]);
//		}
////		alert("target:"+merchantCodes);
//		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
//			Ext.Ajax.request({
//				url : contextPath + '/posApp.do?method=appSetMerchants',
//				params : {
//					appId : id,
//					merchantCodes : merchantCodes
//				},
//				success : function(response,options) {
//					data = Ext.decode(response.responseText);
//					Ext.MessageBox.show({
//						title : data.success ? '成功' : '操作失败',
//						msg : data.msg,
//						buttons : Ext.MessageBox.OK,
//						icon : data.success
//								? Ext.MessageBox.INFO
//								: Ext.MessageBox.ERROR,
//						fn : function() {
//							 merchantsWin.getEl().unmask();
//							 merchantsWin.close();
//						}
//					});
//					Ext.getBody().unmask();
//				},
//				failure : function(response,options) {
//					Ext.MessageBox.show({
//						title : '系统执行失败',
//						msg : '无法连接到服务器,或服务器操作异常!',
//						buttons : Ext.MessageBox.OK,
//						icon : Ext.MessageBox.ERROR,
//						fn : function() {
//							merchantsWin.getEl().unmask();
//						}
//					});
//					Ext.getBody().unmask();
//				}
//
//			});
//	};
	
//	var showMerhcantsWin = function(){
//		var cities = new Array();
//		var data = '';
//		Ext.Ajax.request({
//			url : contextPath + '/posApp.do?method=getCitiesByApp',
//			async :  false,//同步请求数据
//			params : {
//				appId : id
//			},
//			success : function(response,options) {
//				data = Ext.decode(response.responseText);
//			}
//		});
//		if(data == '' || !data.success){
//			Ext.Msg.alert('提示','适用城市范围加载异常');
//			return;
//		}
//		var cityData = Ext.decode(data.msg);
//		if(cityData.length == 0){
//			Ext.Msg.alert('提示','当前应用尚未选择适用城市');
//			return;
//		}
//		for(var i=0;i<cityData.length;i++){
//			cities.push(cityData[i].cityCode);
//		}
//		var merchantGrid = getMerchantGrid(id,cities);
//		var merchantsWin = new Ext.Window({
//			height : 435,
//			width : 700,
//			resizable : false,
//			title : "选择POS应用适用的商户范围",
//			modal : true,
//			constrain : false,
//			constrainHeader : false,
//			closeAction : 'close',
//			stateful : true,
//			closable : true,
//			border : true,
//			layout : 'fit',
//			buttonAlign : 'center',
//			buttons : [{
//				text : '确定',
//				handler : function() {
//					merchantsWin.getEl().mask();
//					resetMerchants(merchantGrid,merchantsWin);
//				}
//			}, {
//				text : '返回',
//				handler : function() {
//					merchantsWin.getEl().mask();
//					merchantsWin.close();
//				}
//			}]
//		});		
//		merchantsWin.on('beforeclose', function() {
//			loadData();
//		});
//		merchantsWin.add(merchantGrid);
//		merchantsWin.doLayout();
//		merchantsWin.show();
//		Ext.QuickTips.init();
//	};
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
			disableButtons();
			Ext.apply(this.baseParams, {
				'search_name_like' : Ext.get('search_name').dom.value,
				'search_code' : Ext.get('search_code').dom.value
			});
		});
		
		grid.store.on('load' ,function(store,records){
//			this.each(function(record){
//				if(record.get('status') == 0){
//					record.set('statusDesc','有效');
//				};
//			});
			for(var i=0;i<records.length;i++){
				if(records[i].get('status') == 0){
					records[i].set('statusDesc','有效');
				}
				if(records[i].get('status') == 1){
					records[i].set('statusDesc','停用待审核');
				}
				if(records[i].get('status') == 2){
					records[i].set('statusDesc','停用');
				}
				if(records[i].get('location') == 0){
					records[i].set('appType','会生活');
				}
				if(records[i].get('location') == 1){
					records[i].set('appType','收单');
				}
			}
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
			status = record.get('status');
			if(status == '0'){
				Ext.getCmp('stopButton').enable();
				Ext.getCmp('bindingCities').enable();
//				Ext.getCmp('bindingMerchants').enable();
			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
			status = '';
			disableButtons();
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('search_name').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('search_code').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('search_name').dom.value = '';
			Ext.getCmp('search_name').clearInvalid();
			Ext.get('search_code').dom.value = '';
			Ext.getCmp('search_code').clearInvalid();
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