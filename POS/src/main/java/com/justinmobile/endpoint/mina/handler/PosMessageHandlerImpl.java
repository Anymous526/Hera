package com.justinmobile.endpoint.mina.handler;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.request.MtRequestCodeAnnotation;
import com.justinmobile.endpoint.mina.service.PosMultiVersionSupport;
import com.justinmobile.endpoint.mina.service.PosProcesserA1;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.vlives.core.exception.PosReturnCode;


/**
 * MINA处理层实现类
 * 
 * @author ThinkPad7
 * 
 */
public class PosMessageHandlerImpl extends AbstractMessageHandler implements PosMessageHandler {
	private static final Log LOG = LogFactory.getLog(PosMessageHandlerImpl.class);
    /**默认版本号*/
	private static final String DEFAULT_VERSION = "1.0";
    /**默认应用码*/
	private static final String DEFAULT_APP_CODE = "1";

	private PosProcesserA1 posPointProcesser;

	public Hs8583Dto process(Hs8583Dto message) {
		MtRequestCode messageCode = message.getRequestCode();
		PosReturnCode posReturnCode = setPosPointProcesser(message.getBm42AppSeriaNo(), message.getBm43AppVersionNo());
		if (posReturnCode != null) {
			message=createResponseFromReq(message);
			message.setBm23RespCode(posReturnCode.getErrorCode());
			message.setBm24RespMess(posReturnCode.getMessage());
			return message;
		}

		Method method = this.messageMethodMap.get(messageCode);
		try {
			// Hs8583Util.validIsNotNull((Hs8583Dto)message);
			if (!message.getBm64().equals("87654321"))
				throw new PosServiceException(PosReturnCode.INVALID_MESSAGE_MAC);
			return (Hs8583Dto) method.invoke(this, message);
		} catch (PosServiceException pse) {
			LOG.error(PosReturnCode.INVALID_MESSAGE_MAC.getMessage(), pse);
			message.setBm23RespCode(PosReturnCode.INVALID_MESSAGE_MAC.getErrorCode());
			message.setBm24RespMess(PosReturnCode.INVALID_MESSAGE_MAC.getMessage());
			return message;
		} catch (Exception e) {
			LOG.error(PosReturnCode.SYSTEM_ERROR.getMessage(), e);
			// 消息类型未知
			message.setBm23RespCode(PosReturnCode.SYSTEM_ERROR.getErrorCode());
			message.setBm24RespMess(PosReturnCode.SYSTEM_ERROR.getMessage());
			return message;
		}
	}

	private PosReturnCode setPosPointProcesser(String appCode, String version) {		
		if (appCode == null && version == null) {
			appCode=DEFAULT_APP_CODE;
			version=DEFAULT_VERSION;
		}
		 
		PosProcesserA1 posPointProcesser = PosMultiVersionSupport.POS_PROCESSER_BEAN_MAP.get(appCode.trim() + "_" + version.trim());
		
		if (posPointProcesser == null) {
			return PosReturnCode.UNSUPPORT_VERSION;
		}
		this.posPointProcesser = posPointProcesser;
		return null;
	}
	
	
	/**用户券信息响应
	 * @param request
	 * @return
	 */
	@MtRequestCodeAnnotation(MtRequestCode.USER_COUPON__QUREY_REQUEST)
	public Hs8583Dto userCouponInfoQuery(Hs8583Dto request) {
		return posPointProcesser.userCouponInfoQuery(request);
	}
	
	/**用户券消费
	 * @param request
	 * @return
	 */
	@MtRequestCodeAnnotation(MtRequestCode.MERCHANT_COUPON__QUREY_REQUEST)
	public Hs8583Dto merchantPublishedCouponQuery(Hs8583Dto request) {
		return posPointProcesser.merchantPublishedCouponQuery(request);
	}
	
	/**检券
	 * @param request
	 * @return
	 */
	@MtRequestCodeAnnotation(MtRequestCode.USER_COUPON_CHECK_REQUEST)
	public Hs8583Dto userCouponInspect(Hs8583Dto request) {
		return posPointProcesser.userCouponInspect(request);
	}
	
	/***
	 * POS签到
	 * 
	 * @param request
	 * @return
	 */
	@MtRequestCodeAnnotation(MtRequestCode.POS_SIGN_REQUEST)
	public Hs8583Dto posSign(Hs8583Dto request) {
		return posPointProcesser.posSign(request);
	}

	@MtRequestCodeAnnotation(MtRequestCode.CONSUME_DISC_REQ)
	public Hs8583Dto discountExpense(Hs8583Dto request) {
		return posPointProcesser.discountExpense(request);
	}

	@MtRequestCodeAnnotation(MtRequestCode.POS_TRANSQUERY_REQUEST)
	public Hs8583Dto transQuery(Hs8583Dto request) {
		return posPointProcesser.transQuery(request);
	}

	/**
	 * 会员查询
	 */
	@MtRequestCodeAnnotation(MtRequestCode.MEMBER_INFO_QUERY_REQUEST)
	public Hs8583Dto getMemberInfo(Hs8583Dto request) {
		return posPointProcesser.getMemberInfo(request);
	}

	/**
	 * 会员认证
	 */
	@MtRequestCodeAnnotation(MtRequestCode.POS_MEMBER_AUTH_REQUEST)
	public Hs8583Dto authMember(Hs8583Dto request) {
		return posPointProcesser.authMember(request);
	}

	/**
	 * 会员增加
	 */
	@MtRequestCodeAnnotation(MtRequestCode.MEMBER_JOIN_REQUEST)
	public Hs8583Dto joinMember(Hs8583Dto request) {
		return posPointProcesser.joinMember(request);
	}

	/**
	 * 交易绑定
	 */
	@MtRequestCodeAnnotation(MtRequestCode.TRADE_BIND_REQUEST)
	public Hs8583Dto bindTrade(Hs8583Dto request) {
		return posPointProcesser.bindTrade(request);
	}

	/**
	 * 交易上送
	 */
	@MtRequestCodeAnnotation(MtRequestCode.TRADE_BATCH_REQUEST)
	public Hs8583Dto uploadTrade(Hs8583Dto request) {
		return posPointProcesser.uploadTrade(request);
	}

	/**
	 * 结算交易
	 */
	@MtRequestCodeAnnotation(MtRequestCode.TRADE_SETTLE_REQUEST)
	public Hs8583Dto settleTrade(Hs8583Dto request) {
		return posPointProcesser.settleTrade(request);
	}
}
