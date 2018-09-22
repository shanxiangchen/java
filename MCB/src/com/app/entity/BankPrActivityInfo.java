package com.app.entity;
/**
 * 权益活动存储表
 * @author zhaolei 
 * @date 2016-4-15 上午11:38:27
 */
public class BankPrActivityInfo {
	
	 private String activityInfoId;//主键ID
	 private String activityStartDate;//活动开始时间
	 private String activityEndDate;//活动结束时间
	 private String activityBeforeDay;//权益预约提前天数
	 private String rightsTypeCode;//权益类型编码
	 private String activityContent;//活动说明
	 private String activityCode;//活动编码
	 private String activityName;//活动名称
	 private String activitySelfUsed;//本人已用(0:否 1:是)
	 private String activityHeelUsed;//随行已用(0:否 1:是)
	 private String activityOrder;//是否预约(0:否 1:是)
	 private String activityOrderName;//预约按钮名称
	 private String activitySow;//是否展示
	 private String activityCalculation;//计算方式 1.自然日 2.工作日
	 private String activityMaxBeforeDay;//服务最大预约提前天数
	 private Page page;
	public String getActivityInfoId() {
		return activityInfoId;
	}
	public void setActivityInfoId(String activityInfoId) {
		this.activityInfoId = activityInfoId;
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
	public String getActivityBeforeDay() {
		return activityBeforeDay;
	}
	public void setActivityBeforeDay(String activityBeforeDay) {
		this.activityBeforeDay = activityBeforeDay;
	}
	public String getRightsTypeCode() {
		return rightsTypeCode;
	}
	public void setRightsTypeCode(String rightsTypeCode) {
		this.rightsTypeCode = rightsTypeCode;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivitySelfUsed() {
		return activitySelfUsed;
	}
	public void setActivitySelfUsed(String activitySelfUsed) {
		this.activitySelfUsed = activitySelfUsed;
	}
	public String getActivityHeelUsed() {
		return activityHeelUsed;
	}
	public void setActivityHeelUsed(String activityHeelUsed) {
		this.activityHeelUsed = activityHeelUsed;
	}
	public String getActivityOrder() {
		return activityOrder;
	}
	public void setActivityOrder(String activityOrder) {
		this.activityOrder = activityOrder;
	}
	public String getActivityOrderName() {
		return activityOrderName;
	}
	public void setActivityOrderName(String activityOrderName) {
		this.activityOrderName = activityOrderName;
	}
	public String getActivitySow() {
		return activitySow;
	}
	public void setActivitySow(String activitySow) {
		this.activitySow = activitySow;
	}
	public String getActivityCalculation() {
		return activityCalculation;
	}
	public void setActivityCalculation(String activityCalculation) {
		this.activityCalculation = activityCalculation;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getActivityMaxBeforeDay() {
		return activityMaxBeforeDay;
	}
	public void setActivityMaxBeforeDay(String activityMaxBeforeDay) {
		this.activityMaxBeforeDay = activityMaxBeforeDay;
	}	
	
}
