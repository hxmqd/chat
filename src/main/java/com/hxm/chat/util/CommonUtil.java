package com.hxm.chat.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.util.StringUtils;


public class CommonUtil {
	
	public static String GetUUID(){
		String uuid = UUID.randomUUID().toString();
    	return StringUtils.replace(uuid, "-", "");
	}
	
	public static Set<String> GetUnauthorizedURL(){
		Set<String> urlSet = new HashSet<>();
		urlSet.add("/pages/login.html");
		urlSet.add("/pages/register.html");
    	return urlSet;
	}

}
