package com.app.entity;
/**
 *错误码实体类
 *create date 2015/8/25
 *<br/>
 * @author shigaungting@tansun.com.cn
 *
 */
public class ErrorCode {
	/**
	 * 错误码id--自动增长
	 */
	private Integer errorCodeId;
	/**
	 * 错误码类别
	 */
	private String errorCodeType;
	/**
	 * 错误编码
	 */
	private String errorEncoding;
	/**
	 * 错误码描述
	 */
	private String errorDescribe;
	private Page page;
	 
	public ErrorCode(){}
	
	 
	public Integer getErrorCodeId() {
		return errorCodeId;
	}
	
	public void setErrorCodeId(Integer errorCodeId) {
		this.errorCodeId = errorCodeId;
	}
	
	public String getErrorCodeType() {
		return errorCodeType;
	}
	
	public void setErrorCodeType(String errorCodeType) {
		this.errorCodeType = errorCodeType;
	}
	
	public String getErrorEncoding() {
		return errorEncoding;
	}
	
	public void setErrorEncoding(String errorEncoding) {
		this.errorEncoding = errorEncoding;
	}
	
	public String getErrorDescribe() {
		return errorDescribe;
	}
	
	public void setErrorDescribe(String errorDescribe) {
		this.errorDescribe = errorDescribe;
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
