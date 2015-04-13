package com.vlives.boss.merchant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.area.domain.Area.AreaType;
import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.Merchant.PosBusInessType;
import com.vlives.boss.merchant.domain.Merchant.Status;
import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.boss.merchant.domain.MerchantEnumType.BusinessLandNature;
import com.vlives.boss.merchant.domain.MerchantEnumType.BusinessLocation;
import com.vlives.boss.merchant.domain.MerchantEnumType.MerchantProperty;
import com.vlives.boss.merchant.domain.MerchantEnumType.PayType;
import com.vlives.boss.merchant.domain.MerchantEnumType.Regions;
import com.vlives.boss.merchant.domain.MerchantEnumType.TransactionType;
import com.vlives.boss.merchant.domain.MerchantExtendInfo;
import com.vlives.boss.merchant.manager.MerchantCategoryManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.operator.domain.Operator;
import com.vlives.boss.operator.domain.OperatorLog.OperatorType;
import com.vlives.boss.operator.manager.OperatorManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.util.FileUploadUtils;
import com.vlives.util.web.interceptor.ObjectConvertAnno;
import com.vlives.util.web.interceptor.OperatorLogAnno;


/***
 * description
 * 
 * @author unicorn
 * @version 1.0,2011-6-7
 * 
 */
@Controller
@RequestMapping("/manager/internal/merchant")
public class MerchantController {

	@Autowired
	private MerchantManager merchantManager;
	
	@Autowired
	private AreaManager areaManager;
	
	@Autowired
	private OperatorManager operatorManager;
	
	@Autowired
	private MerchantCategoryManager merchantCategoryManager;

