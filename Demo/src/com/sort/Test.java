package com.sort;

import java.util.Scanner;


public class Test {

	public static void main(String[] args) {
		while(true){
			System.out.print("请输入数字:");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			System.out.println();
			if(x>0 && x<=9){
				int y = 2*x-1;
				int count = 0;
				String [] ints = new String[x];
				for(int i=0;i<y;i++){
					if(i>x-1){
						System.out.print(ints[(x-1)-(i-(x-1))]);
					}else{
						String str = "";
						int count2 = 0;
						int[] ints2 = new int[x];
						for(int ii=0;ii<y;ii++){
							int yy = x-count2;
							if(ii>x-1){
								yy = ints2[(x-1)-(ii-(x-1))];
							}
							if(ii<x){
								ints2[ii]=yy;
							}
							System.out.print(yy);
							str +=yy;
							if(count2<count){
								count2++;
							}
						}
						if(i<x){
							ints[i]=str;
						}
					}
					System.out.print("  ");
					count ++;
				}
			}else{
				System.out.println("请输入1位的数字！");
			}
			System.out.println();
			System.out.println();
		}
	}

}
