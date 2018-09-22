package com.singleton;

/**
 * 懒汉式，使用时加载
 * 
 * @author Administrator
 *
 */
public class SingletonDemo1 {

	private static SingletonDemo1 instance;

	private SingletonDemo1() {

	}

	public static SingletonDemo1 getInstance() {
		if (instance == null) {
			instance = new SingletonDemo1();
		}
		return instance;
	}

}
