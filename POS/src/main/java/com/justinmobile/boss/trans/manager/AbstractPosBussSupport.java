package com.justinmobile.boss.trans.manager;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.util.PlatformCaller;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.CouponVerifyTransaction;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.exception.MemberException;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.sms.manager.SmsTemplateManager;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeTransaction;
import com.vlives.boss.trade.domain.TradeBatch.Status;
import com.vlives.boss.trade.domain.TradeTransaction.TradeReqType;
import com.vlives.boss.trade.manager.PosTradeLogManager;
import com.vlives.boss.trade.manager.TradeBatchManager;
import com.vlives.boss.trade.manager.TradeTransactionManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

public abstract class AbstractPosBussSupport {

	private static final Log LOG = LogFactory.getLog(AbstractPosBussSupport.class);

	@Autowired
	protected PosTradeLogManager posTradeLogManager;
	@Autowired
	protected TradeTransactionManager tradeTransactionManager;
	@Autowired
	protected TradeBatchManager tradeBatchManager;
	@Autowired
	protected MemberManager memberManager;
	@Autowired
	protected UserManager userManager;
	@Autowired
	protected SmsTemplateManager smsSendManager;
	
	/**
	 * 结算对账 totalCountB 总金额和总笔数是包括 ， 退货和消费
	 * 
	 */
	protected void accountCheck(TradeBatch tradeBatch, Hs8583ClosingCostDto dto) throws PosBussinessException {
		Object[] consumeTotal = tradeTransactionManager.getConsumeTotal(tradeBatch.getPos().getCode(),
				tradeBatch.getBatchNumber());
		Object[] cancelTotal = tradeTransactionManager.getCancelTradeTotal(tradeBatch.getPos().getCode(),
				tradeBatch.getBatchNumber());

		long totalCountB = ((BigDecimal) consumeTotal[0]).longValue();
		BigDecimal totalAmountB = consumeTotal[1] == null ? new BigDecimal("0") : (BigDecimal) consumeTotal[1];
		long cancelTotalCountB = ((BigDecimal) cancelTotal[0]).longValue();
		BigDecimal canceltotalAmountB = cancelTotal[1] == null ? new BigDecimal("0") : (BigDecimal) cancelTotal[1];

		if (dto.getConsumeCount() != totalCountB) {
			LOG.info(dto.getConsumeCount() + "!=" + totalCountB);
			throw new PosBussinessException(PosReturnCode.TOTAL_COUNT_NOT_EQUAL);
		}
		if (dto.getConsumeAmount() != totalAmountB.longValue()) {
			LOG.info(dto.getConsumeAmount() + "!=" + totalAmountB);
			throw new PosBussinessException(PosReturnCode.TOTAL_AMOUNT_NOT_EQUAL);
		}
		if (dto.getReturnCount() != cancelTotalCountB) {
			LOG.info(dto.getReturnCount() + "!=" + cancelTotalCountB);
			throw new PosBussinessException(PosReturnCode.C_TOTAL_COUNT_NOT_EQUAL);
		}
		if (dto.getReturnAmount() != (canceltotalAmountB.longValue())) {
			LOG.info(dto.getReturnAmount() + "!=" + canceltotalAmountB);
			throw new PosBussinessException(PosReturnCode.C_TOTAL_AMOUNT_NOT_EQUAL);
		}
	}

	protected TradeTransaction createTradeTransaction(Hs8583BusinessUploadInfo dto, String batchNo) {
		Integer batch = batchNo == null ? null : Integer.valueOf(batchNo);
		TradeTransaction trade = new TradeTransaction();
		trade.setMobileNo(dto.getMobileNo());
		trade.setPosNo(dto.getTerminalNo());
		trade.setAmount(dto.getAmount());
		trade.setBatchNo(batch);
		trade.setTradeSerialNo(dto.getTerminalSerial());
		trade.setTradeDate(dto.getTradeDate());
		trade.setOriginalBatchNo(dto.getOriginalBatchNo() == null ? null : Integer.valueOf(dto.getOriginalBatchNo()));
		trade.setOriginalPosNo(dto.getOriginalTerminalNo());
		trade.setOriginalSerialNo(dto.getOriginalSerial());
		trade.setTradeReqType(TradeReqType.get(dto.getTransType()));
		trade.setBankCardNo(dto.getBankCardNo());
		trade.setCreateDate(new Date());
		return tradeTransactionManager.create(trade);

	}
	
