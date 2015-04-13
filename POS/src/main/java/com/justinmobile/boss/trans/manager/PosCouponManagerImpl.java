package com.justinmobile.boss.trans.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.justinmobile.endpoint.mina.domain.Hs8583CouponList;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.vlives.boss.coupon.domain.Coupon;
import com.vlives.boss.coupon.domain.CouponPloy;
import com.vlives.boss.coupon.manager.CouponManager;
import com.vlives.boss.coupon.manager.CouponPloyApplyMerchantManager;
import com.vlives.boss.coupon.manager.CouponPloyManager;
import com.vlives.boss.coupon.manager.CouponTransManager;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.TradeBatch;
import com.vlives.boss.user.domain.User;
import com.vlives.core.exception.PosBussinessException;
import com.vlives.core.exception.PosReturnCode;

@Service("posCouponManager")
public class PosCouponManagerImpl extends AbstractPosBussSupport implements PosCouponManager {

	private static final Log LOG = LogFactory.getLog(PosCouponManagerImpl.class);

	@Autowired
	private CouponTransManager couponTransManager;
	@Autowired
	private CouponManager couponManager;
	@Autowired
	private CouponPloyManager couponPloyManager;
	@Autowired
	protected MemberManager memberManager;
	@Autowired
	private CouponPloyApplyMerchantManager couponPloyApplyMerchantManager;

	@Override
	public void settleCoupon(TradeBatch tradeBatch, List<Hs8583CouponList> couponLists) throws PosBussinessException {
		Map<Long, Long> params = new HashMap<Long, Long>();
		LOG.info(StringUtils.center("电子劵结算数据", 50, "="));
		for (Hs8583CouponList couponDto : couponLists) {
			LOG.info(couponDto.toString());
			params.put(couponDto.getCouponPloySerialNo(), couponDto.getCouponNum());
		}
		couponTransManager.settleCoupon(params, tradeBatch);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Coupon userCoupon(Hs8583Dto request, User user, Pos pos) throws PosBussinessException {
		Coupon coupon = null;
		Merchant merchant = pos.getMerchant();
		if (StringUtils.isNotBlank(request.getBm44CouponSeriano())) {
			// 券号检券
			coupon = couponManager.getByCode(request.getBm44CouponSeriano().toString());
			if(coupon==null)throw new  PosBussinessException(PosReturnCode.COUPON_NOT_EXIST);

			verifyMerchant(merchant, coupon.getCouponPloy());
		} else {
			// 手机号检券
			CouponPloy couponPloy = couponPloyManager.get(request.getBm46CouponList().get(0).getCouponPloySerialNo());
			verifyMerchant(merchant, couponPloy);
			if(couponPloy==null)throw new  PosBussinessException(PosReturnCode.COUPONPLOY_NO_EXIST);
			coupon = couponManager.getByUserAndPloy(user, couponPloy);
			if(coupon==null)throw new  PosBussinessException(PosReturnCode.COUPON_NOT_EXIST);
			//	coupon = couponManager.verfyCoupon(user, couponPloy);
		}

		createMember( coupon, pos);
		return coupon;
	}
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void checkUserCoupon(Hs8583Dto request, Coupon coupon, Pos pos) throws PosBussinessException {
		//检券
		couponManager.verifyCoupon(coupon);
		couponTransManager.createCouponTrans(generateCouponVerifyTransaction(request, pos, coupon));
	}

	/**
	 * 验证商户与活动的 关系
	 * @param merchant
	 * @param couponPloy
	 * @throws PosBussinessException
	 */
	private void verifyMerchant(Merchant merchant, CouponPloy couponPloy) throws PosBussinessException {
		try{
			Assert.notNull(merchant);
			Assert.notNull(couponPloy.getMerchant());
			Assert.notNull(couponPloyApplyMerchantManager.getApplyPloyMer(merchant, couponPloy));
		}catch(Exception ex){
			throw new PosBussinessException(PosReturnCode.MERCHANT_NOT_EXIST_COUPON);
		}
	}

	private void createMember(Coupon coupon,Pos pos){
		try {
			createMember(coupon.getUser().getMobile(), pos.getMerchant());
		} catch (PosBussinessException e) {

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
