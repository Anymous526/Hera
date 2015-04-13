package com.justinmobile.boss.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.justinmobile.endpoint.mina.domain.Hs8583BusinessUploadInfo;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.service.PosProcesserA1;

public class PosBussMain {

	public void cancelTrade2()  {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");

			PosProcesserA1 posPointProcesser = (PosProcesserA1) ctx.getBean("posProcesserA1V1_0");

			System.out.println("id ====");
			Hs8583Dto request = new Hs8583Dto();
			request.setBm4MerchantNo("123321");
			request.setBm5TerminalNo("8888");
			request.setBm2MemberMobile("15982927988");
			request.setBm30BatchNo("3");
			/**
			 * select * from member t where id=28
			 * 
			 * 
			 * 
			 */

			List<Hs8583BusinessUploadInfo> list = new ArrayList<Hs8583BusinessUploadInfo>();
			for (int i = 0; i < 1; i++) {
				Hs8583BusinessUploadInfo info = new Hs8583BusinessUploadInfo();
				// 退货 2
				info.setTransType(2);
				info.setOriginalBatchNo("1");
				info.setMobileNo("15982927988");
				info.setAmount(20000L);

				info.setTerminalNo("8888"); // merchant 1
				info.setBankCardNo("1111111111111");
				info.setTerminalSerial("10007" + i);
				// 退货
				info.setOriginalSerial("100060");
				info.setOriginalBatchNo("3");
				info.setOriginalTerminalNo("8888");

				info.setTerminalTransDate("20110612");
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

	public static void main(String[] args) {
	
		PosBussMain mainX= new PosBussMain();
		try {
			mainX.cancelTrade2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
