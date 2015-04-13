package com.justinmobile.bmp.cloudboss.report.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.util.DateUtils;
import com.justinmobile.bmp.util.ExcelUtils;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("reportController")
@RequestMapping("/rp.do")
public class ReportController extends BaseAjaxController {
	
	private static final String[] HEADER_MEMBER_EXPORT = {"会员手机号", "会员加入日期", "商户编号", "商户名称"};
	
	private static final String[] HEADER_MERCHANT_EXPORT = {
		"统计时间", "商户编号", "商户名称", "商户创建日期", "会员当日新增数", "会员总数", "现金消费金额",
		"现金消费数", "银行卡消费数", "银行卡消费数", "营销活动日增数", "营销活动到达数", "商户已发短信数"
		};
	
	private static final int EXPORT_PAGE_SIZE = 10;
	
	@Autowired
	private ExcelUtils excelUtils;
	
	@RequestMapping()
	public void memberReport(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String startDate = request.getParameter("startDate");
			if (startDate != null && !"".equals(startDate)) {
				params.put("startDate", startDate);
			}
			String endDate = request.getParameter("endDate");
			if (endDate != null && !"".equals(endDate)) {
				params.put("endDate", endDate);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
//			params.put("area", this.cityUtil.getCityArray(request.getParameter("cities")));
			//
			JSONObject result = CloudBossCaller.HTTP.MEMBER_REPORT.invoke(null, params);
			//
			JSONObject pagination = result.getJSONObject("pagination");
			//
			if (result.getBoolean("success")) {
				response.getWriter().write(
						makeSuccessArrayString(pagination.getString("count"), result.getJSONArray("memberReprot").toString()));
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
	public void merchantReport(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			initializePagingSortingFiltering(request);
			//
			Map<String, Object> params = new HashMap<String, Object>();
			String startDate = request.getParameter("startDate");
			if (startDate != null && !"".equals(startDate)) {
				params.put("startDate", startDate);
			}
			String endDate = request.getParameter("endDate");
			if (endDate != null && !"".equals(endDate)) {
				params.put("endDate", endDate);
			}
			params.put("pagination.pageSize", getPageSize());
			params.put("pagination.currentPage", getPageNo());
//			params.put("area", this.cityUtil.getCityArray(request.getParameter("cities")));
			//
			JSONObject result = CloudBossCaller.HTTP.MERCHANT_REPORT.invoke(null, params);
			//
			JSONObject pagination = result.getJSONObject("pagination");
			//
			if (result.getBoolean("success")) {
				response.getWriter().write(
						makeSuccessArrayString(pagination.getString("count"), result.getJSONArray("merchantReprot").toString()));
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
	public void merchantExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=export.xls");
			//
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			//
			int pageSize = EXPORT_PAGE_SIZE;
			int pageNo = 1;
			List<List<String>> list = new ArrayList<List<String>>();
			while(true){
				Map<String, Object> params = new HashMap<String, Object>();
				if (startDate != null && !"".equals(startDate)) {
					params.put("startDate", startDate);
				}
				if (endDate != null && !"".equals(endDate)) {
					params.put("endDate", endDate);
				}
				params.put("pagination.pageSize", pageSize);
				params.put("pagination.currentPage", pageNo);
//				params.put("area", this.cityUtil.getCityArray(request.getParameter("cities")));
				JSONObject result = CloudBossCaller.HTTP.MERCHANT_REPORT.invoke(null, params);
				JSONArray ja = result.getJSONArray("merchantReprot");
				//
				for (int i = 0; i < ja.size(); i++) {
					JSONObject json = ja.getJSONObject(i);
					List<String> record = new ArrayList<String>();
					record.add(json.getString("createDate"));
					record.add(json.getJSONObject("merchant").getString("code"));
					record.add(json.getJSONObject("merchant").getString("name"));
					record.add(json.getJSONObject("merchant").getString("createDate"));
					record.add(json.getString("perDayAddMemberCount"));
					record.add(json.getString("totalMemberCount"));
					record.add(json.getString("cashTradeMoney"));
					record.add(json.getString("cashTradeCount"));
					record.add(json.getString("cardTradeMoney"));
					record.add(json.getString("cardTradeCount"));
					record.add(json.getString("perDayAddSalePloyCount"));
					record.add(json.getString("totalSalePloyCount"));
					record.add(json.getString("sendSmsCount"));
					list.add(record);
				}
				//
				int total = result.getJSONObject("pagination").getInt("count");
				if( (ja.size() + pageSize * (pageNo - 1)) >= total ){
					break;
				}
				//
				pageNo++;
			};
			//
			if( list.size() > 0 ){
				excelUtils.writeToOutputStream(list, HEADER_MERCHANT_EXPORT, null, response.getOutputStream());
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
	public void memberExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=export.xls");
			//
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			//
			int pageSize = EXPORT_PAGE_SIZE;
			int pageNo = 1;
			List<List<String>> list = new ArrayList<List<String>>();
			while(true){
				Map<String, Object> params = new HashMap<String, Object>();
				if (startDate != null && !"".equals(startDate)) {
					params.put("startDate", startDate);
				}
				if (endDate != null && !"".equals(endDate)) {
					params.put("endDate", endDate);
				}
				params.put("pagination.pageSize", pageSize);
				params.put("pagination.currentPage", pageNo);
//				params.put("area", this.cityUtil.getCityArray(request.getParameter("cities")));
				JSONObject result = CloudBossCaller.HTTP.MEMBER_REPORT.invoke(null, params);
				JSONArray ja = result.getJSONArray("memberReprot");
				//
				for (int i = 0; i < ja.size(); i++) {
					JSONObject json = ja.getJSONObject(i);
					List<String> record = new ArrayList<String>();
					record.add(json.getString("mobile"));
					record.add(json.getString("createDate"));
					record.add(json.getString("merchantCode"));
					record.add(json.getString("merchantName"));
					list.add(record);
				}
				//
				int total = result.getJSONObject("pagination").getInt("count");
				if( (ja.size() + pageSize * (pageNo -1)) >= total ){
					break;
				}
				//
				pageNo++;
			}
			//
			if( list.size() > 0 ){
				excelUtils.writeToOutputStream(list, HEADER_MEMBER_EXPORT, null, response.getOutputStream());
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
	public void cityReport(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");			
			if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
				throw new PlatformException(PlatformErrorCode.PARAM_ERROR);
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			JSONObject json = CloudBossCaller.HTTP.CITY_REPORT.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				JSONArray temp = json.getJSONArray("cityMerchantReports");
				JSONArray jsonArray = new JSONArray();
				for(int i=0;i<temp.size();i++){
					JSONObject obj = temp.getJSONObject(i);
					obj.put("reportDate", DateUtils.format(DateUtils.parse(obj.getString("reportDate"))));
					jsonArray.add(obj);
				}
				String result = makeSuccessArrayString(String.valueOf(temp.size()), jsonArray.toString());
				response.getWriter().write(result);
			} else {
				super.responseMessage(response, json.getString("desc"), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping()
	public void cityExport(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=cityReport.xls");
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");			
			if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
				throw new PlatformException(PlatformErrorCode.PARAM_ERROR);
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			JSONObject json = CloudBossCaller.HTTP.CITY_REPORT.invoke(null, params);
			if ("true".equals(json.getString("success"))) {
				List<List<String>> list = getCityDataList(json.getJSONArray("cityMerchantReports"));
				String filePath = request.getSession().getServletContext().getRealPath("/")+ File.separator + "reportFile"+ File.separator +"cityReport.xls";
				POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				//从第四行开始写入数据
				int rowIndex = 3;
				for (int i = 0; i < list.size(); i++) {
					List<String> colList = list.get(i);
					HSSFRow row = sheet.createRow(rowIndex+i);
					for (short colIndex = 0; colIndex < colList.size(); colIndex++) {
						HSSFCell cell = row.createCell(colIndex);
						if (colList.get(colIndex) == null) {
							continue;
						}
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(colList.get(colIndex));
					}
				}
				workbook.write(response.getOutputStream());
			} else {
				super.responseMessage(response, json.getString("desc"), false);
			}
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, PlatformErrorCode.UNKNOWN_ERROR.getDefaultMessage(), false);
			e.printStackTrace();
		}

	}

	private List<List<String>> getCityDataList(JSONArray jsonArray) throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			List<String> temp = new ArrayList<String>();
			temp.add(DateUtils.format(DateUtils.parse(obj.getString("reportDate"))));
			temp.add(obj.getJSONObject("city").getString("name"));
			temp.add(obj.getString("perDayAddMerchantCount"));
			temp.add(obj.getString("totalMerchantCount"));
			temp.add(obj.getString("perDayAddPosCount"));
			temp.add(obj.getString("totalPosCount"));
			temp.add(obj.getString("perDayAddMemberCount"));
			temp.add(obj.getString("totalMemberCount"));
			temp.add(obj.getString("perDayPosAddMemberCount"));
			temp.add(obj.getString("totalPosAddMemberCount"));
			temp.add(obj.getString("perDayPlatformAddMemberCount"));
			temp.add(obj.getString("totalPlatformAddMemberCount"));
			temp.add(obj.getString("perDayCloudbossAddMemberCount"));
			temp.add(obj.getString("totalCloudbossAddMemberCount"));
			temp.add(obj.getString("perDaySysLargessSmsCount"));
			temp.add(obj.getString("totalSysLargessSmsCount"));
			temp.add(obj.getString("perDayMerchantBuySmsCount"));
			temp.add(obj.getString("totalMerchantBuySmsCount"));
			temp.add(obj.getString("perDaySentSmsCount"));
			temp.add(obj.getString("totalSentSmsCount"));
			temp.add(obj.getString("perDayCashTradeCount"));
			temp.add(obj.getString("totalCashTradeCount"));
			temp.add(obj.getString("perDayCardTradeCount"));
			temp.add(obj.getString("totalCardTradeCount"));
			temp.add(obj.getString("perDayCashTradeMoney"));
			temp.add(obj.getString("totalCashTradeMoney"));
			temp.add(obj.getString("perDayCardTradeMoney"));
			temp.add(obj.getString("totalCardTradeMoney"));
			list.add(temp);
		}
		return list;
	}
}
