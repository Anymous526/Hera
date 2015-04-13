package com.vlives.util;

import java.util.Random;
/**
 * 创建随机码
 * @author  xu jian guo
 * @version 1.0,Jun 11, 2008
 */
public class RandomCodeUtils {
	 
	private static final Random RANDOM = new Random();
	/**
	 * 创建随机码
	 * @author jianguo.xu
	 * @param length
	 * @param type 随机码长度
	 * @return   随机码类型
	 */
	public static String random(int length,RandomType type) {
		if(length<=0) return null;
		StringBuffer sb = new StringBuffer();
		for( int i = 0; i < length; i ++) {
			if(type == RandomType.NUMBER) {
				sb.append(RANDOM.nextInt(10));
			}
			else if(type == RandomType.LETTER_UPPERCASE) {
				sb.append((char)(65 + RANDOM.nextInt(26)));
			}
			else {
				if(RANDOM.nextBoolean()) {
					sb.append((char)(65 + RANDOM.nextInt(26)));
				}
				else {
					sb.append(RANDOM.nextInt(10));
				}
			}
			
	    }
		return sb.toString();
	}
	public static enum RandomType {
		/**
		 * 数字
		 */
		NUMBER,
		/**
		 * 大写之母
		 */
		LETTER_UPPERCASE,
		/**
		 * 大写字母+数字
		 */
		LETTER_UPPERCASE_NUMBER;
	}
	/**
	 * 生成随机数组
	 * @author jianguo.xu
	 * @param min 随机数最小值(min>0)
	 * @param max随机数最大值(max>=0)
	 * @param count 生成随机数的数组大小
	 * @return
	 */
	public static int[] random(int min,int max,int count) {
		checkRandomParam(min, max, count);
		int[] counts = new int[count];
		for(int i = 0;i<count;i++) {
			counts[i] = RANDOM.nextInt(max-min+1)+min;
		}
		return counts;
	}
	private static void checkRandomParam(int min,int max,int count) {
		if(min<0)
			throw new RuntimeException("min must more than 0");
		if(max<=0)
			throw new RuntimeException("max must more than 0");
		if(min>max)
			throw new RuntimeException("max must more than min");
	}
	
	 
	
}

