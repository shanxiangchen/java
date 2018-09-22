package com.app.entity;
/***
 * 分期类型实体类
 * create data 2015/11/13
 * <br/>
 * @author shiguangtin@tansun.com.cn
 *
 */
public class StagingType {
	private Integer id;//分期类型id
	private String stagCode;//分期类型编码
	private String stagName;//分期类型名称
	private String remark;//备注
	private String hold1;//备用字段1
	private String hold2;//备用字段2
	private Page page;
	 
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
