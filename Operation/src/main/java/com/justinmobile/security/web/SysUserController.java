package com.justinmobile.security.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.dao.UserCache;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.web.BaseAjaxController;
import com.justinmobile.security.domain.SysRole;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SecurityCacheManager;
import com.justinmobile.security.manager.SysRoleManager;
import com.justinmobile.security.manager.SysUserManager;
import com.justinmobile.security.web.util.ModelAndResult;

@Controller("sysUserController")
@RequestMapping("/user.do")
public class SysUserController  extends BaseAjaxController {
	
	@Autowired
	private SysUserManager sysUserManager;
	
	@Autowired
	private SysRoleManager sysRoleManager;
	
	@Autowired
	private SecurityCacheManager securityCacheManager;
	
//	private Properties userStatusProp;// 用户状态映射
	
	@Autowired
	private UserCache userCache;
	 
//	public SysUserController() {
//
//		ClassPathResource statusRes = new ClassPathResource("/i18n/userstatus.properties");
//		userStatusProp = new Properties();
//		try {
//			userStatusProp.load(statusRes.getInputStream());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@RequestMapping()
	public void checkNameNew(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("value");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userName", name);
		
		List<SysUser> li = sysUserManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String reason = "用户名已存在";
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
		conditions.put("userName", name);
		List<SysUser> li = sysUserManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String reason = "用户名已存在";
			responseCheckResult(response, reason, false);
			return;
		} else {
			responseCheckResult(response, "", true);
			return;
		}
	}
	
	
	/* 用户状态 */
