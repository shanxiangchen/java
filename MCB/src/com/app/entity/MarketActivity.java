package com.app.entity;

import java.util.List;

/**
 * 营销活动实体类
 * @author 
 *
 */
public class MarketActivity {
 
	private String activityId;
	private String activityTitle; 
	private String	activityInfo;
	private String activityPictureId;
	private String	activityAreaQuale;
	private String	activityProvince;	
	private String	activityCity;
	private String	activityArea;
	private String	activityStartDate;
	private String activityEndDate;
	private String	isApply;
	private String activityTypeId;
	private Integer activityShowOrder;
	private String activityShopPictureId;
	private String	applyFailDescribe;
	private String	applyFailUrlIco;
	private String	applyFailUrl;
	private String	applySucceed;
	private String	activityStatus;
	private String isTop;
	private String shopName;
	private String	isCheck;
	private String	activityNo;
	private String	trueClickCount;
	private String falseClickCount;
	private String	isMeetDemand;
	private String activityMeetDemandUrl;
	private ActivityType activityType;
	private MarketPicture marketPictures;
	private List<MarketPicture> marketPicture;
	private List<MarketActivityShop> marActivityShop;
	private List<CityGps> cityGps;
	private	 String actictureName;
	private	 String shopPictureName;
	private String staticHtmlUrl;
	private String activityTwo;
	private String acPicId;
	private String shopPicId;
	private String acBigPicId;
	private String meetDemandSucceedPrompt;
	private String meetDemandFailPrompt;
	private String activityContent; 
	private String activityInContent;
	
	private List<Integer> userIds;
	 
	private String citysName;
	private String cityNo;
	private String no;
	private String userId;
	private String logo;
	private String roleTwo;
	private String loginname;
	private Page page;
	
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public String getStaticHtmlUrl() {
		return staticHtmlUrl;
	}
	public void setStaticHtmlUrl(String staticHtmlUrl) {
		this.staticHtmlUrl = staticHtmlUrl;
	}
	public String getActictureName() {
		return actictureName;
	}
	public void setActictureName(String actictureName) {
		this.actictureName = actictureName;
	}
	public String getShopPictureName() {
		return shopPictureName;
	}
	public void setShopPictureName(String shopPictureName) {
		this.shopPictureName = shopPictureName;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public String getTrueClickCount() {
		return trueClickCount;
	}
	public void setTrueClickCount(String trueClickCount) {
		this.trueClickCount = trueClickCount;
	}
	public String getFalseClickCount() {
		return falseClickCount;
	}
	public void setFalseClickCount(String falseClickCount) {
		this.falseClickCount = falseClickCount;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public List<MarketActivityShop> getMarActivityShop() {
		return marActivityShop;
	}
	public void setMarActivityShop(List<MarketActivityShop> marActivityShop) {
		this.marActivityShop = marActivityShop;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public MarketPicture getMarketPictures() {
		return marketPictures;
	}
	public void setMarketPictures(MarketPicture marketPictures) {
		this.marketPictures = marketPictures;
	}
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	public List<MarketPicture> getMarketPicture() {
		return marketPicture;
	}
	public void setMarketPicture(List<MarketPicture> marketPicture) {
		this.marketPicture = marketPicture;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityInfo() {
		return activityInfo;
	}
	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}
	public String getActivityPictureId() {
		return activityPictureId;
	}
	public void setActivityPictureId(String activityPictureId) {
		this.activityPictureId = activityPictureId;
	}
	public String getActivityAreaQuale() {
		return activityAreaQuale;
	}
	public void setActivityAreaQuale(String activityAreaQuale) {
		this.activityAreaQuale = activityAreaQuale;
	}
	public String getActivityProvince() {
		return activityProvince;
	}
	public void setActivityProvince(String activityProvince) {
		this.activityProvince = activityProvince;
	}
	public String getActivityCity() {
		return activityCity;
	}
	public void setActivityCity(String activityCity) {
		this.activityCity = activityCity;
	}
	public String getActivityArea() {
		return activityArea;
	}
	public void setActivityArea(String activityArea) {
		this.activityArea = activityArea;
	}
	public String getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(String activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	public String getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	public String getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public Integer getActivityShowOrder() {
		return activityShowOrder;
	}
	public void setActivityShowOrder(Integer activityShowOrder) {
		this.activityShowOrder = activityShowOrder;
	}
	public String getActivityShopPictureId() {
		return activityShopPictureId;
	}
	public void setActivityShopPictureId(String activityShopPictureId) {
		this.activityShopPictureId = activityShopPictureId;
	}
	public String getIsApply() {
		return isApply;
	}
	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}
	public String getApplyFailDescribe() {
		return applyFailDescribe;
	}
	public void setApplyFailDescribe(String applyFailDescribe) {
		this.applyFailDescribe = applyFailDescribe;
	}
	public String getApplyFailUrlIco() {
		return applyFailUrlIco;
	}
	public void setApplyFailUrlIco(String applyFailUrlIco) {
		this.applyFailUrlIco = applyFailUrlIco;
	}
	public String getApplyFailUrl() {
		return applyFailUrl;
	}
	public void setApplyFailUrl(String applyFailUrl) {
		this.applyFailUrl = applyFailUrl;
	}
	public String getApplySucceed() {
		return applySucceed;
	}
	public void setApplySucceed(String applySucceed) {
		this.applySucceed = applySucceed;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public String getIsMeetDemand() {
		return isMeetDemand;
	}
	public void setIsMeetDemand(String isMeetDemand) {
		this.isMeetDemand = isMeetDemand;
	}
	public String getActivityMeetDemandUrl() {
		return activityMeetDemandUrl;
	}
	public void setActivityMeetDemandUrl(String activityMeetDemandUrl) {
		this.activityMeetDemandUrl = activityMeetDemandUrl;
	}
	public List<CityGps> getCityGps() {
		return cityGps;
	}
	public void setCityGps(List<CityGps> cityGps) {
		this.cityGps = cityGps;
	}
	public String getActivityTwo() {
		return activityTwo;
	}
	public void setActivityTwo(String activityTwo) {
		this.activityTwo = activityTwo;
	}
	public String getCitysName() {
		return citysName;
	}
	public void setCitysName(String citysName) {
		this.citysName = citysName;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAcPicId() {
		return acPicId;
	}
	public void setAcPicId(String acPicId) {
		this.acPicId = acPicId;
	}
	public String getShopPicId() {
		return shopPicId;
	}
	public void setShopPicId(String shopPicId) {
		this.shopPicId = shopPicId;
	}
	public String getAcBigPicId() {
		return acBigPicId;
	}
	public void setAcBigPicId(String acBigPicId) {
		this.acBigPicId = acBigPicId;
	}
	public String getMeetDemandSucceedPrompt() {
		return meetDemandSucceedPrompt;
	}
	public void setMeetDemandSucceedPrompt(String meetDemandSucceedPrompt) {
		this.meetDemandSucceedPrompt = meetDemandSucceedPrompt;
	}
	public String getMeetDemandFailPrompt() {
		return meetDemandFailPrompt;
	}
	public void setMeetDemandFailPrompt(String meetDemandFailPrompt) {
		this.meetDemandFailPrompt = meetDemandFailPrompt;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public String getActivityInContent() {
		return activityInContent;
	}
	public void setActivityInContent(String activityInContent) {
		this.activityInContent = activityInContent;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRoleTwo() {
		return roleTwo;
	}
	public void setRoleTwo(String roleTwo) {
		this.roleTwo = roleTwo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
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
