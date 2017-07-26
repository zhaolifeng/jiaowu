package com.shenlan.common.utils.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
	
	/**
	 * 加密
	 * @author YANG
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return
	 */
	public static byte[] encrypt_aes(String content, String password) {
		try {
			byte[] byteContent = content.getBytes("UTF-8");
			
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);// 创建密码器
			
			SecretKeySpec key = new SecretKeySpec(getSecurityKeyByte(password), "AES");
			
			//使用加密模式初始化 密钥  
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV(password)));
			//按单部分操作加密或解密数据，或者结束一个多部分操作。
			byte[] result = cipher.doFinal(byteContent); 
			
			return result; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;   
	}
	
	/**
	 * 解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */
	public static byte[] decrypt_aes(byte[] content, String password) {

		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);  
			
			SecretKeySpec key = new SecretKeySpec(getSecurityKeyByte(password), "AES");
			
			//使用加密模式初始化 密钥  
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV(password)));
			
			//使用解密模式初始化 密钥  
			byte[] decrypt = cipher.doFinal(content); 
			return decrypt;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;   
	}
	
	/**
	 * 获取秘钥字节数字
	 * @param password
	 * @return
	 */
	private static byte[] getSecurityKeyByte(String password){
		byte[] rtnByte = new byte[16];
		if(password!=null&&password.length()>0&&password.length()<16){
			StringBuffer sb = new StringBuffer(password);
			for(int i=0;i<19;i++){
				sb.append(password);
			}
		}else if(password==null||password.length()<1){
			throw new RuntimeException("the password is empty,please input a password!!");
		}
		
		byte[] parm = password.getBytes();
		int n = parm.length/16;
		for(int i=0;i<16;i++){
			rtnByte[i] = parm[i*n];
		}
		return rtnByte;
	}
	
	/**
	 * 获取向量
	 * @param password
	 */
	private static byte[] getIV(String password){
		byte[] rtnByte = new byte[16];
		if(password!=null&&password.length()>0&&password.length()<16){
			StringBuffer sb = new StringBuffer(password);
			for(int i=0;i<31;i++){
				sb.append(password);
			}
		}else if(password==null||password.length()<1){
			throw new RuntimeException("the password is empty,please input a password!!");
		}
		
		byte[] parm = password.getBytes();
		int n = parm.length/16;
		for(int i=0;i<16;i++){
			rtnByte[i] = parm[i*n];
		}
		return rtnByte;
	}

}