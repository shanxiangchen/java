package com.date;

import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		Test ts = new Test();
		String str = ts.toStr(new Date());
		System.out.println(str);
		System.out.println(ts.toDate(str));
	}

}
