Ext.ns("Ext.ux.renderer");

Ext.ux.renderer.ComboRenderer = function(options) {
    var value = options.value;
    var combo = options.combo;
    var returnValue = value;
    var valueField = combo.valueField;
    var idx = combo.store.findBy(function(record) {
        if(record.get(valueField) == value) {
            returnValue = record.get(combo.displayField);
            return true;
        }
    });
    // ??
    if(idx < 0 && value == 0) {
        returnValue = '';
    }
    return returnValue;
};

Ext.ux.renderer.Combo = function(combo) {
    return function(value, meta, record) {
        return Ext.ux.renderer.ComboRenderer({value: value, meta: meta, record: record, combo: combo});
    };
};

var appSM = new Ext.grid.CheckboxSelectionModel();
	
var getAppGrid = function(merchantCode,cityName){
	
	//让appSM的handleMouseDown失效
	appSM.handleMouseDown = Ext.emptyFn;
	//当前选中的应用行号
	var appLocation = '';
    
	var appData = '';
	Ext.Ajax.request({
		url : contextPath + '/posApp.do?method=getAppsByMerchant',
		async :  false,//同步请求数据
		params : {
			merchantCode : merchantCode
		},
		success : function(response,options) {
			appData = Ext.decode(response.responseText);
			
		}
	});
	if(appData == '' || !appData.success){
		return null;
	}
	
    var appCombo = new Ext.form.ComboBox({   
    	allowBlank : false,
    	blankText : '请选择',
		id: 'app0',
        forceSelection: true,
        selectOnFocus: true,
        typeAhead: true,
        triggerAction : "all",
        editable : false,   
        lazyRender: true,
//      valueField : "id", 
        valueField : "name", 
        displayField : "name",   
        store : new Ext.data.JsonStore({
    		remoteSort : true,
    		url: contextPath + '/posApp.do?method=getAppsByLocation',
    		totalProperty : 'totalProperty',
    		root : 'root',
    		id : "id",
//    		fields : ['id','name','code','location','comments']
    		fields : ['id','name','code','comments']
        }),
	    mode : "remote",
	    listeners : {
	    	select : function(combo ,record ,index){
				var appRow = appGrid.getStore().getAt(appLocation);
	    		appRow.set('id',record.get('id'));
	    		appRow.set('name',record.get('name'));
	    		appRow.set('code',record.get('code'));
				appRow.set('comments',record.get('comments'));
	    	},
			beforequery: function(qe){
				delete qe.combo.lastQuery;
			}
	    }
    });
	
    var appGrid = new Ext.grid.EditorGridPanel({
        store: new Ext.data.ArrayStore({
//    		remoteSort : true,
//    		url: contextPath + '/posApp.do?method=getAppsByMerchant&merchantCode='+merchantCode,
//    		totalProperty : 'totalProperty',
//    		root : 'root',
//    		id : "id",
        	data : [['','点击选择','会生活','',''],['','点击选择','收单','','']],
//        	        ['','点击选择','其他应用','']],
    		fields : ['id','name','type','code','comments']
        }),
        cm: new Ext.grid.ColumnModel({
            defaults: {
                sortable: false
            },
            columns: [
                new Ext.grid.RowNumberer(),
                appSM,
                {dataIndex: 'id',hidden:true},
                {
                	header: "应用名称", 
                	dataIndex: 'name',
                	width: 140, 
                	renderer:Ext.ux.renderer.Combo(appCombo), 
                	editor : appCombo
                },
                {header: "应用类别", dataIndex: 'type',width: 100},
//                {header: "应用位置", dataIndex: 'location',width: 60},
                {header: "应用编号", dataIndex: 'code',width: 40,sortable : true},
                {header: "应用描述", dataIndex: 'comments',width: 150,sortable : true},
                {dataIndex: 'flag',hidden: true}
        	]
        }),
		sm : appSM,
        columnLines: true,
        viewConfig : {
        	forceFit : true
        },
      //设置点击几次才可编辑  
        clicksToEdit: 1 ,
//        title: '有效POS应用'
        bbar: [{
        	text:'<b>请将POS终端的参数“应用编号”设置为与已选中的应用编号相同，否则POS终端此应用将无法正常使用。</b>'
    	}]
    });
    
	var addListener = function(){
		appGrid.on('viewready',function(){
			var records = new Array();
			appGrid.store.each(function(record){
				if(record.get('flag') == true){
					records.push(record);
				};
			});
			appSM.selectRecords(records);
//			var task = new Ext.util.DelayedTask(function(){
//			});
//			task.delay(1);
		});
		
		appCombo.store.on('beforeload', function() {
			Ext.apply(this.baseParams, {
				'appLocation' : appLocation,
				'cityName' : cityName
			});
		});
		
		appCombo.store.on('load', function(store,records) {
			if(records.length == 0){
				Ext.Msg.alert('提示','商户所在城市尚未添加此类应用');
			}
		});
		
		appGrid.on('rowclick',function(grid,rowIndex){
			appLocation = rowIndex;
		});
		
		appSM.on('beforerowselect', function(sm, rowIndex ,keepExisting, record){
			if(record.get('id') == '' || record.get('id') == undefined){
				return false;
			} 
//			return false;
//			var selectApps = sm.getSelections();
//			alert(selectApps);
//			sm.selectRecords(selectApps);
		});

//		appGrid.on("cellclick", function(grid, rowIndex,columnIndex,event) {//这是表格的列点击事件
//		    if (columnIndex != 1) {
//		    	if (appSM.isSelected(rowIndex))//判断表格的行的选中状态
//		            appSM.deselectRow(rowIndex);//设置CheckboxSelectionModel的选中行的状态
//		        else
//		        	appSM.selectRow(rowIndex, true);
//		    }
//		});
		
	};
	var loadApps = function(){
		var data = Ext.decode(appData.msg);
		var store = appGrid.store;
		for(var i=0;i<data.length;i++){
			if(data[i] != undefined){
				var index = data[i].location;
				var type = '';
				if(index == 0){
					type = '会生活';
				}else if(index == 1){
					type = '收单';
				}
//				else{
//					type = '其他应用';
//				}
				store.getAt(index).set('id',data[i].id);
				store.getAt(index).set('name',data[i].name);
				store.getAt(index).set('type',type);
				store.getAt(index).set('code',data[i].code);
				store.getAt(index).set('comments',data[i].comments);
				store.getAt(index).set('flag',data[i].flag);
			}
		}
		
	};
	addListener();
	loadApps();
	Ext.QuickTips.init();
	return appGrid;
};