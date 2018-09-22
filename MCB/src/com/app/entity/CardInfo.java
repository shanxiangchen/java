package com.app.entity;

/**
 * 卡产品信息表 create date 2016/3/15 <br/>
 * 
 * @author shiguangting@tansun.com.cn
 * 
 */
public class CardInfo {
	private String cardId;// 主键ID
	private String cardNum;// 产品编码
	private String cardNmae;// 卡产品名称
	private String cardinfo;// 卡产品介绍
	private String cardType;// 卡产品所属类型id
	private String cardPicUrl;// 卡产品图片存储路径
	private String cardPicName;
	private BankCodeInfo bankCodeInfo;// 数据字典信息表
	private Page page;
	private String cardFlag;
	private String cardIsAdd;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNmae() {
		return cardNmae;
	}

	public void setCardNmae(String cardNmae) {
		this.cardNmae = cardNmae;
	}

	public String getCardinfo() {
		return cardinfo;
	}

	public void setCardinfo(String cardinfo) {
		this.cardinfo = cardinfo;
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

	public BankCodeInfo getBankCodeInfo() {
		return bankCodeInfo;
	}

	public void setBankCodeInfo(BankCodeInfo bankCodeInfo) {
		this.bankCodeInfo = bankCodeInfo;
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

	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}

	public String getCardIsAdd() {
		return cardIsAdd;
	}

	public void setCardIsAdd(String cardIsAdd) {
		this.cardIsAdd = cardIsAdd;
	}

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
