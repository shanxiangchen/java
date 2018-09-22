package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.Sender;

@RestController
public class RabbitController {
	
	@Autowired
	private Sender sender;

	@RequestMapping("/send")
	public String getUserById() {
		int i = 0;
		while (true) {
			i++;
			String message = "hello world " + i;
			sender.send(message);
			if(i==1000){
				break;
			}
		}
		return "/send success";
	}

	@RequestMapping("/test")
	public String test() {
		return "/test success";
	}

}
