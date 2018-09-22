package com.app.service;


import java.util.List;

import com.app.entity.StoreStages;

public interface StoreStagesService {
	/**
	 * 查询分期商店信息
	 * @param page 页数
	 * @return
	 */
	List<StoreStages> selectStoreStagesListPage(StoreStages store);
	boolean savestorestages(StoreStages store);
	@SuppressWarnings("rawtypes")
	int insertStoreList(List list);
	StoreStages getStoreStagesById(String storeStagesId);
	void saverStoreStages(StoreStages storestages);
	int deleteStoreStages(String storeStagesId);

	int deleteActShop(String []  storeStagesId);
	int deleteStoreStagesAll();
	List<String> selectStoreNoList();
	void getJdbcCon(List<StoreStages> insertList);

}
