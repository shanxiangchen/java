package com.app.entity;

public class BankMessageAllocation {
	private int messageId;
	private String messageCode;
	private String allocationName;
	private String times;
	private String intervalTime;
	private Page page;
	public BankMessageAllocation() {
		super();
	}
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getAllocationName() {
		return allocationName;
	}
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
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
