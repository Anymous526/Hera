	
var merchantSM = new Ext.grid.CheckboxSelectionModel({
	checkOnly : true
});
	
var getMerchantGrid = function(appId,cities){
    
    var merchantGrid = new Ext.grid.GridPanel({
        store: new Ext.data.JsonStore({
//    		remoteSort : true,
    		url: contextPath + '/posApp.do?method=getByCities',
    		totalProperty : 'totalProperty',
    		root : 'root',
    		id : "id",
    		fields : ['id','name','shortName','code','status','createDate','cityName','flag','lockFlag']
        }),
        cm: new Ext.grid.ColumnModel({
            defaults: {
                sortable: false
            },
            columns: [
                new Ext.grid.RowNumberer(),
                merchantSM,
                {dataIndex: 'id',hidden:true},
                {header: "商户中文名称", dataIndex: 'name',width: 80,sortable : true},
                {header: "商户中文名称简写", dataIndex: 'shortName',width: 60,sortable : true},
                {header: "商户编号", dataIndex: 'code',width: 60,sortable : true},
                {header: "商户状态", dataIndex: 'status',width: 30,sortable : true},
                {header: "创建时间", dataIndex: 'createDate',width: 50,sortable : true},
                {header: "所在城市", dataIndex: 'cityName',width: 40,sortable : true},
                {dataIndex: 'flag',hidden: true},
                {dataIndex: 'lockFlag',hidden: true}
        	]
        }),
		sm : merchantSM,
//		tbar : searchBar,
        columnLines: true,
        viewConfig : {
        	forceFit : true
        }
    });
    
	var loadMerchants = function() {
		merchantGrid.store.load();
	};
	
	var addListener = function(){
		merchantGrid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'appId' : appId,
				'cities' : cities
			});
		});
		merchantGrid.store.on('load',function(){
			if(this.getTotalCount() == 0){
				Ext.Msg.alert('提示','当前应用的适用城市尚未添加有效商户');
			}
			var records = new Array();
			this.each(function(record){
				if(record.get('flag') == true){
					records.push(record);
				};
				if(record.get('lockFlag') == true){
					var rowIndex = merchantGrid.store.indexOf(record);
					var row = merchantGrid.getView().getRow(rowIndex);//得到这行的div  
					Ext.get(row).mask();//给这行增加遮罩  
				}
			});
			
			//解决自动勾选后又被定时任务取消掉的情况
			var task = new Ext.util.DelayedTask(function(){
				merchantSM.selectRecords(records);
			});
			task.delay(1);

		});
		
		merchantSM.on('rowselect', function(self, rowIndex, record){
			if(record.get('flag') == true){
				var row = merchantGrid.getView().getRow(rowIndex);//得到这行的div  
				Ext.get(row).mask();//给这行增加遮罩  
			}
		});
		merchantSM.on('rowdeselect', function(self, rowIndex, record){
			if(record.get('flag') == true){
				self.selectRow(rowIndex,true);
//				Ext.Msg.alert('提示','不能取消原有选择，请咨询系统管理员');

			}

		});
		
		//处理不能覆盖同类别应用的商户数据
		merchantSM.on('beforerowselect', function(merchantSM,rowIndex,keep,record ){
			if(record.get('lockFlag') == true){
				return false;
			}
		});
		merchantGrid.on('sortchange', function(grid,sortInfo){
			grid.store.each(function(record){
				if(record.get('lockFlag') == true){
					var rowIndex = merchantGrid.store.indexOf(record);
					var row = merchantGrid.getView().getRow(rowIndex);//得到这行的div  
					Ext.get(row).mask();//给这行增加遮罩  
				}
			});
		});

	};
	addListener();
	loadMerchants();
	Ext.QuickTips.init();
	
	return merchantGrid;
};