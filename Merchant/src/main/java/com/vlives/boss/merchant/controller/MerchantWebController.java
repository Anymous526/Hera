/**
 * @(#)MerchantWebController.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.area.manager.AreaManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.dto.MerchantSearchResult;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.sale.domain.SalePloy;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.boss.user.domain.User;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.core.web.filter.cache.Cache;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * 网站开发接口
 * 
 * @author unicorn
 * @version 1.0,2011-7-6
 */
@Controller
public class MerchantWebController {

	@Autowired
	private SalePloyManager salePloyManager;

	@Autowired
	private MerchantManager merchantManager;

	@Autowired
	private AreaManager areaManager;

	@RequestMapping(value = { "/", "/index.htm" }, method = RequestMethod.GET)
	public ModelAndView webIndex() {

		return new ModelAndView("/index.jsp");
	}

	/**
	 * 加载商户页面显示的信息
	 * 
	 * @param id
	 *            商户编号
	 * @param user
	 *            用户对象
	 * @return
	 */
	@RequestMapping(value = "/merchant/{id}/", method = RequestMethod.GET)
	public ModelAndView redirectMerchantInfo(@PathVariable("id")
	Long id, @ObjectConvertAnno(required = false)
	User user) {
		// id 商户编号
		// 定义HashMap来存储对象信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取商户信息
		Merchant merchant = merchantManager.get(id);
		merchant.setFavourPloy(salePloyManager.isExistMerchantPloy(merchant));
		map.put("merchant", merchant);

		// 获取某商户附近的所有商户信息
		List<Merchant> merchants = merchantManager.findByNear(merchant, 5);
		for (Merchant it : merchants) {
			it.setFavourPloy(salePloyManager.isExistMerchantPloy(it));
		}
		map.put("nearMerchants", merchants);

		// 获取某商户的最新的一条营销活动信息
		List<SalePloy> newsPloyList = salePloyManager.getNewlySalePloy(merchant);
		map.put("newsPloyList", newsPloyList);
		// 获取商户的所有营销活动
		List<SalePloy> ployList = salePloyManager.find(merchant);
		map.put("ployList", ployList);

		String redirectUrl = "/merchant.jsp?id=" + id;
		return new ModelAndView(redirectUrl, map);
	}

	/**
	 * 商户首页--找优惠 取得最新发布的10条营销活动，每个商户只能选择最新发布的活动 返回活动模板，以及商户名、商户经纬度
	 * 
	 * @return 商户列表
	 */
	@RequestMapping(value = "/search/saleploy.htm", method = RequestMethod.GET)
	public ModelAndView getSalploy() {
		List<SalePloy> list = salePloyManager.find();
		for (SalePloy saleploy : list) {
			saleploy.getMerchant().setFavourPloy(salePloyManager.isExistMerchantPloy(saleploy.getMerchant()));
		}
		return new ModelAndView(new JsonView("saleploysearch", list));
	}

	@RequestMapping(value = "/search/merchanthead.htm")
	public ModelAndView searchHead(@ObjectConvertAnno
	Pagination pagination) {
		return new ModelAndView("/search/searchlist.jsp");
	}

	/**
	 * 商户首页---搜索 根据商户姓名,商户区域名，商户类型等查询 返回活动模板，以及商户名、商户经纬度
	 * 
	 * @return 商户列表
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/search/merchant.htm")
	public ModelAndView search(@ObjectConvertAnno
	Pagination pagination) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = HttpParameterParser.getString("name");
		Long[] areas = HttpParameterParser.getLongWrapperArray("area");
		Long[] categories = HttpParameterParser.getLongWrapperArray("category");
		String sort = HttpParameterParser.getString("sort");
		Long searchArea = HttpParameterParser.getLong("searchArea");
		
		 

		if (name != null) {
			//System.out.println("Timmy say step 1 ==========================="+name);
			map.put("name", URLDecoder.decode(name, "UTF-8"));
		}
		if (areas != null) {
			//System.out.println("Timmy say step 2 ==========================="+areas);
			map.put("area", areas);
		}
		if (categories != null) {
			//System.out.println("Timmy say step 3 ==========================="+categories);
			map.put("category", categories);
		}
		if (sort != null) {
			//System.out.println("Timmy say step 4 ==========================="+sort);
			map.put("sort", sort);
		}
		if (searchArea != null) {
			//System.out.println("Timmy say step 5 ==========================="+searchArea);
			map.put("searchArea", areaManager.get(searchArea));
		}
		MerchantSearchResult merchantSearchResult = merchantManager.search(map, pagination);
		List<Merchant> merchants = merchantSearchResult.getMerchants();
		for (Merchant it : merchants) {
			it.setFavourPloy(salePloyManager.isExistMerchantPloy(it));
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("merchants", merchants);
		model.put("areas", merchantSearchResult.getAreas());
		model.put("categories", merchantSearchResult.getCategories());
		model.put("preferentialCount", merchantSearchResult.getPreferentialCount());

		model.put("pagination", pagination);
		return new ModelAndView(new JsonView("searchmerchant", model));
	}

	/**
	 * 商户页面 根据商户id查询
	 * 
	 * @return 商户基本信息以及商户统计相关信息 名称，星级，地址，联系电话，营业时间，类别，详细描述，地址坐标，点评，会员，收藏，优惠状态
	 */
	@Cache
	@RequestMapping(value = "/merchant/{id}/index.htm", method = RequestMethod.GET)
	public ModelAndView getMerById(@PathVariable("id")
	Long id) {
		Merchant merchant = merchantManager.get(id);
		merchant.setFavourPloy(salePloyManager.isExistMerchantPloy(merchant));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("merchant", merchant);
		return new ModelAndView(new JsonView("webMerchant", model));
	}

	/**
	 * 商户页面 根据指定商户取得商户营销活动列表---分页查询
	 * 
	 * @author unicorn
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value = "/merchant/{id}/saleploy.htm", method = RequestMethod.GET)
	public ModelAndView findSaleply(@PathVariable("id")
	Long id) {
		Merchant merchant = merchantManager.get(id);
		List<SalePloy> list = salePloyManager.find(merchant);
		return new ModelAndView(new JsonView("saleploy", list));
	}

	/**
	 * 商户页面 根据指定商户取得商户最新发布的一条营销活动
	 * 
	 * @author unicorn
	 * @return
	 */
	@RequestMapping(value = "/merchant/{id}/saleploy/newly.htm", method = RequestMethod.GET)
	public ModelAndView findNewlySalePloy(@PathVariable("id")
	Long id) {
		Merchant merchant = merchantManager.get(id);
		List<SalePloy> list = salePloyManager.getNewlySalePloy(merchant);
		return new ModelAndView(new JsonView("saleploy", list));
	}

	/**
	 * 查找商户的附近商户
	 * 
	 * @author jianguo.xu
	 * @return
	 */
	@RequestMapping(value = "/merchant/{id}/near.htm")
	public ModelAndView findByNear(@PathVariable
	Long id) {
		Merchant merchant = merchantManager.get(id);
		List<Merchant> merchants = merchantManager.findByNear(merchant, 5);
		for (Merchant it : merchants) {
			it.setFavourPloy(salePloyManager.isExistMerchantPloy(it));
		}
		return new ModelAndView(new JsonView("merchantList", merchants));
	}
}