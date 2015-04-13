
 

function TreeItem(id, name, url, isLeaf, parent) {
	this.id = id;
	this.paren = parent;
	this.name = name;
	this.url = url;
}
TreeItem.leaves = [];

TreeItem.menus = [];

TreeItem.prototype.addLeaf = function(item){ if(this.isLeaf){return true;} this.leaves.push(item); };

TreeItem.prototype.getLeaves = function(){if(this.isLeaf){return null;} return this.leaves };

TreeItem.prototype.addMenu = function(menu) {if(this.isLeaf){return true;} this.menus.push(menu);};

TreeItem.prototype.getMenus= function(){if(this.isLeaf){return null;} return this.menus };