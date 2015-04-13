package com.justinmobile.security.web.util;

import java.util.ArrayList;
import java.util.List;
import com.justinmobile.security.domain.SysMenu;

public class TreeItem {

	private String id;

	public boolean isLeaf;

	private TreeItem parent;

	private String name;

	private String url;
	
	/** 显示顺序 */
	private Integer orderNo;

	private List<TreeItem> leaves;

	private List<TreeItem> menus;

	public TreeItem(String id, boolean isLeaf, TreeItem parent, String name,
			String url) {

		this.id = id;
		this.isLeaf = isLeaf;
		this.parent = parent;
		this.name = name;
		this.url = url;

	}

	public TreeItem getParent() {
		return parent;
	}

	public void setParent(TreeItem parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TreeItem> getLeaves() {
		if (this.isLeaf) {
			return null;
		}
		return leaves;
	}

	public void addLeaf(TreeItem leaf) {
		if (this.isLeaf) {
			return;
		}
		if(leaves==null){
			leaves = new ArrayList<TreeItem>();
		}
		leaves.add(leaf);
	}

	public List<TreeItem> getMenus() {
		if (this.isLeaf) {
			return null;
		}
		return menus;
	}

	public void addMenu(TreeItem menu) {
		if (this.isLeaf) {
			return;
		}
		if(menus==null){
			menus = new ArrayList<TreeItem>();
		}
		menus.add(menu);
	}

	public static TreeItem getTree(List<SysMenu> menulist) {
		return null;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public void setLeaves(List<TreeItem> leaves) {
		this.leaves = leaves;
	}

	public void setMenus(List<TreeItem> menus) {
		this.menus = menus;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
