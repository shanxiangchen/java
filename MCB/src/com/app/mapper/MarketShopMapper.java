package com.app.mapper;

import java.util.List;

import com.app.entity.MarketActivityShop;
@SuppressWarnings("rawtypes")
public interface MarketShopMapper {
	
	List selectMarketActivityShopListPage(MarketActivityShop shop);
	int insertMarketActivityShop(MarketActivityShop shop);
	
	List selectMarketActivityShopByShopId(String shopId);
	int updateMarketShopById(MarketActivityShop shop);
	
	List selectActIdByShopId(List list);
	
	int deleteMarketShopById(List list);
	
	List selectShopByLike(MarketActivityShop shop);
	 
   public List<MarketActivityShop> selectActivityShopPageList(MarketActivityShop marketActivityShop);
    
}
