package com.justinmobile.security.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysMenu;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.manager.SysAuthorityManager;
import com.justinmobile.security.manager.SysMenuManager;
import com.justinmobile.security.manager.SysResourceManager;
import com.justinmobile.security.web.util.ModelAndResult;

@Controller("sysAuthorityController")
@RequestMapping("/auth.do")
public class SysAuthorityController extends BaseAjaxController {

	@Autowired
	private SysAuthorityManager sysAuthorityManager;

	@Autowired
	private SysResourceManager sysResourceManager;

	@Autowired
	private SysMenuManager sysMenuManager;
	
	@Autowired
	private SecurityCacheManager securityCacheManager;

	@RequestMapping()
	public void checkNameNew(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("value");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("authName", name);
		
		List<SysAuthority> li = sysAuthorityManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String reason = "权限名已存在";
			responseCheckResult(response, reason, false);
			return;
		} else {
			responseCheckResult(response, "", true);
			return;
		}

	}

	@RequestMapping()
	public void checkNameUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("value");
		String id = request.getParameter("id");
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("authName", name);
		List<SysAuthority> li = sysAuthorityManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String reason = "权限名已存在";
			responseCheckResult(response, reason, false);
			return;
		} else {
			responseCheckResult(response, "", true);
			return;
		}

	}
	
	@RequestMapping()
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getJsonList(request));

	}

	@RequestMapping()
	public void removeSelected(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		SysAuthority auth = null;
		try {
			auth = sysAuthorityManager.get(Long.valueOf(id));
			if (auth == null) {
				String msg = "资源不存在!";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;
			}

//			if (null != auth.getSysResources() && auth.getSysResources().size() > 0) {
//				String msg = "存在与权限关联的资源，不能删除！";
//				setFailureMessage(request, msg);
//				responseMessage(response, msg, false);
//				return;
//
//			}
//
//			if (null != auth.getSysMenus() && auth.getSysMenus().size() > 0) {
//				String msg = "存在与权限关联的菜单，不能删除！";
//				setFailureMessage(request, msg);
//				responseMessage(response, msg, false);
//				return;
//
//			}

			if (null != auth.getSysRoles() && auth.getSysRoles().size() > 0) {
				String msg = "存在与权限关联的角色，不能删除！";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;

			}
			auth.getSysMenus().clear();
			auth.getSysResources().clear();
			sysAuthorityManager.remove(auth);

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
	public ModelAndResult editAuth(String id, String method) {

		ModelAndResult mar = new ModelAndResult();
		if ("create".equals(method)) {
		} else {

			Map m = new HashMap();
			m.put("id", Long.valueOf(id));
			SysAuthority auth = this.sysAuthorityManager.query(m).get(0);
			mar.addObject("id", id);
			mar.addObject("authName", auth.getAuthName());
			mar.addObject("description", auth.getDescription());

		}
		mar.setSuccess(true);
		return mar;
	}

	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		SysAuthority auth = sysAuthorityManager.get(Long.valueOf(id));

		if (auth == null) {
			setFailureMessage(request, "权限不存在");
			responseMessage(response, "权限不存在", false);
			return;
		}

		BindingResult result;
		result = bindObject(request, auth);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("authName", auth.getAuthName());
		List<SysAuthority> li = sysAuthorityManager.query(conditions);
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String msg = "权限名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		} 
		
		try {
			sysAuthorityManager.save(auth);
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			int i = msg.indexOf("\r\n");
			if (i != -1)
				msg = msg.substring(0, i);// 只显示第一条错误
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据保存成功!";
		responseMessage(response, msg, true);

	}

	@RequestMapping()
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SysAuthority auth = new SysAuthority();

		BindingResult result;
		result = bindObject(request, auth);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("authName", auth.getAuthName());
		List<SysAuthority> li = sysAuthorityManager.query(conditions);
		if (li!=null&&li.size()>0) {
			String msg = "权限名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		} 
		
		try {
			sysAuthorityManager.save(auth);
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			int i = msg.indexOf("\r\n");
			if (i != -1)
				msg = msg.substring(0, i);// 只显示第一条错误
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据创建成功!";
		responseMessage(response, msg, true);

	}

	public List<Long> getResources(String authId) {

		SysAuthority auth = sysAuthorityManager.get(Long.valueOf(authId));
		Set<SysResource> resSet = auth.getSysResources();
		List<Long> ids = new ArrayList<Long>();
		for (SysResource res : resSet) {
			ids.add(res.getId());
		}

		return ids;
	}

	@RequestMapping()
	public void setResouces(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String authId = request.getParameter("authId");
		int length = Integer.valueOf(request.getParameter("length"));
		List<String> resIds = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			String value = request.getParameter("resId" + i);
			if (value != null) {
				resIds.add(value);
			}

		}
		SysAuthority auth = sysAuthorityManager.get(Long.valueOf(authId));
		Set<SysResource> sysResources = new HashSet<SysResource>();
		try {
			for (String resId : resIds) {
				SysResource res = sysResourceManager.get(Long.valueOf(resId));
				sysResources.add(res);
				
			}

			auth.setSysResources(sysResources);
			sysAuthorityManager.save(auth);
			securityCacheManager.reloadResourceInCache();

		} catch (BusinessException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response, e.getMessage(), false);
			return;

		} catch (HibernateException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response, e.getMessage(), false);
			return;

		}
		responseMessage(response, "设置资源操作成功！", true);

	}

	public List<Long> getMenus(String authId) {

		SysAuthority auth = sysAuthorityManager.get(Long.valueOf(authId));
		Set<SysMenu> mSet = auth.getSysMenus();
		List<Long> ids = new ArrayList<Long>();
		for (SysMenu m : mSet) {
			ids.add(m.getId());
		}

		return ids;
	}

	@RequestMapping()
	public void setMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String authId = request.getParameter("authId");
		int length = Integer.valueOf(request.getParameter("length"));
		List<Long> menuIds = new ArrayList<Long>();
		for (int i = 0; i < length; i++) {
			String value = request.getParameter("menuId" + i);
			if (value != null) {
				menuIds.add(Long.valueOf(value));
			}

		}
		SysAuthority auth = sysAuthorityManager.get(Long.valueOf(authId));
		Set<SysMenu> sysMenus = new HashSet<SysMenu>();
		try {
			for (Long menuId : menuIds) {
				SysMenu menu = sysMenuManager.get(menuId);
				// 菜单已被授权，但菜单的父菜单没被授权
				if (null != menu.getParent() && StringUtils.hasText((menu.getParent().getMenuName()))) {
					if (!menuIds.contains(menu.getParent().getId())) {
						SysMenu childMenu = menu;
						while (null != childMenu.getParent()) {
							sysMenus.add(childMenu.getParent());
							childMenu = childMenu.getParent();
						}

					}
				}
				sysMenus.add(menu);
			}

			auth.setSysMenus(sysMenus);
			sysAuthorityManager.save(auth);
			securityCacheManager.reloadResourceInCache();

		} catch (BusinessException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response, e.getMessage(), false);
			return;

		} catch (HibernateException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response, e.getMessage(), false);
			return;

		}
		responseMessage(response, "设置菜单操作成功！", true);

	}

	/**
	 * 查询资源列表转化为json格式的字符串
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getJsonList(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		initializePagingSortingFiltering(request);// 初始化页面列表翻页、排序、过滤条件的参数

		String count = "0";
		Page<SysAuthority> page = null;

		try {

			page = sysAuthorityManager.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort,
					filterMap);
			count = String.valueOf(page.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<SysAuthority>();
		}

		List<SysAuthority> resList = page.getResult();
		JSONArray jsonArray = getAuthJSONArray(resList);

		return makeSuccessArrayString(count, jsonArray.toString());
	}

	@SuppressWarnings("unchecked")
	public JSONArray getAuthJSONArray(List<SysAuthority> list) throws BusinessException {

		JsonConfig jsonConfig = new JsonConfig();
		// 过滤延迟加载的属性
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("sysResources")) {
					return true;
				}
				if (name.equals("sysRoles")) {
					return true;
				}
				if (name.equals("sysMenus")) {
					return true;
				}
				return false;
			}
		});

		JSONArray jsonArray = new JSONArray();
		for (SysAuthority o : list) {
			JSONObject jsonObject = getJson(o, jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public JSONObject getJson(SysAuthority auth, JsonConfig jsonConfig) throws BusinessException {

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONObject.fromObject(auth, jsonConfig);

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
		return jsonObject;
	}

}
