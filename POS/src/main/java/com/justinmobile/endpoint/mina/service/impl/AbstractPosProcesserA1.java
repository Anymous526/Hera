package com.justinmobile.endpoint.mina.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.justinmobile.boss.trans.manager.PosBussManager;
import com.justinmobile.boss.trans.manager.PosCouponManager;
import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.PosProcesserA1;
import com.justinmobile.util.PlatformCaller;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.TempMember;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.TempMemberManager;
import com.vlives.boss.member.manager.exception.MemberException;
import com.vlives.boss.merchant.domain.DiscountRule;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.DiscountRuleManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.manager.PosManager;
import com.vlives.boss.sms.manager.SmsTemplateManager;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.manager.PosTradeLogManager;
import com.vlives.boss.trade.manager.TradeBatchManager;
import com.vlives.boss.user.domain.User;
import com.vlives.boss.user.manager.UserManager;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;
import com.vlives.util.DateUtils;

public abstract class AbstractPosProcesserA1 implements PosProcesserA1 {

	private static final Log LOG = LogFactory.getLog(AbstractPosProcesserA1.class);

	@Autowired
	protected PosManager posManager;
	@Autowired
	protected MerchantManager merchantManager;
	@Autowired
	protected MemberManager memberManager;
	@Autowired
	protected DiscountRuleManager discountRuleManager;
	@Autowired
	protected UserManager userManager;
	@Autowired
	protected TradeBatchManager tradeBatchManager;
	@Autowired
	protected TempMemberManager tempMemberManager;
	@Autowired
	protected PosBussManager posBussManager;
	@Autowired
	protected PosTradeLogManager posTradeLogManager;
	@Autowired
	protected SmsTemplateManager smsSendManager;
	@Autowired
	protected PosCouponManager posCouponManager;
	@Autowired
	protected CouponManager couponManager;
	@Autowired
	protected CouponPloyManager couponPloyManager;
	
	
	
	
	
	

	
	
	
	
	
	/**
	 * POS签到
	 * 
	 * @param request
	 * @return
	 * @throws PosBussinessException
	 */
	public Hs8583Dto posSign(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.POS_SIGN_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);

			tradeBatchManager.createBatch(pos, pos.getBatchNumber());

			response.setTradeDate(new Date());
			response.setBm30BatchNo(String.valueOf(pos.getBatchNumber()));
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	/**
	 * 交易信息查询
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto transQuery(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.POS_TRANSQUERY_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);

			List<Hs8583BusinessUploadInfo> businessInfos = request.getBm36BusinessUploadInfoList();
			List<Hs8583BusinessUploadInfo> bussnessInfo = posBussManager.transQuery(businessInfos,
					request.getBm30BatchNo(), posTradeLog);
			response.setBm36BusinessUploadInfoList(bussnessInfo);
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}

		return response;
	}

	/**
	 * 商户添加新会员
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto joinMember(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.MEMBER_JOIN_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			Member member = createMember(request.getBm2MemberMobile(), merchant);
			response.setBm13MerchantName(merchant.getShortName());
			response.setBm14MerchantAdress(merchant.getBusinessAddress());
			this.setResponseData(request, response, merchant, member);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;

	}

	private Member createMember(String mobileNo, Merchant merchant) throws PosBussinessException {
		Member member = null;
		try {
			member = memberManager.createByPos(mobileNo, merchant);
		 
			PlatformCaller.createPosMemberSms(member.getId());
		} catch (MemberException e) {
			throw new PosBussinessException(e.getErrorCode());
		}
		this.checkMemberValid(member);
		return member;
	}

	/** 获取会员积分 */
	private int getMemberDiscount(Merchant merchant, Member member) {
		DiscountRule discountRole = discountRuleManager.getByBuss(merchant, DiscountRule.Type.BY_MEMBER_LEVEL,
				member.getLevel());
		int discount = Integer.valueOf((discountRole == null ? 1000 : discountRole.getParamerTwo() * 10));
		return discount;
	}

