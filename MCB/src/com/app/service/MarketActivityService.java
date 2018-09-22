package com.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.entity.MarketActivity;
import com.app.entity.MarketPicture;
@SuppressWarnings("rawtypes")
public interface MarketActivityService {
	/**
	 * 查询营销活动
	 * @param page 页数
	 * @return
	 */
	List<MarketActivity>  selectMarketActivityListPage(MarketActivity mar);
	/**
	 * 查询营销活动与商户
	 * @param page 页数
	 * @return
	 */
	List<MarketActivity> selectActivityShopListPage(MarketActivity mar);
	int insertMarketActivity(MarketActivity marketActivity);
	int insertMarketPicture(MarketPicture marketPicture);
	List selectMarketActivityById(String id);
	int updateMarketActivityById(MarketActivity marketActivity);
	int deleteMarketActivityById(String str[]);
	int updateMarketPictureById(MarketPicture picture);
	int updateMarketPictureByIds(MarketPicture picture);
	List selectActInfoByActId(@Param("list")List list);
	 
	List selectAllActivityTypeId();
	int updateMarketActivityDataById(String str[]);
	int updateMarketActivityDataByIds(String str[]);
	int updateReturnDataByIds(String str[]);
	List selectShopNameByShopId(String id);
	 
	List selectOverdueMarketActivity();
	int updateMarketActivityStatusById(@Param("list")List list);
	int selectShowOrderCount(int num);
	List selectAllCity();
	int insertActivityAndCityGps(@Param("list")List list);
	int deleteActivityAndCityGps(String actId);
	/**
	 * 根据图片ID查询图片名称
	 * @param strs
	 * @return
	 */
	List selectActPicByPicId(String strs[]);
	String selectPicNameByid(String pictureId);
	public void deleteBankMarketPictureById(String[] str);
}
