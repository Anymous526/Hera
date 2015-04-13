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
	var smsStatusValue = '';
	var smsAmount = '';
	var smsSendTypeValue = '';
	
	var merchantCode;
	var method;// show/create/edit
	var audAction;
	
	var sendTypeDesc = '';
	var sendSignerDesc = '';

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
			header : "短信编号",
			dataIndex: 'smsId',
			width: 70,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},
		{
			header : "短信主题",
			dataIndex : "smsTitle",
			width : 150,
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
			width : 100,
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
			header : "发送方式",
			dataIndex : "smsSendType",
			width : 110,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "预订发送时间",
			dataIndex : "expectedSendTime",
			width : 110,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "发送条数",
			dataIndex : "quantity",
			width : 60,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "签单人",
			dataIndex : "signer",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "录入人",
			dataIndex : "oper",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "营销活动状态",
			dataIndex : "smsStatus",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			dataIndex: 'smsContent',
			hidden: true
		},{
			dataIndex: 'smsStatusValue',
			hidden: true
		},{
			dataIndex: 'smsSendTypeValue',
			hidden: true
		},{
			dataIndex: 'smsAmount',
			hidden: true
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
				'活动状态', {
					xtype : 'combo',
					id : 'searchStatus',
					name : 'searchStatus',
					mode: 'local',
					store: new Ext.data.ArrayStore({
						fields: ['statusId', 'statusDesc'],
						data: [
							['ALL', '全部'],
							['0','创建待审核'],
							['1','审核通过待发送'],
							['2','审核未通过'],
							['3','发送中'],
							['4','发送失败'],
							['5','注销'],
							['6','发送完成'],
							['7','已过期']
						]
					}),
					value: 'ALL',
					width: 105,
					valueField: 'statusId',
					displayField: 'statusDesc',
					editable: false,
					triggerAction: 'all',
					listeners: {
						change: function(combo, newValue, oldValue){
							smsStatusValue = newValue;
						}
					}
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
					{
						id : 'smsAgentAudit',
						text : '营销短信审核',
						tooltip : '营销短信审核',
						tooltipType : 'title',
						iconCls : 'func',
						disabled : true
					},
					'-',
					{
						id : 'smsAgentDetail',
						text : '详情查看',
						tooltip : '详情查看',
						tooltipType : 'title',
						iconCls : 'detail',
						disabled : true
					}
				]
	});

	var grid = new Ext.ux.simpleGrid({
			title:'待审核列表',
			url: contextPath + '/sa.do?method=select',
			cm : colM,
			sm : selectModel,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
				{name: 'smsId', mapping: 'id'},
				{name: 'smsTitle', mapping: 'name'},
				{name: 'merchantName', mapping: 'merchant.name'},
				{name: 'contact', mapping: 'merchant.contactName'},
				{name: 'mobile', mapping: 'merchant.contactTelephone'},
				{name: 'quantity', mapping: 'smsCount'},
				{name: 'expectedSendTime', mapping: 'timingTime'},
				{name: 'smsContent', mapping: 'template'},
				{name: 'smsStatus', mapping: 'status.desc'},
				{name: 'smsStatusValue', mapping: 'status.value'},
				{name: 'smsSendType', mapping: 'salePloyType.desc'},
				{name: 'smsSendTypeValue', mapping: 'salePloyType.value'},
				{name: 'smsAmount', mapping: 'tradeMinMoney'},
				'signer', 'oper'
			]
		});
     	
	var loadData = function() {
		smsStatusValue = Ext.getCmp('searchStatus').getValue();
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value,
				'search_merchantCode': Ext.get('searchMerchantCode').dom.value,
				'search_merchantName': Ext.get('searchMerchantName').dom.value,
				'status': smsStatusValue
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
	function smsAgentAudit() {

		var win = new Ext.Window({
			height : 300,
			width : 407,
			resizable : false,
			title : "营销信息审核",
			modal : true,
			constrain : false,
			constrainHeader : false,
			closeAction : 'close',
			stateful : true,
			closable : true,
			border : true,
			layout : 'fit'
		});

		var smsAuditForm = new Ext.form.FormPanel({
			id : 'smsAuditFormID',
			labelWidth : 100,
			frame : true,
			bodyStyle : 'padding:5px 15px 5px',
			anchor : '100% 100% ',
			monitorValid : true,
			layout : 'form',
			defaults : {
				width : 350
			},
			items : [
				{
					xtype : 'label',
					html : '<p><font size="4">商户：' + smsMerchantName + '</font></p><br />'
				},
				{
					xtype : 'label',
					html : sendTypeDesc
				},
				{
					xtype : 'label',
					html : '<hr></hr>'
				},
				{
					xtype : 'textarea',
					id : 'saf_content',
					name : 'saf_content',
					height : 100,
					fieldLabel : '短信内容',
					value : smsContent,
					readOnly : true,
					hideLabel : true
				},
				{
					xtype : 'label',
					html : sendSignerDesc
				}
			],
			buttons : [
				{
					text : '通过',
					handler : function() {
						Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
						Ext.Ajax.request({
							url: contextPath + '/sa.do?method=audit',
							method: 'post',
							params: { id: smsId, isPass: true },
							success: function(response){
								var data = Ext.decode(response.responseText);
								win.close();
								Ext.getBody().unmask();
								Ext.MessageBox.alert('成功', data.msg);
								loadData();
							},
							failure: function(response){
								var data = Ext.decode(response.responseText);
								Ext.MessageBox.alert('失败', data.msg);
								Ext.getBody().unmask();
							}
						});
					}
				},
				{
					text : '不通过',
					handler : function() {
						Ext.MessageBox.prompt('审核不通过', '请输入审核不通过的理由', function(btn, reason) {
							if( btn == 'ok' ){
								Ext.Ajax.request({
									url: contextPath + '/sa.do?method=audit',
									method: 'post',
									params: { id: smsId, isPass: false, comment: reason },
									success: function(response){
										var data = Ext.decode(response.responseText);
										win.close();
										Ext.getBody().unmask();
										Ext.MessageBox.alert('成功', data.msg);
										loadData();
									},
									failure: function(response){
										var data = Ext.decode(response.responseText);
										Ext.MessageBox.alert('失败', data.msg);
										Ext.getBody().unmask();
									}
								});
							}
						});
					}
				},
				{
					text : '返回',
					handler : function() {
						win.close();
					}
				}
			]
		});
		
		win.add(smsAuditForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();
	}
	
	function smsAgentDetail() {
		var win = new Ext.Window({
			height : 300,
			width : 407,
			resizable : false,
			title : "营销信息详细情况",
			modal : true,
			constrain : false,
			constrainHeader : false,
			closeAction : 'close',
			stateful : true,
			closable : true,
			border : true,
			layout : 'fit'
		});

		var smsAuditForm = new Ext.form.FormPanel({
			id : 'smsDetailFormID',
			labelWidth : 100,
			frame : true,
			bodyStyle : 'padding:5px 15px 5px',
			anchor : '100% 100% ',
			monitorValid : true,
			layout : 'form',
			defaults : {
				width : 350
			},
			items : [
				{
					xtype : 'label',
					html : '<p><font size="4">商户：' + smsMerchantName + '</font></p><br />'
				},
				{
					xtype : 'label',
					html : sendTypeDesc
				},
				{
					xtype : 'label',
					html : '<hr></hr>'
				},
				{
					xtype : 'textarea',
					id : 'saf_content',
					name : 'saf_content',
					height : 100,
					fieldLabel : '短信内容',
					value : smsContent,
					readOnly : true,
					hideLabel : true
				},
				{
					xtype : 'label',
					html : sendSignerDesc
				}
			],
			buttons : [
				{
					text : '返回',
					handler : function() {
						win.close();
					}
				}
			]
		});
		
		win.add(smsAuditForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();
	}

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('smsAgentAudit').disable();
			Ext.apply(this.baseParams, {
				'search_merchantCode' : Ext.get('searchMerchantCode').dom.value,
				'search_merchantName' : Ext.get('searchMerchantName').dom.value 
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			smsId = record.get('smsId');
			smsMerchantName = record.get('merchantName');
			smsContent = record.get('smsContent');
			smsTime = record.get('expectedSendTime');
			smsCount = record.get('quantity');
			smsSigner = record.get('signer');
			smsStatusValue = record.get('smsStatusValue');
			smsSendTypeValue = record.get('smsSendTypeValue');
			smsAmount = record.get('smsAmount');
			Ext.getCmp('smsAgentDetail').enable();
			if( smsStatusValue == '0' ){
				Ext.getCmp('smsAgentAudit').enable();
			}
			if( smsSendTypeValue == '1' ){
				sendTypeDesc = '<p>审核通过后立即发送&nbsp;<u>' + smsCount + '</u>&nbsp;条该营销短信</p>';
			}else if( smsSendTypeValue == '2' ){
				sendTypeDesc = '<p>预定于&nbsp;<u>' + smsTime + '</u>&nbsp;发送&nbsp;<u>' + smsCount + '</u>&nbsp;条该营销短信</p>';
			}else if( smsSendTypeValue == '3' ){
				sendTypeDesc = '<p>注册会员后发送该营销短信</p>';
			}else {
				sendTypeDesc = '<p>会员消费满<u>' + smsAmount + '</u>元后发送该营销短信</p>';
			}
			if( smsSigner == '' ){
				sendSignerDesc = '<p>本条营销短信无签单人</p>';
			}else{
				sendSignerDesc = '<p>本条营销短信由' + smsSigner + '签单</p>';
			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			smsId = '';
			smsMerchantName = '';
			smsContent = '';
			smsTime = '';
			smsCount = '';
			smsSigner = '';
			smsSendTypeValue = '';
			sendTypeDesc = '';
			sendSignerDesc = '';
			Ext.getCmp('smsAgentAudit').disable();
			Ext.getCmp('smsAgentDetail').disable();
		});
		
		Ext.getCmp('smsAgentAudit').on('click', function() {
			method = 'delete';
			smsAgentAudit();
		});
		
		Ext.getCmp('smsAgentDetail').on('click', function() {
			smsAgentDetail();
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
			Ext.getCmp('searchStatus').reset();
			smsStatusValue = 'ALL';
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