	/**
	 * 会员认证
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto authMember(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.POS_MEMBER_AUTH_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			User user = userManager.getByMobile(request.getBm2MemberMobile());

			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			checkUserValid(user);

			Member member = getByMemberGroup(user, merchant);
			checkMemberValid(member);

			response.setBm2MemberMobile(request.getBm2MemberMobile());
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	/**
	 * 会员信息查询
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto getMemberInfo(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.MEMBER_INFO_QUERY_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			User user = userManager.getByMobile(request.getBm2MemberMobile());

			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			checkUserValid(user);

			Member member = getByMemberGroup(user, merchant);
			checkMemberValid(member);

			setResponseData(request, response, merchant, member);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	/**
	 * 折扣消费计算
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto discountExpense(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.CONSUME_DISC_RESP);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			User user = userManager.getByMobile(request.getBm2MemberMobile());

			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			checkUserValid(user);

			Member member = getByMemberGroup(user, merchant);
			checkMemberValid(member);

			DiscountRule discountRule = discountRuleManager.getByBuss(merchant, DiscountRule.Type.BY_MEMBER_LEVEL,
					member.getLevel());
			getDiscountAmount(request.getBm7TradeAmount(), response, discountRule);

			setResponseData(request, response, merchant, member);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	/**
	 * 交易绑定
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto bindTrade(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.TRADE_BIND_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			TempMember tempMember = tempMemberManager.getByCardNo(merchant, request.getBm39OriginalMemberSerial());

			checkValid(merchant, pos);
			checkRelated(merchant, pos);

			checkTempMember(tempMember);
			checkRelated(merchant, tempMember);

			Member member = bindMember(request, tempMember);
			checkMemberValid(member);

			response.setBm40MemberName(member.getUser().getName());
			response.setBm8MemberIntegralAmount(Long.valueOf(member.getPoint()));
			response.setBm15MemberLevelName(member.getLevel().getDesc());
			int discount = getMemberDiscount(merchant, member);
			response.setBm16MemberDiscount(String.valueOf(discount));
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	private Member bindMember(Hs8583Dto request, TempMember tempMember) throws PosBussinessException {
		try {
			Member member = memberManager.bindMember(request.getBm2MemberMobile(), tempMember);
			return member;
		} catch (MemberException e) {
			throw new PosBussinessException(e.getErrorCode());
		}
	}

	/**
	 * 交易结算
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto settleTrade(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.TRADE_SETTLE_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			Integer batchNo = Integer.parseInt(request.getBm30BatchNo());

			TradeBatch tradeBatch = tradeBatchManager.getByBatchNo(pos, batchNo);
			checkExist(merchant, pos);			
			Hs8583ClosingCostDto dto = request.getBm21ClosingCost();
			Integer batchNoNew = posBussManager.settleTrade(tradeBatch, dto);
			
			response.setBm30BatchNo(batchNoNew.toString());
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	/**
	 * 交易上送，
	 * 
	 * @param request
	 * @return
	 */
	public Hs8583Dto uploadTrade(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.TRADE_BATCH_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo().trim());
			Integer batchNo = Integer.parseInt(request.getBm30BatchNo());

			checkExist(merchant, pos);
			checkRelated(merchant, pos);
			checkBatchNoValid(pos, batchNo);

