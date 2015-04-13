package com.justinmobile.boss.trans.manager;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponUploadInfo;
import com.justinmobile.util.PlatformCaller;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.pos.manager.PosManager;
import com.vlives.boss.sms.manager.SmsTemplateManager;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.domain.TradeOrder;
import com.vlives.boss.trade.domain.TradeTransaction;
import com.vlives.boss.trade.manager.TradeBatchManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.trade.manager.TradeOrderManager;
import com.vlives.boss.trade.manager.exception.TradeExistException;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.BusinessException;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

/**
 * 本manager任何方法都不需要事物
 * 
 * @author MrXu
 * 
 */
@Service("posBussManager")
public class PosBussManagerImpl extends AbstractPosBussSupport implements PosBussManager {

	private static final Log LOG = LogFactory.getLog(PosBussManagerImpl.class);

	@Autowired
	private TradeBatchManager tradeBatchManager;
	@Autowired
	private PosManager posManager;
	@Autowired
	private TradeOrderManager tradeOrderManager;	
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private CouponManager couponManager;

	public void batchTrade(List<Hs8583BusinessUploadInfo> businessInfos, String batchNumber, PosTradeLog posTradeLog)
			throws PosBussinessException {
		for (int i = 0; i < businessInfos.size(); i++) {
			Hs8583BusinessUploadInfo bussInfo = businessInfos.get(i);
			LOG.info(StringUtils.center("第 " + i + " 笔业务数据开始处理", 40, '='));
			LOG.info("业务数据：" + bussInfo);
			processerTrade(batchNumber, bussInfo, posTradeLog);
			LOG.info(StringUtils.center("处理结束", 40, '='));
		}
	}

	public void batchTrade(List<Hs8583BusinessUploadInfo> businessInfos, List<Hs8583CouponUploadInfo> couponUploadList,
			String batchNumber, PosTradeLog posTradeLog) throws PosBussinessException {
		for (int i = 0; i < businessInfos.size(); i++) {
			Hs8583BusinessUploadInfo bussInfo = businessInfos.get(i);
			LOG.info(StringUtils.center("第 " + i + " 笔业务数据开始处理", 40, '='));
			LOG.info("业务数据：" + bussInfo);
			processerTrade(batchNumber, bussInfo, posTradeLog);
			addCouponInfo(bussInfo, batchNumber,couponUploadList);
			LOG.info(StringUtils.center("处理结束", 40, '='));
		}
	}

	public List<Hs8583BusinessUploadInfo> transQuery(List<Hs8583BusinessUploadInfo> businessInfos, String batchNumber,
			PosTradeLog posTradeLog) throws PosBussinessException {
		List<Hs8583BusinessUploadInfo> returnLits = new ArrayList<Hs8583BusinessUploadInfo>();
		for (int i = 0; i < businessInfos.size(); i++) {
			Hs8583BusinessUploadInfo bussInfo = businessInfos.get(i);
			returnLits.add(queryTrad(batchNumber, bussInfo, posTradeLog));
		}
		return returnLits;
	}

