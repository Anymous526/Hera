package com.justinmobile.util;


import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ByteUtil {

	public static byte[] contactArray(byte[] src1, byte[] src2) {
		if (src1 == null || src1 == null) {
			throw new IllegalArgumentException();
		}
		byte[] dest = new byte[src1.length + src2.length];
		System.arraycopy(src1, 0, dest, 0, src1.length);
		System.arraycopy(src2, 0, dest, src1.length, src2.length);
		return dest;
	}

	public static byte[] append(byte[] src, byte b) {
		return contactArray(src, new byte[] { b });
	}

	public static void splitArray(byte[] src, byte[] dest1, byte[] dest2, int pos) {
		if (src == null || dest1 == null || dest2 == null) {
			throw new IllegalArgumentException();
		}
		if (src.length == 0 || pos > src.length) {
			throw new IllegalArgumentException();
		}
		dest1 = new byte[pos];
		dest2 = new byte[src.length - pos];
		System.arraycopy(src, 0, dest1, 0, pos);
		System.arraycopy(src, pos, dest2, 0, src.length - pos);
	}

	public static byte[] subArray(byte[] src, int start, int end) {
		if (start < 0 || start > end || end > src.length) {
			throw new IllegalArgumentException();
		}
		byte[] subBytes = new byte[end - start];
		System.arraycopy(src, start, subBytes, 0, subBytes.length);
		return subBytes;
	}

	public static byte[] leftSubArray(byte[] src, int pos) {
		return subArray(src, 0, pos);
	}

	public static byte[] rightSubArray(byte[] src, int pos) {
		return subArray(src, pos, src.length);
	}

	public static int arrayIndexOf(byte[] src, byte toFind) {
		int index = -1;
		for (int i = 0; i < src.length; i++) {
			if (src[i] == toFind) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static String arrayHexString(byte[] src, String delim) {
		if (delim == null) {
			delim = "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length; i++) {
			byte byteNumber = src[i];
			sb.append(hexString(byteNumber));
			sb.append(delim);
		}
		String toPrint = sb.toString();
		int start = toPrint.length() - delim.length();
		if (delim.equals(toPrint.substring(start, toPrint.length()))) {
			toPrint = toPrint.substring(0, start);
		}
		return toPrint;
	}

	public static String arrayShortHexString(byte[] src, String delim) {
		return arrayHexString(src, delim).replace("0x", "");
	}

	public static String arrayShortHexString(byte[] src) {
		return arrayHexString(src, null).replace("0x", "");
	}

	private static String hexString(byte byteNumber) {
		int toStr = byteNumber;
		if (byteNumber < 0) {
			toStr = byteNumber + 256;
		}
		String byteStr = Integer.toHexString(toStr);
		if (byteStr.length() == 1) {
			byteStr = "0" + byteStr;
		}
		return "0x" + byteStr.toUpperCase();
	}

	public static byte[] hexStringToBytes(String hexStr) {
		int length = hexStr.length();
		if (length % 2 != 0) {
			throw new IllegalArgumentException();
		}
		hexStr = hexStr.toUpperCase();
		byte[] outArray = new byte[length / 2];
		for (int i = 0; i < length; i += 2) {
			char li = hexStr.charAt(i);
			char lo = hexStr.charAt(i + 1);
			if (li < '0' || li > 'F' || lo < '0' || lo > 'F') {
				throw new IllegalArgumentException();
			}
			outArray[i / 2] = (byte) Integer.parseInt(String.valueOf(new char[] { li, lo }), 16);
		}
		return outArray;
	}

	public static byte[] asciiToBcd(String input) {
		byte[] ascii = null, bcd = null;
		try {
			ascii = input.getBytes("US-ASCII");
			if (ascii.length % 2 != 0) {
				throw new IllegalArgumentException();
			}
			bcd = new byte[ascii.length / 2];
			for (int i = 0; i < ascii.length; i += 2) {
				if (ascii[i] < 0x30 || ascii[i] > 0x39) {
					throw new IllegalArgumentException();
				}
				int temp = (ascii[i] & 0x0F) << 4;
				if (temp > 127) {
					temp -= 256;
				}
				byte hi = (byte) temp;
				byte lo = (byte) (ascii[i + 1] & 0x0F);
				bcd[i / 2] = (byte) (hi | lo);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bcd;
	}

	public static byte[] asciiToCnBcd(String input, int bcdBytesLength) {
		if (input.length() > 2 * bcdBytesLength) {
			throw new IllegalArgumentException("the input String is too long");
		}
		input = input.toUpperCase();
		int paddingBytesCount = 2 * bcdBytesLength - input.length();
		for (int i = 0; i < paddingBytesCount; i++) {
			input += 'F';
		}

		byte[] cnBcd = null;
		int index = input.indexOf('F');
		if (index != -1) {
			if (index < input.length() - 1) {
				String forCheck = input.substring(index + 1);
				for (int i = 0; i < forCheck.length(); i++) {
					if (forCheck.charAt(i) != 'F') {
						throw new IllegalArgumentException("the input String is not valid to convert to CnBcd");
					}
				}
			}
			String left = null, right = null;
			if (index%2 != 0) {
				left = input.substring(0, index - 1);
				right = input.substring(index - 1);
			} else {
				left = input.substring(0, index);
				right = input.substring(index);
			}
			byte[] leftBytes = asciiToBcd(left);
			byte[] rightBytes = hexStringToBytes(right);
			cnBcd = contactArray(leftBytes, rightBytes);
		} else {
			cnBcd = asciiToBcd(input);
		}
		return cnBcd;
	}

	public static String bcdToAscii(byte[] bcd) {
		byte[] ascii = new byte[2 * bcd.length];
		for (int i = 0; i < bcd.length; i++) {
			byte hi = (byte) (bcd[i] >> 4);
			byte lo = (byte) (bcd[i] & 0x0F);
			if (hi < 0x00 || hi > 0x09 || lo < 0x00 || lo > 0x09) {
				throw new IllegalArgumentException();
			}
			ascii[2 * i] = ((byte) (hi + 0x30));
			ascii[2 * i + 1] = ((byte) (lo + 0x30));
		}
		return new String(ascii);
	}

	public static byte[] intToCnBcd(int srcInt, int bcdByteCount) {
		if (srcInt < 0) {
			throw new IllegalArgumentException("srcInt should not be less than 0");
		}
		String numStr = String.valueOf(srcInt);
		int digitCount = numStr.length();
		byte[] bcd = null;
		if (digitCount % 2 == 0) {
			if (digitCount / 2 > bcdByteCount) {
				throw new IllegalArgumentException("The digit count of srcInt should not be larger than bcdByteCount");
			} else {
				bcd = asciiToBcd(numStr);
			}
		} else {
			if ((digitCount + 1) / 2 > bcdByteCount) {
				throw new IllegalArgumentException("The digit count of srcInt should not be larger than bcdByteCount");
			} else {
				String leftDigitStr = numStr.substring(0, digitCount - 1);
				bcd = asciiToBcd(leftDigitStr);
				int lowestDigit = Integer.parseInt(numStr.substring(digitCount - 1));
				int temp = (lowestDigit & 0x0F) << 4;
				if (temp > 127) {
					temp -= 256;
				}
				byte hi = (byte) temp;
				byte lo = (byte) 0x0F;
				byte last = (byte) (hi | lo);
				bcd = contactArray(bcd, new byte[] { last });
			}
		}
		if (bcd == null) {
			throw new IllegalStateException();
		}
		if (bcd.length < bcdByteCount) {
			byte[] supplement = new byte[bcdByteCount - bcd.length];
			for (int i = 0; i < supplement.length; i++) {
				supplement[i] = (byte) 0xFF;
			}
			bcd = contactArray(bcd, supplement);
		}
		return bcd;
	}

	public static int cnBcdToInt(byte[] cnBcd) {
		String toIntStr = cnBcdToAscii(cnBcd);
		return Integer.parseInt(toIntStr);
	}

	public static String cnBcdToAscii(byte[] cnBcd) {
		byte[] splitedBytes = new byte[2 * cnBcd.length];
		for (int i = 0; i < cnBcd.length; i++) {
			int temp = cnBcd[i];
			if (temp < 0) {
				temp += 256;
			}
			splitedBytes[2 * i] = (byte) (temp >> 4);
			splitedBytes[2 * i + 1] = (byte) (temp & 0x0F);
		}
		byte[] headArray = null;
		int splitIndex = arrayIndexOf(splitedBytes, (byte) 0x0F);
		if (splitIndex == 0) {
			throw new IllegalArgumentException("error CN BCD head");
		} else if (splitIndex == -1) {
			headArray = splitedBytes;
		} else {
			headArray = leftSubArray(splitedBytes, splitIndex);
			byte[] tailArray = rightSubArray(splitedBytes, splitIndex);
			for (int i = 0; i < tailArray.length; i++) {
				if (tailArray[i] != 0x0F) {
					throw new IllegalArgumentException("error CN BCD tail");
				}
			}
		}
		byte[] toStrBytes = new byte[headArray.length];
		for (int i = 0; i < headArray.length; i++) {
			if (headArray[i] < 0x00 || headArray[i] > 0x09) {
				throw new IllegalArgumentException("error CN BCD head");
			}
			toStrBytes[i] = (byte) (headArray[i] + 0x30);
		}
		String toIntStr = null;
		try {
			toIntStr = new String(toStrBytes, "US-ASCII");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return toIntStr;
	}

	public static String toHexString(byte[] block) {
		return arrayShortHexString(block);
	}

	public static String generateHexString(int byteLength) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteLength; i++) {
			Random radom = new Random();
			char hi = hexChars[radom.nextInt(16)];
			char lo = hexChars[radom.nextInt(16)];
			sb.append(hi);
			sb.append(lo);
		}
		return sb.toString();
	}

	public static String generateTransIdStr(long input, int outputLength) {
		String output = String.valueOf(input);
		if (output.length() > outputLength) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < outputLength - output.length(); i++) {
			sb.append('0');
		}
		return sb.append(output).toString();
	}

	public static byte[] intToHexBytes(int srcInt, int byteCount) {
		if (byteCount < 1 || byteCount > 4) {
			throw new IllegalArgumentException("byteCount should not be less than 1 or larger than 4");
		}
		int max = (int) Math.pow(2, 8 * byteCount) - 1;
		if (srcInt < 0 || srcInt > max) {
			throw new IllegalArgumentException("srcInt should not be less than 0 or larger than " + max);
		}
		String hexStr = Integer.toHexString(srcInt);
		if (hexStr.length() % 2 != 0) {
			hexStr = "0" + hexStr;
		}
		int paddingByteCount = byteCount - hexStr.length() / 2;
		if (paddingByteCount < 0) {
			throw new IllegalArgumentException("srcInt is larger than " + max);
		}
		byte[] paddingBytes = new byte[paddingByteCount];
		for (int i = 0; i < paddingBytes.length; i++) {
			paddingBytes[i] = 0x00;
		}
		byte[] unpaddingBytes = new byte[hexStr.length() / 2];
		for (int i = 0; i < unpaddingBytes.length; i++) {
			String byteHex = hexStr.substring(2 * i, 2 * i + 2);
			unpaddingBytes[i] = (byte) Integer.parseInt(byteHex, 16);
		}
		return ByteUtil.contactArray(paddingBytes, unpaddingBytes);
	}

	public static int binaryToInt(byte[] binary) {
		String hexStr = toHexString(binary);
		return Integer.parseInt(hexStr, 16);
	}

	public static String intToBinaryString(int srcInt, int bitCount) {

		if (bitCount < 1 || bitCount > 32) {
			throw new IllegalArgumentException("bitCount should not be less than 1 or larger than 32");
		}
		int max = (int) Math.pow(2, bitCount) - 1;
		if (srcInt < 0 || srcInt > max) {
			throw new IllegalArgumentException("srcInt should not be less than 0 or larger than " + max);
		}
		String binaryStr = Integer.toBinaryString(srcInt);
		int paddingBitCount = bitCount - binaryStr.length();
		if (paddingBitCount < 0) {
			throw new IllegalArgumentException("srcInt is larger than " + max);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < paddingBitCount; i++) {
			sb.append('0');
		}
		sb.append(binaryStr);
		return sb.toString();
	}

	public static String intToKoalFormat(int srcInt) {
		if (srcInt < 0) {
			throw new IllegalArgumentException("the input int should not be less than 0");
		}
		String temp = String.valueOf(srcInt);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10 - temp.length(); i++) {
			sb.append('0');
		}
		sb.append(temp);
		return sb.toString();
	}
	
	public static byte[] bitComplement(byte[] src) {
		byte[] dest = new byte[src.length];
		for (int i = 0; i < src.length; i++) {
			dest[i] = (byte)(~src[i]);
		}
		return dest;
	}
	
	public static byte[] arrayXOR(byte[] a, byte[] b) {
		if(a.length != b.length) {
			throw new IllegalArgumentException();
		}
		byte[] c = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = (byte) (a[i]^b[i]);
		}
		return c;
	}
	
	public static String formatedHexString(byte[] src) {
		String delim = " ";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length; i++) {
			byte byteNumber = src[i];
			sb.append(hexString(byteNumber).substring(2));
			sb.append(delim);
			if ((i + 1) % 16 == 0) {
				sb.append(System.getProperty("line.separator"));
			}
		}
		String toPrint = sb.toString();
		int start = toPrint.length() - delim.length();
		if (delim.equals(toPrint.substring(start, toPrint.length()))) {
			toPrint = toPrint.substring(0, start);
		}
		return toPrint;
	}
	
	 /**整行转化为三个字节的网络字节序
	 * @param n
	 * @return
	 */
	public static byte[] toLLLH(int n) {   
		      byte[] b = new byte[3];   
		      b[0] = (byte) (n & 0xff);   
		      b[1] = (byte) (n >> 8 & 0xff);   
		      b[2] = (byte) (n >> 16 & 0xff);   
		    //  b[3] = (byte) (n >> 24 & 0xff);   
		      return b;   
	}   
	 /**整行转化为三个字节的网络字节序
	 * @param n
	 * @return
	 */ 
	 public static byte[] toLLH(int n) {   
	        byte[] b = new byte[2];   
	        b[0] = (byte) (n & 0xff);   
	        b[1] = (byte) (n >> 8 & 0xff);   
	        return b;   
	 } 
	 
	    /**三个字节的网络字节序转化为本地字节序
	     * @param n
	     * @return
	     */
	    public static byte[] toHHH(int n) {   
	        byte[] b = new byte[3];   
	        b[2] = (byte) (n & 0xff);   
	        b[1] = (byte) (n >> 8 & 0xff);   
	        b[0] = (byte) (n >> 16 & 0xff);   
	       // b[0] = (byte) (n >> 24 & 0xff);   
	        return b;   
	    }  
	    /**两个个字节的网络字节序转化为本地字节序
	     * @param n
	     * @return
	     */
	    public static byte[] toHH(short n) {   
	        byte[] b = new byte[2];   
	        b[1] = (byte) (n & 0xff);   
	        b[0] = (byte) (n >> 8 & 0xff);   
	        return b;   
	      }    
	 
	   public static byte[] exchangeHLSequence(byte[] resource){
		   int j=resource.length;
		   byte[] b=new byte[j];
		   for(int i=0;i<=resource.length-1;i++){
			   j--;
			 b[i]=  resource[j];
		   }
		   return b;
	   }
	
}
