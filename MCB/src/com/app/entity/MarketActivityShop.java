package com.app.entity;


/**
 * 营销活动商户实体类
 * @author 
 *
 */
public class MarketActivityShop {
	private String shopId;
	private String shopName; 
	private String	shopAddress;
	private String	shopPhone;	
	private String shopLongitude;//经度
	private String shopLatitude;//纬度
	private String activityId;
	private String	isDraft;
	private String is0pen;
	private Page page; 
	
	 
	private MarketActivity marketActivity;
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	
	public String getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public String getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}
	public String getIs0pen() {
		return is0pen;
	}
	public void setIs0pen(String is0pen) {
		this.is0pen = is0pen;
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
