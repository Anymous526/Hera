Ext.onReady(function() {
	var colM = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			header: "用户名称",
			dataIndex: "name",
			width: 120,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},
		{
			header: "用户手机号",
			dataIndex: "mobile",
			width: 150,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "反馈内容",
			dataIndex: "content",
			width: 400,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		},{
			header: "反馈时间",
			dataIndex: "time",
			width: 150,
			editor: new Ext.form.TextField({
						readOnly: true
					}),
			sortable: false
		}
	]);

	colM.defaultSortable = true;

	var grid = new Ext.ux.simpleGrid({
		title:'公告信息列表',
		url: contextPath + '/fb.do?method=select',
		cm: colM,
		pageSize: 20,
		height: Ext.lib.Dom.getViewHeight(),
		nameMapping: [
			{name: 'name', mapping: 'user.name', defaultValue: '匿名'},
			{name: 'mobile', mapping: 'user.mobile', defaultValue: ''},
			{name: 'content', mapping: 'feedContent'},
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
	}
	
	removeHearderBox(grid);
	grid.render();
	grid.syncSize();
	loadData();
	Ext.QuickTips.init();
});
