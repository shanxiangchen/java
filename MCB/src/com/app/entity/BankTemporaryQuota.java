package com.app.entity;

public class BankTemporaryQuota {
	  
	private String quotaId;//主键ID
	private String applyDate;//申请日期
    private String applyTime;//申请时间
    private String cardNo;//卡号
    private String expectAmount;//期望调升后的额度
    private String effectiveDate;//临时额度生效日期
    private String expirationDate;//临时额度失效日期
    private String usedType;//临时额度用途
    private String usedTypeName;//临时额度用途
    private String decisionResult;//财务决策结果（A或R）
    private String adjustableAmount;//可调整金额
    private String beginDate;//申请开始时间
    private String endDate;//申请结束时间
    private Page page;
    private String searchAll;
    
    
	public String getQuotaId() {
		return quotaId;
	}
	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}
	public String getUsedTypeName() {
		return usedTypeName;
	}
	public void setUsedTypeName(String usedTypeName) {
		this.usedTypeName = usedTypeName;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	 
	public String getExpectAmount() {
		return expectAmount;
	}
	public void setExpectAmount(String expectAmount) {
		this.expectAmount = expectAmount;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getUsedType() {
		return usedType;
	}
	public void setUsedType(String usedType) {
		this.usedType = usedType;
	}
	public String getDecisionResult() {
		return decisionResult;
	}
	public void setDecisionResult(String decisionResult) {
		this.decisionResult = decisionResult;
	}
	public String getAdjustableAmount() {
		return adjustableAmount;
	}
	public void setAdjustableAmount(String adjustableAmount) {
		this.adjustableAmount = adjustableAmount;
	}
    
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchAll() {
		return searchAll;
	}
	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
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
