package com.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String message) {
		System.out.println("Sender : " + message);
		this.rabbitTemplate.convertAndSend("mq_exg_csx", message);
	}

}