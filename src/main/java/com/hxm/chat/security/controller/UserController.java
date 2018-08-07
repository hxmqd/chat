package com.hxm.chat.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxm.chat.data.ChatUserData;
import com.hxm.chat.security.bean.ChatUser;
import com.hxm.chat.security.bean.UserResponse;
import com.hxm.chat.security.mapper.ChatUserMapper;
import com.hxm.chat.util.CommonUtil;

@Controller
@RequestMapping("/user") 
public class UserController {

	@Autowired
	ChatUserMapper userMapper;
	
	@ResponseBody
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public UserResponse register(@RequestParam String username,
    		@RequestParam String password){
		ChatUser user = new ChatUser();
    	user.setId(CommonUtil.GetUUID());
    	user.setName(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(password);
    	user.setPassword(encodePassword);
        int count = userMapper.countByUserName(user.getName());
        UserResponse response = new UserResponse();
        response.setCode(400);
    	response.setMsg("用户创建失败！");
        if(count>0){
        	response.setMsg("该用户名已存在！");
        	return response;
        }
        int result = userMapper.insert(user);
        if(result==1){
        	ChatUserData.addUser(user.getName());
        	response.setCode(200);
        	response.setMsg("用户创建成功");
        }
        return response;
    }
	
	@RequestMapping(path = "/getCurrentUser")
	@ResponseBody
    public String getCurrentUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
		 username = ((UserDetails)principal).getUsername();
		} else {
		 username = principal.toString();
		}
		return username;
    }
    @RequestMapping(path = "/getOnlineUser")
    @ResponseBody
    public List<String> getOnlineUser(){
    	return new ArrayList<>(ChatUserData.getOnlineUsers());
    }
    
    @RequestMapping(path = "/getOfflineUser")
    @ResponseBody
    public List<String> getOfflineUser(){
    	return new ArrayList<>(ChatUserData.getOfflineUsers());
    }

}
