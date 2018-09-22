package com.app.entity;


 
public class BankPrFiledType {
	
	private String filedTypeId;//主键ID
	private String typeId;//类型ID
	private String filedId;//权益字段ID
	private int sortNo;//排序号
	private String showWhere;//字段展示位置；1.基本信息 2.更多信息
	private String filedAlert;//字段提示信息
	private String typeName;//类型名称
	private String filedName;//字段名称
	private String isShow;//是否展示 1.展示 0.不展示 2、条件展示
	private String formType;//表单类型
	private String maxLength;
	
	//////////////////////////////////////////////////////
	private String filedIds;//权益字段ID拼接字符串
	private String filedNames;//字段名称拼接字符串
	private String filedAbcNames;//字段英文名称拼接串
	private String isShows;//是否展示拼接字符串
	private String sortNos;//排序号拼接字符串
	private String filedAlertInfo;//字段提示信息
	private String alertInfoOne;//备注提示信息1
	private String alertInfoTwo;//备注提示信息2
	private String alertInfoThree;//备注提示信息3
	private String serviceAlertInfo;//服务提示信息
	private String maxlengths;
	private Page page;	
	public String getFiledTypeId() {
		return filedTypeId;
	}
	public void setFiledTypeId(String filedTypeId) {
		this.filedTypeId = filedTypeId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	 
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getShowWhere() {
		return showWhere;
	}
	public void setShowWhere(String showWhere) {
		this.showWhere = showWhere;
	}
	public String getFiledAlert() {
		return filedAlert;
	}
	public void setFiledAlert(String filedAlert) {
		this.filedAlert = filedAlert;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getSortNos() {
		return sortNos;
	}
	public void setSortNos(String sortNos) {
		this.sortNos = sortNos;
	}
	public String getFiledAlertInfo() {
		return filedAlertInfo;
	}
	public void setFiledAlertInfo(String filedAlertInfo) {
		this.filedAlertInfo = filedAlertInfo;
	}
	public String getAlertInfoOne() {
		return alertInfoOne;
	}
	public void setAlertInfoOne(String alertInfoOne) {
		this.alertInfoOne = alertInfoOne;
	}
	public String getAlertInfoTwo() {
		return alertInfoTwo;
	}
	public void setAlertInfoTwo(String alertInfoTwo) {
		this.alertInfoTwo = alertInfoTwo;
	}
	public String getAlertInfoThree() {
		return alertInfoThree;
	}
	public void setAlertInfoThree(String alertInfoThree) {
		this.alertInfoThree = alertInfoThree;
	}
	public String getServiceAlertInfo() {
		return serviceAlertInfo;
	}
	public void setServiceAlertInfo(String serviceAlertInfo) {
		this.serviceAlertInfo = serviceAlertInfo;
	}
	public String getFiledIds() {
		return filedIds;
	}
	public void setFiledIds(String filedIds) {
		this.filedIds = filedIds;
	}
	 
	public String getFiledNames() {
		return filedNames;
	}
	public void setFiledNames(String filedNames) {
		this.filedNames = filedNames;
	}
	public String getIsShows() {
		return isShows;
	}
	public void setIsShows(String isShows) {
		this.isShows = isShows;
	}
	public String getFiledAbcNames() {
		return filedAbcNames;
	}
	public void setFiledAbcNames(String filedAbcNames) {
		this.filedAbcNames = filedAbcNames;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}	
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	 
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getMaxlengths() {
		return maxlengths;
	}
	public void setMaxlengths(String maxlengths) {
		this.maxlengths = maxlengths;
	}	
	
	
}
