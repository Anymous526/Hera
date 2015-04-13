/**
 * @(#)CertificateUtils.java
 *
 * Copyright 2010 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util.security;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2010-12-9
 */
public class CertificateUtils {

	private static final Log LOG = LogFactory.getLog(CertificateUtils.class);

	/**
	 * 验证证书是否过期或无效
	 * @param date
	 * @param certificate
	 * @return
	 */
	public static boolean verifyCertificate(Date date, Certificate certificate) {
		try {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			x509Certificate.checkValidity(date);
			return true;
		} catch (Exception e) {
			LOG.error("verify certificate error.", e);
			return false;
		}
	}
	
	/**
	 * 验证证书是否过期或无效
	 * @param date
	 * @param certificate
	 * @return
	 */
	public static boolean verifyCertificate(Certificate certificate) {
		try {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			x509Certificate.checkValidity(new Date());
			return true;
		} catch (Exception e) {
			LOG.error("verify certificate error.", e);
			return false;
		}
	}
	
	
	
}
