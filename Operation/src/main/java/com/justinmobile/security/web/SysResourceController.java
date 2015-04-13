package com.justinmobile.security.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.web.BaseAjaxController;
import com.justinmobile.security.domain.SysResource;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.manager.SysResourceManager;
import com.justinmobile.security.web.util.JsonComBo;
import com.justinmobile.security.web.util.ModelAndResult;

@Controller("sysResourceController")
@RequestMapping("/res.do")
public class SysResourceController extends BaseAjaxController{
	
	@Autowired
	private SysResourceManager sysResourceManager;
	
	private Properties resourceTypeProp;// 资源类型映射
	
	@Autowired
	private SecurityCacheManager securityCacheManager;
	
	public SysResourceController() {

		ClassPathResource recRes = new ClassPathResource("/i18n/resourcetype.properties");
		resourceTypeProp = new Properties();
		try {
			resourceTypeProp.load(recRes.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping()
	public void checkNameNew(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("value");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("resName", name);
		
		List<SysResource> li = sysResourceManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String reason = "资源名已存在";
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
		conditions.put("resName", name);
		List<SysResource> li = sysResourceManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String reason = "资源名已存在";
			responseCheckResult(response, reason, false);
			return;
		} else {
			responseCheckResult(response, "", true);
			return;
		}
		 
		 

	}
	
	@RequestMapping()
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getJsonList(request));

	}
	
	@RequestMapping()
	public void removeSelected(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		SysResource res = null;
		try {
			res = sysResourceManager.get(Long.valueOf(id));
			if(res==null){
				 String msg = "资源不存在!";
				 setFailureMessage(request,msg);
				 responseMessage(response, msg, false);
				 return;
			}
			if(null!=res.getSysAuthorities()&&res.getSysAuthorities().size()>0){
				String msg = "资源已与权限关联，不能删除";
				setFailureMessage(request,msg);
				responseMessage(response, msg, false);
				return;
				
			}
			
			sysResourceManager.remove(res);
			
		} catch (HibernateException he) {
			he.printStackTrace();
			String msg = "数据库访问异常！";
			setFailureMessage(request,msg);
			responseMessage(response, msg, false);
			return;

		} catch (BusinessException e) {
			e.printStackTrace();
			String msg = "数据删除发生异常！";
			setFailureMessage(request,msg);
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据删除成功!";
		responseMessage(response, msg, true);

	}
	
	@SuppressWarnings("unchecked")
	public ModelAndResult editRes(String id, String method) {

		ModelAndResult mar = new ModelAndResult();
		mar.addObject("resTypeItemList", getResTypeItemList());
		
		if ("create".equals(method)) {
		 
		} else {

			Map m = new HashMap();
			m.put("id", Long.valueOf(id));
			SysResource res = this.sysResourceManager.query(m).get(0);
			mar.addObject("id", id);
			mar.addObject("resName", res.getResName());
			mar.addObject("filterString", res.getFilterString());
			mar.addObject("resType", res.getType());
		}
		mar.setSuccess(true);
		return mar;
	}

	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		SysResource res = sysResourceManager.get(Long.valueOf(id));
		
		if (res==null) {
			setFailureMessage(request, "资源不存在");
			responseMessage(response, "资源不存在", false);
			return;
		}
		
		BindingResult result;
		result = bindObject(request, res);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("resName", res.getResName());
		List<SysResource> li = sysResourceManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String msg = "资源名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		
		try {
			sysResourceManager.save(res);
			securityCacheManager.reloadResourceInCache();
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg =  e.getMessage();
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
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SysResource res = new SysResource();
		
		BindingResult result;
		result = bindObject(request, res);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("resName", res.getResName());
		List<SysResource> li = sysResourceManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String msg = "资源名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		
		try {
			sysResourceManager.save(res);
			securityCacheManager.reloadResourceInCache();
		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, e.getMessage());
			String msg =  e.getMessage();
			int i = msg.indexOf("\r\n");
			if(i!=-1)
			msg = msg.substring(0,i);// 只显示第一条错误
			responseMessage(response, msg, false);
			return;
		}

		String msg = "数据创建成功!";
		responseMessage(response, msg, true);
		 
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
		Page<SysResource> page = null;

		try { 
			page  = sysResourceManager.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort, filterMap);
			count = String.valueOf(page.getTotalCount());

		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<SysResource>();
		}

		List<SysResource> resList = page.getResult();
		JSONArray jsonArray = getRecJSONArray(resList);

		return makeSuccessArrayString(count, jsonArray.toString());
	}

	@SuppressWarnings("unchecked")
	public JSONArray getRecJSONArray(List<SysResource> list) throws BusinessException {

		JsonConfig jsonConfig = new JsonConfig();
		//过滤延迟加载的属性
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("sysAuthorities")) {
					return true;
				}
				return false;
			}
		});
		
		JSONArray jsonArray = new JSONArray();
		for (SysResource o : list) {
			JSONObject jsonObject = getJson(o, jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public JSONObject getJson(SysResource rec,JsonConfig jsonConfig) throws BusinessException {
		
	

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONObject.fromObject(rec, jsonConfig);

		} catch (JSONException jex) {
			throw new BusinessException("JSON Excepstion:" + jex.getMessage());
		}

		if (jsonObject == null) {
			throw new BusinessException(
					"JSON Excepstion: transform from Object failed");
		}

		
		// 处理资源模块名
		//jsonObject.put("moduleName", rec.getModule().getTitle());

		// 处理资源菜单名
		//if (null != rec.getMenu()) {
		//	jsonObject.put("menuName", rec.getMenu().getName());
		//}
		return jsonObject;
	}
	
	/* 资源类型 */
	public String getResTypeItemList() {
		JsonComBo jcb = new JsonComBo();
		return jcb.getJsonString(resourceTypeProp);

	}
	
}
