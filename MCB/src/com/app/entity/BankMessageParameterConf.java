package com.app.entity;

public class BankMessageParameterConf {
	private int bmpcId;
	private String bmpcCode;
	private String bmpcUrl;
	private String bmpcName;
	private String bmpcType;
	private String bmpcTypeName;
	
	private Page page;
	
	public int getBmpcId() {
		return bmpcId;
	}
	public void setBmpcId(int bmpcId) {
		this.bmpcId = bmpcId;
	}
	public String getBmpcCode() {
		return bmpcCode;
	}
	public void setBmpcCode(String bmpcCode) {
		this.bmpcCode = bmpcCode;
	}
	public String getBmpcUrl() {
		return bmpcUrl;
	}
	public void setBmpcUrl(String bmpcUrl) {
		this.bmpcUrl = bmpcUrl;
	}
	public String getBmpcName() {
		return bmpcName;
	}
	public void setBmpcName(String bmpcName) {
		this.bmpcName = bmpcName;
	}
	public String getBmpcType() {
		return bmpcType;
	}
	public void setBmpcType(String bmpcType) {
		this.bmpcType = bmpcType;
	}
	public String getBmpcTypeName() {
		return bmpcTypeName;
	}
	public void setBmpcTypeName(String bmpcTypeName) {
		this.bmpcTypeName = bmpcTypeName;
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
