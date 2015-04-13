// 全局路径   
var basePath = "http://localhost:8083/tree";   
if(typeof(glbRootPath) != "undefined"){   
    basePath = glbRootPath;   
}   
// 扩展窗体   
FormEditWin = function(){   
    var curFormWin;   
    return {   
        width : 600,   
        height : 400,   
        showAddDirWin : function(parentNode) {   
            // 显示添加子目录窗口   
            var number = parentNode.indexOf(parentNode.lastChild) + 1;  
           
           var editpage = contextPath   
                //   + "/industryedit?parentid="  
              + "/ind.do?method=industryedit&parentId="
                   + parentNode.id + "&leaf=0&number=" + number;   
            var window = this.createWin("windirnew", "新建行业", editpage, function() {   
                parentNode.reload();   
           });   
            
           
        window.show();   
        },   
        showAddLeafWin : function(parentNode) {   
            // 显示添加子叶子节点窗口   
            var number = parentNode.indexOf(parentNode.lastChild) + 1;   
            var editpage = basePath   
                    + "/industryedit?parentid="  
                    + parentNode.id + "&leaf=1&number=" + number;   
            var window = this.createWin("winleafnew", "新建子行业", editpage, function() {   
                parentNode.reload();   
            });   
            window.show();   
        },   
        showEditDirWin : function(node) {   
           // 显示目录编辑窗口   
           var editpage = basePath   
                    + "/industryedit?id=" + node.id;   
            var window = this.createWin("win" + node.id, node.text, editpage, function() {   
                var nodeparent = node.parentNode;   
                var tree = node.getOwnerTree();   
                nodeparent.on("expand", function(pnode) {   
                   tree.getNodeById(node.id).select();   
                }, this, {   
                    single : true  
                });   
                node.parentNode.reload();   
            });   
            window.show();   
        },   
        showEditLeafWin : function(node) {   
            // 显示叶子节点编辑窗口   
            var editpage = contextPath   
             //      + "/industryedit?id=" + node.id;   
              + "/ind.do?method=industryedit?id="+node.id;
            var window = this.createWin("win" + node.id, node.text, editpage, function() {   
                var nodeparent = node.parentNode;   
                var tree = node.getOwnerTree();   
                nodeparent.on("expand", function(pnode) {   
                    tree.getNodeById(node.id).select();   
                }, this, {   
                    single : true  
                });   
                node.parentNode.reload();   
            });   
            window.show();   
        },   
        createWin : function(winId, winTitle, iframePage, closeFun) {   
            // 供各类型窗口创建时调用   
            var win = Ext.getCmp(winId);   
            if (!win) {   
                win = new Ext.Window({   
                    id : winId,   
                    title : "编辑窗口-" + winTitle,   
                 //   width : this.width,   
                  //  height : this.height,   
				    width:400,
					height:200,
                    maximizable : false,   
                    modal : true,   
                    html : "<iframe width='100%' height='100%' frameborder='0' src='"  
                            + iframePage + "'></iframe>"  
                });   
                this.reloadNavNode = closeFun;   
            }   
            curFormWin = win;   
            return win;   
        },   

		createWinC : function(winId, winTitle, iframePage,closeFun) {   
            // 供各类型窗口创建时调用   
           var win = Ext.getCmp(winId);   
            if (!win) {   
                win = new Ext.Window({   
                    id : winId,   
                    title : "编辑窗口-" + winTitle,   
                 //   width : this.width,   
                  //  height : this.height,   
				    width:400,
					height:200,
                    maximizable : false,   
                    modal : true,   
                    html : "<iframe width='100%' height='100%' frameborder='0' src='"  
                            + iframePage + "'></iframe>"  
                });   
                this.reloadNavNode = closeFun;   
            }   
            curFormWin = win;   
            return win;   
        },   


        reloadNavNode : function() {   
        },   
        close : function() {   
            if(curFormWin){   
                curFormWin.close();   
            }   
        }   
    }   
}();   
  
