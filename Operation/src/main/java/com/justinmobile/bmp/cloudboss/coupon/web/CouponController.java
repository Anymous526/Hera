package com.justinmobile.bmp.cloudboss.coupon.web;

import java.util.HashMap;
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
import com.justinmobile.bmp.cloudboss.coupon.domain.CouponPloyStatus;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.util.CityUtil;
import com.justinmobile.bmp.util.DateUtils;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("couponController")
@RequestMapping("/coupon.do")
public class CouponController extends BaseAjaxController{
	
	@Autowired
	private CityUtil cityUtil;
	
	/**
	 * 电子券查询
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void list(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("pagination.currentPage", getPageNo());
			params.put("pagination.pageSize", getPageSize());
			params.put("merchantName", request.getParameter("merchantName"));
			params.put("merchantCode", request.getParameter("merchantCode"));
			String queryType = request.getParameter("queryType");
			if("TYPE_AUD".equals(queryType)){
				params.put("couponPloyStatus", new Integer[]{CouponPloyStatus.WAIT_AUDIT.getValue()});
			}else if("TYPE_STATISTICS".equals(queryType)){
				params.put("couponPloyStatus", new Integer[]{CouponPloyStatus.AUDIT_SUCCESS.getValue(),CouponPloyStatus.PAUSE.getValue(),CouponPloyStatus.COMPLETE.getValue()});
			}else if(!"".equals(queryType) && queryType != null){
				params.put("couponPloyStatus", new Integer[]{CouponPloyStatus.get(Integer.valueOf(queryType)).getValue()});
			}
			params.put("couponType", request.getParameter("couponType"));
			String [] cityIds = this.cityUtil.getCityArray(request.getParameter("cities"));
			if(cityIds.length < 1){
				throw new PlatformException(PlatformErrorCode.NO_CITY_ERROR);
			}
			params.put("cityIds",cityIds);
			params.put("createStartDate", request.getParameter("createStartDate"));
			params.put("createEndDate", request.getParameter("createEndDate"));
			JSONObject json = CloudBossCaller.HTTP.COUPON_LIST.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				String totalCount = json.getJSONObject("pagination").getString("count");
				JSONArray jsonArray = json.getJSONArray("couponPloies");
				String result = makeSuccessArrayString(totalCount, jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString("msg"), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 电子券详情
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void load(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", request.getParameter("id"));
			JSONObject json = CloudBossCaller.HTTP.COUPON_DETAIL.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				JSONObject obj = json.getJSONObject("couponPloy");
				String createDate = obj.getString("createDate");
				if(StringUtils.isNotBlank(createDate) && !"null".equals(createDate)){
					obj.put("createDate", DateUtils.format(DateUtils.parse(createDate), DateUtils.defaultDatePattern));
				}
				String validStartDate = obj.getString("validStartDate");
				if(StringUtils.isNotBlank(validStartDate) && !"null".equals(validStartDate)){
					obj.put("validStartDate", DateUtils.format(DateUtils.parse(validStartDate), DateUtils.defaultDatePattern));
				}
				String validEndDate = obj.getString("validEndDate");
				if(StringUtils.isNotBlank(validEndDate) && !"null".equals(validEndDate)){
					obj.put("validEndDate", DateUtils.format(DateUtils.parse(validEndDate), DateUtils.defaultDatePattern));
				}
				super.responseMessage(response, obj.toString(), true);
			} else {
				super.responseMessage(response, json.getString("msg"), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 电子券审核
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void aud(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", request.getParameter("id"));
			boolean auditPass = Boolean.valueOf(request.getParameter("auditPass"));
			params.put("auditPass", auditPass);
			String desc = request.getParameter("desc");
			if(!auditPass){
				if(StringUtils.isBlank(desc)){
					throw new PlatformException(PlatformErrorCode.PARAM_ERROR);
				}
			}
			params.put("desc", desc);
			JSONObject json = CloudBossCaller.HTTP.COUPON_AUDIT.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
			}else{
				super.responseMessage(response, json.getString("msg"), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
}
