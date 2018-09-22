package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			Tests ts= new Tests();
			ts.setNum(i);
			fixedThreadPool.execute(ts);
		}
	}
}