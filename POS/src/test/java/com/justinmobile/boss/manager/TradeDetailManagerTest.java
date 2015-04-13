package com.justinmobile.boss.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.base.BaseTest;
import com.vlives.boss.member.domain.Level;
import com.vlives.boss.member.domain.Member;
import com.vlives.boss.member.domain.MemberPointDetail;
import com.vlives.boss.member.manager.MemberManager;
import com.vlives.boss.member.manager.MemberPointDetailManager;
import com.vlives.boss.merchant.manager.MemberUpdateRuleManager;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.merchant.manager.PointRuleManager;
import com.vlives.boss.merchant.manager.UpdateRuleItemManager;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.boss.trade.domain.TradeDetail;
import com.vlives.boss.trade.manager.TradeBatchManager;
import com.vlives.boss.trade.manager.TradeDetailManager;
import com.vlives.boss.trade.manager.TradeOrderManager;
import com.vlives.core.dao.generic.BaseDao;

@TransactionConfiguration(defaultRollback = false)
public class TradeDetailManagerTest extends BaseTest {

	@Autowired
	private TradeBatchManager tradeBatchManager;
	@Autowired
	private TradeDetailManager tradeDetailManager;
	@Autowired
	private TradeOrderManager tradeOrderManager;
	@Autowired
	private MerchantManager merchantManager;
	@Resource
	private BaseDao<Pos, Long> posDao;
	@Autowired
	private PointRuleManager pointRuleManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private UpdateRuleItemManager updateRuleItemManager;
	@Autowired
	private MemberPointDetailManager memberPointDetailManager;
	@Autowired
	private MemberUpdateRuleManager memberUpdateRuleManager;
	
	// @Test
	public void createBatch() throws Exception {
		try {
			System.out.println("id ====");
			// tradeBatchManager.createBatch(pos, batchNumber);
			// System.out.print(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testCreate() throws Exception {
		try {
			System.out.println("id ====");
			// TradeDetail tradeDetail = new TradeDetail();
			// tradeDetail.setTradeDate(new Date());
			// tradeDetail.setConsumeTrade(true);
			// tradeDetail.setTradeSerialNo(123456);
			// tradeDetail.setTradeBatch(tradeBatchManager.get(1L));
			// tradeDetail.setOrigTradeBatch(tradeBatchManager.get(1L));
			// tradeDetail.setTradeOrder(tradeOrderManager.getId(1L));
			// tradeDetailManager.save(tradeDetail);

			tradeDetailManager.getTradeDetail(tradeBatchManager.get(1L), 111111);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() throws Exception {
		try {
			System.out.println("id ====");
			MemberPointDetail memberPointDetail2 = memberPointDetailManager.get(24L);

			// MemberPointDetail memberPointDetail =
			// memberPointDetailManager.getByMemberAndOrder(memberManager.get(28L),
			// tradeOrderManager.getId(123L), ChangeType.TYPE_REGISTER);
			// System.out.println(memberPointDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testDetail() throws Exception {
		try {
			System.out.println("id ====");
			TradeDetail tradeDetail = new TradeDetail();
			tradeDetail.setTradeDate(new Date());
			tradeDetail.setConsumeTrade(true);
			tradeDetail.setTradeSerialNo(123456);
			tradeDetail.setTradeBatch(tradeBatchManager.get(1L));
			// tradeDetail.setOrigTradeBatch(tradeBatchManager.get(1L));
			tradeDetail.setTradeOrder(tradeOrderManager.getId(1L));
			tradeDetailManager.save(tradeDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testUpGradeLevel() throws Exception {
		try {
			System.out.println("id ====");
			Member member = memberManager.get(22L);
			// updateRuleItemManager.upMemberGrade(member, new
			// BigDecimal("450.23"));
			System.out.println(member.getLevel().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testIncreamtPoint() throws Exception {
		try {
			System.out.println("id ====");
			/**
			 * 1 100
			 */
			Member member = memberManager.get(21L);
			member.setLevel(Level.GOLD);
			// int b = pointRuleManager.getIncreasePoint(member, new
			// BigDecimal("300.23"));

			// System.out.println(b);
			// System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Test
	public void testUpdateRule() throws Exception {
		try {
			System.out.println("id ====");
			memberUpdateRuleManager.getByMerchantAndLevel(merchantManager.get(1008L), Level.GENERAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
