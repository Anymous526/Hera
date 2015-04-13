package com.vlives.boss.merchant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.boss.merchant.manager.MerchantCategoryManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.support.spring.vaildator.ParamValidator;
import com.vlives.core.support.spring.vaildator.ParamValidators;
import com.vlives.core.support.spring.vaildator.ValidatorType;
import com.vlives.core.support.spring.view.JsonView;
import com.vlives.core.web.HttpParameterParser;

/**
 * 商户行业类型 ----运营管理平台接口
 * @author Timmy Cheung
 * @version 创建时间：2011-7-8 上午08:46:50 类说明
 */

@Controller
public class MerchantCategoryController {

	@Autowired
	private MerchantCategoryManager merchantCategoryManager;
	@Autowired
	private MerchantManager merchantManager;
	/**
	 * 创建一个商户分类信息
	 * 如果父类型id不为空，则在父类下创建子类型，否则在root根目录下创建子类型
	 * @param parentId 父类型
	 * @param name	新增行业名字
	 * @return
	 */
	@RequestMapping(value="/manager/platform/merchantcategory/create.htm",method=RequestMethod.POST)
	public ModelAndView createMerchantCategory() {
		Long parentId = HttpParameterParser.getLong("parentId");
		String name = HttpParameterParser.getString("name");
		try {
			merchantCategoryManager.create(parentId, name);
			return new ModelAndView(new JsonView(true,"商户分类信息添加成功！"));
		} catch (BusinessException be) {
			be.printStackTrace();
			return new ModelAndView(new JsonView(false, be.getMessage()));
		}
	}
	/**
	 * 删除商户分类信息，同时会删除当前商户的子分类
	 * @param id 当前商户编号
	 * @return
	 */
	@ParamValidators({
		@ParamValidator(param="id",paramName="商户行业类型ID",vaildatorTypes={ValidatorType.REQUIRED})
		})
	@RequestMapping(value="/manager/platform/merchantcategory/delete.htm")
	public ModelAndView deleteMerchantCategory(){
		Long id = HttpParameterParser.getLong("id");
		MerchantCategory merchantCategory = merchantCategoryManager.getById(id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("category", merchantCategory);
		List<Merchant> list = merchantManager.find(map);
		for(Merchant m : list) {
			m.setCategory(null);
		}
		merchantCategoryManager.delete(merchantCategory);
		return new ModelAndView(new JsonView(true,"商户分类删除成功！"));		
	}
	
	/**
	 * 根据父商户查询商户行业类型---如果有父类id返回父类的第一级子类
	 * 如果没有则返回root根目录下第一级子类型
	 * @author unicorn
	 * @return
	 */
	@RequestMapping(value="/manager/platform/merchantcategory/index.htm",method=RequestMethod.GET)
	public ModelAndView findMerchantCategoryBy(){
		Long parentId = HttpParameterParser.getLong("parentId");
		List<MerchantCategory> categories = merchantCategoryManager.findCategoryBy(parentId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("categories", categories);
		return new ModelAndView(new JsonView("categories",result));
	}
	
}
