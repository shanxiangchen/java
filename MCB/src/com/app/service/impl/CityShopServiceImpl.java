package com.app.service.impl;

import java.util.List;

import com.app.entity.CityShop;
import com.app.mapper.CityShopMapper;
import com.app.service.CityShopService;

public class CityShopServiceImpl implements CityShopService{
	private CityShopMapper cityShopMapper;

	public CityShopMapper getCityShopMapper() {
		return cityShopMapper;
	}

	public void setCityShopMapper(CityShopMapper cityShopMapper) {
		this.cityShopMapper = cityShopMapper;
	}

	@Override
	public List<CityShop> listPageCityShop(CityShop cityShop) {
		 
		List<CityShop> list=cityShopMapper.listPageCityShop(cityShop);
		return list;
	}

	@Override
	public boolean insertCityShop(CityShop cityShop) {
		 
		int count=cityShopMapper.getCountByName(cityShop.getShopRingNo());
		if(count>0)
		{
			return false;
		}else{
			cityShopMapper.insertCityShop(cityShop);
			return true;
		}
	}

	@Override
	public void deleteById(int shopRingId) {
		// TODO Auto-generated method stub
		cityShopMapper.detDeleteById(shopRingId);
	}

	@Override
	public CityShop getCityShopById(int shopRingId) {
		// TODO Auto-generated method stub
		return cityShopMapper.getCityShopById(shopRingId);
	}

	@Override
	public void updateCityShop(CityShop cityShop) {
		// TODO Auto-generated method stub
		cityShopMapper.getupdateCityShop(cityShop);
	}

	@Override
	public List<CityShop> findAllshopring(CityShop cityShop) {
		// TODO Auto-generated method stub
		return cityShopMapper.findAllshopring(cityShop);
	}

	@Override
	public List<CityShop> listCityShop(String cityno) {
		// TODO Auto-generated method stub
		return cityShopMapper.listCityShop(cityno);
	}

	
	
}
