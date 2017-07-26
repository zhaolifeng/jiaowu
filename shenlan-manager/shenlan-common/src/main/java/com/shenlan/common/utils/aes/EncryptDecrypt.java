package com.shenlan.common.utils.aes;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class EncryptDecrypt {

	/**
	 * 加密
	 * @author YANG
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		String rtnStr = "";
		byte[] rltByte = AES.encrypt_aes(content, password);
		if (rltByte == null) {
			throw new RuntimeException("encrpte exception!check console log to get more information!");
		} else {
			String hexStr = parseByte2HexStr(rltByte);
			rtnStr = new String(Base64.encodeBase64(hexStr.getBytes()));
		}
		return rtnStr;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		String rtnStr = "";

		byte[] contentByte = Base64.decodeBase64(content.getBytes());
		byte[] aesByte = parseHexStr2Byte(new String(contentByte));

		byte[] rltByte = AES.decrypt_aes(aesByte, password);
		if (rltByte == null) {
			throw new RuntimeException("decrpte exception!check console log to get more information!");
		} else {
			try {
				rtnStr = new String(rltByte, "UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return rtnStr;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