			List<Hs8583BusinessUploadInfo> businessInfos = request.getBm36BusinessUploadInfoList();
		//	List<Hs8583CouponUploadInfo>   couponUploadList=request.getBm47CouponUploadList();
			posBussManager.batchTrade(businessInfos, request.getBm30BatchNo(), posTradeLog);
			returnSuccess(response);
		} catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}

	protected void getDiscountAmount(Long amount, Hs8583Dto response, DiscountRule discountRole) {
		if (discountRole == null) {
			response.setBm7TradeAmount(amount);
		} else {
			BigDecimal amountNew = new BigDecimal(amount).multiply(new BigDecimal(discountRole.getParamerTwo()))
					.divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP);
			response.setBm7TradeAmount(amountNew.longValue());
		}
	}

	private void setResponseData(Hs8583Dto request, Hs8583Dto response, Merchant merchant, Member member) {
		response.setBm2MemberMobile(request.getBm2MemberMobile());
		response.setBm8MemberIntegralAmount(Long.valueOf(member.getPoint()));
		response.setBm15MemberLevelName(member.getLevel().getDesc());
		int discount = getMemberDiscount(merchant, member);
		response.setBm16MemberDiscount(String.valueOf(discount));
		response.setBm25MemberApplyDate(DateUtils.format(member.getCreateDate(), "yyyyMMddHHmmss"));

		returnSuccess(response);
	}


	
	
	/**
	 * 验证商户和POS是否存在
	 * 
	 * @param merchant
	 * @param pos
	 * @throws PosBussinessException
	 */
	protected void checkExist(Merchant merchant, Pos pos) throws PosBussinessException {
		if (merchant == null) {
			throw new PosBussinessException(PosReturnCode.MERCHANT_NOT_EXIST);
		}
		if (pos == null) {
			throw new PosBussinessException(PosReturnCode.POS_NOT_EXIST);
		}

	}

	/**
	 * 验证商户和POS状态是否有效
	 * 
	 * @param merchant
	 * @param pos
	 * @throws PosBussinessException
	 */
	protected void checkValid(Merchant merchant, Pos pos) throws PosBussinessException {
		this.checkExist(merchant, pos);
		if (merchant.getStatus() != Merchant.Status.STATUS_ACTIVE) {
			throw new PosBussinessException(PosReturnCode.MERCHANT_NOT_VALID);
		}
		if (pos.getStatus() != Pos.Status.STATUS_ACTIVE) {
			throw new PosBussinessException(PosReturnCode.POS_NOT_VALID);
		}

	}

	/**
	 * 检查会员是否存在
	 * 
	 * @param user
	 * @throws PosBussinessException
	 */
	protected void checkUserExist(User user) throws PosBussinessException {
		if (user == null) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_EXIST);
		}
	}

	protected void checkUserValid(User user) throws PosBussinessException {
		this.checkUserExist(user);
		if (!user.isValid()) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
	}

	/**
	 * 检查会员是否存在
	 * 
	 * @param member
	 * @throws PosBussinessException
	 */
	protected void checkMemberExist(Member member) throws PosBussinessException {
		if (member == null) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_EXIST);
		}
	}

	/**
	 * 检查会员是否存在，并状态有效
	 * 
	 * @param member
	 * @throws PosBussinessException
	 */
	protected void checkMemberValid(Member member) throws PosBussinessException {
		this.checkMemberExist(member);
		if (member.getStatus() != Member.Status.STATUS_ACTIVE) {
			throw new PosBussinessException(PosReturnCode.MEMBER_NOT_VALID);
		}
	}

	protected void checkRelated(Merchant merchant, Pos pos) throws PosBussinessException {
		if (!pos.getMerchant().equals(merchant)) {
			throw new PosBussinessException(PosReturnCode.POS_NOT_RELATED_MERCHANT);
		}

	}

	/**
	 * 检查商户和会员卡是否关联
	 * 
	 * @param merchant
	 * @param tempMember
	 * @throws PosBussinessException
	 */
	protected void checkRelated(Merchant merchant, TempMember tempMember) throws PosBussinessException {
		if (!tempMember.getMerchant().equals(merchant)) {
			throw new PosBussinessException(PosReturnCode.MERCHANT_CARDNO_NOT_BINDED);
		}
	}

	/**
	 * 检查会员卡
	 * 
	 * @param tempMember
	 * @throws PosBussinessException
	 */
	protected void checkTempMember(TempMember tempMember) throws PosBussinessException {
		if (tempMember == null) {
			throw new PosBussinessException(PosReturnCode.MEMBER_CARDNO_NOT_EXIST);
		}
		if (tempMember.isBind()) {
			throw new PosBussinessException(PosReturnCode.MEMBER_CARDNO_BINDED);
		}

	}

	/**
	 * 根据返回类型创建Response
	 * 
	 * @param code
	 * @return
	 */
	protected Hs8583Dto createResponse(MtRequestCode code) {
		Hs8583Dto response = new Hs8583Dto();
		String responseCode = code.getCode();
		response.setBm0MessageType(responseCode.substring(0, 4));
		response.setBm3TradeCode(responseCode.substring(4, 10));
		return response;
	}

	/**
	 * 返回失败处理结果
	 * 
	 * @param response
	 * @param code
	 */
	protected void returnFail(Hs8583Dto response, PosReturnCode code) {
		response.setBm23RespCode(code.getErrorCode());
		response.setBm24RespMess(code.getMessage());
	}

	/**
	 * 检查批次是否正确
	 * 
	 * @param pos
	 * @param batchNo
	 * @throws PosBussinessException
	 */
	protected void checkBatchNoValid(Pos pos, Integer batchNo) throws PosBussinessException {
		if (batchNo > pos.getBatchNumber()) {
			throw new PosBussinessException(PosReturnCode.BATCH_NO_NOT_VALID);
		}
	}

	/**
	 * 设置返回成功
	 * 
	 * @param response
	 */
	protected void returnSuccess(Hs8583Dto response) {
		response.setBm23RespCode(PosReturnCode.SUCCESS.getErrorCode());
		response.setBm24RespMess(PosReturnCode.SUCCESS.getMessage());
	}

	protected PosTradeLog createLog(Hs8583Dto request) {
		PosTradeLog log = new PosTradeLog();
		log.setMerchantNo(request.getBm4MerchantNo());
		log.setPosNo(request.getBm5TerminalNo());
		log.setMobileNo(request.getBm2MemberMobile());
		log.setRequestData(request.toString());
		posTradeLogManager.createLog(log);
		return log;
	}

	public void updateResult(PosTradeLog log, Hs8583Dto response) {
		log.setRespCode(response.getBm23RespCode());
		log.setRespDesc(response.getBm24RespMess());
		posTradeLogManager.updateResult(log);
	}

	public Member getByMemberGroup(User user, Merchant merchant) {
		return memberManager.getBy(user, merchant.getMemberGroup());
	}
	
	/**用户票信息查询
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInfoQuery(Hs8583Dto request){
		return null;
	}
	
	/**商户可用票
	 * @param request
	 * @return
	 */
	public Hs8583Dto merchantPublishedCouponQuery(Hs8583Dto request){
		return null;
	}
	
	/**检券 
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInspect(Hs8583Dto request){
		return null;
	}
}
