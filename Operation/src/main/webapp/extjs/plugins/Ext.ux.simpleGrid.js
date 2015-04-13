Ext.namespace('Ext.ux');
Ext.ux.simpleGrid = Ext.extend(Ext.grid.EditorGridPanel, {

			renderTo : "tableDiv",
			border : false,
			layout : 'fit',
			autoScroll : true,
			trackMouseOver : true,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			initComponent : function() {

				if (!this.width) {
					this.width = Ext.lib.Dom.getViewportWidth();
				}
				if (!this.height) {
					this.height = Ext.lib.Dom.getViewHeight() - 26;
				}
				if (!this.store) {
					this.store = new Ext.data.JsonStore({
								root : 'root',
								totalProperty : 'totalProperty',
								id : "id",
								remoteSort : true,
								proxy : new Ext.data.HttpProxy({
											url : this.url
										}),

								fields : this.nameMapping

							});
				}
				if (!this.pageSizeCombo) {
					this.pageSizeCombo = new Ext.form.ComboBox({
								mode : 'local',// 默认为remote，需要proxy-MemoryProxy
								width : 50,
								fieldLabel : '每页显示',
								store : new Ext.data.SimpleStore({
											fields : ["text", "value"],
											data : [['15', 15], ['20', 20],
													['25', 25], ['30', 30]]
										}),
								displayField : 'text',
								valueField : 'value',
								value : this.pageSize,
								typeAhead : true,
								forceSelection : true,
								triggerAction : 'all',
								allowBlank : false,
								tableStore : this.store,
								grid : this

							});

					this.pageSizeCombo.on("select", function(combo) {
								paging.pageSize = combo.getValue();
								this.grid.pageSize = combo.getValue();
								this.tableStore.load({
											params : {
												start : 0,
												limit : combo.getValue()
											}
										});
								
							});
				}
				if (!this.bbar) {
					var paging = new Ext.PagingToolbar({ // 底层分页条
						pageSize : this.pageSize,
						store : this.store,
						displayInfo : true,
						beforePageText : '第',
						afterPageText : '页 共{0}页',
						displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
						emptyMsg : "没有记录",
						items : ['-', '每页显示：', this.pageSizeCombo, '条记录']

					});
					this.bbar = paging;
				}
				Ext.ux.simpleGrid.superclass.initComponent.call(this);
			}
		})

Ext.reg('simpleGrid', Ext.ux.simpleGrid);