package com.app.service;
import java.util.List;

import com.app.entity.CityShop;

public interface CityShopService {
	/**
	 * 查询城市商户圈信息数据
	 * @param cityShop
	 * @return
	 */
	public List<CityShop> listPageCityShop (CityShop cityShop);
	
	/**
	 * 添加商户圈信息数据
	 * @param cityShop
	 */
	public boolean insertCityShop(CityShop cityShop);
	
	/**
	 * 删除城市商户圈信息数据
	 * @param shopRingId
	 */
	public void deleteById(int shopRingId);
	
	/**
	 * 查询城市化商户圈单个值信息
	 * @param shopRingId
	 * @return
	 */
	public CityShop getCityShopById(int shopRingId);
	
	/**
	 * 修改城市商户信息数据
	 * @param cityShop
	 */
	public void updateCityShop(CityShop cityShop);
	
	
	
	public List<CityShop> findAllshopring(CityShop cityShop);
	
	
	/***
	 * 查询商户
	 * @param cityno
	 * @return
	 */
	public List<CityShop> listCityShop(String cityno);
}
