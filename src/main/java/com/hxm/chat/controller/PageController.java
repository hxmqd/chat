package com.hxm.chat.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxm.chat.security.bean.ChatUser;
import com.hxm.chat.security.mapper.ChatUserMapper;


@Controller
public class PageController {

	@Autowired ChatUserMapper mapper;
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model, HttpSession session){
		return "/login.html";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String registerPage(){
	 	return "/register.html";
	}
	
	@RequestMapping(path = "/",  method = RequestMethod.GET)
	public String chatPage(){
		
		return "/chat.html";
	}
	
	@RequestMapping(path = "/error",  method = RequestMethod.GET)
	public String errorPage(){
		return "/error.html";
	}
}
