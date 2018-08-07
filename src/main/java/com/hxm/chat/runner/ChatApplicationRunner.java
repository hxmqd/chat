package com.hxm.chat.runner;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.hxm.chat.config.WebSocketConfig;
import com.hxm.chat.data.ChatUserData;
import com.hxm.chat.security.mapper.ChatUserMapper;

@Component
public class ChatApplicationRunner implements ApplicationRunner {

	@Autowired
	ChatUserMapper userMapper;
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		ChatUserData.setUser(new HashSet<>(userMapper.selectAllUserName()));
		WebSocketConfig.messagingTemplate = messagingTemplate;
	}

}
