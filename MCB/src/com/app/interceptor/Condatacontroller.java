package com.app.interceptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


/**
 * 日期类型换换
 * @author Administrator
 *
 */
public class Condatacontroller  implements Converter<String, Date>{

	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
