package com.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.app.entity.Shop;
import com.app.mapper.ShopMapper;
import com.app.service.ShopService;

public class ShopServiceImpl implements ShopService{
	private ShopMapper shopMapper;


	public Shop getShopById(String oddsshopid) {
		return shopMapper.getShopById(oddsshopid);
	}
 
	public int deleteShop(String oddsshopid) {
		return shopMapper.deleteShop(oddsshopid);
	}

	public ShopMapper getShopMapper() {
		return shopMapper;
	}

	public void setShopMapper(ShopMapper shopMapper) {
		this.shopMapper = shopMapper;
	}



	@Override
	public void saverShop(Shop shop) {
		shopMapper.updateShop(shop);
	}


	@Override
	public List<Shop> findAllShop(Shop shop) {
	 
		List<Shop> list=shopMapper.findAllShopPageList(shop);
		return list;
	}

	@Override
	public List<Shop> listAllshop() {
		return shopMapper.listAllshop();
	}

	 

	@Override
	public boolean saveshop(Shop shop) {
		int count = shopMapper.getCountByName(shop.getOddsshoporder());
		 
		if(count>0){
			return false;
		}else{
			int radom1 = (int)(Math.random()*10000000);
			Date date = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
			String datetime = fmt.format(date);
			String oddsShopId = radom1+datetime;
			shop.setOddsshopid(oddsShopId);
			shopMapper.insertShop(shop);
			return true;
	}
	}
 
	@Override
	public void updateShop(Shop shop) {
		 
		shopMapper.updateShops(shop);
	}
 
	@Override
	public List<Shop> odddsShopbyId(String oddsshopid) {
		 
		return shopMapper.odddsShopbyId(oddsshopid);
	}




	@Override
	public String oddsshopId(String oddsshopid) {
		 
		return shopMapper.oddsshopId(oddsshopid);
	}

 
		
	}

	





	

	


	

	



	

	


