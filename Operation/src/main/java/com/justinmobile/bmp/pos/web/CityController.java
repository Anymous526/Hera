package com.justinmobile.bmp.pos.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.pos.domain.City;
import com.justinmobile.bmp.pos.domain.PosApp;
import com.justinmobile.bmp.pos.manager.CityManager;
import com.justinmobile.bmp.pos.manager.PosAppManager;
import com.justinmobile.bmp.util.SpringSecurityUtils;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.web.BaseAjaxController;
import com.justinmobile.security.domain.SysUser;
import com.justinmobile.security.manager.SysUserManager;

@Controller("cityController")
@RequestMapping("/city.do")
public class CityController extends BaseAjaxController{

	@Autowired
	private CityManager cityManager;
	
	@Autowired
	private PosAppManager posAppManager;
	
	@Autowired
	private SysUserManager sysUserManager;
	
	/**
	 * 城市列表分页查询
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getOpenCities(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			super.initializePagingSortingFiltering(request);
			super.filterMap.put("status", 1);
			Page<City> page = this.cityManager.pagedQuery(super.getPageNo(), super.getPageSize(), super.sort,filterMap);
			List<City> list = page.getResult();
			JSONArray jsonArray = new JSONArray();
			for(City city : list){
				JSONObject json = city.toJson();
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
	 * 应用适用城市列表（不分页）
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void listCitiesByApp(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PosApp app = this.posAppManager.get(Long.parseLong(request.getParameter("appId")));
			List<City> allCity = this.cityManager.getOpenCities();
			JSONArray jsonArray = new JSONArray();
			for(City city : allCity){
				JSONObject json = city.toJson();
				for(City appCity : app.getCitySet()){
					if(city.getCityCode() == appCity.getCityCode()){
						json.put("flag", true);
					}
				}
				jsonArray.add(json);
			}
			String result = super.makeSuccessArrayString(String.valueOf(allCity.size()), jsonArray.toString());
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
	 * 城市应用列表（不分页）
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void listAppsByCity(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			City city = this.cityManager.get(Long.valueOf(request.getParameter("cityId")));
			List<PosApp> allApp = this.posAppManager.getValidApps();
			JSONArray jsonArray = new JSONArray();
			for(PosApp app : allApp){
				JSONObject json = app.toJson();
				for(PosApp cityApp : city.getAppSet()){
					if(app.getId() == cityApp.getId()){
						json.put("flag", true);
					}
				}
				jsonArray.add(json);
			}
			String result = super.makeSuccessArrayString(String.valueOf(allApp.size()), jsonArray.toString());
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
	 * 用户配置城市列表（不分页）
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void listCitiesByUser(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Set<City> optionalCities = null;
			String userName = SpringSecurityUtils.getCurrentUserName();
			if("admin".equals(userName)){
				optionalCities = new HashSet<City>(this.cityManager.getOpenCities());
			}else{
				SysUser currentUser = this.sysUserManager.getUserByLoginName(userName);
				optionalCities = currentUser.getCitySet();
			}
			SysUser selectedUser = this.sysUserManager.get(Long.valueOf(request.getParameter("userId")));
			Set<City> selectedCities = selectedUser.getCitySet();
			JSONArray jsonArray = new JSONArray();
			for(City city : optionalCities){
				JSONObject json = city.toJson();
				for(City selectedCity : selectedCities){
					if(city.getCityCode() == selectedCity.getCityCode()){
						json.put("flag", true);
					}
				}
				jsonArray.add(json);
			}
			String result = super.makeSuccessArrayString(String.valueOf(optionalCities.size()), jsonArray.toString());
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
	 * 页面下拉框根据当前用户加载可选城市
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getCityStore(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try{
			String userName = SpringSecurityUtils.getCurrentUserName();
			SysUser user = this.sysUserManager.getUserByLoginName(userName);
			Set<City> citySet = user.getCitySet();
			if(citySet.size() == 0){
				throw new PlatformException(PlatformErrorCode.NO_CITY_ERROR);
			}
			JSONArray jsonArray = new JSONArray();
			for(City city : citySet){
				JSONObject json = city.toJson();
				jsonArray.add(json);
			}
			String result = super.makeSuccessArrayString(String.valueOf(citySet.size()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
}
