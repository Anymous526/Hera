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
			header: "统计日期",
			dataIndex: "reportDate",
			width: 80,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "城市",
			dataIndex: "city",
			width: 60,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户当日新增",
			dataIndex: "perDayAddMerchantCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户总数",
			dataIndex: "totalMerchantCount",
			width: 60,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "POS当日新增",
			dataIndex: "perDayAddPosCount",
			width: 80,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "POS总数",
			dataIndex: "totalPosCount",
			width: 60,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会员当日新增",
			dataIndex: "perDayAddMemberCount",
			width: 80,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会员总数",
			dataIndex: "totalMemberCount",
			width: 60,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "POS当日加入会员数",
			dataIndex: "perDayPosAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "POS加入会员总数",
			dataIndex: "totalPosAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "运营平台当日导入数",
			dataIndex: "perDayPlatformAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "运营平台导入总数",
			dataIndex: "totalPlatformAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户后台当日导入数",
			dataIndex: "perDayCloudbossAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户后台导入总数",
			dataIndex: "totalCloudbossAddMemberCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "短信当日赠送数",
			dataIndex: "perDaySysLargessSmsCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "短信赠送总数",
			dataIndex: "totalSysLargessSmsCount",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户当日购买短信数",
			dataIndex: "perDayMerchantBuySmsCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户购买短信总数",
			dataIndex: "totalMerchantBuySmsCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户当日发送短信数",
			dataIndex: "perDaySentSmsCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户发送短信总数",
			dataIndex: "totalSentSmsCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活当日现金交易笔数",
			dataIndex: "perDayCashTradeCount",
			width: 130,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活现金交易总笔数",
			dataIndex: "totalCashTradeCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},
		{
			header: "会生活刷卡交易笔数",
			dataIndex: "perDayCardTradeCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活刷卡交易总笔数",
			dataIndex: "totalCardTradeCount",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},
		{
			header: "会生活当日现金交易金额",
			dataIndex: "perDayCashTradeMoney",
			width: 130,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活现金交易总额",
			dataIndex: "totalCashTradeMoney",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活当日刷卡金额",
			dataIndex: "perDayCardTradeMoney",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "会生活刷卡总额",
			dataIndex: "totalCardTradeMoney",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);
	var grid = new Ext.ux.simpleGrid({
		title:'城市数据统计列表',  
		url: contextPath + '/rp.do?method=cityReport',
		cm: colM,
		height: Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30: Ext.isIE7 ? 30: Ext.isIE8 ? 28: 28),
		nameMapping: [
			{name: 'reportDate', mapping: 'reportDate'},
			{name: 'city', mapping: 'city.name'},
			{name: 'perDayAddMerchantCount', mapping: 'perDayAddMerchantCount'},
			{name: 'totalMerchantCount', mapping: 'totalMerchantCount'},
			{name: 'perDayAddPosCount', mapping: 'perDayAddPosCount'},
			{name: 'totalPosCount', mapping: 'totalPosCount'},
			{name: 'perDayAddMemberCount', mapping: 'perDayAddMemberCount'},
			{name: 'totalMemberCount', mapping: 'totalMemberCount'},
			{name: 'perDayPosAddMemberCount', mapping: 'perDayPosAddMemberCount'},
			{name: 'totalPosAddMemberCount', mapping: 'totalPosAddMemberCount'},
			{name: 'perDayPlatformAddMemberCount', mapping: 'perDayPlatformAddMemberCount'},
			{name: 'totalPlatformAddMemberCount', mapping: 'totalPlatformAddMemberCount'},
			{name: 'perDayCloudbossAddMemberCount', mapping: 'perDayCloudbossAddMemberCount'},
			{name: 'totalCloudbossAddMemberCount', mapping: 'totalCloudbossAddMemberCount'},
			{name: 'perDaySysLargessSmsCount', mapping: 'perDaySysLargessSmsCount'},
			{name: 'totalSysLargessSmsCount', mapping: 'totalSysLargessSmsCount'},
			{name: 'perDayMerchantBuySmsCount', mapping: 'perDayMerchantBuySmsCount'},
			{name: 'totalMerchantBuySmsCount', mapping: 'totalMerchantBuySmsCount'},
			{name: 'perDaySentSmsCount', mapping: 'perDaySentSmsCount'},
			{name: 'totalSentSmsCount', mapping: 'totalSentSmsCount'},
			{name: 'perDayCashTradeCount', mapping: 'perDayCashTradeCount'},
			{name: 'totalCashTradeCount', mapping: 'totalCashTradeCount'},
			{name: 'perDayCardTradeCount', mapping: 'perDayCardTradeCount'},
			{name: 'totalCardTradeCount', mapping: 'totalCardTradeCount'},
			{name: 'perDayCashTradeMoney', mapping: 'perDayCashTradeMoney'},
			{name: 'totalCashTradeMoney', mapping: 'totalCashTradeMoney'},
			{name: 'perDayCardTradeMoney', mapping: 'perDayCardTradeMoney'},
			{name: 'totalCardTradeMoney', mapping: 'totalCardTradeMoney'},
		],
        bbar: new Ext.Toolbar({
        	hidden : true
        })
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
					width: 100,
					editable : false,
					allowBlank : false,
					listeners : {
						'select' : function(){
							Ext.getCmp('endDate').setMinValue(this.getValue());
							var maxDate = new Date(this.getValue()).add(Date.MONTH, +1);
							var allowMaxDate = new Date().add(Date.DAY, -1);
							if(maxDate < allowMaxDate){
								Ext.getCmp('endDate').setMaxValue(maxDate);
							}else{
								Ext.getCmp('endDate').setMaxValue(allowMaxDate);
							}
						},
						'render' : function(){
							var maxDate = new Date().add(Date.DAY, -1);
							this.setMaxValue(maxDate);
							this.validate();
						}
					}
				},'结束日期', {
					xtype: 'datefield',
					id: 'endDate',
					name: 'endDate',
					format: 'Y-m-d',
					width: 100,
					editable : false,
					allowBlank : false,
					listeners : {
						'select' : function(){
							Ext.getCmp('startDate').setMaxValue(this.getValue());
							var minDate = new Date(this.getValue()).add(Date.MONTH, -1);
							Ext.getCmp('startDate').setMinValue(minDate);
						},
						'render' : function(){
							var maxDate = new Date().add(Date.DAY, -1);
							this.setMaxValue(maxDate);
							this.validate();
						}
					}
				},'-',
				{
					id: 'refreshButton',
					text: '清空日期',
					iconCls: 'refresh',
					handler: function(){
						Ext.getCmp('startDate').setValue('');
						Ext.getCmp('endDate').setValue('');
						Ext.getCmp('startDate').setMinValue('');
						Ext.getCmp('endDate').setMinValue('');
						var maxDate = new Date().add(Date.DAY, -1);
						Ext.getCmp('startDate').setMaxValue(maxDate);
						Ext.getCmp('endDate').setMaxValue(maxDate);
					}
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
					html: '<a id="download" href="' + contextPath + '/rp.do?method=cityExport">下载</a>',
					hidden: false
				}]
	});
     	
	var loadData = function() {		
		grid.store.load({
			params: {
//				start: 0,
//				limit: grid.pageSizeCombo.value
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
			if(Ext.getCmp('startDate').getValue() == '' || Ext.getCmp('endDate').getValue() == ''){
				Ext.Msg.alert('提示','请先选择统计开始日期和结束日期');
				return;
			}
			loadData();
		});
		
		Ext.getCmp('startDate').on('change', function() {
			var startDate = Ext.get('startDate').dom.value;
			var endDate = Ext.get('endDate').dom.value;
			var download = Ext.get('download').dom;
			download.href = contextPath + '/rp.do?method=cityExport&startDate=' + startDate + '&endDate=' + endDate;
		});
		
		Ext.getCmp('endDate').on('change', function() {
			var startDate = Ext.get('startDate').dom.value;
			var endDate = Ext.get('endDate').dom.value;
			var download = Ext.get('download').dom;
			download.href = contextPath + '/rp.do?method=cityExport&startDate=' + startDate + '&endDate=' + endDate;
		});

		Ext.get('download').on('click',function(){
			if(Ext.getCmp('startDate').getValue() == '' || Ext.getCmp('endDate').getValue() == ''){
				this.dom.href = '#';
				Ext.Msg.alert('提示','请先选择统计开始日期和结束日期');
			}
		});
	};
	
//		removeHearderBox(grid);
		grid.render();
		grid.syncSize();
		addListener();
		Ext.QuickTips.init();
});
