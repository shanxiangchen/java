package com.app.entity;
/**
 * 活动报名名单实体类
 * create date 2016/3/10
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class ActivityApplyList {
	private String activityApplyListId;
	private String activityId;
	private String activityApplyListPhone;
	//引入营销活动实体类
	private MarketActivity marketActivity;
	private String  activityTitle;
	private String  activityStartDate;
	private String  activityEndDate;
	private Page page; 
	
	
	
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
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityApplyListId() {
		return activityApplyListId;
	}
	public void setActivityApplyListId(String activityApplyListId) {
		this.activityApplyListId = activityApplyListId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityApplyListPhone() {
		return activityApplyListPhone;
	}
	public void setActivityApplyListPhone(String activityApplyListPhone) {
		this.activityApplyListPhone = activityApplyListPhone;
	}
	 
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
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
