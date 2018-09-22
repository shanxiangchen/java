package com.app.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.entity.WebDav;
import com.app.entity.WebDavInfo;
import com.app.mapper.WebDavMapper;
import com.app.service.WebDavService;
import com.app.util.DESUtil;

 
public class WebDavServiceImpl implements WebDavService{
	
	private WebDavMapper  webDavMapper;
    
	public WebDavMapper getWebDavMapper() {
		return webDavMapper;
	}

	public void setWebDavMapper(WebDavMapper webDavMapper) {
		this.webDavMapper = webDavMapper;
	}



	@Override
	public List<WebDav> selectWebDavList() {
		
		List<WebDav> list=webDavMapper.selectWebDavList();
		return list;
		
	}

	@Override
	public int insertWebDav(WebDav webDav) {
		 
		return webDavMapper.insertWebDav(webDav);
	}

	@Override
	public int selectWebDavCount(String webDavId) {
		 
		return webDavMapper.selectWebDavCount(webDavId);
	}

	@Override
	public void deleteWebDav(String[] strs) {
		 
		webDavMapper.deleteWebDav(strs);
	}
	@Override
	public void updateWebDav(WebDav webDav) {
		webDavMapper.updateWebDav(webDav);
	}

	 

	@Override
	public WebDav selectWebDavById(String webDavId) {
		 
		return webDavMapper.selectWebDavById(webDavId);
	}

	@Override
	public int selectWebDavNumCount(String webDavNum) {
		 
		return webDavMapper.selectWebDavNumCount(webDavNum);
	}

	@Override
	public String getWebDavPath() {
		List<WebDav> list=webDavMapper.selectWebDavIsOk();
		if(list==null||list.size()==0){
			return null;
		}
		String path=new StringBuffer("://").append(list.get(0).getWebDavHostName()).
				append(":").append(list.get(0).getWebDavPort()).toString();
		
		return path; 
	}
	@Override
	public void updateWebDavIsOk(String webDavId) {
		webDavMapper.updateWebDavIsOk(webDavId);
	}

	@Override
	public void updateWebDavIsNo(String webDavId) {
		webDavMapper.updateWebDavIsNo(webDavId);
	}

	@Override
	public Map<String,String> getWebDavInfo() {
		String hostPath=""; 
		String standPath="";
		List<WebDav> webDavList = webDavMapper.selectWebDavList();
		Map<String,String> webDavMap=new HashMap<String,String>(); 
	    if(webDavList!=null&&webDavList.size()!=0){
	    	for (WebDav webDav : webDavList) {
				if ("1".equals(webDav.getWebDavNum())) {
					WebDavInfo.hostName = webDav.getWebDavHostName();
					WebDavInfo.hostPort = webDav.getWebDavPort();
					WebDavInfo.hostUserName = webDav.getWebDavUserName();
					WebDavInfo.hostPassword = DESUtil.getDesString(webDav.getWebDavPassword());
					WebDavInfo.hostUrl = webDav.getWebDavUrl();
					WebDavInfo.hostLookUrl=webDav.getWebDavLookUrl();
				} else if ("2".equals(webDav.getWebDavNum())) {
					WebDavInfo.standbyName = webDav.getWebDavHostName();
					WebDavInfo.standbyPort = webDav.getWebDavPort();
					WebDavInfo.standbyUserName = webDav.getWebDavUserName();
					WebDavInfo.standbyPassword = DESUtil.getDesString(webDav.getWebDavPassword());
					WebDavInfo.standbyUrl = webDav.getWebDavUrl();
					WebDavInfo.standbyLookUrl=webDav.getWebDavLookUrl();
				} 
				
				
			}
	    }
	    
	    if(WebDavInfo.hostUrl!=null){
			if(!WebDavInfo.hostUrl.endsWith("/"))
				WebDavInfo.hostUrl+="/";
			 
	        if(!WebDavInfo.hostUrl.startsWith("/"))
	        	WebDavInfo.hostUrl="/"+WebDavInfo.hostUrl;
		}
		
		if(WebDavInfo.hostLookUrl!=null){
			if(!WebDavInfo.hostLookUrl.endsWith("/"))
				WebDavInfo.hostLookUrl+="/";
	        
	        if(!WebDavInfo.hostLookUrl.startsWith("/"))
	        	WebDavInfo.hostLookUrl="/"+WebDavInfo.hostLookUrl;
		}
		
		if(WebDavInfo.standbyUrl!=null){
			if(!WebDavInfo.standbyUrl.endsWith("/"))
				WebDavInfo.standbyUrl+="/";
	        
	        if(!WebDavInfo.standbyUrl.startsWith("/"))
	        	WebDavInfo.standbyUrl="/"+WebDavInfo.standbyUrl;
		}
        
		if(WebDavInfo.standbyLookUrl!=null){
			if(!WebDavInfo.standbyLookUrl.endsWith("/"))
				WebDavInfo.standbyLookUrl+="/";
	        if(!WebDavInfo.standbyLookUrl.startsWith("/"))
	        	WebDavInfo.standbyLookUrl="/"+WebDavInfo.standbyLookUrl;
		}
	    
		hostPath =new StringBuffer("://").append(WebDavInfo.hostName).append(":").
	    		append(WebDavInfo.hostPort).append(WebDavInfo.hostUrl).toString();
	    standPath =new StringBuffer("://").append(WebDavInfo.standbyName).append(":").
	    		append(WebDavInfo.standbyPort).append(WebDavInfo.standbyUrl).toString();
	    webDavMap.put("A", hostPath);
	    webDavMap.put("B", standPath);
		return webDavMap;
		 
	}
	
	@Override
	public void insertBankControlLog(Map<String, String> map) {
		
		  webDavMapper.insertBankControlLog(map);
	}

	@Override
	public int selectControlLog(Map<String, String> map) {
		 
		return webDavMapper.selectControlLog(map);
	}

	@Override
	public void updateBankControlLog(Map<String, String> map) {
		webDavMapper.updateBankControlLog(map);
	}

	
	
	
	
	  

	 
	 

	 
}
