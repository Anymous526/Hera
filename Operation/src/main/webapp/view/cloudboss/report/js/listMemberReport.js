Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var startDate = '';
	var endDate = '';
	/* 列表及其相关组件 */
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			header: "会员手机号",
			dataIndex: "mobile",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会员加入日期",
			dataIndex: "regDate",
			width: 150,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户编号",
			dataIndex: "merchantCode",
			width: 150,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户名称",
			dataIndex: "merchantName",
			width: 300,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);
	
	var grid = new Ext.ux.simpleGrid({
		title:'会员信息列表',
		url: contextPath + '/rp.do?method=memberReport',
		cm: colM,
		pageSize: 20,
		height: Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30: Ext.isIE7 ? 30: Ext.isIE8 ? 28: 28),
		nameMapping: [
			{name: 'mobile', mapping: 'mobile'},
			{name: 'regDate', mapping: 'createDate'},
			{name: 'merchantName', mapping: 'merchantName'},
			{name: 'merchantCode', mapping: 'merchantCode'}
		]
	});

	var searchBar = new Ext.Toolbar({
		width: Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
		items: [
				'开始日期', {
					xtype: 'datefield',
					id: 'startDate',
					name: 'startDate',
					format: 'Y-m-d',
					width: 100
				},
				'结束日期', {
					xtype: 'datefield',
					id: 'endDate',
					name: 'endDate',
					format: 'Y-m-d',
					width: 100
				},
				'-',
				{
					id: 'searchButton',
					text: '查询',
					iconCls: 'search'
				},
				'-',
				{
					xtype: 'label',
					html: '<a id="download" href="' + contextPath + '/rp.do?method=memberExport">下载</a>',
					hidden: false
				}]
	});
     	
	var loadData = function() {		
		grid.store.load({
			params: {
				start: 0,
				limit: grid.pageSizeCombo.value
			}
		});
	}

	var addListener = function() {
		
		grid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				startDate: Ext.get('startDate').dom.value,
				endDate: Ext.get('endDate').dom.value
			});
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			loadData();
		});
		
		Ext.getCmp('startDate').on('change', function() {
			var startDate = Ext.get('startDate').dom.value;
			var endDate = Ext.get('endDate').dom.value;
			var download = Ext.get('download').dom;
			download.href = contextPath + '/rp.do?method=memberExport&startDate=' + startDate + '&endDate=' + endDate;
		});
		
		Ext.getCmp('endDate').on('change', function() {
			var startDate = Ext.get('startDate').dom.value;
			var endDate = Ext.get('endDate').dom.value;
			var download = Ext.get('download').dom;
			download.href = contextPath + '/rp.do?method=memberExport&startDate=' + startDate + '&endDate=' + endDate;
		});

	}
	
		removeHearderBox(grid);
		grid.render();
		grid.syncSize();
		addListener();
		Ext.QuickTips.init();
});
