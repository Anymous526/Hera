package com.justinmobile.bmp.cloudboss.member.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;
import com.justinmobile.bmp.common.exception.PlatformErrorCode;
import com.justinmobile.bmp.common.exception.PlatformException;
import com.justinmobile.bmp.common.log.manager.LogManager;
import com.justinmobile.bmp.util.CalendarUtils;
import com.justinmobile.bmp.util.DateUtils;
import com.justinmobile.core.web.BaseAjaxController;

@Controller("memberController")
@RequestMapping("/memberManager.do")
public class MemberController extends BaseAjaxController {
	@Autowired
	private LogManager logManager;
	private static final String PAGENO = "pagination.currentPage";

	private static final String PAGESIZE = "pagination.pageSize";

	private static final String NAME = "name";

	private static final String MOBILE = "mobile";

	private static final String MERCHANTCODE = "merchantCode";

	private static final String LEVEL = "level";

	private static final String STARTLASTCONSUMEDATE = "startLastConsumeDate";

	private static final String ENDLASTCONSUMEDATE = "endLastConsumeDate";

	private static final String STARTCREATEDATE = "startCreateDate";

	private static final String ENDCREATEDATE = "endCreateDate";

	private static final String PAGEINFO = "pagination";

	private static final String PAGECOUNT = "count";

	private static final String MEMBERJSONLIST = "list";

	private static final String SUCCESS = "success";

	private static final String DESCRIPTION = "desc";

	private static String EXCELCODE[] = { "mobile", "name", "petName", "Gender", "area", "birthday", "post", "email",
			"msn", "qq", "cardType", "cardNumber", "level", "memberCard", "memberPoint" };
	
	private static String EXCELTITLE[] = { "手机号码", "名称", "昵称", "性别", "地区", "出生年月", "邮政编码", "电子邮件", "MSN", "QQ", "证件类型",
			"证件号码", "会员等级", "会员卡号", "会员积分" };

	@RequestMapping()
	public void getMemberLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String result = makeSuccessArrayString(
					String.valueOf(2),
					"[{\"key\":\"0\",\"value\":\"所有会员\"},{\"key\":\"1\",\"value\":\"普通会员\"},{\"key\":\"2\",\"value\":\"银卡会员\"},{\"key\":\"3\",\"value\":\"金卡会员\"},{\"key\":\"4\",\"value\":\"钻石会员\"}]");
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping()
	public void listMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			super.initializePagingSortingFiltering(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(PAGENO, getPageNo());
			params.put(PAGESIZE, getPageSize());
			params.put(NAME, request.getParameter("name"));
			params.put(MOBILE, request.getParameter("mobile"));
			params.put(MERCHANTCODE, request.getParameter("merchantCode"));
			params.put(LEVEL, request.getParameter("level"));
			params.put(STARTLASTCONSUMEDATE, request.getParameter("startLastConsumeDate"));
			params.put(ENDLASTCONSUMEDATE, request.getParameter("endLastConsumeDate"));
			params.put(STARTCREATEDATE, request.getParameter("startCreateDate"));
			params.put(ENDCREATEDATE, request.getParameter("endCreateDate"));
			params.put("source", request.getParameter("source"));
			JSONObject json = CloudBossCaller.HTTP.MEMBER_LIST_READ.invoke(null, params);
			if (json.getString(SUCCESS) != null && "false".equals(json.getString(SUCCESS))) {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
			} else {
				String totalCount = json.getJSONObject(PAGEINFO).getString(PAGECOUNT);
				String jsonArray = json.getJSONArray(MEMBERJSONLIST).toString();
				String result = makeSuccessArrayString(totalCount, jsonArray);
				response.getWriter().write(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "列表查询失败", false);
		}
	}

