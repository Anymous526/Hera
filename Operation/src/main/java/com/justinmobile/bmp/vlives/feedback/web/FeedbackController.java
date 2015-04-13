package com.justinmobile.bmp.vlives.feedback.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.vlives.caller.VLivesCaller;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("feedbackController")
@RequestMapping("/fb.do")
public class FeedbackController extends BaseAjaxController{

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
			JSONObject json = VLivesCaller.HTTP.USER_FEEDBACK_READ.invoke(null, params);
			//
			JSONArray ja = new JSONArray();
			for(Object obj : json.getJSONArray("list").toArray()){
				JSONObject jsObj = (JSONObject)obj;
				JSONObject jt = (JSONObject)jsObj.get("user");
				String str = jsObj.getString("user");
				if( jt == null || "null".equals(str) ){
					jsObj.remove("user");
					JSONObject jn = new JSONObject();
					jn.put("name", "匿名");
					jn.put("mobile", "");
					jsObj.put("user", jn);
					ja.add(jsObj);
				}else{
					str = jt.getString("name");
					if( str == null || "null".equals(str) ){
						jt.put("name", "匿名");
					}
					ja.add(jsObj);
				}
			}
			//
			if("true".equals(json.getString("success"))){
				String result = makeSuccessArrayString(json.getJSONObject("pagination").getString("count"), ja.toString());
				response.getWriter().write(result);
			}else{
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询反馈信息列表失败", false);
		}
		
	}
}
