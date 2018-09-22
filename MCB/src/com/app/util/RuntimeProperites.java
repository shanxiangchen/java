package com.app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class RuntimeProperites extends PropertyPlaceholderConfigurer{
	
private static Map<String,String> CIBPROPMAP;
	
	@Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        CIBPROPMAP = new HashMap<String, String>();
        for (Object key : props.keySet()){
            String keyStr = key.toString();
            String value = String.valueOf(props.get(keyStr));
            CIBPROPMAP.put(keyStr,value);
        }
    }
	
	public static String getCIBPROPMAP(String name) {
        return CIBPROPMAP.get(name);
    }

    public static Map<String, String> getCIBPROPMAP() {
        return CIBPROPMAP;
    }

}
