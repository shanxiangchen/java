package com.app.entity;

public class Template {
	private Integer infoTenplateId;//信息模板维护id
	private String infoTemplateType;//信息类别
	private String infoTemplateEncoding;//信息编码
	private String infoTemplateDescribe;//信息描述
	private String infoTemplateOne;//备用字段
	private String infoTemplateTow;//备用字段
	private String infoTemplateMould;//标识列
	private Page page; 
	public Template(){}
	 
	public Integer getInfoTenplateId() {
		return infoTenplateId;
	}
	public void setInfoTenplateId(Integer infoTenplateId) {
		this.infoTenplateId = infoTenplateId;
	}
	public String getInfoTemplateType() {
		return infoTemplateType;
	}
	public void setInfoTemplateType(String infoTemplateType) {
		this.infoTemplateType = infoTemplateType;
	}
	public String getInfoTemplateEncoding() {
		return infoTemplateEncoding;
	}
	public void setInfoTemplateEncoding(String infoTemplateEncoding) {
		this.infoTemplateEncoding = infoTemplateEncoding;
	}
	public String getInfoTemplateDescribe() {
		return infoTemplateDescribe;
	}
	public void setInfoTemplateDescribe(String infoTemplateDescribe) {
		this.infoTemplateDescribe = infoTemplateDescribe;
	}
	public String getInfoTemplateOne() {
		return infoTemplateOne;
	}
	public void setInfoTemplateOne(String infoTemplateOne) {
		this.infoTemplateOne = infoTemplateOne;
	}
	public String getInfoTemplateTow() {
		return infoTemplateTow;
	}
	public void setInfoTemplateTow(String infoTemplateTow) {
		this.infoTemplateTow = infoTemplateTow;
	}
	public String getInfoTemplateMould() {
		return infoTemplateMould;
	}
	public void setInfoTemplateMould(String infoTemplateMould) {
		this.infoTemplateMould = infoTemplateMould;
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
