package com.memery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int nums = 2*1024*1024;
		String[] lists = new String[nums];
		for(int i=0;i<lists.length;i++){
			DecimalFormat df = new DecimalFormat("0000000000");
			lists[i]=df.format(i);
		}
		String[] list2 = new String[nums];
		for(int i=0;i<list2.length;i++){
			DecimalFormat df = new DecimalFormat("0000000000");
			list2[i]=df.format(i)+"@!@完爆了@!@10100@!@61240119900322497x@!@+86-13122998897@!@01";
		}
		StringBuffer bf = new StringBuffer();
		for(int i=0;i<lists.length;i++){
			DecimalFormat df = new DecimalFormat("0000000000");
			bf.append(df.format(i)+"@!@完爆了@!@10100@!@61240119900322497x@!@+86-13122998897@!@01"+"\r\n");
		}
		double s = System.currentTimeMillis();
//		try {
//			File file = new File("F:/Soga.txt");
//			FileOutputStream fos = new FileOutputStream(file);
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
//			bw.write(bf.toString());
//			bw.close();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println(System.currentTimeMillis()-s);
		Scanner cs = new Scanner(System.in);
		System.out.println("请输入：");
		System.out.println(cs.next());
	}

}
