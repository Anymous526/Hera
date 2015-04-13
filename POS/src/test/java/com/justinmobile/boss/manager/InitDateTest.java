package com.justinmobile.boss.manager;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.base.BaseTest;
import com.vlives.boss.merchant.domain.Merchant;
import com.vlives.boss.merchant.manager.MerchantManager;
import com.vlives.boss.pos.domain.Pos;
import com.vlives.core.dao.generic.BaseDao;

@TransactionConfiguration(defaultRollback = false)
public class InitDateTest extends BaseTest {

	@Resource
	private BaseDao<Merchant, Long> merchantDao;
    @Autowired
    private MerchantManager  merchantManager;
	
    @Resource
    private BaseDao<Pos, Long>  posDao;
	
	/**
	 * 测试数据 merchant id：1 ，code 123321，
	 * Pos测试
	 * @throws Exception
	 */
    //@Test
	public void testCreate() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");

//			PosPointProcesser posPointProcesser = (PosPointProcesser) ctx.getBean("posPointProcesser");
			
			
			
			System.out.println("id ====");
			Merchant merchant = new Merchant();
			super.setSimpleProperties(merchant);
			merchant.setCode("88888");
			//merchant.setMemberGroup(new MemberGroup().)
			
			merchantDao.save(merchant);
			
			

			System.out.println("id ====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 8888
	 * @throws Exception
	 */
	@Test
	public void testPos() throws Exception {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
