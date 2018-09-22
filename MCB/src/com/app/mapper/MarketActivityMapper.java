package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.MarketActivity;
import com.app.entity.MarketPicture;
@SuppressWarnings("rawtypes")
public interface MarketActivityMapper {
	
	List selectMarketActivityListPage(MarketActivity mar);
	
	List selectActivityShopListPage(MarketActivity mar);
	int insertMarketActivity(MarketActivity marketActivity);
	int insertMarketPicture(MarketPicture marketPicture);
	
	List selectMarketActivityById(String id);
	int updateMarketActivityById(MarketActivity marketActivity);
	int deleteMarketActivityById(String[] str);
	public void deleteBankMarketPictureById(String[] str);
    int updateMarketPictureById(MarketPicture picture);
    int updateMarketPictureByIds(MarketPicture picture);
    
    /**
     * 营销活动,id,标题,分页
     * @param marketActivity
     * @return
     */
   public List<MarketActivity> selectActivityPageList(MarketActivity marketActivity);
    
   
	int selectCountByShopIdAndActivityId(Map map);
	
	int selectCountByShopId(Map map);
	int updateActivityShopOfActivityId(MarketActivity marketActivity);
	
	List selectActInfoByActId(List list);
	//String selectMarketPictureId();
	
	List selectAllActivityTypeId();
	int updateMarketActivityDataById(String[] str);
	int updateMarketActivityDataByIds(String[] str);
	int updateReturnDataByIds(String[] str);
	
	List selectShopNameByShopId(String id);
	//String selectMarketActId();
	
    List selectOverdueMarketActivity();
    
	int updateMarketActivityStatusById(List list);
    int selectShowOrderCount(int num);
    
	List selectAllCity();
   
}
