package com.thread;

public class TestThread5 implements Runnable {

	// 三个线程分别打印ABC
	// 启动三个线程，让输出结果为ABCABC...
	private String name;
	private Object prev;
	private Object self;

	private TestThread5(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		TestThread5 pa = new TestThread5("A", c, a);
		TestThread5 pb = new TestThread5("B", a, b);
		TestThread5 pc = new TestThread5("C", b, c);
		Thread tpa = new Thread(pa);
		Thread tpb = new Thread(pb);
		Thread tpc = new Thread(pc);
		tpa.start();
		tpb.start();
		tpc.start();
		try {
			Thread.sleep(500);
			System.out.println();
			if(!tpa.isInterrupted()){
				System.out.println("tpa:"+tpa.isInterrupted());
				tpa.interrupt();
			}
			if(!tpb.isInterrupted()){
				System.out.println("tpb:"+tpb.isInterrupted());
				tpb.interrupt();
			}
			if(!tpc.isInterrupted()){
				System.out.println("tpc:"+tpc.isInterrupted());
				tpc.interrupt();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {
			tpa.stop();
			tpb.stop();
			tpc.stop();
			System.out.println("线程结束！");
		}
	}
}
