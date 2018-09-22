package com.app.entity;

import java.util.List;

/**
 * 功能模块名称实体类
 * @author admin
 *
 */
public class ModuleFunctionName {
	private  String moduleFunctionNameId;
	private  String moduleFunctionName;
	private  int moduleFunctionNo;
	private List<Advert> advert;
	private Advert advertOne;
	private String advertTitle;
	private String activityTitle;
	private String shopName;
	private String linkName;
	private Page page; 
	private String order;
	 
	
	public ModuleFunctionName(){}
	 

	public String getAdvertTitle() {
		return advertTitle;
	}
	public void setAdvertTitle(String advertTitle) {
		this.advertTitle = advertTitle;
	}
	public Advert getAdvertOne() {
		return advertOne;
	}
	public void setAdvertOne(Advert advertOne) {
		this.advertOne = advertOne;
	}
	public List<Advert> getAdvert() {
		return advert;
	}
	public void setAdvert(List<Advert> advert) {
		this.advert = advert;
	}
	public String getModuleFunctionNameId() {
		return moduleFunctionNameId;
	}
	public void setModuleFunctionNameId(String moduleFunctionNameId) {
		this.moduleFunctionNameId = moduleFunctionNameId;
	}
	public String getModuleFunctionName() {
		return moduleFunctionName;
	}
	public void setModuleFunctionName(String moduleFunctionName) {
		this.moduleFunctionName = moduleFunctionName;
	}
	public int getModuleFunctionNo() {
		return moduleFunctionNo;
	}
	public void setModuleFunctionNo(int moduleFunctionNo) {
		this.moduleFunctionNo = moduleFunctionNo;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
