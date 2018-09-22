package com.app.entity;
/**
 *补件类型实体类
 *create date 2016/1/18
 *<br/>
 *@author shiguangting@tansun.com.cn
 *
 */
public class Patch {
	/**
	 * 补件类型ID
	 */
	private String patchTypeId;
	/**
	 * 补件类型编码
	 */
	private String patchTypeCode;
	/**
	 * 补件类型名称
	 */
	private String patchTypeNmae;
	/**
	 * 补件页面说明
	 */
	private String patchPageExplain;
	/**
	 * 补件页面展示
	 */
	private String patchPageShow;
	/**
	 * 补件档案说明
	 */
	private String patchRecordExplain;
	/**
	 * 备用字段1
	 */
	private String hold1;
	/**
	 * 备用字段2
	 */
	private String hold2;
	 
	private Page page;
	
	public String getPatchTypeId() {
		return patchTypeId;
	}
	public void setPatchTypeId(String patchTypeId) {
		this.patchTypeId = patchTypeId;
	}
	public String getPatchTypeCode() {
		return patchTypeCode;
	}
	public void setPatchTypeCode(String patchTypeCode) {
		this.patchTypeCode = patchTypeCode;
	}
	public String getPatchTypeNmae() {
		return patchTypeNmae;
	}
	public void setPatchTypeNmae(String patchTypeNmae) {
		this.patchTypeNmae = patchTypeNmae;
	}
	public String getPatchPageExplain() {
		return patchPageExplain;
	}
	public void setPatchPageExplain(String patchPageExplain) {
		this.patchPageExplain = patchPageExplain;
	}
	public String getPatchPageShow() {
		return patchPageShow;
	}
	public void setPatchPageShow(String patchPageShow) {
		this.patchPageShow = patchPageShow;
	}
	public String getPatchRecordExplain() {
		return patchRecordExplain;
	}
	public void setPatchRecordExplain(String patchRecordExplain) {
		this.patchRecordExplain = patchRecordExplain;
	}
	public String getHold1() {
		return hold1;
	}
	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}
	public String getHold2() {
		return hold2;
	}
	public void setHold2(String hold2) {
		this.hold2 = hold2;
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

