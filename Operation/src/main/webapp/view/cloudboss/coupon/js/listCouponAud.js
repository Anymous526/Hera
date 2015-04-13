Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var id ='';
	var status = '';
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
				}),'-',
				{
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
			]});
		
	//创建工具栏
	var buttonBar = new Ext.Toolbar({
		id : 'buttonBar',
		width : Ext.lib.Dom.getViewportWidth(),
		items : [{// 顶层工具条
			id : 'showDetailButton',
			text : '详情',
			tooltip : '查看所选行详细信息',
			tooltipType : 'title',
			iconCls : 'detail',
			disabled : true
		}, '-',{
			id : 'audPassButton',
			text : '审核通过',
			tooltip : '审核通过',
			tooltipType : 'title',
			iconCls : 'yes',
			disabled : true,
			handler : function() {
				Ext.MessageBox.confirm('审核通过',
						'确定审核通过?  ',audPass);
			}
		}, {
			xtype : 'tbseparator'
		}, {
			id : 'audNotPassButton',
			text : '审核不通过',
			tooltip : '审核不通过',
			tooltipType : 'title',
			iconCls : 'no',
			disabled : true,
			handler : function() {
				Ext.MessageBox.confirm('审核不通过',
						'确定审核不通过?  ',audNotPass);
			}
		}]
	});
	
	var grid = new Ext.ux.simpleGrid({
		title:'优惠券活动审核列表',
		url: contextPath + '/coupon.do?method=list',
		cm : colM,
		sm : selectModel,
		pageSize : 20,
		height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
		nameMapping : [
			{name : 'title'},
			{name : 'id'},
			{name : 'merchant.name'},
			{name : 'merchant.code'},
			{name : 'createDate'},
			{name : 'couponType.desc'},
			{name : 'couponPloyStatus.desc'},
			{name : 'couponPloyStatus.value'}
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
		clearSelections();
	};

	var	showForm = function(pid, m, form) {
		var couponDetails = new Ext.ux.CouponForm({
			url : contextPath + '/coupon.do',
			method : m,
			pid : pid
		});
		couponDetails.show();
	};
	
	var ajaxRequest = function(pass,auditInfo) {
		Ext.getBody().mask("正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : contextPath + '/coupon.do',
			params : {
				method : 'aud',
				auditPass : pass,
				id : id,
				desc : auditInfo
			},
			timeout : 20000,
			success : function(response, options) {
				data = Ext.decode(response.responseText);
				Ext.MessageBox.show({
//					minWidth : 350,
//					maxWidth : 350,		
					title : '提示',
					msg : data.msg + '  ',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO,
					fn : function() {
						grid.store.reload();
						disableButtons();
					}
				});
				Ext.getBody().unmask();
			},
			failure : function(response, options) {
				Ext.MessageBox.show({
					title : '系统执行失败',
					msg : '无法连接到服务器,或服务器操作异常!  ',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO
				});
				Ext.getBody().unmask();
			}
		});
	};	
	
	var audPass = function(btn) {
		if (btn == 'yes') {
			Ext.Msg.prompt('审核通过', '请输入原因(可选)', function(btn, text){
				if(btn == 'ok'){
			    	if(text.length > 100){
			    		Ext.Msg.alert('提示','          文本内容超出最大长度');
			    		return;
			    	}
				    ajaxRequest(true,text);
				}
			},true,true);

		}
	};	
		
	var audNotPass = function(btn) {
		if (btn == 'yes') {
			Ext.Msg.prompt('审核不通过', '请输入原因', function(btn, text){
				if(btn == 'ok'){
					if(text.length == 0){
						Ext.Msg.alert('提示','          审核不通过,原因不能为空');
						return;
					}
			    	if(text.length > 100){
			    		Ext.Msg.alert('提示','          文本内容超出最大长度');
			    		return;
			    	}
				    ajaxRequest(false,text);
				}
			},true,true);

		}
	};		
	
	var disableButtons = function(){
		Ext.getCmp('showDetailButton').disable();
		Ext.getCmp('audPassButton').disable();
		Ext.getCmp('audNotPassButton').disable();
	};
	
	var addListener = function(){
		grid.store.on('beforeload', function() {
			disableButtons();
			Ext.apply(this.baseParams, {
				'queryType' : 'TYPE_AUD',
				'merchantCode' : Ext.get('search_merchantCode').dom.value,
				'merchantName' : Ext.get('search_merchantName').dom.value,
				'cities' : Ext.getCmp('cityCombo').getValue()
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			Ext.getCmp('showDetailButton').enable();
			id = record.get('id');
			status = record.get('couponPloyStatus.value');
			if(status == 0){
				Ext.getCmp('audPassButton').enable();
				Ext.getCmp('audNotPassButton').enable();
			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
			disableButtons();
		});
		
		Ext.getCmp('showDetailButton').on('click', function() {
			showForm(id, 'load');
		});	
		
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
			Ext.getCmp('cityCombo').clearValue();
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