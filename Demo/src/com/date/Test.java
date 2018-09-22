package com.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public String toStr(Date date) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}
	
	public Date toDate(String str) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date;
	}
	
	


}
