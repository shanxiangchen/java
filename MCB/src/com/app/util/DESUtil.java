package com.app.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 加密与解密
 * @author zhaolei 
 * @date 2016-3-3 下午6:57:32
 */
public class DESUtil {
	 
	public static final String KEY_STRING = "faraswayfrom";// 生成密钥的字符串
	public static Key key;

	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	public static void getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public static String getEncString(String strMing) {
		DESUtil.getKey(KEY_STRING);// 生成密匙
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDesString(String strMi) {
		DESUtil.getKey(KEY_STRING);// 生成密匙
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = getDesCode(byteMi);
			if(byteMing==null){
				return strMi;
			}
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
			
		} catch (Exception e) {
		   return null;
		} finally {
			cipher = null;
		}
		return byteFina;

	}
	
	 

	public static void main(String[] args) {  
		String bus = new String("password");
		String strEnc = DESUtil.getEncString(bus);// 加密字符串,返回String的密文
    	System.out.println("密文：  "+strEnc);
		String strDes = DESUtil.getDesString(strEnc);// 把String 类型的密文解密
		System.out.println("明文：   "+strDes);
		System.out.println("原文解密："+DESUtil.getDesString(bus));
     /*  if(args.length>0){
        	String strEnc = DESUtil.getEncString(args[0]);// 加密字符串,返回String的密文
        	System.out.println("密文：  "+strEnc);
    		String strDes = DESUtil.getDesString(strEnc);// 把String 类型的密文解密
    		System.out.println("明文：   "+strDes);
        }else{
        	System.out.println("请输入明文参数。。。");
        }*/
		
	}
	
	
}
