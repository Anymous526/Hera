	
var appSM = new Ext.grid.CheckboxSelectionModel({
	checkOnly : true
});
	
var getAppGrid = function(id){
//	var isFirst = true;
	//当前选中的应用行号
//	var appIndex = '';
//	var searchBar = new Ext.Toolbar({
//		items : [{
//			text : '重新加载',
//			iconCls : 'refresh',
//			handler : function() {
//				loadApps();
//			}
//		}]
//	});
    
    var appGrid = new Ext.grid.GridPanel({
//		renderTo : "tableDiv",
        store: new Ext.data.JsonStore({
//    		remoteSort : true,
    		url: contextPath + '/city.do?method=listAppsByCity',
    		totalProperty : 'totalProperty',
    		root : 'root',
    		id : "id",
    		fields : ['id','name','code','type','comments','location','flag']
        }),
        cm: new Ext.grid.ColumnModel({
            defaults: {
                sortable: false
            },
            columns: [
                new Ext.grid.RowNumberer(),
                appSM,
                {dataIndex: 'id',hidden:true},
                {header: "应用名称", dataIndex: 'name',width: 100,sortable : true},
                {header: "应用编号", dataIndex: 'code',width: 40,sortable : true},
                {header: "应用类型", dataIndex: 'type',width: 40,sortable : true},
                {header: "应用描述", dataIndex: 'comments',width: 150,sortable : true},
                {dataIndex: 'location',hidden : true},
                {dataIndex: 'flag',hidden: true}
        	]
        }),
		sm : appSM,
//		tbar : searchBar,
        columnLines: true,
        viewConfig : {
        	forceFit : true
        }
//        title: '有效POS应用'
    });
    
	var loadApps = function() {
		appGrid.store.load();
//		appSM.clearSelections();
	};
	
	var addListener = function(){
		appGrid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'cityId' : id
			});
		});
		appGrid.store.on('load',function(){
			if(this.getTotalCount() == 0){
				Ext.Msg.alert('提示','系统尚未添加有效POS应用');
			}
			var records = new Array();
			this.each(function(record){
				if(record.get('location') == 0){
					record.set('type','会生活');
				}else if(record.get('location') == 1){
					record.set('type','收单');
				}
//				else{
//					record.set('type','其他应用');
//				}
			
				if(record.get('flag') == true){
					records.push(record);
				};
			});
			//解决自动勾选后又被定时任务取消掉的情况
			var task = new Ext.util.DelayedTask(function(){
				appSM.selectRecords(records);
			});
			task.delay(1);

		});
		
//		//不知道为什么第一次渲染窗口grid加载数据时不触发，而之后每次渲染窗口grid加载数据时都要触发
//		appSM.on('rowdeselect', function(self, rowIndex, record){
//			if(record.get('flag') == true){
//				appSM.selectRecords([record],true);
//				if(!isFirst){
//					Ext.Msg.alert('提示','不能取消原有选择，请咨询系统管理员');
//				}
//				
//			}
//			isFirst = false;
//		});
		
		appSM.on('rowselect', function(self, rowIndex, record){
			if(record.get('flag') == true){
				var row = appGrid.getView().getRow(rowIndex);//得到这行的div  
				Ext.get(row).mask();//给这行增加遮罩  
			}
		});
		appSM.on('rowdeselect', function(self, rowIndex, record){
			if(record.get('flag') == true){
				self.selectRow(rowIndex,true);
//				Ext.Msg.alert('提示','不能取消原有选择，请咨询系统管理员');

			}

		});

	};
	addListener();
	loadApps();
	Ext.QuickTips.init();
	
	return appGrid;
};