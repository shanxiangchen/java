package com.app.entity;
/***
 * 分期计划实体类
 * create data 2015/11/14
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class StagingPlan{
	/***
	 * 分期计划ID
	 */
	private Integer planId;
	/***
	 * 分期计划编码
	 */
	private String  stagingPlanCode;
	/***
	 * 分期计划名称
	 */
	private String 	stagingPlanName;
	/***
	 * 费率
	 */
	private String 	stagingPlanRate;
	/***
	 * 状态编码(查看状态表)
	 */
	private String  stateCode;
	/***
	 * 备注
	 */
	private String  remark;
	/***
	 * 备用字段1
	 */
	private String  hold1;
	/***
	 * 备用字段2
	 */
	private String  hold2;
	
	private State state;
	 
	private Page page;
	
	 
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
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
	public String getStagingPlanRate() {
		return stagingPlanRate;
	}
	public void setStagingPlanRate(String stagingPlanRate) {
		this.stagingPlanRate = stagingPlanRate;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
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
