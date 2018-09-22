package com.app.entity;
/**
 * 权益映射表,实体类
 * create date 2016/1/20
 * <br/>
 * @author shigaungting@tansun.com.cn
 */
public class RightsPackagerkMapping {
	private String rightsPackagerkId;	//权益包映射Id
	private String rightsPackagerkName; //权益包名称
	private String appShowName;         //APP显示名称
	private String rightsPackagerkCode; //权益包编码
	private Page page;
	public String getRightsPackagerkId() {
		return rightsPackagerkId;
	}
	public void setRightsPackagerkId(String rightsPackagerkId) {
		this.rightsPackagerkId = rightsPackagerkId;
	}
	public String getRightsPackagerkName() {
		return rightsPackagerkName;
	}
	public void setRightsPackagerkName(String rightsPackagerkName) {
		this.rightsPackagerkName = rightsPackagerkName;
	}
	public String getAppShowName() {
		return appShowName;
	}
	public void setAppShowName(String appShowName) {
		this.appShowName = appShowName;
	}
	public String getRightsPackagerkCode() {
		return rightsPackagerkCode;
	}
	public void setRightsPackagerkCode(String rightsPackagerkCode) {
		this.rightsPackagerkCode = rightsPackagerkCode;
	}
	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
