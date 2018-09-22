package com.app.service.impl;

import java.util.List;

import com.app.entity.MarketActivityShop;
import com.app.mapper.MarketShopMapper;
import com.app.service.MarketShopService;
@SuppressWarnings({"rawtypes","unchecked"})
public class MarketShopServiceImp implements MarketShopService{

	private MarketShopMapper  marketShopMapper;

	public MarketShopMapper getMarketShopMapper() {
		return marketShopMapper;
	}

	public void setMarketShopMapper(MarketShopMapper marketShopMapper) {
		this.marketShopMapper = marketShopMapper;
	}

	@Override
	public List<MarketActivityShop> selectMarketActivityShopListPage(MarketActivityShop shop) {
		 
		List<MarketActivityShop> list=marketShopMapper.selectMarketActivityShopListPage(shop);
		return list;
	}

	@Override
	public int insertMarketActivityShop(MarketActivityShop shop) {
		 
		return marketShopMapper.insertMarketActivityShop(shop);
	}

	@Override
	public int updateMarketShopById(MarketActivityShop shop) {
		 
		return marketShopMapper.updateMarketShopById(shop);
	}

	@Override
	public List selectMarketActivityShopByShopId(String shopId) {
		 
		return marketShopMapper.selectMarketActivityShopByShopId(shopId);
	}

	@Override
	public List selectActIdByShopId(List list) {
		 
		return marketShopMapper.selectActIdByShopId(list);
	}

	@Override
	public int deleteMarketShopById(List list) {
		 
		return marketShopMapper.deleteMarketShopById(list);
	}

	@Override
	public List selectShopByLike(MarketActivityShop shop) {
		 
		return marketShopMapper.selectShopByLike(shop);
	}

	@Override
	public List<MarketActivityShop> selectActivityShopAllId(
			MarketActivityShop marketActivityShop) {
		  
		List<MarketActivityShop> list=marketShopMapper.selectActivityShopPageList(marketActivityShop);
		return list;
	}
	
}
