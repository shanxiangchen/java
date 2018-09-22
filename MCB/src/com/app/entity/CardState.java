package com.app.entity;

import java.util.Date;

/**
 * 卡片申请状态实体类
 * create date 2016/3/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class CardState {
	private String cardStId;
	private String cardConsumerPwd;
	private String cardMsgNotice; 
	private String cardSendMode;
	private String cardSendAddRess;
	private String custId;
	private String cardInfoId;
	
	private String custName;
	
	private CardInfo cardInfo;
	private InforMation inforMation;
	private Date cardApplyDate;
	private Page page;
	
	private int pageNo;
	private int pageSize;
	public String getCardStId() {
		return cardStId;
	}
	public void setCardStId(String cardStId) {
		this.cardStId = cardStId;
	}
	public String getCardConsumerPwd() {
		return cardConsumerPwd;
	}
	public void setCardConsumerPwd(String cardConsumerPwd) {
		this.cardConsumerPwd = cardConsumerPwd;
	}
	public String getCardMsgNotice() {
		return cardMsgNotice;
	}
	public void setCardMsgNotice(String cardMsgNotice) {
		this.cardMsgNotice = cardMsgNotice;
	}
	public String getCardSendMode() {
		return cardSendMode;
	}
	public void setCardSendMode(String cardSendMode) {
		this.cardSendMode = cardSendMode;
	}
	public String getCardSendAddRess() {
		return cardSendAddRess;
	}
	public void setCardSendAddRess(String cardSendAddRess) {
		this.cardSendAddRess = cardSendAddRess;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(String cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	public InforMation getInforMation() {
		return inforMation;
	}
	public void setInforMation(InforMation inforMation) {
		this.inforMation = inforMation;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Date getCardApplyDate() {
		return cardApplyDate;
	}
	public void setCardApplyDate(Date cardApplyDate) {
		this.cardApplyDate = cardApplyDate;
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