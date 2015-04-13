package com.vlives.boss.merchant.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.merchant.domain.MerchantCategory;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.exception.BusinessException;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-7-7 上午08:07:15
 * 类说明
 */
@Service("merchantCategoryManager")
public class MerchantCategoryManagerImp implements MerchantCategoryManager {
	
	@Resource
	private BaseDao<MerchantCategory, Long> merchantCategoryDao;
	
	
	/**
	 * 根据当前分类编号，获取分类对象
	 * @param id
	 * @return
	 */
	@Override
	@Transactional (readOnly = true,rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
	public MerchantCategory getById(Long id){
		MerchantCategory merchantCategory = merchantCategoryDao.get(id);
		return merchantCategory;
	}
	
	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED) 
	public void create(Long parentId,String name) throws BusinessException{
		MerchantCategory parent = this.getById(parentId);
		if(parent.getChildrenByName(name)!=null) {
			throw new BusinessException("商户类型名已存在！");
		}
		MerchantCategory chilren = new MerchantCategory();
		chilren.setName(name);
		chilren.setParent(parent);
		parent.getChildrens().add(chilren);
		merchantCategoryDao.save(chilren);
	}

	/**
	 *  商户分类删除
	 *  @param merchantCategory 商户分类
	 */
	@Override
	@Transactional(readOnly=false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void delete(MerchantCategory merchantCategory){
		merchantCategoryDao.delete(merchantCategory);
	}

	@Override
	@Transactional(readOnly=true,rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
	public List<MerchantCategory> findCategoryBy(Long parentId){
		MerchantCategory parent =  this.getById(parentId);
		Finder finder = new PropertiesFinder(MerchantCategory.class,"parent",parent);
		return merchantCategoryDao.find(finder);
	}

}
