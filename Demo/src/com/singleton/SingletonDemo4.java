package com.singleton;

/**
 * ˫����У�飬ʹ���Ǽ���
 * 
 * @author Administrator
 *
 */
public class SingletonDemo4 {

	private volatile static SingletonDemo4 instance;

	private SingletonDemo4() {
		System.out.println("Singleton has loaded");
	}

	public static SingletonDemo4 getInstance() {
		if (instance == null) {
			synchronized (SingletonDemo4.class) {
				if (instance == null) {
					instance = new SingletonDemo4();
				}
			}
		}
		return instance;
	}

}
