package com.justinmobile.bmp.cloudboss.merchant.web;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantStatus;
import com.justinmobile.bmp.cloudboss.merchant.manager.MerchantInfoManager;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.bmp.util.CityUtil;
import com.justinmobile.bmp.util.DateUtils;
import com.justinmobile.bmp.util.ExcelUtils;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("merchantInfoController")
@RequestMapping("/mi.do")
public class MerchantInfoController extends BaseAjaxController {

	@Autowired
	private MerchantInfoManager merchantInfoManager;

	@Autowired
	private LogManager logManager;

	@SuppressWarnings("unused")
	@Autowired
	private CityUtil cityUtil;
	
	@Autowired
	private ExcelUtils excelUtils;

	private static final String SUCCESS = "success";

	private static final String DESCRIPTION = "msg";

	private static final String PAGEINFO = "pagination";

	private static final String PAGENO = "pagination.currentPage";

	private static final String PAGESIZE = "pagination.pageSize";

	private static final String PAGECOUNT = "count";

	private static final String MERCHANTJSONLIST = "merchants";

	private static final String MERCHANTJSON = "merchant";

	private static final String MERCHANTNAME = "merchantName";

	private static final String MERCHANTCODE = "code";

	/** 商户查询类型：审核页面（待审核状态） */
	private static final Integer[] TYPE_AUD = new Integer[] { MerchantStatus.STATUS_WAIT_AUDIT.getValue() };

	/** 商户查询类型：管理页面（默认显示有效状态、冻结状态、待审核状态以及审核不通过状态） */
	private static final Integer[] TYPE_MGR = new Integer[] { MerchantStatus.STATUS_ACTIVE.getValue(),
			MerchantStatus.STATUS_FREEZED.getValue(), MerchantStatus.STATUS_WAIT_AUDIT.getValue(),
			MerchantStatus.STATUS_AUDIT_FAIL.getValue() };

	/** 商户查询类型：下拉列表页面（有效状态和冻结状态） */
	private static final Integer[] TYPE_VALIDANDFREEZED = new Integer[] { MerchantStatus.STATUS_ACTIVE.getValue(),
			MerchantStatus.STATUS_FREEZED.getValue() };

	/** 商户查询类型：下拉列表页面（有效状态冻结状态和审核不通过状态） */
	private static final Integer[] TYPE_VFAF = new Integer[] { MerchantStatus.STATUS_ACTIVE.getValue(),
			MerchantStatus.STATUS_FREEZED.getValue(), MerchantStatus.STATUS_AUDIT_FAIL.getValue() };

	/** 商户查询类型：下拉列表页面（有效状态） */
	private static final Integer[] TYPE_VALID = new Integer[] { MerchantStatus.STATUS_ACTIVE.getValue() };

	/** 商户查询类型：审核不通过状态 */
	private static final Integer[] TYPE_AUD_FAIL = new Integer[] { MerchantStatus.STATUS_AUDIT_FAIL.getValue() };

	/** 商户查询类型：冻结状态 */
	private static final Integer[] TYPE_FREEZED = new Integer[] { MerchantStatus.STATUS_FREEZED.getValue() };

	/** 商户查询类型：注销状态 */
	private static final Integer[] TYPE_DISABLE = new Integer[] { MerchantStatus.STATUS_DISABLE.getValue() };

	/** 商户查询类型：所有状态 */
	private static final Integer[] TYPE_ALL = new Integer[] { MerchantStatus.STATUS_ACTIVE.getValue(),
			MerchantStatus.STATUS_FREEZED.getValue(), MerchantStatus.STATUS_WAIT_AUDIT.getValue(),
			MerchantStatus.STATUS_AUDIT_FAIL.getValue(), MerchantStatus.STATUS_DISABLE.getValue() };

	private static final String QUERYTYPE_AUD = "TYPE_AUD";

	private static final String QUERYTYPE_VALID = "TYPE_VALID";

	private static final String QUERYTYPE_AUD_FAIL = "TYPE_AUD_FAIL";

	private static final String QUERYTYPE_FREEZED = "TYPE_FREEZED";

	private static final String QUERYTYPE_DISABLE = "TYPE_DISABLE";

	private static final String QUERYTYPE_VALIDANDFREEZED = "TYPE_VALIDANDFREEZED";

