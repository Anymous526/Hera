Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var smsId = '';
	var smsMerchantName = '';
	var smsContent = '';
	var smsTime = '';
	var smsCount = '';
	var smsSigner = '';
	var smsAmount = '';
	var smsSendTypeValue = '';
	
	var merchantCode;
	var method;// show/create/edit
	var audAction;
	
	var sendTypeDesc = '';
	var sendSignerDesc = '';

	/* 列表及其相关组件 */
	
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			dataIndex: 'merchantId',
			hidden: true
		},
		{
			header : "商户编号",
			dataIndex : "code",
			width : 110,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户名称",
			dataIndex : "merchantName",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户手机号",
			dataIndex : "mobile",
			width : 90,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户联系人",
			dataIndex : "contactName",
			width : 80,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "点评总数",
			dataIndex : "count",
			width : 60,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "好评率",
			dataIndex : "goodRate",
			width : 60,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "信用积分",
			dataIndex : "score",
			width : 60,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "1星数",
			dataIndex : "one_stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "1星率",
			dataIndex : "one_stars_rate",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "2星数",
			dataIndex : "two_stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "2星率",
			dataIndex : "two_stars_rate",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "3星数",
			dataIndex : "three_stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "3星率",
			dataIndex : "three_stars_rate",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "4星数",
			dataIndex : "four_stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "4星率",
			dataIndex : "four_stars_rate",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "5星数",
			dataIndex : "five_stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "5星率",
			dataIndex : "five_stars_rate",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		}
	]);

	colM.defaultSortable = true;

	var searchBar = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
		items : [
				'商户名称', {
					xtype : 'textfield',
					id : 'searchMerchantName',
					name : 'searchMerchantName',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
					regexText : '商户中文名称应由数字、26个英文字母以及中文汉字组成',
					maxLength:'40',
					maxLengthText:'商户名称长度不得超过40位'
				},
				'商户编号', {
					xtype : 'textfield',
					id : 'searchMerchantCode',
					name : 'searchMerchantCode',
					regex: /^\d{15}$/,
					regexText: '商户编号必须是15位数字',
					maxLength:'15',
					maxLengthText:'商户编码长度必须是15位'
				},
				'商户手机号', {
					xtype : 'textfield',
					id : 'searchMobile',
					name : 'searchMobile'
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

	var grid = new Ext.ux.simpleGrid({
			title:'商户统计信息列表',
			url: contextPath + '/c.do?method=readm',
			cm : colM,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30 : Ext.isIE7 ? 30 : Ext.isIE8 ? 28 : 28),
			nameMapping : [
				{name: 'merchantId', mapping: 'id'},
				{name: 'code', mapping: 'code'},
				{name: 'merchantName', mapping: 'name'},
				{name: 'mobile', mapping: 'contactTelephone'},
				{name: 'count', mapping: 'merchantReferenceStatistic.consumeCount'},
				{name: 'score', mapping: 'merchantReferenceStatistic.creditScore'},
				{name: 'one_stars', mapping: 'merchantReferenceStatistic.oneGrade'},
				{name: 'one_stars_rate', mapping: 'merchantReferenceStatistic.oneGradePercent'},
				{name: 'two_stars', mapping: 'merchantReferenceStatistic.twoGrade'},
				{name: 'two_stars_rate', mapping: 'merchantReferenceStatistic.twoGradePercent'},
				{name: 'three_stars', mapping: 'merchantReferenceStatistic.threeGrade'},
				{name: 'three_stars_rate', mapping: 'merchantReferenceStatistic.threeGradePercent'},
				{name: 'four_stars', mapping: 'merchantReferenceStatistic.fourGrade'},
				{name: 'four_stars_rate', mapping: 'merchantReferenceStatistic.fourGradePercent'},
				{name: 'five_stars', mapping: 'merchantReferenceStatistic.fiveGrade'},
				{name: 'five_stars_rate', mapping: 'merchantReferenceStatistic.fiveGradePercent'},
				{name: 'contactName', mapping: 'contactName'},
				{name: 'goodRate', mapping: 'merchantReferenceStatistic.bestPercent'}
			]
		});
     	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value,
				'search_merchantCode': Ext.get('searchMerchantCode').dom.value,
				'search_merchantName': Ext.get('searchMerchantName').dom.value,
				'search_mobile': Ext.get('searchMobile').dom.value
			}
		});
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
							Ext.getCmp('smsAgentAudit').disable();
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
				'search_merchantCode' : Ext.get('searchMerchantCode').dom.value,
				'search_merchantName' : Ext.get('searchMerchantName').dom.value 
			});
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('searchMerchantName').isValid(false)) {
				return false;
			} else if (!Ext.getCmp('searchMerchantCode').isValid(false)) {
				return false;
			} else if (!Ext.getCmp('searchMobile').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('searchMerchantCode').dom.value = '';
			Ext.getCmp('searchMerchantCode').clearInvalid();
			Ext.get('searchMerchantName').dom.value = '';
			Ext.getCmp('searchMerchantName').clearInvalid();
			Ext.get('searchMobile').dom.value = '';
			Ext.getCmp('searchMobile').clearInvalid();
			loadData();	
		});
	}
	
		removeHearderBox(grid);
		grid.render();
		grid.syncSize();
		addListener();
		loadData();
		Ext.QuickTips.init();
});
