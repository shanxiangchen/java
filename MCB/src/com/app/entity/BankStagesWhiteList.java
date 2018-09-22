package com.app.entity;
 
public class BankStagesWhiteList {
	
	private String listId;
	private String listPhone;
	private Page page;
	 
	 
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public String getListPhone() {
		return listPhone;
	}
	public void setListPhone(String listPhone) {
		this.listPhone = listPhone;
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

