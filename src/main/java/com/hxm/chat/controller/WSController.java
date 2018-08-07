package com.hxm.chat.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.hxm.chat.bean.Talk;
import com.hxm.chat.data.ChatUserData;
import com.hxm.chat.mapper.TalkMapper;
import com.hxm.chat.util.CommonUtil;

@Controller
public class WSController {
	@Autowired
	SimpMessagingTemplate messagingTemplate;

	@Autowired
	TalkMapper talkMapper;

	  // 点对点聊天
	  @MessageMapping("/personchat")
	  public void chat(Principal principal, String message){
	    // 参数说明  principal 当前登录的用户， message 客户端发送过来的内容（应该至少包含发送对象toUser和消息内容content）
		String srcUser = principal.getName();
		String[] array = message.split("\\|");
		String destUser = array[0];
		String content = srcUser+"|"+array[1];
		// 如果当前用户不在线，将收到的消息存入数据库
		if (!ChatUserData.getOnlineUsers().contains(destUser)) {
			talkMapper.insert(new Talk(CommonUtil.GetUUID(),srcUser, destUser, array[1], 1));
			return;
		}
		messagingTemplate.convertAndSendToUser(destUser, "/chat", content);
	  }


	  @MessageMapping("/groupchat")
	  public void topic(Principal principal, String message) {  
	    // 参数说明 principal 当前登录的用户， message 客户端发送过来的内容
	    // principal.getName() 可获得当前用户的username  
		String srcUser = principal.getName();
		if (ChatUserData.getOfflineUsers().size()!=0) {
			talkMapper.insert(new Talk(CommonUtil.GetUUID(),srcUser, "/", message, 0));
		}
	    // 发送消息给订阅 "/topic/notice" 且在线的用户
		messagingTemplate.convertAndSend("/topic", srcUser +'|'+ message); 
	  }

}
