package com.app.entity;

public class BankControlLog {
	
	private String controlId;//主键ID
	private String controlAct;//功能标识  ONE:登录 TWO:我要开卡 THREE :预借现金 FOUR:我要还款 FIVE 修改密码
	private String controlCause;//错误原因
	private String controlDate;//系统时间
	private String controlPhone;//手机号
	private String controlIsOk;//是否成功
	private String controlCauseNo;//错误编码
	private String controlCount;//次数
	private String controlType;//交易类型
	private String controlTime;//记录时间 
	private String nowTime;//现在时间
	private String bfTime;//10分钟之前
	private Page page;
	
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getControlAct() {
		return controlAct;
	}
	public void setControlAct(String controlAct) {
		this.controlAct = controlAct;
	}
	public String getControlCause() {
		return controlCause;
	}
	public void setControlCause(String controlCause) {
		this.controlCause = controlCause;
	}
	public String getControlDate() {
		return controlDate;
	}
	public void setControlDate(String controlDate) {
		this.controlDate = controlDate;
	}
	public String getControlPhone() {
		return controlPhone;
	}
	public void setControlPhone(String controlPhone) {
		this.controlPhone = controlPhone;
	}
	public String getControlIsOk() {
		return controlIsOk;
	}
	public void setControlIsOk(String controlIsOk) {
		this.controlIsOk = controlIsOk;
	}
	public String getControlCauseNo() {
		return controlCauseNo;
	}
	public void setControlCauseNo(String controlCauseNo) {
		this.controlCauseNo = controlCauseNo;
	}
	public String getControlCount() {
		return controlCount;
	}
	public void setControlCount(String controlCount) {
		this.controlCount = controlCount;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getControlTime() {
		return controlTime;
	}
	public void setControlTime(String controlTime) {
		this.controlTime = controlTime;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	public String getBfTime() {
		return bfTime;
	}
	public void setBfTime(String bfTime) {
		this.bfTime = bfTime;
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
