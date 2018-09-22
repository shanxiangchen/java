package com.app.entity;

public class ShopPhoto {
	/***
	 * 图片ID
	 */
	private String photoalbumid;
	/***
	 * 图片路径
	 */
	private String photourl;
	/***
	 * 图片名称
	 */
	private String photoname;
	/***
	 * 图片标识
	 */
	private Integer photoflag;
	private String oddsshopid;
	private Shop shop;
	private Type type;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getOddsshopid() {
		return oddsshopid;
	}
	public void setOddsshopid(String oddsshopid) {
		this.oddsshopid = oddsshopid;
	}
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	public String getPhotoalbumid() {
		return photoalbumid;
	}
	public void setPhotoalbumid(String photoalbumid) {
		this.photoalbumid = photoalbumid;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public Integer getPhotoflag() {
		return photoflag;
	}
	public void setPhotoflag(Integer photoflag) {
		this.photoflag = photoflag;
	}
	
}
