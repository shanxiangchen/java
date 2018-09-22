package com.app.entity;

public class CityShop {
	 private Integer shopRingId;
	 private String cityno;
	 private String shopRingName;
	 private String shopRingNo;
	 private String shopRingDescribe;
	 private String shopRingCity;
	 private String shopRingOne;
	 private String shopRingTwo;
	 private Page page;
	  
	 public CityShop(){}
	 
	 
	
	public Integer getShopRingId() {
		return shopRingId;
	}
	
	public void setShopRingId(Integer shopRingId) {
		this.shopRingId = shopRingId;
	}
	public String getShopRingName() {
		return shopRingName;
	}
	
	public void setShopRingName(String shopRingName) {
		this.shopRingName = shopRingName;
	}
	
	public String getShopRingNo() {
		return shopRingNo;
	}
	
	public void setShopRingNo(String shopRingNo) {
		this.shopRingNo = shopRingNo;
	}
	
	public String getShopRingDescribe() {
		return shopRingDescribe;
	}
	
	public void setShopRingDescribe(String shopRingDescribe) {
		this.shopRingDescribe = shopRingDescribe;
	}
	
	public String getShopRingOne() {
		return shopRingOne;
	}
	
	public void setShopRingOne(String shopRingOne) {
		this.shopRingOne = shopRingOne;
	}
	
	public String getShopRingTwo() {
		return shopRingTwo;
	}
	
	public void setShopRingTwo(String shopRingTwo) {
		this.shopRingTwo = shopRingTwo;
	}
	
	public String getCityno() {
		return cityno;
	}
	 
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	 
	public String getShopRingCity() {
		return shopRingCity;
	}
	 
	
	public void setShopRingCity(String shopRingCity) {
		this.shopRingCity = shopRingCity;
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
