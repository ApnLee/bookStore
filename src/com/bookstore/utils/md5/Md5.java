package com.bookstore.utils.md5;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * MD5加密
 * @author DivineLee
 *
 */
public class Md5 {

	/*
	 * 加盐版的md5
	 * 摘要中+了盐：防止抓取摘要后反得出密码（原数据）
	 */
	public static final String salt="com.bookstore";
	public static String saltMd5(String str){
		try {			
			byte[] data=str.getBytes("utf-8");
			//生成一个MD5加密计算摘要
			MessageDigest md=MessageDigest.getInstance("md5");
			//计算md5函数
			md.update(data);
			md.update(salt.getBytes("utf-8"));
			//digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			byte[] md5=md.digest();
			String code=Base64.encodeBase64String(md5);
			return code;
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 不加盐版的md5
	 */
	public static String md5(String str) {
		try {
			byte[] data=str.getBytes("utf-8");
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(data);
			byte[] md5=md.digest();
			String code=Base64.encodeBase64String(md5);
			return code;
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
