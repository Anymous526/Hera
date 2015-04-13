package com.justinmobile.bmp.cloudboss.industry.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.core.web.BaseAjaxController;


@Controller("industryController")
@RequestMapping("/ind.do")
public class IndustryController extends BaseAjaxController {

	@Autowired
	private LogManager logManager;

	/**
	 * 行业信息列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void listIndustry(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			String id = request.getParameter("id");
			params.put("parentId", id);

			JSONObject json = CloudBossCaller.HTTP.MERCHANTCATEGORY_LIST
					.invoke(null, params);
			System.out.println("json:" + json);
			String result = "";
			if ("true".equals(json.getString("success"))) {
				JSONArray ja = json.getJSONArray("categories");
				List l = (List) JSONArray.toCollection(ja, Industry.class);
				result = "[";
				for (int i = 0; i < l.size(); i++) {
					result += "{id:" + ((Industry) l.get(i)).getId()
							+ ",text:'" + ((Industry) l.get(i)).getName()
							+ "',leaf:false,singleClickExpand:true}";
					if (i != l.size() - 1) {
						result += ",";
					}
				}
				result += "]";

				response.getWriter().write(result);
			} else {
				responseMessage(response, json.getString("msg"), false);
			}

			// String result =
			// "[{id:1,text:1111,leaf:false,singleClickExpand:true},{id:2,text:222,leaf:false,singleClickExpand:true}]";
			//response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "行业查询失败！", false);
		}

	}

	/**
	 * 新增行业
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void createIndustry(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", request.getParameter("name"));
			params.put("parentId", request.getParameter("parentId"));
			JSONObject json = CloudBossCaller.HTTP.MERCHANTCATEGORY_CREATE
					.invoke(null, params);
			
			if ("true".equals(json.getString("success"))) {
				String result = json.getString("msg");
				response.getWriter().write("<script type=\"text/javascript\"> this.parent.FormEditWin.reloadNavNode();window.parent.FormEditWin.close();    </script>");
			} else {
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "新增行业失败！", false);
		}

	}

	/**
	 * 删除行业（包括子行业）
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void removeNode(HttpServletRequest request,

	HttpServletResponse response) {
		System.out.println("删除节点");
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", request.getParameter("id"));
			JSONObject json = CloudBossCaller.HTTP.MERCHANTCATEGORY_DELETE
					.invoke(null, params);
			// System.out.println(json);
			if ("true".equals(json.getString("success"))) {
				String result = makeSuccessArrayString(
						json.getJSONObject("pagination").getString("count"),
						json.getJSONArray("illegalWords").toString());
				response.getWriter().write(result);
			} else {
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "行业查询失败！", false);
		}

	}
	
	
	


	@RequestMapping()
	public void industryedit(HttpServletRequest request,

	HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();

			String parentId = request.getParameter("parentId");
			Industry obj = new Industry();
			obj.setParentId(Integer.valueOf(parentId));
			request.setAttribute("obj", obj);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/view/cloudboss/splitmanager/edit.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "行业查询失败！", false);
		}

	}

}
