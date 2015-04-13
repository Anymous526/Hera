Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	/* 列表及其相关组件 */
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			header: "统计日期",
			dataIndex: "reportDate",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},
		{
			header: "商户编号",
			dataIndex: "merchantCode",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户名称",
			dataIndex: "merchantName",
			width: 200,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户创建日期",
			dataIndex: "createDate",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会员当日新增数",
			dataIndex: "newMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会员总数",
			dataIndex: "allMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "现金消费金额",
			dataIndex: "cashAmount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "现金消费数",
			dataIndex: "cashCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "银行卡消费金额",
			dataIndex: "cardAmount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "银行卡消费数",
			dataIndex: "cardCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "营销活动日增数",
			dataIndex: "addSaleployCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "营销活动到达数",
			dataIndex: "allSaleployCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户已发短信数",
			dataIndex: "allSmsCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);
	
	var grid = new Ext.ux.simpleGrid({
		title:'商户统计信息列表',
		url: contextPath + '/rp.do?method=merchantReport',
		cm: colM,
		pageSize: 20,
		height: Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30: Ext.isIE7 ? 30: Ext.isIE8 ? 28: 28),
		nameMapping: [
			{name: 'reportDate', mapping: 'createDate'},
			{name: 'merchantCode', mapping: 'merchant.code'},
			{name: 'merchantName', mapping: 'merchant.name'},
			{name: 'createDate', mapping: 'merchant.createDate'},
			{name: 'newMemberCount', mapping: 'perDayAddMemberCount'},
			{name: 'allMemberCount', mapping: 'totalMemberCount'},
			{name: 'cashAmount', mapping: 'cashTradeMoney'},
			{name: 'cashCount', mapping: 'cashTradeCount'},
			{name: 'cardAmount', mapping: 'cardTradeMoney'},
			{name: 'cardCount', mapping: 'cardTradeCount'},
			{name: 'addSaleployCount', mapping: 'perDayAddSalePloyCount'},
			{name: 'allSaleployCount', mapping: 'totalSalePloyCount'},
			{name: 'allSmsCount', mapping: 'sendSmsCount'}
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
					html: '<a id="download" href="' + contextPath + '/rp.do?method=merchantExport">下载</a>',
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
	};

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
			download.href = contextPath + '/rp.do?method=merchantExport&startDate=' + startDate + '&endDate=' + endDate;
		});
		
		Ext.getCmp('endDate').on('change', function() {
			var startDate = Ext.get('startDate').dom.value;
			var endDate = Ext.get('endDate').dom.value;
			var download = Ext.get('download').dom;
			download.href = contextPath + '/rp.do?method=merchantExport&startDate=' + startDate + '&endDate=' + endDate;
		});
	};
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	addListener();
	Ext.QuickTips.init();
});
