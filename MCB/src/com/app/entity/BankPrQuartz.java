package com.app.entity;
/**
 * 定时任务实体
 */
public class BankPrQuartz {
	
	private String quartzId;//主键ID
	private String quartzAbc;//字段英文名
	private String quartzChina;//字段中文名
	private String quartzType;//字段所属权益类型 所属权益类型 
	//1：境内机场接送 2：境外机场接送 3：国际航班快速通关 4：机场周边免费停车 5：1000万保险 6：航班延误 7：体检 
	//8：洁牙 9：绿色通道 10：专家预约 11：健身 12：网球 13：名车接送 14：十全十美体检 15：马术
	
	public String getQuartzId() {
		return quartzId;
	}
	public void setQuartzId(String quartzId) {
		this.quartzId = quartzId;
	}
	public String getQuartzAbc() {
		return quartzAbc;
	}
	public void setQuartzAbc(String quartzAbc) {
		this.quartzAbc = quartzAbc;
	}
	public String getQuartzChina() {
		return quartzChina;
	}
	public void setQuartzChina(String quartzChina) {
		this.quartzChina = quartzChina;
	}
	public String getQuartzType() {
		return quartzType;
	}
	public void setQuartzType(String quartzType) {
		this.quartzType = quartzType;
	}
	
	
	
	
	
	 
	
	
}
