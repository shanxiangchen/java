package com.app.entity;
/**
 * 积分实体类
 * create date 2016/4/17
 * <br/>
 * @author shiguangting@tansun.cn.com
 *
 */
public class BankIntegral {
	/** 积分ID */
	private String  integralId;
	/** 积分图片 */
	private String integralImgRul;
	/** 积分图片名称 */
	private String integralImgRulName;
	/** 积分类型 */
	private String integralType;
	/** 积分详情(MB) */
	private String integralDetailsMb;
	/** 积分详情(PC) */
	private String integralDetailsPc;
	private Page page;
	public String getIntegralId() {
		return integralId;
	}
	public void setIntegralId(String integralId) {
		this.integralId = integralId;
	}
	public String getIntegralImgRul() {
		return integralImgRul;
	}
	public void setIntegralImgRul(String integralImgRul) {
		this.integralImgRul = integralImgRul;
	}
	public String getIntegralImgRulName() {
		return integralImgRulName;
	}
	public void setIntegralImgRulName(String integralImgRulName) {
		this.integralImgRulName = integralImgRulName;
	}
	public String getIntegralType() {
		return integralType;
	}
	public void setIntegralType(String integralType) {
		this.integralType = integralType;
	}
	public String getIntegralDetailsMb() {
		return integralDetailsMb;
	}
	public void setIntegralDetailsMb(String integralDetailsMb) {
		this.integralDetailsMb = integralDetailsMb;
	}
	public String getIntegralDetailsPc() {
		return integralDetailsPc;
	}
	public void setIntegralDetailsPc(String integralDetailsPc) {
		this.integralDetailsPc = integralDetailsPc;
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
