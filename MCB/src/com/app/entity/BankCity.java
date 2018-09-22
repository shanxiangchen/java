package com.app.entity;

import java.io.Serializable;

public class BankCity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private String cityNo;
	private String cityName;
	private String cityParentNo;
	private String cityAdministration;
	
	private Integer enabled;
	private String cityOne;
	private String cityOwo;
	private Page page;
	 
	
	public BankCity(){}

	 
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
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

	public String getCityParentNo() {
		return cityParentNo;
	}

	public void setCityParentNo(String cityParentNo) {
		this.cityParentNo = cityParentNo;
	}

	public String getCityAdministration() {
		return cityAdministration;
	}

	public void setCityAdministration(String cityAdministration) {
		this.cityAdministration = cityAdministration;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCityOne() {
		return cityOne;
	}

	public void setCityOne(String cityOne) {
		this.cityOne = cityOne;
	}

	public String getCityOwo() {
		return cityOwo;
	}

	public void setCityOwo(String cityOwo) {
		this.cityOwo = cityOwo;
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
