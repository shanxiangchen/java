package com.app.entity;
/**
 * 代码类别实体类 
 * @author zhaolei 
 * @date 2016-3-7 下午3:04:18
 */
public class BankCodeType {
	
	 private String codeTypeId;//代码类别ID
	 private String codeTypeName;//代码类别名称
	 private Integer sortNo;//排序号
	 private String remark;//备注
	 private Page page;
	 
	public String getCodeTypeId() {
		return codeTypeId;
	}
	public void setCodeTypeId(String codeTypeId) {
		this.codeTypeId = codeTypeId;
	}
	public String getCodeTypeName() {
		return codeTypeName;
	}
	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
