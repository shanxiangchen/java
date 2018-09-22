package com.app.entity;

public class CityGps {
	private int cityId;
	private String cityNo;
	private String cityName;
	private String cityOne;
	private MarketActivity marketActivity;
	
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityOne() {
		return cityOne;
	}
	public void setCityOne(String cityOne) {
		this.cityOne = cityOne;
	}
	
}
