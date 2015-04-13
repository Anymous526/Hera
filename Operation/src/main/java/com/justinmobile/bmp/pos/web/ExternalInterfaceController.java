package com.justinmobile.bmp.pos.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.pos.domain.PosAppVersion;
import com.justinmobile.bmp.pos.manager.PosAppVersionManager;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("externalInterfaceController")
@RequestMapping("/externalInterface.do")
public class ExternalInterfaceController extends BaseAjaxController{

	@Autowired
	private PosAppVersionManager posAppVersionManager;
	
	/**
	 * 查询高阳会生活当前有效版本（仅包含应用 编号和应用版本号，对云掌柜）
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void getValidVersions(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		JSONObject obj = new JSONObject();
		try {
			List<PosAppVersion> list = this.posAppVersionManager.getValidVersionsByLocation(0);
			obj.put("success", true);
			JSONArray jsonArray = new JSONArray();
			for(PosAppVersion version : list){
				JSONObject temp = new JSONObject();
				temp.put("version", version.getVersion());
				temp.put("code", version.getPosApp().getCode());
				jsonArray.add(temp);
			}
			obj.put("data", jsonArray);
		} catch (Exception e) {
			obj.put("success", false);
			e.printStackTrace();
		} finally {
			try {
				response.getWriter().write(obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
