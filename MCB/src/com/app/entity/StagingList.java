package com.app.entity;

/**
 * 分期快捷名单
 * 
 * @author zhuhao
 * 
 */
public class StagingList {
	private String speedyList;
	private String city;
	private String name;
	private String phone;
	private String creditCardLimit;
	private String noMortgageLargeStaging;
	private String noMortgageCarStaging;
	private String noMortgageDirectStaging;
	private String mortgageCarStaging;
	private String mortgageDirectStaging;
	private String expiryDate;
	private String cityName;
	private Page page;

	public String getSpeedyList() {
		return speedyList;
	}

	public void setSpeedyList(String speedyList) {
		this.speedyList = speedyList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getCreditCardLimit() {
		return creditCardLimit;
	}

	public void setCreditCardLimit(String creditCardLimit) {
		this.creditCardLimit = creditCardLimit;
	}

	public String getNoMortgageLargeStaging() {
		return noMortgageLargeStaging;
	}

	public void setNoMortgageLargeStaging(String noMortgageLargeStaging) {
		this.noMortgageLargeStaging = noMortgageLargeStaging;
	}

	public String getNoMortgageCarStaging() {
		return noMortgageCarStaging;
	}

	public void setNoMortgageCarStaging(String noMortgageCarStaging) {
		this.noMortgageCarStaging = noMortgageCarStaging;
	}

	public String getNoMortgageDirectStaging() {
		return noMortgageDirectStaging;
	}

	public void setNoMortgageDirectStaging(String noMortgageDirectStaging) {
		this.noMortgageDirectStaging = noMortgageDirectStaging;
	}

	public String getMortgageCarStaging() {
		return mortgageCarStaging;
	}

	public void setMortgageCarStaging(String mortgageCarStaging) {
		this.mortgageCarStaging = mortgageCarStaging;
	}

	public String getMortgageDirectStaging() {
		return mortgageDirectStaging;
	}

	public void setMortgageDirectStaging(String mortgageDirectStaging) {
		this.mortgageDirectStaging = mortgageDirectStaging;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
