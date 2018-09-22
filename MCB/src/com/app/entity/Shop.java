package com.app.entity;




public class Shop  {
	/***
	 * 特惠商户id
	 */
	private String   oddsshopid;
	/***
	 * 商户名称
	 */
	private String   oddsshopname;
	/***
	 * 商户地址
	 */
	private String   oddsshopaddress;
	/***
	 * 商户经度
	 */
	private String	 oddsshoplongitude; 
	/***
	 * 商户纬度
	 */
	private String  oddsshoplatitude;
	/**
	 * 联系方式
	 */
	private String   oddsshopphone; 
	/***
	 * 优惠形式
	 */
	private String   oddsshopprivilege;
	/***
	 * 商户所在的城市 
	 */
	private String   oddsshopcity; 
	/***
	 * 商户展示顺序
	 */
	private Integer  oddsshoporder;	
	/**
	 * 商圈 
	 */
	private String   oddsshopring; 			
	/**
	 * 优惠活动
	 */
	private String   oddsshopprivilegeact;	
	/***
	 * 状态 1营业 2停止营业
	 */
	private Integer  oddsshopstatus;  
	/***
	 * 商户图片地址
	 */
	private String   oddsshoppictureurl; 
	/***
	 * 商户图片名称
	 */
	private String   oddsshoppicturename;  
	/***
	 *  特惠商户类型id
	 */
	private String   oddsshoptypeid; 
	/***
	 * 特惠商户类型
	 */
	private String   oddsshoptype;
	/***
	 * 人均消费
	 */
	private String   oddsshopconsumption;  
	/***
	 * 分店名称  
	 */
	private String   oddsshopsubbranchname; 
	/***
	 * 优惠内容  
	 */
	private String   oddsshopfavorableinfo;  
	/***
	 *  截止日期
	 */
	private String   oddsshopendtime;
	private String oddsshopbegintime;
	private String oddsshoppublictime;
	private String   oddsshopbriefintroduction;
	private  Integer photoalbumid;
	private Page page;
	public Shop(){}
	private Gprs gprs;
	


	public Gprs getGprs() {
		return gprs;
	}


	public void setGprs(Gprs gprs) {
		this.gprs = gprs;
	}
 
	public Integer getPhotoalbumid() {
		return photoalbumid;
	}
	public void setPhotoalbumid(Integer photoalbumid) {
		this.photoalbumid = photoalbumid;
	}

	private ShopPraise shoppraise;
	public ShopPraise getShoppraise() {
		return shoppraise;
	}


	public void setShoppraise(ShopPraise shoppraise) {
		this.shoppraise = shoppraise;
	}

	private Type type;
	
	public ShopPhoto getShopphoto() {
		return shopphoto;
	}
	public void setShopphoto(ShopPhoto shopphoto) {
		this.shopphoto = shopphoto;
	}

	private ShopPhoto shopphoto;
	
//	private List<ShopPhoto> shopphoto;
	
	public String getOddsshopbriefintroduction() {
		return oddsshopbriefintroduction;
	}
	public void setOddsshopbriefintroduction(String oddsshopbriefintroduction) {
		this.oddsshopbriefintroduction = oddsshopbriefintroduction;
	}
	
	
	public String getOddsshoppicturename() {
		return oddsshoppicturename;
	}


	public void setOddsshoppicturename(String oddsshoppicturename) {
		this.oddsshoppicturename = oddsshoppicturename;
	}
	
