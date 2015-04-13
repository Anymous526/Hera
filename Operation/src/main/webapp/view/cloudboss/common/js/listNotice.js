Ext.onReady(function() {
	Ext.MessageBox.minWidth = 300;
	Ext.MessageBox.maxWidth = 300;
	/* 全局变量 */
	var id = '';
	var title = '';
	var type = '';
	var content = '';
	
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
			header: "标题",
			dataIndex: "title",
			width: 150,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "公告类型",
			dataIndex: "type",
			width: 80,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "公告内容",
			dataIndex: "content",
			width: 500,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "创建时间",
			dataIndex: "time",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);

	colM.defaultSortable = true;

	//创建工具栏
	var buttonBar = new Ext.Toolbar({
				id: 'buttonBar',
				width: Ext.lib.Dom.getViewportWidth(),
				items: [
				{
					id: 'addAnnouncement',
					text: '添加公告',
					iconCls: 'add',
					disabled: false
				},
				'-',
				{
					id : 'removeAnnouncement',
					text : '删除公告',
					tooltip : '删除公告',
					tooltipType : 'title',
					iconCls : 'remove',
					disabled : true
				},
				'-',
				{
					id: 'refreshButton',
					text: '刷新',
					iconCls: 'refresh'
				}]
	});

	var grid = new Ext.ux.simpleGrid({
			title:'公告信息列表',
			url: contextPath + '/a.do?method=select',
			cm: colM,
			sm: selectModel,
			pageSize: 20,
			height: Ext.lib.Dom.getViewHeight() - (Ext.isIE6 ? 30: Ext.isIE7 ? 30: Ext.isIE8 ? 28: 28),
			nameMapping: [
				{name: 'id', mapping: 'id'},
				{name: 'title', mapping: 'name'},
				{name: 'content', mapping: 'content'},
				{name: 'type', mapping: 'type.desc'},
				{name: 'time', mapping: 'createDate'}
			]
		});
     	
	var loadData = function() {
		grid.store.load({
			params: {
				start: 0,
				limit: grid.pageSizeCombo.value
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
							Ext.getCmp('removeAnnouncement').disable();
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

	function addAnnouncement() {
		var win = new Ext.Window({
			height: 320,
			width: 650,
			resizable: false,
			title: "添加公告",
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
				width: 525
			},
			items: [
				{
					xtype: 'combo',
					id: 'type',
					name: 'type',
					mode: 'local',
					fieldLabel: '公告类型',
					emptyText: '请选择...',
					allowBlank: false,
					blankText: '必须选择类型',
					store: new Ext.data.ArrayStore({
						fields: ['typeId', 'typeName'],
						data: [
							['1','用户公告'], ['2','商户公告'], ['3','通用公告']
						]
					}),
					valueField: 'typeId',
					displayField: 'typeName',
					editable: false,
					triggerAction: 'all',
					listeners: {
						change: function(combo, newValue, oldValue){
							type = newValue;
						}
					}
				},
				{
					xtype: 'textfield',
					id: 'title',
					name: 'title',
					fieldLabel: '公告标题',
					emptyText: '请填写...',
					allowBlank: false,
					blankText: '必填字段',
					maxLength: 64,
					maxLengthText: '公告标题超过最大长度',
					listeners: {
						change: function(textfield, newValue, oldValue) {
							title = newValue;
						}
					}
				},
				{
					xtype: 'htmleditor',
					id: 'content',
					name: 'content',
					fieldLabel: '公告内容',
					emptyText: '请填写...',
					allowBlank: false,
					blankText: '必填字段'
				}
			],
			buttonAlign: 'center',
			buttons: [
				{
					text: '确定',
					formBind: true,
					handler: function() {
//						var re = /(<p>)(&nbsp;)+(<\/p>)/g;
						var re = new RegExp("\\"+String.fromCharCode('8203'),"g");
						content = Ext.getDom('content').value;
//						content = content.replace(re,"");
						content = content.replace(re, "");
						
						if( content.length > 2000 ){
							Ext.MessageBox.alert('错误', '公告内容（包含格式源码）超出最大长度2000');
							return;
						}
						Ext.MessageBox.confirm('添加确认', '<p>确定要添加公告吗？</p><br /><p>' + title + '</p>', function(btn){
							if( btn == 'yes' ){
								Ext.getBody().mask("正在执行，请等待。", 'x-mask-loading');
								Ext.Ajax.request({
									url: contextPath + '/a.do?method=append',
									params: { title: title, content: content, type: type },
									success: function(response){
										var data = Ext.decode(response.responseText);
										if( data.success ){
											Ext.MessageBox.alert('成功', data.msg);
											Ext.getBody().unmask();
											win.close();
										}else{
											Ext.MessageBox.alert('失败', data.msg);
											Ext.getBody().unmask();
										}
										loadData();
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
	
	var removeAnnouncement = function() {
		Ext.MessageBox.confirm('删除确认', '<p>确定要删除公告吗？</p><br /><p>' + title + '</p>', function(btn, reason) {
			if( btn == 'yes' ){
				Ext.Ajax.request({
					url: contextPath + '/a.do?method=delete',
					method: 'post',
					params: { id: id },
					success: function(response){
						var data = Ext.decode(response.responseText);
						Ext.MessageBox.alert('成功', data.msg);
						Ext.getBody().unmask();
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

	var addListener = function() {
		grid.store.on('beforeload', function() {
			Ext.getCmp('removeAnnouncement').disable();
		});
		
		selectModel.on('rowselect', function(self, rowIndex, record) {
			id = record.get('id');
			title = record.get('title');
			Ext.getCmp('removeAnnouncement').enable();
		});
		
		Ext.getCmp('addAnnouncement').on('click', function() {
			addAnnouncement();
		});
		
		Ext.getCmp('removeAnnouncement').on('click', function() {
			removeAnnouncement();
		});
		
		selectModel.on('rowdeselect', function(self, rowIndex, record){
			Ext.getCmp('removeAnnouncement').disable();
		});
		
		Ext.getCmp('refreshButton').on('click', function() {
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
