package com.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
/**
 * 读取配置文件（属性文件）的工具类
 * @author 赵雷
 *
 */
public class ConfigManager {
	private static ConfigManager configManager;
	private static Properties properties;
	
	private ConfigManager(){
		String configFile="jdbc.properties";
		properties=new Properties();
		InputStream in=ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
	
	public static ConfigManager getInstance(){
		if(configManager==null){
			configManager=new ConfigManager();
		}
		return configManager;
	}
	
	public  String getString(String key){
		return properties.getProperty(key);
		
	}
	 
	public static String getProperties(String sign) {
    	//app为属性文件名，放在包com.cib下，如果是放在src下，直接用app即可    
    	ResourceBundle resource = ResourceBundle.getBundle("jdbc");
    	return resource.getString(sign); 
	}
}