	private static final String QUERYTYPE_VFAF = "TYPE_VFAF";

	private static final String QUERYTYPE_MGR = "TYPE_MGR";

	private static final String QUERYTYPE_ALL = "TYPE_ALL";

	private static final String NULLSTRING = "null";

	private static final String AUDITFLAG = "pass";

	/**
	 * 查询商户 列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			initializePagingSortingFiltering(request);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PAGENO, getPageNo());
			params.put(PAGESIZE, getPageSize());
			params.put(MERCHANTNAME, request.getParameter("merchantName"));
			params.put(MERCHANTCODE, request.getParameter("merchantCode"));
//			String [] cityIds = this.cityUtil.getCityArray(request.getParameter("cities"));
//			if(cityIds.length < 1){
//				throw new PlatformException(PlatformErrorCode.NO_CITY_ERROR);
//			}
//			params.put("cityIds",cityIds);
			String sortField = request.getParameter("sort");
			if("cityName".equals(sortField)){
				sortField = "area.name";
			}
			String dir = request.getParameter("dir");
			if (StringUtils.isNotBlank(dir)) {
				if (dir.equalsIgnoreCase("asc")) {
					params.put("sort1", sortField);
				} else if (dir.equalsIgnoreCase("desc")) {
					params.put("sort2", sortField);
				}
			}
			String queryType = request.getParameter("queryType");
			if (QUERYTYPE_AUD.equals(queryType)) {
				// 查询状态为待审核的商户
				params.put("status", TYPE_AUD);
			} else if (QUERYTYPE_MGR.equals(queryType)) {
				params.put("status", TYPE_MGR);
			} else if (QUERYTYPE_VALIDANDFREEZED.equals(queryType)) {
				params.put("status", TYPE_VALIDANDFREEZED);
			} else if (QUERYTYPE_VALID.equals(queryType)) {
				params.put("status", TYPE_VALID);
			} else if (QUERYTYPE_AUD_FAIL.equals(queryType)) {
				params.put("status", TYPE_AUD_FAIL);
			} else if (QUERYTYPE_FREEZED.equals(queryType)) {
				params.put("status", TYPE_FREEZED);
			} else if (QUERYTYPE_DISABLE.equals(queryType)) {
				params.put("status", TYPE_DISABLE);
			} else if (QUERYTYPE_ALL.equals(queryType)) {
				params.put("status", TYPE_ALL);
			} else if (QUERYTYPE_VFAF.equals(queryType)) {
				params.put("status", TYPE_VFAF);
			}

			JSONObject json = CloudBossCaller.HTTP.MERCHANT_LIST_READ.invoke(null, params);
			// System.out.println(json);
			if ("true".equals(json.getString(SUCCESS))) {
				String totalCount = json.getJSONObject(PAGEINFO).getString(PAGECOUNT);
				JSONArray jsonArray = new JSONArray();
				JSONArray temp = json.getJSONArray(MERCHANTJSONLIST);
				for (int i = 0; i < temp.size(); i++) {
					JSONObject jsonObject = temp.getJSONObject(i);
					jsonObject.put("status", jsonObject.getJSONObject("status").getString("desc"));
					String createDate = jsonObject.getString("createDate");
					if (!NULLSTRING.equals(createDate)) {
						jsonObject.put("createDate", DateUtils.format(DateUtils.parse(createDate)));
					}
					JSONObject cityObj = jsonObject.getJSONObject("area");
					if(!cityObj.isNullObject()){
						jsonObject.put("cityName", cityObj.getJSONObject("parent").getString("name"));
					}
					jsonArray.add(jsonObject);
				}
				String result = makeSuccessArrayString(totalCount, jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
		} catch (Exception e) {
			super.responseMessage(response, "商户列表查询失败", false);
			e.printStackTrace();
		}

	}

//	private void setCityParam(String cities, Map<String, Object> params) {
//		if(StringUtils.isNotBlank(cities)){
//			if(cities.indexOf(",") == -1){
//				params.put("area", cities);
//			}else{
//				params.put("area", cities.split(","));
//			}
//		}else{
//			SysUser user = this.sysUserManager.getUserByLoginName(SpringSecurityUtils.getCurrentUserName());
//			String[] cityArray = new String[user.getCitySet().size()];
//			int i = 0;
//			for(City city : user.getCitySet()){
//				cityArray[i] = city.getCityCode();
//				i++;
//			}
//			if(i > 0){
//				params.put("area", cityArray);
//			}
//		}
//		
//	}

	/**
	 * 显示商户详情
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void load(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			Long id = Long.parseLong(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_DETAIL_READ.invoke(null, params);
			if ("true".equals(json.getString(SUCCESS))) {
				super.responseMessage(response, this.formatJson(json).toString(), true);
			} else {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
			}

		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
		} catch (Exception e) {
			e.printStackTrace();
			super.responseMessage(response, "商户详情加载失败", false);
		}
	}

	private JSONObject formatJson(JSONObject json) throws Exception {
		// 检查是否丢失扩展信息
		if (NULLSTRING.equals(json.getString("extend"))) {
			throw new PlatformException(PlatformErrorCode.DATA_ERROR);
		}
		JSONObject obj = json.getJSONObject(MERCHANTJSON);
		obj.put("managerMobile", json.getString("managerMobile"));
		// 解析区域
		JSONArray areaArray = json.getJSONArray("areas");
		int areaNo = 0;
		for (Object o : areaArray) {
			JSONObject j = (JSONObject) o;
			if (areaNo == 1) {
				obj.put("province", j.getString("name"));
				obj.put("provinceValue", j.getString("id"));
			} else if (areaNo == 2) {
				obj.put("city", j.getString("name"));
				obj.put("cityValue", j.getString("id"));
			} else if (areaNo == 3) {
				obj.put("district", j.getString("name"));
				obj.put("districtValue", j.getString("id"));
			}
			areaNo++;
		}
		// 解析pos业务类型
		JSONArray posArray = json.getJSONArray("posBusInessType");
		int[] posTypeArray = new int[posArray.size()];
		for (int i = 0; i < posArray.size(); i++) {
			JSONObject posType = (JSONObject) posArray.get(i);
			int value = Integer.valueOf(posType.getString("value"));
			int index = (int) (Math.log(value) / Math.log(2));
			posTypeArray[i] = index;
		}
		obj.put("posType", posTypeArray);
		if (!NULLSTRING.equals(obj.getString("category"))) {
			JSONObject categoryObj = obj.getJSONObject("category");
			obj.put("category", categoryObj.getString("name"));
			obj.put("categoryValue", categoryObj.getString("id"));
		}
		if (StringUtils.isNotBlank(obj.getString("info"))) {
			obj.put("info", getTextAreaString(obj.getString("info")));
		}
		// if (StringUtils.isNotBlank(obj.getString("comments"))) {
		// obj.put("comments", getTextAreaString(obj.getString("comments")));
		// }
		JSONObject extend = json.getJSONObject("extend");
		// extend.put("presentSmsCount",obj.getJSONObject("merchantSmsAccount").getString("presentSmsCount"));
		String[] enumType = new String[] { "status" };
		String[] extendEnumType = new String[] { "businessLand", "businessLocation", "property", "payType", "regions",
				"transactionType" };
		for (String str : enumType) {
			String rawValue = obj.getJSONObject(str).getString("desc");
			String value = obj.getJSONObject(str).getString("value");
			obj.put(str, rawValue);
			obj.put(str + "Value", value);
		}
		if (!NULLSTRING.equals(obj.getString("parent"))) {
			obj.put("parentId", obj.getJSONObject("parent").getString("name"));
		}
		obj.put("childCount", json.getString("childCount"));
		String businessTime = obj.getString("businessTime");
		if (StringUtils.isNotBlank(businessTime)) {
			if (businessTime.indexOf("-") != -1) {
				String[] str = businessTime.split("-");
				obj.put("businessTime1", str[0]);
				obj.put("businessTime2", str[1]);
			} else {
				obj.put("businessTime1", businessTime);
			}
		}
		for (String str : extendEnumType) {
			if (NULLSTRING.equals(extend.getString(str))) {
				extend.remove(str);
				continue;
			}
			String rawValue = extend.getJSONObject(str).getString("desc");
			String value = extend.getJSONObject(str).getString("value");
			extend.put(str, rawValue);
			extend.put(str + "Value", value);
		}
		String signDate = extend.getString("signDate");
		if (!NULLSTRING.equals(signDate)) {
			extend.put("signDate", DateUtils.format(DateUtils.parse(signDate)));
		}
		obj.put("extend", extend);
		return obj;
	}

	private String getTextAreaString(String value) {
		String textAreaString = StringUtils.replace(value, "\n\r", "\\n\\r");
		textAreaString = StringUtils.replace(textAreaString, "\n", "\\n");
		textAreaString = StringUtils.replace(textAreaString, "\r", "\\r");
		return textAreaString;
	}

	/**
	 * 创建商户
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void create(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		// 检查商户编号、商户操作员手机号、短信编码是否存在
		String mobile = request.getParameter("managerMobile");
		String checkResult = codeAndMobileCheck(request.getParameter("num"), mobile);
		if (checkResult != null) {
			super.responseMessage(response, checkResult, false);
			return;
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, Object> params = getRequestParamsMap(multipartRequest);
			params = getOtherParams(multipartRequest, params);

			boolean useFlag = Boolean.valueOf(request.getParameter("useFlag"));
			MultipartFile multipartFile = multipartRequest.getFile("logo");
			File file = null;
			// 如果logo不为空，表示不使用父商户logo
			if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
				params.put("useParentLogo", "false");
				file = new File(getFilePath(request) + multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				// 如果logo为空且使用标志为使用，表示使用父商户logo
			} else if (useFlag == true) {
				if (StringUtils.isNotBlank(request.getParameter("parentId"))) {
					params.put("useParentLogo", "true");
				} else {
					params.put("useParentLogo", "false");
				}
				// 如logo为空且使用标志为不使用，表示不使用父商户logo
			} else if (useFlag == false) {
				params.put("useParentLogo", "false");
			}

			JSONObject json = CloudBossCaller.HTTP.MERCHANT_CREATE.invokeFile(null, params, file);
			super.responseMessage(response, json.getString(DESCRIPTION), true);
			try {
				if ("true".equals(json.getString(SUCCESS))) {
					logManager.insertLog("创建商户[编号：" + json.getString(MERCHANTCODE) + "]成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.responseMessage(response, "商户创建失败", false);
		}
	}

	// 封装request请求参数
	@SuppressWarnings("unchecked")
	private Map<String, Object> getRequestParamsMap(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
		String key = "";
		while (paramNames.hasMoreElements()) {
			key = paramNames.nextElement();
			// paramMap.put(key, request.getParameterValues(key));
			String paramValue = request.getParameter(key);
			if (StringUtils.isNotBlank(paramValue)) {
				paramMap.put(key, paramValue);
			}
		}
		paramMap.remove("method");
		return paramMap;
	}

	// 封装其他请求参数
	private Map<String, Object> getOtherParams(HttpServletRequest request, Map<String, Object> params) {
		// 获取营业时间
		String businessTime1 = request.getParameter("businessTime1");
		String businessTime2 = request.getParameter("businessTime2");
		if (StringUtils.isNotBlank(businessTime1)) {
			if (StringUtils.isNotBlank(businessTime2)) {
				params.put("businessTime", businessTime1 + "-" + businessTime2);
				params.remove("businessTime2");
			} else {
				params.put("businessTime", businessTime1);
			}
			params.remove("businessTime1");
		}
		// 获取POS机业务类型
		String posBusInessType = request.getParameter("posType");
		if (posBusInessType.indexOf(",") != -1) {
			String[] posTypeStr = posBusInessType.split(",");
			List<String> list = new ArrayList<String>();
			for (String str : posTypeStr) {
				if (!"0".equals(str)) {
					list.add(str);
				}
			}
			params.put("posBusInessType", list.toArray());
			params.remove("posType");
		}
		// 获取区域Id
		if (StringUtils.isNotBlank(request.getParameter("district"))) {
			params.put("areaId", request.getParameter("district"));
			return params;
		}
		if (StringUtils.isNotBlank(request.getParameter("city"))) {
			params.put("areaId", request.getParameter("city"));
			return params;
		}
		params.put("areaId", request.getParameter("province"));
		return params;
	}

	private String codeAndMobileCheck(String code, String mobile) {
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("num", code);
		Map<String, Object> mobileMap = new HashMap<String, Object>();
		mobileMap.put("mobile", mobile);
		try {
			JSONObject codeJson = CloudBossCaller.HTTP.MERCHANT_NUM_CHECK.invoke(null, codeMap);
			if ("true".equals(codeJson.getString(SUCCESS))) {
				return "短信编码已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			JSONObject mobileJson = CloudBossCaller.HTTP.MERCHANT_MOBILE_CHECK.invoke(null, mobileMap);
			if ("true".equals(mobileJson.getString(SUCCESS))) {
				return "商户管理员手机号已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改商户
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void update(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String merchantCode = request.getParameter(MERCHANTCODE);
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, Object> params = getRequestParamsMap(multipartRequest);
			params = getOtherParams(multipartRequest, params);
			params.remove(MERCHANTCODE);

			boolean useFlag = Boolean.valueOf(request.getParameter("useFlag"));
			MultipartFile multipartFile = multipartRequest.getFile("logo");
			File file = null;
			// 如果logo不为空，表示要替换logo
			if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
				params.put("changeLogo", "true");
				file = new File(getFilePath(request) + multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				// 如果logo为空且使用标志为不使用，则将原logo设空（有可能已为空）
			} else if (useFlag == false) {
				params.put("changeLogo", "true");
				// 如logo为空且使用标志为使用，表示原logo存在且不用改变
			} else if (useFlag == true) {
				params.put("changeLogo", "false");
			}
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_UPDATE.invokeFile(null, params, file);
			super.responseMessage(response, json.getString(DESCRIPTION), true);
			try {
				if ("true".equals(json.getString(SUCCESS))) {
					logManager.insertLog("修改商户[编号：" + merchantCode + "]成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.responseMessage(response, "商户修改失败", false);
		}

	}

	/**
	 * 商户冻结
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void suspend(HttpServletRequest request, HttpServletResponse response) {
		changeStatus(request, response, "suspend");
	}

	/**
	 * 商户解冻
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void recovery(HttpServletRequest request, HttpServletResponse response) {
		changeStatus(request, response, "recovery");
	}

	/**
	 * 商户注销
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		changeStatus(request, response, "remove");
	}

	private void changeStatus(HttpServletRequest request, HttpServletResponse response, String type) {
		response.setContentType("text/html;charset=UTF-8");
		Long id = Long.valueOf(request.getParameter("id"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("desc", request.getParameter("desc"));
		JSONObject json = null;
		boolean flag = false;
		String msg = "";
		String merchantCode = "";
		String operationDesc = "";
		try {
			if ("remove".equals(type)) {
				json = CloudBossCaller.HTTP.MERCHANT_DELETE.invoke(null, params);
				operationDesc = "注销";
			} else if ("suspend".equals(type)) {
				json = CloudBossCaller.HTTP.MERCHANT_FREEZE.invoke(null, params);
				operationDesc = "冻结";
			} else if ("recovery".equals(type)) {
				json = CloudBossCaller.HTTP.MERCHANT_UNFREEZE.invoke(null, params);
				operationDesc = "解冻";
			}
			flag = Boolean.valueOf(json.getString(SUCCESS));
			msg = json.getString(DESCRIPTION);
			if (flag) {
				merchantCode = json.getJSONObject(MERCHANTJSON).getString(MERCHANTCODE);
			}
		} catch (Exception e) {
			super.responseMessage(response, "操作失败", false);
			e.printStackTrace();
			return;
		}

		if (flag) {
			try {
				if (!merchantInfoManager.notifyPms(json)) {
					msg = msg + " 通知终端管理系统失败，稍后将自动通知,该商户编号为：" + merchantCode;
				}
			} catch (Exception e) {
				msg = msg + " 通知终端管理系统失败，需要手动通知,该商户编号为：" + merchantCode;
				e.printStackTrace();
			}
		}
		super.responseMessage(response, msg, flag);
		if (flag) {
			logManager.insertLog(operationDesc + "商户[编号：" + merchantCode + "]成功");
		}
	}

	/**
	 * 商户审核
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void aud(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> params = new HashMap<String, Object>();
		boolean pass = false;
		boolean flag = false;
		JSONObject json = null;
		StringBuilder msg = new StringBuilder();
		String merchantCode = "";
		try {
			pass = Boolean.valueOf(request.getParameter(AUDITFLAG));
			Long id = Long.valueOf(request.getParameter("id"));
			params.put("id", id);
			params.put("desc", request.getParameter("desc"));
			if (pass) {
				params.put(AUDITFLAG, true);
				json = CloudBossCaller.HTTP.MERCHANT_AUDIT.invoke(null, params);
				flag = Boolean.valueOf(json.getString(SUCCESS));
				if (flag) {
					merchantCode = json.getJSONObject(MERCHANTJSON).getString(MERCHANTCODE);
					msg.append("审核通过，商户编号为：");
					msg.append(merchantCode);
					msg.append("<br>云掌柜管理系统登录账号为：");
					msg.append(json.getString("mobile"));
					msg.append("<br>初始密码为：111111");
				} else {
					msg.append(json.getString(DESCRIPTION));
				}
			} else {
				params.put(AUDITFLAG, false);
				json = CloudBossCaller.HTTP.MERCHANT_AUDIT.invoke(null, params);
				flag = Boolean.valueOf(json.getString(SUCCESS));
				if (flag) {
					merchantCode = json.getJSONObject(MERCHANTJSON).getString(MERCHANTCODE);
				}
				msg.append(json.getString(DESCRIPTION));
			}
		} catch (Exception e) {
			super.responseMessage(response, "操作失败", false);
			e.printStackTrace();
			return;
		}

		// 提交审核通过且返回成功的情况，通知PMS创建商户
		if (pass && flag) {
			try {
				if (!merchantInfoManager.notifyPms(json)) {
					msg.append("<br>通知终端管理系统失败，稍后将自动通知");
				}
			} catch (Exception e) {
				msg.append("<br>通知终端管理系统失败，需要手动通知");
				e.printStackTrace();
			}
		}
		super.responseMessage(response, msg.toString(), flag);
		if (pass && flag) {
			logManager.insertLog("商户[编号：" + merchantCode + "]审核通过操作成功");
		} else if (!pass && flag) {
			logManager.insertLog("商户[编号：" + merchantCode + "]审核不通过操作成功");
		}
	}

	/**
	 * 检查商户编号是否已经存在
	 */
	@RequestMapping()
	public void checkCodeNew(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String merchantCode = request.getParameter("value");
			Pattern p = Pattern.compile("^[0-9]{15}$");
			Matcher m = p.matcher(merchantCode);
			if (!m.matches()) {
				super.responseCheckResult(response, "商户编号为15位数字", false);
				return;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(MERCHANTCODE, merchantCode);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_CODE_CHECK.invoke(null, params);
			if ("true".equals(json.getString(SUCCESS))) {
				super.responseCheckResult(response, "商户编号可以使用", true);
			} else {
				super.responseCheckResult(response, "商户编号已存在", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.responseCheckResult(response, "验证失败", false);
		}
	}

	/**
	 * 检查商户管理员手机号是否已经存在
	 */
	@RequestMapping()
	public void checkMobileNew(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String managerMobile = request.getParameter("value");
			Pattern p = Pattern.compile("^(13|15|18)\\d{9}$");
			Matcher m = p.matcher(managerMobile);
			if (!m.matches()) {
				super.responseCheckResult(response, "商户管理员手机号格式不正确", false);
				return;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mobile", managerMobile);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_MOBILE_CHECK.invoke(null, params);
			if ("true".equals(json.getString(SUCCESS))) {
				super.responseCheckResult(response, "商户管理员手机号可以使用", true);
			} else {
				super.responseCheckResult(response, "商户管理员手机号已存在", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.responseCheckResult(response, "验证失败", false);
		}
	}

	/**
	 * 加载区域和行业类别信息
	 */
	@RequestMapping()
	public void loadComboData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		try {
			JSONArray jsonArray = null;
			String combo = request.getParameter("combo");
			String param = request.getParameter("param");
			if ("area".equals(combo)) {
				paramsMap.put("id", param);
				JSONObject json = CloudBossCaller.HTTP.AREA_LIST.invoke(null, paramsMap);
				jsonArray = json.getJSONArray("childrens");
			} else if ("category".equals(combo)) {
				paramsMap.put("parentId", "0");
				JSONObject json = CloudBossCaller.HTTP.CATEGORIES_LIST.invoke(null, paramsMap);
				jsonArray = json.getJSONArray("categories");
			}
			String result = makeSuccessArrayString(String.valueOf(jsonArray.size()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (Exception e) {
			responseMessage(response, "下拉列表信息加载失败", false);
			e.printStackTrace();
		}
	}

	/**
	 * 获取可选父商户列表
	 */
	@RequestMapping()
	public void getParentList(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PAGENO, getPageNo());
			params.put(PAGESIZE, getPageSize());
			params.put(MERCHANTNAME, request.getParameter("merchantName"));
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_PARENT_READ.invoke(null, params);
			// System.out.println(json);
			if ("true".equals(json.getString(SUCCESS))) {
				String totalCount = json.getJSONObject(PAGEINFO).getString(PAGECOUNT);
				JSONArray jsonArray = new JSONArray();
				JSONArray temp = json.getJSONArray(MERCHANTJSONLIST);
				for (int i = 0; i < temp.size(); i++) {
					JSONObject jsonObject = temp.getJSONObject(i);
					JSONObject obj = new JSONObject();
					obj.put("id", jsonObject.get("id"));
					obj.put("name", jsonObject.get("name"));
					jsonArray.add(obj);
				}
				String result = makeSuccessArrayString(totalCount, jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.responseMessage(response, "商户列表加载失败", false);
		}
	}

	/**
	 * 查询商户列表（组合商户编号）
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping()
	public void listNameAndCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PAGENO, getPageNo());
			params.put(PAGESIZE, getPageSize());
			params.put("status", TYPE_VALIDANDFREEZED);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_LIST_READ.invoke(null, params);
			if ("true".equals(json.getString(SUCCESS))) {
				String totalCount = json.getJSONObject(PAGEINFO).getString(PAGECOUNT);
				JSONArray jsonArray = new JSONArray();
				JSONArray temp = json.getJSONArray(MERCHANTJSONLIST);
				for (int i = 0; i < temp.size(); i++) {
					JSONObject jsonObject = temp.getJSONObject(i);
					JSONObject obj = new JSONObject();
					obj.put("code", jsonObject.getString("code"));
					obj.put("name", "编号" + jsonObject.getString("code") + "[名称:" + jsonObject.getString("name") + "]");
					jsonArray.add(obj);
				}
				String result = makeSuccessArrayString(totalCount, jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
			}
		} catch (Exception e) {
			super.responseMessage(response, "商户列表查询失败", false);
			e.printStackTrace();
		}

	}

	/**
	 * 导出所有商户至excel文件
	 */
	@RequestMapping
	public void exp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String[] columnNams = { "商户中文名称|name", "商户中文名称简写|shortName", "英文名称|englishName", "商户编号|code",
				"隶属机构|parentOrganization", "隶属商户|parent", "区域信息|area", "行业类别|category", "是否是签约商户|signMerchant",
				"是否是银行卡收单商户|bankCardMerchant", "营业地址|businessAddress", "营业地址邮编|businessAddressCode", "经度|longitude",
				"纬度|latitude", "商户电话|merchantTelephone", "商户传真|merchantFax", "商户网站|merchantWeb", "联系人姓名|contactName",
				"联系电话|contactTelephone", "POS费率(百分比)|rating", "注册登记号|registNo", "税务登记号|taxNo",
				"机构代码证|organizationCard", "注册地址|registAddress", "法人代表|legal", "注册资本(万元)|registCapital",
				"营业用地面积(平米)|businessArea", "员工人数|employeesNumber", "经营范围(主业)|rangeMain", "经营范围(副业)|rangeSideline",
				"分支机构|branch", "前一年营业额(万元)|turnoverYear", "前一年利润(万元)|profitYear", "预计月平均银行卡营业额(万元)|turnoverBankCard",
				"预计每张签购单平均交易额(元)|signTrading", "银行商户编号|bankMerchantCode", "开户银行|merchantBank",
				"开户银行基本账户账号|accountSettlement", "对账单地址|billAddress", "对账单邮编|billZipcode", "对账单收件人|billPerson",
				"对账单邮件地址|billEmail", "对账单传真|billFax", "签约日期|signDate"

		};

		// String fileNameParam = "";
		try {
			String[] fileCondition = { "有效商户信息", "截止日期", DateUtils.format(new Date(), DateUtils.defaultDatePattern) };
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", TYPE_VALIDANDFREEZED);
			JSONObject json = CloudBossCaller.HTTP.MERCHANT_EXPORT.invoke(null, params);
			if ("true".equals(json.getString(SUCCESS))) {
				String fileName = excelUtils.toExcel(this.formatData(json), columnNams, fileCondition,
						getFilePath(request));
				responseMessage(response, fileName, true);
			} else {
				super.responseMessage(response, "查询信息失败", false);
			}
		} catch (Exception e) {
			responseMessage(response, "操作失败", false);
			e.printStackTrace();
		}
	}

	private List<List<String>> formatData(JSONObject json) {
		String[] columnParams = { "name", "shortName", "englishName", "code", "parentOrganization", "parent", "area",
				"category", "signMerchant", "bankCardMerchant", "businessAddress", "businessAddressCode", "longitude",
				"latitude", "merchantTelephone", "merchantFax", "merchantWeb", "contactName", "contactTelephone",
				"rating", "registNo", "taxNo", "organizationCard", "registAddress", "legal", "registCapital",
				"businessArea", "employeesNumber", "rangeMain", "rangeSideline", "branch", "turnoverYear",
				"profitYear", "turnoverBankCard", "signTrading", "bankMerchantCode", "merchantBank",
				"accountSettlement", "billAddress", "billZipcode", "billPerson", "billEmail", "billFax", "signDate" };
		List<List<String>> list = new ArrayList<List<String>>();
		JSONArray jsonArray = json.getJSONArray(MERCHANTJSONLIST);

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if (!NULLSTRING.equals(jsonObject.getString("parent"))) {
				jsonObject.put("parent", jsonObject.getJSONObject("parent").getString("name"));
			}
			if (!NULLSTRING.equals(jsonObject.getString("area"))) {
				jsonObject.put("area", jsonObject.getJSONObject("area").getString("address"));
			}
			if (!NULLSTRING.equals(jsonObject.getString("category"))) {
				jsonObject.put("category", jsonObject.getJSONObject("category").getString("name"));
			}
			JSONObject extend = jsonObject.getJSONObject("merchantExtendInfo");
			@SuppressWarnings("unchecked")
			Iterator<String> ite = extend.keys();
			while (ite.hasNext()) {
				String key = ite.next();
				jsonObject.put(key, extend.getString(key));
			}
			if (jsonObject.getString("signMerchant") == "false") {
				jsonObject.put("signMerchant", "否");
			} else if (jsonObject.getString("signMerchant") == "true") {
				jsonObject.put("signMerchant", "是");
			}
			if (jsonObject.getString("bankCardMerchant") == "false") {
				jsonObject.put("bankCardMerchant", "否");
			} else if (jsonObject.getString("bankCardMerchant") == "true") {
				jsonObject.put("bankCardMerchant", "是");
			}
			String signDate = jsonObject.getString("signDate");
			try {
				if (!NULLSTRING.equals(signDate)) {
					jsonObject.put("signDate", DateUtils.format(
							DateUtils.parse(signDate, DateUtils.defaultDatePattern), DateUtils.defaultDatePattern));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<String> tempList = new ArrayList<String>();
			for (String str : columnParams) {
				if (NULLSTRING.equals(jsonObject.getString(str))) {
					tempList.add("");
				} else {
					tempList.add(jsonObject.getString(str));
				}
			}
			list.add(tempList);
		}
		return list;
	}

	public String getFilePath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/") + "merchantFile" + File.separator;
	}

	@RequestMapping
	public void readSub(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			super.initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
			params.put("id", request.getParameter("id"));
			//
			JSONObject json = CloudBossCaller.HTTP.SUB_MERCHANT_LIST_READ.invoke(null, params);
			//
			if ("true".equals(json.getString("success"))) {
				String result = makeSuccessArrayString("" + json.getJSONArray(MERCHANTJSONLIST).size(), json
						.getJSONArray(MERCHANTJSONLIST).toString());
				response.getWriter().write(result);
			} else {
				responseMessage(response, json.getString("msg"), false);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseMessage(response, "系统错误<br />请检查配置后重试", false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "查询店铺信息列表失败", false);
		}
	}

}