package com.vlives.boss.border.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.border.domain.Border;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.dao.generic.finder.SimpleQueryStringFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

@Service("borderManager")
public class BorderManagerImpl implements BorderManager {

	@Resource
	private BaseDao<Border, Long> borderDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Border getId(Long id) {
		return borderDao.get(id);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(Border border) throws BusinessException {
		isExistByName(border.getName());
		border.setCreateDate(new Date());
		borderDao.save(border);		
	}

	private void isExistByName(String name) throws BusinessException{
		if(borderDao.isExisted("name", name))
			throw new BusinessException(name+": 公告已经存在！");
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Border> find(Map<String, Object> map, Pagination pagination) {

		String dynamicString = " from Border where 1=1" +
		"{ and id =:id} "+
		"{ and type in (:type)} "+
		"{ and name like :name} "+
		" order by createDate desc ";
		Finder finder = new DynamicFinder(dynamicString,map);
		return borderDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Border> find(int count) {
		String sql = " from Border where type in (1,3) order by createDate desc ";
		Finder finder = new SimpleQueryStringFinder(sql);
		return borderDao.find(finder, count);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(Border border) {

		borderDao.delete(border);
		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Border> newsBorder(int maxSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Finder finder = new SimpleParametersFinder(" from Border where type in (2,3) order by createDate desc ", map);
		return borderDao.find(finder, maxSize);
	}
	

}
