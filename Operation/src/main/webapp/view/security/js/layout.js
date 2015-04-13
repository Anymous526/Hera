Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	
	var forceRelogin = function(){
		topWin = window;
 			while(topWin.parent != topWin.self)   
     		 {   
          		topWin = topWin.parent;   
     		 } 
			topWin.location=contextPath + "/view/security/jsp/login.jsp?login_error=session_expired";
			return false;
	}
	
	var loginUser;
	TreeBuilder.getLoginName({
				async : false,
				callback : function(result) {
					loginUser = result;
				}
			});
			
	var loginTime; 
	TreeBuilder.getLoginTime({
				async : false,
				callback : function(result) {
					loginTime = result;
				}
			});
			
	if(!loginTime||!loginUser){
			forceRelogin();
	}
			
	var openPasswordWin = function() {
		pwWin = new Ext.Window({
					height : 200,
					width : 300,
					resizable : false,
					title : "密码修改",
					modal : true,
					constrain : false,
					constrainHeader : false,
					closeAction : 'close',
					stateful : true,
					closable : true,
					border : true,
					layout : 'fit'
				});

		pwWin.on('beforeclose', function() {

				});

		var editForm = getPassWordForm(loginUser.toString(), pwWin);

		pwWin.add(editForm);
		pwWin.doLayout();
		pwWin.show();
		Ext.QuickTips.init();

	};
	
	var topBar = new Ext.Toolbar({

				id : 'topbar',
				cls : 'topbar',
				items : [{
							xtype : 'tbtext',
							text : "登录ID：[" + loginUser + " ] 登录时间：[" + loginTime + "]"

						}, {
							xtype : 'tbfill'
						}, {

							text : '修改密码',
							hidden : selfservice,
							iconCls : 'lock',
							handler : function() {
								openPasswordWin();

							}

						},{

							text : '退出系统',
							iconCls : 'logout',
							handler : function() {
								var isexpired; 
								TreeBuilder.isExpired({
										async : false,
										callback : function(result) {
											isexpired = result;
										}
								});										
								if(isexpired){
									forceRelogin();
								}else{
									window.location = contextPath
											+ "/j_spring_security_logout"
								}
							}

						}, {
							xtype : 'tbspacer'
						}]
			})

	var topPanel = new Ext.Panel({

				region : "north",
				height : 80,
				collapsible : false,
				bbar : topBar,
				bodyStyle : 'background-image:url(' + contextPath
						+ '/images/longbar.jpg);background-repeat:repeat;'

			})

	/* 标签栏 */
	var tpanel = new Ext.TabPanel({

				region : "center",
				plugins : new Ext.ux.TabCloseMenu(),
				enableTabScroll : true,
				activeTab : 0,
				items : [{
					title : ' 欢迎页面 ',
					closable : false,
					html : "<iframe src='"
						+ contextPath
						+ "/view/security/jsp/welcome.jsp' width = '100%' height = '100%'  marginwidth=0 marginheight=0 hspace=0 frameborder ='0' ></iframe>"
					}]
			});

	/* 浏览器视图区 */
	new Ext.Viewport({
				enableTabScroll : true,
				layout : "border",
				autoScroll : false,
				items : [topPanel, {
							id : 'm',
							title : menuTitle,
							region : "west",
							width : 200,
							border : true,
							margins : '3 8 3 3',
							collapsible : false,
							html : "<div id='menu'></>",
							autoScroll : true,
							split : !Ext.isIE,
							tools : [{
										id : 'refresh',
										qtip : '刷新菜单树',
										handler :setTree
									}]
						}, tpanel

				]
			});

	/* dwr预处理得到节点管理url操作，调用回调打开节点管理页面 */
	function getPanel(panelId, panelTitle, view) {
		var xPanel = tpanel.getItem(panelId);
		if (xPanel != null) {
			tpanel.activate(xPanel);
		} else {
			var xPanel = new Ext.Panel({
				id : panelId,
				title : panelTitle,
				layout : 'fit',
				region : 'center',
				autoScroll : true,
				closable : true,
				html : "<iframe src='"
						+ view
						+ "' width = '100%' height = '100%'  marginwidth=0 marginheight=0 hspace=0 frameborder ='0' allowtransparency='yes'></iframe>"
			});

			tpanel.add(xPanel);
			tpanel.activate(xPanel);
			tpanel.doLayout();
		}
	}

	/* 递归 构建Ext菜单树 */
	function buildTree(root, menus, leaves) {

		if (menus != null && menus.length > 0) {

			for (var i = 0; i < menus.length; i++) {

				var menu = menus[i];

				var menuNode = new Ext.tree.TreeNode({
							id : "pmsMenu" + menu.id,
							text : menu.name,
							expanded : false
						});
				menuNode.order =  menu.orderNo?menu.orderNo:0;
				if (menu.url && menu.url.length && menu.url.length > 0) {
					menuNode.url = menu.url;
					menuNode.clickHanlder = function(node, event) {
						getPanel(node.id + "Panel", node.text, node.url);

					};
					menuNode.on("click", menuNode.clickHanlder);
				}
				if ((null != menu.menus && menu.menus.length > 0)
						|| (null != menu.leaves && menu.leaves.length > 0)) {

					buildTree(menuNode, menu.menus, menu.leaves)
				}
				root.appendChild(menuNode);
			}
		}
		if (leaves != null && leaves.length > 0) {
			for (var i = 0; i < leaves.length; i++) {
				var leaf = leaves[i];
				var leafNode = new Ext.tree.TreeNode({
							id : "pmsLeaf" + leaf.id,
							text : leaf.name,
							expanded : false
						});
				leafNode.order = leaf.orderNo?leaf.orderNo:0;
				leafNode.url = leaf.url;
				leafNode.clickHanlder = function(node, event) {
					getPanel(node.id + "Panel", node.text, node.url);

				};
				leafNode.on("click", leafNode.clickHanlder);
				root.appendChild(leafNode);
			}
		}
	}
	
   /* 将菜单树放置到相应区域，此方法也可供刷新用 */
   function setTree() {
		var tree = Ext.getCmp('menuTree');
		if (tree) {
			tree.destroy();
		}
		var treeTemplate;
		TreeBuilder.getUsersTree({
					async : false,
					callback : function(result) {
						treeTemplate = result;
					}
				})
				
		if (!treeTemplate||!treeTemplate.id) {
			 forceRelogin();
		}
		

		var root = new Ext.tree.TreeNode({
					id : treeTemplate.id,
					text : '功能模块',
					expanded : true,
					order : 0
				});

		buildTree(root, treeTemplate.menus, treeTemplate.leaves);

		// 菜单树
		var tree = new Ext.tree.TreePanel({
					id : 'menuTree',
					root : root,
					layout : 'fit'
				});
		new Ext.tree.TreeSorter(tree, {
					sortType : function(node) {
						if(node.order==0)
						return Number.MAX_VALUE;
						return parseInt(node.order, 10);
					}
				});
		tree.render("menu");
	}
	setTree();
	 
});