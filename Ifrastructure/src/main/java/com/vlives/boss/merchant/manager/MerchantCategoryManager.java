package com.vlives.boss.merchant.manager;

import java.util.List;

import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.core.exception.BusinessException;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-6 上午10:41:25
 * 类说明
 */

public interface MerchantCategoryManager{

	/**
	 * 根据当前分类编号，获取分类对象
	 * @param id
	 * @return
	 */
	public MerchantCategory getById(Long id);

	/**
	 * 根据父类创建子类，如果不穿父id则在root根目录下创建子类
	 * @param parentId 父类id
	 * @param name 子类名称
	 */
	public void create(Long parentId,String name) throws BusinessException;
	

	/**
	 *  商户分类删除
	 *  @param merchantCategory 商户分类
	 *  @author timmy cheung
	 */
	public void delete(MerchantCategory merchantCategory);

	/**
	 * 根据商户父类型查找子类型
	 * @param parent
	 * @return 返回类型集合
	 */
	public List<MerchantCategory> findCategoryBy(Long parentId);
	
	
}

