package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.entity.WebDav;
 
public interface WebDavService {
	public List<WebDav> selectWebDavList();
	
	public int insertWebDav(WebDav webDav);
	public int selectWebDavCount(String webDavId);
	public int selectWebDavNumCount(String webDavNum);
	public void deleteWebDav(String[] strs);

	void updateWebDav(WebDav webDav);
	public String getWebDavPath();
	public WebDav selectWebDavById(String webDavId);
	public void updateWebDavIsOk(String webDavId);
	public void updateWebDavIsNo(String webDavId);
	public Map<String,String> getWebDavInfo();
	public void insertBankControlLog(Map<String,String> map);
	public int selectControlLog(Map<String,String> map);
	public void updateBankControlLog(Map<String,String> map);
}
