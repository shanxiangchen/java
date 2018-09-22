package com.app.entity;
/**
 * 广告类实体类
 * @author admin
 *
 */
public class Advert {
	private  String advertId;/*广告ID*/
	private  String advertNo;/*广告编号*/
	private  String advertTitle;/*广告标题*/
	private  String advertPicture;/*广告图片名称*/
	private  String advertPicUrl;/*广告对应的URL连接*/
	private  String advertType;/*广告功能模块 a.商户类 b.活动类 c.分期推荐类*/  
	private  String advertSeat;/*广告位置  1.顶部  2.中部 3.启动页 4底部*/
	private String clientType;
	private int moduleFunctionNo;
	private String advertUrl;
	private ModuleFunctionName moduleFunctionName;
	private String advertTwo;
	private String advertParam;
	private String linkNo;//内链对象编号
	private String linkURL;//内链链接
	private String activityNo;//活动编号
	private String shopId;//商户ID
	private String linkType;//内链方式 1：内链 2：外链
	private int pageNo;
	private int pageSize;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public ModuleFunctionName getModuleFunctionName() {
		return moduleFunctionName;
	}
	public void setModuleFunctionName(ModuleFunctionName moduleFunctionName) {
		this.moduleFunctionName = moduleFunctionName;
	}
	public int getModuleFunctionNo() {
		return moduleFunctionNo;
	}
	public void setModuleFunctionNo(int moduleFunctionNo) {
		this.moduleFunctionNo = moduleFunctionNo;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getAdvertId() {
		return advertId;
	}
	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}
	public String getAdvertNo() {
		return advertNo;
	}
	public void setAdvertNo(String advertNo) {
		this.advertNo = advertNo;
	}
	public String getAdvertTitle() {
		return advertTitle;
	}
	public void setAdvertTitle(String advertTitle) {
		this.advertTitle = advertTitle;
	}
	public String getAdvertPicture() {
		return advertPicture;
	}
	public void setAdvertPicture(String advertPicture) {
		this.advertPicture = advertPicture;
	}
	public String getAdvertPicUrl() {
		return advertPicUrl;
	}
	public void setAdvertPicUrl(String advertPicUrl) {
		this.advertPicUrl = advertPicUrl;
	}
	public String getAdvertType() {
		return advertType;
	}
	public void setAdvertType(String advertType) {
		this.advertType = advertType;
	}
	public String getAdvertSeat() {
		return advertSeat;
	}
	public void setAdvertSeat(String advertSeat) {
		this.advertSeat = advertSeat;
	}
	public String getAdvertUrl() {
		return advertUrl;
	}
	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}
	public String getAdvertParam() {
		return advertParam;
	}
	public void setAdvertParam(String advertParam) {
		this.advertParam = advertParam;
	}
	public String getAdvertTwo() {
		return advertTwo;
	}
	public void setAdvertTwo(String advertTwo) {
		this.advertTwo = advertTwo;
	}
	public String getLinkNo() {
		return linkNo;
	}
	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
	
	
}
