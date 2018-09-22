package com.app.mapper;

import java.util.List;

import com.app.entity.Shop;


public interface ShopMapper {
	public List<Shop> findAllShopPageList(Shop shop);
	 
	Shop getShopById(String oddsshopid);
	int insertShop(Shop shop);
	
	int updateShop(Shop shop);
	
	int updateShops(Shop shop);
	int deleteShop(String oddsshopid);
	List<Shop> listAllshop();
	 
	int getCountByName(Integer oddsshoporder);
	/**
	 * 查询shop表中的图片名
	 * @param oddsshopid
	 * @return
	 */               
	public List<Shop> odddsShopbyId(String oddsshopid);
	
	/***
	 * 获取shop表中id
	 */
	public String oddsshopId(String oddsshopid);
}
