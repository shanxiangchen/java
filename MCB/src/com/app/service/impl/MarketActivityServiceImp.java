package com.app.service.impl;

import java.util.List;

import com.app.entity.MarketActivity;
import com.app.entity.MarketPicture;
import com.app.mapper.ActivityAndCityGpsMapper;
import com.app.mapper.MarketActivityMapper;
import com.app.mapper.MarketPictureMapper;
import com.app.service.MarketActivityService;
@SuppressWarnings({"rawtypes","unchecked"})
public class MarketActivityServiceImp implements MarketActivityService{

	private MarketActivityMapper  marketActivityMapper;
    private ActivityAndCityGpsMapper activityAndCityGpsMapper;
    private MarketPictureMapper marketPictureMapper;
	public MarketActivityMapper getMarketActivityMapper() {
		return marketActivityMapper;
	}


	public void setMarketActivityMapper(MarketActivityMapper marketActivityMapper) {
		this.marketActivityMapper = marketActivityMapper;
	}

   
	public ActivityAndCityGpsMapper getActivityAndCityGpsMapper() {
		return activityAndCityGpsMapper;
	}


	public void setActivityAndCityGpsMapper(
			ActivityAndCityGpsMapper activityAndCityGpsMapper) {
		this.activityAndCityGpsMapper = activityAndCityGpsMapper;
	}

	public MarketPictureMapper getMarketPictureMapper() {
		return marketPictureMapper;
	}


	public void setMarketPictureMapper(MarketPictureMapper marketPictureMapper) {
		this.marketPictureMapper = marketPictureMapper;
	}


	@Override
	/**
	 * 查询营销活动
	 * @param pageBean 页数
	 * @return
	 */
	public List<MarketActivity> selectMarketActivityListPage(MarketActivity mar) {
		  
		List<MarketActivity> list=marketActivityMapper.selectMarketActivityListPage(mar);
		return list;
	}
	@Override
	public int insertMarketActivity(MarketActivity marketActivity) {
		 
		return marketActivityMapper.insertMarketActivity(marketActivity);
	}

	@Override
	public int insertMarketPicture(MarketPicture marketPicture) {
		 
		return marketActivityMapper.insertMarketPicture(marketPicture);
	}


	@Override
	public List selectMarketActivityById(String id) {
		 
		return marketActivityMapper.selectMarketActivityById(id);
	}


	@Override
	public int updateMarketActivityById(MarketActivity marketActivity) {
		 
		return marketActivityMapper.updateMarketActivityById(marketActivity);
	}


	@Override
	public int deleteMarketActivityById(String[] str) {
	 
		return marketActivityMapper.deleteMarketActivityById(str);
	}


	@Override
	public int updateMarketPictureById(MarketPicture picture) {
		 
		return marketActivityMapper.updateMarketPictureById(picture);
	}


	@Override
	public int updateMarketPictureByIds(MarketPicture picture) {
		 
		return marketActivityMapper.updateMarketPictureByIds(picture);
	}


	@Override
	/**
	 * 查询营销活动与商户关系
	 */
	public List<MarketActivity> selectActivityShopListPage(MarketActivity mar) {
		 
		List<MarketActivity> list=marketActivityMapper.selectActivityShopListPage(mar);
		return list;
	}


	@Override
	public List selectActInfoByActId(List list) {
		 
		return marketActivityMapper.selectActInfoByActId(list);
	}

  

	@Override
	public List selectAllActivityTypeId() {
		 
		return marketActivityMapper.selectAllActivityTypeId();
	}


	@Override
	public int updateMarketActivityDataById(String[] str) {
		 
		return marketActivityMapper.updateMarketActivityDataById(str);
	}


	@Override
	public int updateMarketActivityDataByIds(String[] str) {
		 
		return marketActivityMapper.updateMarketActivityDataByIds(str);
	}


	@Override
	public int updateReturnDataByIds(String[] str) {
		 
		return marketActivityMapper.updateReturnDataByIds(str);
	}


	@Override
	public List selectShopNameByShopId(String id) {
		 
		return marketActivityMapper.selectShopNameByShopId(id);
	}

 


	@Override
	public List selectOverdueMarketActivity() {
		 
		return marketActivityMapper.selectOverdueMarketActivity();
	}


	@Override
	public int updateMarketActivityStatusById(List list) {
		 
		return marketActivityMapper.updateMarketActivityStatusById(list);
	}


	@Override
	public int selectShowOrderCount(int num) {
		 
		return marketActivityMapper.selectShowOrderCount(num);
	}


	@Override
	public List selectAllCity() {
		 
		return marketActivityMapper.selectAllCity();
	}


	@Override
	public int insertActivityAndCityGps(List list) {
		 
		return activityAndCityGpsMapper.insertActivityAndCityGps(list);
	}


	@Override
	public int deleteActivityAndCityGps(String actId) {
		 
		return activityAndCityGpsMapper.deleteActivityAndCityGps(actId);
	}


	@Override
	public List selectActPicByPicId(String strs[]) {
		
		return marketPictureMapper.selectActPicByPicId(strs);
	}
	
	public String selectPicNameByid(String pictureId){
		
		return marketPictureMapper.selectPicNameByid(pictureId);
	}


	@Override
	public void deleteBankMarketPictureById(String[] str) {
		marketActivityMapper.deleteBankMarketPictureById(str);
		
	}
	 
}
