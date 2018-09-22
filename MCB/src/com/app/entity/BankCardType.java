package com.app.entity;
 
public class BankCardType {
	 
	 private String typeId;//主键
	 private String typeNum;//种类编号
	 private String typeName;//种类名称
	 private String typePicName;//图片名称
	 private String typePicUrl;//种类图片访问路径
	 private String typeInfo;//种类介绍
	 private String cardOfType;//卡种所属类型 1.推荐卡 2.标准卡 3.主题卡 4.联名卡 5.认同卡 6.其他
	 private String cardOfName;//卡种所属类型名称
	 private Page page;
	 
	 
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(String typeNum) {
		this.typeNum = typeNum;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypePicName() {
		return typePicName;
	}
	public void setTypePicName(String typePicName) {
		this.typePicName = typePicName;
	}
	public String getTypePicUrl() {
		return typePicUrl;
	}
	public void setTypePicUrl(String typePicUrl) {
		this.typePicUrl = typePicUrl;
	}
	public String getTypeInfo() {
		return typeInfo;
	}
	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}
	public String getCardOfType() {
		return cardOfType;
	}
	public void setCardOfType(String cardOfType) {
		this.cardOfType = cardOfType;
	}
	public String getCardOfName() {
		return cardOfName;
	}
	public void setCardOfName(String cardOfName) {
		this.cardOfName = cardOfName;
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

