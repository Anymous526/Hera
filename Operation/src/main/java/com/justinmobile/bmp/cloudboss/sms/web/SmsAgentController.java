package com.justinmobile.bmp.cloudboss.sms.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.cloudboss.sms.domain.SalePloyAgentInfo;
import com.justinmobile.bmp.cloudboss.sms.manager.SalePloyAgentInfoManager;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller
@RequestMapping("/sa.do")
public class SmsAgentController extends BaseAjaxController {

	@Autowired
	private LogManager logManager;
	
	@Autowired
	private SalePloyAgentInfoManager salePloyAgentInfoManager;
	
	@SuppressWarnings("unused")
	private static final String SEND_TYPE_AUTO = "1";

	private static final String SEND_TYPE_TIMING = "2";

	@SuppressWarnings("unused")
	private static final String SEND_TYPE_REG = "3";

	private static final String SEND_TYPE_CONSUME = "4";

	@RequestMapping()
	public void select(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String merchantName = request.getParameter("search_merchantName");
			if (merchantName != null && !"".equals(merchantName)) {
				params.put("merchantName", merchantName);
			}
			String merchantCode = request.getParameter("search_merchantCode");
			if (merchantCode != null && !"".equals(merchantCode)) {
				params.put("merchantCode", merchantCode);
			}
			String status = request.getParameter("status");
			if (status != null && !"".equals(status) && !"ALL".equals(status)) {
				params.put("status", status);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			//
			JSONObject result = CloudBossCaller.HTTP.SMS_READ.invoke(null,
					params);
			//
			JSONObject pagination = result.getJSONObject("pagination");
			//
			if (result.getBoolean("success")) {
				JSONArray ja = result.getJSONArray("salePloys");
				JSONArray newJA = new JSONArray();
				for( Object obj : ja.toArray() ){
					JSONObject json = (JSONObject)obj;
					Long salePloyId = Long.valueOf(json.getString("id"));
					SalePloyAgentInfo salePloyAgentInfo = salePloyAgentInfoManager.getById(salePloyId);
					if( !(salePloyAgentInfo == null) ){
						json.put("signer", salePloyAgentInfo.getSigner());
						json.put("oper", salePloyAgentInfo.getOper());
					}else{
						json.put("signer", "");
						json.put("oper", "");
					}
					newJA.add(json);
				}
				response.getWriter().write(
						makeSuccessArrayString(pagination.getString("count"), newJA.toString()));
			} else {
				response.getWriter().write(makeSuccessArrayString("0", "{}"));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询商户营销活动列表失败", false);
		}

	}

	@RequestMapping()
	public void append(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String id = request.getParameter("id");
			String name = request.getParameter("title");
			String template = request.getParameter("content");
			if (id == null || name == null || template == null) {
				throw new Exception();
			}
			params.put("id", id);
			params.put("name", name);
			params.put("template", template);
			//
			String[] memberLevel = request.getParameterValues("level");
			if (memberLevel != null) {
				if (memberLevel.length > 0) {
					Vector<Integer> levels = new Vector<Integer>();
					for (int i = 0; i < memberLevel.length; i++) {
						if (Boolean.valueOf(memberLevel[i])) {
							levels.add(i + 1);
						}
					}
					if (levels.size() > 0) {
						params.put("memberLevel", levels.toArray());
					}
				}
			}
			//
			String minPoint = request.getParameter("pointMin");
			if (minPoint != null && !"".equals(minPoint)) {
				params.put("minPoint", minPoint);
			}
			//
			String maxPoint = request.getParameter("pointMax");
			if (maxPoint != null && !"".equals(maxPoint)) {
				params.put("maxPoint", maxPoint);
			}
			//
			String activeRate = request.getParameter("active");
			if (activeRate != null && !"".equals(activeRate)) {
				params.put("activeRate", activeRate);
			}
			//
			String salePloyType = request.getParameter("type");
			params.put("salePloyType", salePloyType);
			if (SEND_TYPE_TIMING.equals(salePloyType)) {
				String date = request.getParameter("date");
				String time = request.getParameter("time");
				params.put("timingTime", date.substring(0, 10) + " " + time
						+ ":00:00");
			} else if (SEND_TYPE_CONSUME.equals(salePloyType)) {
				String tradeMinMoney = request.getParameter("amount");
				params.put("tradeMinMoney", tradeMinMoney);
			}
			//
			String validDateStart = request.getParameter("validDateStart");
			if (validDateStart != null && !"".equals(validDateStart)) {
				params.put("startDate", validDateStart.substring(0, 10) + " 00:00:00");
			}
			String validDateEnd = request.getParameter("validDateEnd");
			if (validDateEnd != null && !"".equals(validDateEnd)) {
				params.put("endDate", validDateEnd.substring(0, 10) + " 00:00:00");
			}
			//
			String[] applyMerchants = request.getParameterValues("subMerchantIds");
			String applyMerchant = "";
			for(String mName : applyMerchants){
				if( mName != null && !"".equals(mName) ){
					if( "".equals(applyMerchant) ){
						applyMerchant = mName;
					}else{
						applyMerchant = applyMerchant + "," + mName;
					}
				}
			}
			if( "".equals(applyMerchant) ){
				params.put("applyMerchant", id);
			}else{
				params.put("applyMerchant", applyMerchant);
			}
			//
			JSONObject result = CloudBossCaller.HTTP.SMS_CREATE.invoke(null,
					params);
			//
			if (result.getBoolean("success")) {
				responseMessage(response, "操作成功", true);
				if( result.getString("id") != null ){
					Long salePloyId = Long.valueOf(result.getString("id"));
					String signer = request.getParameter("signer");
					salePloyAgentInfoManager.create(salePloyId, signer);
				}
				logManager.insertLog("代发营销短信录入：" + name);
			} else {
				responseMessage(response, "操作失败：" + result.getString("msg"),
						false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "代发营销短信失败", false);
		}
	}

	@RequestMapping()
	public void audit(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String id = request.getParameter("id");
			String pass = request.getParameter("isPass");
			if (id == null || pass == null) {
				throw new Exception();
			}
			params.put("id", id);
			if (Boolean.valueOf(pass)) {
				params.put("pass", 1);
			} else {
				String desc = request.getParameter("comment");
				if (desc != null && !("".equals(desc))) {
					params.put("desc", desc);
				}
				params.put("pass", 0);
			}
			//
			JSONObject result = CloudBossCaller.HTTP.SMS_AUDIT.invoke(null,
					params);
			//
			if (result.getBoolean("success")) {
				responseMessage(response, "操作成功", true);
				if(Boolean.valueOf(pass)){
					logManager.insertLog("审核通过营销短信，id：" + id);
				}else{
					logManager.insertLog("审核不通过营销短信，id：" + id);
				}
				
			} else {
				responseMessage(response, "操作失败" + result.getString("msg"),
						true);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "审核营销活动失败", false);
		}
	}
	
	@RequestMapping()
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String id = request.getParameter("id");
			params.put("id", id);
			//
			JSONObject result = CloudBossCaller.HTTP.SMS_DELETE.invoke(null,
					params);
			//
			if (result.getBoolean("success")) {
				responseMessage(response, "操作成功", true);
				logManager.insertLog("注销营销短信，id：" + id);
			} else {
				responseMessage(response, "操作失败" + result.getString("msg"),
						true);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "注销营销活动失败", false);
		}
	}
}
