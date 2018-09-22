package com.timer;

import java.util.Timer;

public class TestMain {

	public static void main(String[] args) {
		Timer timer = new Timer();
		TestTimerTask timerTsk = new TestTimerTask();
		timer.schedule(timerTsk, 0, 5000L);
		System.out.println("调度任务创建完毕！");
	}

}
