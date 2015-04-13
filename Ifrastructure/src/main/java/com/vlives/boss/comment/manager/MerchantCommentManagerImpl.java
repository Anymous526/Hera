package com.vlives.boss.comment.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vlives.boss.comment.domain.CommentSource;
import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.dao.generic.finder.DynamicFinder;
import com.vlives.core.dao.generic.finder.PropertiesFinder;
import com.vlives.core.dao.generic.finder.SimpleParametersFinder;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

@Service("merchantCommentManager")
public class MerchantCommentManagerImpl implements MerchantCommentManager {

	private static final Log LOG = LogFactory.getLog(MerchantCommentManagerImpl.class);
	@Resource
	private BaseDao<MerchantComment, Long> merchantCommentDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MerchantComment get(Long id) {
		return merchantCommentDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void create(MerchantComment merchantComment) {

		merchantComment.setModifyed(false);
		merchantComment.getMerchant().getMerchantReferenceStatistic().addCommentCount(merchantComment);
		merchantCommentDao.save(merchantComment);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void createSysComment(TradeDetail tradeDetail) {
		MerchantComment comment = new MerchantComment();
		TradeOrder tradeOrder = tradeDetail.getTradeOrder();
		comment.setTradeDetail(tradeDetail);
		comment.setCreateDate(tradeDetail.getTradeDate());
		comment.setMember(tradeOrder.getMember());
		comment.setMerchant(tradeOrder.getMerchant());
		comment.setMerchantGrade(MerchantGrade.FIVE_GRADE);
		comment.setComments(MerchantGrade.FIVE_GRADE.getComments());
		comment.setSource(CommentSource.SOURCE_SYSTEM);
		comment.setModifyed(false);
		comment.getMerchant().getMerchantReferenceStatistic().addCommentCount(comment);
		merchantCommentDao.save(comment);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MerchantComment> find(Map<String, Object> params, Pagination pagination) {
		DynamicFinder finder = constFinder(params);
		return merchantCommentDao.find(finder, pagination);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MerchantComment getByTradeDetail(TradeDetail tradeDetail) {
		PropertiesFinder finder = new PropertiesFinder(MerchantComment.class);
		finder.add("tradeDetail", tradeDetail);
		return merchantCommentDao.getBy(finder);
	}

	private DynamicFinder constFinder(Map<String, Object> params) {
		if (params.get("like_merchantName") != null) {
			params.put("like_merchantName", "%" + params.get("like_merchantName") + "%");
		}
		String hql = "from MerchantComment m where 1=1" + "{ and m.merchantGrade = :merchantGrade}"
				+ "{ and m.merchant = :merchant}" + "{ and m.merchant.code = :merchantCode}"
				+ "{ and m.merchant.name like :like_merchantName}" + "{ and m.member.level = :level}"
				+ "{ and m.member.user = :user}" + "{ and m.member.user.mobile = :mobile}"
				+ "{ and  m.createDate>=:startCreateDate}"
				+ "{ and  m.createDate<=:endCreateDate}  order by  m.id desc ";
		DynamicFinder finder = new DynamicFinder(hql, params);
		return finder;
	}

	@Override
	public List<MerchantComment> find(int maxResults) {
		String hql = "from MerchantComment mc order by mc.createDate desc";
		SimpleParametersFinder finder = new SimpleParametersFinder(hql, null, null);
		return merchantCommentDao.find(finder, maxResults);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long count(Map<String, Object> param) {
		DynamicFinder finder = constFinder(param);
		return merchantCommentDao.count(finder);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void modifyComment(MerchantComment merchantComment, MerchantGrade merchantGrade) throws BusinessException {
		if (merchantComment.isModifyed()) {
			throw new BusinessException("该评论已修改过");
		}
		merchantComment.setModifyed(true);
		merchantComment.getMerchant().getMerchantReferenceStatistic().removeCommentCount(merchantComment);
		merchantComment.setMerchantGrade(merchantGrade);
		merchantComment.getMerchant().getMerchantReferenceStatistic().addCommentCount(merchantComment);
		merchantCommentDao.update(merchantComment);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createReply(MerchantComment merchantComment, Merchant merchant) throws BusinessException {
		if (!merchantComment.getMerchant().equals(merchant)) {
			throw new BusinessException("商户不正确");
		}
		merchantComment.setReplyDate(new Date());
		merchantCommentDao.update(merchantComment);

	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void createSmsComment(SmsRecord smsRecord) {
		User user = userManager.getByMobile(smsRecord.getMobile());
		if (user == null) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		Pagination pagination = new Pagination();
		pagination.setPageSize(1);
		List<TradeDetail> details = tradeDetailManager.findNoCommentTradeDetails(map, pagination);
		if (CollectionUtils.isEmpty(details)) {
			return;
		}
		TradeDetail tradeDetail = details.get(0);
		if (tradeDetail == null) {
			return;
		}
		MerchantComment comment = new MerchantComment();
		comment.setTradeDetail(tradeDetail);
		comment.setMerchant(tradeDetail.getTradeOrder().getMerchant());
		comment.setMember(tradeDetail.getTradeOrder().getMember());
		comment.setSource(CommentSource.SOURCE_SMS);
		comment.setCreateDate(smsRecord.getSendDate());
		try {
			this.setComment(smsRecord.getContent(), comment);
		} catch (BusinessException e) {
			return;
		}
		this.create(comment);

	}

	private void setComment(String content, MerchantComment comment) throws BusinessException {
		try {
			int index = Integer.parseInt(content.trim().substring(0, 1));
			content = content.trim().substring(1);
			if (content.indexOf("#") == 0) {
				content = content.trim().substring(1);
			}
			MerchantGrade merchantGrade = MerchantGrade.get(index);
			if(StringUtils.isBlank(content)){
				content=merchantGrade.getComments();
			}
			comment.setComments(content);
			comment.setMerchantGrade(merchantGrade);
		} catch (Exception e) {
			LOG.error("短信评论错误：" + content);
			throw new BusinessException("短信评论错误");
		}
	}

}
