package com.vlives.boss.comment.manager;

import java.util.List;
import java.util.Map;

import com.vlives.boss.comment.domain.MerchantComment;
import com.vlives.boss.comment.domain.MerchantComment.MerchantGrade;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.sms.domain.SmsRecord;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.pagination.Pagination;

public interface MerchantCommentManager {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public MerchantComment get(Long id);

	/**
	 * 创建商户评论
	 * 
	 * @param merchantComment
	 */
	public void create(MerchantComment merchantComment);

	/**
	 * 创建系统评论
	 * 
	 * @param tradeDetail
	 */
	public void createSysComment(TradeDetail tradeDetail);
	
	/**
	 * 创建短信评论
	 * 评论会员最后消费一个记录
	 * @param smsRecord
	 */
	public void  createSmsComment(SmsRecord smsRecord);
	
	/**
	 * 修改评论
	 */
	public void modifyComment(MerchantComment merchantComment,MerchantGrade merchantGrade)throws BusinessException;
	
	/**
	 * 创建回复
	 * @param merchantComment
	 * @param merchant
	 * @throws BusinessException
	 */
	public void createReply(MerchantComment merchantComment,Merchant merchant)throws BusinessException;

	/**
	 * 查询评论信息
	 * 
	 * @param param
	 * @return
	 */
	public List<MerchantComment> find(Map<String, Object> param, Pagination pagination);
	
	public long count(Map<String, Object> param);
	
	/**
	 * 获取消费交易的评论
	 * @param tradeDetail
	 * @return
	 */
	public MerchantComment getByTradeDetail(TradeDetail tradeDetail);

	/**
	 * 查询最新的评论
	 * @param maxResults
	 * @return
	 */
	public List<MerchantComment> find(int maxResults);
	
	
}
