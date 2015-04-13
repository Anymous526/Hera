package com.vlives.util.security;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;
/**
 * <p>
 * Title: 证书签名类
 * </p>
 * <p>
 * Description: 该程序根据签发者（CA）的证书信息（即CA的私钥）来对被签发者
 * 的证书进行签名，
 * ，过程即是使用CA的证书和被签证书来重构形成一个新的证书，新证书编码规则为X509
 * </p>
 * @author jianguo.xu
 *
 */
public class SignCertUtils {
	/**
	 * 
	 * @author jianguo.xu
	 * @param cer Certificate			CA证书对象
	 * @param privateKey Certificate 	CA私钥
	 * @param cered 					被签证书对象 
	 * @param beginDate					被签证书有效期开始日期
	 * @param endDate					被签证书有效期终止日期
	 * @param sn						 被签证书序列号，这个序列号由CA根据自己的规则确定，比如200701001代表该CA在2007年1月签发的第001个证书
	 * @return X509CertImpl 			返回X509编码规则的证书
	 * @throws CertificateException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws SignatureException
	 */
	public static X509CertImpl sign(Certificate cer,
			PrivateKey privateKey, Certificate cered,
			Date beginDate, Date endDate, int sn) throws CertificateException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException{
		X500Name issuer = getIssuer(cer);
		X509CertInfo x509CertInfo = getCeredX509CertInfo(cered);
		update(x509CertInfo,issuer,beginDate,endDate,sn);
		
		// 创建新的签名后的证书
		X509CertImpl newcert = new X509CertImpl(x509CertInfo);

		// 签名,使用CA证书的私钥进行签名，签名使用的算法为SHA1withRSA
		newcert.sign(privateKey, SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		return newcert;
	}
	/**
	 * 更新x509CertInfo相关信息
	 * @author jianguo.xu
	 * @param certInfo
	 * @param issuer
	 * @param beginDate
	 * @param endDate
	 * @param sn
	 * @throws CertificateException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	private static void update(X509CertInfo certInfo,X500Name issuer,Date beginDate,Date endDate,int sn) throws CertificateException, IOException, NoSuchAlgorithmException {
		// 设置被签证书有效期
		CertificateValidity cv = new CertificateValidity(beginDate, endDate);
		certInfo.set(X509CertInfo.VALIDITY, cv);

		// 设置新证书的序列号
		CertificateSerialNumber csn = new CertificateSerialNumber(sn);
		certInfo.set(X509CertInfo.SERIAL_NUMBER, csn);

		// 设置新证书的签发者
		certInfo.set(X509CertInfo.ISSUER + "."
				+ CertificateIssuerName.DN_NAME, issuer);

		// 设置新证书的算法，指定CA签名该证书所使用的算法为sha1WithRSA
		AlgorithmId algorithmId = AlgorithmId.get(SecurityPropertiesUtils.DIGITAL_SIGN_ALGORITHM);
		certInfo.set(CertificateAlgorithmId.NAME + "."
				+ CertificateAlgorithmId.ALGORITHM, algorithmId);
	}
	
	/**
	 * 得到签发者信息
	 * @author jianguo.xu
	 * @param cer
	 * @return
	 * @throws CertificateException
	 * @throws IOException
	 */
	private static X500Name getIssuer(Certificate cer) throws CertificateException, IOException {
		// 提取CA证书的编码
		byte[] encodl = cer.getEncoded();
		// 根据CA证书的编码创建X509CertImpl类型的对象
		X509CertImpl cimpl = new X509CertImpl(encodl);

		// 根据上面的对象获得X509CertInfo类型的对象，该对象封装了CA证书的全部内容。
		X509CertInfo cinfo_first = (X509CertInfo) cimpl.get(X509CertImpl.NAME
				+ "." + X509CertImpl.INFO);

		// 然后获得X500Name类型的签发者信息
		return (X500Name) cinfo_first.get(X509CertInfo.SUBJECT + "."
				+ CertificateIssuerName.DN_NAME);
	}
	/**
	 * 得到被签发者的X509CertInfo对象
	 * @author jianguo.xu
	 * @param cered
	 * @throws CertificateException
	 */
	private static X509CertInfo getCeredX509CertInfo(Certificate cered) throws CertificateException {
		// 提取被签证书的编码
		byte[] encod2 = cered.getEncoded();
		// 根据被签证书的编码创建X509CertImpl类型的对象
		X509CertImpl cimp2 = new X509CertImpl(encod2);

		// 根据上面的对象获得X509CertInfo类型的对象，该对象封装了被签证书的全部内容。
		return (X509CertInfo) cimp2.get(X509CertImpl.NAME
				+ "." + X509CertImpl.INFO);
	}
	
	
	
	
	
	
	/*public static void main(String[] args) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, IOException, KeyStoreException {
		KeyStoreUtils.createKeyStore("test001","test001", "test001", "test001");
		KeyStore keystore = KeyStoreUtils.loadKeyStore("d:/home/key/test001", "test001");
		Certificate cered = KeyStoreUtils.getCertificate(keystore, "test001");
		X509CertImpl x509CertImpl = sign(KeyStoreUtils.ROOT_CERTIFICATE, KeyStoreUtils.ROOT_PRIVATE_KEY, cered, new Date(), new Date(),1234567);
		
		PrivateKey privateKey = KeyStoreUtils.getPrivateKey(keystore, "test001", "test001");
		
		KeyStore newKeyStore = KeyStore.getInstance("PKCS12");
		newKeyStore.load(null, "test001".toCharArray());
		 
		newKeyStore.setKeyEntry("1234567", privateKey, "test001".toCharArray(), new java.security.cert.Certificate[] { x509CertImpl });
		FileOutputStream ous = new FileOutputStream("D:/home/www/security/test001");
		newKeyStore.store(ous, "test001".toCharArray());
		ous.flush();
		ous.close();
	}*/
}
