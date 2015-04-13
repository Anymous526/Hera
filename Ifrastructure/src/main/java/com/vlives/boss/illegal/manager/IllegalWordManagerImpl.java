package com.vlives.boss.illegal.manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.illegal.domain.IllegalWord;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.Finder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

@Service("illegalWordManager")
public class IllegalWordManagerImpl implements IllegalWordManager {

	@Resource
	private BaseDao<IllegalWord, Long> illegalWordDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IllegalWord getId(Long id) {
		return illegalWordDao.get(id);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(IllegalWord illegalWord) throws BusinessException, IOException, DocumentException {
		isExistByName(illegalWord.getContent());
		illegalWord.setContent(illegalWord.getContent());
		illegalWord.setCreateDate(new Date());
		illegalWordDao.save(illegalWord);	
	}

	private void isExistByName(String content) throws BusinessException{
		if(illegalWordDao.isExisted("content", content))
			throw new BusinessException("违禁字已经存在！");
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IllegalWord> find(Map<String, Object> map, Pagination pagination) {

		String dynamicString = " from IllegalWord where 1=1" +
				"{ and content like :content} ";
		Finder finder = new DynamicFinder(dynamicString,map);
		return illegalWordDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(IllegalWord illegalWord) throws IOException, DocumentException {

		illegalWordDao.delete(illegalWord);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IllegalWord> findAll() {
		PropertiesFinder fider = new PropertiesFinder(IllegalWord.class);
		fider.setCacheable(true);
		return illegalWordDao.find(fider);
	}



}
