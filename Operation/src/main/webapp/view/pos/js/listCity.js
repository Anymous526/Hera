
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
				header : "城市名称",
				dataIndex : "cityName",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "城市编号",
				dataIndex : "cityCode",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "状态",
				dataIndex : "status",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true,
				hidden : true

			}
			]);
		
	colM.defaultSortable = true;
	
		var searchBar = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
			items : [
				'城市名称: ', {
				xtype : 'textfield',
				id : 'search_cityName',
				name : 'search_cityName',
				regex : /^\u4E00-\u9FA5$/,
				regexText : '请输入正确的城市名称',
				maxLength:'30',
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
			}, {
				xtype : 'tbseparator'
			}, {
				id : 'bindingApp',
				text : '选择POS应用',
				tooltip : '为指定城市选择POS应用',
				iconCls : 'edit'
			}
		]});
	
	var grid = new Ext.ux.simpleGrid({
//		renderTo : "tableDiv",
		title:'合同信息列表',
		url: contextPath + '/city.do?method=getOpenCities',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 32 : Ext.isIE7 ? 32 : Ext.isIE8 ? 30 : 27),
		nameMapping : [
			{name : 'id'},
			{name : 'cityName'},
			{name : 'cityCode'},
			{name : 'status'}
		]
	});
	
	var loadData = function() {
		grid.store.load();	
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
//		alert(appIds);
		var nonModifiableIds = new Array();
		appGrid.getStore().each(function(record){
			if(record.get('flag') == true){
				nonModifiableIds.push(record.get('id'));
			};
		});
//		alert(nonModifiableIds);
		
		//处理全选反选后，显示的原有选中行在selections中不存在的问题
		for (var i = 0 ; i < appIds.length ; i ++ ){
			for(var j = 0 ; j < nonModifiableIds.length ; j ++ ){
				if (appIds[i] === nonModifiableIds[j]){
					appIds.splice(i,1); //利用splice函数删除元素，从第i个位置，截取长度为1的元素
				}
			}
		}
		for(var i = 0; i <nonModifiableIds.length; i++){
			appIds.push(nonModifiableIds[i]);
		}
//		alert("target:"+appIds);
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
			Ext.Ajax.request({
				url : contextPath + '/posApp.do?method=citySetApps',
				params : {
					cityId : id,
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
		var appGrid = getAppGrid(id);
		var appWin = new Ext.Window({
			height : 435,
			width : 700,
			resizable : false,
			title : "选择城市可使用的POS应用",
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
				'search_cityName' : Ext.get('search_cityName').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			if(record.get('status') == 1){
				Ext.getCmp('bindingApp').enable();
			}
			id = record.get('id');
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
			disableButtons();
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('search_cityName').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('search_cityName').dom.value = '';
			Ext.getCmp('search_cityName').clearInvalid();
			loadData();	
		});
		
		Ext.getCmp('bindingApp').on('click', function() {
			showAppWin();
		});
	};
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	addListener();
	loadData();
	Ext.QuickTips.init();
});