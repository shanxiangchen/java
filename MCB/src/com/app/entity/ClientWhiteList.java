package com.app.entity;
/**
 * 客户白名单
 * @author admin
 *
 */

public class ClientWhiteList {
	
   private int	 whiteListId ;/*白名单ID*/
   private String whiteListName ;/*客户名称*/
   private String	papersType;/*证件类型*/
   private String  papersNum;/*证件号码*/
   private String  activityType;/*活动类型*/
   private String 	activityNo ;/*活动编号*/
   private String  whiteListphone;/*手机号*/
   private String whiteListType;
   private String whiteImportId;
   public String getWhiteListType() {
	return whiteListType;
}
	public void setWhiteListType(String whiteListType) {
		this.whiteListType = whiteListType;
	}
	public String getWhiteImportId() {
		return whiteImportId;
	}
	public void setWhiteImportId(String whiteImportId) {
		this.whiteImportId = whiteImportId;
	}
	public String getPapersType() {
		return papersType;
	}
	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}
	public String getPapersNum() {
		return papersNum;
	}
	public void setPapersNum(String papersNum) {
		this.papersNum = papersNum;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public int getWhiteListId() {
		return whiteListId;
	}
	public void setWhiteListId(int whiteListId) {
		this.whiteListId = whiteListId;
	}
	public String getWhiteListName() {
		return whiteListName;
	}
	public void setWhiteListName(String whiteListName) {
		this.whiteListName = whiteListName;
	}
	public String getWhiteListphone() {
		return whiteListphone;
	}
	public void setWhiteListphone(String whiteListphone) {
		this.whiteListphone = whiteListphone;
	}
}
