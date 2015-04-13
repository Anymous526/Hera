Ext.namespace('Ext.ux');
Ext.ux.MerchantForm = Ext.extend(Ext.Window, {

	height : 450,
	width :500,
	resizable : true,
	title : "商户信息管理",
	modal : true,
	constrain : false,
	constrainHeader : false,
	closeAction : 'close',
	stateful : true,
	closable : true,
	border : true,
	regFlag : '',
	layout : 'fit',
	method : '',
	pid : '',
	url : '',
	logoPath : '',
	useFlag : true,
	cityDs : new Ext.data.Store({
	     proxy: new Ext.data.HttpProxy({      
	         url: ''     
	     }),      
	     reader: new Ext.data.JsonReader({      
		     root: 'root',      
		     id: 'id'  
		 }, [      
		     {name: 'id', mapping: 'id'},      
		     {name: 'name', mapping: 'name'}      
		 ])      
	}),
	districtDs : new Ext.data.Store({
	     proxy: new Ext.data.HttpProxy({      
	         url: ''    
	     }),      
	     reader: new Ext.data.JsonReader({      
		     root: 'root',      
		     id: 'id'  
		 }, [      
		     {name: 'id', mapping: 'id'},      
		     {name: 'name', mapping: 'name'}      
		 ])      
	}),
	posType : new Array(2),
	initComponent : function() {
		this.loadData();
		this.loadComponent();
		this.items = [this.formPanel];
		Ext.QuickTips.init();
		this.addListener();
		Ext.ux.MerchantForm.superclass.initComponent.call(this);
		if (this.method == 'create') {
			this.title = "新增商户";
		} else if (this.method == 'update') {
			this.title = "修改商户";
		} else {
			this.title = "商户详细信息";
		}
	},

	loadComponent : function() {
		var ppForm = this;
		Ext.form.Field.prototype.msgTarget = 'side';
		this.formPanel = new Ext.form.FormPanel({
			id : 'formPanel',
//			labelWidth : 135,
			border : false,
			bodyStyle : 'padding:5px 15px 5px;background-image:url('
					+ contextPath
					+ '/images/ext_images/background-lightblue.JPG);background-position:center;background-repeat:repeat;',
			monitorValid : true,
			fileUpload : true,
//			width : 350,
			
	items : [{
		id : 'merTab',
		xtype : 'tabpanel',
		plain : true,
		activeTab : 0,
		height : 375,
		defaults : {
			bodyStyle : 'padding:10px;background-image:url('
					+ contextPath
					+ '/images/ext_images/background-lightblue.JPG);background-position:center;background-repeat:repeat;'
		},
		items : [{
			title : '基本信息',
			layout : 'form',
			autoScroll : true,
			labelWidth : 130,
			defaults : {
				width : 250
			},
//			defaultType : 'textfield',
			items : [
				new Ext.form.ComboBox({
					store : new Ext.data.JsonStore({
						remoteSort : true,
						url : contextPath + '/mi.do?method=getParentList',
						totalProperty : 'totalProperty',
						root : 'root',
						id : "id",
						fields : ['id', 'name']
					}),
					mode : 'remote',
					id : 'parentCombo',
					width : 102,
					fieldLabel : '隶属商户',
					hiddenName : 'parentId',
					displayField : 'name',
					valueField : 'id',
					value : '',
					editable : true,
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : true,
					allowBlank : true,
					emptyText : ppForm.method =='load' ? '' : '请选择...',
					blankText : ppForm.method =='load' ? '' : '请选择...',
					selectOnFocus : true,
	//				readOnly : ppForm.method == 'load' ? true : false,
					disabled : ppForm.method == 'create' ? false : true,
					cls : ppForm.method == 'create' ? '':'item-field-readonly',
					resizable : true,
					minChars : 0,
					pageSize : 10,
					queryParam : 'merchantName'
				}),			         
			{
				xtype : 'textfield',
				id : 'name',
				name : 'name',
				fieldLabel : '商户中文名称',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5()（）-]*$/,
				regexText : '商户中文名称应由数字、26个英文字母、中文汉字及部分特殊符号如-()等组成',
				allowBlank : false,
				blankText : '商户中文名称不能为空',
				maxLength : 64,
				maxLengthText : '商户中文名称超过最大长度'
			},{
				xtype : 'textfield',
				id : 'shortName',
				name : 'shortName',
				fieldLabel : '商户中文名简称',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5()（）-]*$/,
				regexText : '商户中文名简称应由数字、26个英文字母、中文汉字及部分特殊符号如-()等组成',
				allowBlank : false,
				blankText : '商户中文名简称不能为空',
				maxLength : 32,
				maxLengthText : '商户中文名简称超过最大长度'
			},{
				xtype : 'textfield',
				id : 'englishName',
				name : 'englishName',
				fieldLabel : '英文名称',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z 0-9.,()&]*$/,
				regexText : '英文名称由数字、26个英文字母及特殊符号(如.&等)组成',
				allowBlank : true,
				maxLength : 64,
				maxLengthText : '英文名称超过最大长度'
			},{
				xtype : 'textfield',
				id : 'code',
				name : 'code',
				fieldLabel : '商户编号',
				value : '',
//				allowBlank : false,
//				blankText : '商户编号不能为空',
//				maxLength : 15,
//				maxLengthText : '商户编号超过最大长度',
//				regex : /^[0-9]{15}$/,
//				regexText : '商户编号为15位数字',
				readOnly : true,
				disabled : ppForm.method == 'create' ? true : false,
				cls : 'item-field-readonly'
//				plugins : ppForm.method == 'create'
//						? [Ext.ux.plugins.RemoteValidator]
//						: '',
//				rvOptions : {
//					url : contextPath
//							+ '/mi.do?method=checkCodeNew',
//					params : {
//						id : ppForm.pid
//					},
//					ignoreCheckOnBeginning : false
//				}
			},{
				xtype : 'textfield',
				id : 'status',
				name : 'status',
				fieldLabel : '商户状态',
				value : '',
				readOnly : true,
				disabled : true,
				cls : 'item-field-readonly',
				listeners : {
					render : function(){
						if(ppForm.method == 'create'){
							this.getEl().up('.x-form-item').setDisplayed(false); 
						}
					}	
				}
			},			

			//如果是单店，填0
			{
				xtype : 'textfield',
				id : 'childCount',
				name : 'childCount',
				fieldLabel : '门店数',
				value : '',
				readOnly : true,
				disabled : true,
				cls : 'item-field-readonly',
				listeners : {
					render : function(){
						if(ppForm.method == 'create'){
							this.getEl().up('.x-form-item').setDisplayed(false); 
						}
					}	
				}
			},
//			new Ext.form.ComboBox({
//				store : ppForm.areaDs,
//				mode : 'remote',
//				id : 'areaCombo',
//				width : 102,
//				fieldLabel : '所在区域',
//				hiddenName : 'area',
//				displayField : 'value',
//				valueField : 'key',
//				value : '',
//				typeAhead : true,
//				triggerAction : 'all',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':''
//			}),
			{
				xtype : 'textfield',
				id : 'parentOrganization',
				name : 'parentOrganization',
				fieldLabel : '隶属机构',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '隶属机构应由数字、26个英文字母以及中文汉字组成',
				allowBlank : false,
				blankText : '隶属机构不能为空',
				maxLength : 64,
				maxLengthText : '隶属机构超过最大长度'
			},
			new Ext.form.ComboBox({
				store : ppForm.categoryDs,
				mode : 'remote',
				id : 'merchantCategory',
				width : 102,
				fieldLabel : '行业类别',
				hiddenName : 'categoryId',
				displayField : 'name',
				valueField : 'id',
				value : '',
				editable : true,
				typeAhead : true,
				triggerAction : 'all',
				forceSelection : true,
				allowBlank : false,
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : '请选择...',
				selectOnFocus : true,
				resizable : true,
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}),
			
//			new Ext.form.ComboBox({
//				store : ppForm.parentOrganizationDs,
//				mode : 'remote',
//				id : 'parentOrganizationCombo',
//				width : 102,
//				fieldLabel : '隶属机构',
//				hiddenName : 'parentOrganization',
//				displayField : 'value',
//				valueField : 'key',
//				value : '',
//				typeAhead : true,
//				triggerAction : 'all',
//				readOnly : true,
//				cls : 'item-field-readonly'
//			}),
			{
			layout: 'column',
			fieldLabel : '区域信息',
			baseCls: 'x-plain',
			id : 'areaInfo',
			items: [ 
				new Ext.form.ComboBox({
					store : ppForm.provinceDs,
					mode : 'remote',
					id : 'provinceCombo',
					hideLabel : true,
					width : 76,
//					hiddenName : 'province',
					displayField : 'name',
					valueField : 'id',
					value : '',
					editable : false,
					readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : false,
//					allowBlank : false,
//					blankText : '请选择...',
//					emptyText : ppForm.method =='load' ? '' : '请选择...',
					emptyText : '',
					cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
					selectOnFocus : true,
					resizable : true,
					disabled : true
				}),
				{
					xtype: 'label',
					cls : 'item-field-readonly',
					width: 10, 
					html: '&nbsp;-&nbsp;'
				},
				new Ext.form.ComboBox({
//					store : ppForm.cityDs,
					store : new Ext.data.JsonStore({
						remoteSort : true,
						url : contextPath + '/city.do?method=getCityStore',
						totalProperty : 'totalProperty',
						root : 'root',
						id : "id",
						fields : ['cityCode', 'cityName']
					}),
//					mode : 'local',
					mode : 'remote',
					id : 'cityCombo',
					hideLabel : true,
					width : 76,
					hiddenName : 'city',
					displayField : 'cityName',
					valueField : 'cityCode',
//					displayField : 'name',
//					valueField : 'id',
					value : '',
					editable : false,
					readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
					disabled : ppForm.method == 'load' ?  true : false,
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : false,
					allowBlank : false,
					blankText : '请选择...',
					emptyText : ppForm.method =='load' ? '' : '请选择...',
					cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
					selectOnFocus : true,
					resizable : true
				}),
				{
					xtype: 'label',
					cls : 'item-field-readonly',
					width: 10, 
					html: '&nbsp;-&nbsp;'
				},
				new Ext.form.ComboBox({
					store : ppForm.districtDs,
					mode : 'local',
					id : 'districtCombo',
					hideLabel : true,
					width : 76,
					hiddenName : 'district',
					displayField : 'name',
					valueField : 'id',
					value : '',
					editable : false,
					readOnly : ppForm.method == 'load' ?  true : false,
					disabled : ppForm.method == 'load' ?  true : false,
					typeAhead : true,
					triggerAction : 'all',
					forceSelection : false,
					allowBlank : false,
					blankText : '请选择...',
					emptyText : ppForm.method =='load' ? '' : '请选择...',
					cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
					selectOnFocus : true,
					resizable : true,
				    listeners: {
				        // delete the previous query in the beforequery event or set
				        // combo.lastQuery = null (this will reload the store the next time it expands)
				        beforequery: function(qe){
				            delete qe.combo.lastQuery;
				        }
				    }

				})
			]},
			{
				xtype : 'textfield',
				fieldLabel : '营业地址',
				id : 'businessAddress',
				name : 'businessAddress',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5-,()，、（）]*$/,
				regexText : '营业地址应由数字、26个英文字母以及中文汉字组成',
				allowBlank : false,
				blankText : '营业地址不能为空',
				maxLength : 100,
				maxLengthText : '营业地址超过最大长度'
			}, 
			{
				xtype : 'textfield',
				fieldLabel : '搜索关键字',
				id : 'keyWords',
				value : '',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5-]*$/,
				regexText : '可输入数字、或中英文',
				allowBlank : true,
				maxLength : 100,
				maxLengthText : '关键字超过最大长度',
				listeners : {
					render : function(){
						if(ppForm.method == 'load'){
							this.getEl().up('.x-form-item').setDisplayed(false); 
						}
					}
				}
			}, 
			{
				fieldLabel : '地图定位',
				baseCls: 'x-plain',
				id : 'map',
				layout: 'column',
				items: [ 
					{
						xtype: 'label',
						cls : 'item-field-readonly',
						width: 35, 
						html: '经度:'
					},  
					{
						xtype: 'textfield',
						hideLabel : true,
						cls : 'item-field-readonly',
						width : 89,
						id : 'longitude',
						name : 'longitude',
						value : '',
						editable : false,
						allowBlank : false,
						readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
						cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly'
					}, 
					{
						xtype: 'label',
						cls : 'item-field-readonly',
						width: 35, 
						html: '&nbsp;纬度:'
					},
					{
						xtype: 'textfield',
						hideLabel : true,
						cls : 'item-field-readonly',
						width : 89,
						id : 'latitude',
						name : 'latitude',
						value : '',
						editable : false,
						allowBlank : false,
						readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
						cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly'
					}
				]	
			},
			{
				xtype : 'textfield',
				fieldLabel : '营业地址邮编',
				id : 'businessAddressCode',
				name : 'businessAddressCode',
				value : '',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				allowBlank : true,
				maxLength : 6,
				maxLengthText : '营业地址邮编超过最大长度',
				regex : /^[0-9]{6}$/,
				regexText : '营业地址邮编由6位数字组成'
			},
//			{
//				xtype : 'textfield',
//				fieldLabel : '公交信息',
//				id : 'busInformation',
//				name : 'busInformation',
//				value : '',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				allowBlank : false,
//				blankText : '公交信息不能为空',
//				maxLength : 32,
//				maxLengthText : '公交信息超过最大长度',
//				regex : /^[a-zA-Z0-9]*$/,
//				regexText : '公交信息应由数字、字母组成'
//			},{
//				xtype : 'textfield',
//				fieldLabel : '消费环境',
//				id : 'restaurant',
//				name : 'restaurant',
//				value : '',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				allowBlank : false,
//				blankText : '消费环境不能为空',
//				maxLength : 32,
//				maxLengthText : '消费环境超过最大长度',
//				regex : /^[a-zA-Z0-9]*$/,
//				regexText : '消费环境应由数字、字母组成'
//			},{
//				xtype : 'textfield',
//				fieldLabel : '停车信息',
//				id : 'parkingInformation',
//				name : 'parkingInformation',
//				value : '',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
//				allowBlank : false,
//				blankText : '停车信息不能为空',
//				maxLength : 32,
//				maxLengthText : '停车信息超过最大长度',
//				regex : /^[a-zA-Z0-9]*$/,
//				regexText : '停车信息应由数字、字母组成'
//			},		
//			new Ext.form.ComboBox({
//				store : ppForm.yesOrNoDs,
//				mode : 'local',
//				id : 'wifiCombo',
//				width : 102,
//				fieldLabel : '是否有无线网络',
//				hiddenName : 'wifi',
//				displayField : 'value',
//				valueField : 'key',
//				value : '',
//				editable : false,
//				typeAhead : true,
//				triggerAction : 'all',
//				forceSelection : false,
//				allowBlank : false,
//				emptyText : ppForm.method =='load' ? '' : '请选择...',
//				blankText : '请选择...',
//				selectOnFocus : true,
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':''
//			}),
			{
				layout: 'column',
				fieldLabel : '营业时间',
				baseCls: 'x-plain',
				items: [ 
//					{	
//						layout: 'form',
//						items : [{
//							xtype : 'textfield',
//							width : 105,
//							hideLabel : true,
//							id : 'businessTime',
//							name : 'businessTime',
//							value : ''
//						}]
//					},
					new Ext.form.ComboBox({
						store : businessTime1Ds,
						mode : 'local',
						id : 'businessTime1Combo',
						hideLabel : true,
						width : 109,
						hiddenName : 'businessTime1',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						allowBlank : false,
						blankText : '请选择...',
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
						selectOnFocus : true
					}),
					{
						xtype: 'label',
						cls : 'item-field-readonly',
						width: 30, 
						html: '&nbsp;&nbsp;至'
					},
					new Ext.form.ComboBox({
						store : businessTime2Ds,
						mode : 'local',
						id : 'businessTime2Combo',
						hideLabel : true,
						width : 109,
						hiddenName : 'businessTime2',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						allowBlank : false,
						blankText : '请选择...',
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
						selectOnFocus : true
					})
				]
			
			}, 
			{
				xtype : 'textfield',
				fieldLabel : '商户电话',
				id : 'merchantTelephone',
				name : 'merchantTelephone',
				value : '',
				allowBlank : true,
				maxLength : 45,
				maxLengthText : '商户电话超过最大长度',
				regex :  /^[0-9-]{7,13}([,|，][0-9-]{7,13}([,|，][0-9-]{7,13})?)?$/,
				regexText : '请输入正确格式的电话号码，多部电话可使用","隔开',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},{
				xtype : 'textfield',
				fieldLabel : '商户传真',
				id : 'merchantFax',
				name : 'merchantFax',
				value : '',
				allowBlank : true,
				maxLength : 15,
				maxLengthText : '商户传真超过最大长度',
				regex : /^[0-9-]{6,15}$/,
				regexText : '商户传真由6-15位数字组成',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},{
				xtype : 'textfield',
				fieldLabel : '商户网站',
				id : 'merchantWeb',
				name : 'merchantWeb',
				value : '',
				allowBlank : true,
				maxLength : 50,
				maxLengthText : '商户网站超过最大长度',
				regex : /^(?:([a-zA-Z]+):)?(\/{0,3})([0-9.\-a-zA-Z]+)(?::(\d+))?(?:\/([^?#])*)?(?:\?([^#]*))?(?:#(.*))?$/,
				regexText : '网站格式不正确',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},{
				xtype : 'textfield',
				fieldLabel : '联系人姓名',
				id : 'contactName',
				name : 'contactName',
				value : '',
				allowBlank : false,
				blankText : '联系人姓名不能为空',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '联系人姓名应由数字、26个英文字母以及中文汉字组成',
				maxLength : 20,
				maxLengthText : '联系人姓名超过最大长度',				
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},{
				xtype : 'textfield',
				fieldLabel : '联系电话',
				id : 'contactTelephone',
				name : 'contactTelephone',
				value : '',
				allowBlank : false,
				blankText : '联系电话不能为空',
				maxLength : 15,
				maxLengthText : '联系电话超过最大长度',
				regex :  /^[0-9-]{6,15}$/,
				regexText : '联系电话由6-15位数字组成',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},{
				xtype : 'textfield',
				fieldLabel : '商户管理员手机号',
				id : 'managerMobile',
				name : 'managerMobile',
				value : '',
				allowBlank : false,
				blankText : '手机号不能为空',
				maxLength : 11,
				maxLengthText : '手机号超过最大长度',
				regex : /^(13|15|18)\d{9}$/,
				regexText : '手机号格式不正确',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':''
				disabled : ppForm.method == 'create' ? false : true
//				plugins : ppForm.method == 'create'
//						? [Ext.ux.plugins.RemoteValidator]
//						: '',
//				rvOptions : {
//					url : contextPath
//							+ '/mi.do?method=checkMobileNew',
//					params : {
//						id : ppForm.pid
//					},
//					ignoreCheckOnBeginning : false
//				},
//				listeners : {
//					render : function(){
//						if(ppForm.method != 'create'){
//							this.getEl().up('.x-form-item').setDisplayed(false); 
//						}
//					}	
//				}
			},
			{
				xtype : 'textfield',
				fieldLabel : '商户拓展人员',
				id : 'sales',
				name : 'sales',
				value : '',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '商户拓展人员应由数字、26个英文字母以及中文汉字组成',
				maxLength : 20,
				maxLengthText : '字段超过最大长度',				
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},
			{
				xtype : 'textfield',
				fieldLabel : '短信编码',
				id : 'num',
				name : 'num',
				value : '',
				allowBlank : false,
				blankText : '短信编码不能为空',
				regex : /^[1-9]{1}[0-9]{2,7}$/,
				regexText : '短信编码为3-8位数字(首位不能是0)',
				maxLength : 8,
				maxLengthText : '字段超过最大长度',				
				readOnly : ppForm.method == 'create' ? false : true,
				cls : ppForm.method == 'create' ? '':'item-field-readonly'
			},
			new Ext.form.CheckboxGroup({
				id : 'posCheckbox',
				fieldLabel : 'POS机业务类型',
				itemCls : 'x-check-group-alt',
//				columns : 1,
				allowBlank : false,
				blankText : '请选择',
				invalidText : '请选择',
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				items : [{
					id : 'posType0',
					boxLabel : '支持会生活业务',
					disabled : ppForm.method == 'load' ? true : false,
					checked : ppForm.method == 'create' ? true : false
				}, {
					id : 'posType1',
					boxLabel : '支持银行业务',
					disabled : ppForm.method == 'load' ? true : false,
					checked : ppForm.method == 'create' ? true : false
				}]
//				listeners : {
//					render : function(){
//						this.markInvalid();
//					}
//				}
			}),
			new Ext.form.ComboBox({
				store : ppForm.yesOrNoDs,
				mode : 'local',
				id : 'discountFlagCombo',
				fieldLabel : 'POS是否进行打折计算',
				hiddenName : 'discountFlag',
				displayField : 'value',
				valueField : 'key',
				value : '',
				editable : false,
				readOnly : ppForm.method == 'load' ? true : false,
				typeAhead : true,
				triggerAction : 'all',
				forceSelection : false,
				allowBlank : false,
				blankText : '请选择...',
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				cls : ppForm.method == 'load' ? 'item-field-readonly' : '',
				selectOnFocus : true
			}),
			{
				fieldLabel : 'POS费率(百分比)',
				id : 'rating',
				name : 'rating',
				xtype : 'textfield',
				maxLength : 10,
				maxLengthText : '费率超过最大长度',
				regex : /^(0\.0[1-9]|0\.[1-9][0-9]?|[1-9]\d{0,1}(\.\d{1,2})?)$/,
				regexText : '费率为大于0小于100的整数或小数，整数位最多为2位，若为小数，小数点后最多保留两位',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},
			{
				fieldLabel : '赠送短信条数',
				id : 'presentSmsCount',
				name : 'presentSmsCount',
				xtype : 'textfield',
				maxLength : 8,
				maxLengthText : '字段超过最大长度',
				regex : /^(0|([1-9](\d+)?))$/,
				regexText : '字段格式不正确',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}
			]
		},{
				id : 'businessInfo',
				title : '经营信息',
				layout : 'form',
				autoScroll : true,
				labelWidth : 145,
				defaults : {
					width : 220
				},
//				defaultType : 'textfield',
				items : [
//					如果不是签约商户，以下字段全部可以为空。
					new Ext.form.ComboBox({
						store : ppForm.yesOrNoDs,
						mode : 'local',
						id : 'signMerchantCombo',
						fieldLabel : '是否是签约商户',
						hiddenName : 'signMerchant',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						readOnly : ppForm.method == 'create'|| ppForm.method == 'update' ? false : true,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						allowBlank : false,
						blankText : '请选择...',
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						cls : ppForm.method == 'create' || ppForm.method == 'update' ? '': 'item-field-readonly',
						selectOnFocus : true
					}),
			{
				xtype : 'textfield',
				fieldLabel : '注册登记号',
				id : 'registNo',
				name : 'registNo',
				value : '',
				blankText : '注册登记号不能为空',
				regex : /^[a-zA-Z0-9]*$/,
				regexText : '注册登记号格式不正确',
				maxLength : 30,
				maxLengthText : '注册登记号超过最大长度',						
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '税务登记号',
				id : 'taxNo',
				name : 'taxNo',
				value : '',
				blankText : '税务登记号不能为空',
				regex : /^[a-zA-Z0-9]*$/,
				regexText : '税务登记号格式不正确',
				maxLength : 30,
				maxLengthText : '税务登记号超过最大长度',						
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '机构代码证',
				id : 'organizationCard',
				name : 'organizationCard',
				value : '',
				blankText : '机构代码证不能为空',
				regex : /^[-a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '格式不正确',
				maxLength : 30,
				maxLengthText : '字段超过最大长度',					
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '注册地址',
				id : 'registAddress',
				name : 'registAddress',
				value : '',
				blankText : '注册地址不能为空',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5-,()，、（）]*$/,
				regexText : '注册地址由数字、26个英文字母以及中文汉字组成',
				maxLength : 100,
				maxLengthText : '注册地址超过最大长度',						
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '法人代表',
				id : 'legal',
				name : 'legal',
				value : '',
				blankText : '法人代表不能为空',
				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '法人代表应由数字、26个英文字母以及中文汉字组成',
				maxLength : 20,
				maxLengthText : '字段超过最大长度',						
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},
			new Ext.form.ComboBox({
				store : merchantPropertyStore,
				mode : 'local',
				id : 'merchantPropertyCombo',
				width : 102,
				fieldLabel : '商户性质',
				hiddenName : 'property',
				displayField : 'value',
				valueField : 'key',
				value : '',
				editable : false,
				typeAhead : true,
				triggerAction : 'all',
				forceSelection : false,
				invalidText : '请选择...',
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : '请选择...',
				selectOnFocus : true,
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':'',
				listeners : {
					focus : function(){
					}
				}
			}), {
				xtype : 'textfield',
				fieldLabel : '注册资本(万元)',
				id : 'registCapital',
				name : 'registCapital',
				value : '',
				blankText : '注册资本不能为空',
				regex : /^[1-9][0-9]{0,9}$/,
				regexText : '注册资本格式不正确',
				maxLength : 10,
				maxLengthText : '注册资本超过最大长度',	
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},
			new Ext.form.ComboBox({
				store : businessLandStore,
				mode : 'local',
				id : 'businessLandCombo',
				width : 102,
				fieldLabel : '营业用地性质',
				hiddenName : 'businessLand',
				displayField : 'value',
				valueField : 'key',
				value : '',
				editable : false,
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : '请选择...',
				typeAhead : true,
				triggerAction : 'all',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}),{
				xtype : 'textfield',
				fieldLabel : '营业用地面积(平米)',
				id : 'businessArea',
				name : 'businessArea',
				value : '',
				maxLength : 10,
				maxLengthText : '字段超过最大长度',
				regex : /^[1-9][0-9]{0,9}$/,
				regexText : '营业用地面积格式不正确',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},
			new Ext.form.ComboBox({
				store : businessLocationStore,
				mode : 'local',
				id : 'businessLocationCombo',
				width : 102,
				fieldLabel : '经营地段',
				hiddenName : 'businessLocation',
				displayField : 'value',
				valueField : 'key',
				value : '',
				editable : false,
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : '请选择...',
				typeAhead : true,
				triggerAction : 'all',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}),
			new Ext.form.ComboBox({
				store : regionsStore,
				mode : 'local',
				id : 'regionsCombo',
				width : 102,
				fieldLabel : '经营区域',
				hiddenName : 'regions',
				displayField : 'value',
				valueField : 'key',
				value : '',
				editable : false,
				emptyText : ppForm.method =='load' ? '' : '请选择...',
				blankText : '请选择...',
				typeAhead : true,
				triggerAction : 'all',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}),{
				xtype : 'textfield',
				fieldLabel : '员工人数',
				id : 'employeesNumber',
				name : 'employeesNumber',
				value : '',
				maxLength : 10,
				maxLengthText : '员工人数超过最大长度',
				regex : /^[0-9]*$/,
				regexText : '员工人数应由数字组成',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''									
			},{
				xtype : 'textfield',
				fieldLabel : '经营范围(主业)',
				id : 'rangeMain',
				name : 'rangeMain',
				value : '',
				regex : /^[：:；;、,，.。a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '字段格式有误',
				maxLength : 100,
				maxLengthText : '该项超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '经营范围(副业)',
				id : 'rangeSideline',
				name : 'rangeSideline',
				value : '',
				regex : /^[：:；;、,，.。a-zA-Z0-9\u4E00-\u9FA5]*$/,
				regexText : '字段格式有误',
				maxLength : 100,
				maxLengthText : '该项超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '分支机构',
				id : 'branch',
				name : 'branch',
				value : '',
				regex : /^[1-9][0-9]{0,7}$/,  
				regexText : '分支机构由数字组成(首位不能为零)',
				maxLength : 8,
				maxLengthText : '分支机构超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''	
			},{
				xtype : 'textfield',
				fieldLabel : '前一年营业额(万元)',
				id : 'turnoverYear',
				name : 'turnoverYear',
				value : '',
				regex : /^(0\.0[1-9]|0\.[1-9][0-9]?|[1-9]\d{0,7}(\.\d{1,2})?)$/,
				regexText : '营业额为大于0的整数或小数，整数位最多为8位，若为小数，小数点后最多保留两位',
				maxLength : 11,
				maxLengthText : '营业额超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},{
				xtype : 'textfield',
				fieldLabel : '前一年利润(万元)',
				id : 'profitYear',
				name : 'profitYear',
				value : '',
				regex : /^(0\.0[1-9]|0\.[1-9][0-9]?|[1-9]\d{0,7}(\.\d{1,2})?)$/,
				regexText : '利润为大于0的整数或小数，整数位最多为8位，若为小数，小数点后最多保留两位',
				maxLength : 11,
				maxLengthText : '利润超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},{
				xtype : 'textfield',
				fieldLabel : '预计月平均银行卡营业额(万元)',
				id : 'turnoverBankCard',
				name : 'turnoverBankCard',
				value : '',
				regex : /^(0\.0[1-9]|0\.[1-9][0-9]?|[1-9]\d{0,7}(\.\d{1,2})?)$/,
				regexText : '营业额为大于0的整数或小数，整数位最多为8位，若为小数，小数点后最多保留两位',
				maxLength : 11,
				maxLengthText : '营业额超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			},{
				xtype : 'textfield',
				fieldLabel : '预计每张签购单平均交易额(元)',
				id : 'signTrading',
				name : 'signTrading',
				value : '',
				regex : /^(0\.0[1-9]|0\.[1-9][0-9]?|[1-9]\d{0,7}(\.\d{1,2})?)$/,
				regexText : '交易额为大于0的整数或小数，整数位最多为8位，若为小数，小数点后最多保留两位',
				maxLength : 11,
				maxLengthText : '交易额超过最大长度',
				readOnly : ppForm.method == 'load' ? true : false,
				cls : ppForm.method == 'load' ? 'item-field-readonly':''
			}
//			,{
//				xtype : 'textfield',
//				fieldLabel : '会员系统情况',
//				id : 'memberSys',
//				name : 'memberSys',
//				value : '',
//				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
//				regexText : '会员系统情况应由数字组成',
//				maxLength : 20,
//				maxLengthText : '字段超过最大长度',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':''
//			},{
//				xtype : 'textfield',
//				fieldLabel : '储值卡系统情况',
//				id : 'cardSys',
//				name : 'cardSys',
//				value : '',
//				regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
//				regexText : '储值卡系统情况应由数字组成',
//				maxLength : 20,
//				maxLengthText : '字段超过最大长度',
//				readOnly : ppForm.method == 'load' ? true : false,
//				cls : ppForm.method == 'load' ? 'item-field-readonly':''
//			}
			]
		},{
				id : 'bankInfo',
				title : '银行收单信息',
				layout : 'form',
				autoScroll : true,
				defaults : {
					width : 250
				},
				labelWidth :135,
//				defaultType : 'textfield',
				items : [
//如果不是收单商户，以下字段全部可以为空。    
					new Ext.form.ComboBox({
						store : ppForm.yesOrNoDs,
						mode : 'local',
						id : 'bankCardMerchantCombo',
						width : 102,
						fieldLabel : '是否是银行卡收单商户',
						hiddenName : 'bankCardMerchant',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						allowBlank : false,
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						blankText : '请选择...',
						selectOnFocus : true,
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					}),
//					{
//						xtype : 'textfield',
//						fieldLabel : '收单机构',
//						id : 'acceptOrganization',
//						name : 'acceptOrganization',
//						value : '',
//						regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
//						regexText : '该项由数字组成',
//						maxLength : 20,
//						maxLengthText : '字段超过最大长度',
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''
//					},
					{
						xtype : 'textfield',
						fieldLabel : '银行商户编号',
						id : 'bankMerchantCode',
						name : 'bankMerchantCode',
						value : '',
						regex : /^[0-9]{15}$/,
						regexText : '银行商户编号由15位数字组成',
						maxLength : 15,
						maxLengthText : '银行商户编号超过最大长度',
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					},
//					new Ext.form.ComboBox({
//						store : merchantTypeStore,
//						mode : 'local',
//						id : 'merchantTypCombo',
//						width : 102,
//						fieldLabel : '商户类型',
//						hiddenName : 'merchantType',
//						displayField : 'typeName',
//						valueField : 'typeCode',
//						value : '',
//						editable : true,
//						typeAhead : true,
//						triggerAction : 'all',
//						forceSelection : true,
//						emptyText : ppForm.method =='load' ? '' : '请选择...',
//						blankText : '请选择...',
//						selectOnFocus : true,
//						resizable : true,
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''
//					}),
//					{
//						xtype : 'textfield',
//						fieldLabel : '商户类型',
//						id : 'merchantType',
//						name : 'merchantType',
//						value : '',
//						regex : /^[0-9]{4}$/,
//						regexText : '商户类型由4位数字组成',
//						maxLength : 4,
//						maxLengthText : '字段超过最大长度',
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''
//					},
					{
						xtype : 'textfield',
						fieldLabel : '商户开户银行',
						id : 'merchantBank',
						name : 'merchantBank',
						value : '',
						regex : /^[a-zA-Z0-9\u4E00-\u9FA5-（）()]*$/,
						regexText : '字段格式有误',
						maxLength : 64,
						maxLengthText : '字段超过最大长度',
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					},
					{
						xtype : 'textfield',
						fieldLabel : '开户银行基本账户账号',
						id : 'accountSettlement',
						name : 'accountSettlement',
						value : '',
						regex : /^[0-9]*$/,
						regexText : '该项由数字组成',
						maxLength : 50,
						maxLengthText : '字段超过最大长度',
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					},			
					new Ext.form.ComboBox({
						store : transactionTypeStore,
						mode : 'local',
						id : 'transactionTypeCombo',
						width : 102,
						fieldLabel : '商户交易类型',
						hiddenName : 'transactionType',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						invalidText : '请选择...',
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						blankText : '请选择...',
						selectOnFocus : true,
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					}),
					new Ext.form.ComboBox({
						store : payTypeStore,
						mode : 'local',
						id : 'payTypeCombo',
						width : 102,
						fieldLabel : '商户付款方式',
						hiddenName : 'payType',
						displayField : 'value',
						valueField : 'key',
						value : '',
						editable : false,
						typeAhead : true,
						triggerAction : 'all',
						forceSelection : false,
						invalidText : '请选择...',
						emptyText : ppForm.method =='load' ? '' : '请选择...',
						blankText : '请选择...',
						selectOnFocus : true,
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					}),{
						xtype : 'textfield',
						fieldLabel : '对账单地址',
						id : 'billAddress',
						name : 'billAddress',
						value : '',
						regex : /^[a-zA-Z0-9\u4E00-\u9FA5-,()，、（）]*$/,
						regexText : '对账单地址应由数字、26个英文字母以及中文汉字组成',
						maxLength : 100,
						maxLengthText : '字段超过最大长度',		
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
					},{
						xtype : 'textfield',
						fieldLabel : '对账单邮编',
						id : 'billZipcode',
						name : 'billZipcode',
						value : '',
						regex : /^[0-9]{6}$/,
						regexText : '邮编由6位数字组成',
						maxLength : 6,
						maxLengthText : '字段超过最大长度',	
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''									
					},{
						xtype : 'textfield',
						fieldLabel : '对账单收件人',
						id : 'billPerson',
						name : 'billPerson',
						value : '',
						regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
						regexText : '该项应由数字、26个英文字母以及中文汉字组成',
						maxLength : 20,
						maxLengthText : '字段超过最大长度',	
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''									
					},{
						xtype : 'textfield',
						fieldLabel : '对账单邮件地址',
						id : 'billEmail',
						name : 'billEmail',
						value : '',
						regex : /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,  
						regexText : '请输入正确格式的Email',
						maxLength : 100,
						maxLengthText : '邮件地址超过最大长度',
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''									
					},{
						xtype : 'textfield',
						fieldLabel : '对账单传真',
						id : 'billFax',
						name : 'billFax',
						value : '',
						regex : /^[0-9]*$/,
						regexText : '对账单传真由数字组成',
						maxLength : 15,
						maxLengthText : '字段超过最大长度',	
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''									
					},
//					{
//						xtype : 'textfield',
//						fieldLabel : 'POS终端数量',
//						id : 'billFax',
//						name : 'billFax',
//						value : '',
//						regex : /^[0-9]$/,
//						regexText : '对账单传真由数字组成',
//						maxLength : 15,
//						maxLengthText : '字段超过最大长度',	
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''									
//					},
					{
						xtype : 'datefield',
		                fieldLabel : '签约日期',
		                id : 'signDate',
		                name : 'signDate',
						format : 'Ymd',
						value : '',
						editable : false,
						readOnly : ppForm.method == 'load' ? true : false,
						cls : ppForm.method == 'load' ? 'item-field-readonly':''
		            }
//					,
//		            {
//						xtype : 'datefield',
//		                fieldLabel : '最后交易日期',
//		                id : 'signDate',
//		                name : 'signDate',
//						format : 'Ymd',
//						value : '',
//						editable : false,
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''
//		            },{
//						xtype : 'datefield',
//		                fieldLabel : '最后扎帐日期',
//		                id : 'signDate',
//		                name : 'signDate',
//						format : 'Ymd',
//						value : '',
//						editable : false,
//						readOnly : ppForm.method == 'load' ? true : false,
//						cls : ppForm.method == 'load' ? 'item-field-readonly':''
//		            }
		            
		  ]
		},{
			id : 'otherInfo',
			title : '其他信息',
			layout : 'form',
			autoScroll : true,
			defaults : {
				width : 270
			},
			labelWidth :110,
			items : [
				{
					xtype : 'textarea',
					fieldLabel : '商户简介',
					height : 150,
					id : 'info',
					name : 'info',
					value : '',
					maxLength : 1900,
					maxLengthText : '商户简介超过最大长度',
					regex : /^[^<>&'"\|\\]+$/,
					regexText : '不能包含特殊字符',
					readOnly : ppForm.method == 'load' ? true : false,
					cls : ppForm.method == 'load' ? 'item-field-readonly':''
				},
//				{
//					xtype : 'textarea',
//					fieldLabel : '备注',
//					height : 100,
//					id : 'comments',
//					name : 'comments',
//					value : ''	,
//					maxLength : 1900,
//					maxLengthText : '备注超过最大长度',
//					regex : /^[^<>&'"\|\\]+$/,
//					regexText : '不能包含特殊字符',
//					readOnly : ppForm.method == 'load' ? true : false,
//					cls : ppForm.method == 'load' ? 'item-field-readonly':''
//				},
				new Ext.form.RadioGroup({
					fieldLabel : '是否使用logo',
					id : 'useLogo',
					items : [
					    {
							boxLabel : '使用',
//							inputValue : '1',
							id : 'use',
							name : 'useLogo',
							disabled : ppForm.method == 'load' ? true : false,
							checked : true,
							listeners : {
								check : function(){
									if(this.checked){
										ppForm.useFlag = true;
										Ext.getCmp('logo').enable();
										Ext.getCmp('logo').getEl().up('.x-form-item').setDisplayed(true);
										Ext.getCmp('browseImage').getEl().up('.x-form-item').setDisplayed(true);
									}
								}
							}
						},{
							boxLabel : '不使用',
//							inputValue : '2',
							name : 'useLogo',
				     		disabled : ppForm.method == 'load' ? true : false,
				     		id : 'notUse',
							listeners : {
								check : function(){
									if(this.checked){
										ppForm.useFlag = false;
										Ext.getCmp('logo').setValue('');
										Ext.get('imageBrowse').dom.src = Ext.BLANK_IMAGE_URL;
										Ext.getCmp('logo').disable();
										Ext.getCmp('logo').getEl().up('.x-form-item').setDisplayed(false);
										Ext.getCmp('browseImage').getEl().up('.x-form-item').setDisplayed(false);
									}
								}
							}
						}
					]
				}),
//				{
//					baseCls: 'x-plain',
//					layout: "column",
//					fieldLabel: '是否使用logo',
//					defaultType: 'radio',
////					xtype: 'panel',
////					isFormField: true,
////					name : 'useLogo',
//					id : 'useLogo',
//					items: [{
//						width : 70,
//						id : 'use',
//						name: 'useLogo',
//						boxLabel: '使用',
//						disabled : ppForm.method == 'load' ? true : false,
//						checked : true,
//						listeners : {
//							check : function(){
//								if(this.checked){
//									ppForm.useFlag = true;
//									Ext.getCmp('logo').enable();
//									Ext.getCmp('logo').getEl().up('.x-form-item').setDisplayed(true);
//									Ext.getCmp('browseImage').getEl().up('.x-form-item').setDisplayed(true);
//								}
//							}
//						}
//					},{
//						width : 70,
//						id : 'notuse',
//			       		name: 'useLogo',
//			     		boxLabel: '不使用',
//			     		disabled : ppForm.method == 'load' ? true : false,
//						listeners : {
//							check : function(){
//								if(this.checked){
//									ppForm.useFlag = false;
//									Ext.getCmp('logo').setValue('');
//									Ext.get('imageBrowse').dom.src = Ext.BLANK_IMAGE_URL;
//									Ext.getCmp('logo').disable();
//									Ext.getCmp('logo').getEl().up('.x-form-item').setDisplayed(false);
//									Ext.getCmp('browseImage').getEl().up('.x-form-item').setDisplayed(false);
//								}
//							}
//						}
//					}]
//				},
				{
					id : 'logo',
					name : 'logo',
					inputType : "file",
					fieldLabel : '上传商户logo',
					editable : true,
					xtype : 'textfield',
					disabled : ppForm.method == 'load' ? true : false
				},
		         {
		        	 xtype : 'box',
		        	 id : 'browseImage',
		        	 fieldLabel : "当前logo预览",
		        	 width : 100,
		        	 autoEl : {
		        		 tag : 'img',
		        		 // type : 'image',
		        		 src : Ext.BLANK_IMAGE_URL,
		        		 style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
		        		 complete : 'off',
		        		 id : 'imageBrowse'
		        	 }
		         }
//		         {
//		        	 fieldLabel : '取消logo',
//		        	 xtype: 'button',
//		        	 text : '取消该logo',
//		        	 id : 'deleteLogo',
//		        	 width : 70
//		         }

		  ]
		},
		{
			id : 'operInfo',
			title : '操作记录',
			layout : 'form',
			autoScroll : true,
			defaults : {
				width : 430
			},
			labelWidth :80,
//			closable : true,
//			disabled : true,
			items : [
				{
					xtype : 'textarea',
					fieldLabel : '商户操作信息',
					hideLabel : true,
					height : 320,
					id : 'operLogs',
					name : 'operLogs',
					value : '',
					readOnly : true,
					cls : true,
					disabled : true
				}

		  ]
		}]	
   }],
			
			buttons : [{
				text : '确定',
				id : 'submitButton',
				formBind :  true,
				hidden : ppForm.method == 'load' ? true : false 
			}, {
				text : '返回',
				handler : function() {
					Ext.getBody().unmask();
					ppForm.formPanel.getEl().mask();
					ppForm.close();
				}
			}]
		});
	},
	loadData : function() {
		var ppForm = this;
//		this.areaDs = this.loadComboData('area');
//		this.parentOrganizationDs = this.loadComboData('parentOrganization');
//		this.merchantPropertyDs = this.loadComboData('merchantProperty');
//		this.businessLandDs = this.loadComboData('businessLand');
//		this.businessLocationDs = this.loadComboData('businessLocation');
//		this.regionsDs = this.loadComboData('regions');
//		this.bankCardMerchantDs = this.loadComboData('');
//		this.transactionTypeDs = this.loadComboData('transactionType');
//		this.payTypeDs = this.loadComboData('payType');
		this.yesOrNoDs = new Ext.data.SimpleStore({
			data : [['true', '是'], ['false', '否']],
			fields : ['key', 'value']
		});
		this.provinceDs = this.loadComboData('area');
		this.categoryDs = this.loadComboData('category');
		if (ppForm.pid != '') {
			ppForm.loadModel('load', ppForm.pid);
		} else {
			
		}
	},

	loadComboData : function(combo) {
		var ppForm = this;
		var reader = new Ext.data.JsonReader({
				root : 'root',
				id : "id"
				}, 
				['id','name']
			);
		var store = new Ext.data.Store({
			remoteSort : true,
			proxy : new Ext.data.HttpProxy({
				url : ppForm.url + '?method=loadComboData&param=1&combo=' + combo
			}),
			reader : reader
		});					
		return store;
	},
	
	loadParentInfo : function(method ,id , flag){
		var ppForm = this;
		Ext.Ajax.request({
			url : ppForm.url,
			params : {
				method : method,
				id : id
			},
			timeout : 120000,
			success : function(response) {
				var text = Ext.decode(response.responseText);
				if(!text.success){
					Ext.MessageBox.show({
						title : '提示',
						msg : text.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.INFO
					});
					Ext.getBody().unmask();
					return;
				}
				var data = Ext.decode(text.msg);
				var rc = new Ext.data.Record(data);
				ppForm.formPanel.getForm().loadRecord(rc);
				var rc2 = new Ext.data.Record(data.extend);
				ppForm.formPanel.getForm().loadRecord(rc2);
//		Ext.getCmp('merTab').setActiveTab(2);
//		Ext.getCmp('merTab').setActiveTab(1);
				Ext.getCmp('businessTime1Combo').setValue(data.businessTime1);
				Ext.getCmp('businessTime1Combo').setRawValue(data.businessTime1);
				if(data.businessTime1 == '全天'){
					Ext.getCmp('businessTime2Combo').allowBlank = true;
					Ext.getCmp('businessTime2Combo').clearInvalid();
					Ext.getCmp('businessTime2Combo').setValue('');
					Ext.getCmp('businessTime2Combo').readOnly = true;
				}else{
					Ext.getCmp('businessTime2Combo').setValue(data.businessTime2);
					Ext.getCmp('businessTime2Combo').setRawValue(data.businessTime2);
				}
//				Ext.getCmp('provinceCombo').setValue(data.provinceValue);
//				Ext.getCmp('provinceCombo').setRawValue(data.province);
				Ext.getCmp('cityCombo').setValue(data.cityValue);
				Ext.getCmp('cityCombo').setRawValue(data.city);
				if(data.status == '有效' && ppForm.method == 'update'){
//					Ext.getCmp('provinceCombo').readOnly = true;
					Ext.getCmp('cityCombo').readOnly = true;
				}
				Ext.getCmp('districtCombo').setValue(data.districtValue);
				Ext.getCmp('districtCombo').setRawValue(data.district);
				for(var i=0;i<data.posType.length;i++){
					Ext.getCmp('posType'+data.posType[i]).setValue(true);
				}
				
				Ext.getCmp('merchantCategory').setValue(data.categoryValue);
				Ext.getCmp('merchantCategory').setRawValue(data.category);
				
				Ext.getCmp('signDate').setValue(data.extend.signDate);
				Ext.getCmp('signDate').getValue();
				Ext.getCmp('businessLandCombo').setValue(data.extend.businessLandValue);
				Ext.getCmp('businessLandCombo').setRawValue(data.extend.businessLand);
				Ext.getCmp('businessLocationCombo').setValue(data.extend.businessLocationValue);
				Ext.getCmp('businessLocationCombo').setRawValue(data.extend.businessLocation);
				Ext.getCmp('merchantPropertyCombo').setValue(data.extend.propertyValue);
				Ext.getCmp('merchantPropertyCombo').setRawValue(data.extend.property);
				Ext.getCmp('payTypeCombo').setValue(data.extend.payTypeValue);
				Ext.getCmp('payTypeCombo').setRawValue(data.extend.payType);
				Ext.getCmp('regionsCombo').setValue(data.extend.regionsValue);
				Ext.getCmp('regionsCombo').setRawValue(data.extend.regions);
				Ext.getCmp('transactionTypeCombo').setValue(data.extend.transactionTypeValue);
				Ext.getCmp('transactionTypeCombo').setRawValue(data.extend.transactionType);	
				if(data.discountFlag == true){
					Ext.getCmp('discountFlagCombo').setValue('true');
					Ext.getCmp('discountFlagCombo').setRawValue('是');
				}else if(data.discountFlag == false){
					Ext.getCmp('discountFlagCombo').setValue('false');
					Ext.getCmp('discountFlagCombo').setRawValue('否');	
				}
				if(data.extend.bankCardMerchant == true){
					Ext.getCmp('bankCardMerchantCombo').setValue('true');
					Ext.getCmp('bankCardMerchantCombo').setRawValue('是');
				}else if(data.extend.bankCardMerchant == false){
					Ext.getCmp('bankCardMerchantCombo').setValue('false');
					Ext.getCmp('bankCardMerchantCombo').setRawValue('否');
				}
				if(data.extend.signMerchant == true){
					Ext.getCmp('signMerchantCombo').setValue('true');
					Ext.getCmp('signMerchantCombo').setRawValue('是');
				}else if(data.extend.signMerchant == false){
					Ext.getCmp('signMerchantCombo').setValue('false');
					Ext.getCmp('signMerchantCombo').setRawValue('否');	
				}
				if(data.head != null){
					ppForm.logoPath = 'http://192.168.3.111:8001' + data.head;
				}
				if(ppForm.method == 'load'){
					var logValue = '';
					var merchantLogs = data.merchantLogs;
					for(var i=0;i<merchantLogs.length;i++){
						if(merchantLogs[i].description != null){
							logValue = logValue + merchantLogs[i].createDate + "  :  " + merchantLogs[i].description + '\n';
						}
					}
					Ext.getCmp('operLogs').setValue(logValue);
				}
				//继承父商户的情况，清除管理员手机号等字段
				if(flag){
					Ext.getCmp('num').setValue('');
					Ext.getCmp('businessAddress').setValue('');
					Ext.getCmp('longitude').setValue('');
					Ext.getCmp('latitude').setValue('');
					Ext.getCmp('managerMobile').setValue('');
					Ext.getCmp('cityCombo').setValue('');
					Ext.getCmp('districtCombo').setValue('');
				}
		Ext.getCmp('merTab').setActiveTab(3);		
		Ext.getCmp('merTab').setActiveTab(2);
		Ext.getCmp('merTab').setActiveTab(1);
		Ext.getCmp('merTab').setActiveTab(0);
				Ext.getBody().unmask();
			},
			failure : function(response) {
				Ext.MessageBox.show({
							title : '系统执行失败',
							msg : '无法连接到服务器,或服务器操作异常!',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.INFO
						});
				Ext.getBody().unmask();
			}
		});
	},
	
	loadModel : function(method, id) {
		var ppForm = this;
		var hiddenTarget = function(target) {
			target.setValue(null);
			target.allowBlank = true;
			target.clearInvalid();
			target.getEl().up('.x-form-item').setDisplayed(false);
		};
		
		var showTarget = function(target) {
			target.enable();
			target.show();
			target.getEl().up('.x-form-item').setDisplayed(true);
			if(target.getValue() == null || target.getValue() == ''){
				target.markInvalid();
			}
		};
		
		Ext.getBody().mask("系统正在执行,请等待。", 'x-mask-loading');
		ppForm.loadParentInfo(method,id,false);
	},

	resultMessageShow : function(data) {
		var ppForm = this;
		if (data.success) {
			ppForm.showMessageInfo(data.msg);
		} else {
			ppForm.showMessageInfo(data.msg);
		}
	},
	showMessageInfo : function(message) {
		Ext.MessageBox.show({
			title : '提示',
			msg : message,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.INFO
		});
	},
	addListener : function(){
		var ppForm = this;
		var data ;
		var hiddenTarget = function(target){
			target.setValue(null);
			target.allowBlank = true;
			target.clearInvalid();
			target.getEl().up('.x-form-item').setDisplayed(false);
		}; 
		var showTarget = function(target) {
			target.allowBlank = false;
			target.getEl().up('.x-form-item').setDisplayed(true);
			target.validate();
		};
		var showTargetNoValidate = function(target) {
//			target.allowBlank = false;
			target.getEl().up('.x-form-item').setDisplayed(true);
//			target.validate();
		};
		var validateTarget = function(target){
			target.allowBlank = false;
			target.validate();
		};
		var validateTargetAllowBlank = function(target){
			target.allowBlank = true;
			target.validate();
		};
		var showMessageInfo = function(message) {
			Ext.MessageBox.show({
				title : '提示',
				msg : message,
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
		};
		
		// 上传图片类型,在前台的拦截
		var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
		
		Ext.getCmp('submitButton').on('click', function() {
			var filePath = Ext.get('logo').dom.value;
			//要验证图片格式，但无法清空inputtype为file的文本框值（IE）
			if (ppForm.useFlag && filePath != '' && !img_reg.test(filePath)) {
				alert('商户logo图片格式有误');
				return;
			}
			var posNo = 1;
			for(var i=0;i<10;i++){
				var obj = Ext.getCmp('posType'+i);
				if(obj){
					if(obj.checked){
						ppForm.posType[i] = posNo;
					}else{
						ppForm.posType[i] = 0;
					}
				}else{
					break;
				}
				posNo = posNo*2;
			}
			ppForm.formPanel.getEl().mask('处理中....');
			ppForm.formPanel.form.doAction('submit', {
						url : ppForm.url,
						params : {
							method : ppForm.method,
							id : ppForm.pid,
							posType : ppForm.posType,
							useFlag : ppForm.useFlag
						},
						method : 'post',
						timeout : 120000,
						success : function(form, action) {
							var data = Ext.decode(action.response.responseText);
							if (data.success) {
								ppForm.close();
							}
							ppForm.resultMessageShow(data);
							ppForm.formPanel.getEl().unmask();

						},
						failure : function(form, action) {
							var data = Ext.decode(action.response.responseText);
							ppForm.resultMessageShow(data);
							ppForm.formPanel.getEl().unmask();
						}
//						failure : function(response,options) {
//							var reqst=response.status;        // 根据返回的状态码值判断是否超时
//							if(reqst=='-1'){                  // 超时的状态码为 -1
//								Ext.Msg.getDialog().setWidth(500);
//								Ext.MessageBox.alert("提示","请求超时,可能存在网络异常,检查后请重试...");
//							}else{
//								Ext.Msg.alert('提示','操作失败！');
//							}
//							ppForm.formPanel.getEl().unmask();
//
//						}
					});
		});
		
		this.on('show',function(){
			if(ppForm.method == 'create'){
				Ext.getCmp('code').getEl().up('.x-form-item').setDisplayed(false);
			}
			if(ppForm.method != 'load'){
				Ext.getCmp('merTab').remove(Ext.getCmp('operInfo'));
			}
		});
		
		Ext.getCmp('signMerchantCombo').on('select', function() {
			if(this.getValue() == 'true'){
				validateTarget(Ext.getCmp('registNo'));
				validateTarget(Ext.getCmp('taxNo'));
//				validateTarget(Ext.getCmp('businessLicense'));
//				validateTarget(Ext.getCmp('taxCertificate'));
//				validateTarget(Ext.getCmp('organizationCard'));
				validateTarget(Ext.getCmp('registAddress'));
				validateTarget(Ext.getCmp('legal'));
				validateTarget(Ext.getCmp('merchantPropertyCombo'));
//				validateTarget(Ext.getCmp('registCapital'));
				validateTarget(Ext.getCmp('businessLocationCombo'));
				validateTarget(Ext.getCmp('regionsCombo'));
//				validateTarget(Ext.getCmp('rangeMain'));
//				validateTarget(Ext.getCmp('rangeSideline'));
			}else if(this.getValue() == 'false'){
				validateTargetAllowBlank(Ext.getCmp('registNo'));
				validateTargetAllowBlank(Ext.getCmp('taxNo'));
//				validateTargetAllowBlank(Ext.getCmp('businessLicense'));
//				validateTargetAllowBlank(Ext.getCmp('taxCertificate'));
//				validateTargetAllowBlank(Ext.getCmp('organizationCard'));
				validateTargetAllowBlank(Ext.getCmp('registAddress'));
				validateTargetAllowBlank(Ext.getCmp('legal'));
				validateTargetAllowBlank(Ext.getCmp('merchantPropertyCombo'));
//				validateTargetAllowBlank(Ext.getCmp('registCapital'));
				validateTargetAllowBlank(Ext.getCmp('businessLocationCombo'));
				validateTargetAllowBlank(Ext.getCmp('regionsCombo'));
//				validateTargetAllowBlank(Ext.getCmp('rangeMain'));
//				validateTargetAllowBlank(Ext.getCmp('rangeSideline'));
			}
		});
		
		Ext.getCmp('bankCardMerchantCombo').on('select', function() {
			if(this.getValue() == 'true'){
//				validateTarget(Ext.getCmp('merchantTypCombo'));
				validateTarget(Ext.getCmp('transactionTypeCombo'));
				validateTarget(Ext.getCmp('billAddress'));
				validateTarget(Ext.getCmp('billZipcode'));
				validateTarget(Ext.getCmp('billPerson'));
				validateTarget(Ext.getCmp('signDate'));
			}else if(this.getValue() == 'false'){
//				validateTargetAllowBlank(Ext.getCmp('merchantTypCombo'));
				validateTargetAllowBlank(Ext.getCmp('transactionTypeCombo'));
				validateTargetAllowBlank(Ext.getCmp('billAddress'));
				validateTargetAllowBlank(Ext.getCmp('billZipcode'));
				validateTargetAllowBlank(Ext.getCmp('billPerson'));
				validateTargetAllowBlank(Ext.getCmp('signDate'));
			}
		});
		
		Ext.getCmp('businessTime1Combo').on('select', function() {
			if(this.getValue() == '全天'){
				Ext.getCmp('businessTime2Combo').setValue('');
				Ext.getCmp('businessTime2Combo').allowBlank = true;
				Ext.getCmp('businessTime2Combo').clearInvalid();
				Ext.getCmp('businessTime2Combo').readOnly = true;
			}else {
				Ext.getCmp('businessTime2Combo').allowBlank = false;
				Ext.getCmp('businessTime2Combo').readOnly = false;
				Ext.getCmp('businessTime2Combo').validate();
			}
		});
		
		Ext.getCmp('parentCombo').on('select',function(){
			if(this.getValue() == ''){
				return;
			}
			Ext.getBody().mask("正在加载数据,请等待。", 'x-mask-loading');
			ppForm.loadParentInfo('load',this.getValue(),true);
		});
		
//		Ext.getCmp('parentCombo').on('change',function(){
//			if(this.getValue() == ''){
//				return;
//			}
//			Ext.getBody().mask("正在加载数据,请等待。", 'x-mask-loading');
//			ppForm.loadParentInfo('load',this.getValue(),true);
//		});
		
		//省市区下拉菜单联动
//		Ext.getCmp('provinceCombo').on('select',function(){
//			Ext.getCmp('districtCombo').clearValue();
//			Ext.getCmp('districtCombo').validate();
//			Ext.getCmp('cityCombo').clearValue();
//			Ext.getCmp('cityCombo').readOnly = false;
//			Ext.getCmp('cityCombo').validate();
////	        ppForm.cityDs.proxy = new Ext.data.HttpProxy({url: contextPath + '/mi.do?method=loadComboData&combo=area&param=' + this.value}); 
//			ppForm.cityDs.proxy.setUrl(contextPath + '/mi.do?method=loadComboData&combo=area&param=' + this.value,true);
//	        ppForm.cityDs.load();
//			Ext.getCmp('longitude').setValue('');
//			Ext.getCmp('latitude').setValue('');
////			ppForm.areaDs2.baseParams.combo = this.value;
////			ppForm.areaDs2.load({
////				params : {
////					areaId1 : this.value
////				} 
////			});
//		});
		
		Ext.getCmp('cityCombo').on('select',function(){
			Ext.getCmp('districtCombo').clearValue();
			Ext.getCmp('districtCombo').readOnly = false;
			Ext.getCmp('districtCombo').validate();
//	        ppForm.districtDs.proxy = new Ext.data.HttpProxy({url: contextPath + '/mi.do?method=loadComboData&combo=area&param=' + this.value});
			ppForm.districtDs.proxy.setUrl(contextPath + '/mi.do?method=loadComboData&combo=area&param=' + this.value,true);
	        ppForm.districtDs.load();
			Ext.getCmp('longitude').setValue('');
			Ext.getCmp('latitude').setValue('');
		});
		
		Ext.getCmp('districtCombo').on('select',function(){
			Ext.getCmp('longitude').setValue('');
			Ext.getCmp('latitude').setValue('');
		});
		
//		Ext.getCmp('cityCombo').on('focus',function(){
//			var cityId = Ext.getCmp('provinceCombo').getValue();
//			if( cityId == ''){
//				this.readOnly = true;
//			}else{
//				this.readOnly = false;
//				ppForm.cityDs.proxy.setUrl(contextPath + '/mi.do?method=loadComboData&combo=area&param=' + cityId,true);
//				ppForm.cityDs.load();
//			}
//		});
		
		Ext.getCmp('districtCombo').on('focus',function(){
			var districtId = Ext.getCmp('cityCombo').getValue();
			if( districtId == ''){
				this.readOnly = true;
			}else{
				this.readOnly = false;
				ppForm.districtDs.proxy.setUrl(contextPath + '/mi.do?method=loadComboData&combo=area&param=' + districtId,true);
		        ppForm.districtDs.load();
			}
		});
		
//		Ext.getCmp('cityCombo').on('change',function(){
//
//		});
		
//		Ext.getCmp('businessAddress').on('change',function(){
//			Ext.getCmp('longitude').setValue('');
//			Ext.getCmp('latitude').setValue('');
//		});
		Ext.getCmp('keyWords').on('change',function(){
			Ext.getCmp('longitude').setValue('');
			Ext.getCmp('latitude').setValue('');
		});
		
//		this.on('render' , function(f) {
			Ext.getCmp('logo').on('render',function() {
				// 通過change事件
				Ext.get('logo').on('change',function(field,newValue,oldValue) {
					var url = 'file://'+ Ext.get('logo').dom.value;
					if (img_reg.test(url)) {
						if (Ext.isIE) {
							var image = Ext.get('imageBrowse').dom;
							image.src = Ext.BLANK_IMAGE_URL;// 覆盖原来的图片
							image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
						}// 支持FF
						else {
							Ext.get('imageBrowse').dom.src = Ext.get('logo').dom.files.item(0).getAsDataURL();
						}
					}else{
						alert('请选择正确的图片文件');
					}
				}, this);
			}, this);
//		});

		Ext.getCmp('browseImage').on('render',function(){
			if(ppForm.logoPath != ''){
				Ext.get('imageBrowse').dom.src = ppForm.logoPath;
			}
		});
		Ext.getCmp('otherInfo').on('activate',function(){
			if(ppForm.logoPath != ''){
				Ext.get('imageBrowse').dom.src = ppForm.logoPath;
			}
			if(ppForm.method != 'create' && ppForm.logoPath ==''){
				Ext.getCmp('useLogo').onSetValue('notUse',true);
			}
		});	
		//参数：经度，纬度，省市区信息，营业地址，商户名，用作查询定位及气泡提示用
		var getMap = function(longitude,latitude,city,address,merchantName){
			var mapWin = new Ext.Window({
				layout : 'fit',
				modal : true,
				title : '地图定位',
				closeAction : 'hide',
				width : 600,
				height : 400,
				x : 40,
				y : 60,
				items : {
					xtype : 'bmappanel',
					id : 'bmap',
					lng : longitude,
					lat : latitude,
					lastLng : '',
					lastLat : '',
//					zoomLevel : 14,
					gmapType: 'map',
					address : address,
					city : city,
					tip : merchantName
				},
				listeners : {
					beforehide : function(){
						Ext.getCmp('longitude').setValue(Ext.getCmp('bmap').lastLng);
						Ext.getCmp('latitude').setValue(Ext.getCmp('bmap').lastLat);
					}
				}
			});
			mapWin.show();
		};
		
		Ext.getCmp('longitude').on('focus',function(){
			var latValue = Ext.getCmp('latitude').getValue();
			var cityValue = 
//				Ext.getCmp('provinceCombo').getRawValue()+
				Ext.getCmp('cityCombo').getRawValue()+Ext.getCmp('districtCombo').getRawValue();
			var addressValue;
			if(this.value == '' || latValue == ''){
				addressValue = Ext.getCmp('keyWords').getValue();
			}
			var merchantName = Ext.getCmp('name').getValue();
			getMap(this.value,latValue,cityValue,addressValue,merchantName);
		});
		Ext.getCmp('latitude').on('focus',function(){
			var lngValue = Ext.getCmp('longitude').getValue();
			var cityValue = 
//				Ext.getCmp('provinceCombo').getRawValue()+
				Ext.getCmp('cityCombo').getRawValue()+Ext.getCmp('districtCombo').getRawValue();
			var addressValue;
			if(this.value == '' || lngValue == ''){
				addressValue = Ext.getCmp('keyWords').getValue();
			}
			var merchantName = Ext.getCmp('name').getValue();
			getMap(lngValue,this.value,cityValue,addressValue,merchantName);
		});
		
	}
});

Ext.reg('merchantForm', Ext.ux.MerchantForm);