Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var merchantCode = '';
	var merchantName = '';
	var memberForm = 0;
	var smsType = true;
	var data;
	var resultStore = new Ext.data.JsonStore({
		fields: ['mobile','name','petName','Gender','area','birthday','post','email','msn','qq','cardType','cardNumber','level','memberCard','memberPoint']
	});	

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
			header: "商户地址",
			dataIndex: "addr",
			width: 300,
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
					maxLength:'40',
					maxLengthText:'商户名称长度不得超过40位'
				},
				'商户编号', {
					xtype: 'textfield',
					id: 'searchMerchantCode',
					name: 'searchMerchantCode',
					maxLength:'15',
					maxLengthText:'商户编码长度必须是15位'
				},
				/*'手机号码', {
					xtype: 'textfield',
					id: 'searchMerchantMobile',
					name: 'searchMerchantMobile',
					maxLength:'11',
					maxLengthText:'手机号码长度必须是11位'
				},*/
				'-',{
					id: 'searchButton',
					text: '搜索',
					iconCls: 'search'
				}, {
					xtype: 'tbseparator'
				}, {
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
					id: 'memberAdd',
					text: '会员导入',
					iconCls: 'import',
					disabled: false
				},{
					xtype: 'label',
					html: '<a href="../member/template/MEMBER_UPLOAD.xls">点击下载会员导入模板</a>'
				}]
	});

	var grid = new Ext.ux.simpleGrid({
			title:'商户信息列表',
			url: contextPath + '/mi.do?method=list&queryType=TYPE_VALIDANDFREEZED',
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
				{name: 'addr', mapping: 'businessAddress'},
				{name: 'regTime', mapping: 'createDate'}
			]
		});
     	
	var loadData = function() {
		grid.store.load({
			params: {
				start: 0,
				limit: grid.pageSizeCombo.value,
				queryType: 'TYPE_VALIDANDFREEZED'
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

	function memberAdd() {
		var win = new Ext.Window({
			height: 130,
			width: 325,
			resizable: false,
			title: "会员数据导入",
			modal: true,
			constrain: false,
			constrainHeader: false,
			closeAction: 'close',
			stateful: true,
			closable: true,
			border: true,
			layout: 'fit'
		});

		var memberForm = new Ext.form.FormPanel({
			id: 'memberFormID',
			labelWidth: 60,
			frame: true,
			bodyStyle: 'padding:5px 15px 5px',
			anchor: '100% 100% ',
			monitorValid: true,
			layout: 'form',
			fileUpload: true,  
			defaults: {
				width: 200
			},
			items: [
   	        	  {
 	        		  xtype: 'textfield',   
 	        		  fieldLabel: '文件名',   
 	        		  name: 'loadFile',   
 	        		  id : 'loadFile',
 	        		  inputType: 'file'//文件类型   
 	        	  }
 	        	  ],
			buttonAlign: 'right',
			buttons: [
				{
					text: '导入预览',
					id  : 'queryButton'
				},
				{
					text: '导入保存',
					id  : 'saveButton'
				}
			]
		});
		
		Ext.getCmp('queryButton').on('click', function() {
			var xlsName = Ext.getCmp('loadFile').getValue();
			if (xlsName == '' || xlsName.indexOf('.xls') == -1) {
				Ext.MessageBox.show({
					title : '提示',
					msg : "请选择会员导入文件(xls格式)。",
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO
				});
				return false;
			}else{			
				memberForm.getForm().submit({   
					url:contextPath + '/memberManager.do?method=memberUploadQuery',
					method : 'post',
					success: function(form, action){  
						data = Ext.decode(action.response.responseText);
						resultStore.loadData(data.root);
						queryData();	
					},   
					failure: function(form, action){   
						var data = Ext.decode(action.response.responseText);
						Ext.MessageBox.show({
							title: '提示',
							msg: data.msg,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.INFO
						})
					}   
				});
			}
		});

		Ext.getCmp('saveButton').on('click', function() {
			var xlsName = Ext.getCmp('loadFile').getValue();
			if (xlsName == '' || xlsName.indexOf('.xls') == -1) {
				Ext.MessageBox.show({
					title : '提示',
					msg : "请选择会员导入文件(xls格式)。",
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.INFO
				});
				return false;
			}else{			
				memberForm.getForm().submit({   
					url:contextPath + '/memberManager.do?method=memberUpload',
					params : {
					merchantCode : merchantCode
					},
					success: function(form, action){   
						var data = Ext.decode(action.response.responseText);
						Ext.MessageBox.show({
							title: '提示',
							msg: data.msg,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.INFO
					});
						win.close();
//						Ext.getCmp('saveButton').disable();
					},   
					failure: function(form, action){   
						var data = Ext.decode(action.response.responseText);
						Ext.MessageBox.show({
							title: '提示',
							msg: data.msg,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.INFO
					});
					}   
				});
			}
		});
		
		win.add(memberForm);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();
	}

	function queryData() {
		var win = new Ext.Window({
			height: 350,
			width: 800,
			resizable: false,
			title: "会员导入预览列表",
			modal: true,
			constrain: false,
			constrainHeader: false,
			closeAction: 'close',
			stateful: true,
			closable: true,
			border: true,
			layout: 'fit'
		});
		
		var colM1 = new Ext.grid.ColumnModel([
     		new Ext.grid.RowNumberer(), 
     		{
     			header : "手机号码",
     			dataIndex : "mobile",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "姓名",
     			dataIndex : "name",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "昵称",
     			dataIndex : "petName",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "性别",
     			dataIndex : "Gender",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "地区",
     			dataIndex : "area",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "出生日期",
     			dataIndex : "birthday",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "邮政编码",
     			dataIndex : "post",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "电子邮件",
     			dataIndex : "email",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "MSN",
     			dataIndex : "msn",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "QQ",
     			dataIndex : "qq",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "证件类型",
     			dataIndex : "cardType",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "证件号码",
     			dataIndex : "cardNumber",
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
     			header : "会员卡号",
     			dataIndex : "memberCard",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		},{
     			header : "会员积分",
     			dataIndex : "memberPoint",
     			width : 150,
     			editor : new Ext.form.TextField({
     						readOnly : true
     					}),
     			sortable : false
     		}]);

          	colM1.defaultSortable = true;
          	
        	var grid1 = new Ext.ux.simpleGrid({
    			store: resultStore,
    			cm : colM1,
    			pageSize : 200000,
    			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
    			nameMapping : [
    			   			{name : 'mobile'},
    			   			{name : 'name'},
    			   			{name : 'petName'},
    			   			{name : 'Gender'},
    			   			{name : 'area'},
    			   			{name : 'birthday'},
    			   			{name : 'post'},
    			   			{name : 'email'},
    			   			{name : 'msn'},
    			   			{name : 'qq'},
    			   			{name : 'cardType'},
    			   			{name : 'cardNumber'},
    			   			{name : 'level'},
    			   			{name : 'memberCard'},
    			   			{name : 'memberPoint'}
    			   		]
    		});
		win.add(grid1);
		win.doLayout();
		win.show();
		Ext.QuickTips.init();
	}
		
	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('memberAdd').disable();
			Ext.apply(this.baseParams, {
				'merchantName': Ext.get('searchMerchantName').dom.value,
				'merchantCode': Ext.get('searchMerchantCode').dom.value
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			merchantId = record.get('merchantId');
			merchantCode = record.get('merchantCode');
			merchantName = record.get('merchantName');
			Ext.getCmp('memberAdd').enable();
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			merchantId = '';
			merchantCode = '';
			merchantName = '';
			Ext.getCmp('memberAdd').disable();
		});
		
		Ext.getCmp('memberAdd').on('click', function() {
			memberAdd();
		});		

//		Ext.getCmp('memberDownload').on('click', function() {
//			var downloadForm = new Ext.form.FormPanel({
//		     	  renderTo:'topBarDiv',
//		     	  hidden: true,   
//		     	  labelAlign: 'left',
//		     	  labelWidth: 40,
//		     	  layout: 'column',
//		     	  frame:true,   
//		     	  width : Ext.lib.Dom.getViewportWidth(),  
//		     	  fileUpload: true
//			});
//			downloadForm.getForm().submit({   
//				url:contextPath + '/memberManager.do?method=memberDownload',
//				success: function(form, action){  
//				  
//				},   
//				failure: function(){   
//					Ext.Msg.alert('错误', '文件下载失败');   
//				}   
//			});
//		});
		
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
