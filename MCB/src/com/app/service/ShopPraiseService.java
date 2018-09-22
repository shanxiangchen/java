package com.app.service;

import java.util.List;

import com.app.entity.ShopPraise;

public interface ShopPraiseService {
	public List<ShopPraise> findAllShopPraise(ShopPraise shoppraise);
	int insertShopPraise(ShopPraise shoppraise);
	int deleteShopPraise(String oddsshopid);

}