	private Hs8583BusinessUploadInfo queryTrad(String batchNumber, Hs8583BusinessUploadInfo bussInfo,
			PosTradeLog posTradeLog) throws PosBussinessException {
		PosTradeLog log = createLog(bussInfo, posTradeLog);
		try {
			User user = userManager.getByMobile(bussInfo.getMobileNo());
			checkUserValid(user);

			TradeBatch ortradeBatch = this.getTradeBatch(bussInfo.getOriginalTerminalNo(),
					bussInfo.getOriginalBatchNo());

			TradeDetail tradeDetail = tradeDetailManager.getTradeDetail(ortradeBatch,
					Integer.parseInt(bussInfo.getOriginalSerial()));
			TradeDetail cancelTradeDetail = tradeDetailManager.getCancelTradeDetail(ortradeBatch,
					Integer.parseInt(bussInfo.getOriginalSerial()));
			if (cancelTradeDetail != null && !tradeDetail.getTradeOrder().isConsumeTrade())
				throw new PosBussinessException(PosReturnCode.ORGINAL_TRANSE_RETURNED);

			if (tradeDetail == null) {
				throw new PosBussinessException(PosReturnCode.ORGINAL_TRANSE_NOTEXIST);
			}
			if (cancelTradeDetail != null && !cancelTradeDetail.getTradeOrder().isConsumeTrade()) {
				throw new PosBussinessException(PosReturnCode.ORGINAL_TRANSE_RETURNED);
			}
			if (bussInfo.getAmount() != tradeDetail.getAmount().movePointRight(2).longValue()) {
				throw new PosBussinessException(PosReturnCode.TOTAL_AMOUNT_NOT_EQUAL);
			}
			if (!bussInfo.getMobileNo().equals(tradeDetail.getTradeOrder().getMember().getUser().getMobile())) {
				throw new PosBussinessException(PosReturnCode.ORGINAL_TRANSE_MOBILE_INVALID);
			}
			bussInfo.setMobileNo(tradeDetail.getTradeOrder().getMember().getUser().getMobile());
			bussInfo.setAmount(tradeDetail.getAmount().movePointRight(2).longValue());
			bussInfo.setBankCardNo(tradeDetail.getBankCard());
			bussInfo.setTerminalTransDate(DateUtils.formatDate(tradeDetail.getCreateDate(), "yyyyMMdd"));
			bussInfo.setTerminalTransTime(DateUtils.formatDate(tradeDetail.getCreateDate(), "HHmmss"));
			bussInfo.setTransType(getTradeType(tradeDetail));

		} catch (PosBussinessException e) {
			e.printStackTrace();
			throw e;
		} catch (UncategorizedSQLException e) {
			posTradeLogManager.updateResult(log, PosReturnCode.OTHER_ERROR.getErrorCode(), "系统错误");
			e.printStackTrace();
			throw new PosBussinessException(PosReturnCode.SYSTEM_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			posTradeLogManager.updateResult(log, PosReturnCode.OTHER_ERROR.getErrorCode(), "系统错误");
		}
		return bussInfo;
	}

	private int getTradeType(TradeDetail tradeDetail) {
		switch (tradeDetail.getTradeOrder().getType().getValue()) {
		case 1:
			return 0;
		case 2:
			return 1;
		default:
			return 2;
		}
	}

	private TradeDetail processerTrade(String batchNumber, Hs8583BusinessUploadInfo bussInfo, PosTradeLog posTradeLog)
			throws PosBussinessException {
		PosTradeLog log = createLog(bussInfo, posTradeLog);
		TradeTransaction trade = createTradeTransaction(bussInfo, batchNumber);
		TradeDetail tradeDetail = new TradeDetail();
		try {
			TradeBatch tradeBatch = this.getTradeBatch(bussInfo.getTerminalNo(), batchNumber);
			Pos pos = posManager.getByCode(bussInfo.getTerminalNo());
			// 封装订单数据
			TradeOrder tradeOrder = new TradeOrder();
			tradeOrder.setMerchant(pos.getMerchant());

			// 封装订单明细数据

			tradeDetail.setTradeSerialNo(Integer.parseInt(bussInfo.getTerminalSerial()));
			tradeDetail.setBankCard(bussInfo.getBankCardNo());
			tradeDetail.setTradeDate(bussInfo.getTradeDate());
			tradeDetail.setAmount(bussInfo.getTradeAmount());

			if (bussInfo.getTransType() == 0 || bussInfo.getTransType() == 1) {

				Member member = createMember(bussInfo.getMobileNo(), pos.getMerchant());
				tradeOrder.setType(bussInfo.getEnumTradeType());
				tradeOrder.setMoney(bussInfo.getTradeAmount());
				tradeOrder.setTradeDate(bussInfo.getTradeDate());
				this.pointTrade(tradeBatch, member, tradeOrder, tradeDetail);

			} else if (bussInfo.getTransType() == 2) {
				Member member = getMember(bussInfo.getMobileNo(), pos.getMerchant());
				tradeDetail.setOriginalSerailNo(Integer.parseInt(bussInfo.getOriginalSerial()));
				TradeBatch originalBatch = this.getTradeBatch(bussInfo.getOriginalTerminalNo(),
						bussInfo.getOriginalBatchNo());

				tradeOrderManager.pointCancel(tradeBatch, originalBatch, member, tradeOrder, tradeDetail);
			}
			setResult(log, trade, PosReturnCode.SUCCESS);
		} catch (PosBussinessException e) {
			LOG.error(e);
			setResult(log, trade, e.getErrorCode());
		} catch (UncategorizedSQLException e) {
			LOG.error(e);
			setResult(log, trade, PosReturnCode.OTHER_ERROR);
			posTradeLogManager.updateResult(log, PosReturnCode.OTHER_ERROR.getErrorCode(), "系统错误");
			e.printStackTrace();
			throw new PosBussinessException(PosReturnCode.SYSTEM_ERROR);
		} catch (Exception e) {
			LOG.error(e);
			setResult(log, trade, PosReturnCode.OTHER_ERROR);
			posTradeLogManager.updateResult(log, PosReturnCode.OTHER_ERROR.getErrorCode(), "系统错误");
		}

		return tradeDetail;
	}

	/**
	 * 积分消费交易
	 * 
	 * @param tradeBatch
	 * @param member
	 * @param tradeOrder
	 * @param tradeDetail
	 * @param couponUploadList
	 * @throws PosBussinessException
	 */
	public void pointTrade(TradeBatch tradeBatch, Member member, TradeOrder tradeOrder, TradeDetail tradeDetail)
			throws PosBussinessException {
		try {
			tradeOrderManager.pointTrade(tradeBatch, member, tradeOrder, tradeDetail);
		//	smsTemplateManager.tradeSms(tradeDetail);
			PlatformCaller.posTradeSms(tradeDetail.getId());
		} catch (TradeExistException e) {
		}
	}

	/**
	 * 绑定电子劵
	 * 
	 * @param tradeDetail
	 * @param couponUploadList
	 */
	private void addCouponInfo(Hs8583BusinessUploadInfo bussInfo, String batchNumber,
			List<Hs8583CouponUploadInfo> couponUploadList) throws PosBussinessException {
		TradeBatch tradeBatch = this.getTradeBatch(bussInfo.getTerminalNo(), batchNumber);
		TradeDetail tradeDetail = tradeDetailManager.getTradeDetail(tradeBatch,
				Integer.parseInt(bussInfo.getTerminalSerial()));
		if (tradeDetail==null||!tradeDetail.isConsumeTrade()) {
			return;
		}
		if (CollectionUtils.isEmpty(couponUploadList)) {
			return;
		}
		for (Hs8583CouponUploadInfo couponUpload : couponUploadList) {
			if (Integer.valueOf(couponUpload.getTerminalSerial()) == tradeDetail.getTradeSerialNo()) {
				Coupon coupon = couponManager.getByCode(couponUpload.getCouponSerialNo().trim());
				try {					
					couponManager.relatedTradeOrder(coupon, tradeDetail.getTradeOrder());
				} catch (BusinessException e) {
				}
			}
		}
	}

	private void setResult(PosTradeLog log, TradeTransaction trade, PosReturnCode posReturnCode) {
		posTradeLogManager.updateResult(log, posReturnCode.getErrorCode(), posReturnCode.getMessage());
		tradeTransactionManager.updateResult(trade, posReturnCode.getErrorCode(), posReturnCode.getMessage());
	}

	/**
	 * 根据PosNo和批次号batchNumber查询 检查pos存在不，并检查批次号是否小于pos当前批次号
	 * 
	 * @param posNo
	 * @param batchNumber
	 * @return
	 * @throws PosBussinessException
	 */
	private TradeBatch getTradeBatch(String posNo, String batchNumber) throws PosBussinessException {
		Pos pos = posManager.getByCode(posNo);
		this.checkExist(pos);
		this.checkBatchNoValid(pos, Integer.valueOf(batchNumber));
		return tradeBatchManager.createBatch(pos, Integer.parseInt(batchNumber));
	}

	public int settleTrade(TradeBatch tradeBatch, Hs8583ClosingCostDto dto) throws PosBussinessException {
		checkBatch(tradeBatch);
		accountCheck(tradeBatch, dto);
		// TODO需要检查和当前批次对上不
		return tradeBatchManager.settleTrade(tradeBatch);
	}

}
