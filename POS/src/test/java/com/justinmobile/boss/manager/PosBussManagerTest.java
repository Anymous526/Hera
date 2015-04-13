package com.justinmobile.boss.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.base.BaseTest;
import com.justinmobile.boss.trans.manager.PosCouponManager;
import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponList;
import com.justinmobile.endpoint.mina.domain.Hs8583CouponUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.service.PosProcesserA1;

@TransactionConfiguration(defaultRollback = false)
public class PosBussManagerTest extends BaseTest {

	private static final Hs8583Dto request = null;
	@Resource(name = "posProcesserA1V1_2")
	private PosProcesserA1 posPointProcesser;
	@Autowired
	private PosCouponManager posCouponManager;

	// boss1
	// private String merchantNo = "201107261635520";
	// private String posNo = "00000023";
	// cloudboss
	// private String merchantNo = "123456789012345";
	// private String posNo = "12345678";

	// 1044 coupon 40 31
	private String merchantNo = "201107191627355";
	private String posNo = "00000032";

	// private String mobile = "";

	// 父
	// private String merchantNo = "201108031119300";
	// private String posNo = "00000067";
	// boss2
	// private String merchantNo = "201108031837058";
	// private String posNo = "00000068";

	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试 POS签到
	 * 
	 * @throws Exception
	 */
	@Test
	public void posSign() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo(merchantNo);
			request.setBm5TerminalNo(posNo);
			Hs8583Dto response = posPointProcesser.posSign(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试 创建会员
	 * 
	 * @throws Exception
	 */
	@Test
	public void testjoinMember() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo(merchantNo);
			request.setBm5TerminalNo(posNo);
			// 13602623810
			request.setBm2MemberMobile("15112656327");
			// request.setBm2MemberMobile("13602623810");

			Hs8583Dto response = posPointProcesser.joinMember(request);
			// Hs8583Dto response = posPointProcesser.authMember(request);
			// Hs8583Dto response = posPointProcesser.getMemberInfo(request);

			System.out.print(response);

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("sssssssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试 * 交易绑定
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBindMember() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();

			request.setBm4MerchantNo("123456789012345");
			request.setBm5TerminalNo("12345678");
			request.setBm2MemberMobile("13333333356");
			request.setBm39OriginalMemberSerial("333");
			Hs8583Dto response = posPointProcesser.bindTrade(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void discountExpense() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo(merchantNo);
			request.setBm5TerminalNo(posNo);
			// request.setBm4MerchantNo("123456789012345");
			// request.setBm5TerminalNo("11111111");
			request.setBm2MemberMobile("15112656325");
			request.setBm7TradeAmount(10000L);
			// request.setBm39OriginalMemberSerial("333");
			Hs8583Dto response = posPointProcesser.discountExpense(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结算
	 * 
	 * @throws Exception
	 */
	@Test
	public void TradeBussness() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm30BatchNo("7");

			Hs8583ClosingCostDto dto = new Hs8583ClosingCostDto();
			dto.setConsumeAmount(24620L);
			dto.setConsumeCount(6L);
			dto.setReturnAmount(0L);
			dto.setReturnCount(0L);
			request.setBm21ClosingCost(dto);

			// request.setBm4MerchantNo("123321");
			// request.setBm5TerminalNo("8888");

			request.setBm4MerchantNo(this.merchantNo);
			request.setBm5TerminalNo(this.posNo);

			List<Hs8583CouponList> coupons = new ArrayList<Hs8583CouponList>();

			Hs8583CouponList coupon55Dto = new Hs8583CouponList();
			coupon55Dto.setCouponPloySerialNo(55L);
			coupon55Dto.setCouponNum(1L);

			coupons.add(coupon55Dto);

			// Hs8583CouponList coupon58Dto=new Hs8583CouponList();
			// coupon58Dto.setCouponSerialNo(58L);
			// coupon58Dto.setCouponNum(1L);

			// coupons.add(coupon58Dto);

			request.setBm46CouponList(coupons);

			// request.setBm4MerchantNo("000000000000001");
			// request.setBm5TerminalNo("00000001");
			// request.setBm2MemberMobile("15982927989");

			// Hs8583Dto response = posPointProcesser.joinMember(request);
			Hs8583Dto response = posPointProcesser.settleTrade(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消费流程
	 * 
	 * @throws Exception
	 */
	@Test
	public void consleTrade2() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo(this.merchantNo);
			request.setBm5TerminalNo(this.posNo);
			request.setBm30BatchNo("7");

			List<Hs8583BusinessUploadInfo> list = new ArrayList<Hs8583BusinessUploadInfo>();
			for (int i = 0; i < 1; i++) {
				Hs8583BusinessUploadInfo info = new Hs8583BusinessUploadInfo();
				info.setTransType(1);
				// info.setMobileNo("15900000041");
				// info.setMobileNo("15112656327");
				info.setMobileNo("15112656325");

				info.setAmount(6000L);
				info.setTerminalNo(request.getBm5TerminalNo());
				info.setBankCardNo("1111111111111");
				info.setTerminalSerial("101564");
				info.setTerminalTransDate("20110623");
				info.setTerminalTransTime("202020");
				list.add(info);
				this.addCoupon(info, request);
			}
			request.setBm36BusinessUploadInfoList(list);
			Hs8583Dto response = posPointProcesser.uploadTrade(request);
			System.out.print(response);
		} catch (Exception e) {
			System.out.println("-----------------------");
			e.printStackTrace();
		}
	}

	public void addCoupon(Hs8583BusinessUploadInfo info, Hs8583Dto request) {

		List<Hs8583CouponUploadInfo> couponUploadList = new ArrayList<Hs8583CouponUploadInfo>();
		for (int i = 0; i < 1; i++) {
			Hs8583CouponUploadInfo couponInfo = new Hs8583CouponUploadInfo();
			couponInfo.setTerminalSerial(info.getTerminalSerial());
			couponInfo.setCouponSerialNo("91284114");
			couponUploadList.add(couponInfo);
		}
		request.setBm47CouponUploadList(couponUploadList);
	}

	@Test
	public void cancelTrade2() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			// request.setBm4MerchantNo("123321");
			// request.setBm5TerminalNo("8888");

			// request.setBm4MerchantNo("000000000000001");
			// request.setBm5TerminalNo("00000001");
			request.setBm4MerchantNo(this.merchantNo);
			request.setBm5TerminalNo(this.posNo);
			request.setBm2MemberMobile("13333333339");
			request.setBm30BatchNo("3");
			/**
			 * select * from member t where id=28
			 */
			List<Hs8583BusinessUploadInfo> list = new ArrayList<Hs8583BusinessUploadInfo>();
			for (int i = 0; i < 2; i++) {
				Hs8583BusinessUploadInfo info = new Hs8583BusinessUploadInfo();
				// 退货 2
				info.setTransType(2);
				// info.setOriginalBatchNo("1");
				info.setMobileNo("15112656325");
				info.setAmount(20000L);

				info.setTerminalNo(request.getBm5TerminalNo()); // merchant 1
				info.setBankCardNo("1111111111111");
				info.setTerminalSerial("131012");
				// 退货
				info.setOriginalSerial("101559");
				info.setOriginalBatchNo("3");
				info.setOriginalTerminalNo(request.getBm5TerminalNo());

				info.setTerminalTransDate("20110812");
				info.setTerminalTransTime("202020");
				list.add(info);
			}

			request.setBm36BusinessUploadInfoList(list);
			Hs8583Dto response = posPointProcesser.uploadTrade(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void userCouponInspect() throws Exception {
		try {
			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo(this.merchantNo);
			request.setBm5TerminalNo(this.posNo);
			request.setBm6TradeSerialNo("1111111");
			request.setBm30BatchNo("7");
			// 15112656309
			// request.setBm2MemberMobile("15112656309");
			// request.setBm44CouponSeriano(36865948L);
			Hs8583Dto response = posPointProcesser.userCouponInspect(request);
			System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
