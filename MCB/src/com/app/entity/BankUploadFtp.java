package com.app.entity;


/**
 * 网银已持卡进件导入数据存储表 
 * @author zhaolei 
 * @date 2016-4-14 下午3:24:08
 */
public class BankUploadFtp {
	
	  private String ftpId; //主键ID
	  private String custName;//客户姓名
	  private String cardNum;//登录卡号
	  private String custPhone;//客户手机号
	  private String cardConsumerPwd;//境内是否使用消费密码
	  private String autoStages;//自动分期功能
	  private String autoStagesMoney;//自动分期金额
	  private String autoStagesNum;//自动分期数
	  private String cardName;//卡片名称
	  private String cardType;//卡片种类
	  private String mainCardPage;//主卡版面编号
	  private String unitNum;//单位代号
	  private String barCodeNumber;//条形码编号
	  private String businessSource;//业务来源
	  private String insertSysDate;//数据生成时间
	  private String custCardId;//客户身份证号
	  private String cardId;//卡片编号
	  private Page page;
	  
		public String getFtpId() {
			return ftpId;
		}
		public void setFtpId(String ftpId) {
			this.ftpId = ftpId;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getCardNum() {
			return cardNum;
		}
		public void setCardNum(String cardNum) {
			this.cardNum = cardNum;
		}
		public String getCustPhone() {
			return custPhone;
		}
		public void setCustPhone(String custPhone) {
			this.custPhone = custPhone;
		}
		public String getCardConsumerPwd() {
			return cardConsumerPwd;
		}
		public void setCardConsumerPwd(String cardConsumerPwd) {
			this.cardConsumerPwd = cardConsumerPwd;
		}
		public String getAutoStages() {
			return autoStages;
		}
		public void setAutoStages(String autoStages) {
			this.autoStages = autoStages;
		}
		public String getAutoStagesMoney() {
			return autoStagesMoney;
		}
		public void setAutoStagesMoney(String autoStagesMoney) {
			this.autoStagesMoney = autoStagesMoney;
		}
		public String getAutoStagesNum() {
			return autoStagesNum;
		}
		public void setAutoStagesNum(String autoStagesNum) {
			this.autoStagesNum = autoStagesNum;
		}
		public String getCardName() {
			return cardName;
		}
		public void setCardName(String cardName) {
			this.cardName = cardName;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getMainCardPage() {
			return mainCardPage;
		}
		public void setMainCardPage(String mainCardPage) {
			this.mainCardPage = mainCardPage;
		}
		public String getUnitNum() {
			return unitNum;
		}
		public void setUnitNum(String unitNum) {
			this.unitNum = unitNum;
		}
		public String getBarCodeNumber() {
			return barCodeNumber;
		}
		public void setBarCodeNumber(String barCodeNumber) {
			this.barCodeNumber = barCodeNumber;
		}
		public String getBusinessSource() {
			return businessSource;
		}
		public void setBusinessSource(String businessSource) {
			this.businessSource = businessSource;
		}
        
		public String getInsertSysDate() {
			return insertSysDate;
		}
		public void setInsertSysDate(String insertSysDate) {
			this.insertSysDate = insertSysDate;
		}
		public Page getPage() {
			if(page==null)
				page = new Page();
			return page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public String getCustCardId() {
			return custCardId;
		}
		public void setCustCardId(String custCardId) {
			this.custCardId = custCardId;
		}
		public String getCardId() {
			return cardId;
		}
		public void setCardId(String cardId) {
			this.cardId = cardId;
		}	
		 
	    
      
 
 
}
