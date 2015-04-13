package com.justinmobile.boss.manager;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.base.BaseTest;
import com.vlives.boss.user.dao.UserDao;
import com.vlives.boss.user.manager.UserManager;

@TransactionConfiguration(defaultRollback = false)
public class UserManagerTest extends BaseTest {

//	private static final Hs8583Dto request = null;

	@Autowired
	private UserManager userManager;
	@Resource
	private  UserDao userDao;
	


	/**
	 * 测试数据 merchant id：1 ，code 123321， Pos测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		try {
			System.out.println("id ====");
		//	testTranscationManager.test();
//			Hs8583Dto request = new Hs8583Dto();
//			JSONObject json=JSONObject.fromObject(request);		
//			System.out.println(json.toString());
//			User user=new User();
//			userDao.save(user);
		}catch(UncategorizedSQLException e){
			
		System.out.println("sssssssss");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
