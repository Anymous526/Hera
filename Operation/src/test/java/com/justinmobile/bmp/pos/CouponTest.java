package com.justinmobile.bmp.pos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.justinmobile.bmp.cloudboss.coupon.web.CouponController;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CouponTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CouponController couponController;
	
	@Test
	public void testCouponWeb(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
//		request.setMethod("POST");
//		request.addParameter("viewDetails", "true");
		couponController.list(request, response);
	}
}
