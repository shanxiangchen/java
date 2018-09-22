package com.timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TimerTask;

public class TestTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println();
		for (int i = 0; i < 2; i++) {
			try {
				Object object = Class.forName("com.timer.TestMethod").newInstance();
				long time = System.currentTimeMillis();
				if(time%2==0){
					Method method = object.getClass().getMethod("testMthod1");
					method.invoke(object);
				}else{
					Method method = object.getClass().getMethod("testMthod2");
					method.invoke(object);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}

}
