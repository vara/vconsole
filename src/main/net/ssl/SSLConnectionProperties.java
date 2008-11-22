/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.net.ssl;

import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author vara
 */
public class SSLConnectionProperties {

    private static String keyStored=null;
    private static String keyPass=null;
    private static String storePass=null;    
    private static String trustStore=null;
    
    
    public SSLConnectionProperties(){	
    }
    
    protected static SSLContext createSSLContext(String type) throws Exception{
	
	if(getKeyStore()==null||getKeyStore().equals(""))
	    throw new Exception("[ERROR] Path to key Store is null !\n" +
				"Try set -Djavax.net.ssl.keyStore=[path to key store]");
	if(getStorePass()==null||getStorePass().equals(""))
	    throw new Exception("[ERROR] Password to key Store is null !\n " +
				"Try set -Djavax.net.ssl.keyStorePassword=[password]");
	if(getKeyPass()==null||getKeyPass().equals(""))
	    throw new Exception("[ERROR] Password to key Store is null !\n " +
				"Try set -Djavax.net.ssl.keyStorePassword=[password]");
	try {   
		
		SSLContext ctx;
		
/*  ----------------KeyManagerFactory-------------------------------------
    This class acts as a factory for key managers based 
    on a source of key material. Each key manager manages 
    a specific type of key material for use by secure sockets. 
    The key material is based on a KeyStore and/or provider specific sources.*/
		KeyManagerFactory keyManager;

/*  --------------------KeyStore------------------------------------------
    This class represents an in-memory collection of keys and certificates. 
    It manages two types of entries:

    1 Key Entry 
	This type of keystore entry holds very sensitive cryptographic key information, 
	which is stored in a protected format to prevent unauthorized access. 
	Typically, a key stored in this type of entry is a secret key, or a private key 
	accompanied by the certificate chain for the corresponding public key. 
	Private keys and certificate chains are used by a given entity for self-authentication. 
	Applications for this authentication include software distribution organizations which 
	sign JAR files as part of releasing and/or licensing software.

    2 Trusted Certificate Entry 
	This type of entry contains a single public key certificate belonging to 
	another party. It is called a trusted certificate because the keystore owner trusts 
	that the public key in the certificate indeed belongs to the identity identified by 
	the subject (owner) of the certificate. 
	This type of entry can be used to authenticate other parties.
 */ 
		KeyStore keyStore;

		keyStore = KeyStore.getInstance("JKS");		
		keyStore.load(new FileInputStream(getKeyStore()),
					    getStorePass().toCharArray());		
		keyManager = KeyManagerFactory.getInstance("SunX509");
		keyManager.init(keyStore, getKeyPass().toCharArray());
		
		ctx = SSLContext.getInstance(type);
		ctx.init(keyManager.getKeyManagers(), null, null);		
		return ctx;

	    } catch (Exception e) {throw e;}
    }
    
    public static SSLServerSocketFactory getServerSocketFactory(String type)throws Exception {
		
	try {
	    if (type.equals("TLS"))  
		return createSSLContext(type).getServerSocketFactory();	    
	    else 
		return (SSLServerSocketFactory) ServerSocketFactory.getDefault();
	}catch(Exception e){throw e;}
    }
    
    public static SSLSocketFactory getSSLSocketFactory(String type) throws Exception{	
	try {
	    if(type==null) type="TLS";
	    if (type.equals("TLS")){ 
	    
		    return createSSLContext(type).getSocketFactory();
	    }else
		return (SSLSocketFactory) SSLSocketFactory.getDefault();
	}catch (Exception e) { throw e; }
	
    }
    
    public static void setKeyStore(String path){
	keyStored = path;
    }
    
    public static String getKeyStore(){
	return keyStored;
    }
    public static String getStorePass() {
	return storePass;
    }

    public static void setStorePass(String aPasswordKeyStore) {
	storePass = aPasswordKeyStore;
    }
    public static void setSSlProperities(String key,String val){
	System.setProperty(key, val);
    }
    public static String getTrustStore() {
	return trustStore;
    }

    public static void setTrustStore(String aPathToTrustStore) {
	trustStore = aPathToTrustStore;
    }
    public static String getKeyPass() {
	return keyPass;
    }

    public static void setKeyPass(String aKeyPass) {
	keyPass = aKeyPass;
    }
}
