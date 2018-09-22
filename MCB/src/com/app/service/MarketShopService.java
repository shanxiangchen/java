package com.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.entity.MarketActivityShop;

@SuppressWarnings("rawtypes")
public interface MarketShopService {
	/**
	 * 查询营销商户
	 * @param page 页数
	 * @return
	 */
	List<MarketActivityShop> selectMarketActivityShopListPage(MarketActivityShop shop);
 	int insertMarketActivityShop(MarketActivityShop shop);
 	
 	List selectMarketActivityShopByShopId(String shopId);
 	int updateMarketShopById(MarketActivityShop shop);
 	
 	List selectActIdByShopId(@Param("list")List list);
 	
 	int deleteMarketShopById(@Param("list")List list);
 	
 	List selectShopByLike(MarketActivityShop shop);
 	 
	public List<MarketActivityShop> selectActivityShopAllId(MarketActivityShop marketActivityShop);
}
