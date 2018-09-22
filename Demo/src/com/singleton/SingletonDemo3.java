package com.singleton;

/**
 * ¶öººÊ½£¬ÄÚ´æ³£×¤
 * 
 * @author Administrator
 *
 */
public class SingletonDemo3 {

	private static SingletonDemo3 instance = new SingletonDemo3();

	private SingletonDemo3() {

	}

	public static SingletonDemo3 getInstance() {
		return instance;
	}
}
