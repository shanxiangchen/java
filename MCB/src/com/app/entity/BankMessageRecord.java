package com.app.entity;

import java.util.Date;

public class BankMessageRecord {
	private int recordId;
	private Date date;
	private int failTimes;
	private int isSuccess;
	private String messageCode;
	//private int isDelete;
	private BankMessageAllocation bankMessageAllocation;
	private Page page;
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getFailTimes() {
		return failTimes;
	}
	public void setFailTimes(int failTimes) {
		this.failTimes = failTimes;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public BankMessageAllocation getBankMessageAllocation() {
		return bankMessageAllocation;
	}
	public void setBankMessageAllocation(BankMessageAllocation bankMessageAllocation) {
		this.bankMessageAllocation = bankMessageAllocation;
	}
}
