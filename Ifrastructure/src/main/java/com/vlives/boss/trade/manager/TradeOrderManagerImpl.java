package com.vlives.boss.trade.manager;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.MemberPointDetailManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.PointRuleManager;
import com.vlives.boss.merchant.manager.UpdateRuleItemManager;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.manager.exception.TradeExistException;
import com.vlives.core.dao.generic.BaseDao;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

@Service("tradeOrderManager")
public class TradeOrderManagerImpl implements TradeOrderManager {

	private static final Log LOG = LogFactory.getLog(TradeOrderManagerImpl.class);

	@Resource
	private BaseDao<TradeOrder, Long> tradeOrderDao;
	@Autowired
	private UpdateRuleItemManager updateRuleItemManager;
	@Autowired
	private PointRuleManager pointRuleManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private MemberPointDetailManager memberPointDetailManager;
	@Autowired
	private MemberManager memberManager;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TradeOrder getId(Long id) {
		return tradeOrderDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(TradeOrder tradeOrder) {
		tradeOrderDao.save(tradeOrder);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void update(TradeOrder tradeOrder) {
		tradeOrderDao.update(tradeOrder);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void pointTrade(TradeBatch tradeBatch, Member member, TradeOrder tradeOrder, TradeDetail tradeDetail)
			throws PosBussinessException, TradeExistException {
		TradeDetail oldTradeDetail = tradeDetailManager.getTradeDetail(tradeBatch, tradeDetail.getTradeSerialNo());
		// 判读是否重复订购
		if (oldTradeDetail != null) {
			LOG.info("重复消费");
			tradeDetail=oldTradeDetail;
			throw new TradeExistException("该消费交易已存在");

		}

		TradeDetail cancelTradeDetail = tradeDetailManager.getCancelTradeDetail(tradeBatch, tradeDetail
				.getTradeSerialNo());
		if (cancelTradeDetail != null && !cancelTradeDetail.getTradeOrder().isConsumeTrade()) {
			// 如果退货订单已存在，则是先退货。
			LOG.info("先退货后消费交易处理");
			TradeOrder cancelOrder = cancelTradeDetail.getTradeOrder();
			cancelOrder.setMoney(tradeDetail.getAmount());
			cancelOrder.setType(tradeOrder.getType());
			cancelOrder.setTradeDate(tradeOrder.getTradeDate());
			tradeOrderDao.update(cancelOrder);

			this.createTradeDetail(tradeBatch, cancelOrder, tradeDetail, true);
			return;
		}

		createOrder(tradeBatch, member, tradeOrder, tradeDetail, true);
		Merchant merchant = tradeBatch.getPos().getMerchant();

		if (tradeOrder.getMoney().compareTo(new BigDecimal("0")) > 0) {
			increasePoint(member, tradeOrder, merchant);
			updateRuleItemManager.upMemberGrade(member, tradeOrder.getMoney(), merchant);
		}
	}

	private void increasePoint(Member member, TradeOrder tradeOrder, Merchant merchant) {
		if (tradeOrder.getMoney().compareTo(new BigDecimal("0")) > 0) {
			int point = pointRuleManager.getIncreasePoint(member, tradeOrder.getMoney(), merchant);
			LOG.info("会员增加积分：" + point);
			member.increasePoint(point);
			member.setLastConsumeDate(new Date());
			memberPointDetailManager.createDetail(member, tradeOrder, point, MemberPointDetail.Type.TYPE_REGISTER, "积分增加");
			memberManager.update(member);
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void pointCancel(TradeBatch tradeBatch, TradeBatch originalBatch, Member member, TradeOrder tradeOrder,
			TradeDetail tradeDetail) throws PosBussinessException {
		TradeDetail oldTradeDetail = tradeDetailManager.getTradeDetail(tradeBatch, tradeDetail.getTradeSerialNo());
		// 判断是否重复退货
		if (oldTradeDetail != null) {
			LOG.info("重复退货");
			return;
		}

		// 通过原批次，和流水号找原消费明细
		TradeDetail origTradeDetail = tradeDetailManager.getTradeDetail(originalBatch, tradeDetail
				.getOriginalSerailNo());
		// 没找原消费记录，则是先退货
		if (origTradeDetail == null) {
			LOG.info("先退货后处理");
			tradeDetail.setOriginalTradeBatch(originalBatch);
			createOrder(tradeBatch, member, tradeOrder, tradeDetail, false);
			return;
		}

		// 再次验证是否通过其他POS退货，发现已退货。
		if (!origTradeDetail.getTradeOrder().isConsumeTrade()) {
			return;
		}

		cancelMemberPoint(tradeBatch, originalBatch, member, tradeDetail, origTradeDetail);
	}

	/**
	 * 验证完后，减少会员积分
	 * 
	 * @param tradeBatch
	 * @param originalBatch
	 * @param member
	 * @param tradeDetail
	 * @param origTradeDetail
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void cancelMemberPoint(TradeBatch tradeBatch, TradeBatch originalBatch, Member member,
			TradeDetail tradeDetail, TradeDetail origTradeDetail) throws PosBussinessException {
		TradeOrder origTradeOrder = origTradeDetail.getTradeOrder();
		if (!origTradeOrder.getMember().equals(member)) {
			throw new PosBussinessException(PosReturnCode.CANECL_TRADE_MEMBER_NOT_RIGHT);
		}

		// 找到原消费记录，则创建退货
		MemberPointDetail pointDetail = memberPointDetailManager.getByMemberAndOrder(member, origTradeOrder,
				MemberPointDetail.Type.TYPE_REGISTER);
		decreasePoint(member, origTradeOrder, pointDetail.getPoint());

		origTradeOrder.setConsumeTrade(false);
		this.update(origTradeOrder);
		tradeDetail.setOriginalTradeBatch(originalBatch);
		this.createTradeDetail(tradeBatch, origTradeOrder, tradeDetail, false);
	}

	private void decreasePoint(Member member, TradeOrder origTradeOrder, int point) {
		member.decrease(point);
		memberPointDetailManager.createDetail(member, origTradeOrder, (0 - point), MemberPointDetail.Type.TYPE_BUY_POINT, "退货积分减少");
		memberManager.update(member);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void createOrder(TradeBatch tradeBatch, Member member, TradeOrder tradeOrder, TradeDetail tradeDetail,
			boolean isConsume) {
		tradeOrder.setMember(member);
		tradeOrder.setConsumeTrade(isConsume);
		tradeOrder.setCreateDate(new Date());
		tradeOrderDao.save(tradeOrder);
		createTradeDetail(tradeBatch, tradeOrder, tradeDetail, isConsume);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void createTradeDetail(TradeBatch tradeBatch, TradeOrder tradeOrder, TradeDetail tradeDetail,
			boolean isConsume) {
		tradeDetail.setTradeBatch(tradeBatch);
		tradeDetail.setTradeOrder(tradeOrder);
		tradeDetail.setConsumeTrade(isConsume);
		tradeDetail.setCreateDate(new Date());
		tradeDetailManager.save(tradeDetail);
	}

}
