package com.list;

import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		list.add("abc");
		list.add("abc");
		list.add(1, "ddd");
		System.out.println(list.size());
		System.out.println(list.toString());
	}

}
