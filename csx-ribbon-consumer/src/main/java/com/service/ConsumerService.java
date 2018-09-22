package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "userFallback")
	public User getUserById() {
		User user = restTemplate.getForEntity("http://user-server/getUserById?userId={1}", User.class,"111").getBody();
		return user;
	}
	
	public User userFallback(){
		return null;
	}

}
