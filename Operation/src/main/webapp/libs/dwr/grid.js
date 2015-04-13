var GridUI = function() {
  	var ds;
	var grid; //component
	var columnModel; // definition of the columns
	
	function initDataSource() {
		var recordType = Ext.data.Record.create([
		  	{name: "id", type: "int"},
		  	{name: "description", type: "string"},
			{name: "first", mapping:"author.firstName", type: "string"},
			{name: "last", mapping:"author.lastName", type: "string"}
		  ]);

		  ds = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(TodoService.getItems, true),
		    reader: new Ext.data.ListRangeReader( 
					{id:'id', totalProperty:'totalSize'}, recordType),
		    remoteSort: true
		  });
		
			ds.on("load", function () {
			});		
	}
	
	function getColumnModel() {
		if(!columnModel) {
			columnModel = new Ext.grid.ColumnModel(
				[
					{
						header: 'Description',
						width: 250,
						sortable: true,
						dataIndex: 'description'
					},
					{
						header: 'First Name',
						width: 250,
						sortable: true,
						dataIndex: 'first'
					},
					{
						header: 'Last Name',
						width: 250,
						sortable: true,
						dataIndex: 'last'
					}											
				]
			);
		}
		return columnModel;
	}	
	
	function buildGrid() {				
		grid = new Ext.grid.GridPanel(
			
			{
				title: 'my dwr EXT grid',
				renderTo:'mygrid',
				ds: ds,
				cm: getColumnModel(),
				autoSizeColumns: true,
				height:500,
				selModel: new Ext.grid.RowSelectionModel({singleSelect:true})
			}
		);
		
		 
		grid.render();
	}
			

	return {
		init : function() {
			initDataSource();
				
			ds.load({params:{start:0, limit:22}, arg:['walter', true]});
			buildGrid();
			
		},
		
		getStore: function() {
			return ds;
		}
	}
}();

Ext.onReady(GridUI.init, GridUI, true);	