package com.app.entity;
/**
 * 分期商店信息Entity
 * @author admin
 *
 */
public class StoreStages {
	private String  storeStagesId;//分期商店id
	private String  storeNo;
	private String  storeName;
	private String  storeAddr;
	private String  fee3;
	private String  fee6;
	private String  fee12;
	private String  fee24;
	private String  cityNo; 
	private String mayTransactStagesPeriods;//可办理分期期数
	private String tradeName;//行业名称 
	private PaymentCity paymentcity;
	private Page page;
	 
	
	
	public StoreStages() {
	}
	
	public StoreStages(String storeStagesId, String storeNo, String storeName,String  storeAddr,String 
			fee3,String  fee6,String  fee12,String  fee24, String  cityNo,
			String mayTransactStagesPeriods,String tradeName) {
		this.storeStagesId = storeStagesId;
		this.storeNo = storeNo;
		this.storeName = storeName;
		this.storeAddr = storeAddr;
		this.fee3 = fee3;
		this.fee6 = fee6;
		this.fee12 = fee12;
		this.fee24 = fee24;
		this.cityNo = cityNo;
		this.mayTransactStagesPeriods = mayTransactStagesPeriods;
		this.tradeName = tradeName;
		
	}
	
	
	public StoreStages(String storeName,String  storeAddr,String 
			fee3,String  fee6,String  fee12,String  fee24, String  cityNo,
			String mayTransactStagesPeriods,String tradeName) {
		this.storeName = storeName;
		this.storeAddr = storeAddr;
		this.fee3 = fee3;
		this.fee6 = fee6;
		this.fee12 = fee12;
		this.fee24 = fee24;
		this.cityNo = cityNo;
		this.mayTransactStagesPeriods = mayTransactStagesPeriods;
		this.tradeName = tradeName;
		
	}
	
	@Override
	public String toString() {
		return "StoreStages [storename=" + storeName
				+", storeaddr="+ storeAddr+ ",fee3 ="+ fee3+ ",fee6 ="+ fee6 +",fee12 ="+ fee12 +",fee24 ="+ fee24 
				+",cityno ="+ cityNo +",maytransactstagesperiods ="+ mayTransactStagesPeriods +",tradename ="+ tradeName +"]";
		
	}
	
	public PaymentCity getPaymentcity() {
		return paymentcity;
	}
	public void setPaymentcity(PaymentCity paymentcity) {
		this.paymentcity = paymentcity;
	}
	 
	public String getStoreStagesId() {
		return storeStagesId;
	}
	public void setStoreStagesId(String storeStagesId) {
		this.storeStagesId = storeStagesId;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddr() {
		return storeAddr;
	}
	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	public String getFee3() {
		return fee3;
	}
	public void setFee3(String fee3) {
		this.fee3 = fee3;
	}
	public String getFee6() {
		return fee6;
	}
	public void setFee6(String fee6) {
		this.fee6 = fee6;
	}
	public String getFee12() {
		return fee12;
	}
	public void setFee12(String fee12) {
		this.fee12 = fee12;
	}
	public String getFee24() {
		return fee24;
	}
	public void setFee24(String fee24) {
		this.fee24 = fee24;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getMayTransactStagesPeriods() {
		return mayTransactStagesPeriods;
	}
	public void setMayTransactStagesPeriods(String mayTransactStagesPeriods) {
		this.mayTransactStagesPeriods = mayTransactStagesPeriods;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
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
