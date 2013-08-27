package com.voxeolabs.jmxwatcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.voxeo.util.AESUtils;

public class PrismProperties {
	private static final String prismHome = "/opt/voxeo/prism";

	String prismRMIPort = null;
	String prismRMIUser = null;
	String prismRMIPass = null;
	String prismRMIAddr = null;

	public PrismProperties() {
		getProperties(prismHome);
	}

	public PrismProperties(String propertiesPath, String aesKey) {
		getProperties(propertiesPath);
	}

	public PrismProperties(String propertiesPath) {
		getProperties(propertiesPath);
	}

	private void getProperties(String propertiesPath) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propertiesPath
					+ "/conf/sipenv.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		setPrismRMIPort(prop.getProperty("kernel.ms.rmi.port"));
		setPrismRMIAddress(prop.getProperty("kernel.ms.rmi.address"));
		setPrismRMIUser(prop.getProperty("kernel.ms.rmi.user"));
		setPrismRMIPass(prop.getProperty("kernel.ms.rmi.pwd"));
	}

	private String decryptPassword(String encrpyedPassword) {

		String r = null;
		try {
			r = AESUtils.decrypt_base64Decode(encrpyedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	private static Boolean encrypedConfigCheck() {
		Boolean result = null;
		try {
			AESUtils.decrypt_base64Decode("XW9t2agGd1shZPDkz3LMKQ==");
			result = true;
		} catch (FileNotFoundException e) {
			// If we get a FileNotFound we assume there is no AES key, and thus no encrpytion
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getPrismRMIPort() {
		return this.prismRMIPort;
	}

	public String getPrismRMIUser() {
		return this.prismRMIUser;
	}

	public String getPrismRMIAddress() {
		return this.prismRMIAddr;
	}

	public String getPrismRMIPass() {
		String r = null;
		if(encrypedConfigCheck()){
			r = decryptPassword(this.prismRMIPass);
		}else{
			r = this.prismRMIPass;
		}
		return r;
	}

	private void setPrismRMIPort(String port) {
		this.prismRMIPort = port;
	}

	private void setPrismRMIPass(String pass) {
		this.prismRMIPass = pass;
	}

	private void setPrismRMIUser(String user) {
		this.prismRMIUser = user;
	}

	private void setPrismRMIAddress(String addr) {
		this.prismRMIAddr = addr;
	}

//  private static final String aesKeyFilePropertyKey = "com.voxeolabs.aes.key";
//	public static void main(String[] args) {
//		System.setProperty(aesKeyFilePropertyKey, "/Users/jdyer/aeskey");
//		PrismProperties p = new PrismProperties("/Users/jdyer/opt/voxeo/prism/");
//		//System.out.println(configEncrypted());
//		System.out.println(p.getPrismRMIPass());
//		//System.out.println(p.decryptPassword(p.getPrismRMIPass()));
//
//	}
}
