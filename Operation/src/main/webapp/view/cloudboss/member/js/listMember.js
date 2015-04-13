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
		new Ext.grid.RowNumberer(),
		{
			header : "会员编号",
			dataIndex : "id",
			width : 50,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},
		{
			header : "会员姓名",
			dataIndex : "user.name",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "手机号码",
			dataIndex : "user.mobile",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		}
		,{
			header : "所属商户",
			dataIndex : "createMerchant.name",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "商户编号",
			dataIndex : "createMerchant.code",
			width : 120,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "会员等级",
			dataIndex : "level.desc",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "加入渠道",
			dataIndex : "source.desc",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "注册日期",
			dataIndex : "createDate",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "可用积分",
			dataIndex : "point",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "总积分",
			dataIndex : "totalPoint",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "邮箱",
			dataIndex : "user.email",
			width : 100,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		},{
			header : "联系地址",
			dataIndex : "user.address",
			width : 210,
			editor : new Ext.form.TextField({
						readOnly : true
					}),
			sortable : false
		}]);

     	colM.defaultSortable = true;

    	var startLastConsumeDate = new Ext.form.DateField({
    		fieldLabel : '有效期起',
    		editable : false,
    		id : 'startLastConsumeDate',
    		name : 'startLastConsumeDate',
    		width : 110,
    		format : 'Y-m-d'
    	});

    	var endLastConsumeDate = new Ext.form.DateField({
    		fieldLabel : '有效期止',
    		editable : false,
    		id : 'endLastConsumeDate',
    		name : 'endLastConsumeDate',
    		width : 110,
    		format : 'Y-m-d'
    	});
    	
    	var startCreateDate = new Ext.form.DateField({
    		fieldLabel : '注册时间起',
    		editable : false,
    		id : 'startCreateDate',
    		name : 'startCreateDate',
    		width : 110,
    		format : 'Y-m-d'
    	});

    	var endCreateDate = new Ext.form.DateField({
    		fieldLabel : '注册时间止',
    		editable : false,
    		id : 'endCreateDate',
    		name : 'endCreateDate',
    		width : 110,
    		format : 'Y-m-d'
    	});
    	
	    var datasource = new Ext.data.Store({  
		                proxy : new Ext.data.HttpProxy({  
		                	url : contextPath + '/memberManager.do?method=getMemberLevel'
		                        }),  
		                reader : new Ext.data.JsonReader({root : 'root'}, ['key', 'value'])  
		            });  
		    datasource.load(); 
    	var levelCom = new Ext.form.ComboBox({
			store : datasource,
			mode : 'local',//remote&local
			id : 'level',
			width : 102,
			fieldLabel : '会员级别',
			hiddenName : 'shopState',
			displayField : 'value',
			valueField : 'key',
			value : '',
			allowBlank : true,
			editable : false,
			emptyText : '所有会员',
			typeAhead : true,
			triggerAction : 'all',
			forceSelection : false,							
			selectOnFocus : true
		});	
     	var searchBar = new Ext.Toolbar({
    		width : Ext.lib.Dom.getViewportWidth(),
    		renderTo:"topBarDiv",
    		items : [
    				'姓名: ', {
    					xtype : 'textfield',
    					id : 'name',
    					name : 'name',
    					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
    					regexText : '姓名应由字母、数字、汉字组成',
    					maxLength:'100',
    					maxLengthText:'姓名长度不得超过100位'
    				},'手机号码: ', {
    					xtype : 'textfield',
    					id : 'mobile',
    					name : 'mobile',
    					regex : /^\d{11}$/,
    					regexText : '手机号为11位数字'
    				},'商户编号: ', {
    					xtype : 'textfield',
    					id : 'merchantCode',
    					name : 'merchantCode',
    					regex : /^[0-9]{15}$/,
    					regexText : '商户编号为15位数字',
    					maxLength:'15',
    					maxLengthText:'商户编号超过15位'
    				},'会员级别: ',levelCom
    				,'加入渠道:',
    				new Ext.form.ComboBox({	
    					id : 'source',
    					store: new Ext.data.SimpleStore({
    						data: [
    						    ['0','不限'],
    							['1','POS机'],
    							['2','管理平台'],
    							['3','网站'],
    							['4','短信'],
    						    ['5','云掌柜']
    						],
    						fields : ['value', 'desc']
    					}),
    					mode : 'local',
//    					hiddenName : 'source',
    					width : 100,
    					valueField : 'value',
    					displayField : 'desc',
    					value : '',
    					allowBlank : true,
    					editable : false,
    					emptyText : '不限',
    					typeAhead : true,
    					triggerAction : 'all',
    					forceSelection : false,					
    					selectOnFocus : true
    				})]
    	});
     	
     	
     	//创建工具栏
    	var buttonBar = new Ext.Toolbar({
    				id : 'buttonBar',
    				width : Ext.lib.Dom.getViewportWidth(),
    				items : [
					'最后消费时间: ',startLastConsumeDate,'至',endLastConsumeDate,'-','注册时间: ',startCreateDate,'至',endCreateDate,'-',
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
    				}]
    			});
    	
     	
    	var grid = new Ext.ux.simpleGrid({
			title:'会员查看列表',
			url: contextPath + '/memberManager.do?method=listMember',
			cm : colM,
			sm : selectModel,
			pageSize : 10,
			height : Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 57 : Ext.isIE7 ? 57 : Ext.isIE8 ? 55 : 55),
			nameMapping : [
			            {name : 'id'},   
			   			{name : 'user.name'},
			   			{name : 'user.mobile'},
			   			{name : 'createMerchant.name'},
			   			{name : 'createMerchant.code'},
			   			{name : 'level.desc'},
			   			{name : 'source.desc'},
			   			{name : 'createDate'},
			   			{name : 'point'},
			   			{name : 'totalPoint'},
			   			{name : 'user.email'},
			   			{name : 'user.address'}
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
	};
	
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
	};

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'level' : levelCom.getValue(),//Ext.get('level').dom.value,
				'name' : Ext.get('name').dom.value,
				'merchantCode' : Ext.get('merchantCode').dom.value,
				'startLastConsumeDate' : Ext.get('startLastConsumeDate').dom.value,
				'endLastConsumeDate' : Ext.get('endLastConsumeDate').dom.value,
				'startCreateDate' : Ext.get('startCreateDate').dom.value,
				'endCreateDate' : Ext.get('endCreateDate').dom.value,
				'mobile' : Ext.get('mobile').dom.value,
				'source' : Ext.getCmp('source').getValue()
			});
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			id = '';
		});
		
		Ext.getCmp('searchButton').on('click', function() {
			if (!Ext.getCmp('name').isValid(false)) {
				return false;
			}
			if (!Ext.getCmp('mobile').isValid(false)) {
				return false;
			}
			loadData();
		});
		Ext.getCmp('refreshButton').on('click', function() {
			Ext.get('level').dom.value = '所有会员';
			levelCom.value='';
			Ext.getCmp('level').clearInvalid();
			Ext.get('name').dom.value = '';
			Ext.getCmp('name').clearInvalid();
			Ext.get('mobile').dom.value = '';
			Ext.getCmp('mobile').clearInvalid();
			Ext.get('startLastConsumeDate').dom.value = '';
			Ext.getCmp('startLastConsumeDate').clearInvalid();
			Ext.get('endLastConsumeDate').dom.value = '';
			Ext.getCmp('endLastConsumeDate').clearInvalid();
			Ext.get('startCreateDate').dom.value = '';
			Ext.getCmp('startCreateDate').clearInvalid();
			Ext.get('endCreateDate').dom.value = '';
			Ext.getCmp('endCreateDate').clearInvalid();
			Ext.getCmp('source').setRawValue('不限');
			Ext.getCmp('source').setValue('0');
			Ext.get('merchantCode').dom.value = '';
			Ext.getCmp('merchantCode').clearInvalid();
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
