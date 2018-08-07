package com.hxm.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hxm.chat.bean.Talk;

@Mapper
public interface TalkMapper {
    
	int deleteByDestUser(String destUser);
	
	int deleteById(String id);

    int insert(Talk record);

    List<Talk> selectByDestUser(String destUser);
    
    List<Talk> selectGroupTalk();


}