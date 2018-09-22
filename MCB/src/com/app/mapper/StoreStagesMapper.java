package com.app.mapper;

import java.util.List;

import com.app.entity.StoreStages;
@SuppressWarnings("rawtypes")
public interface StoreStagesMapper {
	
	
	List selectStoreStagesListPage(StoreStages store);
	int getCount(StoreStages store);
	int insertStoreStages(StoreStages store);
	
	int insertStoreList(List list);
	 
	
	StoreStages getStoreStagesById(String storeStagesId);
	
	int updateStoreStages(StoreStages store);
	
	int deleteStoreStages(String storeStagesId);

	
	int deleteActShop(String []  storeStagesId);

	int deleteStoreStagesAll();
	List selectStoreNoList();

}