	/**
	 * 根据登录的商户管理员得到相应的商户信息-----本系统
	 * 
	 * @author unicorn
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView get(@ObjectConvertAnno Operator operator, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Merchant merchant = operator.getMerchant();
		map.put("merchant", merchant);
		return new ModelAndView("/manager/selfinfo/self_info.jsp", map);
	}
	
	/**
	 * 获得商户信息
	 * @author unicorn
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public ModelAndView modifyIndex(@PathVariable("id") Long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Merchant merchant = merchantManager.get(merchantId);
		map.put("merchant", merchant);
		map.put("childCount", merchant.getChildCount());
		map.put("parentName", merchant.getParentName());
		return new ModelAndView("/manager/selfinfo/self_info_change.jsp", map);
	}


	/***
	 * 修改商户基本信息----本系统请求
	 * @author unicorn
	 * @param merchant
	 * @param parentId
	 * @param id
	 * @throws Exception 
	 */
	@RequestMapping(value = "/{id}/selfupdate.htm", method = RequestMethod.PUT)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_MERCHANT)
	public ModelAndView selfUpdate(@ObjectConvertAnno Operator operator,@ModelAttribute Merchant exmerchant, @PathVariable("id") Long id) throws Exception {
		Merchant merchant = merchantManager.get(id);
		getMerchantInfo(exmerchant,merchant);
		merchantManager.update(merchant, operator);
		return new ModelAndView(new RedirectView("/manager/internal/merchant/index.htm"));
	}
	
	private void getMerchantInfo(Merchant exmerchant,Merchant merchant) {
		Long areaId = HttpParameterParser.getLong("areaId");
		Area area = areaManager.get(areaId);
		merchant.setArea(area);
		if(StringUtils.isNotBlank(exmerchant.getContactName())) {
			merchant.setContactName(exmerchant.getContactName().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getContactTelephone())) {
			merchant.setContactTelephone(exmerchant.getContactTelephone().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getBusinessAddress())) {
			merchant.setBusinessAddress(exmerchant.getBusinessAddress().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getShortName())) {
			merchant.setShortName(exmerchant.getShortName().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getEnglishName())) {
			merchant.setEnglishName(exmerchant.getEnglishName().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getBusinessAddressCode())) {
			merchant.setBusinessAddressCode(exmerchant.getBusinessAddressCode().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getMerchantTelephone())) {
			merchant.setMerchantTelephone(exmerchant.getMerchantTelephone().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getMerchantFax())) {
			merchant.setMerchantFax(exmerchant.getMerchantFax().trim());
		}
		if(StringUtils.isNotBlank(exmerchant.getMerchantWeb())) {
			merchant.setMerchantWeb(exmerchant.getMerchantWeb().trim());
		}
	}
	
	/***
	 *  修改商户基本信息----外围系统请求
	 * @author unicorn
	 * @param operator	
	 * @param exmerchant			商户更新信息
	 * @param id					商户id 
	 * @param merchantExtendInfo	商户扩展信息更新
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_MERCHANT)
	public ModelAndView undate(HttpServletRequest request,@ObjectConvertAnno Operator operator,@ModelAttribute Merchant exmerchant, @RequestParam Long id) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			HttpParameterParser.init(request);
			Merchant merchant = merchantManager.get(id);
			merchant = this.setMerchant(merchant);
			String head = null;
			try {
				head = FileUploadUtils.uploadImage(multipartRequest, "importFile");
			} catch (Exception e) {
				
			}
			boolean flag = HttpParameterParser.getBooleanValue("changeLogo");  //flag = true,表示图片清空，false表示
			if(flag) {
				merchant.setHead(head);
			} 
			MerchantExtendInfo merchantExnfo = null;
			if(merchant.getMerchantExtendInfo() == null) {
				 merchantExnfo = new MerchantExtendInfo();
				 merchantExnfo.setMerchant(merchant);
			} else {
				merchantExnfo = merchant.getMerchantExtendInfo();
			}
			merchantExnfo = this.setExtendInfo(merchantExnfo);
			merchant.setMerchantExtendInfo(merchantExnfo);
			merchantManager.update(merchant, operator);
			return new ModelAndView(new JsonView(true, "商户信息修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, "商户信息修改失败"));
		} 
	}
	
	private Merchant setMerchant(Merchant merchant) {
		
		if(StringUtils.isNotBlank(HttpParameterParser.getString("name"))) {
			merchant.setName(HttpParameterParser.getString("name"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("shortName"))) {
			merchant.setShortName(HttpParameterParser.getString("shortName"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("businessAddress"))) {
			merchant.setBusinessAddress(HttpParameterParser.getString("businessAddress"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("parentOrganization"))) {
			merchant.setParentOrganization(HttpParameterParser.getString("parentOrganization"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("wifi"))) {
			merchant.setWifi(HttpParameterParser.getBoolean("wifi"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("englishName"))) {
			merchant.setEnglishName(HttpParameterParser.getString(("englishName")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("businessAddressCode"))) {
			merchant.setBusinessAddressCode(HttpParameterParser.getString(("businessAddressCode")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("merchantTelephone"))) {
			merchant.setMerchantTelephone(HttpParameterParser.getString(("merchantTelephone")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("busInformation"))) {
			merchant.setBusInformation(HttpParameterParser.getString(("busInformation")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("restaurant"))) {
			merchant.setRestaurant(HttpParameterParser.getString(("restaurant")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("parkingInformation"))) {
			merchant.setParkingInformation(HttpParameterParser.getString(("parkingInformation")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("businessTime"))) {
			merchant.setBusinessTime(HttpParameterParser.getString(("businessTime")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("merchantFax"))) {
			merchant.setMerchantFax(HttpParameterParser.getString(("merchantFax")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("merchantWeb"))) {
			merchant.setMerchantWeb(HttpParameterParser.getString(("merchantWeb")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("contactName"))) {
			merchant.setContactName(HttpParameterParser.getString(("contactName")));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("contactTelephone"))) {
			merchant.setContactTelephone(HttpParameterParser.getString(("contactTelephone")));
		}
		Long parentId = HttpParameterParser.getLong("parentId");
		 if(parentId != null) {
			 Merchant parent = merchantManager.get(parentId);
			 merchant.setParent(parent);
		 }
		//新增
		if(StringUtils.isNotBlank(HttpParameterParser.getString("categoryId"))) {
			Long id = HttpParameterParser.getLong("categoryId");
			MerchantCategory merchantCategory = merchantCategoryManager.getById(id);
			merchant.setCategory(merchantCategory);
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("longitude"))) {
			merchant.setLongitude(HttpParameterParser.getString("longitude"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("latitude"))) {
			merchant.setLatitude(HttpParameterParser.getString("latitude"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("info"))) {
			merchant.setInfo(HttpParameterParser.getString("info"));
		}
		if(StringUtils.isNotBlank(HttpParameterParser.getString("sales"))) {
			merchant.setSales(HttpParameterParser.getString("sales"));
		}
		int[] posBusInessTypeValues = HttpParameterParser.getIntArray("posBusInessType");
		if(posBusInessTypeValues != null && posBusInessTypeValues.length>0) {
			List<PosBusInessType> types = new ArrayList<Merchant.PosBusInessType>();
			for(int value :posBusInessTypeValues) {
				types.add(PosBusInessType.get(value));
			}
			merchant.setPosBusInessTypes(types.toArray(new PosBusInessType[]{}));
		} else {
			List<PosBusInessType> types = new ArrayList<Merchant.PosBusInessType>();
			merchant.setPosBusInessTypes(types.toArray(new PosBusInessType[]{}));
		}
		if(HttpParameterParser.getLong("areaId")!=null) {
			Long areaId = HttpParameterParser.getLong("areaId");
			Area area = areaManager.get(areaId);
			merchant.setArea(area);
		}
		if(HttpParameterParser.getString("discountFlag")!=null) {
			boolean discountFlag = HttpParameterParser.getBooleanValue("discountFlag");
			merchant.setDiscountFlag(discountFlag);
		}
		
		long num = HttpParameterParser.getIntValue("num");
		if(num != 0){
			merchant.setNum(num);
		}
		return merchant;
		
	}
	
	
	private MerchantExtendInfo setExtendInfo(MerchantExtendInfo merchantExnfo) {
		if(HttpParameterParser.getString("signMerchant") != "" && HttpParameterParser.getString("signMerchant") != null) {
			merchantExnfo.setSignMerchant(HttpParameterParser.getBooleanValue("signMerchant"));
		 }
		
		 if(HttpParameterParser.getString("registNo") != "" && HttpParameterParser.getString("registNo") != null) {
			 merchantExnfo.setRegistNo(HttpParameterParser.getString("registNo"));
		 }
		 if(HttpParameterParser.getString("taxNo") != "" && HttpParameterParser.getString("taxNo")!=null) {
			 merchantExnfo.setTaxNo(HttpParameterParser.getString("taxNo"));
		 }
		if(HttpParameterParser.getString("businessLicense")!="" && HttpParameterParser.getString("businessLicense")!=null) {
			 merchantExnfo.setBusinessLicense(HttpParameterParser.getString("businessLicense"));
		}
		if(HttpParameterParser.getString("taxCertificate")!=""&&HttpParameterParser.getString("taxCertificate")!=null) {
			 merchantExnfo.setTaxCertificate(HttpParameterParser.getString("taxCertificate"));
		}
		if(HttpParameterParser.getString("organizationCard")!=""&&HttpParameterParser.getString("organizationCard")!=null) {
			merchantExnfo.setOrganizationCard(HttpParameterParser.getString("organizationCard"));
		}
		if(HttpParameterParser.getString("registAddress")!="" &&HttpParameterParser.getString("registAddress") !=null) {
			merchantExnfo.setRegistAddress(HttpParameterParser.getString("registAddress"));
		}
		if(HttpParameterParser.getString("legal")!="" &&HttpParameterParser.getString("legal")!=null) {
			merchantExnfo.setLegal(HttpParameterParser.getString("legal"));
		}
		
		 int property = HttpParameterParser.getIntValue("property");
		 if(property != 0 ) {
			 merchantExnfo.setProperty(MerchantProperty.get(property));
		 }
		 if(HttpParameterParser.getString("registCapital")!="" &&HttpParameterParser.getString("registCapital")!=null) {
			 merchantExnfo.setRegistCapital(HttpParameterParser.getBigDecimal("registCapital"));
		 }
		 
		 int businessLand = HttpParameterParser.getIntValue("businessLand");
		 if(businessLand != 0 ) {
			 merchantExnfo.setBusinessLand(BusinessLandNature.get(businessLand));
		 }
		 if(HttpParameterParser.getString("businessArea")!=""&&HttpParameterParser.getString("businessArea")!=null) {
			 merchantExnfo.setBusinessArea(HttpParameterParser.getInteger("businessArea"));
		 }
		
		 int businessLocation = HttpParameterParser.getIntValue("businessLocation");
		 if(businessLocation != 0 ) {
			 merchantExnfo.setBusinessLocation(BusinessLocation.get(businessLocation));
		 }
		 int regions = HttpParameterParser.getIntValue("regions");
		 if(regions != 0 ) {
			 merchantExnfo.setRegions(Regions.get(regions));
		 }
		 if(HttpParameterParser.getString("employeesNumber") !=""&&HttpParameterParser.getString("employeesNumber") != null) {
			 merchantExnfo.setEmployeesNumber(HttpParameterParser.getInteger("employeesNumber"));
		 }
		 if(HttpParameterParser.getString("rangeMain")!="" && HttpParameterParser.getString("rangeMain")!=null) {
			 merchantExnfo.setRangeMain(HttpParameterParser.getString("rangeMain"));
		 }
		 if(HttpParameterParser.getString("rangeSideline") !="" &&HttpParameterParser.getString("rangeSideline")!= null) {
			 merchantExnfo.setRangeSideline(HttpParameterParser.getString("rangeSideline"));
		 }
		 if(HttpParameterParser.getString("branch") != null&&HttpParameterParser.getString("branch")!= ""){
			 merchantExnfo.setBranch(HttpParameterParser.getInteger("branch"));
		 }
		 if(HttpParameterParser.getString("turnoverYear") !=""&&HttpParameterParser.getString("turnoverYear") !=null) {
			 merchantExnfo.setTurnoverYear(HttpParameterParser.getBigDecimal("turnoverYear"));
		 }
		 if(HttpParameterParser.getString("profitYear") !="" &&HttpParameterParser.getString("profitYear")!=null) {
			 merchantExnfo.setProfitYear(HttpParameterParser.getBigDecimal("profitYear"));
		 }
		 if(HttpParameterParser.getString("turnoverBankCard")!="" &&HttpParameterParser.getBigDecimal("turnoverBankCard")!=null) {
			 merchantExnfo.setTurnoverBankCard(HttpParameterParser.getBigDecimal("turnoverBankCard"));
		 }
		 if(HttpParameterParser.getString("signTrading") !="" && HttpParameterParser.getString("signTrading") !=null) {
			 merchantExnfo.setSignTrading(HttpParameterParser.getBigDecimal("signTrading"));
		 }
		 if(HttpParameterParser.getString("bankCardMerchant") !="" && HttpParameterParser.getString("bankCardMerchant") !=null) {
			 merchantExnfo.setBankCardMerchant(HttpParameterParser.getBooleanValue("bankCardMerchant"));
		 }
		 if(HttpParameterParser.getString("bankMerchantCode") != "" || HttpParameterParser.getString("bankMerchantCode") != null) {
			 merchantExnfo.setBankMerchantCode(HttpParameterParser.getString("bankMerchantCode"));
		 }
		 if(HttpParameterParser.getString("merchantType")!=""&&HttpParameterParser.getString("merchantType") !=null) {
			 merchantExnfo.setMerchantType(HttpParameterParser.getString("merchantType"));
		 }
		if(HttpParameterParser.getString("accountSettlement") !="" && HttpParameterParser.getString("accountSettlement") != null) {
			merchantExnfo.setAccountSettlement(HttpParameterParser.getString("accountSettlement"));
		}
		 
		 int transactionType = HttpParameterParser.getIntValue("transactionType");
		 if(transactionType != 0 ) {
			 merchantExnfo.setTransactionType(TransactionType.get(transactionType));
		 }
		 int payType = HttpParameterParser.getIntValue("payType");
		 if(payType != 0 ) {
			 merchantExnfo.setPayType(PayType.get(payType));
		 }
		 
		 if(HttpParameterParser.getString("billAddress")!="" &&HttpParameterParser.getString("billAddress")!=null) {
			 merchantExnfo.setBillAddress(HttpParameterParser.getString("billAddress"));
		 }
		if(HttpParameterParser.getString("billZipcode")!="" && HttpParameterParser.getString("billZipcode")!=null) {
			 merchantExnfo.setBillZipcode(HttpParameterParser.getString("billZipcode"));
		}
		if(HttpParameterParser.getString("billPerson")!="" &&HttpParameterParser.getString("billPerson")!=null){
			merchantExnfo.setBillPerson(HttpParameterParser.getString("billPerson"));
		}
		 if(HttpParameterParser.getString("billEmail")  !="" && HttpParameterParser.getString("billEmail") !=null) {
			 merchantExnfo.setBillEmail(HttpParameterParser.getString("billEmail"));
		 }
		 if(HttpParameterParser.getString("billFax")!="" && HttpParameterParser.getString("billFax")!=null) {
			 merchantExnfo.setBillFax(HttpParameterParser.getString("billFax"));
		 }
		 if(HttpParameterParser.getString("signDate")!="" && HttpParameterParser.getString("signDate")!=null) {
			 merchantExnfo.setSignDate(HttpParameterParser.getDate("signDate","yyyyMMdd"));
		 }
		 //新增
		 if(HttpParameterParser.getBigDecimal("rating") != null) {
			 merchantExnfo.setRating(HttpParameterParser.getBigDecimal("rating"));
		 }
		 if(HttpParameterParser.getString("presentSmsCount") != null) {
			 merchantExnfo.setPresentSmsCount(HttpParameterParser.getIntValue("presentSmsCount"));
		 }
		 if(HttpParameterParser.getString("merchantBank") != null) {
			 merchantExnfo.setMerchantBank(HttpParameterParser.getString("merchantBank"));
		 }
		 
		return merchantExnfo;
	}

	/***
	 * 注销商户-----外围系统请求
	 * @author unicorn
	 * @param operator
	 * @param merchantId
	 * @param desc		变更理由
	 */
	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_DISABLE_MERCHANT)
	public ModelAndView logOut(@ObjectConvertAnno Operator operator, @RequestParam Long id) {
		try {
			Merchant merchant = merchantManager.get(id);
			String desc = HttpParameterParser.getString("desc");
			if(desc == null) {
				desc = "";
			}
			merchantManager.logOut(merchant, desc, operator);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("msg", "注销商户成功");
			result.put("merchant", merchant);
			return new ModelAndView(new JsonView("shortmerchant", result));
		}  catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
	}
	
	/***
	 * 冻结商户-----外围系统请求
	 * @param operator
	 * @param merchantId
	 * @param desc		变更理由
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="商户ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value = "/freeze.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_FREEZE_MERCHANT)
	public ModelAndView freeze(@ObjectConvertAnno Operator operator, @RequestParam Long id) {
		try {
			Merchant merchant = merchantManager.get(id);
			String desc = HttpParameterParser.getString("desc");
			if(desc == null) {
				desc = "";
			}
			merchantManager.freeze(merchant, desc, operator);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("msg", "冻结商户成功");
			result.put("merchant", merchant);
			return new ModelAndView(new JsonView("shortmerchant", result));
		}  catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
	}

	/***
	 * 解冻商户-----外围系统请求
	 * @param operator
	 * @param merchantId
	 * @param desc		变更理由
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="商户ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value = "/unfreeze.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_UNFREEZE_MERCHANT)
	public ModelAndView unFreeze(@ObjectConvertAnno Operator operator, @RequestParam Long id) {
		try {
			Merchant merchant = merchantManager.get(id);
			String desc = HttpParameterParser.getString("desc");
			if(desc == null) {
				desc = "";
			}
			merchantManager.unFreeze(merchant, desc, operator);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("msg", "解冻商户成功");
			result.put("merchant", merchant);
			return new ModelAndView(new JsonView("shortmerchant", result));
		}  catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false, e.getMessage()));
		}
	}

	/**
	 * 外围系统根据具体商户id得到详细
	 * @author unicorn
	 * @param id 商户id
	 */
	@ParamValidators({@ParamValidator(param="id",paramName="商户ID",vaildatorTypes={ValidatorType.REQUIRED})})
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam Long id) {
		Merchant merchant = merchantManager.get(id);
		PosBusInessType[] posBusInessType = merchant.getPosBusInessTypes();
		MerchantExtendInfo merchantExtendInfo = merchant.getMerchantExtendInfo();
		int childCount = merchant.getChildCount();
		Operator operator = operatorManager.findMerchantOperator(merchant);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("managerMobile", operator.getMobile());
		result.put("merchant", merchant);
		result.put("posBusInessType", posBusInessType);
		result.put("extend", merchantExtendInfo);
		result.put("logs", merchant.getMerchantLogs());
		result.put("areas", merchant.getArea().getAreaPath());
		result.put("childCount", childCount);
		return new ModelAndView(new JsonView("merchant",result));
	}
	
	/**
	 * 根据商户编码取得商户信息
	 * @param code
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="code",paramName="商户编号",length=15,vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value = "/detailbycode.htm",method=RequestMethod.GET)
	public ModelAndView getByCode(@RequestParam String code) {
		Merchant merchant = merchantManager.getByCode(code);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("merchant", merchant);
		return new ModelAndView(new JsonView("shortmerchant",result));
		
	}
	
	/**
	 * 验证商户编码是否重复
	 * @param code
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="code",paramName="商户编号",length=15,vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/check.htm",method=RequestMethod.GET)
	public ModelAndView isExistCode(@RequestParam String code) {
		boolean flag = merchantManager.isExistCode(code);
		if(flag) {
			return new ModelAndView(new JsonView(true,"商户编码存在"));
		} else {
			return new ModelAndView(new JsonView(false,"商户编码不存在"));
		}
	}
	/**
	 * 验证商户短信编号是否存在
	 * @author jianguo.xu
	 * @param num
	 * @return
	 */
	@RequestMapping(value="/isExistNum.htm",method=RequestMethod.GET)
	public ModelAndView isExistNum(@RequestParam int num) {
		boolean flag = merchantManager.isExistNum(num);
		if(flag) {
			return new ModelAndView(new JsonView(true,"商户短信编号已存在"));
		} else {
			return new ModelAndView(new JsonView(false,"商户短信编号已存在"));
		}
	}

	/***
	 * 接收外围系统信息创建商户
	 * @author unicorn
	 * @param request
	 * @param managerMobile		商户操作员手机号
	 * @param operator			
	 * @return
	 */
	@RequestMapping(value = "/create.htm", method = RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_UPDATE_MERCHANT)
	public ModelAndView create(HttpServletRequest request,@ObjectConvertAnno Operator operator) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String managerMobile = multipartRequest.getParameter("managerMobile");
			Merchant merchant = new Merchant();
			HttpParameterParser.init(request);
			setMerchant(merchant);
		
			String head = null;
			try {
				head = FileUploadUtils.uploadImage(multipartRequest, "importFile");
			} catch (Exception e) {
			}
			boolean flag = HttpParameterParser.getBooleanValue("useParentLogo");	//是否使用父商户头像
			if(flag) {
				Long parentId = HttpParameterParser.getLong("parentId");
				Merchant parent = merchantManager.get(parentId);
				head = parent.getHead();
			}
			merchant.setHead(head);
			MerchantExtendInfo merchantExtendInfo = new MerchantExtendInfo();
			merchantExtendInfo = setExtendInfo(merchantExtendInfo);
			String code = merchantManager.create(merchant, merchantExtendInfo, managerMobile, operator);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",true);
			map.put("msg", "商户创建成功");
			map.put("code", code);
			return new ModelAndView(new JsonView("createresponse",map));
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"商户创建失败"+","+e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"系统错误，创建商户失败"));
		}
	}
	
	

	/***
	 * 根据条件动态查询商户列表
	 * 商户中文全名、商户中文简称、商户编码、状态、pageSize、currentPage
	 * @author unicorn
	 * @param pagination 
	 */
	@RequestMapping("/findMerchantBy.htm")
	public ModelAndView find(@ObjectConvertAnno Pagination pagination) {
		try {
			String name = HttpParameterParser.getString("merchantName");
			String code = HttpParameterParser.getString("code");
			String sort1 = HttpParameterParser.getString("sort1");
			String sort2 = HttpParameterParser.getString("sort2");
			int[] status = HttpParameterParser.getIntArray("status");
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(StringUtils.isNotBlank(name)) {
				map.put("name", "%"+name+"%");
				map.put("shortName","%"+name+"%");
			}
			if(StringUtils.isNotBlank(code)) {
				map.put("code", code);
			}
			if(StringUtils.isNotBlank(sort1)) {
				map.put("sort1", sort1);
			}
			if(StringUtils.isNotBlank(sort2)) {
				map.put("sort2", sort2);
			}
			if(status != null) {
				List<Status> merchantStatus = new ArrayList<Status>();
				for(int s : status) {
					merchantStatus.add(Status.get(s));
				}
				map.put("status", merchantStatus);
			}
			List<Merchant> merchants = merchantManager.find(map, pagination);
			Map<String, Object> result = new HashMap<String, Object>();

			result.put("success", true);
			result.put("merchants", merchants);
			result.put("pagination", pagination);
			
			return new ModelAndView(new JsonView("merchants",result));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"商户查询失败"));
		}
	}
	
	/***
	 * 根据条件动态查询所有商户列表，不分页
	 * @author unicorn
	 */
	@RequestMapping(value="/export.htm",method=RequestMethod.GET)
	public ModelAndView findAll() {
		try {
			String name = HttpParameterParser.getString("merchantName");
			String code = HttpParameterParser.getString("code");
			int[] status = HttpParameterParser.getIntArray("status");
			Long[] areaIds = HttpParameterParser.getLongWrapperArray("area");
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(areaIds != null&&areaIds.length>0) {
				List<Area> areas = new ArrayList<Area>();
				for(Long id : areaIds) {
					Area area = areaManager.get(id);
					if(area.getAreaType()== AreaType.CITY) {
						areas.addAll(area.getChildrens());
					}
				}
				map.put("areas",areas);
			}
			if(StringUtils.isNotBlank(name)) {
				map.put("name", "%"+name+"%");
				 
			}
			if(StringUtils.isNotBlank(code)) {
				map.put("code", code);
			}
			if(status != null) {
				List<Status> merchantStatus = new ArrayList<Status>();
				for(int s : status) {
					merchantStatus.add(Status.get(s));
				}
				map.put("status", merchantStatus);
			}
			List<Merchant> merchants = merchantManager.find(map);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("merchants", merchants);
			return new ModelAndView(new JsonView("export",result));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"商户查询失败"));
		}
	}
	/***
	 * 审核商户
	 * @author unicorn
	 * @param operator
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="商户ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/audit.htm",method=RequestMethod.POST)
	@OperatorLogAnno(OperatorType.TYPE_AUDIT_MERCHANT)
	public ModelAndView auditMerchant(@ObjectConvertAnno Operator operator) {
		Long id = HttpParameterParser.getLong("id");
		boolean pass = HttpParameterParser.getBoolean("pass");
		String desc = HttpParameterParser.getString("desc");
		if(desc == null) {
			desc = "";
		}
		Merchant merchant = merchantManager.get(id);
		try {
			merchantManager.aduitMerchant(merchant, pass, desc, operator);
			List<Operator> list = operatorManager.findByMerchant(merchant);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("msg", "操作成功");
			result.put("mobile", list.get(0).getMobile());
			result.put("merchant", merchant);
			return new ModelAndView(new JsonView("shortmerchant", result));
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ModelAndView(new JsonView(false,"商户审核失败"));
		}
	}
	/**
	 * 获得可以做父商户的商户列表
	 * @author unicorn
	 * @return
	 */
	@RequestMapping(value="/parent.htm",method=RequestMethod.GET)
	public ModelAndView findCanParentMerchant(HttpServletRequest request,@ObjectConvertAnno Pagination pagination) {
		Map<String,Object> param = new HashMap<String, Object>();
		String merchantName = HttpParameterParser.getString("merchantName");
		if(StringUtils.isNotEmpty(merchantName)) {
			param.put("merchantName", "%"+merchantName+"%");
		}
		List<Merchant> list = merchantManager.findOtherParents(param,pagination);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("pagination", pagination);
		result.put("merchants", list);
		return new ModelAndView(new JsonView("merchants",result));
	}
	
	/**
	 * 获得商户子商户
	 * @author unicorn
	 * @param id
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="商户ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/children.htm",method=RequestMethod.GET)
	public ModelAndView findChild(@RequestParam Long id) {
		Merchant merchant = merchantManager.get(id);
		if(merchant == null) {
			return new ModelAndView(new JsonView(false,"请求商户不存在"));
		}
		List<Merchant> list = new LinkedList<Merchant>(merchant.getValidChildrens());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("merchants", list);
		return new ModelAndView(new JsonView("merchants",result));
	}

}
