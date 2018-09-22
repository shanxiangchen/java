package com.app.entity;
 
public class BankEditorSave {
	 
	private String editorId;//主键ID
	private String editorInfo;//富文本内容
	private String editorNum;//富文本标识 1.申办须知 2：申请人申明
	private String editorName;//文本标题
	
	public String getEditorId() {
		return editorId;
	}
	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}
	public String getEditorInfo() {
		return editorInfo;
	}
	public void setEditorInfo(String editorInfo) {
		this.editorInfo = editorInfo;
	}
	public String getEditorNum() {
		return editorNum;
	}
	public void setEditorNum(String editorNum) {
		this.editorNum = editorNum;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	
	
	
	
	
}

