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
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},
			{
				header : "应用编号",
				dataIndex : "code",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},
			{
				header : "应用类别",
				dataIndex : "appType",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			}
			,{
				header : "状态",
				dataIndex : "statusDesc",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : true

			},{
				header : "应用描述",
				dataIndex : "comments",
				width : 100,
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
					id : 'audPassButton',
					text : '审核通过',
					tooltip : '审核通过',
					tooltipType : 'title',
					iconCls : 'yes',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('审核通过',
								'确定审核通过?  ',audPass);
					}
				}, {
					xtype : 'tbseparator'
				}, {
					id : 'audNotPassButton',
					text : '审核不通过',
					tooltip : '审核不通过',
					tooltipType : 'title',
					iconCls : 'no',
					disabled : true,
					handler : function() {
						Ext.MessageBox.confirm('审核不通过',
								'确定审核不通过?  ',audNotPass);
					}
				}]
			});
	
	var grid = new Ext.ux.simpleGrid({
		title:'待审核应用信息列表',
		url: contextPath + '/posApp.do?method=list&aud=true',
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

	var ajaxRequest = function(pass,auditInfo) {
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : contextPath + '/posApp.do',
			params : {
				method : 'auditApp',
				pass : pass,
				appId : id,
				auditInfo : auditInfo
			},
			timeout : 10000,
			success : function(response, options) {
				data = Ext.decode(response.responseText);
				Ext.MessageBox.show({
//					minWidth : 350,
//					maxWidth : 350,		
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
	
	var audPass = function(btn) {
		if (btn == 'yes') {
			ajaxRequest(true);
		}
	};	
		
	var audNotPass = function(btn) {
		if (btn == 'yes') {
			Ext.Msg.prompt('审核不通过', '请输入原因', function(btn, text){
		    	if(text.length > 100){
		    		alert('文本内容超出最大长度');
		    		return;
		    	}
			    if (btn == 'ok'){
			    	ajaxRequest(false,text);
			    }
			},true,true);

		}
	};		
	
	var disableButtons = function(){
		Ext.getCmp('audPassButton').disable();
		Ext.getCmp('audNotPassButton').disable();
	};
	
	var addListener = function() {
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
			}
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
			status = record.get('status');
			if(status =='1'){
				Ext.getCmp('audPassButton').enable();
				Ext.getCmp('audNotPassButton').enable();
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