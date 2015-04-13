Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var id ='';
	var status = '';
//	var selectModel = new JustIn.grid.CheckboxSelectionModel({
//		singleSelect : true,
//		keepSelections : true
//	});
		
//	var clearSelections  = function() {		
//		selectedIds = new Array();
//		selectModel.selectedIds = selectedIds;
//		selectModel.clearSelections();
//	};	
		
	var colM = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(), 
//			selectModel,
			{
				header : "优惠券活动名称 ",
				dataIndex : "title",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			},			{
				header : "优惠券活动编号 ",
				dataIndex : "id",
				width : 100,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false
			},{
				header : "所属商户",
				dataIndex : "merchant.name",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "商户编号",
				dataIndex : "merchant.code",
				width : 150,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "提交时间",
				dataIndex : "createDate",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "优惠券类型",
				dataIndex : "couponType.desc",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "优惠券活动状态",
				dataIndex : "couponPloyStatus.desc",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "优惠券预计发送数量",
				dataIndex : "maxSendCount",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "优惠券实际发送数量",
				dataIndex : "sentCount",
				width : 120,
				editor : new Ext.form.TextField({
							readOnly : true
						}),
				sortable : false

			},{
				header : "优惠券验券数量",
				dataIndex : "validateCount",
				width : 120,
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
				'商户名称: ', {
					xtype : 'textfield',
					id : 'search_merchantName',
					name : 'search_merchantName',
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5()（）-]*$/,
					regexText : '商户中文名称应由数字、26个英文字母、中文汉字及部分特殊符号如-()等组成',
					maxLength: 64,
					maxLengthText:'字段超过最大长度'
				},'商户编号: ', {
					xtype : 'textfield',
					id : 'search_merchantCode',
					name : 'search_merchantCode',
					regex : /^[0-9]{15}$/,
					regexText : '商户编号为15位数字',
					maxLength:'15',
					maxLengthText:'商户编号超过15位'
				},
//				'优惠券活动状态: ',
//				new Ext.form.ComboBox({	
//					id : 'statusCombo',
//					store: new Ext.data.SimpleStore({
//						data: [
//							['0', '创建待审核'],
//							['1','活动进行中'],
//							['2','审核未通过'],
//							['3','暂停'],
//							['4','删除'],
//							['5','活动结束']
//						],
//						fields : ['value', 'desc']
//					}),
//					mode : 'local',
//					width : 100,
//					valueField : 'value',
//					displayField : 'desc',
//					value : '',
//					allowBlank : true,
//					editable : false,
//					emptyText : '请选择...',
//					typeAhead : true,
//					triggerAction : 'all',
//					forceSelection : false,					
//					selectOnFocus : true
//				}),
				'优惠券类型: ',
				new Ext.form.ComboBox({	
					id : 'typeCombo',
					store: new Ext.data.SimpleStore({
						data: [
							['1', '代金券'],
							['2','折扣券'],
							['3','兑换券'],
							['4','特价券']
						],
						fields : ['value', 'desc']
					}),
					mode : 'local',
					width : 100,
					valueField : 'value',
					displayField : 'desc',
					value : '',
					allowBlank : true,
					editable : false,
					emptyText : '请选择...',
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : false,					
					selectOnFocus : true
				}),
				'指定城市：',
				new Ext.ux.form.LovCombo({
					id:'cityCombo',
					width:100,
//					hideOnSelect:false
//					maxHeight:310,
					store : new Ext.data.JsonStore({
						remoteSort : true,
						url : contextPath + '/city.do?method=getCityStore',
						totalProperty : 'totalProperty',
						root : 'root',
						id : "id",
						fields : ['cityCode', 'cityName']
					}),
				   valueField :"cityCode",
				   displayField: "cityName",
				   labelSeparator:'：',
				   displaySeparator:';',
				   valueSeparator:',',
				   mode: 'remote',
				   forceSelection: true,
//					hiddenName:'cities',
				   editable: true,
				   readOnly : false,
				   triggerAction: 'all',
				   emptyText: '默认城市'
//						   minChars : 0,
//						   pageSize : 5
				})
			]});
		
	//创建工具栏
	var buttonBar = new Ext.Toolbar({
		id : 'buttonBar',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [
			'开始日期', {
				xtype: 'datefield',
				id: 'createStartDate',
				name: 'createStartDate',
				format: 'Y-m-d',
				editable: false,
				listeners:{
					'select' : function(){
						Ext.getCmp('createEndDate').setMinValue(this.value);
					}
				}
			},
			'结束日期', {
				xtype: 'datefield',
				id: 'createEndDate',
				name: 'createEndDate',
				format: 'Y-m-d',
				editable: false,
				listeners:{
					'select' : function(){
						Ext.getCmp('createStartDate').setMaxValue(this.value);
					}
				}
			},'-',{
				id : 'searchButton',
				text : '搜索',
				iconCls : 'search'
			}, {
				xtype : 'tbseparator'
			}, {
				id : 'refreshButton',
				text : '刷新',
				iconCls : 'refresh'
			}
//			,'-',{
//				id : 'showDetailButton',
//				text : '详情',
//				tooltip : '查看所选行详细信息',
//				tooltipType : 'title',
//				iconCls : 'detail',
//				disabled : true
//			}
		]
	});
	
	var grid = new Ext.ux.simpleGrid({
		title:'优惠券活动信息统计',
		url: contextPath + '/coupon.do?method=list',
		cm : colM,
//		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
		nameMapping : [
			{name : 'id'},
			{name : 'title'},
			{name : 'merchant.name'},
			{name : 'merchant.code'},
			{name : 'createDate'},
			{name : 'couponType.desc'},
			{name : 'couponPloyStatus.desc'},
			{name : 'maxSendCount'},
			{name : 'sentCount'},
			{name : 'validateCount'}
		],
        viewConfig : {
        	forceFit : true
        }
	});
	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value
			}
		});	
