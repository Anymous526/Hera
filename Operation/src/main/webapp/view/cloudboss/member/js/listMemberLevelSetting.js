Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var queryType = 'TYPE_VALIDANDFREEZED';

	/* 列表及其相关组件 */
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			header : "商户编号",
			dataIndex : "merchantCode",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户名称",
			dataIndex : "merchantName",
			width : 200,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户联系人",
			dataIndex : "contact",
			width : 80,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户联系人手机号",
			dataIndex : "mobile",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员等级",
			dataIndex : "levelName",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "折扣规则",
			dataIndex : "discountRule",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "积分规则",
			dataIndex : "pointRule",
			width : 60,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "升级规则",
			dataIndex : "upgradeRule",
			width : 100,
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
			title:'商户规则信息列表',
			url: contextPath + '/mi.do?method=list&queryType=' + queryType,
			cm : colM,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30 : Ext.isIE7 ? 30 : Ext.isIE8 ? 28 : 28),
			nameMapping : [
				{name: 'merchantCode', mapping: 'code'},
				{name: 'merchantName', mapping: 'name'},
				{name: 'contact', mapping: 'contactName'},
				{name: 'mobile', mapping: 'contactTelephone'},
				{name: 'pointRule', mapping: 'memberPoint'},
				{name: 'levelName', mapping: 'memberLevel'},
				{name: 'discountRule', mapping: 'memberDiscount'},
				{name: 'upgradeRule', mapping: 'memberUpdate'}
			]
		});
     	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value,
				'search_merchantCode': Ext.get('searchMerchantCode').dom.value,
				'search_merchantName': Ext.get('searchMerchantName').dom.value
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
							id = '';
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
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('searchMerchantCode').dom.value = '';
			Ext.getCmp('searchMerchantCode').clearInvalid();
			Ext.get('searchMerchantName').dom.value = '';
			Ext.getCmp('searchMerchantName').clearInvalid();
			smsStatusValue = 'ALL';
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
