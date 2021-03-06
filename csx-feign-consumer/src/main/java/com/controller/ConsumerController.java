package com.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.ConsumerService;

@RestController
public class ConsumerController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private ConsumerService consumerService;

	@RequestMapping("/getUserById")
	public User getUserById() {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/getUserById,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
		User user = consumerService.getUserById("111");
		logger.info("/getUserById,getUser:"+user.getUserName());
		return user;
	}
	
	@RequestMapping("/updateUser")
	public String updateUser() {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/updateUser,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
		User user = consumerService.getUserById("111");
		logger.info("/updateUser,getUser:"+user.getUserName());
		String str = consumerService.updateUser(user);
		return str;
	}
	
	
	@RequestMapping("/test")
	public String test() {
		int i = 0;
		long start = System.currentTimeMillis();
		while (true) {
			long end = System.currentTimeMillis();
			if (end - start >= 1000) {
				break;
			}
			User user = consumerService.getUserById("111");
			logger.info("获取返回:" + user.getUserName());
			i++;
		}
		logger.info("每秒操作数：" + i);
		return "/test,每秒操作数：" + i;
	}

}
