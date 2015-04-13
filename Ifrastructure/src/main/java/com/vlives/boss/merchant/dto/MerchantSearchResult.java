/**
 * @(#)MerchantSearchResult.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.boss.merchant.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vlives.boss.area.domain.Area;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.domain.MerchantCategory;

/**
 * 商户搜索结果类
 * @author  jianguo.xu
 * @version 1.0,2011-7-16
 */
public class MerchantSearchResult {
	
	public MerchantSearchResult(List<Merchant> merchants,
			Map<Area, Long> areas, Map<MerchantCategory, Long> categories,long preferentialCount) {
		this.merchants = merchants;
		this.areas = new ArrayList<MerchantSearchResult.AreaWrapper>();
		for(Area area : areas.keySet()) {
			this.areas.add(new AreaWrapper(area, areas.get(area)));
		}
		this.categories = new ArrayList<MerchantSearchResult.CategoryWrapper>();
		for(MerchantCategory category:categories.keySet()){
			this.categories.add(new CategoryWrapper(category, categories.get(category)));
		}
		this.preferentialCount = preferentialCount;
	}
	/**
	 * 商户集合
	 */
	private final List<Merchant> merchants;
	/**
	 * 所以在区域数量
	 * 内部按数量进行倒排
	 */
	private final List<AreaWrapper> areas;
	/**
	 * 商户所在分类数量
	 * 内部按分类数倒排
	 */
	private final  List<CategoryWrapper> categories;
	/**
	 * 优惠商户数
	 */
	private final long preferentialCount;
	
	public long getPreferentialCount() {
		return preferentialCount;
	}

	public List<Merchant> getMerchants() {
		return merchants;
	}

	public List<AreaWrapper> getAreas() {
		return areas;
	}

	public List<CategoryWrapper> getCategories() {
		return categories;
	}

	public static class AreaWrapper{
		private final Area area;
		private final long merchantCount;
		
		public AreaWrapper(Area area, long merchantCount) {
			super();
			this.area = area;
			this.merchantCount = merchantCount;
		}
		public Area getArea() {
			return area;
		}
		public long getMerchantCount() {
			return merchantCount;
		}
	}
	
	public static class CategoryWrapper{
		private final MerchantCategory category;
		private final long merchantCount;
		
		public CategoryWrapper(MerchantCategory category, long merchantCount) {
			super();
			this.category = category;
			this.merchantCount = merchantCount;
		}
		public MerchantCategory getCategory() {
			return category;
		}
		public long getMerchantCount() {
			return merchantCount;
		}
		
		
		
	}
	
}
