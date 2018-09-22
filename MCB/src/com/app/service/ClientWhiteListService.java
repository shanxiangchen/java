package com.app.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.app.entity.ClientWhiteImport;
import com.app.entity.ClientWhiteList;
@SuppressWarnings("rawtypes")
public interface ClientWhiteListService {
	/**
	 *导入客户白名单
	 * @param page 页数
	 * @return
	 */
	List<ClientWhiteImport> selectWhiteImportListPage(ClientWhiteImport whiteImport);
	int insertbatchClientWhiteList(@Param("list")List list);
	int deleteAllClientWhiteList(String activityNo);
	int insertbatchApplyWhiteList(@Param("list")List list);
	int insertbatchStandardWhiteList(@Param("list")List list);
	int deleteAllApplyWhiteList(String activityNo);
	int deleteAllStandardWhiteList(String activityNo);
	int selectApplyWhiteListOfActNo(String activityNo);
	int selectClientWhiteListOfActNo(String activityNo);
	int selectStandardWhiteListOfActNo(String activityNo);
	Map getDataSource();
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