// 导航树   
NavTree = function(){   
    var nav;   
    var navEditor;   
    var leafMenu;   
    var dirMenu;   
    var loader;   
    var root;   
    var removeFlag = false;   
    var titleChangeFlag = false;   
    var nodeSelected;   
    var mgr;   
    return {   
        init : function(){   
        //    if(!mgr){   
          //      Ext.Msg.alert("警告提示","请先通过NavTree.setMgr()设置mgr");   
            //    return;   
           // }   
            if(!loader){   
                loader = new Ext.tree.TreeLoader({   
            
                	url: contextPath + '/ind.do?method=listIndustry'
                });   
                loader.on('beforeload', function(treeloader, node) { 
               
                    treeloader.baseParams = {   
                        id : node.id,   
                        method : 'tree'  
                    };   
                }, this);   
            }   
            if(!root){   
                root = new Ext.tree.AsyncTreeNode({   
                    id : '0',   
                    text : "行业分类"  
                });   
            }   
            if(!nav){   
                nav = new Ext.tree.TreePanel({   
                    title : "行业分类信息",   
                    width:Ext.lib.Dom.getViewportWidth(),   
                   // autoScroll : true,   
                  //  animate : true,   
                    loader : loader,   
                    root : root,
            
                    enableDD : true,
					preloadChildren:true,
                    listeners : {   
                        'click' : function(node, event) { 
                            if (node.isLeaf()) {   
                                // 为叶子节点时，点击不进入链接   
                              event.stopEvent();   
                            }   
                        }   
                    }   
                });   
                // 添加右键菜单   
				
                nav.on("contextmenu", this.showTreeMenu);   
             
                // 当节点移动时触发事件   
              //  nav.on("movenode", function(tree, node, oldParent, newParent, index) {   
                //    mgr.ajaxMoveNode(node.id, oldParent.id, newParent.id, index);   
                //});   
                // 当节点删除时触发事件   
                nav.on("remove", function(tree, parentNode, node) {   
                    if (removeFlag) {  
					
              
					   Ext.Ajax.request({
							url: contextPath+'/ind.do?method=removeNode',
							success: function(){ Ext.Msg.alert("提示", "删除行业成功！");  this.parent.FormEditWin.reloadNavNode();},
							failure:  function(){ Ext.Msg.alert("提示", "删除行业失败！");},
							params: { id: node.id }
						});

                    }   
                });   
            }   
           
            this.setLeafMenu();   
            this.setDirMenu();   
        },   
        setMgr : function(manager){   
            mgr = manager;   
        },   
        getMgr : function(){   
            return mgr;   
        },   
        setLeafMenu: function(){   
            // 设置叶子菜单   
            if(!leafMenu){   
                leafMenu = new Ext.menu.Menu({   
                    items : [{   
                        text : "修改行业名称",   
                        handler : function() {   
                            navEditor.triggerEdit(nodeSelected);   
                        }   
                    }, "-", {   
                        text : "编辑",   
                        handler : function() {   
                            FormEditWin.showEditLeafWin(nodeSelected);   
                        }   
                    }, "-", {   
                        text : "删除",   
                        handler : this.delTreeItemComfirm   
                    }]   
                });   
            }   
        },   
        setDirMenu: function(){   
            // 设置目录菜单   
            if(!dirMenu){   
                dirMenu = new Ext.menu.Menu({   
                    items : [ {   
                        text : "添加行业",   
                        handler : function() {   
                            FormEditWin.showAddDirWin(nodeSelected);   
                        }   
                    }, "-", {   
                        text : "删除",   
                        handler : this.delTreeItemComfirm   
                    }]   
                });   
            }   
        },   
        
      //  "-", {   
        //    text : "编辑",   
          //  handler : function() {   
            //    FormEditWin.showEditDirWin(nodeSelected);   
            //}   
        //}, "-", {   
         //  text : "添加子行业",   
          //  handler : function() {   
            //    FormEditWin.showAddLeafWin(nodeSelected);   
          //  }   
      // },
        showTreeMenu : function(node, e){   
            nodeSelected = node;   
            nodeSelected.select();   
            if (node.isLeaf()) {   
                // 显示叶子节点菜单   
                leafMenu.showAt(e.getPoint());   
            } else {   
                // 显示目录节点菜单   
                dirMenu.showAt(e.getPoint());   
            }   
        },   
        delTreeItemComfirm : function(){   
            Ext.MessageBox.confirm("行业删除", "确定要删除所行业吗？", function(btn) {   
                if (btn == "yes") {   
                    NavTree.delTreeItem();   
                }   
            });   
        },   
        delTreeItem : function(){   
            if (nodeSelected != nav.getRootNode()) {   
                removeFlag = true;   
                nodeSelected.remove();   
                removeFlag = false;   
            } else {   
                Ext.Msg.alert("警告", "不能删除树的根节点！");   
            }   
        },   
        show : function(){   
            nav.render(Ext.getBody());   
            nav.getRootNode().toggle();   
        }   
    }   
}();   
  
// 文档加载完毕执行   
Ext.onReady(function(){   
	
    Ext.BLANK_IMAGE_URL = "../scripts/resources/images/default/s.gif";   
    if(typeof(IndustryManager)=="undefined"){   
        Ext.Msg.alert("警告提示","请先设置DWR，并实例化IndustryManager");   
    }else{   
        NavTree.setMgr(IndustryManager);   
        NavTree.init();   
        NavTree.show();   
    }   
});  