	protected CouponVerifyTransaction generateCouponVerifyTransaction(Hs8583Dto request,Pos pos,Coupon coupon){
		TradeBatch tradeBatch=tradeBatchManager.createBatch(pos, Integer.valueOf(request.getBm30BatchNo()));
		CouponVerifyTransaction couponVerifyTransaction=new  CouponVerifyTransaction();
		couponVerifyTransaction.setSerialNo(request.getBm6TradeSerialNo());
		couponVerifyTransaction.setTradeBatch(tradeBatch);
		couponVerifyTransaction.setCoupon(coupon);
		couponVerifyTransaction.setVerifyDate(new Date());
		couponVerifyTransaction.setVerifyMerchant(tradeBatch.getPos().getMerchant());
		return couponVerifyTransaction;
	}
	

	protected PosTradeLog createLog(Hs8583BusinessUploadInfo busInfo, PosTradeLog posTradeLog) {
		PosTradeLog log = new PosTradeLog();
		log.setPosNo(busInfo.getTerminalNo());
		log.setMobileNo(busInfo.getMobileNo());
		log.setRequestData(busInfo.toString());
		log.setParentPosTradeLog(posTradeLog);
		posTradeLogManager.createLog(log);
		return log;
	}

	protected void checkUserExist(User user) throws PosBussinessException {
		if (user == null) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_EXIST);
		}
	}

	protected void checkExist(Pos pos) throws PosBussinessException {
		if (pos == null) {
			throw new PosBussinessException(PosReturnCode.POS_NOT_EXIST);
		}

	}

	protected void checkMemberExist(Member member) throws PosBussinessException {
		if (member == null) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_EXIST);
		}
	}

	protected void checkBatchNoValid(Pos pos, Integer batchNo) throws PosBussinessException {
		if (batchNo > pos.getBatchNumber()) {
			throw new PosBussinessException(PosReturnCode.BATCH_NO_NOT_VALID);
		}
	}
	
	protected void checkUserValid(User user) throws PosBussinessException {
		this.checkUserExist(user);
		if (!user.isValid()) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
	}

	protected void checkBatch(TradeBatch tradeBatch) throws PosBussinessException {
		if (tradeBatch == null) {
			throw new PosBussinessException(PosReturnCode.BATCH_NUMBER_NOT_EXIST);
		}
		if (tradeBatch.getStatus() == Status.TYPE_BUY_POINT) {
			throw new PosBussinessException(PosReturnCode.BATCH_STATUS_BUY_POINT);
		}
	}
	
	/**
	 * 根据手机获取会员 并检查是否存在
	 * 
	 * @param mobileNo
	 * @param pos
	 * @return
	 * @throws PosBussinessException
	 */
	protected Member getMember(String mobileNo, Merchant merchant) throws PosBussinessException {
		User user = userManager.getByMobile(mobileNo);
		this.checkUserExist(user);
		Member member = memberManager.getBy(user, merchant.getMemberGroup());
		this.checkMemberExist(member);
		return member;
	}
	
	/**
	 * 创建会员 消费时如果会员不存在则创建会员
	 * 
	 * @param mobileNo
	 * @param merchant
	 * @return
	 * @throws PosBussinessException
	 */
	protected Member createMember(String mobileNo, Merchant merchant) throws PosBussinessException {
		Member member = null;
		try {
			return member = this.getMember(mobileNo, merchant);
		} catch (PosBussinessException e) {
			try {
				member = memberManager.createByPos(mobileNo, merchant);
			//	smsSendManager.registerMemberSms(member);
				PlatformCaller.createPosMemberSms(member.getId());
				return member;
			} catch (MemberException e1) {
			}
		}
		return member;

	}
}
