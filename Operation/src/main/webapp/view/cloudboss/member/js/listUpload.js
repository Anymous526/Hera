Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = '';
	var merchantCode;
	var win;// 详情/新建/修改窗口
	var method;// show/create/edit
	var audAction;

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
			header : "会员编号",
			dataIndex : "memberNo",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "姓名",
			dataIndex : "memberName",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "手机号码",
			dataIndex : "mobile",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员等级",
			dataIndex : "level",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "可用积分",
			dataIndex : "useIntegral",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "总积分",
			dataIndex : "integral",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "有效期起",
			dataIndex : "sDate",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "有效期止",
			dataIndex : "eDate",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员生日",
			dataIndex : "birthday",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "邮箱",
			dataIndex : "eMail",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "联系地址",
			dataIndex : "address",
			width : 150,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		}]);

     	colM.defaultSortable = true;
    	
     	var searchBar = new Ext.Toolbar({
    		width : Ext.lib.Dom.getViewportWidth(),
    		renderTo:"topBarDiv",
    		items : [
    				'会员编号: ', {
    					xtype : 'textfield',
    					id : 'memberNo',
    					regex : /^\d*$/,
    					regexText : '会员编号由全数字组成',
    					name : 'memberNo',
    					maxLength:'100',
    					maxLengthText:'会员 编号长度不得超过10位'
    				},'姓名: ', {
    					xtype : 'textfield',
    					id : 'memberName',
    					name : 'memberName',
    					regex : /^\d*$/,
    					regexText : '姓名应由字母、数字、汉字组成',
    					maxLength:'11',
    					maxLengthText:'姓名长度不得超过100位'
    				},'手机号码: ', {
    					xtype : 'textfield',
    					id : 'mobile',
    					name : 'mobile',
    					regex : /^\d*$/,
    					regexText : '手机号由全数字组成',
    					maxLength:'11',
    					maxLengthText:'商户编码长度不得超过11位'
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
    				}]
    	});
     	
     	
     	//创建工具栏
    	var buttonBar = new Ext.Toolbar({
    				id : 'buttonBar',
    				width : Ext.lib.Dom.getViewportWidth(),
    				items : [
//    				         {			// 顶层工具条
//    					id : 'asignMerchantCode',
//    					text : '配置商户编码',
//    					tooltip : '配置商户编码',
//    					tooltipType : 'title',
//    					iconCls : 'func',
//    					disabled : true
//    				},'-',{
//    					id : 'cancelMerchantCode',
//    					text : '取消商户编码',
//    					tooltip : '取消商户编码',
//    					tooltipType : 'title',
//    					iconCls : 'remove',
//    					disabled : true
//    				}
    				]
    			});
    	
     	
    	var grid = new Ext.ux.simpleGrid({
			title:'导入历史列表',
			url: contextPath + '/memberManager.do?method=listUpload',
			cm : colM,
			sm : selectModel,
			pageSize : 20,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
			   			{name : 'memberNo'},
			   			{name : 'memberName'},
			   			{name : 'mobile'},
			   			{name : 'level'},
			   			{name : 'useIntegral'},
			   			{name : 'integral'},
			   			{name : 'sDate'},
			   			{name : 'eDate'},
			   			{name : 'birthday'},
			   			{name : 'eMail'},
			   			{name : 'address'}
			   		]
		});
     	
	var loadData = function() {
		grid.store.load({
			params : {
				start : 0,
				limit : grid.pageSizeCombo.value
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
							disableButtons();
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
	
	
//	//编辑窗口
//	var getMav = function() {
//		merchantService.eidt(id,method,showModelView);
//	}
//	
//	var showModelView = function(mav) {
//		var model = mav.model;
//		win = new Ext.Window( {
//			height : 150,
//			width : 350,
//			resizable :false,
//			title :"配置商户编号",
//			modal :true,
//			constrain :false,
//			constrainHeader :false,
//			closeAction :'close',
//			stateful :true,
//			closable :true,
//			border :true,
//			layout :'fit'
//		});
//		win.on('beforeclose', loadData);
//		
//		var merchantForm;
//		merchantForm = editEncodeMerchantForm(id.toString(),merchantCode, mav, method, win);
//		
//		win.add(merchantForm);
//		win.doLayout();
//		win.show();
//		Ext.QuickTips.init();
//	}
	
//	var disableButtons = function(){
//		Ext.getCmp('asignMerchantCode').disable();
//		Ext.getCmp('cancelMerchantCode').disable();
//	}
	
	

//	function cancelMerchantCode(btn) {
//		if (btn == 'yes') {
//			Ext.getBody().mask("正在执行审核,请等待。", 'x-mask-loading');
//			Ext.Ajax.request({
//						url : contextPath
//								+ '/merchantCode.do',
//						params : {
//							method 		:	method,
//							id 			: 	id
//						},
//						success : function(response) {
//							data = Ext.decode(response.responseText);
//							resultMessageShow(data);
//							Ext.getBody().unmask();
//						},
//						failure : function(response) {
//							var data = Ext.decode(response.responseText);
//							resultMessageShow(data);
//							Ext.getBody().unmask();
//						}
//					});
//		}
//	}
	

	var addListener = function() {
		grid.store.on('beforeload', function() {
//			disableButtons();
			Ext.apply(this.baseParams, {
				'search_memberNo_like' : Ext.get('memberNo').dom.value,
				'search_memberName_like' : Ext.get('memberName').dom.value,
				'search_mobile_like' : Ext.get('mobile').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
//			merchantCode = record.get('merchantCode');
//			Ext.getCmp('asignMerchantCode').enable();
//			Ext.getCmp('cancelMerchantCode').enable();
//			
//			//为配置编码
//			if (merchantCode == ""){
//				Ext.getCmp('cancelMerchantCode').disable();
//			}
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
//			merchantCode='';
//			disableButtons();
		});
		
//		Ext.getCmp('asignMerchantCode').on('click', function() {
//			method = 'eidt';
//			getMav();
//		});
//		
//		Ext.getCmp('cancelMerchantCode').on('click', function() {
//			method = 'cancelMerchantCode';
//			Ext.MessageBox.confirm('取消商户编码', '你确定要取消商户编码？', cancelMerchantCode);
//		});		
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('memberNo').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('memberName').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('mobile').isValid(false)) {
				return false;
			}
			loadData();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('memberNo').dom.value = '';
			Ext.getCmp('memberNo').clearInvalid();
			Ext.get('memberName').dom.value = '';
			Ext.getCmp('memberName').clearInvalid();
			Ext.get('mobile').dom.value = '';
			Ext.getCmp('mobile').clearInvalid();
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
