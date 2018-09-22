package com.app.entity;
 
public class BankLohasAllocation {
	
	 private String lohasId;//主键ID
	 private String lohasImgUrl;//图片链接地址
	 private String lohasImgName;//图片名称
	 private String lohasImgWhere;//图片位置
	 private String linkType;//链接类型
	 private String linkTypes;//链接类型拼串
	 private String lohasOutLink;//链接
	 private String lohasOutLinks;//链接拼接串
	 private String lohasTitleName;//乐活广告标题
	 private int lohasSortNo;//乐活排序号
	 private String lohasShowType;//乐活样式
	 private String lohasShowNo;//乐活展示顺序
	 private String lohasParams;//连接参数拼接串
	 private String lohasParam;//连接
	 private String linkNos;
	 private String linkNo;
	 private String isAddPhone;//是否添加电话参数
	 private String isAddPhones;//是否添加电话参数拼串
	 private Page page;
	 
	 
	public String getLohasId() {
		return lohasId;
	}
	public void setLohasId(String lohasId) {
		this.lohasId = lohasId;
	}
	public String getLohasImgUrl() {
		return lohasImgUrl;
	}
	public void setLohasImgUrl(String lohasImgUrl) {
		this.lohasImgUrl = lohasImgUrl;
	}
	public String getLohasImgName() {
		return lohasImgName;
	}
	public void setLohasImgName(String lohasImgName) {
		this.lohasImgName = lohasImgName;
	}
	public String getLohasImgWhere() {
		return lohasImgWhere;
	}
	public void setLohasImgWhere(String lohasImgWhere) {
		this.lohasImgWhere = lohasImgWhere;
	}
	public String getLohasParams() {
		return lohasParams;
	}
	public void setLohasParams(String lohasParams) {
		this.lohasParams = lohasParams;
	}
	public String getLinkNos() {
		return linkNos;
	}
	public void setLinkNos(String linkNos) {
		this.linkNos = linkNos;
	}
	public String getLinkNo() {
		return linkNo;
	}
	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}
	public String getLohasParam() {
		return lohasParam;
	}
	public void setLohasParam(String lohasParam) {
		this.lohasParam = lohasParam;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkTypes() {
		return linkTypes;
	}
	public void setLinkTypes(String linkTypes) {
		this.linkTypes = linkTypes;
	}
	
	public String getLohasOutLink() {
		return lohasOutLink;
	}
	public void setLohasOutLink(String lohasOutLink) {
		this.lohasOutLink = lohasOutLink;
	}
	 
	public String getLohasTitleName() {
		return lohasTitleName;
	}
	public void setLohasTitleName(String lohasTitleName) {
		this.lohasTitleName = lohasTitleName;
	}
	public String getLohasOutLinks() {
		return lohasOutLinks;
	}
	public void setLohasOutLinks(String lohasOutLinks) {
		this.lohasOutLinks = lohasOutLinks;
	}
	 
	public String getLohasShowType() {
		return lohasShowType;
	}
	public void setLohasShowType(String lohasShowType) {
		this.lohasShowType = lohasShowType;
	}
	public int getLohasSortNo() {
		return lohasSortNo;
	}
	public void setLohasSortNo(int lohasSortNo) {
		this.lohasSortNo = lohasSortNo;
	}
	public String getLohasShowNo() {
		return lohasShowNo;
	}
	public void setLohasShowNo(String lohasShowNo) {
		this.lohasShowNo = lohasShowNo;
	}
	
	public String getIsAddPhone() {
		return isAddPhone;
	}
	public void setIsAddPhone(String isAddPhone) {
		this.isAddPhone = isAddPhone;
	}
	public String getIsAddPhones() {
		return isAddPhones;
	}
	public void setIsAddPhones(String isAddPhones) {
		this.isAddPhones = isAddPhones;
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
  
