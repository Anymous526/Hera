package com.justinmobile.boss.manager;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.jdbc.UncategorizedSQLException;

public class OhterTest {

	
	@Test
	public void testCreate() throws Exception {
		try {
			
//			String bm19PosBussList="1";
//			
//			if(StringUtils.isNotBlank(bm19PosBussList)){
//				String b = String.format("%4s", bm19PosBussList).replace(" ", "0");
//				System.out.print(b);
//			}
//			Integer.toBinaryString(a);//转换成2进制
//			Integer.toOctalString(b);//转换成8进制
//			Integer.toHexString(c)//转换成16进制
	         System.out.println(Integer.toOctalString(9));
			System.out.println(Integer.toHexString(13));
			
		}catch(UncategorizedSQLException e){
			
		System.out.println("sssssssss");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
