package com.hxm.chat.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hxm.chat.security.bean.ChatUser;

@Mapper
public interface ChatUserMapper {

    int insert(ChatUser record);
    
    ChatUser selectByUserName(String name);
    
    List<String> selectAllUserName();
    
    int countByUserName(String name);
}