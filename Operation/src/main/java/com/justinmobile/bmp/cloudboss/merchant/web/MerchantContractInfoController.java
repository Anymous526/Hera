package com.justinmobile.bmp.cloudboss.merchant.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.justinmobile.bmp.cloudboss.merchant.domain.MerchantContractInfo;
import com.justinmobile.bmp.cloudboss.merchant.manager.MerchantContractInfoManager;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.bmp.util.CalendarUtils;
import com.justinmobile.core.dao.support.Page;
import com.justinmobile.core.exception.BusinessException;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("merchantContractInfoController")
@RequestMapping("/mc.do")
public class MerchantContractInfoController extends BaseAjaxController {

	@Autowired
	private MerchantContractInfoManager merchantContractInfoManager;

	@Autowired
	private LogManager logManager;
	
	@RequestMapping
	public void list(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			super.initializePagingSortingFiltering(request);
			Page<MerchantContractInfo> page = this.merchantContractInfoManager.pagedQuery(super.getPageNo(), super.getPageSize(), super.sort,super.filterMap);
			List<MerchantContractInfo> list = page.getResult();
			JSONArray jsonArray = getJsonArray(list);
			String result = super.makeSuccessArrayString(String.valueOf(page.getTotalCount()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (BusinessException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}

	private JSONArray getJsonArray(List<MerchantContractInfo> list) {
		JSONArray jsonArray = new JSONArray();
		for(MerchantContractInfo info : list){
			JSONObject json = info.toJson();
			if(info.getSignDate() != null){
				json.put("signDate", CalendarUtils.fomatCalendar(info.getSignDate(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			if(info.getValidDateBegin() != null){
				json.put("validDateBegin", CalendarUtils.fomatCalendar(info.getValidDateBegin(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			if(info.getValidDateEnd() != null){
				json.put("validDateEnd", CalendarUtils.fomatCalendar(info.getValidDateEnd(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	@RequestMapping
	public void load(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			MerchantContractInfo merchantContractInfo = this.merchantContractInfoManager.get(id);
			JSONObject json = merchantContractInfo.toJson();
			if(merchantContractInfo.getSignDate() != null){
				json.put("signDate", CalendarUtils.fomatCalendar(merchantContractInfo.getSignDate(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			if(merchantContractInfo.getValidDateBegin() != null){
				json.put("validDateBegin", CalendarUtils.fomatCalendar(merchantContractInfo.getValidDateBegin(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			if(merchantContractInfo.getValidDateEnd() != null){
				json.put("validDateEnd", CalendarUtils.fomatCalendar(merchantContractInfo.getValidDateEnd(), CalendarUtils.SHORT_FORMAT_LINE));
			}
			super.responseMessage(response, json.toString(), true);
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		} 
	}
	
	@RequestMapping
	public void create(HttpServletRequest request, HttpServletResponse response){
		this.saveOrUpdate(request, response, "create");
	}
	
	@RequestMapping
	public void update(HttpServletRequest request, HttpServletResponse response){
		this.saveOrUpdate(request, response, "update");
	}
	
	private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,String method) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			MerchantContractInfo merchantContractInfo = null;
			String operationDesc = "";
			if("create".equals(method)){
				merchantContractInfo = new MerchantContractInfo();
				operationDesc = "新增合同";
			}else if("update".equals(method)){
				merchantContractInfo = this.merchantContractInfoManager.get(Long.valueOf(request.getParameter("id")));
				operationDesc = "修改合同";
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			BindingResult result = super.bindObject(multipartRequest, merchantContractInfo);
			if(result.hasErrors() && CollectionUtils.isNotEmpty(result.getAllErrors())){
				super.responseMessage(response,"传入参数异常",false);
				return;
			}
			MultipartFile file = multipartRequest.getFile("upload");
			if (file != null && !file.isEmpty()) {
				String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				StringBuilder fileName = new StringBuilder(merchantContractInfo.getContractNumber());
				fileName.append(".").append(suffixName);
				merchantContractInfo.setContractFilePath(fileName.toString());
				StringBuilder sb = new StringBuilder(request.getSession().getServletContext().getRealPath("/"));
				sb.append("merchantFile");
				sb.append(File.separator);
				sb.append(fileName);
				this.writeFile((CommonsMultipartFile) file, sb.toString());
			}
			this.merchantContractInfoManager.saveOrUpdate(merchantContractInfo);
			super.responseMessage(response, PlatformErrorCode.SUCCESS.getDefaultMessage(), true);
			this.logManager.insertLog(operationDesc+"[编号：" + merchantContractInfo.getContractNumber() + "]成功");
		} catch (BusinessException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping()
	public void checkContractNew(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(this.merchantContractInfoManager.isContractNumberExist(request.getParameter("value"))){
				responseCheckResult(response, "合同编号已存在", false);
			}else{
				responseCheckResult(response, "", true);
			}
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}
	}
	
	@RequestMapping()
	public void checkContractUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String contractNumber = request.getParameter("value");
			Long id = Long.parseLong(request.getParameter("id"));
			MerchantContractInfo merchantContractInfo = this.merchantContractInfoManager.get(id);
			if(this.merchantContractInfoManager.isContractNumberExist(merchantContractInfo,contractNumber)){
				responseCheckResult(response, "合同编号已存在", false);
			}else{
				responseCheckResult(response, "", true);
			}
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		} 
	}
	
	private void writeFile(CommonsMultipartFile file, String filePath) throws BusinessException {
		int byteread = 0;
		byte[] buffer = new byte[8192];
		try {
			InputStream in = file.getInputStream();
			OutputStream out = new FileOutputStream(filePath);
			while ((byteread = in.read(buffer, 0, 8192)) != -1) {
				out.write(buffer, 0, byteread);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("文件读写异常");
		}

	}
	
	
}