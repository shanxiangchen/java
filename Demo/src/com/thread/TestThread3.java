package com.thread;

public class TestThread3 implements Runnable {

	//ȫ�ַǾ�̬�������̲߳���ȫ
	public int i;
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
		TestThread3 ts = new TestThread3();
		for(int i=1;i<3000;i++){
			new Thread(ts, "�߳�" + i).start();  
		}
	}
}
