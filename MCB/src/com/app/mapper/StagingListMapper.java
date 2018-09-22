package com.app.mapper;

import java.util.List;

import com.app.entity.StagingList;

public interface StagingListMapper {

	/**
	 * 查询分页
	 * @param Beauideal
	 * @return
	 */
	
	public List<StagingList> stagingListPageList(StagingList lista);
	/**
	 * 根据ID查询
	 * @param beauidealExperienceId
	 * @return
	 */
	int getStagingListbyid();
	
	/**
	 * 保存
	 * @return 
	 */
	public void insertStagingList(String speedyList);
	
	
	/**
	 * 删除
	 */
	public int delectStagingList(String[] strs);
	
	public int selectCity();
	
	public int getStangeListNum(StagingList stagingList);
//	String sqlstagingList(Map<String, String> mapStr);
	
	public int deleteAllStaging();
}
