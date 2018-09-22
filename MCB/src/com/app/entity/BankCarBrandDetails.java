package com.app.entity;


/**
 * 汽车品牌详情实体类
 * @author Administrator
 *
 */
public class BankCarBrandDetails {

	private String detailsId;
	private String brandId;
	private String carModel;
	private String downPaymentProportion;
	private String installmentMoney;
	private double installmentNumber;
	private String clienteleRate;
	private String detailsImgUrl;
	private String detailsImgName;
	private String startTime;
	private String endTime;
	private String brandName;
	private Page page;
	
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getDownPaymentProportion() {
		return downPaymentProportion;
	}
	public void setDownPaymentProportion(String downPaymentProportion) {
		this.downPaymentProportion = downPaymentProportion;
	}
	public String getInstallmentMoney() {
		return installmentMoney;
	}
	public void setInstallmentMoney(String installmentMoney) {
		this.installmentMoney = installmentMoney;
	}
	public double getInstallmentNumber() {
		return installmentNumber;
	}
	public void setInstallmentNumber(double installmentNumber) {
		this.installmentNumber = installmentNumber;
	}
	public String getClienteleRate() {
		return clienteleRate;
	}
	public void setClienteleRate(String clienteleRate) {
		this.clienteleRate = clienteleRate;
	}
	public String getDetailsImgUrl() {
		return detailsImgUrl;
	}
	public void setDetailsImgUrl(String detailsImgUrl) {
		this.detailsImgUrl = detailsImgUrl;
	}
	public String getDetailsImgName() {
		return detailsImgName;
	}
	public void setDetailsImgName(String detailsImgName) {
		this.detailsImgName = detailsImgName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
