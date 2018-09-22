package com.app.entity;
/**
 * 代码信息
 * @author zhaolei 
 * @date 2016-3-7 下午3:04:18
 */
public class BankCodeInfo {
	
	 private String codeInfoId;//代码信息ID
	 private String codeTypeId;//代码类别ID
	 private String value;//下拉框值
	 private String content;//下拉框内容
	 private Integer sortNo;//排序号
	 private String remark;//备注
	 private String codeTypeName;//代码类别名称
	 private Page page;
	 
	 
	public String getCodeInfoId() {
		return codeInfoId;
	}
	public void setCodeInfoId(String codeInfoId) {
		this.codeInfoId = codeInfoId;
	}
	public String getCodeTypeId() {
		return codeTypeId;
	}
	public void setCodeTypeId(String codeTypeId) {
		this.codeTypeId = codeTypeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	 
	public String getCodeTypeName() {
		return codeTypeName;
	}
	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
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
