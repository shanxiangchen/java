package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.ClientWhiteImport;
import com.app.entity.ClientWhiteList;
@SuppressWarnings("rawtypes")
public interface ClientWhiteListMapper {
	
	List selectWhiteImportListPage(ClientWhiteImport whiteImport);
	
	int getCount(ClientWhiteImport whiteImport);
	
	int insertbatchClientWhiteList(List list);
	
	int deleteAllClientWhiteList(String activityNo) ;
	
	int insertbatchApplyWhiteList(List list);
	
	int insertbatchStandardWhiteList(List list);
	
	int deleteAllApplyWhiteList(String activityNo);
	
	int deleteAllStandardWhiteList(String activityNo);
	int selectApplyWhiteListOfActNo(String activityNo);
	int selectClientWhiteListOfActNo(String activityNo);
	int selectStandardWhiteListOfActNo(String activityNo);
	Map getDataSource();
	
	List selectApplyWhiteListOfActNo();
	
	List selectClientWhiteListOfActNo();
	
	List selectStandardWhiteListOfActNo();
	
	int deleteWhiteImport(String[] strs);
	int insertClientWhiteImport(ClientWhiteImport clientWhiteImport);
	int updateClientWhiteImportStatus(String whiteImportId);
	String getSeqId();
	int selectWhiteClientNo(ClientWhiteList clientWhite);
	void updateClientWhiteImport(ClientWhiteImport clientWhiteImport);
	ClientWhiteImport selectClientWhiteImportById(String whiteImportId);
	void deleteClientWhiteList(ClientWhiteImport clientWhiteImport);
	int selectClientWhiteListByWhiteImport(ClientWhiteList clientWhiteList);
	void deleteWhiteImportById(String whiteImportId);
//	String sqlexecute(Map<String, String> mapStr);
}
