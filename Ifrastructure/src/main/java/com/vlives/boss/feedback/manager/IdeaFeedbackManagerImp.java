package com.vlives.boss.feedback.manager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.feedback.domain.IdeaFeedback;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.pagination.Pagination;
import com.vlives.util.web.interceptor.ObjectConvertAnno;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-6-28 上午09:14:20 类说明
 */

@Service("ideaFeedbackManager")
public class IdeaFeedbackManagerImp implements IdeaFeedbackManager {

	@Resource
	private BaseDao<IdeaFeedback, Long> ideaFeedbackDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IdeaFeedback get(Long id) {
		return ideaFeedbackDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(IdeaFeedback objFeedback) {
		ideaFeedbackDao.save(objFeedback);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IdeaFeedback> find(Map<String, Object> params,@ObjectConvertAnno Pagination pagination) {
		DynamicFinder finder = constFinder(params);
		return ideaFeedbackDao.find(finder,pagination);
	}

	/**
	 * 定义查找反馈意见信息的方法
	 * 
	 * @param params
	 * @return
	 */
	private DynamicFinder constFinder(Map<String, Object> params) {
		if (params.get("name") != null) {
			params.put("name", "%"+params.get("name") + "%");
		}
		String _sql = "FROM IdeaFeedback FE WHERE 1=1 "
				+ " { AND FE.user = :user }"
				+ " { AND FE.createName like :name}"
				+ " { AND FE.createDate >= :cur_date}";
		DynamicFinder finder = new DynamicFinder(_sql, params);
		return finder;
	}

}
