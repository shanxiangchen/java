package com.app.entity;


public class Gprs {
	//市
	private  Integer  cityid;
	private  String   cityno;
	private  String  cityname;
	//商圈
	
	private Integer shopringid;       
	private String  cityon;          
	private String shopringname;    
	private String shopringno;       
	private String  shopringdescribe; 
	private String  shopringcity;    
	
	
	
	public Integer getShopringid() {
		return shopringid;
	}
	public void setShopringid(Integer shopringid) {
		this.shopringid = shopringid;
	}
	public String getCityon() {
		return cityon;
	}
	public void setCityon(String cityon) {
		this.cityon = cityon;
	}
	public String getShopringname() {
		return shopringname;
	}
	public void setShopringname(String shopringname) {
		this.shopringname = shopringname;
	}
	public String getShopringno() {
		return shopringno;
	}
	public void setShopringno(String shopringno) {
		this.shopringno = shopringno;
	}
	public String getShopringdescribe() {
		return shopringdescribe;
	}
	public void setShopringdescribe(String shopringdescribe) {
		this.shopringdescribe = shopringdescribe;
	}
	public String getShopringcity() {
		return shopringcity;
	}
	public void setShopringcity(String shopringcity) {
		this.shopringcity = shopringcity;
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
}
