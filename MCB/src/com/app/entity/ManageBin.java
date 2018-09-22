package com.app.entity;

public class ManageBin {

	/**
	 * 卡产品BIN名称id-- 自动增长
	 */
	private Integer binProductId;
	/**
	 * 卡产品BIN编码
	 */
	private String  binProductCode;
	/**
	 * 卡产品BIN名称
	 */
	private String  binProductName;
	/**
	 * 卡产品BIN号段
	 */
	private String  binProductNo;
	
	private String stateCode;//卡片状态编码
	private String autoStagFlag;//自动分期标识 0-false 1-true
	private String binProductNoLow;//卡产品BIN号低段
	private String binProductNoHigh;//卡产品BIN号高段
	private String cardOfType;//卡产品所属种类 1.兴业通 2.立享卡 3.标准公务卡 4.其他
	private Page page;
	 
	public ManageBin(){}

	
	 
	public Integer getBinProductId() {
		return binProductId;
	}

	public void setBinProductId(Integer binProductId) {
		this.binProductId = binProductId;
	}

	
	public String getBinProductCode() {
		return binProductCode;
	}


	public void setBinProductCode(String binProductCode) {
		this.binProductCode = binProductCode;
	}


	public String getBinProductName() {
		return binProductName;
	}

	public void setBinProductName(String binProductName) {
		this.binProductName = binProductName;
	}

	public String getBinProductNo() {
		return binProductNo;
	}

	public void setBinProductNo(String binProductNo) {
		this.binProductNo = binProductNo;
	}


	public String getStateCode() {
		return stateCode;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


	public String getAutoStagFlag() {
		return autoStagFlag;
	}


	public void setAutoStagFlag(String autoStagFlag) {
		this.autoStagFlag = autoStagFlag;
	}


	public String getBinProductNoLow() {
		return binProductNoLow;
	}


	public void setBinProductNoLow(String binProductNoLow) {
		this.binProductNoLow = binProductNoLow;
	}


	public String getBinProductNoHigh() {
		return binProductNoHigh;
	}


	public void setBinProductNoHigh(String binProductNoHigh) {
		this.binProductNoHigh = binProductNoHigh;
	}


	public String getCardOfType() {
		return cardOfType;
	}


	public void setCardOfType(String cardOfType) {
		this.cardOfType = cardOfType;
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
