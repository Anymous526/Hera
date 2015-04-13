package com.justinmobile.bmp.pos.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.manager.PosAppVersionManager;
import com.justinmobile.bmp.util.CalendarUtils;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("posAppVersionController")
@RequestMapping("/posVersion.do")
public class PosAppVersionController extends BaseAjaxController{

	@Autowired
	private PosAppVersionManager posAppVersionManager;
	
	/**
	 * 应用版本分页显示
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void list(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			super.initializePagingSortingFiltering(request);
			if (StringUtils.isNotEmpty(sort.get("appCode"))) {
				sort.put("posApp.code", sort.get("appCode"));
				sort.remove("appCode");
			}
			if (StringUtils.isNotEmpty(sort.get("appName"))) {
				sort.put("posApp.name", sort.get("appName"));
				sort.remove("appName");
			}
			if(super.filterMap.containsKey("appCode")){
				filterMap.put("posApp.code", filterMap.get("appCode"));
				filterMap.remove("appCode");
			}
			if(super.filterMap.containsKey("appName_like")){
				filterMap.put("posApp.name_like", filterMap.get("appName_like"));
				filterMap.remove("appName_like");
			}
			if(StringUtils.isNotBlank(request.getParameter("aud"))){
				Map<String, Object> statusFilter = new HashMap<String, Object>();
				statusFilter.put("in", new Integer[] {PosAppVersion.STATUS_VALID_AUD,PosAppVersion.STATUS_DISABLED_AUD,PosAppVersion.STATUS_INVALID_AUD });
				filterMap.put("status", statusFilter);
			}
			Page<PosAppVersion> page = this.posAppVersionManager.pagedQueryBySimpleHsql(super.getPageNo(), super.getPageSize(), super.sort, filterMap);
			List<PosAppVersion> list = page.getResult();
			JSONArray jsonArray = getVersionJSONArray(list);
			String result = super.makeSuccessArrayString(String.valueOf(page.getTotalCount()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	private JSONArray getVersionJSONArray(List<PosAppVersion> list) {
		JSONArray jsonArray = new JSONArray();
		for(PosAppVersion version : list){
			JSONObject json = version.toJson();
//			json.put("appCode", version.getPosApp().getCode());
			json.put("appName", version.getPosApp().getName());
			if(version.getCreateTime() != null){
				json.put("createTime", CalendarUtils.fomatCalendar(version.getCreateTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getValidTime() != null){
				json.put("validTime", CalendarUtils.fomatCalendar(version.getValidTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getStopTime() != null){
				json.put("stopTime", CalendarUtils.fomatCalendar(version.getStopTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getInvalidTime() != null){
				json.put("invalidTime", CalendarUtils.fomatCalendar(version.getInvalidTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getAuditValidTime() != null){
				json.put("auditValidTime", CalendarUtils.fomatCalendar(version.getAuditValidTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getAuditStopTime() != null){
				json.put("auditStopTime", CalendarUtils.fomatCalendar(version.getAuditStopTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			if(version.getAuditInvalidTime() != null){
				json.put("auditInvalidTime", CalendarUtils.fomatCalendar(version.getAuditInvalidTime(), CalendarUtils.LONG_FORMAT_LINE));
			}
			jsonArray.add(json);
		}
		return jsonArray;
	}

	/**
	 * 应用版本详情显示
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void load(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	/**
	 * 应用版本修改
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void modify(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	/**
	 * 应用版本提交
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void commit(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosAppVersion version = this.posAppVersionManager.get(Long.valueOf(request.getParameter("versionId")));
			this.posAppVersionManager.commit(version);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 应用版本停用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void disable(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosAppVersion version = this.posAppVersionManager.get(Long.valueOf(request.getParameter("versionId")));
			this.posAppVersionManager.disable(version);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 应用版本废除
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void setInvalid(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosAppVersion version = this.posAppVersionManager.get(Long.valueOf(request.getParameter("versionId")));
			this.posAppVersionManager.setInvalid(version);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 应用版本审核
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void aud(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosAppVersion version = this.posAppVersionManager.get(Long.valueOf(request.getParameter("versionId")));
			boolean pass = Boolean.valueOf(request.getParameter("pass"));
			if(!pass){
				version.setAuditInfo(request.getParameter("auditInfo"));
			}
			this.posAppVersionManager.auditVersion(version,pass);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
}