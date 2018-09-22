package com.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

@RestController
public class UserController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private UserService userService;

	@RequestMapping("/getUserById")
	public User getUserById(@RequestParam String userId) {
		ServiceInstance instance = client.getLocalServiceInstance();
		User user = userService.getUserById(userId);
		logger.info("/getUserById,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
		return user;
	}
	
	@RequestMapping("/getUserByName")
	public User getUserByName(@RequestParam String userName) {
		ServiceInstance instance = client.getLocalServiceInstance();
		User user = userService.getUserByName(userName);
		logger.info("/getUserByName,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
		return user;
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/saveUser,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
		return "Save User "+user.getUserName()+" Success";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(@RequestBody User user) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/updateUser,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
		return "Update User "+user.getUserName()+" Success";
	}

	@RequestMapping("/test")
	public String println() {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/test,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
		return "Spring Boot Test";
	}

}
