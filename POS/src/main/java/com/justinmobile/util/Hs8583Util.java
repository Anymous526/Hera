package com.justinmobile.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.justinmobile.endpoint.mina.domain.Hs8583ClosingCostDto;
import com.justinmobile.endpoint.mina.domain.Hs8583Dto;
import com.justinmobile.endpoint.mina.request.MtRequestCode;
import com.justinmobile.endpoint.mina.service.PosServiceException;
import com.vlives.core.exception.PosReturnCode;

public class Hs8583Util {

	public static Map<Integer, Integer> getBmlentMap() {
		Map<Integer, Integer> bmLent = new HashMap<Integer, Integer>();
		bmLent.put(0, 4);
		bmLent.put(1, 8);
		bmLent.put(2, 11);
		bmLent.put(3, 6);
		bmLent.put(4, 15);
		bmLent.put(5, 8);
		bmLent.put(6, 6);
		bmLent.put(7, 10);
		bmLent.put(8, 10);
		bmLent.put(9, 6);
		bmLent.put(11, 8);
		bmLent.put(12, 6);
		bmLent.put(13, 20);
		bmLent.put(14, 40);
		bmLent.put(15, 16);
		bmLent.put(16, 4);
		bmLent.put(17, 20);
		bmLent.put(21, 64);
		bmLent.put(23, 2);
		bmLent.put(24, 24);
		bmLent.put(25, 14);
		bmLent.put(29, 6);
		bmLent.put(30, 6);
		bmLent.put(31, 6);
		bmLent.put(35, 10);
		bmLent.put(36, 450);
		bmLent.put(37, 6);
		bmLent.put(38, 1);
		bmLent.put(39, 20);
		bmLent.put(40, 10);
		return bmLent;
	}



	/**验证报文的合法性
	 * @param hs
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings( { "unused", "unchecked" })
	public static void validIsNotNull(Hs8583Dto hs)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		MtRequestCode rc = MtRequestCode.get(hs.getBm0MessageType()
				+ hs.getBm3TradeCode());
		Field[] fields = hs.getClass().getDeclaredFields();

//		for (int i : rc.getValid()) {
//			if (fields[i].getName().startsWith("bm" + i)) {
//				Method m = hs.getClass().getDeclaredMethod(
//						"get" + StringUtils.capitalize(fields[i].getName()));
//				String type = fields[i].getType().getName();
//				if (type.equals("java.lang.String")) {
//					// String date=(String)
//					// message.get(StringUtils.upperCase(field.getName()));
//					String value = (String) m.invoke(hs);
//					if (value == null || value == "")
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//					if (value.length() > getBmlentMap().get(i))
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//				} else if (type.equals("java.lang.Long")) {
//					Integer value = (Integer) m.invoke(hs);
//					if (value == null)
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//				} else if (type.equals("java.util.List")) {
//					List value = (List) m.invoke(hs);
//					if (value.size() == 0 || value == null)
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//					
//				} else if (type
//						.equals("com.justinmobile.test.Hs8583ClosingCostDto")) {
//					Hs8583ClosingCostDto hcc = (Hs8583ClosingCostDto) m
//							.invoke(hs);
//					if (hcc == null)
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//					else {
//						validHcc(hcc);
//					}
//				} else {
//					// 非字符串属性
//					Object o = m.invoke(hs);
//					if (o == null)
//						throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
//				}
//			}
//
//		}
	}

	private static void validHcc(Hs8583ClosingCostDto hcc) {
		if (hcc.getConsumeAmount() == null || hcc.getConsumeCount() == null
				|| hcc.getIntegralConsumeAmount() == null
				|| hcc.getIntegralConsumeCount() == null
				|| hcc.getIntegralReturnAmount() == null
				|| hcc.getIntegralReturnCount() == null
				|| hcc.getReturnAmount() == null
				|| hcc.getReturnCount() == null) {
			 throw new PosServiceException(PosReturnCode.DATAGRAM_ERROR);
		}
	}
}
