package com.hxm.chat.data;

import java.util.HashSet;
import java.util.Set;

import net.bytebuddy.asm.Advice.This;

public class ChatUserData {
	
	private static Set<String> users = new HashSet<>();
	
	private static Set<String> onlineUsers = new HashSet<>();
	
	private static  Set<String> offlineUsers = new HashSet<>();

	public static void addUser(String name){
		users.add(name);
	}
	
	public static void setUser(Set<String> arg){
		users = arg;
	}
	
	public static void addOnLineUser(String name){
		onlineUsers.add(name);
	}
	
	public static void removeOnLineUser(String name){
		onlineUsers.remove(name);
	}
	
	public static Set<String> getUsers() {
		return users;
	}

	public static Set<String> getOnlineUsers() {
		return onlineUsers;
	}

	public static Set<String> getOfflineUsers() {
		offlineUsers.clear();
		for(String user : users){
			if(!onlineUsers.contains(user)){
				offlineUsers.add(user);
			}
		}
		return offlineUsers;
	}
	
	

}
