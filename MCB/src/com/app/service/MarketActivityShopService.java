package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.entity.MarketActivity;
@SuppressWarnings("rawtypes")
public interface MarketActivityShopService {
	/**
	 * 查询营销活动,id,标题,分页
	 * @param marketActivity
	 * @return
	 */
	public List<MarketActivity> selectActivityAllId(MarketActivity marketActivity);
	int updateActivityShopOfActivityId(MarketActivity marketActivity);
	int selectCountByShopIdAndActivityId(Map map);
	int selectCountByShopId(Map map);
}
