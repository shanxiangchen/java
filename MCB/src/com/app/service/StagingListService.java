package com.app.service;

import java.util.List;

import com.app.entity.StagingList;

public interface StagingListService {

	public List<StagingList> stagingListPageList(StagingList lista);
	
	
	
	public int deleteStagingList(String[] strs);
	public int StagingListbyid();
	
	public void insertStagingList(StagingList stagingList);
	
	public void getJdbcCon(List<StagingList> insertList);
	
	public int selectCity();
	
	public int getStangeListNum(StagingList stagingList);
//	String sqlstagingList(Map<String, String> mapStr);
	
	/**
	 * 删除全部快捷分期数据，请慎用
	 * @return
	 */
	public int deleteAllStaging();
	
	/**
	 * 批量导入文件
	 * @param fileName 文件名
	 * @param filePath 文件路径
	 * @param fileCount 文件总行数
	 */
	public void batchImportData(String fileName,String filePath,Integer fileCount) throws Exception;
}
