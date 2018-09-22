package com.app.entity;


 
public class BankPrRightsFiled {
	
	 private String filedId;//主键
	 private String filedName;//字段中文名称
	 private String filedAbcName;//字段英文名称
	 private String filedFormType;//字段所属表单类型
	 private String filedIsShow;//是否显示 1.是 2.否
	 
	 
	 
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public String getFiledAbcName() {
		return filedAbcName;
	}
	public void setFiledAbcName(String filedAbcName) {
		this.filedAbcName = filedAbcName;
	}
	public String getFiledFormType() {
		return filedFormType;
	}
	public void setFiledFormType(String filedFormType) {
		this.filedFormType = filedFormType;
	}
	public String getFiledIsShow() {
		return filedIsShow;
	}
	public void setFiledIsShow(String filedIsShow) {
		this.filedIsShow = filedIsShow;
	}
	 
	 
	 
	 
}