	@RequestMapping()
	public void listUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("");
		} catch (Exception e) {

			e.printStackTrace();
			responseMessage(response, "查询失败:" + e.getMessage(), false);
		}
	}

	@RequestMapping()
	public void memberUploadQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			response.setContentType("text/html;charset=UTF-8");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("loadFile");
			InputStream is = multipartFile.getInputStream();
			JSONArray jsonArray = this.parseExcel(is);
			String result = makeSuccessArrayString(String.valueOf(jsonArray.size()), jsonArray.toString());
			response.getWriter().write(result);
		} catch (PlatformException e) {
			super.responseMessage(response, e.getMessage(), false);
			e.printStackTrace();
		} catch (Exception e) {
			super.responseMessage(response, "读取失败", false);
			e.printStackTrace();
		}
	}

	/**
	 * 解析EXCEL文件,将数据存放到LIST中
	 * 
	 * @param is
	 * @return
	 * @author devgqk
	 */
	protected JSONArray parseExcel(InputStream is) throws PlatformException {
		JSONArray memberList = new JSONArray();
		POIFSFileSystem fs;
		try {
			fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				if (row != null) {
					JSONObject member = new JSONObject();
					for (int k = 0; k < 15; k++) {
						HSSFCell cell = row.getCell(k);
						String value = "";
						if (cell != null) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								value = cell.getStringCellValue();
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								NumberFormat format = NumberFormat.getInstance();
								value = format.format(cell.getNumericCellValue());
								if (cell.getDateCellValue() != null && !cell.getDateCellValue().equals(""))
									value = CalendarUtils.format(cell.getDateCellValue());
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
								value = cell.getCellFormula();
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
								value = "" + cell.getErrorCellValue();
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
								value = "" + cell.getBooleanCellValue();
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
								value = "";
							}
							checkValue(k, value, cell.getRowIndex() + 1, EXCELTITLE[k]);
							member.put(EXCELCODE[k], value);
						}
					}
					memberList.add(member);
				}
			}
		} catch (PlatformException e) {
			throw e;
		} catch (Exception e) {
			throw new PlatformException(PlatformErrorCode.EXCEL_PARSE_ERROR, e);
		}

		return memberList;
	}

	private void checkValue(int key, String value, int row, String field) {
		Pattern p = null;
		switch (key) {
		case 0:
			p = Pattern.compile("^((13|15|18)\\d{9})?$");
			break;
		case 1:
			p = Pattern.compile("^([a-zA-Z0-9\u4E00-\u9FA5]{0,10})?$");
			break;
		case 2:
			p = Pattern.compile("^([a-zA-Z0-9\u4E00-\u9FA5]{0,10})?$");
			break;
		case 3:
			p = Pattern.compile("^[\u7537\u5973]?$");
			break;
		case 4:
			p = Pattern.compile("^([a-zA-Z0-9\u4E00-\u9FA5]{0,30})?$");
			break;
		case 5:
			try {
				DateUtils.parse(value);
			} catch (Exception e) {
				throw new PlatformException("第" + row + "行," + field + "格式错误");
			}
			p = Pattern.compile("^[0-9-]{0,10}$");
			break;
		case 6:
			p = Pattern.compile("^([0-9]{6})?$");
			break;
		case 7:
			if (value.length() > 20) {
				throw new PlatformException("第" + row + "行," + field + "超出最大长度");
			}
			p = Pattern.compile("^(\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)?$");
			break;
		case 8:
			p = Pattern.compile("^([a-zA-Z0-9.@_-]{0,20})?$");
			break;
		case 9:
			p = Pattern.compile("^([0-9]{0,20})?$");
			break;
		case 10:
			p = Pattern.compile("^([a-zA-Z0-9\u4E00-\u9FA5]{0,10})?$");
			break;
		case 11:
			p = Pattern.compile("^([0-9]{0,30})?$");
			break;
		case 12:
			p = Pattern
					.compile("^((\u666E\u901A\u4F1A\u5458)|(\u94F6\u5361\u4F1A\u5458)|(\u91D1\u5361\u4F1A\u5458)|(\u94BB\u77F3\u4F1A\u5458))?$");
			break;
		case 13:
			p = Pattern.compile("^([0-9]{0,20})?$");
			break;
		case 14:
			p = Pattern.compile("^[\\d]{0,9}$");
			break;
		}
		Matcher m = p.matcher(value);
		if (!m.matches()) {
			throw new PlatformException("第" + row + "行," + field + "格式错误");
		}

	}

	@RequestMapping()
	public void memberUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			response.setContentType("text/html;charset=UTF-8");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("loadFile");
			InputStream is = multipartFile.getInputStream();
			this.parseExcel(is);
			String merchantCode = request.getParameter("merchantCode");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("merchantCode", merchantCode);
			String filePath = request.getSession().getServletContext().getRealPath("/") + "merchantFile"+File.separator;
			File file = new File(filePath + multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			JSONObject json = CloudBossCaller.HTTP.MEMBER_UPLOAD_ADD.invokeFile(null, params, file);// new
																									// File("d:/user.xls")
			if (json.getString(SUCCESS) != null && "false".equals(json.getString(SUCCESS))) {
				super.responseMessage(response, json.getString(DESCRIPTION), false);
				logManager.insertLog("会员数据导入失败");
			} else {
				super.responseMessage(response, json.getString(DESCRIPTION), true);
				logManager.insertLog("会员数据导入成功");
			}
		} catch (PlatformException e) {
			e.printStackTrace();
			responseMessage(response, e.getMessage(), false);
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage(response, "操作失败", false);
		}
	}

	@RequestMapping()
	public void memberDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			StringBuffer path = new StringBuffer();
			path.append("http://");
			path.append(request.getLocalAddr());
			path.append(":");
			path.append(request.getServerPort());
			path.append(request.getContextPath());
			path.append("/view/cloudboss/member/template/MEMBER_UPLOAD.xls");
			URL url = new URL(path.toString());
			HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			BufferedInputStream fis = new BufferedInputStream(httpUrl.getInputStream());
			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment; filename=MEMBER_UPLOAD.xls");
			byte[] byt = new byte[64 * 1024];
			ServletOutputStream sos = response.getOutputStream();
			int readLength;
			int filelen = 0;
			while ((readLength = fis.read(byt)) != -1) {
				filelen += readLength;
				sos.write(byt, 0, readLength);
			}
			fis.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping()
	public void test(HttpServletRequest request) {
		MultipartHttpServletRequest multRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multRequest.getFile("");

		try {
			System.out.println(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getSize());
	}
}
