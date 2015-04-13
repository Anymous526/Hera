package com.vlives.site.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.dto.MerchantSearchResult;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.sale.manager.SalePloyManager;
import com.vlives.core.pagination.Pagination;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;
import com.vlives.site.entity.CityObject;
import com.vlives.site.util.CityHelper;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

@Controller
public class SearchController {
	@Autowired
	private MerchantManager merchantManager;
	@Autowired
	private SalePloyManager salePloyManager;
	
	/**
	 * 搜索: 根据商户全称, 简称, 地址, 所在区域, 商户类型查询
	 * 
	 * @return 商户列表
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/search")
	public ModelAndView search(@ObjectConvertAnno Pagination pagination)
			throws UnsupportedEncodingException {
		return new ModelAndView("/search.jsp");
	}

	/**
	 * 搜索: 根据商户全称, 简称, 地址, 所在区域, 商户类型查询
	 * 
	 * @return 商户列表
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/ajax/search")
	public ModelAndView ajaxSearch(@ObjectConvertAnno Pagination pagination)
			throws UnsupportedEncodingException {
		CityObject city = CityHelper.getCurrentCity();

		Map<String, Object> map = new HashMap<String, Object>();
		String name = HttpParameterParser.getString("name");
		Long[] areas = HttpParameterParser.getLongWrapperArray("area");
		Long[] categories = HttpParameterParser.getLongWrapperArray("category");
		String sort = HttpParameterParser.getString("sort");

		if (name != null) {
			map.put("name", URLDecoder.decode(name, "UTF-8"));
		}
		
		if (areas != null) {
			map.put("area", areas);
		}

		if (categories != null) {
			map.put("category", categories);
		}

		if (sort != null) {
			map.put("sort", sort);
		}

		map.put("searchArea", city.getCity());

		MerchantSearchResult merchantSearchResult = merchantManager.search(map,
				pagination);
		List<Merchant> merchants = merchantSearchResult.getMerchants();
		for (Merchant it : merchants) {
			it.setFavourPloy(salePloyManager.isExistMerchantPloy(it));
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("merchants", merchants);
		model.put("areas", merchantSearchResult.getAreas());
		model.put("categories", merchantSearchResult.getCategories());
		model.put("preferentialCount",
				merchantSearchResult.getPreferentialCount());

		model.put("pagination", pagination);
		return new ModelAndView(new JsonView("search.merchants", model));
	}
}
