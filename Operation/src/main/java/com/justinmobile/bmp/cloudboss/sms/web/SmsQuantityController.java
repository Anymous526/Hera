package com.justinmobile.bmp.cloudboss.sms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller
@RequestMapping("/sq.do")
public class SmsQuantityController extends BaseAjaxController {
	
	private static final Integer ALERT_QUANTITY = 20000;

	@Autowired
	private LogManager logManager;
	
	@RequestMapping()
	public void select(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String merchantName = request.getParameter("search_merchantName");
			String merchantCode = request.getParameter("search_merchantCode");
			if( merchantName != null ){
				params.put("merchantName", merchantName);
			}
			if( merchantCode != null ){
				params.put("code", merchantCode);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			//
			JSONObject result = CloudBossCaller.HTTP.MERCHANT_LIST_READ.invoke(null, params);
			//
			if( result.getBoolean("success") ){
				response.getWriter().write(
						makeSuccessArrayString(result.getJSONObject("pagination").getString("count"), result.getJSONArray("merchants").toString())
				);
			}
			else{
				throw new Exception("查询商户短信数量列表失败");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, e.getMessage(), false);
		}
		
	}
	
	@RequestMapping()
	public void append(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("merchantCode", request.getParameter("code"));
			params.put("count", request.getParameter("quantity"));
			params.put("type", request.getParameter("type"));
			//
			JSONObject json = CloudBossCaller.HTTP.SMS_QUANTITY_ADD.invoke(null, params);
			//
			Boolean isSuccess = json.getBoolean("success");
			//
			if( isSuccess ){
				responseMessage(response, "操作成功", true);
				logManager.insertLog("为商户[" + request.getParameter("code") + "]添加短信" + request.getParameter("quantity") + "条");
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseMessage(response, "短信数量添加失败！", false);
		} catch (IOException e) {
			e.printStackTrace();
			responseMessage(response, "短信数量添加失败！", false);
		}
	}

	@RequestMapping()
	public void readCount(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject json = CloudBossCaller.HTTP.SMS_ALL_QUANTITY_READ.invoke(null, null);
			json.put("alertQuantity", ALERT_QUANTITY);
			response.getWriter().write( json.toString() );
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseMessage(response, "", false);
		} catch (IOException e) {
			e.printStackTrace();
			responseMessage(response, "", false);
		}
	}
}