//		clearSelections();
	};

//	var	showForm = function(pid, m, form) {
//		var couponDetails = new Ext.ux.CouponForm({
//			url : contextPath + '/coupon.do',
//			method : m,
//			pid : pid
//		});
//		couponDetails.show();
//	};
	
//	var disableButtons = function(){
//		Ext.getCmp('showDetailButton').disable();
//	};
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
//			disableButtons();
			Ext.apply(this.baseParams, {
				'queryType' : 'TYPE_STATISTICS',
				'merchantCode' : Ext.get('search_merchantCode').dom.value,
				'merchantName' : Ext.get('search_merchantName').dom.value,
				'couponType' : Ext.getCmp('typeCombo').getValue(),
				'cities' : Ext.getCmp('cityCombo').getValue(),
				'createStartDate' : Ext.getCmp('createStartDate').getValue(),
				'createEndDate' : Ext.getCmp('createEndDate').getValue()
			});
		});
		grid.store.on('load', function() {
			this.each(function(record){
				if(record.get('maxSendCount') == null){
					record.set('maxSendCount','无限量');
				}
			});
		});
//		selectModel.on('rowselect', function(self, rowIndex, record) {
//			Ext.getCmp('showDetailButton').enable();
//			id = record.get('id');
//		});
		
//		selectModel.on('rowdeselect', function(self, rowIndex, record){
//			id = '';
//			disableButtons();
//		});
		
//		Ext.getCmp('showDetailButton').on('click', function() {
//			showForm(id, 'load');
//		});	
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('search_merchantCode').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('search_merchantName').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('search_merchantCode').dom.value = '';
			Ext.getCmp('search_merchantCode').clearInvalid();
			Ext.get('search_merchantName').dom.value = '';
			Ext.getCmp('search_merchantName').clearInvalid();
			Ext.getCmp('typeCombo').clearValue();
			Ext.getCmp('cityCombo').clearValue();
			Ext.getCmp('createStartDate').setValue('');
			Ext.getCmp('createEndDate').setValue('');
			loadData();	
		});
		
	};
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	buttonBar.render('topBarDiv');
	addListener();
	loadData();
	Ext.QuickTips.init();
});