//	public String getStatusList() {
//		JsonComBo jcb = new JsonComBo();
//		return jcb.getJsonString(userStatusProp);
//
//	}
	
	@RequestMapping()
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		try {
			initializePagingSortingFiltering(request);
			Page<SysUser> page = sysUserManager.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort, filterMap);
			List<SysUser> list = page.getResult();
			JSONArray jsonArray = new JSONArray();
			for(SysUser user : list){
				JSONObject obj = user.toJson();
				if(user.getStatus() == 1){
					obj.put("status", "有效");
				}
				jsonArray.add(obj);
			}
			String result = makeSuccessArrayString(String.valueOf(page.getTotalCount()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
		}

	}
	
	/**
	 * 查询资源列表转化为json格式的字符串
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
//	public String getJsonList(HttpServletRequest request) throws Exception {
//
//		request.setCharacterEncoding("UTF-8");
//		initializePagingSortingFiltering(request);// 初始化页面列表翻页、排序、过滤条件的参数
//
//		String count = "0";
//		Page<SysUser> page = null;
//
//		try {
//
//			page = sysUserManager
//					.pagedQueryBySimpleHsql(getPageNo(), getPageSize(), sort, filterMap);
//			count = String.valueOf(page.getTotalCount());
//		} catch (Exception e) {
//			e.printStackTrace();
//			page = new Page<SysUser>();
//		}
//
//		List<SysUser> list = page.getResult();
//		JSONArray jsonArray = getUserJSONArray(list);
//
//		return makeSuccessArrayString(count, jsonArray.toString());
//	}
//
//	public JSONArray getUserJSONArray(List<SysUser> list) throws BusinessException {
//
//		JsonConfig jsonConfig = new JsonConfig();
//		// 过滤延迟加载的属性
//		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
//			public boolean apply(Object source, String name, Object value) {
//				if (name.equals("sysRoles")) {
//					return true;
//				}
//				if (name.equals("citySet")) {
//					return true;
//				}
//				return false;
//			}
//		});
//		
//		JSONArray jsonArray = new JSONArray();
//		for (SysUser o : list) {
//			JSONObject jsonObject = getJson(o,jsonConfig);
//			jsonArray.add(jsonObject);
//		}
//		return jsonArray;
//	}
//
//	public JSONObject getJson(SysUser user,JsonConfig jsonConfig) throws BusinessException {
//
//		JSONObject jsonObject = null;
//		try {
//			jsonObject = JSONObject.fromObject(user, jsonConfig);
//
//		} catch (JSONException jex) {
//			throw new BusinessException("JSON Excepstion:" + jex.getMessage());
//		}
//
//		if (jsonObject == null) {
//			throw new BusinessException("JSON Excepstion: transform from Object failed");
//		}
//		String value = user.getStatus().equals(1)?"有效":"无效";
//		jsonObject.put("status", value);
//		return jsonObject;
//	}

	@RequestMapping()
	public void removeSelected(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		SysUser user = null;
		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user = sysUserManager.get(Long.valueOf(id));
		if (user == null){
			String msg = "用户不存在!";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		if (sessionUser.getUsername().equals(user.getUserName())) {
			String msg = "用户不能删除自己!";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
//		if(user.getSysRoles().size()>0){
//			
//			String msg = "用户与角色关联，不能删除！";
//			setFailureMessage(request, msg);
//			responseMessage(response, msg, false);
//			return;
//		}
		try {
			user.getSysRoles().clear();
			sysUserManager.remove(user);

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

	public ModelAndResult editUser(String id, String method) {

		ModelAndResult mar = new ModelAndResult();
		
//		mar.addObject("statusList", getStatusList());
		if ("create".equals(method)) {
			 
		} else {

			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", Long.valueOf(id));
			SysUser user = this.sysUserManager.query(m).get(0);
			mar.addObject("id", id);
			mar.addObject("userName", user.getUserName());
			mar.addObject("password",user.getPassword());
			mar.addObject("realName",user.getRealName());
			mar.addObject("mobile",user.getMobile());
			mar.addObject("email",user.getEmail());
			mar.addObject("status",user.getStatus());
			mar.addObject("provinceCode", user.getProvinceCode());
			
		}
		mar.setSuccess(true);
		return mar;
	}
	
	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		SysUser user = sysUserManager.get(Long.valueOf(id));

		if (user == null) {
			setFailureMessage(request, "用户不存在");
			responseMessage(response, "用户不存在", false);
			return;
		}

		BindingResult result;
		result = bindObject(request, user);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userName", user.getUserName());
		List<SysUser> li = sysUserManager.query(conditions);
		 
		if (li!=null&&li.size()>0&&!li.get(0).getId().equals(Long.valueOf(id))) {
			String msg = "用户名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}

		try {
			sysUserManager.save(user);
			User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (user.getUserName().equals(sessionUser.getUsername()) && !user.isEnable()) {
				SecurityContextHolder.clearContext();
				userCache.removeUserFromCache(user.getUserName());
			}
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

		SysUser user = new SysUser();

		BindingResult result;
		result = bindObject(request, user);

		if (result.hasErrors()) {
			setFailureMessage(request, "绑定请求数据发生异常！");
			responseMessage(response, "绑定请求数据发生异常！", false);
			return;
		}
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userName", user.getUserName());
		List<SysUser> li = sysUserManager.query(conditions);
		 
		if (li!=null&&li.size()>0) {
			String msg = "用户名已存在";
			setFailureMessage(request, msg);
			responseMessage(response, msg, false);
			return;
		}
		
		String passWord = user.getPassword();
		String salt = String.valueOf(new Random(Long.MAX_VALUE).nextLong());
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
		// 表示：生成24位的Base64版
		md5.setEncodeHashAsBase64(false);
		String pwd = md5.encodePassword(passWord, salt);
		user.setSalt(salt);
		user.setPassword(pwd);
		  
		try {
			sysUserManager.save(user);
		}  catch (BusinessException e) {
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

	public List<Long> getRoles(String userId){
		
		SysUser user = sysUserManager.get(Long.valueOf(userId));
		Set<SysRole> set = user.getSysRoles();
		List<Long> ids = new ArrayList<Long>();
		for(SysRole a :set){
			ids.add(a.getId());
		}
		
		return ids;
	}
	
	@RequestMapping()
	public void setRoles(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String userId = request.getParameter("userId");
		int length = Integer.valueOf(request.getParameter("length"));
		List<Long> roleIds = new ArrayList<Long>();
		for (int i = 0; i < length; i++) {
			String value = request.getParameter("roleId" + i);
			if (value != null) {
				roleIds.add(Long.valueOf(value));
			}
			
		}
		 
		SysUser user = sysUserManager.get(Long.valueOf(userId));
		Set<SysRole> roles = new HashSet<SysRole>();
		try {
			for (Long roleId : roleIds) {
				SysRole a = sysRoleManager.get(roleId);
				roles.add(a);
			}

			user.setSysRoles(roles);
			sysUserManager.save(user);
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
		responseMessage(response,"设置角色操作成功！", true);
		
	}

	@RequestMapping()
	public void userSetCities(HttpServletRequest request, HttpServletResponse response){
		try {
			SysUser user = this.sysUserManager.get(Long.valueOf(request.getParameter("userId")));
			String[] cityIds = request.getParameterValues("cityIds");
			this.sysUserManager.setCities(user,cityIds);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);	
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping()
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		SysUser user = null;
		try {
			user = sysUserManager.get(Long.valueOf(id));
			if (user == null) {
				setFailureMessage(request, "未找到用户");
				responseMessage(response, "未找到用户", false);
				return;
			}

			// 设置为初始密码
			String defaultPassword = "000000";
			sysUserManager.encodeWithSalt(user,defaultPassword);
			sysUserManager.save(user);

		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, "数据访问异常!");
			responseMessage(response, "数据访问异常!", false);
			return;
		}
		 
		responseMessage(response, "重置密码成功！", true);

	}
	
	@RequestMapping()
	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String loginId = request.getParameter("loginId");
		String oldPassword = request.getParameter("passwordOld");
		String password = request.getParameter("password");
		
		SysUser user = null;
		try {
			user = sysUserManager.getUserByLoginName(loginId);
			if (user == null) {
				setFailureMessage(request, "未找到用户");
				responseMessage(response, "未找到用户", false);
				return;
			}
			
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
			// 表示：生成24位的Base64版
			md5.setEncodeHashAsBase64(false);
			oldPassword = md5.encodePassword(oldPassword,user.getSalt());
			if (!user.getPassword().equals(oldPassword)) {
				setFailureMessage(request,  "旧密码错误");
				responseMessage(response,  "旧密码错误", false);
				return;
			}
			sysUserManager.encodeWithSalt(user,password);
			sysUserManager.save(user);

		} catch (BusinessException e) {
			e.printStackTrace();
			setFailureMessage(request, "数据访问异常!");
			responseMessage(response, "数据访问异常!", false);
			return;
		}
		 
		responseMessage(response, "修改密码成功！", true);
	}
	
}
