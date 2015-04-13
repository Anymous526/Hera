	
var citySM = new Ext.grid.CheckboxSelectionModel({
	checkOnly : true
});
	
var getCityGrid = function(id){
    
    var cityGrid = new Ext.grid.GridPanel({
        store: new Ext.data.JsonStore({
//    		remoteSort : true,
    		url: contextPath + '/city.do?method=listCitiesByUser',
    		totalProperty : 'totalProperty',
    		root : 'root',
    		id : "id",
    		fields : ['id','cityName','cityCode','flag']
        }),
        cm: new Ext.grid.ColumnModel({
            defaults: {
                sortable: false
            },
            columns: [
                new Ext.grid.RowNumberer(),
                citySM,
                {dataIndex: 'id',hidden:true},
                {header: "城市名称", dataIndex: 'cityName',width: 100,sortable : true},
                {header: "城市编号", dataIndex: 'cityCode',width: 100,sortable : true},
                {dataIndex: 'flag',hidden: true}
        	]
        }),
		sm : citySM,
        columnLines: true,
        viewConfig : {
        	forceFit : true
        }
    });
    
	var loadCities = function() {
		cityGrid.store.load();
	};
	
	var addListener = function(){
		cityGrid.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'userId' : id
			});
		});
		cityGrid.store.on('load',function(){
			if(this.getTotalCount() == 0){
				Ext.Msg.alert('提示','当前登录用户尚未配置城市');
			}
			var records = new Array();
			this.each(function(record){
				if(record.get('flag') == true){
					records.push(record);
				};
			});
			//解决自动勾选后又被定时任务取消掉的情况
			var task = new Ext.util.DelayedTask(function(){
				citySM.selectRecords(records);
			});
			task.delay(1);

		});
		
	};
	addListener();
	loadCities();
	Ext.QuickTips.init();
	return cityGrid;
};