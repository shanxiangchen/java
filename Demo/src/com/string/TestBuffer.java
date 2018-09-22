package com.string;

public class TestBuffer {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 3000; i++) {
			sb.append("1");
		}
		while (true) {
			System.out.println(sb.length());
		}
	}

}
