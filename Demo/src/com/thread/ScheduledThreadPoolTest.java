package com.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("��ʱ3���ִ��!");
			}
		}, 3, TimeUnit.SECONDS);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("��ʱ1���ִ�У�ÿ��3��ִ��һ��!");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}
}