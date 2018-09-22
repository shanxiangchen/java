package com.memery;

import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int nums = 1000*1024*1024;
		int[] lists = new int[nums];
		for(int i=0;i<lists.length;i++){
			lists[i]=i;
		}
		Scanner cs = new Scanner(System.in);
		System.out.println("ÇëÊäÈë£º");
		System.out.println(cs.next());
	}

}
