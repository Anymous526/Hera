var getTreePanel = function(ids) {

	var selectedMenuIds = new Array(); // 保存选择结果
	if (ids !== undefined && ids.length > 0) {
		selectedMenuIds = ids;
	}
	
	// 递归选择所有父节点
	function selectAllAnce(node){
		if(node.parentNode){
			node = node.parentNode;
			if(node.getUI().isChecked()){
				return;
			}
			node.getUI().toggleCheck(true);
			selectAllAnce(node);
		}else{
			return;
		}
	}
	// 递归去除所有子节点
	function deselectAllChildren(node){
		if(node.hasChildNodes()){
			node.eachChild(function(childNode){
				if(childNode.getUI().isChecked()){
					childNode.getUI().toggleCheck(false);
					deselectAllChildren(childNode);
				}
			})
			 
		}else{
			return;
		}
	}
	//选项框处理函数
	function checkHanlder(node,checked){
		if(checked){
			selectAllAnce(node);
		}else{
			deselectAllChildren(node);
		}
	 }
	/* 递归 构建Ext菜单树 */
	function buildTree(root, menus, leaves) {

		if (menus != null && menus.length > 0) {

			for (var i = 0; i < menus.length; i++) {

				var menu = menus[i];

				var menuNode = new Ext.tree.TreeNode({
							id : menu.id,
							text : menu.name,
							expanded : true,
							checked : (selectedMenuIds.indexOf(parseInt(menu.id)) != -1)?true:false
						});
				menuNode.order = menu.orderNo ? menu.orderNo : 0;
				menuNode.addListener('checkchange',checkHanlder);
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
							id : leaf.id,
							text : leaf.name,
							expanded : false,
							checked : (selectedMenuIds.indexOf(parseInt(leaf.id)) != -1)?true:false
						});
				leafNode.order = leaf.orderNo ? leaf.orderNo : 0;
				leafNode.addListener('checkchange',checkHanlder);
				root.appendChild(leafNode);
			}
		}
	}

	// 用dwr获取所有菜单
	var treeTemplate;
	TreeBuilder.getAllTrees({
				async : false,
				callback : function(result) {
					treeTemplate = result;
				}
			})

	var root = new Ext.tree.TreeNode({
				id : treeTemplate.id,
				text : treeTemplate.name,
				expanded : true,
				order : 0
			});

	buildTree(root, treeTemplate.menus, treeTemplate.leaves);

	// 菜单选择处理器
	var checkHanlder = function(node, checked) {
	
		if (checked) {

			// 保存选择项id
			var newId = node.id;
			// 需剔除重复项
			for (var i = 0; i < selectedMenuIds.length; i++) {
				if (selectedMenuIds[i] == newId) {
					return;
				}
			}

			selectedMenuIds.push(newId);

		} else {
			// 删除列表中的此项
			for (var i = 0; i < selectedMenuIds.length; i++) {
				if (selectedMenuIds[i] == node.id) {
					selectedMenuIds.splice(i, 1);

				}
			}

		}

	};

	// 菜单树
	var tree = new Ext.tree.TreePanel({
				id : 'menuSelectTree',
				root : root,
				layout : 'fit',
				autoScroll : true,
				selModel : new Ext.tree.MultiSelectionModel(),
				selectedIds : selectedMenuIds
			});

	tree.on('checkchange', checkHanlder);

	new Ext.tree.TreeSorter(tree, {
				sortType : function(node) {
					if (node.order == 0)
						return Number.MAX_VALUE;
					return parseInt(node.order, 10);
				}
			});

	return tree;
};