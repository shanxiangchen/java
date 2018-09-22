package com.sort;

import java.util.Arrays;
import java.util.List;

public class TestSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nums = "3864219";
		String nums1 = Sort.BubbleSorts(nums);
		System.out.println(nums1.toCharArray());
		int[] num= {12,6,3,8,4};
		int[] num1 = Sort.BubbleSort(num);
		for(int i=0;i<num1.length;i++){
			System.out.print(num1[i]+",");
		}
	}

}
