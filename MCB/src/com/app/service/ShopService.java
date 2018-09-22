package com.app.service;

import java.util.List;

import com.app.entity.Shop;

public interface ShopService {
	public List<Shop> findAllShop(Shop shop);
 
	Shop  getShopById(String oddsshopid);
 
	void saverShop(Shop shop);
	
	void updateShop(Shop shop);
	
	int deleteShop(String oddsshopid);
	List<Shop> listAllshop();
	
	
	boolean saveshop(Shop shop);
 
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
