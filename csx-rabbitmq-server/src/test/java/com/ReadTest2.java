package com;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class ReadTest2 {

	private final static String QUEUE_NAME = "mq_exg_nzr2";
	private final static String userName = "scott";
	private final static String password = "tiger";
	// private final static String virtualHost = "/bosc";
	private final static int portNumber = 5672;
	private final static String host = "127.0.0.1";

	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername(userName);
			factory.setPassword(password);
			// factory.setVirtualHost(virtualHost);
			factory.setHost(host);
			factory.setPort(portNumber);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			System.out.println(" Waiting for messages. To exit press CTRL+C");
			TestConsumer consumer = new TestConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);
		} catch (ShutdownSignalException e) {
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

}
