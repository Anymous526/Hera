package com.vlives.boss.feedback.dao;

import org.springframework.stereotype.Repository;

import com.vlives.boss.feedback.domain.IdeaFeedback;
import com.vlives.core.dao.generic.BaseDaoHibernateImpl;

/**
 * @author Timmy Cheung
 * @version 创建时间：2011-6-28 上午05:52:37
 * 类说明
 */

@Repository("ideaFeedbackDao")
public class IdeaFeedbackDao extends BaseDaoHibernateImpl<IdeaFeedback,Long> {

}
