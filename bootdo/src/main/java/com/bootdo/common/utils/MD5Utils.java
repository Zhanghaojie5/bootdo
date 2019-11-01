package com.bootdo.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
	private static final String SALT = "shjemn_kjdfsd231";

	private static final String ALGORITH_NAME_MD5 = "md5";
	private static final String ALGORITH_NAME_SHA256 = "sha-256";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME_MD5, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS)
				.toHex();
		newPassword = new SimpleHash(ALGORITH_NAME_SHA256, newPassword, ByteSource.Util.bytes(SALT), HASH_ITERATIONS)
				.toHex().substring(0, 32);
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME_MD5, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		newPassword = new SimpleHash(ALGORITH_NAME_SHA256, newPassword, ByteSource.Util.bytes(SALT), HASH_ITERATIONS)
				.toHex().substring(0, 32);

		return newPassword;
	}

	public static void main(String[] args) {

		System.out.println(MD5Utils.encrypt("admin", "admin"));	
	}

}
