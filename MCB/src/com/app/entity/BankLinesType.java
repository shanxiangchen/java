package com.app.entity;

/**
 * 额度类型表
 * 
 * @author Administrator
 * 
 */
public class BankLinesType {

	private String linesTypeId;
	private String linesType; //额度类型 A.临额  B.固额
	private int linesNumber;
	private String promptInformation;
	private String linesMaxValue;
	private Page page;
	public String getLinesTypeId() {
		return linesTypeId;
	}

	public void setLinesTypeId(String linesTypeId) {
		this.linesTypeId = linesTypeId;
	}

	public String getLinesType() {
		return linesType;
	}

	public void setLinesType(String linesType) {
		this.linesType = linesType;
	}
 

	public int getLinesNumber() {
		return linesNumber;
	}

	public void setLinesNumber(int linesNumber) {
		this.linesNumber = linesNumber;
	}

	public String getPromptInformation() {
		return promptInformation;
	}

	public void setPromptInformation(String promptInformation) {
		this.promptInformation = promptInformation;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String getLinesMaxValue() {
		return linesMaxValue;
	}

	public void setLinesMaxValue(String linesMaxValue) {
		this.linesMaxValue = linesMaxValue;
	}	
	
}
