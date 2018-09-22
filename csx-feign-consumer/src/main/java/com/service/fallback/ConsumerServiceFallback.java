package com.service.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;
import com.service.ConsumerService;

@Component
public class ConsumerServiceFallback implements ConsumerService {

	@Override
	public User getUserById(@RequestParam("userId") String userId) {
		User user = new User();
		user.setUserId(111);
		user.setNameCn("未知");
		return user;
	}

	@Override
	public String updateUser(@RequestBody User user) {
		return "error";
	}

}
