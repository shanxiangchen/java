package com.app.entity;
/**
 * 静态页信息
 * @author zhaolei 
 * @date 2016-3-14 上午11:10:46
 */
public class ImportHtml {
	
	 private String htmlId;//主键ID
	 private String htmlUrl;//访问路径
	 private String htmlDate;//导入日期
	 private String htmlUser;//上传用户
	 private String htmlName;//文件名称
	 private Page page;
		 
	 
	public String getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public String getHtmlDate() {
		return htmlDate;
	}
	public void setHtmlDate(String htmlDate) {
		this.htmlDate = htmlDate;
	}
	public String getHtmlUser() {
		return htmlUser;
	}
	public void setHtmlUser(String htmlUser) {
		this.htmlUser = htmlUser;
	}
	public String getHtmlName() {
		return htmlName;
	}
	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
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
