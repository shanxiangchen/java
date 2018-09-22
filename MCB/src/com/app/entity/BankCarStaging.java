package com.app.entity;
/**
 * 汽车分期,经销商实体类
 * create date 2016/06/30
 * <br/>
 * @author shigaungting@tansun.com.cn
 *
 */
public class BankCarStaging {
	/** 汽车分期Id */
	private int carStagingId;
	/** 汽车分期城市 */
	private String carStagingCity;
	/** 汽车分期品牌 */
	private String carStagingBrand;
	/** 经销商名称 */
	private String marketingName;
	/** 汽车分期地址 */
	private String carStagingLocation;
	/** 电话 */
	private String carStagingPhone;
	/** 汽车分城市编码 */
	private String carStagingCityCode;
	private Page page;
	public int getCarStagingId() {
		return carStagingId;
	}
	
	public void setCarStagingId(int carStagingId) {
		this.carStagingId = carStagingId;
	}
	public String getCarStagingCity() {
		return carStagingCity;
	}
	public void setCarStagingCity(String carStagingCity) {
		this.carStagingCity = carStagingCity;
	}
	public String getCarStagingBrand() {
		return carStagingBrand;
	}
	public void setCarStagingBrand(String carStagingBrand) {
		this.carStagingBrand = carStagingBrand;
	}
	public String getMarketingName() {
		return marketingName;
	}
	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}
	public String getCarStagingLocation() {
		return carStagingLocation;
	}
	public void setCarStagingLocation(String carStagingLocation) {
		this.carStagingLocation = carStagingLocation;
	}
	public String getCarStagingPhone() {
		return carStagingPhone;
	}
	public void setCarStagingPhone(String carStagingPhone) {
		this.carStagingPhone = carStagingPhone;
	}
	public String getCarStagingCityCode() {
		return carStagingCityCode;
	}
	public void setCarStagingCityCode(String carStagingCityCode) {
		this.carStagingCityCode = carStagingCityCode;
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
