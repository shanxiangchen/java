package com.app.entity;
/**
 * 营销图片实体类
 * @author admin
 *
 */
public class MarketPicture {
	
	private String pictureId;
	private	 String actPictureUrl;
	private	 String actictureName;
	private	 String shopPictureUrl;
	private	 String shopPictureName;
	private Integer pictureFlag;
	private String pictureSize;
	private MarketActivity marketActivity;
	private String activityId;
	private String acBigPicName;
	private String acBigPicUrl;
	private String acPicId;
	private String shopPicId;
	private String acBigPicId;
	
	public String getAcBigPicName() {
		return acBigPicName;
	}
	public void setAcBigPicName(String acBigPicName) {
		this.acBigPicName = acBigPicName;
	}
	public String getAcBigPicUrl() {
		return acBigPicUrl;
	}
	public void setAcBigPicUrl(String acBigPicUrl) {
		this.acBigPicUrl = acBigPicUrl;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getActPictureUrl() {
		return actPictureUrl;
	}
	public void setActPictureUrl(String actPictureUrl) {
		this.actPictureUrl = actPictureUrl;
	}
	public String getActictureName() {
		return actictureName;
	}
	public void setActictureName(String actictureName) {
		this.actictureName = actictureName;
	}
	public String getShopPictureUrl() {
		return shopPictureUrl;
	}
	public void setShopPictureUrl(String shopPictureUrl) {
		this.shopPictureUrl = shopPictureUrl;
	}
	public String getShopPictureName() {
		return shopPictureName;
	}
	public void setShopPictureName(String shopPictureName) {
		this.shopPictureName = shopPictureName;
	}
	public Integer getPictureFlag() {
		return pictureFlag;
	}
	public void setPictureFlag(Integer pictureFlag) {
		this.pictureFlag = pictureFlag;
	}
	public String getPictureSize() {
		return pictureSize;
	}
	public void setPictureSize(String pictureSize) {
		this.pictureSize = pictureSize;
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
	
}
