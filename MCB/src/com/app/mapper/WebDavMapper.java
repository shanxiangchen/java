package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.WebDav;
public interface WebDavMapper {
	public List<WebDav> selectWebDavList();
	public int insertWebDav(WebDav webDav);
	public int selectWebDavCount(String webDavId);
	public int selectWebDavNumCount(String webDavNum);
	public void deleteWebDav(String[] strs);
	
	public void updateWebDav(WebDav webDav);
	
	public WebDav selectWebDavById(String webDavId);
	public List<WebDav> selectWebDavIsOk();
	public void updateWebDavIsOk(String webDavId);
	public void updateWebDavIsNo(String webDavId);
	public void insertBankControlLog(Map<String,String> map);
	public int selectControlLog(Map<String,String> map);
	public void updateBankControlLog(Map<String,String> map);
}
