package com.thread;

public class TestThread1 implements Runnable {

	//�ֲ��������̰߳�ȫ
	@Override
	public void run() {
		int i = 2;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*1:" + i);
		i = 10;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*2:" + i*2);
		i = 100;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*3:" + i*3);
	}
	
	public static void main(String[] args) {
		TestThread1 ts = new TestThread1();
		for(int i=1;i<3000;i++){
			new Thread(ts, "�߳�" + i).start();  
		}
	}
}
