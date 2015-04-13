Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = new Array();
	var win;
	
	/* 存储已选项 */
	var selectedStore = new Ext.data.Store({
		fields: ['id', 'content']
	});

	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect : false,
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
			dataIndex : "id",
			hidden: true
		},{
			header : "违禁字",
			dataIndex : "content",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		}]);

	colM.defaultSortable = true;

	var searchBar = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
		items : [
			'违禁字',
			{
				xtype : 'textfield',
				id : 'searchContent',
				name : 'searchContent',
				maxLength:'20',
				maxLengthText:'违禁字长度不得超过20位'
			},
			'-',
			{
				id : 'searchButton',
				text : '搜索',
				iconCls : 'search'
			},
			'-',
			{
				id : 'refreshButton',
				text : '刷新',
				iconCls : 'refresh'
			}
		]
	});
    	
	//创建工具栏
	var buttonBar = new Ext.Toolbar({
				id : 'buttonBar',
				width : Ext.lib.Dom.getViewportWidth(),
				items : [{			// 顶层工具条
					id : 'appendBanedWord',
					text : '添加违禁字',
					tooltip : '添加违禁字',
					tooltipType : 'title',
					iconCls : 'add',
					disabled : false
				},'-',{
					id : 'deleteBanedWord',
					text : '删除违禁字',
					tooltip : '删除违禁字',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true
				}]
			});

	var grid = new Ext.ux.simpleGrid({
			title:'违禁字列表',
			url: contextPath + '/bw.do?method=select',
			cm : colM,
			sm : selectModel,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
			   			{name : 'id'},
			   			{name : 'content'}
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
							Ext.getCmp('deleteBanedWord').disable();
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
	
	function appendBanedWord(btn, banedWord) {
		if ( btn == 'ok' ) {
			if ( banedWord.length < 20 && banedWord.length != 0 ) {
				Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
				Ext.Ajax.request({
					url : contextPath
							+ '/bw.do?method=append',
					params : {
						content : banedWord
					},
					success : function(response) {
						data = Ext.decode(response.responseText);
						resultMessageShow(data);
						Ext.getBody().unmask();
					},
					failure : function(response) {
						var data = Ext.decode(response.responseText);
						resultMessageShow(data);
						Ext.getBody().unmask();
					}
				});
			} else if ( banedWord.length > 20 ) {
				Ext.MessageBox.show({
					title : '出错',
					msg : '违禁字词条长度不能超过10个汉字',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			} else if ( banedWord.length == 0 ){
				Ext.MessageBox.show({
					title : '出错',
					msg : '请先输入内容再确认',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	}

	function deleteBanedWord(btn) {
		if (btn == 'yes') {
			var deleteIds = new Array();
			var idx = 0;
			selectedStore.each(function(recode){
				deleteIds[idx] = recode.get('id');
				idx = idx + 1;
			});
			Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
			Ext.Ajax.request({
						url : contextPath
								+ '/bw.do?method=delete',
						params : {
							id : deleteIds
						},
						success : function(response) {
							data = Ext.decode(response.responseText);
							resultMessageShow(data);
							Ext.getBody().unmask();
						},
						failure : function(response) {
							var data = Ext.decode(response.responseText);
							resultMessageShow(data);
							Ext.getBody().unmask();
						}
					});
		}
	}

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('deleteBanedWord').disable();
			Ext.apply(this.baseParams, {
				'content' : Ext.get('searchContent').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			selectedStore.addSorted(record);
			Ext.getCmp('deleteBanedWord').enable();
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			selectedStore.remove(record);
			if( !grid.getSelectionModel().hasSelection() ){
				Ext.getCmp('deleteBanedWord').disable();
			}
		});
		
		Ext.getCmp('appendBanedWord').on('click', function() {
			Ext.MessageBox.prompt('添加', '请输入要添加的违禁字词条', appendBanedWord);
		});
		
		Ext.getCmp('deleteBanedWord').on('click', function() {
			Ext.MessageBox.confirm('删除', '确定要已选择的删除违禁字 吗？', deleteBanedWord);
		});		
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('searchContent').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('searchContent').dom.value = '';
			Ext.getCmp('searchContent').clearInvalid();
			loadData();	
		});
	}
	
	grid.render();
	grid.syncSize();
	buttonBar.render('topBarDiv');
	addListener();
	loadData();
	Ext.QuickTips.init();
});
