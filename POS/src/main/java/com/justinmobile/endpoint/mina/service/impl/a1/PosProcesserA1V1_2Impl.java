/**
 * @(#)PosProcesserA1V1_2Impl.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.justinmobile.endpoint.mina.service.impl.a1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponList;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponSketchList;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.impl.AbstractPosProcesserA1;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.PosTradeLog;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-9-20
 */
@Service("posProcesserA1V1_2")
public class PosProcesserA1V1_2Impl extends PosProcesserA1V1_1Impl{
	
	private static final Log LOG = LogFactory.getLog(PosProcesserA1V1_2Impl.class);
	
	
	@Override
	public Hs8583Dto posSign(Hs8583Dto request) {
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.POS_SIGN_RESPONSE);
		try {
			Pos pos = posManager.getByPosSNSerial(request.getBm18PosSerialNo());
			if (pos == null) {
				throw new PosBussinessException(PosReturnCode.POSSIGN_INVALID);
			}
			Merchant merchant = pos.getMerchant();
			checkValid(merchant, pos);
			checkRelated(merchant, pos);

			tradeBatchManager.createBatch(pos, pos.getBatchNumber());

			response.setBm4MerchantNo(merchant.getCode());
			response.setBm5TerminalNo(pos.getCode());
			response.setBm13MerchantName(merchant.getShortName());
			response.setBm19PosBussList(String.valueOf(Long.toHexString(pos.getMerchant().getPosBusInessType())));
			response.setBm20MobileValidRule("13#15#18#14");
			response.setBm41DiscountFlag(pos.getMerchant().isDiscountFlag()?"1":"0");

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
	
	/**用户票信息查询
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInfoQuery(Hs8583Dto request){
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.USER_COUPON__QUREY_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			User user = userManager.getByMobile(request.getBm2MemberMobile());
			checkUserValid(user);
			
			List<Coupon>	userCouponList=couponManager.findByUserAndMer(user, merchant);
			if(userCouponList==null||userCouponList.isEmpty())throw new PosBussinessException(PosReturnCode.COUPON_NOT_BELONG_USER);
			response.setBm2MemberMobile(user.getMobile());
			response.setBm45CouponSketchList(packagingCouponList(userCouponList));
			returnSuccess(response);
		}catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		
		return response;
	}
	
	
	private Hs8583CouponSketchList packagingCouponList(List<Coupon>	userCouponList){
		
		Hs8583CouponSketchList  couponList=new Hs8583CouponSketchList();
		if(userCouponList==null)return null;
		
		for(Coupon coupon:userCouponList){
			if(couponList.getCouponSketchList().size()<10){
				couponList.addCouponType(coupon.getCode(), coupon.getCouponPloy().getContent());
			}else{
				break;
			}
			}
		return couponList;
	}
	
	
	/**商户可用票
	 * @param request
	 * @return
	 */
	public Hs8583Dto merchantPublishedCouponQuery(Hs8583Dto request){
		PosTradeLog posTradeLog = createLog(request);
		Hs8583Dto response = createResponse(MtRequestCode.MERCHANT_COUPON__QUREY_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			List<CouponPloy> couPonPloyList=couponPloyManager.findValidPloy(merchant);
			if(couPonPloyList==null||couPonPloyList.isEmpty())throw new PosBussinessException(PosReturnCode.COUPONPLOY_NOT_BELONG_MERCHANT);
			response.setBm46CouponList(  packagingCouponPloyList(couPonPloyList));
			
			returnSuccess(response);
		}catch (PosBussinessException e) {
			returnFail(response, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			returnFail(response, PosReturnCode.OTHER_ERROR);
		}
		updateResult(posTradeLog, response);
		return response;
	}
	
	private List<Hs8583CouponList>  packagingCouponPloyList(List<CouponPloy>	merchantCouponPloyList){
		List<Hs8583CouponList>  couponPloyList=new ArrayList<Hs8583CouponList>();
		if(merchantCouponPloyList==null)return null;
		for(CouponPloy couponPloy:merchantCouponPloyList){
			Hs8583CouponList  couponPloyCode=new Hs8583CouponList(couponPloy.getId(),couponPloy.getTitle(),0l);
			couponPloyList.add(couponPloyCode);
		}
		return couponPloyList;
	}
	
	/**检券 
	 * @param request
	 * @return
	 */
	public Hs8583Dto userCouponInspect(Hs8583Dto request){
		PosTradeLog posTradeLog = createLog(request);

		Hs8583Dto response = createResponse(MtRequestCode.USER_COUPON_CHECK_RESPONSE);
		try {
			Pos pos = posManager.getByCode(request.getBm5TerminalNo());
			Merchant merchant = merchantManager.getByCode(request.getBm4MerchantNo());
			checkValid(merchant, pos);
			checkRelated(merchant, pos);
			User user=null;
			if(request.getBm2MemberMobile()!=null){
				user = userManager.getByMobile(request.getBm2MemberMobile());
				checkUserValid(user);
			}
			//取出所要检的券
			Coupon coupon=posCouponManager.userCoupon(request,user,pos);
			checkUserValid(coupon.getUser());
			if(request.getBm2MemberMobile()!=null&&!request.getBm2MemberMobile().equals(coupon.getUser().getMobile()))
				 throw new PosBussinessException(PosReturnCode.COUPON_NOT_MATCH_MOREUSER);
			Member member = getByMemberGroup(coupon.getUser(), merchant);
			checkMemberValid(member);
			posCouponManager.checkUserCoupon(request, coupon, pos);
			
			Hs8583CouponList couponList=new Hs8583CouponList(coupon.getCouponPloy().getId(),coupon.getCouponPloy().getTitle(),1l);
			response.setBm44CouponSeriano(coupon.getCode());
			response.setBm2MemberMobile(coupon.getUser().getMobile());
			response.addBm46CouponList(couponList);
			returnSuccess(response);
		}catch (PosBussinessException e) {
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
	 * 交易结算之1.2
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

			if( request.getBm46CouponList()!=null&& request.getBm46CouponList().size()!=0){
				posCouponManager.settleCoupon(tradeBatch, request.getBm46CouponList());
			}
			
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
			List<Hs8583CouponUploadInfo>   couponUploadList=request.getBm47CouponUploadList();
			posBussManager.batchTrade(businessInfos,couponUploadList, request.getBm30BatchNo(), posTradeLog);
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
	
	
	
}
