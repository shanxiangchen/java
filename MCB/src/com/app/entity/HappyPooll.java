package com.app.entity;

/***
 * 热词实体类
 * 
 * @author zhuhao 2016.1.11
 * 
 */
public class HappyPooll {

	private String happyPoollId; //热词ID
	private String happyPoollName; //热词名称
	private int happyPoollorder;  //热词顺序
	private Page page;
	 

	public String getHappyPoollId() {
		return happyPoollId;
	}

	public void setHappyPoollId(String happyPoollId) {
		this.happyPoollId = happyPoollId;
	}

	public String getHappyPoollName() {
		return happyPoollName;
	}

	public void setHappyPoollName(String happyPoollName) {
		this.happyPoollName = happyPoollName;
	}

	public int getHappyPoollorder() {
		return happyPoollorder;
	}

	public void setHappyPoollorder(int happyPoollorder) {
		this.happyPoollorder = happyPoollorder;
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
