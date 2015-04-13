Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var smsMerchantId = '';
	var smsMerchantName = '';
	var smsLevel = new Array(true,true,true,true);
	var smsActive = '';
	var smsPointMin = '';
	var smsPointMax = '';
	var smsSendType = '';
	var smsSendDate = '';
	var smsSendTime = '';
	var smsTitle = '';
	var smsContent = '';
	var smsSigner = '';
	var smsAmount = '';
	var smsValidDateStart = '';
	var smsValidDateEnd= '';
	var queryType = 'TYPE_VALID';
	var selectedItems = new Array();

	/* 商户名称列表&选择框 */
	var merchantNameComboBox = new Ext.form.ComboBox({
		id: 'searchMerchantName',
		name: 'searchMerchantName',
		fieldLabel: '商户名称',
		mode: 'remote',
		store: new Ext.data.JsonStore({
			root: 'root',
			idProperty: 'merchantId',
			url: contextPath + '/mi.do?method=list&queryType=' + queryType,
			totalProperty : 'totalProperty',
			fields: [
				{name: 'merchantId', mapping: 'id'},
				{name: 'merchantName', mapping: 'name'}
			],
			autoDestroy: true
		}),
		valueField: 'merchantId',
		displayField: 'merchantName',
		allowBlank: false,
		autoScroll: true,
		triggerAction: 'all',
		emptyText: '请选择...',
		blankText: '必须选择商户',
		loadingText: '正在查询有效商户列表...',
		typeAhead: true,
		forceSelection : true,
		selectOnFocus : true,
		width: 300,
		pageSize: 20,
		queryParam: 'search_merchantName',
		lastQuery: '',
		listeners: {
			beforequery: function(qe){
				delete qe.combo.lastQuery;
			}
		}
	});
	
	/* 子商户名称列表&选择框 */
	var subMerchantNameComboBox = new Ext.form.ComboBox({
		id: 'searchSubMerchantName',
		name: 'searchSubMerchantName',
		fieldLabel: '门店名称',
		mode: 'local',
		store: new Ext.data.JsonStore({
			root: 'root',
			idProperty: 'merchantId',
			url: contextPath + '/mi.do?method=readSub',
			fields: [
				{name: 'merchantId', mapping: 'id'},
				{name: 'merchantName', mapping: 'name'}
			],
			autoDestroy: true,
			baseParams: {
				id: smsMerchantId
			}
		}),
		valueField: 'merchantId',
		displayField: 'merchantName',
		editable: false,
		disabled: true,
		autoScroll: true,
		triggerAction: 'all',
		emptyText: '可选择门店...',
		value: '无可选门店',
		loadingText: '正在查询有效店铺列表...',
		typeAhead: true,
		forceSelection : true,
		selectOnFocus : true,
		width: 300,
		pageSize: 20,
		queryParam: 'id',
		lastQuery: '',
		listeners: {
			beforequery: function(qe){
				delete qe.combo.lastQuery;
			}
		},
		selectedItems: selectedItems,
		tpl: new Ext.XTemplate(
			'<tpl for="."><div>',
				'<tpl if="this.isSelected(merchantId) == true">',
					'&nbsp<input type=\'checkbox\' checked onchange=\'Ext.selectSubMerchant(checked, {merchantId});\'>&nbsp&nbsp{merchantName}</input>',
				'</tpl>',
				'<tpl if="this.isSelected(merchantId) == false">',
					'&nbsp<input type=\'checkbox\' onchange=\'Ext.selectSubMerchant(checked, {merchantId});\'>&nbsp&nbsp{merchantName}</input>',
				'</tpl>',
			'</div></tpl>',
			{
				compiled: true,
				isSelected: function(merId){
					for(var i = 0; i < selectedItems.length; i++){
						if( selectedItems[i] == merId ){
							return true;
						}
					}
					return false;
				}
			}
		)
	});
	Ext.selectSubMerchant = function(checked, merId){
		if( checked ){
			selectedItems.push(merId);
		}else{
			selectedItems.pop(merId);
		}
		if( selectedItems.length != 0 ){
			subMerchantNameComboBox.setValue('已选择' + selectedItems.length + '个店铺');
		}else{
			subMerchantNameComboBox.reset();
		}
		
	};
	
	/* 营销短信等级范围选择 */
	var sendLevelCheckboxGroup = new Ext.form.CheckboxGroup({
		fieldLabel: '选择等级',
		id: 'sendLevelGroup',
		itemCls: 'x-check-group-alt',
		width: 300,
		columns: 4,
		items: [
			{boxLabel: '普通会员', checked: true, listeners: {
					check: function(checkbox, isChecked){ smsLevel[0] = isChecked; }
				}
			},
			{boxLabel: '银卡会员', checked: true, listeners: {
					check: function(checkbox, isChecked){ smsLevel[1] = isChecked; }
				}
			},
			{boxLabel: '金卡会员', checked: true, listeners: {
					check: function(checkbox, isChecked){ smsLevel[2] = isChecked; }
				}
			},
			{boxLabel: '白金会员', checked: true, listeners: {
					check: function(checkbox, isChecked){ smsLevel[3] = isChecked; }
				}
			}
		]
	});
	
	/* 积分范围上限 */
	var pointMinTextField = new Ext.form.TextField({
		id: 'pointMin',
		name: 'pointMin',
		hideLabel: true,
		width: 130,
		regex: /^\d+$/,
		regexText: '请输入非零正整数',
		validator: pointValidator,
		listeners: {
			change: function(textfield, newValue, oldValue){
				smsPointMin = newValue;
			}
		}
	});

	/* 积分范围上限 */
	var pointMaxTextField = new Ext.form.TextField({
		id: 'pointMax',
		name: 'pointMax',
		hideLabel: true,
		width: 130,
		regex: /^\d+$/,
		regexText: '请输入非零正整数',
		validator: pointValidator,
		listeners: {
			change: function(textfield, newValue, oldValue){
				smsPointMax = newValue;
			}
		}
	});
	
	var pointValidator = function(value){
		if( Ext.getCmp('pointMin').getValue() =='' || Ext.getCmp('pointMax').getValue() =='' ){
			return true;
		}else if( Ext.getCmp('pointMin').getValue() > Ext.getCmp('pointMax').getValue() ){
			var tmp = Ext.getCmp('pointMin').getValue();
			Ext.getCmp('pointMin').setValue( Ext.getCmp('pointMax').getValue() );
			Ext.getCmp('pointMax').setValue( tmp );
			Ext.alert('123');
		}
		return true;
	}
	
	
	
	/* 日期选择 */
	var dataField = new Ext.form.DateField({
		id: 'sendTime',
		name: 'sendTime',
		fieldLabel: '发送日期',
		width: 195,
		format: 'Y-m-d',
		allowBlank: false,
		emptyText: '请选择...',
		blankText: '必须选择日期',
		editable: false,
		disabled: true,
		listeners: {
			change: function(datefield, newValue, oldValue){
				smsSendDate = newValue;
			}
		}
	});
	
	/* 时间整点列表&选择框 */
	var clockComboBox = new Ext.form.ComboBox({
		id: 'timingClock',
		name: 'timingClock',
		fieldLabel: '时间',
		mode: 'local',
		emptyText: '请选择...',
		blankText: '必须选择时间',
		allowBlank: false,
		store: new Ext.data.ArrayStore({
			fields: ['clockId', 'clock'],
			data: [
				['09','9'],   ['10','10'],
				['11','11'], ['12','12'],
				['13','13'], ['14','14'],
				['15','15'], ['16','16'],
				['17','17'], ['18','18']
			]
		}),
		width: 50,
		valueField: 'clockId',
		displayField: 'clock',
		editable: false,
		disabled: true,
		triggerAction: 'all',
		listeners: {
			change: function(combo, newValue, oldValue){
				smsSendTime = newValue;
			}
		}
	});
	
	/* 积分范围上限 */
	var amountTextField = new Ext.form.TextField({
		id: 'amount',
		name: 'amount',
		fieldLabel: '额度',
		allowBlank: false,
		width: 50,
		regex: /^[0-9]+(.[0-9]{1})?$/,
		regexText: '只能有一位小数',
		listeners: {
			change: function(textfield, newValue, oldValue){
				amount = newValue;
			}
		},
		disabled: true
	});
	
	var sendTypeComboBox = new Ext.form.ComboBox({
		id: 'sendTypeSelect',
		name: 'sendTypeSelect',
		fieldLabel: '发送方式',
		mode: 'local',
		emptyText: '请选择...',
		store: new Ext.data.ArrayStore({
			fields: ['sendTypeId', 'sendTypeName'],
			data: [
				['1', '审核通过自动发送'],
				['2', '定时发送'],
				['3', '注册会员发送'],
				['4', '单次消费满额发送']
			]
		}),
		value: '1',
		width: 195,
		valueField: 'sendTypeId',
		displayField: 'sendTypeName',
		editable: false,
		triggerAction: 'all',
		listeners: {
			change: function(combo, newValue, oldValue){
				smsAmount = newValue;
			}
		}
	});
	
	/* 活跃度时间范围列表&选择框 */
	var activeComboBox = new Ext.form.ComboBox({
		id: 'activeSelect',
		name: 'activeSelect',
		fieldLabel: '活跃程度',
		mode: 'local',
		emptyText: '请选择...',
		store: new Ext.data.ArrayStore({
			fields: ['activeId', 'activeName'],
			data: [
				['1', '一个星期以内无消费记录的会员'],
				['2', '一个月以内无消费记录的会员'],
				['3', '三个月以内无消费记录的会员'],
				['4', '半年以内无消费记录的会员'],
				['5', '完全无消费记录的会员']
			]
		}),
		width: 300,
		valueField: 'activeId',
		displayField: 'activeName',
		editable: false,
		triggerAction: 'all',
		listeners: {
			change: function(combo, newValue, oldValue){
				smsActive = newValue;
			}
		}
	});
	
	var smsContentTitle = new Ext.form.TextField({
		id: 'smsContentTitle',
		name: 'smsContentTitle',
		fieldLabel: '短信主题',
		allowBlank: false,
		emptyText: '请填写...',
		blankText: '营销短信主题不能为空',
		maxLength: 32,
		maxLengthText: '短信主题不能超过32个字',
		width: 300,
		listeners: {
			change: function(textfield, newValue, oldValue){
				smsTitle = newValue;
			}
		}
	});

	var smsContentEdit = new Ext.form.TextArea({
		id: 'smsContent',
		name: 'smsContent',
		height: 180,
		fieldLabel: '短信内容',
		allowBlank: false,
		emptyText: '请填写...',
		enableKeyEvents: true,
		blankText: '营销短信内容不能为空',
		maxLength: 70,
		maxLengthText: '短信内容不能超过70个字',
		width: 300,
		listeners: {
			change: function(textarea, newValue, oldValue){
				smsContent = newValue;
			}
		},
		validateValue : function(value){
			if(value.length < 1 || value === this.emptyText){
				if(this.allowBlank){
					this.clearInvalid();
					return true;
				}else{
					this.markInvalid(this.blankText);
					return false;
				}
			}
			if(value.length < this.minLength){
				this.markInvalid(String.format(this.minLengthText, this.minLength));
				return false;
			}
			if(value.length > this.maxLength){
				this.markInvalid(String.format(this.maxLengthText, this.maxLength));
				return false;
			}
			if(this.vtype){
				var vt = Ext.form.VTypes;
				if(!vt[this.vtype](value, this)){
					this.markInvalid(this.vtypeText || vt[this.vtype +'Text']);
					return false;
				}
			}
			if(typeof this.validator == "function"){
				var msg = this.validator(value);
				if(msg !== true){
					this.markInvalid(msg);
					return false;
				}
			}
			if(this.regex && !this.regex.test(value)){
				this.markInvalid(this.regexText);
				return false;
			}
			return true;
		}
	});
	
	
	/*
	var textareaInsertAtCursor = function(textarea, v) {
		if (Ext.isIE) {
			textarea.el.focus();
			var sel = document.selection.createRange();
			sel.text = v;
			sel.moveEnd('character', v.length);
			sel.moveStart('character', v.length);
		}else
		{
			var startPos = textarea.el.dom.selectionStart;
			var endPos =textarea.el.dom.selectionEnd;
			textarea.el.dom.value = textarea.el.dom.value.substring(0, startPos)
				+ v
				+ textarea.el.dom.value.substring(endPos, textarea.el.dom.value.length);
			textarea.el.focus();				
			textarea.el.dom.setSelectionRange(endPos+v.length, endPos+v.length);
		}
	}
	*/
	
	var signerTextField = new Ext.form.TextField({
		id: 'billSigner',
		name: 'billSigner',
		allowBlank: false,
		emptyText: '请填写...',
		fieldLabel: '签单人',
		blankText: '签单人不能为空',
		regex: /^[a-zA-Z0-9\u4E00-\u9FA5()]*$/,
		regexText: '签单人由数字、26个英文字母以及中文汉字组成',
		maxLength: 32,
		maxLengthText: '签单人超过最大长度',
		width: 300,
		listeners: {
			change: function(textfield, newValue, oldValue){
				smsSigner = newValue;
			}
		}
	});
	
	/* 有效开始日期选择 */
	var dateFieldStart = new Ext.form.DateField({
		id: 'validDateStart',
		name: 'validDateStart',
		fieldLabel: '开始日期',
		width: 110,
		format: 'Y-m-d',
		emptyText: '请选择...',
		editable: false,
		listeners: {
			change: function(datefield, newValue, oldValue){
				smsValidDateStart = newValue;
			}
		}
	});
	/* 有效结束日期选择 */
	var dateFieldEnd = new Ext.form.DateField({
		id: 'validDateEnd',
		name: 'validDateEnd',
		fieldLabel: '结束日期',
		width: 110,
		format: 'Y-m-d',
		emptyText: '请选择...',
		editable: false,
		listeners: {
			change: function(datefield, newValue, oldValue){
				smsValidDateEnd = newValue;
			}
		}
	});
	
	var resetAll = function() {
		merchantNameComboBox.reset();
//		subMerchantNameComboBox.reset('无可选门店');
		Ext.getCmp('searchSubMerchantName').disable();
		subMerchantNameComboBox.setRawValue('无可选门店');
		sendLevelCheckboxGroup.reset();
		pointMinTextField.reset();
		pointMaxTextField.reset();
		dataField.reset();
		clockComboBox.reset();
		amountTextField.reset();
		sendTypeComboBox.reset();
		activeComboBox.reset();
		smsContentEdit.reset();
		smsContentTitle.reset();
		signerTextField.reset();
		Ext.getCmp('labelWordCount').el.dom.innerHTML = '<font size=2>0个字</font>';
	}
	
	var smsEditForm = new Ext.form.FormPanel({
			id: 'smsAuditFormID',
			labelWidth: 60,
			frame: true,
			bodyStyle: 'padding:5px 15px 5px',
			anchor: '100% 100% ',
			monitorValid: true,
			layout: 'form',
			renderTo: 'tableDiv',
			height: Ext.lib.Dom.getViewHeight(),
			autoScroll: true,
			items: [
				{
					layout: 'column',
					items: [
						{
							layout: 'form',
							items: [
								{
									xtype: 'fieldset',
									title: '营销范围',
									width: 400,
									border: 0,
									items: [
									merchantNameComboBox,
									subMerchantNameComboBox,
									{
										layout: 'column',
										items:[
											{ layout: 'form', items: [ sendLevelCheckboxGroup, activeComboBox ] },
											{ layout: 'form', items: [ dataField ] },
											{ layout: 'form', items: [ clockComboBox ] }
										]
									},
									{
										layout: 'column',
										fieldLabel: '积分范围',
										items:[
											{ layout: 'form', width: 135, items: [ pointMinTextField ] },
											{ xtype: 'label', width: 15, html: '~'},
											{ layout: 'form', width: 135, items: [ pointMaxTextField ] },
											{ xtype: 'label', html: '分'}
										]
									}
									]
								},
								{
									xtype: 'fieldset',
									title: '发送参数',
									width: 400,
									border: 0,
									items: [
										{
											layout: 'column',
											items:[ /*sendTypeRadioGroup*/
												{ layout: 'form', width: 275, items: [ sendTypeComboBox ] },
												{ layout: 'form', labelWidth: 35, items: [ amountTextField ] }
											]
										},
										{
											layout: 'column',
											items:[
												{ layout: 'form', width: 275, items: [ dataField ] },
												{ layout: 'form', labelWidth: 35, items: [ clockComboBox ] }
											]
										}
									]
								},
								{
									xtype: 'fieldset',
									title: '受理信息',
									width: 400,
									border: 0,
									items: [ signerTextField ]
								}
							]
						},
						{
							layout: 'form',
							items: [ { xtype: 'label', html: '&nbsp;&nbsp;&nbsp;'} ]
						},
						{
							layout: 'form',
							items: [
								{
									xtype: 'fieldset',
									title: '有效日期范围',
									width: 400,
									border: 0,
									items: [
										{
											layout: 'column',
											items:[
												{ layout: 'form', width: 190, items: [ dateFieldStart ] },
												{ layout: 'form', width: 180, items: [ dateFieldEnd ] }
											]
										}
									]
								},
								{
									xtype: 'fieldset',
									title: '营销短信内容',
									width: 400,
									height: 276,
									border: 0,
									items: [
										smsContentTitle,
										smsContentEdit,
										{
											fieldLabel: '已用字数',
											layout: 'column',
											defaults: {layout: 'form'},
											items: [
												{ items: { id: 'labelWordCount', xtype: 'label', html: '<font size=2>0个字</font>' } }
											]
										}
									]
								}
							]
						}
					]
				},
				{
					xtype: 'label',
					html: '<p>&nbsp;</p>'
				},
				{
					xtype: 'label',
					html: '<hr></hr>'
				},
				{
					xtype: 'label',
					text: '注意：若选择了"审核通过自动发送"方式，则该营销短信审核通过后，将会立即发送。'
				}
			],
			buttonAlign: 'left',
			buttons: [
				{
					text: '提交',
					formBind: true,
					handler: function() {
						Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
						smsMerchantId = merchantNameComboBox.getValue();
						smsLevel1 = 
						Ext.Ajax.request({
							url: contextPath + '/sa.do?method=append',
							method: 'post',
							params: {
								id: smsMerchantId,
								level: smsLevel,
								active: smsActive,
								pointMin: smsPointMin,
								pointMax: smsPointMax,
								type: smsSendType,
								date: smsSendDate,
								time: smsSendTime,
								amount: smsAmount,
								title: smsTitle,
								content: smsContent,
								signer: smsSigner,
								validDateStart: smsValidDateStart,
								validDateEnd: smsValidDateEnd,
								subMerchantIds: selectedItems
							},
							success: function(response){
								var data = Ext.decode(response.responseText);
								Ext.MessageBox.alert('结果', data.msg);
								resetAll();
								Ext.getBody().unmask();
							},
							failure: function(response){
								var data = Ext.decode(response.responseText);
								Ext.MessageBox.alert('失败', '通讯故障，请检查系统后重试');
								Ext.getBody().unmask();
							}
						});
					}
				},
				{
					text: '清空',
					handler: function() {
						resetAll();
					}
				}
			]
	});

	// 事件监听
	var addListener = function() {
		merchantNameComboBox.on('select', function(combo, record, idx){
			if( smsMerchantId == record.get('merchantId') )
				return;
			smsMerchantId = record.get('merchantId');
			smsMerchantName = record.get('merchantName');
			delete subMerchantNameComboBox.getStore().data;
			subMerchantNameComboBox.getStore().proxy = new Ext.data.HttpProxy({
				url: contextPath + '/mi.do?method=readSub&start=0&limit=20&id=' + smsMerchantId
			});
			
			subMerchantNameComboBox.getStore().on('load', function(store, records){
				var dataLength = records.length;
				if( dataLength != 0 ){
					var mainMerchantData = {
						merchantId: smsMerchantId,
						merchantName: smsMerchantName
					};
					var rc = new store.recordType(mainMerchantData, -1);
					store.insert(0, rc);
					subMerchantNameComboBox.setValue('');
					Ext.getCmp('searchSubMerchantName').enable();
				}else{
					subMerchantNameComboBox.setValue('无可选门店');
					Ext.getCmp('searchSubMerchantName').disable();
				}
			});
			subMerchantNameComboBox.getStore().reload();
			selectedItems = new Array();
		});
		
		Ext.getCmp('sendTypeSelect').on('select', function(combo, record, idx){
				smsSendType = record.get('sendTypeId');
				if( smsSendType == '2' ){
					Ext.getCmp('sendTime').enable();
					Ext.getCmp('timingClock').enable();
					amountTextField.clearInvalid();
					Ext.getCmp('amount').disable();
				} else if( smsSendType == '4' ){
					dataField.clearInvalid();
					clockComboBox.clearInvalid();
					Ext.getCmp('sendTime').disable();
					Ext.getCmp('timingClock').disable();
					Ext.getCmp('amount').enable();
				} else {
					dataField.clearInvalid();
					clockComboBox.clearInvalid();
					amountTextField.clearInvalid();
					Ext.getCmp('sendTime').disable();
					Ext.getCmp('timingClock').disable();
					Ext.getCmp('amount').disable();
				}
		});
		
		Ext.getCmp('searchMerchantName').on('change', function(combo, newValue, oldValue){
			smsMerchantId = newValue;
		});
		
		Ext.getCmp('smsContent').on('keyup', function(textarea, event){
			Ext.getCmp('labelWordCount').el.dom.innerHTML = '<font size=2>' + smsContentEdit.el.dom.value.length + '个字</font>';
		});
	}

	addListener();
	Ext.QuickTips.init();
});