	public String getOddsshoptype() {
		return oddsshoptype;
	}
	public void setOddsshoptype(String oddsshoptype) {
		this.oddsshoptype = oddsshoptype;
	}
	public String getOddsshopconsumption() {
		return oddsshopconsumption;
	}
	public void setOddsshopconsumption(String oddsshopconsumption) {
		this.oddsshopconsumption = oddsshopconsumption;
	}
	public String getOddsshopsubbranchname() {
		return oddsshopsubbranchname;
	}
	public void setOddsshopsubbranchname(String oddsshopsubbranchname) {
		this.oddsshopsubbranchname = oddsshopsubbranchname;
	}
	public String getOddsshopfavorableinfo() {
		return oddsshopfavorableinfo;
	}
	public void setOddsshopfavorableinfo(String oddsshopfavorableinfo) {
		this.oddsshopfavorableinfo = oddsshopfavorableinfo;
	}
	public String getOddsshopendtime() {
		return oddsshopendtime;
	}
	public void setOddsshopendtime(String oddsshopendtime) {
		this.oddsshopendtime = oddsshopendtime;
	}
	public String getOddsshopbegintime() {
		return oddsshopbegintime;
	}
	public void setOddsshopbegintime(String oddsshopbegintime) {
		this.oddsshopbegintime = oddsshopbegintime;
	}
	
	public String getOddsshoppublictime() {
		return oddsshoppublictime;
	}


	public void setOddsshoppublictime(String oddsshoppublictime) {
		this.oddsshoppublictime = oddsshoppublictime;
	}


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
	public String getOddsshopname() {
		return oddsshopname;
	}
	public void setOddsshopname(String oddsshopname) {
		this.oddsshopname = oddsshopname;
	}
	public String getOddsshopaddress() {
		return oddsshopaddress;
	}
	public void setOddsshopaddress(String oddsshopaddress) {
		this.oddsshopaddress = oddsshopaddress;
	}
	/*public Integer getOddsshoplongitude() {
		return oddsshoplongitude;
	}
	public void setOddsshoplongitude(Integer oddsshoplongitude) {
		this.oddsshoplongitude = oddsshoplongitude;
	}
	public Integer getOddsshoplatitude() {
		return oddsshoplatitude;
	}
	public void setOddsshoplatitude(Integer oddsshoplatitude) {
		this.oddsshoplatitude = oddsshoplatitude;
	}*/
	
	public String getOddsshopphone() {
		return oddsshopphone;
	}
	public String getOddsshoplongitude() {
		return oddsshoplongitude;
	}


	public void setOddsshoplongitude(String oddsshoplongitude) {
		this.oddsshoplongitude = oddsshoplongitude;
	}


	public String getOddsshoplatitude() {
		return oddsshoplatitude;
	}


	public void setOddsshoplatitude(String oddsshoplatitude) {
		this.oddsshoplatitude = oddsshoplatitude;
	}


	public void setOddsshopphone(String oddsshopphone) {
		this.oddsshopphone = oddsshopphone;
	}
	public String getOddsshopprivilege() {
		return oddsshopprivilege;
	}
	public void setOddsshopprivilege(String oddsshopprivilege) {
		this.oddsshopprivilege = oddsshopprivilege;
	}
	public String getOddsshopcity() {
		return oddsshopcity;
	}
	public void setOddsshopcity(String oddsshopcity) {
		this.oddsshopcity = oddsshopcity;
	}
	public Integer getOddsshoporder() {
		return oddsshoporder;
	}
	public void setOddsshoporder(Integer oddsshoporder) {
		this.oddsshoporder = oddsshoporder;
	}
	public String getOddsshopring() {
		return oddsshopring;
	}
	public void setOddsshopring(String oddsshopring) {
		this.oddsshopring = oddsshopring;
	}
	public String getOddsshopprivilegeact() {
		return oddsshopprivilegeact;
	}
	public void setOddsshopprivilegeact(String oddsshopprivilegeact) {
		this.oddsshopprivilegeact = oddsshopprivilegeact;
	}
	public Integer getOddsshopstatus() {
		return oddsshopstatus;
	}
	public void setOddsshopstatus(Integer oddsshopstatus) {
		this.oddsshopstatus = oddsshopstatus;
	}
	public String getOddsshoptypeid() {
		return oddsshoptypeid;
	}


	public void setOddsshoptypeid(String oddsshoptypeid) {
		this.oddsshoptypeid = oddsshoptypeid;
	}


	public String getOddsshoppictureurl() {
		return oddsshoppictureurl;
	}
	public void setOddsshoppictureurl(String oddsshoppictureurl) {
		this.oddsshoppictureurl = oddsshoppictureurl;
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






