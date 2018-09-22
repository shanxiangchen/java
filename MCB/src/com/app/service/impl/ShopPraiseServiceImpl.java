package com.app.service.impl;

import java.util.List;

import com.app.entity.ShopPraise;
import com.app.mapper.ShopPraiseMapper;
import com.app.service.ShopPraiseService;

public class ShopPraiseServiceImpl implements ShopPraiseService {

	private ShopPraiseMapper shopPraiseMapper;

	
	public ShopPraiseMapper getShopPraiseMapper() {
		return shopPraiseMapper;
	}


	public void setShopPraiseMapper(ShopPraiseMapper shopPraiseMapper) {
		this.shopPraiseMapper = shopPraiseMapper;
	}


	@Override
	public List<ShopPraise> findAllShopPraise(ShopPraise shoppraise) {
		// TODO Auto-generated method stub
		return shopPraiseMapper.findAllShopPraise(shoppraise);
	}


	@Override
	public int insertShopPraise(ShopPraise shoppraise) {
		// TODO Auto-generated method stub
		return shopPraiseMapper.insertShopPraise(shoppraise);
	}

	 
	@Override
	public int deleteShopPraise(String oddsshopid) {
		// TODO Auto-generated method stub
		return shopPraiseMapper.deleteShopPraise(oddsshopid);
	}
	
}
