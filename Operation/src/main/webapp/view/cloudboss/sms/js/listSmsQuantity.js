Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var merchantCode = '';
	var merchantName = '';
	var smsQuantity = 0;
	var queryType = 'TYPE_VALID';	

	/* 列表及其相关组件 */
	var selectModel = new JustIn.grid.CheckboxSelectionModel({
				singleSelect: true,
				keepSelections: true
			});

	var clearSelections  = function() {		
		selectedIds = new Array();
		selectModel.selectedIds = selectedIds;
		selectModel.clearSelections();
	};	
	
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(), selectModel,
		{
			dataIndex: "merchantId",
			hidden: true
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
			header: "商户联系人",
			dataIndex: "contact",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "商户联系人手机号",
			dataIndex: "mobile",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "注册时间",
			dataIndex: "regTime",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "现有短信条数",
			dataIndex: "nowTotal",
			width: 100,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);

	colM.defaultSortable = true;

	var searchBar = new Ext.Toolbar({
		width: Ext.lib.Dom.getViewportWidth(),
		renderTo:"topBarDiv",
		items: [
				'商户名称', {
					xtype: 'textfield',
					id: 'searchMerchantName',
					name: 'searchMerchantName',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
					regexText : '商户中文名称应由数字、26个英文字母以及中文汉字组成',
					maxLength:'40',
					maxLengthText:'商户名称长度不得超过40位'
				},
				'商户编号', {
					xtype: 'textfield',
					id: 'searchMerchantCode',
					name: 'searchMerchantCode',
					regex: /^\d{15}$/,
					regexText: '商户编号必须是15位数字',
					maxLength:'15',
					maxLengthText:'商户编码长度必须是15位'
				},
				'-',
				{
					id: 'searchButton',
					text: '搜索',
					iconCls: 'search'
				},
				'-',
				{
					id: 'refreshButton',
					text: '刷新',
					iconCls: 'refresh'
				}]
	});
    	
	//创建工具栏
	var buttonBar = new Ext.Toolbar({
				id: 'buttonBar',
				width: Ext.lib.Dom.getViewportWidth(),
				items: [
				{
					id: 'smsQuantityAdd',
					text: '添加短信数量',
					iconCls: 'add',
					disabled: true
				},
				'-',
				{
					id: 'smsCountAll',
					xtype: 'label',
					html: '系统剩余短信数量获取中...'
				}
			]
	});

	var grid = new Ext.ux.simpleGrid({
			title:'商户短信数量信息列表',
			url: contextPath + '/mi.do?method=list&queryType=' + queryType,
			cm: colM,
			sm: selectModel,
			pageSize: 20,
			height: Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57: Ext.isIE7 ? 57: Ext.isIE8 ? 55: 55),
			nameMapping: [
				{name: 'merchantId', mapping: 'id'},
				{name: 'merchantCode', mapping: 'code'},
				{name: 'merchantName', mapping: 'name'},
				{name: 'contact', mapping: 'contactName'},
				{name: 'mobile', mapping: 'contactTelephone'},
				{name: 'regTime', mapping: 'createDate'},
				{name: 'nowTotal', mapping: 'merchantSmsAccount.remainCount'}
			]
		});
     	
	var loadData = function() {
		Ext.Ajax.request({
			url: contextPath + '/sq.do?method=readCount',
			success: function(response){
				var data = Ext.decode(response.responseText);
				if( data.success ){
					count = data.smsCount;
					if( count < data.alertQuantity ) {
						Ext.getCmp('smsCountAll').el.dom.innerHTML =
							'<font color=red>系统剩余短信数量为 ' + count + ' 条</font>';
					}else{
						Ext.getCmp('smsCountAll').el.dom.innerHTML = '系统剩余短信数量为 ' + count + ' 条';
					}
				}else{
					Ext.getCmp('smsCountAll').html = '<font color=red>暂时无法获取系统剩余短信数量</font>';
				}
				Ext.getCmp('smsCountAll').show();
			},
			failure: function(response){
				Ext.getCmp('smsCountAll').html = '<font color=red>暂时无法获取系统剩余短信数量</font>';
				Ext.getCmp('smsCountAll').show();
			}
		});
		
		grid.store.load({
			params: {
				start: 0,
				limit: grid.pageSizeCombo.value,
				queryType: queryType
			}
		});
		
		clearSelections();
	}
	
	var resultMessageShow = function(data) {
		if (data.success) {
			Ext.MessageBox.show({
					title: '提示',
					msg: data.msg,
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.INFO,
					fn: function(btn) {
						if (btn == 'ok') {
							grid.store.reload();
							id = '';
							Ext.getCmp('smsQuantityAdd').disable();
						}
					}
			});
		} else {
			Ext.MessageBox.show({
					title: '提示',
					msg: data.msg,
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.INFO
			});
		}
	}

	function smsQuantityAdd() {
		var smsType = true;
		var smsTypeName = '商户购买';
	
		var win = new Ext.Window({
			height: 200,
			width: 325,
			resizable: false,
			title: "添加短信数量",
			modal: true,
			constrain: false,
			constrainHeader: false,
			closeAction: 'close',
			stateful: true,
			closable: true,
			border: true,
			layout: 'fit'
		});

		var smsQuantity = new Ext.form.FormPanel({
			id: 'smsQuantityFormID',
			labelWidth: 60,
			frame: true,
			bodyStyle: 'padding:5px 15px 5px',
			anchor: '100% 100% ',
			monitorValid: true,
			layout: 'form',
			defaults: {
				width: 200
			},
			items: [
				{
					xtype: 'textfield',
					id: 'sqa_quantity',
					name: 'sqa_quantity',
					fieldLabel: '添加数量',
					emptyText: '请填写...',
					allowBlank: false,
					blankText: '必填字段',
					regex: /^\+?[1-9][0-9]*$/,
					regexText: '请输入非零正整数',
					listeners: {
						change: function(textfield, newValue, oldValue) {
							smsQuantity = newValue;
						}
					}
				},
				{
					xtype: 'radiogroup',
					fieldLabel: '添加类型',
					itemCls: 'x-radio-group-alt',
					columns: 2,
					items: [
						{boxLabel: '商户购买', id: 'typeMercBuy', name: 'smsTypeSelect', checked: true, handler: function(radio, isChecked){
								if( isChecked ){ smsType = true; smsTypeName = '商户购买'; }
							}
						},
						{boxLabel: '系统赠送', id: 'typeSysGive', name: 'smsTypeSelect', handler: function(radio, isChecked){
								if( isChecked ){ smsType = false; smsTypeName = '系统赠送'; }
							}
						}
					]
				},
				{ xtype: 'label', html: '<p>&nbsp;</p>' },
				{ xtype: 'label', html: '<hr></hr>' },
				{ xtype: 'label', align: 'right', html: '请小心操作，一经生效，无法更改!' }
			],
			buttonAlign: 'right',
			buttons: [
				{
					text: '确定',
					formBind: true,
					handler: function() {
						Ext.MessageBox.confirm('添加确认', '<p>确定要给商户[' + merchantName + ']添加吗？</p><p>数量：' + smsQuantity + '</p><p>类型：' + smsTypeName + '</p>', function(btn){
							if( btn == 'yes' ){
								Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
								Ext.Ajax.request({
									url: contextPath + '/sq.do?method=append',
									params: { code: merchantCode, quantity: smsQuantity, type: smsType?2:1 },
									success: function(response){
										var data = Ext.decode(response.responseText);
										if( data.success ){
											Ext.MessageBox.alert('成功', data.msg);
											Ext.getBody().unmask();
											win.close();
											loadData();
										}else{
											Ext.MessageBox.alert('失败', data.msg);
											Ext.getBody().unmask();
										}
									},
									failure: function(response){
										Ext.MessageBox.alert('失败', '操作失败');
										Ext.getBody().unmask();
									}
								});
							}
						});
					}
				},
				{
					text: '返回',
					handler: function() {
						win.close();
					}
				}
			]
		});
		
		win.add(smsQuantity);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();
	}

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('smsQuantityAdd').disable();
			Ext.apply(this.baseParams, {
				'merchantName': Ext.get('searchMerchantName').dom.value,
				'merchantCode': Ext.get('searchMerchantCode').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			merchantId = record.get('merchantId');
			merchantCode = record.get('merchantCode');
			merchantName = record.get('merchantName');
			Ext.getCmp('smsQuantityAdd').enable();
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			merchantId = '';
			merchantCode = '';
			merchantName = '';
			Ext.getCmp('smsQuantityAdd').disable();
		});
		
		Ext.getCmp('smsQuantityAdd').on('click', function() {
			smsQuantityAdd();
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
