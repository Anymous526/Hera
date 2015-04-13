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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.web.BaseAjaxController;
import com.justinmobile.security.domain.SysAuthority;
import com.justinmobile.security.domain.SysRole;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.manager.SysAuthorityManager;
import com.justinmobile.security.manager.SysRoleManager;
import com.justinmobile.security.web.util.ModelAndResult;

@Controller("sysRoleController")
@RequestMapping("/role.do")
public class SysRoleController  extends BaseAjaxController {
	
	@Autowired
	private SysRoleManager sysRoleManager;
	
	@Autowired
	private SysAuthorityManager sysAuthorityManager;
	
	@Autowired
	private SecurityCacheManager securityCacheManager;
	
	@RequestMapping()
	public void checkNameNew(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("value");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", name);
		
		List<SysRole> li = sysRoleManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String reason = "角色名已存在";
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
		conditions.put("roleName", name);
		List<SysRole> li = sysRoleManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String reason = "角色名已存在";
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
		Page<SysRole> page = null;

		try {

			page = sysRoleManager
					.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort, filterMap);
			count = String.valueOf(page.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<SysRole>();
		}

		List<SysRole> list = page.getResult();
		JSONArray jsonArray = getRoleJSONArray(list);

		return makeSuccessArrayString(count, jsonArray.toString());
	}

	@SuppressWarnings("unchecked")
	public JSONArray getRoleJSONArray(List<SysRole> list) throws BusinessException {

		JsonConfig jsonConfig = new JsonConfig();
		// 过滤延迟加载的属性
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("sysUsers")) {
					return true;
				}
				if (name.equals("sysAuthoritys")) {
					return true;
				}
				return false;
			}
		});
		
		JSONArray jsonArray = new JSONArray();
		for (SysRole o : list) {
			JSONObject jsonObject = getJson(o,jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public JSONObject getJson(SysRole role,JsonConfig jsonConfig) throws BusinessException {

		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(role, jsonConfig);

		} catch (JSONException jex) {
			throw new BusinessException("JSON Excepstion:" + jex.getMessage());
		}

		if (jsonObject == null) {
			throw new BusinessException("JSON Excepstion: transform from Object failed");
		}
 
		return jsonObject;
	}

	@RequestMapping()
	public void removeSelected(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		SysRole role = null;
		try {
			role = sysRoleManager.get(Long.valueOf(id));
			if (role == null) {
				String msg = "角色不存在!";
				setFailureMessage(request, msg);
				responseMessage(response, msg, false);
				return;
			}
			
//			if (null != role.getSysUsers() && role.getSysUsers().size() > 0) {
//				String msg = "存在与角色关联的用户，不能删除！";
//				setFailureMessage(request, msg);
//				responseMessage(response, msg, false);
//				return;
//
//			}
//			
//			if (null != role.getSysAuthoritys() && role.getSysAuthoritys().size() > 0) {
//				String msg = "存在与角色关联的权限，不能删除！";
//				setFailureMessage(request, msg);
//				responseMessage(response, msg, false);
//				return;
//
//			}
			role.getSysAuthoritys().clear();
			sysRoleManager.remove(role);

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
	public ModelAndResult editRole(String id, String method) {

		ModelAndResult mar = new ModelAndResult();
		
		
		if ("create".equals(method)) {
			 
		} else {

			Map m = new HashMap();
			m.put("id", Long.valueOf(id));
			SysRole role = this.sysRoleManager.query(m).get(0);
			mar.addObject("id", id);
			mar.addObject("roleName", role.getRoleName());
			mar.addObject("description",role.getDescription());
			
		}
		mar.setSuccess(true);
		return mar;
	}
	
	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		SysRole role = sysRoleManager.get(Long.valueOf(id));

		if (role == null) {
			setFailureMessage(request, "角色不存在");
			responseMessage(response, "角色不存在", false);
			return;
		}

		BindingResult result;
		result = bindObject(request, role);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", role.getRoleName());
		List<SysRole> li = sysRoleManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String msg = "角色名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		try {
			sysRoleManager.save(role);
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			int i = msg.indexOf("\r\n");
			if(i!=-1)
			msg = msg.substring(0,i);// 只显示第一条错误
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据保存成功!";
		responseMessage(response, msg, true);

	}

	@RequestMapping()
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SysRole role = new SysRole();

		BindingResult result;
		result = bindObject(request, role);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", role.getRoleName());
		List<SysRole> li = sysRoleManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String msg = "角色名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		try {
			sysRoleManager.save(role);
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg = e.getMessage();
			int i = msg.indexOf("\r\n");
			if(i!=-1)
			msg = msg.substring(0,i);// 只显示第一条错误
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据创建成功!";
		responseMessage(response, msg, true);

	}

	public List<Long> getAuthorities(String roleId){
		
		SysRole role = sysRoleManager.get(Long.valueOf(roleId));
		Set<SysAuthority> set = role.getSysAuthoritys();
		List<Long> ids = new ArrayList<Long>();
		for(SysAuthority a :set){
			ids.add(a.getId());
		}
		
		return ids;
	}
	
	@RequestMapping()
	public void setAuthorities(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String roleId = request.getParameter("roleId");
		int length = Integer.valueOf(request.getParameter("length"));
		List<Long> authIds = new ArrayList<Long>();
		for (int i = 0; i < length; i++) {
			String value = request.getParameter("authId" + i);
			if (value != null) {
				authIds.add(Long.valueOf(value));
			}
			
		}
		SysRole role = sysRoleManager.get(Long.valueOf(roleId));
		Set<SysAuthority> auths = new HashSet<SysAuthority>();
		try {
			for (Long authId : authIds) {
				SysAuthority a = sysAuthorityManager.get(authId);
				auths.add(a);
			}

			role.setSysAuthoritys(auths);
			sysRoleManager.save(role);
			securityCacheManager.reloadResourceInCache();

		} catch (BusinessException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response,e.getMessage(), false);
			return;

		} catch (HibernateException e) {
			setFailureMessage(request, e.getMessage());
			responseMessage(response,e.getMessage(), false);
			return;

		}
		responseMessage(response,"设置权限操作成功！", true);
		
	}

}
