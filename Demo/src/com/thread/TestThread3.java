package com.thread;

public class TestThread3 implements Runnable {

	//全局非静态变量，线程不安全
	public int i;
	@Override
	public void run() {
		i = 2;
		System.out.println("[" + Thread.currentThread().getName() + "]的值*1:" + i);
		i = 10;
		System.out.println("[" + Thread.currentThread().getName() + "]的值*2:" + i*2);
		i = 100;
		System.out.println("[" + Thread.currentThread().getName() + "]的值*3:" + i*3);
	}
	
	public static void main(String[] args) {
		TestThread3 ts = new TestThread3();
		for(int i=1;i<3000;i++){
			new Thread(ts, "线程" + i).start();  
		}
	}
}
