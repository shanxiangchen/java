package com.app.entity;

public class Type {

	private  String oddsshoptypeid;  //特惠商户类型
	private  String  oddsshoptype;     //特惠商户类型
	private ShopPhoto shopphoto;
	private Shop shop;
	private Page page;
	public Type(){}
	 
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public ShopPhoto getShopphoto() {
		return shopphoto;
	}
	public void setShopphoto(ShopPhoto shopphoto) {
		this.shopphoto = shopphoto;
	}
	
	
	public String getOddsshoptypeid() {
		return oddsshoptypeid;
	}
	public void setOddsshoptypeid(String oddsshoptypeid) {
		this.oddsshoptypeid = oddsshoptypeid;
	}
	public String getOddsshoptype() {
		return oddsshoptype;
	}
	public void setOddsshoptype(String oddsshoptype) {
		this.oddsshoptype = oddsshoptype;
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
