package com.justinmobile.security.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;
import  org.apache.commons.lang.StringUtils;

import com.justinmobile.security.domain.SysMenu;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysMenuManager;
import com.justinmobile.security.manager.SysUserManager;

public class TreeBuilder {

	private SysMenuManager sysMenuManager;
	
	private SysUserManager sysUserManager;

	private String contextPath = "";

	private List<SysMenu> menuListAll;
	
	private HttpServletRequest getRequest() {
		return WebContextFactory.get().getHttpServletRequest();
	}

	private HttpSession getSession() {		
		return WebContextFactory.get().getSession();
	}
	
	public boolean isExpired(){
		if(WebContextFactory.get().getSession(false) == null){
			return true;
		}else{
			return false;
		}
	}
	
	public String getLoginTime() {		
		String loginTime = String.valueOf(getSession().getAttribute("loginTime"));
		if(StringUtils.isBlank(loginTime)||loginTime.equalsIgnoreCase("null")){
			SysUser user = sysUserManager.getUserByLoginName(getLoginName());
			loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getLatestLogin().getTime());
			getSession().setAttribute("loginTime", loginTime);
		}
 		return  loginTime;
	}

	public String getLoginName(){
		User user = null;
		try {
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String loginName = user.getUsername();
			return  loginName;
		} catch (Exception e) {
			return "";
		}
	}

	public TreeItem getUsersTree() throws Exception {
		
		contextPath = getRequest().getContextPath();
		menuListAll = sysMenuManager.queryMunesByUser(getLoginName());
		TreeItem treeRoot = new TreeItem("root", false, null, "选择管理模块", "");
		treeRoot = buildTree(treeRoot, menuListAll);
		return treeRoot;
	}
	
	public TreeItem getAllTrees() throws Exception {
		
		menuListAll = sysMenuManager.getAll();
		TreeItem treeRoot = new TreeItem("root", false, null, "选择菜单", "");
		treeRoot = buildTree(treeRoot, menuListAll);
		return treeRoot;
	}

	public TreeItem buildTree(TreeItem treeRoot, List<SysMenu> menulist)
			throws Exception {
		if (treeRoot == null) {
			throw new Exception("The root tree is empty!");
		}
		if (treeRoot.isLeaf) {
			throw new Exception("Leaf don't have leaves!");
		}
		for (int i = 0; i < menulist.size(); i++) {

			boolean isLeaf = true;

			SysMenu sysMenu = (SysMenu) menulist.get(i);
			String id = String.valueOf(sysMenu.getId());
			List<SysMenu> subMenus = filterUngranted(getList(sysMenu
					.getChildMenu()));

			if (subMenus != null && subMenus.size() > 0) {
				isLeaf = false;
			}
			
			String url = "";
			if(null!= sysMenu.getUrl()&&!sysMenu.getUrl().equals("")){
				url = contextPath + sysMenu.getUrl();
			}

			if (null == sysMenu.getParent()) {// 无父菜单判断为顶级菜单，只发生在第一次递归时
				
				TreeItem treeItem = new TreeItem(id, isLeaf, treeRoot,
						sysMenu.getMenuName(), url);
				treeItem.setOrderNo(sysMenu.getOrderNo());
				if (isLeaf) {
					treeRoot.addLeaf(treeItem);
				} else {
					buildTree(treeItem, subMenus);
					treeRoot.addMenu(treeItem);
				}
			} else {

				if (menulist.contains(sysMenu.getParent())) {// 父菜单在当前菜单列表中，说明是更低级的菜单，不在这层添加
					continue;
				}

				if (String.valueOf(sysMenu.getParent().getId()).equals(treeRoot.getId())) {
					TreeItem treeItem = new TreeItem(id, isLeaf, treeRoot,
							sysMenu.getMenuName(), url);
					treeItem.setOrderNo(sysMenu.getOrderNo());
					if (isLeaf) {
						treeRoot.addLeaf(treeItem);
					} else {
						buildTree(treeItem, subMenus);
						treeRoot.addMenu(treeItem);
					}
				}

			}

		}
		return treeRoot;
	}

	private List<SysMenu> getList(Set<SysMenu> treeSet) {

		List<SysMenu> menulist = new ArrayList<SysMenu>();
		for (SysMenu sm : treeSet) {
			menulist.add(sm);
		}
		return menulist;
	}

	public List<SysMenu> filterUngranted(List<SysMenu> menulist)
			throws Exception {

		menulist.retainAll(menuListAll);
		return menulist;
	}

	public void setSysMenuManager(SysMenuManager sysMenuManager) {
		this.sysMenuManager = sysMenuManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

}
