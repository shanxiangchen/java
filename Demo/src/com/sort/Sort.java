package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(23);
		list.add(22);
		list.add(45);
		list.add(8);
		Collections.sort(list);
		System.out.print(list);

	}
	
	public static int[] BubbleSort(int[] str1) {
		try {
			int length = str1.length;
			int t = 0;
			for (int i = 0; i < length - 1; i++) {
				for (int j=i+1; j < length; j++) {
					if(str1[i]>str1[j]){
						t=str1[i];
						str1[i]=str1[j];
						str1[j]=t;
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str1;
	}

	public static String BubbleSorts(String str1) {
		String str2 ;
		try {
			char[] list = str1.toCharArray();
			int length = str1.length();
			char t ;
			if (length > 1) {
				for (int i = 0; i < length-1; i++) {
					for (int j = i+1; j < length; j++) {
						if (list[i] > list[j]) {
							t = list[i];
							list[i] = list[j];
							list[j] = t;
						}
					}
				}
				str2 = new String(list);
			} else
				str2 = str1;
		} catch (Exception ex) {
			str2 = null;
		}
		return str2;
	}
}
