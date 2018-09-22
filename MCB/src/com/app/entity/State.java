package com.app.entity;
/***	
 * 状态是实体类
 * create date 2015/11/14
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class State {
	private Integer stateId;//状态id
	private String stateCode;//状态编号
	private String stateName;//状态名称
	private String stagCode;//分期类型编码
	private String remark;//备注
	private String hold1;//备用字段1
	private String hold2;//备用字段2
	
	private StagingType stagingType;
	private Page page;
	  

	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStagCode() {
		return stagCode;
	}
	public void setStagCode(String stagCode) {
		this.stagCode = stagCode;
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
	public StagingType getStagingType() {
		return stagingType;
	}
	public void setStagingType(StagingType stagingType) {
		this.stagingType = stagingType;
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
