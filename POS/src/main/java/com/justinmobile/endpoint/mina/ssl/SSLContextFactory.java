package com.justinmobile.endpoint.mina.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLContextFactory {

	private final String PROTOCOL = "TLS";
	private final String keyManagerFactoryAlgorithm;
	private final String trustManagerFactoryAlgorithm;

	/**
	 * Bougus Server certificate keystore file name.
	 */
	private String clientKeystore ;//"config/myclient.jks";
	private String clientTrustKeystore;// "config/clientTrust.jks";
	private String serverKeystroe;//= "config/server.jks";
	private String passwd;

	// NOTE: The keystore was generated using keytool:
	// keytool -genkey -alias bogus -keysize 512 -validity 3650
	// -keyalg RSA -dname "CN=bogus.com, OU=XXX CA,
	// O=Bogus Inc, L=Stockholm, S=Stockholm, C=SE"
	// -keypass boguspw -storepass boguspw -keystore bogus.cert

	SSLContextFactory() {
		String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
		if (algorithm == null) {
			algorithm = KeyManagerFactory.getDefaultAlgorithm();
		}
		keyManagerFactoryAlgorithm = algorithm;

		algorithm = Security.getProperty("ssl.TrustManagerFactory.algorithm");
		if (algorithm == null) {
			algorithm = TrustManagerFactory.getDefaultAlgorithm();
		}
		trustManagerFactoryAlgorithm = algorithm;
	}

	private SSLContext serverInstance = null;

	private SSLContext clientInstance = null;

	public SSLContext getClientInstance() throws GeneralSecurityException {
		return this.getInstance(false);
	}
	
	/**
	 * Get SSLContext singleton.
	 * 
	 * @return SSLContext
	 * @throws java.security.GeneralSecurityException
	 * 
	 */
	public SSLContext getInstance(boolean server) throws GeneralSecurityException {
		SSLContext retInstance = null;
		if (server) {
			synchronized (SSLContextFactory.class) {
				if (serverInstance == null) {
					try {
						serverInstance = createBougusServerSslContext();
					} catch (Exception ioe) {
						throw new GeneralSecurityException("Can't create Server SSLContext:" + ioe);
					}
				}
			}
			retInstance = serverInstance;
		} else {
			synchronized (SSLContextFactory.class) {
				if (clientInstance == null) {
					clientInstance = createBougusClientSslContext();
				}
			}
			retInstance = clientInstance;
		}
		return retInstance;
	}

	private SSLContext createBougusServerSslContext() throws GeneralSecurityException, IOException {
		SSLContext context = SSLContext.getInstance(PROTOCOL);

		try {
			context.init(getKeyManagers(serverKeystroe, passwd.toCharArray()),
					getTrustManagers(serverKeystroe, passwd.toCharArray()), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return context;
	}

	private SSLContext createBougusClientSslContext() throws GeneralSecurityException {
		SSLContext context = SSLContext.getInstance(PROTOCOL);

		try {
			context.init(getKeyManagers(clientKeystore, passwd.toCharArray()),
					getTrustManagers(clientTrustKeystore, passwd.toCharArray()), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return context;
	}

	private KeyManager[] getKeyManagers(String keysfile, char[] password) throws GeneralSecurityException, IOException {
		// First, get the default KeyManagerFactory.
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(keyManagerFactoryAlgorithm);

		// Next, set up the TrustStore to use. We need to load the file into
		// a KeyStore instance.
		KeyStore ks = KeyStore.getInstance("JKS");
		InputStream in = SSLContextFactory.class.getClassLoader().getResourceAsStream(keysfile);
		ks.load(in, password);
		in.close();

		// Now we initialise the KeyManagerFactory with this KeyStore
		kmf.init(ks, password);

		// And now get the TrustManagers
		return kmf.getKeyManagers();
	}

	protected TrustManager[] getTrustManagers(String trustfile, char[] pasword) throws IOException,
			GeneralSecurityException {

		TrustManagerFactory tmFact = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);

		// Next, set up the TrustStore to use. We need to load the file into
		// a KeyStore instance.
		InputStream in = SSLContextFactory.class.getClassLoader().getResourceAsStream(trustfile);
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(in, pasword);
		in.close();

		// Now we initialise the TrustManagerFactory with this KeyStore
		tmFact.init(ks);

		// And now get the TrustManagers
		TrustManager[] tms = tmFact.getTrustManagers();
		return tms;
	}

	public String getClientKeystore() {
		return clientKeystore;
	}

	public void setClientKeystore(String clientKeystore) {
		this.clientKeystore = clientKeystore;
	}

	public String getClientTrustKeystore() {
		return clientTrustKeystore;
	}

	public void setClientTrustKeystore(String clientTrustKeystore) {
		this.clientTrustKeystore = clientTrustKeystore;
	}

	public String getServerKeystroe() {
		return serverKeystroe;
	}

	public void setServerKeystroe(String serverKeystroe) {
		this.serverKeystroe = serverKeystroe;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
