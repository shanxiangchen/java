package com.app.entity;
/**
 * 客户白名单
 * @author admin
 *
 */

public class ClientWhiteImport {
	
	private String whiteImportId;//主键ID
	private String whiteImportOldname;//原文件名
	private String whiteImportNewname;//现文件名
	private String whiteImportUrl;//存储路径
	private String whiteImportSize;//文件大小
	private String whiteImportDate;//导入日期
	private String whiteImportStatus;//处理进度标识 1 ：处理中 2：完成
	private String whiteImportUser;//上传用户
	private String whiteSaveNo;//文件临时存储编码
	private String loginfo;//插入日志文件
	private Page page;
	 
	
	public ClientWhiteImport(){}
	
	
	public ClientWhiteImport(String whiteImportId,String whiteImportOldname,
			String whiteImportNewname, String whiteImportUrl,
			String whiteImportSize, String whiteImportDate,
			String whiteImportStatus, String whiteImportUser) {
		this.whiteImportId=whiteImportId;
		this.whiteImportOldname = whiteImportOldname;
		this.whiteImportNewname = whiteImportNewname;
		this.whiteImportUrl = whiteImportUrl;
		this.whiteImportSize = whiteImportSize;
		this.whiteImportDate = whiteImportDate;
		this.whiteImportStatus = whiteImportStatus;
		this.whiteImportUser = whiteImportUser;
	}
	public ClientWhiteImport(String whiteImportOldname,
			String whiteImportNewname, String whiteImportUrl,
			String whiteImportSize, String whiteImportDate,
			String whiteImportStatus, String whiteImportUser) {
		this.whiteImportOldname = whiteImportOldname;
		this.whiteImportNewname = whiteImportNewname;
		this.whiteImportUrl = whiteImportUrl;
		this.whiteImportSize = whiteImportSize;
		this.whiteImportDate = whiteImportDate;
		this.whiteImportStatus = whiteImportStatus;
		this.whiteImportUser = whiteImportUser;
	}


	public String getWhiteImportId() {
		return whiteImportId;
	}
	public void setWhiteImportId(String whiteImportId) {
		this.whiteImportId = whiteImportId;
	}
	public String getWhiteImportOldname() {
		return whiteImportOldname;
	}
	public void setWhiteImportOldname(String whiteImportOldname) {
		this.whiteImportOldname = whiteImportOldname;
	}
	public String getLoginfo() {
		return loginfo;
	}
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}
	public String getWhiteImportNewname() {
		return whiteImportNewname;
	}
	public void setWhiteImportNewname(String whiteImportNewname) {
		this.whiteImportNewname = whiteImportNewname;
	}
	public String getWhiteImportUrl() {
		return whiteImportUrl;
	}
	public void setWhiteImportUrl(String whiteImportUrl) {
		this.whiteImportUrl = whiteImportUrl;
	}
	public String getWhiteImportSize() {
		return whiteImportSize;
	}
	public void setWhiteImportSize(String whiteImportSize) {
		this.whiteImportSize = whiteImportSize;
	}
	public String getWhiteImportDate() {
		return whiteImportDate;
	}
	public void setWhiteImportDate(String whiteImportDate) {
		this.whiteImportDate = whiteImportDate;
	}
	public String getWhiteImportStatus() {
		return whiteImportStatus;
	}
	public void setWhiteImportStatus(String whiteImportStatus) {
		this.whiteImportStatus = whiteImportStatus;
	}
	public String getWhiteImportUser() {
		return whiteImportUser;
	}
	public void setWhiteImportUser(String whiteImportUser) {
		this.whiteImportUser = whiteImportUser;
	}
	public String getWhiteSaveNo() {
		return whiteSaveNo;
	}
	public void setWhiteSaveNo(String whiteSaveNo) {
		this.whiteSaveNo = whiteSaveNo;
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
