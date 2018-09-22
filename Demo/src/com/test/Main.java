package com.test;

public class Main {

	public static void main(String[] args) {
		String b = "abc";
		String bb = "abc";
		System.out.println(b == bb);
		System.out.println(b.equals(bb));
		String c = new String("abc");
		String cc = new String("abc");
		System.out.println(c == cc);
		System.out.println(c.equals(cc));
		byte d = (byte) 158;
		Byte dd = (byte) 158;
		System.out.println(d + "  " + dd);
		char e = 'a';
		//String f = 'a';
	}

}
