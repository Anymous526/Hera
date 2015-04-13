Ext.namespace('Ext.ux');
Ext.ux.CouponForm = Ext.extend(Ext.Window, {

	height : 390,
	width :470,
	resizable : true,
	title : "优惠券活动详细信息",
	modal : true,
	constrain : false,
	constrainHeader : false,
	closeAction : 'close',
	stateful : true,
	closable : true,
	border : true,
//	regFlag : '',
	layout : 'fit',
	method : '',
	pid : '',
	url : '',
	initComponent : function() {
		this.loadData();
		this.loadComponent();
		this.items = [this.formPanel];
		Ext.QuickTips.init();
//		this.addListener();
		Ext.ux.CouponForm.superclass.initComponent.call(this);
	},

	loadComponent : function() {
		var ppForm = this;
		Ext.form.Field.prototype.msgTarget = 'side';
		this.formPanel = new Ext.form.FormPanel({
			id : 'formPanel',
			labelWidth : 125,
			border : false,
			bodyStyle : 'padding:5px 15px 5px;background-image:url('
					+ contextPath
					+ '/images/ext_images/background-lightblue.JPG);background-position:center;background-repeat:repeat;',
			monitorValid : true,
			width : 450,
			layout : 'form',
			autoScroll : true,
			fileUpload: true,  
			defaults : {
				width : 275
			},
			
			items : [
			{
				xtype : 'textfield',
				id : 'title',
				name : 'title',
				fieldLabel : '优惠券活动名称',
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'textfield',
				id : 'couponType',
				name : 'couponType',
				fieldLabel : '优惠券类型',
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'textfield',
				id : 'couponPloyStatus',
				name : 'couponPloyStatus',
				fieldLabel : '优惠券活动状态',
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'textfield',
				id : 'merchantName',
				name : 'merchantName',
				fieldLabel : '所属商户',
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'textfield',
				id : 'merchantCode',
				name : 'merchantCode',
				fieldLabel : '商户编号',
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'datefield',
				id : 'createDate',
				name : 'createDate',
				fieldLabel : '优惠券活动创建日期',
				format : 'Ymd',
//				value : '',
//				editable : false,
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'datefield',
				id : 'validStartDate',
				name : 'validStartDate',
				fieldLabel : '优惠券活动开始时间',
				format : 'Ymd',
//				value : '',
//				editable : false,
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'datefield',
				id : 'validEndDate',
				name : 'validEndDate',
				fieldLabel : '优惠券活动结束时间',
				format : 'Ymd',
//				value : '',
//				editable : false,
				readOnly : true,
				cls : 'item-field-readonly'
			},{
				xtype : 'textarea',
				id : 'content',
				name : 'content',
				fieldLabel : '优惠券活动内容',
				readOnly : true,
				cls : 'item-field-readonly'
			}
	        
			],

			buttons : [
			    {
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
		if (ppForm.pid != '') {
			ppForm.loadModel('load', ppForm.pid);
		} 
	},
	
	loadModel : function(method, id) {
		var ppForm = this;
		
		Ext.getBody().mask("系统正在执行,请等待。", 'x-mask-loading');
		Ext.Ajax.request({
			url : ppForm.url,
			params : {
				method : method,
				id : id
			},
			timeout : 10000,
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
				Ext.getCmp('merchantName').setValue(data.merchant.name);
				Ext.getCmp('merchantCode').setValue(data.merchant.code);
				Ext.getCmp('couponType').setValue(data.couponType.desc);
				Ext.getCmp('couponPloyStatus').setValue(data.couponPloyStatus.desc);
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

	}

});

Ext.reg('couponForm', Ext.ux.CouponForm);