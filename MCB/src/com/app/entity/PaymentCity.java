package com.app.entity;
/***
 * 分期城市
 * @create date 2015/12/28
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class PaymentCity {
	/**
	 * 城市Id
	 */
	private String cityId;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 级次
	 */
	private String levNum;
	/**
	 * 备用字段1
	 */
	private String hold1;
	/**
	 * 备用字段2
	 */
	private String hold2;
	
	private Page page;
	
  
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLevNum() {
		return levNum;
	}
	public void setLevNum(String levNum) {
		this.levNum = levNum;
	}
	public String getHold1() {
		return hold1;
	}
	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}
	public String getHold2() {
		return hold2;
	}
	public void setHold2(String hold2) {
		this.hold2 = hold2;
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
