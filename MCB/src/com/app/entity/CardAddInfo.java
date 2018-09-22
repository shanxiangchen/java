package com.app.entity;
 
public class CardAddInfo {
	
	private String cardId;//主键ID
	private String cardName;//卡产品名称
	private String cardInfo;//卡产品介绍
	private String cardType;//卡产品所属类型 1.运动健康 2.星座时尚 3.网络购物 4.境外交易 5.低碳环保 6.尊贵身份
	private String cardPicUrl;//卡产品图片存储路径
	private String cardPicName;//卡产品图片名称
	private String cardNum;//卡产品编号
	 
	 
	private String cardForSex;//卡产品适合性别 1.男 2.女
	private String cardForAge;//卡产品适合年龄段：1.30岁以下 2.30-40岁 3.40岁以上
	private String cardForOrder;//卡产品推荐优先级：输入范围1-999之间， 数字大小决定卡片展示时的排列先后顺序，优先级数值越高，排列越靠前。
	private String cardForLike;//适合喜好
	private String cardFlag;//卡产品标识 1：新办卡 2：加办卡卡产品
	private String typeId;//所属卡片种类   
	private String typeName;//所属卡片种类名称  立享卡 、兴业通卡、标准公务卡
	private String cardIsAdd;//是否支持加办卡
	private String cardBmNo;//卡产品版面编号
	
	private Page page;
	
	 
	 
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardPicUrl() {
		return cardPicUrl;
	}
	public void setCardPicUrl(String cardPicUrl) {
		this.cardPicUrl = cardPicUrl;
	}
	public String getCardPicName() {
		return cardPicName;
	}
	public void setCardPicName(String cardPicName) {
		this.cardPicName = cardPicName;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	 
	 
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getCardForSex() {
		return cardForSex;
	}
	public void setCardForSex(String cardForSex) {
		this.cardForSex = cardForSex;
	}
	public String getCardForAge() {
		return cardForAge;
	}
	public void setCardForAge(String cardForAge) {
		this.cardForAge = cardForAge;
	}
	public String getCardForOrder() {
		return cardForOrder;
	}
	public void setCardForOrder(String cardForOrder) {
		this.cardForOrder = cardForOrder;
	}
	public String getCardFlag() {
		return cardFlag;
	}
	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCardForLike() {
		return cardForLike;
	}
	public void setCardForLike(String cardForLike) {
		this.cardForLike = cardForLike;
	}
	public String getCardIsAdd() {
		return cardIsAdd;
	}
	public void setCardIsAdd(String cardIsAdd) {
		this.cardIsAdd = cardIsAdd;
	}
	public String getCardBmNo() {
		return cardBmNo;
	}
	public void setCardBmNo(String cardBmNo) {
		this.cardBmNo = cardBmNo;
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
  
