package com.springboot.core.common.contans;

public class RedisKeys {
	
	/**
	 * 分隔符
	 */
	private static final String SEPARATER = "_";

	/**
	 * 用户信息
	 */
	private static final String USER_CACHE = "USER_CACHE";
	
	public static String getUserCache(String username){
		return USER_CACHE + SEPARATER + username;
	}
}
