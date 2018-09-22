package com.file;

import java.io.File;

public class Main {
	
	public static String paths = "C:/Users/Administrator/Desktop/ssmjar";

	public static void main(String[] args) {
		String path = "C:/Users/Administrator/Desktop/lib";
		reNameFiles(path,".RELEASE");
	}
	
	public static void reNameFiles(String filePath,String str){
		File filein = new File(filePath);
		File[] filelist = filein.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			File file = filelist[i];
			if(file.isDirectory()){
				reNameFiles(file.getPath(),str);
			}else{
				String name = file.getName();
				String ftype = name.substring(name.lastIndexOf(".")+1,name.length());
				if ("jar".equalsIgnoreCase(ftype)) {
					System.out.println(name);
					File newfile = new File(filePath,name.replace(str, ""));
					//File newfile = new File(paths,name);
					file.renameTo(newfile);
				}else{
					System.out.println(name);
				}
			}
		}
	}

}
