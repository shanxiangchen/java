package com.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mq_exg_csx")
public class Receiver {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("Receiver : " + msg);
	}

}
