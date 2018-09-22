package com.app.entity;
/**
 * 快递地址实体类
 * create date 2015/8/24
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class Express {
	private Integer expressServiceNameId;
	private String  expressServiceName;
	private String  expressServiceAddress;
	private String  isUse;
	private Page page; 
	public Express(){}

 
	public Integer getExpressServiceNameId() {
		return expressServiceNameId;
	}
	public void setExpressServiceNameId(Integer expressServiceNameId) {
		this.expressServiceNameId = expressServiceNameId;
	}
	public String getExpressServiceName() {
		return expressServiceName;
	}
	public void setExpressServiceName(String expressServiceName) {
		this.expressServiceName = expressServiceName;
	}
	public String getExpressServiceAddress() {
		return expressServiceAddress;
	}
	public void setExpressServiceAddress(String expressServiceAddress) {
		this.expressServiceAddress = expressServiceAddress;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
