package com.thread;

public class Tests implements Runnable  {
	
	public int num;
	
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println(num+":我运行了！");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
