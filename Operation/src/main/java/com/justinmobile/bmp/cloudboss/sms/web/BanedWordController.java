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

@Controller("banedWordController")
@RequestMapping("/bw.do")
public class BanedWordController extends BaseAjaxController{
	
	@Autowired
	private LogManager logManager;
	
	@RequestMapping()
	public void select(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			params.put("content", request.getParameter("content"));
			JSONObject json = CloudBossCaller.HTTP.BANED_WORD_READ.invoke(null, params);
//			System.out.println(json);		
			if("true".equals(json.getString("success"))){
				String result = makeSuccessArrayString(json.getJSONObject("pagination").getString("count"), json.getJSONArray("illegalWords").toString());
				response.getWriter().write(result);
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "违禁字查询失败！", false);
		}
		
	}
	
	@RequestMapping()
	public void append(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("content", request.getParameter("content"));
			JSONObject json = CloudBossCaller.HTTP.BANED_WORD_CREATE.invoke(null, params);
			response.getWriter().write(json.toString());
			logManager.insertLog("违禁字添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "违禁字添加失败！", false);
		}
	}
	
	@RequestMapping()
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", request.getParameterValues("id"));
			JSONObject json = CloudBossCaller.HTTP.BANED_WORD_DELETE.invoke(null, params);
			response.getWriter().write(json.toString());
			logManager.insertLog("违禁字删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "违禁字删除失败！", false);
		}
	}
}
