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

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantStatus;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.bmp.pos.domain.MerchantAppConfig;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.manager.CityManager;
import com.justinmobile.bmp.pos.manager.PosAppManager;
import com.justinmobile.bmp.util.CalendarUtils;
import com.justinmobile.bmp.util.DateUtils;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("posAppController")
@RequestMapping("/posApp.do")
public class PosAppController extends BaseAjaxController{

	@Autowired
	private PosAppManager posAppManager;
	
	@Autowired
	private CityManager cityManager;
	
	/**
	 * 应用分页查询
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void list(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			super.initializePagingSortingFiltering(request);
			if(StringUtils.isNotBlank(request.getParameter("aud"))){
				super.filterMap.put("status", PosApp.STATUS_DISABLED_AUD);
			}
			Page<PosApp> page = this.posAppManager.pagedQuery(super.getPageNo(), super.getPageSize(), super.sort,super.filterMap);
			List<PosApp> list = page.getResult();
			JSONArray jsonArray = new JSONArray();
			for(PosApp app : list){
				JSONObject json = app.toJson();
				if(app.getCreateTime() != null){
					json.put("createTime", CalendarUtils.fomatCalendar(app.getCreateTime(), CalendarUtils.LONG_FORMAT_LINE));
				}
				if(app.getStopTime() != null){
					json.put("stopTime", CalendarUtils.fomatCalendar(app.getStopTime(), CalendarUtils.LONG_FORMAT_LINE));
				}
				if(app.getAuditStopTime() != null){
					json.put("auditStopTime", CalendarUtils.fomatCalendar(app.getAuditStopTime(), CalendarUtils.LONG_FORMAT_LINE));
				}
				if(app.getProperty() == PosApp.PROPERTY_MAIN){
					json.put("propertyDesc", "主应用");
				}else if(app.getProperty() == PosApp.PROPERTY_SUB){
					json.put("propertyDesc", "子应用");
				}
				jsonArray.add(json);
			}
			String result = super.makeSuccessArrayString(String.valueOf(list.size()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据应用位置及所在城市获取有效应用，不分页
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getAppsByLocation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			String location = request.getParameter("appLocation");
			String cityName = request.getParameter("cityName");
			if(StringUtils.isBlank(cityName)){
				throw new PlatformException(PlatformErrorCode.PARAM_ERROR);
			}
			List<PosApp> list = this.posAppManager.getValidAppsByLocation(Integer.valueOf(location));
			JSONArray jsonArray = new JSONArray();
			for(PosApp app : list){
				for(City city : app.getCitySet()){
					if(cityName.equals(city.getCityName())){
						jsonArray.add(app.toJson());
					}
				}
			}
			String result = super.makeSuccessArrayString(String.valueOf(list.size()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示指定应用适用城市范围内的有效商户（含冻结，不分页）
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void getByCities(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String[] cities = request.getParameterValues("cities");
			if(cities == null || cities.length == 0){
				throw new PlatformException(PlatformErrorCode.PARAM_ERROR);
			}
			PosApp app = this.posAppManager.get(Long.valueOf(request.getParameter("appId")));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", new Integer[]{MerchantStatus.STATUS_FREEZED.getValue(),MerchantStatus.STATUS_ACTIVE.getValue()});
			params.put("area", cities);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_EXPORT.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				JSONArray jsonArray = new JSONArray();
				JSONArray temp = json.getJSONArray("merchants");
				for (int i = 0; i < temp.size(); i++) {
					JSONObject jsonObject = temp.getJSONObject(i);
					jsonObject.put("status", jsonObject.getJSONObject("status").getString("desc"));
					String createDate = jsonObject.getString("createDate");
					JSONObject cityObj = jsonObject.getJSONObject("area").getJSONObject("parent");
					if(!cityObj.isNullObject()){
						jsonObject.put("cityName", cityObj.getString("name"));
					}
					if (!"null".equals(createDate)) {
						jsonObject.put("createDate", DateUtils.format(DateUtils.parse(createDate)));
					}
					boolean hasSelected = this.merchantHasSelectedApp(jsonObject.getString("code"),app);
					if(hasSelected){
						jsonObject.put("flag", true);
					}else{
						if(this.needToLock(jsonObject.getString("code"),app)){
							jsonObject.put("lockFlag", true);
						}
					}
					jsonArray.add(jsonObject);
				}
				String result = makeSuccessArrayString(String.valueOf(temp.size()), jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString("msg"), false);
			}
		} catch (Exception e) {
			super.responseMessage(response, "商户列表查询失败", false);
			e.printStackTrace();
		}

	}
	
	/**
	 * 检查商户是否已选择该应用
	 * @param code 商户编号
	 * @param app 指定应用
	 * @return
	 */
	private boolean merchantHasSelectedApp(String code, PosApp app) {
		for(MerchantAppConfig config : app.getCofigSet()){
			if(code.equals(config.getMerchantCode())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查商户是否需要锁定（不能绑定指定应用）
	 * @param code 商户编号
	 * @param app 指定应用
	 * @return
	 */
	private boolean needToLock(String code, PosApp app) {
		List<PosApp> list = this.posAppManager.getValidAppsByMerchant(code);
		if(list.size() == 0){
			return false;
		}
		for(PosApp tempApp : list){
			if(tempApp.getLocation() == app.getLocation()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取应用适用城市范围
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getCitiesByApp(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosApp app = this.posAppManager.get(Long.valueOf(request.getParameter("appId")));
			JSONArray json = new JSONArray();
			for(City city : app.getCitySet()){
				json.add(city.toJson());
			}
			super.responseMessage(response, json.toString(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询商户有效应用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getAppsByMerchant(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			String merchantCode = request.getParameter("merchantCode");
			List<PosApp> list = this.posAppManager.getValidAppsByMerchant(merchantCode);
			JSONArray jsonArray = new JSONArray();
			for(int i=0;i<list.size();i++){
				if(i > PosApp.TOTALLOCATIONS){
					throw new PlatformException(PlatformErrorCode.MERCHANT_APP_ERROR);
				}
				PosApp app = list.get(i);
				JSONObject obj  = new JSONObject();
				obj.put("id", app.getId());
				obj.put("name", app.getName());
				obj.put("code", app.getCode());
				obj.put("comments", app.getComments());
				obj.put("flag", true);
				obj.put("location", app.getLocation());
				jsonArray.add(obj);
			}
			super.responseMessage(response, jsonArray.toString(), true);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 应用停用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void disableApp(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosApp app = this.posAppManager.get(Long.valueOf(request.getParameter("appId")));
			this.posAppManager.disableApp(app);
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
	 * 审核应用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void auditApp(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosApp app = this.posAppManager.get(Long.valueOf(request.getParameter("appId")));
			boolean pass = Boolean.valueOf(request.getParameter("pass"));
			if(!pass){
				app.setAuditInfo(request.getParameter("auditInfo"));
			}
			this.posAppManager.auditApp(app,pass);
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
	 * 城市绑定应用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void citySetApps(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Long cityId = Long.valueOf(request.getParameter("cityId"));
			String[] appIds = request.getParameterValues("appIds");
			City city = this.cityManager.get(cityId);
			this.posAppManager.citySetApps(city ,appIds);
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
	 * 商户绑定应用
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void merchantSetApps(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			String merchantCode = request.getParameter("merchantCode");
			String[] appIds = request.getParameterValues("appIds");
			if("".equals(appIds[0])){
				appIds = new String[0];
			}
			this.posAppManager.merchantSetApps(merchantCode, appIds);
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
	 * 应用绑定城市
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void appSetCities(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Long appId = Long.valueOf(request.getParameter("appId"));
			PosApp app = this.posAppManager.get(appId);
			String[] cityIds = request.getParameterValues("cityIds");
			this.posAppManager.appSetCities(app,cityIds);
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
	 * 应用绑定商户
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void appSetMerchants(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Long appId = Long.valueOf(request.getParameter("appId"));
			PosApp app = this.posAppManager.get(appId);
			String[] merchantCodes = request.getParameterValues("merchantCodes");
			this.posAppManager.appSetMerchants(app,merchantCodes);
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