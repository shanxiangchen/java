package com.service;

import org.springframework.stereotype.Service;

import com.entity.User;

@Service
public class UserService {

	public User getUserById(String userId) {
		User user = new User();
		user.setUserId(111);
		user.setUserName("zhangsanfeng");
		user.setPassword("123456");
		user.setNameCn("张三丰");
		user.setSex("M");
		user.setPhone("13122998897");
		user.setEmail("123456@qq.com");
		return user;
	}
	
	public User getUserByName(String userName) {
		User user = new User();
		user.setUserId(111);
		user.setUserName("zhangsanfeng");
		user.setPassword("123456");
		user.setNameCn("张三丰");
		user.setSex("M");
		user.setPhone("13122998897");
		user.setEmail("123456@qq.com");
		return user;
	}

}
