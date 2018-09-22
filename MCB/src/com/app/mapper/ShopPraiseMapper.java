package com.app.mapper;

import java.util.List;

import com.app.entity.ShopPraise;

public interface ShopPraiseMapper {
	public List<ShopPraise> findAllShopPraise(ShopPraise shoppraise);
	int insertShopPraise(ShopPraise shoppraise);
	int deleteShopPraise(String oddsshopid);
	
}
