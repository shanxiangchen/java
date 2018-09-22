package com.app.entity;
/**
 * 消息模板
 * create date 2016/4/14
 * <br/>
 * @author shiguangting@tansun.cn.com
 *
 */
public class BankMessageTemplate {
	/** 消息模板id*/
	private String messageTemplateId;
	/** 广告图片*/
	private String advertisingImg;
	/** 广告图片名称 */
	private String advertisingImgName;
	/** 文字内容*/
	private String writtenContent;
	/** 内链去看看*/
	private String inGoAndSeeLink;
	/** 外链去看看*/
	private String outGoAndSeeLink;
	/** 具体消息类型*/
	private String smallTypeNo;
	/** 消息标题*/
	private String messageTitle;
	/** 消息类型编码 */
	private String typeNo;
	/** 链接方式*/
	private String linkWay;
	/**备注*/
	private String remark;
	
	private Page page;
	
	public String getMessageTemplateId() {
		return messageTemplateId;
	}
	public void setMessageTemplateId(String messageTemplateId) {
		this.messageTemplateId = messageTemplateId;
	}
	public String getAdvertisingImg() {
		return advertisingImg;
	}
	public void setAdvertisingImg(String advertisingImg) {
		this.advertisingImg = advertisingImg;
	}
	public String getAdvertisingImgName() {
		return advertisingImgName;
	}
	public void setAdvertisingImgName(String advertisingImgName) {
		this.advertisingImgName = advertisingImgName;
	}
	public String getWrittenContent() {
		return writtenContent;
	}
	public void setWrittenContent(String writtenContent) {
		this.writtenContent = writtenContent;
	}
	public String getInGoAndSeeLink() {
		return inGoAndSeeLink;
	}
	public void setInGoAndSeeLink(String inGoAndSeeLink) {
		this.inGoAndSeeLink = inGoAndSeeLink;
	}
	public String getOutGoAndSeeLink() {
		return outGoAndSeeLink;
	}
	public void setOutGoAndSeeLink(String outGoAndSeeLink) {
		this.outGoAndSeeLink = outGoAndSeeLink;
	}
	public String getSmallTypeNo() {
		return smallTypeNo;
	}
	public void setSmallTypeNo(String smallTypeNo) {
		this.smallTypeNo = smallTypeNo;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getLinkWay() {
		return linkWay;
	}
	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
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
    
