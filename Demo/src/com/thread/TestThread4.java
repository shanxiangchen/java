package com.thread;

public class TestThread4 implements Runnable {

    //���߳��д�ֵ
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("�ҵ�������:" +name);
		
	}
	
	public static void main(String[] args) {
		TestThread4 ts = new TestThread4();
		ts.setName("Tom");
		new Thread(ts).start();

	}

}
