Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = '';
	var merchantCode;
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var audAction;

	/* 列表及其相关组件 */
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
			header : "操作员ID",
			dataIndex : "opId",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : true
		},{
			header : "用户名",
			dataIndex : "opName",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : true
		},{
			header : "手机号码",
			dataIndex : "opMobile",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : true
		},{
			header : "操作 ",
			dataIndex : "opDescription",
			width : 200,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : true
		},{
			header : "日期",
			dataIndex : "opDateTime",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : true
		}]);

     	colM.defaultSortable = true;

     	var searchBar = new Ext.Toolbar({
    		width : Ext.lib.Dom.getViewportWidth(),
    		renderTo:"topBarDiv",
    		items : [
    				'用户名: ', {
    					xtype : 'textfield',
    					id : 'opName',
    					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
    					regexText : '操作员应由字母、数字、汉字组成',
    					name : 'opName',
    					maxLength:'100',
    					maxLengthText:'操作员长度不得超过100位'
    				},'手机号码: ', {
    					xtype : 'textfield',
    					id : 'opMobile',
    					name : 'opMobile',
    					regex : /^\d*$/,
    					regexText : '手机号由全数字组成',
    					maxLength:'11',
    					maxLengthText:'商户编码长度不得超过11位'
    				},
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
    				}]
    	});
     	
     	
     	//创建工具栏
    	var buttonBar = new Ext.Toolbar({
    				id : 'buttonBar',
    				width : Ext.lib.Dom.getViewportWidth(),
    				items : [
    				]
    			});
    	
     	
    	var grid = new Ext.ux.simpleGrid({
			title:'操作日志列表',
			url: contextPath + '/bmpLog.do?method=listLog',
			cm : colM,
			sm : selectModel,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
			   			{name : 'id'},
			   			{name : 'opId'},
			   			{name : 'opName'},
			   			{name : 'opMobile'},
			   			{name : 'opDescription'},
			   			{name : 'opTime'},
			   			{name : 'opDateTime'}
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
	}
	
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
	}

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'search_opName_like' : Ext.get('opName').dom.value,
				'search_opMobile_like' : Ext.get('opMobile').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('opName').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('opMobile').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('opName').dom.value = '';
			Ext.getCmp('opName').clearInvalid();
			Ext.get('opMobile').dom.value = '';
			Ext.getCmp('opMobile').clearInvalid();
			loadData();	
		});
	}
	
		removeHearderBox(grid);
		grid.render();
		grid.syncSize();
		buttonBar.render('topBarDiv');
		addListener();
		loadData();
		Ext.QuickTips.init();
});
