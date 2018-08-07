package com.hxm.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hxm.chat.bean.Talk;
import com.hxm.chat.mapper.TalkMapper;

@RestController
@RequestMapping("/talk")
public class TalkController {
	
	@Autowired
	TalkMapper talkMapper;
	
	@RequestMapping(path = "/offLineTalk", method = RequestMethod.GET)
	public List<Talk> getOfflineTalk(String user){
		List<Talk> talks = talkMapper.selectByDestUser(user);
		for(Talk talk : talks){
			talkMapper.deleteById(talk.getId());
		}
		return talks;
	}
	@RequestMapping(path = "/offLineGroupTalk", method = RequestMethod.GET)
	public List<Talk> getOffLineGroupTalk(){
		List<Talk> talks = talkMapper.selectGroupTalk();
		return talks;
	}

}
