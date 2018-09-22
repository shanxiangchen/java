package com.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;
import com.service.fallback.ConsumerServiceFallback;

@FeignClient(name = "user-server", fallback = ConsumerServiceFallback.class)
public interface ConsumerService {

	@RequestMapping("/getUserById")
	public User getUserById(@RequestParam("userId") String userId);

	@RequestMapping("/updateUser")
	public String updateUser(@RequestBody User user);

}
