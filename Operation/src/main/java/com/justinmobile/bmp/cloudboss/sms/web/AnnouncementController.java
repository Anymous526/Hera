package com.justinmobile.bmp.cloudboss.sms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("announcementController")
@RequestMapping("/a.do")
public class AnnouncementController extends BaseAjaxController{
	
	@Autowired
	private LogManager logManager;
	
	@RequestMapping()
	public void select(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			//
			JSONObject json = CloudBossCaller.HTTP.ANNOUNCEMENT_READ.invoke(null, params);
			//
			if("true".equals(json.getString("success"))){
				String result = makeSuccessArrayString(json.getJSONObject("pagination").getString("count"), json.getJSONArray("borders").toString());
				response.getWriter().write(result);
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询公告信息列表失败", false);
		}
		
	}
	
	@RequestMapping()
	public void append(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			//
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", request.getParameter("title"));
			params.put("content", request.getParameter("content"));
			params.put("type", request.getParameter("type"));
			//
			JSONObject json = CloudBossCaller.HTTP.ANNOUNCEMENT_CREATE.invoke(null, params);
			//
			if("true".equals(json.getString("success"))){
				responseMessage(response, "操作成功", true);
				logManager.insertLog("添加公告：" + request.getParameter("title"));
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "添加公告失败", false);
		}
	}
	
	@RequestMapping()
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			//
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", request.getParameter("id"));
			//
			JSONObject json = CloudBossCaller.HTTP.ANNOUNCEMENT_DELETE.invoke(null, params);
			//
			if("true".equals(json.getString("success"))){
				responseMessage(response, "操作成功", true);
				logManager.insertLog("删除公告，id：" + request.getParameter("id"));
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "删除公告失败", false);
		}
	}
}
