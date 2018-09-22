package com.app.entity;
/**
 * 权益类型实体类
 * create date 2016/1/28
 * <br/> 
 * author shiguangting@tansun.com.cn
 *
 */
public class RightsType{
	
	/** 权益类型Id */
	private String rigthsTypeId;
	/** 权益类型编码 */
	private String rigthsTypeCode;
	/** 权益类型名称  */
	private String rigthsTypeName;
	/** 权益类型图片  */
	private String rigthsTypeUrl;
	/** 权益类型图片名称 */
	private String rigthsTypeUrlName;
	/** 权益类型图标 */
	private String rigthsTypeIconUrl;
	/** 权益类型图标名称 */
	private String rigthsTypeIconUrlNmae;
	/** 权益包编码 */
	private String rigthsPackagerk;
	/** 权益描述  */
	private String rigthsDescribe;
	private String rightsInDescribe;//权益描述内管取值
	/** 引入权益包映射实体类 */
	private RightsPackagerkMapping rightsPackagerkMapping;
	/** 服务明细按钮(0:否 1:是) */
	private String rightsDetailBtn;
	/** 权益预约按钮(0:否 1:是) */
	private String rightsOrderBtn;
	/** 服务明细按钮名称 */
	private String rightsDetailName;
	/** 权益预约按钮名称  */
	private String rightsOrderName;
	/** 计算方式 */
	private String calculation; 
	/** 本人已用(0:否 1:是) */
	private String rightsSelfUsed;
	/** 随行已用(0:否 1:是) */
	private String rightsHeelUsed;
	private String rightsTogetherNum;//随行次数最大值
	private String rightsMyNum;//本人次数最大值
	private String rightsBeforeDay;//服务预约提前天数
	private String rightsMaxBeforeDay;//服务最大预约提前天数
	private String rightsBeforeHour;//服务预约提前小时
	private Page page;
	public String getRigthsTypeId() {
		return rigthsTypeId;
	}
	public void setRigthsTypeId(String rigthsTypeId) {
		this.rigthsTypeId = rigthsTypeId;
	}
	public String getRigthsTypeCode() {
		return rigthsTypeCode;
	}
	public void setRigthsTypeCode(String rigthsTypeCode) {
		this.rigthsTypeCode = rigthsTypeCode;
	}
	public String getRigthsTypeName() {
		return rigthsTypeName;
	}
	public void setRigthsTypeName(String rigthsTypeName) {
		this.rigthsTypeName = rigthsTypeName;
	}
	public String getRigthsTypeUrl() {
		return rigthsTypeUrl;
	}
	public void setRigthsTypeUrl(String rigthsTypeUrl) {
		this.rigthsTypeUrl = rigthsTypeUrl;
	}
	public String getRigthsTypeUrlName() {
		return rigthsTypeUrlName;
	}
	public void setRigthsTypeUrlName(String rigthsTypeUrlName) {
		this.rigthsTypeUrlName = rigthsTypeUrlName;
	}
	public String getRigthsTypeIconUrl() {
		return rigthsTypeIconUrl;
	}
	public void setRigthsTypeIconUrl(String rigthsTypeIconUrl) {
		this.rigthsTypeIconUrl = rigthsTypeIconUrl;
	}
	public String getRigthsTypeIconUrlNmae() {
		return rigthsTypeIconUrlNmae;
	}
	public void setRigthsTypeIconUrlNmae(String rigthsTypeIconUrlNmae) {
		this.rigthsTypeIconUrlNmae = rigthsTypeIconUrlNmae;
	}
	public String getRigthsPackagerk() {
		return rigthsPackagerk;
	}
	public void setRigthsPackagerk(String rigthsPackagerk) {
		this.rigthsPackagerk = rigthsPackagerk;
	}
	public String getRigthsDescribe() {
		return rigthsDescribe;
	}
	public void setRigthsDescribe(String rigthsDescribe) {
		this.rigthsDescribe = rigthsDescribe;
	}
	public RightsPackagerkMapping getRightsPackagerkMapping() {
		return rightsPackagerkMapping;
	}
	public void setRightsPackagerkMapping(
			RightsPackagerkMapping rightsPackagerkMapping) {
		this.rightsPackagerkMapping = rightsPackagerkMapping;
	}
	public String getRightsDetailBtn() {
		return rightsDetailBtn;
	}
	public void setRightsDetailBtn(String rightsDetailBtn) {
		this.rightsDetailBtn = rightsDetailBtn;
	}
	public String getRightsOrderBtn() {
		return rightsOrderBtn;
	}
	public void setRightsOrderBtn(String rightsOrderBtn) {
		this.rightsOrderBtn = rightsOrderBtn;
	}
	public String getRightsDetailName() {
		return rightsDetailName;
	}
	public void setRightsDetailName(String rightsDetailName) {
		this.rightsDetailName = rightsDetailName;
	}
	public String getRightsOrderName() {
		return rightsOrderName;
	}
	public void setRightsOrderName(String rightsOrderName) {
		this.rightsOrderName = rightsOrderName;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public String getRightsSelfUsed() {
		return rightsSelfUsed;
	}
	public void setRightsSelfUsed(String rightsSelfUsed) {
		this.rightsSelfUsed = rightsSelfUsed;
	}
	public String getRightsHeelUsed() {
		return rightsHeelUsed;
	}
	public void setRightsHeelUsed(String rightsHeelUsed) {
		this.rightsHeelUsed = rightsHeelUsed;
	}
	public String getRightsInDescribe() {
		return rightsInDescribe;
	}
	public void setRightsInDescribe(String rightsInDescribe) {
		this.rightsInDescribe = rightsInDescribe;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getRightsTogetherNum() {
		return rightsTogetherNum;
	}
	public void setRightsTogetherNum(String rightsTogetherNum) {
		this.rightsTogetherNum = rightsTogetherNum;
	}
	public String getRightsMyNum() {
		return rightsMyNum;
	}
	public void setRightsMyNum(String rightsMyNum) {
		this.rightsMyNum = rightsMyNum;
	}
	public String getRightsBeforeDay() {
		return rightsBeforeDay;
	}
	public void setRightsBeforeDay(String rightsBeforeDay) {
		this.rightsBeforeDay = rightsBeforeDay;
	}
	public String getRightsMaxBeforeDay() {
		return rightsMaxBeforeDay;
	}
	public void setRightsMaxBeforeDay(String rightsMaxBeforeDay) {
		this.rightsMaxBeforeDay = rightsMaxBeforeDay;
	}
	public String getRightsBeforeHour() {
		return rightsBeforeHour;
	}
	public void setRightsBeforeHour(String rightsBeforeHour) {
		this.rightsBeforeHour = rightsBeforeHour;
	}	
}
