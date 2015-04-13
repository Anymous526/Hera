package com.justinmobile.security.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.web.BaseAjaxController;
import com.justinmobile.security.domain.SysMenu;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.manager.SysMenuManager;
import com.justinmobile.security.web.util.JsonComBo;
import com.justinmobile.security.web.util.ModelAndResult;

@Controller("sysMenuController")
@RequestMapping("/menu.do")
public class SysMenuController extends BaseAjaxController {

	@Autowired
	private SysMenuManager sysMenuManager;
	
	@Autowired
	private SecurityCacheManager securityCacheManager;

	@RequestMapping()
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getJsonList(request));

	}

	@RequestMapping()
	public void removeSelected(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		SysMenu menu = null;
		try {
			menu = sysMenuManager.get(Long.valueOf(id));
			if (!StringUtils.hasText(menu.getMenuName())) {
				String msg = "菜单不存在!";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;
			}
			if (menu.getChildMenu().size() > 0) {
				String msg = "菜单拥有子菜单，不能删除";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;

			}
			if (menu.getSysAuthority().size() > 0) {
				String msg = "菜单与权限关联，不能删除";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;

			}

			sysMenuManager.remove(menu);

		} catch (HibernateException he) {
			he.printStackTrace();
			String msg = "数据库访问异常！";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;

		} catch (BusinessException e) {
			e.printStackTrace();
			String msg = "数据删除发生异常！";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据删除成功!";
		responseMessage(response, msg, true);

	}

	@SuppressWarnings("unchecked")
	public ModelAndResult editMenu(String id, String method) {

		ModelAndResult mar = new ModelAndResult();

		if ("create".equals(method)) {
			mar.addObject("menuItemList", this.getMenuItemList());
		} else {

			Map m = new HashMap();
			m.put("id", Long.valueOf(id));
			SysMenu menu = this.sysMenuManager.query(m).get(0);
			if (null == menu) {
				mar.setSuccess(false);
				mar.setMessage("菜单不存在！");
			}
			mar.addObject("id", id);
			mar.addObject("menuName", menu.getMenuName());
			mar.addObject("url", menu.getUrl());
			mar.addObject("orderNo", menu.getOrderNo());
			if (null != menu.getParent() && StringUtils.hasText(menu.getParent().getMenuName())) {
				mar.addObject("parentName", menu.getParent().getMenuName());
				mar.addObject("parentId", menu.getParent().getId());
			}
			mar.addObject("menuItemList", getOptionalParents(menu));

		}
		mar.setSuccess(true);
		return mar;
	}

	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse menuponse) throws Exception {

		String id = request.getParameter("id");
		SysMenu menu = sysMenuManager.get(Long.valueOf(id));

		if (menu == null) {
			String msg = "菜单不存在！";
			setFailureMessage(request, msg);
			responseMessage(menuponse, msg, false);
			return;
		}

		BindingResult result;
		result = bindObject(request, menu);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(menuponse, "绑定请求数据发生异常！", false);
			return;
		}

		String parentId = request.getParameter("parentId");
		String menuName = request.getParameter("menuName");
		if (StringUtils.hasText(parentId)) {
			Long pId = Long.valueOf(parentId);
			if (pId.equals(0l)) {
				if (StringUtils.hasText(menuName) && !menu.getMenuName().equals(menuName)) {// 验证顶级菜单名是否重复
					Map<String, Object> conditions = new HashMap<String, Object>();
					Map<String, Object> innerCondition = new HashMap<String, Object>();
					innerCondition.put("null", null);
					conditions.put("parent", innerCondition);
					List<SysMenu> topMenus = sysMenuManager.query(conditions);
					for (SysMenu m : topMenus) {
						if (m.getMenuName().equals(menuName)) {
							setFailureMessage(request, "同级菜单不能重名");
							responseMessage(menuponse, "同级菜单不能重名", false);
							return;
						}
					}
				}
				menu.setParent(null);
			} else {

				SysMenu parent = sysMenuManager.get(pId);
				if (!StringUtils.hasText(parent.getMenuName())) {
					setFailureMessage(request, "父菜单不存在");
					responseMessage(menuponse, "父菜单不存在", false);
					return;
				}
				// 验证同级菜单名是否重复
				if (StringUtils.hasText(menuName)) {
					Set<SysMenu> brotherMenus = parent.getChildMenu();
					for (SysMenu m : brotherMenus) {
						if (m.getMenuName().equals(menuName) && !menuName.equals(menu.getMenuName())) {
							setFailureMessage(request, "同级菜单不能重名");
							responseMessage(menuponse, "同级菜单不能重名", false);
							return;
						}

					}
				}
				menu.setParent(parent);
			}
		}

		try {
			sysMenuManager.save(menu);
			securityCacheManager.reloadResourceInCache();
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			msg = msg.substring(0, msg.indexOf("\r\n"));// 只显示第一条错误
			responseMessage(menuponse, msg, false);
			return;
		}

		String msg = "数据保存成功!";
		responseMessage(menuponse, msg, true);

	}

	@RequestMapping()
	public void save(HttpServletRequest request, HttpServletResponse menuponse) throws Exception {

		SysMenu menu = new SysMenu();

		BindingResult result;
		result = bindObject(request, menu);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(menuponse, "绑定请求数据发生异常！", false);
			return;
		}

		String parentId = request.getParameter("parentId");
		String menuName = request.getParameter("menuName");
		if (StringUtils.hasText(parentId)) {
			Long pId = Long.valueOf(parentId);
			if (pId.equals(0l)) {// 验证父菜单是否存在
				if (StringUtils.hasText(menuName)) {// 验证顶级菜单名是否重复
					Map<String, Object> conditions = new HashMap<String, Object>();
					Map<String, Object> innerCondition = new HashMap<String, Object>();
					innerCondition.put("null", null);
					conditions.put("parent", innerCondition);
					List<SysMenu> topMenus = sysMenuManager.query(conditions);
					for (SysMenu m : topMenus) {
						if (m.getMenuName().equals(menuName)) {
							setFailureMessage(request, "同级菜单不能重名");
							responseMessage(menuponse, "同级菜单不能重名", false);
							return;
						}
					}
				}
				menu.setParent(null);
			} else {
				SysMenu parent = sysMenuManager.get(pId);
				if (!StringUtils.hasText(parent.getMenuName())) {
					setFailureMessage(request, "父菜单不存在");
					responseMessage(menuponse, "父菜单不存在", false);
					return;
				}

				// 验证同级菜单名是否重复
				if (StringUtils.hasText(menuName)) {
					Set<SysMenu> brotherMenus = parent.getChildMenu();
					for (SysMenu m : brotherMenus) {
						if (m.getMenuName().equals(menuName)) {
							setFailureMessage(request, "同级菜单不能重名");
							responseMessage(menuponse, "同级菜单不能重名", false);
							return;
						}

					}
				}
				menu.setParent(parent);
			}
		}

		try {
			sysMenuManager.save(menu);
			securityCacheManager.reloadResourceInCache();
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			msg = msg.substring(0, msg.indexOf("\r\n"));// 只显示第一条错误
			responseMessage(menuponse, msg, false);
			return;
		}

		String msg = "数据创建成功!";
		responseMessage(menuponse, msg, true);

	}

	/**
	 * 查询资源列表转化为json格式的字符串
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getJsonList(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		initializePagingSortingFiltering(request);// 初始化页面列表翻页、排序、过滤条件的参数

		String pId = (String) filterMap.get("parentId");
		if (StringUtils.hasText(pId)) {
			filterMap.put("parent.id", pId);
		}
		filterMap.remove("parentId");

		if (sort.containsKey("parentName")) {
			sort.put("parent.menuName", sort.get("parentName"));
			sort.remove("parentName");
		}

		String count = "0";
		Page<SysMenu> page = null;

		try {
			page = sysMenuManager.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort, filterMap);
			count = String.valueOf(page.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<SysMenu>();
		}

		List<SysMenu> menuList = page.getResult();
		JSONArray jsonArray = getMenuJSONArray(menuList);

		return makeSuccessArrayString(count, jsonArray.toString());
	}

	public JSONArray getMenuJSONArray(List<SysMenu> list) throws BusinessException {

		JsonConfig jsonConfig = new JsonConfig();
		// 过滤延迟加载的属性
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("parent")) {
					return true;
				}
				if (name.equals("childMenu")) {
					return true;
				}
				if (name.equals("sysAuthority")) {
					return true;
				}
				return false;
			}
		});

		JSONArray jsonArray = new JSONArray();
		for (SysMenu o : list) {
			JSONObject jsonObject = getJson(o, jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public JSONObject getJson(SysMenu menu, JsonConfig jsonConfig) throws BusinessException {

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONObject.fromObject(menu, jsonConfig);

		} catch (JSONException jex) {
			throw new BusinessException("JSON Excepstion:" + jex.getMessage());
		}

		if (jsonObject == null) {
			throw new BusinessException("JSON Excepstion: transform from Object failed");
		}

		// 处理资源模块名
		// jsonObject.put("moduleName", rec.getModule().getTitle());

		// 处理资源菜单名
		// if (null != rec.getMenu()) {
		// jsonObject.put("menuName", rec.getMenu().getName());
		// }

		// 处理父菜单名和父菜单id
		if (null != menu.getParent()) {
			jsonObject.put("parentName", menu.getParent().getMenuName());
			jsonObject.put("parentId", menu.getParent().getId());

		}
		return jsonObject;
	}

	/* 菜单列表 */
	public String getMenuItemList() {

		List<SysMenu> list = sysMenuManager.getAll();
		JsonComBo jcb = new JsonComBo();
		for (SysMenu m : list) {
			jcb.add(m.getMenuName(), m.getId());
		}
		return jcb.getJsonString();

	}

	/* 可选父菜单列表 */
	public String getOptionalParents(SysMenu menu) {

		List<SysMenu> list = sysMenuManager.getAll();
		list.remove(menu);// 去除自身
		
		Set<SysMenu> offspring = getOffspring(menu);
		if (null != offspring) {//去除子孙节点
			list.removeAll(offspring);
		}
		
		JsonComBo jcb = new JsonComBo();
		for (SysMenu m : list) {
			jcb.add(m.getMenuName(), m.getId());
		}
		return jcb.getJsonString();

	}

	/**
	 * 递归得到一菜单的所有后代菜单
	 * 
	 * @param menu
	 * @return
	 */
	private Set<SysMenu> getOffspring(SysMenu menu) {

		Set<SysMenu> set = new HashSet<SysMenu>();
		Set<SysMenu> children = menu.getChildMenu();
		if (children != null && children.size() > 0) {
			set.addAll(children);
			for (SysMenu m : children) {
				Set<SysMenu> offspring = getOffspring(m);
				if (null != offspring) {
					set.addAll(getOffspring(m));
				}
			}

			return set;

		} else
			return null;
	}
	

	@RequestMapping()
	public void queryParentMenuItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			initializePagingSortingFiltering(request);
			Page<SysMenu> page = sysMenuManager.pagedQueryParentMenus(super.getPageNo(), super.getPageSize());
			List<SysMenu> menus = page.getResult();
			JSONArray jsonArray = new JSONArray();
			for (SysMenu menu : menus) {
				JSONObject json = new JSONObject();
				json.put("id", menu.getId());
				json.put("menuName", menu.getMenuName());
				jsonArray.add(json);
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(
					makeSuccessArrayString(String.valueOf(page.getTotalCount()), jsonArray.toString()));
		} catch (BusinessException e) {
			e.printStackTrace();
			markResultMessage(request, response, e.getMessage(), false);
		}
	}
	
}