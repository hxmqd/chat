package com.hxm.chat.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hxm.chat.security.bean.ChatUser;
import com.hxm.chat.security.mapper.ChatUserMapper;

@Component
public class ChatUserDetailsService implements UserDetailsService {

	@Autowired
	ChatUserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ChatUser user = userMapper.selectByUserName(username);
		if (user == null) {
            throw new UsernameNotFoundException(String.format("'%s'用户不存在！", username));
        } else {
            return new User(user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
        }
	}

}
