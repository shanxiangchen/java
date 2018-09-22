package com.app.entity;
/**
 * 产品权益实体类
 * create date 2016/1/21
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class ProductRightsInterests {
	/**
	 * 产品权益ID
	 */
	private String productRightsInterestsId;
	/**
	 * 产品权益名称
	 */
	private String productRightsInterestsName;
	/**
	 * 产品权益编码
	 */
	private String productRightsInterestsCode;
	/**
	 * 产品权益类型
	 */
	private String productRightsInterestsType;
	/**
	 * 是否支持查询明细
	 */
	private String whetherSutpporQueryDetail;
	/**
	 * 是否支持APP预约
	 */
	private String whetherSutpporAppPreabout;
	private Page  page;
	
	public String getProductRightsInterestsId() {
		return productRightsInterestsId;
	}
	public void setProductRightsInterestsId(String productRightsInterestsId) {
		this.productRightsInterestsId = productRightsInterestsId;
	}
	public String getProductRightsInterestsName() {
		return productRightsInterestsName;
	}
	public void setProductRightsInterestsName(String productRightsInterestsName) {
		this.productRightsInterestsName = productRightsInterestsName;
	}
	public String getProductRightsInterestsCode() {
		return productRightsInterestsCode;
	}
	public void setProductRightsInterestsCode(
			String productRightsInterestsCode) {
		this.productRightsInterestsCode = productRightsInterestsCode;
	}
	public String getProductRightsInterestsType() {
		return productRightsInterestsType;
	}
	public void setProductRightsInterestsType(String productRightsInterestsType) {
		this.productRightsInterestsType = productRightsInterestsType;
	}
	public String getWhetherSutpporQueryDetail() {
		return whetherSutpporQueryDetail;
	}
	public void setWhetherSutpporQueryDetail(String whetherSutpporQueryDetail) {
		this.whetherSutpporQueryDetail = whetherSutpporQueryDetail;
	}
	public String getWhetherSutpporAppPreabout() {
		return whetherSutpporAppPreabout;
	}
	public void setWhetherSutpporAppPreabout(String whetherSutpporAppPreabout) {
		this.whetherSutpporAppPreabout = whetherSutpporAppPreabout;
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
