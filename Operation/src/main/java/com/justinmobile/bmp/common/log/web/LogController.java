package com.justinmobile.bmp.common.log.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.common.log.domain.OperationLog;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("logController")
@RequestMapping("/bmpLog.do")
public class LogController extends BaseAjaxController{
	@Autowired
	private LogManager logManager;
	
	@RequestMapping()
	public void listLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			initializePagingSortingFiltering(request);// 初始化页面列表翻页、排序、过滤条件的参数
			Page<OperationLog> page = null;
			if(sort.get("opDateTime")!=null){
				sort.put("opTime", sort.get("opDateTime"));
				sort.remove("opDateTime");
			}
			if(sort.size()==0)
				sort.put("opTime", "false");
			page  = logManager.pagedQueryBySimpleHsql(getPageNo(), getPageSize(),sort, filterMap);
			List<OperationLog> resList = page.getResult();
			JSONArray jsonArray = getLogJSONArray(resList);
			String result = makeSuccessArrayString(String.valueOf(page.getTotalCount()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询失败:"+e.getMessage(),false);
		}
	}
	
	private JSONArray getLogJSONArray(List<OperationLog> list) {
		JSONArray jsonArray = new JSONArray();
		for (OperationLog merchant : list) {
			JSONObject jsonObject = getJson(merchant);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
