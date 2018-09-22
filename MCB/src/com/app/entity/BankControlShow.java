package com.app.entity;

public class BankControlShow {
	 
	private String showId;//主键ID
	private String showType;//监控类型：1.CCGW交易情况 2.制卡系统加密机情况 3.中间业务平台加密机情况 4.彩信平台交易情况 5.交易平台情况 6.Apache服务情况
    private String showSuccessNum;//监控成功次数
    private String showErrorNum;//监控失败次数
    private String showErrorRate;//监控失败率
    private String showIsOk;//是否解决
    private String showAlarmInfo;//告警内容（中文）描述
    private String showRemarks;//备注说明
    private String showInsertDate;//统计日期
    private String showInsertTime;//统计时间
    
    private String startDate;//告警开始时间
    private String endDate;//告警结束时间
    private Page page;
    
    
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getShowSuccessNum() {
		return showSuccessNum;
	}
	public void setShowSuccessNum(String showSuccessNum) {
		this.showSuccessNum = showSuccessNum;
	}
	public String getShowErrorNum() {
		return showErrorNum;
	}
	public void setShowErrorNum(String showErrorNum) {
		this.showErrorNum = showErrorNum;
	}
	public String getShowErrorRate() {
		return showErrorRate;
	}
	public void setShowErrorRate(String showErrorRate) {
		this.showErrorRate = showErrorRate;
	}
	public String getShowIsOk() {
		return showIsOk;
	}
	public void setShowIsOk(String showIsOk) {
		this.showIsOk = showIsOk;
	}
	public String getShowAlarmInfo() {
		return showAlarmInfo;
	}
	public void setShowAlarmInfo(String showAlarmInfo) {
		this.showAlarmInfo = showAlarmInfo;
	}
	public String getShowRemarks() {
		return showRemarks;
	}
	public void setShowRemarks(String showRemarks) {
		this.showRemarks = showRemarks;
	}
	public String getShowInsertDate() {
		return showInsertDate;
	}
	public void setShowInsertDate(String showInsertDate) {
		this.showInsertDate = showInsertDate;
	}
	public String getShowInsertTime() {
		return showInsertTime;
	}
	public void setShowInsertTime(String showInsertTime) {
		this.showInsertTime = showInsertTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
