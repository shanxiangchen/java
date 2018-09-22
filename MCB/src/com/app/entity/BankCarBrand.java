package com.app.entity;

/**
 * 汽车品牌实体类
 * @author ZH
 *
 */
public class BankCarBrand {

	
	private String brandId;//品牌id
	private String brandName;//品牌名称
	private String imgAddressUrl;//图片地址url
	private String imgName;//图片名称
	private String brandDetailsMb;//品牌详情MB
	private String brandDetailsPc;//品牌详情PC
	private Page page;
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getImgAddressUrl() {
		return imgAddressUrl;
	}
	public void setImgAddressUrl(String imgAddressUrl) {
		this.imgAddressUrl = imgAddressUrl;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getBrandDetailsMb() {
		return brandDetailsMb;
	}
	public void setBrandDetailsMb(String brandDetailsMb) {
		this.brandDetailsMb = brandDetailsMb;
	}
	public String getBrandDetailsPc() {
		return brandDetailsPc;
	}
	public void setBrandDetailsPc(String brandDetailsPc) {
		this.brandDetailsPc = brandDetailsPc;
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
