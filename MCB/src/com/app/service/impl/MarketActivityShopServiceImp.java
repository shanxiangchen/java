package com.app.service.impl;

import java.util.List;
import java.util.Map;

import com.app.entity.MarketActivity;
import com.app.mapper.MarketActivityMapper;
import com.app.service.MarketActivityShopService;
@SuppressWarnings({"rawtypes"})
public class MarketActivityShopServiceImp implements MarketActivityShopService{

	private MarketActivityMapper marketActivityMapper;
 

	public MarketActivityMapper getMarketActivityMapper() {
		return marketActivityMapper;
	}

	public void setMarketActivityMapper(MarketActivityMapper marketActivityMapper) {
		this.marketActivityMapper = marketActivityMapper;
	}


	

	@Override
	public List<MarketActivity> selectActivityAllId(
			MarketActivity marketActivity) {
		 
		List<MarketActivity> list=marketActivityMapper.selectActivityPageList(marketActivity);
		return list;
	}

	@Override
	public int updateActivityShopOfActivityId(MarketActivity marketActivity) {
		 
		return marketActivityMapper.updateActivityShopOfActivityId(marketActivity);
	}
	
	@Override
	public int selectCountByShopIdAndActivityId(Map map) {
		 
		return marketActivityMapper.selectCountByShopIdAndActivityId(map);
	}

	@Override
	public int selectCountByShopId(Map map) {
		 
		return marketActivityMapper.selectCountByShopId(map);
	}

 

	 
}
