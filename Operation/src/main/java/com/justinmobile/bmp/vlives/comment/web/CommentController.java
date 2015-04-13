package com.justinmobile.bmp.vlives.comment.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.vlives.caller.VLivesCaller;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("commentController")
@RequestMapping("/c.do")
public class CommentController extends BaseAjaxController{
	
	@RequestMapping()
	public void readu(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			//
			String merchantName = request.getParameter("search_merchantName");
			String merchantCode = request.getParameter("search_merchantCode");
			String mobile = request.getParameter("search_mobile");
			String level = request.getParameter("search_level");
			String startDate = request.getParameter("search_startDate");
			String endDate = request.getParameter("search_endDate");
			//
			Map<String, Object> params = new HashMap<String, Object>();
			if( merchantName != null ){
				params.put("merchantName", merchantName);
			}
			if( merchantCode != null ){
				params.put("code", merchantCode);
			}
			if( mobile != null ){
				params.put("mobile", mobile);
			}
			if( level != null ){
				if( "ALL".equals(level) ){
					params.put("level", "0");
				}else{
					params.put("level", level);
				}
			}
			if( startDate != null ){
				params.put("startDate", startDate);
			}
			if( endDate != null ){
				params.put("endDate", endDate);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			//
			JSONObject json = VLivesCaller.HTTP.USER_COMMENTS_READ.invoke(null, params);
			//
			if("true".equals(json.getString("success"))){
				String result = makeSuccessArrayString(json.getJSONObject("pagination").getString("count"), json.getJSONArray("comments").toString());
				response.getWriter().write(result);
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (PlatformException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询公告信息列表失败", false);
		}
		
	}
	
	@RequestMapping()
	public void readm(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			//
			String merchantName = request.getParameter("search_merchantName");
			String merchantCode = request.getParameter("search_merchantCode");
			String mobile = request.getParameter("search_mobile");
			//
			Map<String, Object> params = new HashMap<String, Object>();
			if( merchantName != null ){
				params.put("merchantName", merchantName);
			}
			if( merchantCode != null ){
				params.put("merchantCode", merchantCode);
			}
			if( mobile != null ){
				params.put("mobile", mobile);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			//
			JSONObject json = VLivesCaller.HTTP.MERCHANT_COMMENTS_READ.invoke(null, params);
			//
			if("true".equals(json.getString("success"))){
				String result = makeSuccessArrayString(json.getJSONObject("pagination").getString("count"), json.getJSONArray("merchants").toString());
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
}
