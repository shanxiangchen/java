package com;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TestConsumer extends DefaultConsumer {

	public TestConsumer(Channel channel) {
		super(channel);
	}

	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
		String message = new String(body);
		System.out.println(" Received '" + message + "'");
	}

}
