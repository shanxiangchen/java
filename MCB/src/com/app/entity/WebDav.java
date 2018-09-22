package com.app.entity;
/**
 * webDav实体类 
 * @author zhaolei 
 * @date 2016-3-7 下午3:04:18
 */
public class WebDav {
	
	private String webDavId;//主键
	private String webDavHostName;//webDav服务器IP
	private Integer webDavPort;//服务器端口
	private String webDavUserName;//服务器用户名
	private String webDavPassword;//服务器密码
	private String webDavUrl;//服务器上传路径
	private String webDavNum;//服务器标识 1：主机 2：备机
	private String webDavLookUrl;//服务器访问路径
	private String webDavIsOk;//服务器是否宕机
	
	
	
	public String getWebDavId() {
		return webDavId;
	}
	public void setWebDavId(String webDavId) {
		this.webDavId = webDavId;
	}
	public String getWebDavHostName() {
		return webDavHostName;
	}
	public void setWebDavHostName(String webDavHostName) {
		this.webDavHostName = webDavHostName;
	}
	 
	public String getWebDavUserName() {
		return webDavUserName;
	}
	public void setWebDavUserName(String webDavUserName) {
		this.webDavUserName = webDavUserName;
	}
	public String getWebDavPassword() {
		return webDavPassword;
	}
	public void setWebDavPassword(String webDavPassword) {
		this.webDavPassword = webDavPassword;
	}
	public String getWebDavUrl() {
		return webDavUrl;
	}
	public void setWebDavUrl(String webDavUrl) {
		this.webDavUrl = webDavUrl;
	}
	public String getWebDavNum() {
		return webDavNum;
	}
	public void setWebDavNum(String webDavNum) {
		this.webDavNum = webDavNum;
	}
	public Integer getWebDavPort() {
		return webDavPort;
	}
	public void setWebDavPort(Integer webDavPort) {
		this.webDavPort = webDavPort;
	}
	public String getWebDavLookUrl() {
		return webDavLookUrl;
	}
	public void setWebDavLookUrl(String webDavLookUrl) {
		this.webDavLookUrl = webDavLookUrl;
	}
	public String getWebDavIsOk() {
		return webDavIsOk;
	}
	public void setWebDavIsOk(String webDavIsOk) {
		this.webDavIsOk = webDavIsOk;
	}
	
	
	 
  
 
 
}
