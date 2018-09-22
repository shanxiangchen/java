package com.app.entity;
/**
 * 费率实体类
 * create date 2015/11/13
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class Rate {
	/***
	 * 费率id
	 */
	private Integer rateId;
	/***
	 * 费率期数
	 */
	private String ratePeriod;
	/***
	 * 费率
	 */
	private double rates;
	/***
	 * 分期类型编码
	 */
	private String stagCode;
	/***
	 * 分期类型名称
	 */
	private String stagName;
	/***
	 * 分期计划编码
	 */
	private String stagingPlanCode;
	/***
	 * 分期计划名称
	 */
	private String stagingPlanName;
	/***
	 * 状态编码
	 */
	private String stateCode;
	/***
	 * 状态名称
	 */
	private String stateNmae;
	/***
	 * 支付方式编码
	 */
	private String paytypeCode;
	/**
	 * 支付方式名称
	 */
	private String paytypeName;
	/***
	 * 备注
	 */
	private String remark;
	/***
	 * 币种编码
	 */
	private String currencyCode;
	/***
	 * 币种名称
	 */
	private String currencyName;
	private Page page;
	 
	public Integer getRateId() {
		return rateId;
	}
	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}
	public String getRatePeriod() {
		return ratePeriod;
	}
	public void setRatePeriod(String ratePeriod) {
		this.ratePeriod = ratePeriod;
	}
	public double getRates() {
		return rates;
	}
	public void setRates(double rates) {
		this.rates = rates;
	}
	public String getStagCode() {
		return stagCode;
	}
	public void setStagCode(String stagCode) {
		this.stagCode = stagCode;
	}
	public String getStagName() {
		return stagName;
	}
	public void setStagName(String stagName) {
		this.stagName = stagName;
	}
	public String getStagingPlanCode() {
		return stagingPlanCode;
	}
	public void setStagingPlanCode(String stagingPlanCode) {
		this.stagingPlanCode = stagingPlanCode;
	}
	public String getStagingPlanName() {
		return stagingPlanName;
	}
	public void setStagingPlanName(String stagingPlanName) {
		this.stagingPlanName = stagingPlanName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateNmae() {
		return stateNmae;
	}
	public void setStateNmae(String stateNmae) {
		this.stateNmae = stateNmae;
	}
	
	public String getPaytypeCode() {
		return paytypeCode;
	}
	public void setPaytypeCode(String paytypeCode) {
		this.paytypeCode = paytypeCode;
	}
	public String getPaytypeName() {
		return paytypeName;
	}
	public void setPaytypeName(String paytypeName) {
		this.paytypeName = paytypeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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
