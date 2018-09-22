package com.singleton;

/**
 * 线程安全的懒汉式，使用时加载
 * 
 * @author Administrator
 *
 */
public class SingletonDemo2 {

	private static SingletonDemo2 instance;

	private SingletonDemo2() {

	}

	public static synchronized SingletonDemo2 getInstance() {
		if (instance == null) {
			instance = new SingletonDemo2();
		}
		return instance;
	}

}
