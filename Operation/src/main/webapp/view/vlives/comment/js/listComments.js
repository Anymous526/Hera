Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var stars = '';
	var comments = '';
	var searchLevel = '';

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
			dataIndex: 'commentId',
			hidden: true
		},
		{
			header : "会员编号",
			dataIndex : "code",
			width : 80,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员名",
			dataIndex : "name",
			width : 80,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员手机号",
			dataIndex : "mobile",
			width : 80,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员等级",
			dataIndex : "level",
			width : 65,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "所属商户",
			dataIndex : "merchantName",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "点评时间",
			dataIndex : "time",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "评分星数",
			dataIndex : "stars",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "点评内容",
			dataIndex : "comments",
			width : 400,
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
				'开始日期',{
					xtype: 'datefield',
					id : 'searchStartDate',
					format: 'Y-m-d',
					width: 90,
					editable : false
				},
				'结束日期',{
					xtype: 'datefield',
					id : 'searchEndDate',
					format: 'Y-m-d',
					width: 90,
					editable : false
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
	
	var searchBar2 = new Ext.Toolbar({
		width : Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
		items : [
				'会员手机号', {
					xtype : 'textfield',
					id : 'searchMobile',
					name : 'searchMobile'
				},
				'会员等级', {
					xtype : 'combo',
					id : 'searchLevel',
					name : 'searchLevel',
					mode: 'local',
					store: new Ext.data.ArrayStore({
						fields: ['levelId', 'levelDesc'],
						data: [
							['ALL', '全部会员'],
							['1','普通会员'],
							['2','银卡会员'],
							['3','金卡会员'],
							['4','钻石会员']
						]
					}),
					value: 'ALL',
					width: 70,
					valueField: 'levelId',
					displayField: 'levelDesc',
					editable: false,
					triggerAction: 'all',
					listeners: {
						change: function(combo, newValue, oldValue){
							searchLevel = newValue;
						}
					}
				},
				'-',{
					id : 'detail',
					text : '详情',
					tooltip : '详情查看',
					tooltipType : 'title',
					iconCls : 'func',
					disabled : true
				}]
	});

	var grid = new Ext.ux.simpleGrid({
			title:'用户点评信息列表',
			url: contextPath + '/c.do?method=readu',
			cm : colM,
			sm : selectModel,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
				{name: 'commentId', mapping: 'id'},
				{name: 'code', mapping: 'member.id'},
				{name: 'name', mapping: 'member.user.name'},
				{name: 'mobile', mapping: 'member.user.mobile'},
				{name: 'level', mapping: 'member.level.desc'},
				{name: 'merchantName', mapping: 'merchant.name'},
				{name: 'time', mapping: 'createDate'},
				{name: 'stars', mapping: 'merchantGrade.desc'},
				{name: 'comments', mapping: 'comments'}
			]
		});
     	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value,
				'search_merchantCode' : Ext.get('searchMerchantCode').dom.value,
				'search_merchantName' : Ext.get('searchMerchantName').dom.value,
				'search_mobile' : Ext.get('searchMobile').dom.value,
				'search_level' : searchLevel,
				'search_startDate' : Ext.get('searchStartDate').dom.value,
				'search_endDate' : Ext.get('searchEndDate').dom.value
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
	function detail() {
		Ext.getBody().mask(null, 'x-mask-loading');
		Ext.MessageBox.show({
			title:'用户评分： ' + stars,
			msg: comments,
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.INFO,
			fn: function(){
				Ext.getBody().unmask();
			}
		});
	}

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('detail').disable();
			Ext.apply(this.baseParams, {
				'search_merchantCode' : Ext.get('searchMerchantCode').dom.value,
				'search_merchantName' : Ext.get('searchMerchantName').dom.value,
				'search_mobile' : Ext.get('searchMobile').dom.value,
				'search_level' : searchLevel,
				'search_startDate' : Ext.get('searchStartDate').dom.value,
				'search_endDate' : Ext.get('searchEndDate').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			stars = record.get('stars');
			comments = record.get('comments');
			Ext.getCmp('detail').enable();
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			stars = '';
			comments = '';
			Ext.getCmp('detail').disable();
		});
		
		Ext.getCmp('detail').on('click', function() {
			detail();
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
			Ext.getCmp('searchMobile').setValue('');
			Ext.getCmp('searchMobile').clearInvalid();
			Ext.getCmp('searchLevel').reset();
			searchLevel = 'ALL';
			Ext.getCmp('searchStartDate').reset();
			Ext.getCmp('searchEndDate').reset();
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
