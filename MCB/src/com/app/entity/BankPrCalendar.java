package com.app.entity;

public class BankPrCalendar {
	
	private String calendarId;//主键
	private String calendarType;//假期类型
	private String calendarYear;//假期年份
	private String calendarBeginDate;//假期开始日期
	private String calendarEndDate;//假期结束日期
	private Page page;
	
	
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCalendarType() {
		return calendarType;
	}
	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}
	public String getCalendarYear() {
		return calendarYear;
	}
	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
	}
	public String getCalendarBeginDate() {
		return calendarBeginDate;
	}
	public void setCalendarBeginDate(String calendarBeginDate) {
		this.calendarBeginDate = calendarBeginDate;
	}
	public String getCalendarEndDate() {
		return calendarEndDate;
	}
	public void setCalendarEndDate(String calendarEndDate) {
		this.calendarEndDate = calendarEndDate;
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
