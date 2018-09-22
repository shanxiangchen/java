package com;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class WriteTest {

	private final static String QUEUE_NAME = "mq_exg_nzr";
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
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);

			System.out.println(" Send messages. To exit press CTRL+C");
			int i = 0;
			while (true) {
				i++;
				String message = "hello world " + i;
				channel.basicPublish("amq.topic", QUEUE_NAME, null, message.getBytes());
				System.out.println(" Send '" + message + "'");
				synchronized (Thread.currentThread()) {
					Thread.currentThread().wait(200);
				}
			}
		} catch (ShutdownSignalException e) {
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
