package com.thread;

public class TestThread2 implements Runnable {

	//��̬ȫ�ֱ������̲߳���ȫ
	public static int i;
	@Override
	public void run() {
		i = 2;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*1:" + i);
		i = 10;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*2:" + i*2);
		i = 100;
		System.out.println("[" + Thread.currentThread().getName() + "]��ֵ*3:" + i*3);
	}
	
	public static void main(String[] args) {
		TestThread2 ts = new TestThread2();
		for(int i=1;i<3000;i++){
			new Thread(ts, "�߳�" + i).start();  
		}
	}
}
