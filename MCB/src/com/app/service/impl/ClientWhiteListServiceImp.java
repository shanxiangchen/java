package com.app.service.impl;

import java.util.List;
import java.util.Map;

import com.app.entity.ClientWhiteImport;
import com.app.entity.ClientWhiteList;
import com.app.mapper.ClientWhiteListMapper;
import com.app.service.ClientWhiteListService;
@SuppressWarnings({"rawtypes","unchecked"})
public class ClientWhiteListServiceImp implements ClientWhiteListService{

	private ClientWhiteListMapper  clientWhiteListMapper;
	
	
	@Override
	/**
	 * 导入客户白名单
	 * @param page 页数
	 * @return
	 */
	public int insertbatchClientWhiteList(List list) {
		 
		return clientWhiteListMapper.insertbatchClientWhiteList(list);
	}


	public ClientWhiteListMapper getClientWhiteListMapper() {
		return clientWhiteListMapper;
	}


	public void setClientWhiteListMapper(ClientWhiteListMapper clientWhiteListMapper) {
		this.clientWhiteListMapper = clientWhiteListMapper;
	}
	
	/**
	 * 查询白名单列表
	 * @param whiteImport
	 * @return
	 */
	public List<ClientWhiteImport> selectWhiteImportListPage(ClientWhiteImport whiteImport) {
		 
		List<ClientWhiteImport> list=clientWhiteListMapper.selectWhiteImportListPage(whiteImport);
		return list;
	}


	@Override
	public int deleteAllClientWhiteList(String activityNo) {
		return clientWhiteListMapper.deleteAllClientWhiteList(activityNo);
	}


	@Override
	public int insertbatchApplyWhiteList(List list) {
		return clientWhiteListMapper.insertbatchApplyWhiteList(list);
	}


	@Override
	public int insertbatchStandardWhiteList(List list) {
		return clientWhiteListMapper.insertbatchStandardWhiteList(list);
	}


	@Override
	public int deleteAllApplyWhiteList(String activityNo) {
		return clientWhiteListMapper.deleteAllApplyWhiteList(activityNo);
	}


	@Override
	public int deleteAllStandardWhiteList(String activityNo) {
		return clientWhiteListMapper.deleteAllStandardWhiteList(activityNo);
	}


	@Override
	public int selectApplyWhiteListOfActNo(String activityNo) {
		return clientWhiteListMapper.selectApplyWhiteListOfActNo(activityNo);
	}

	@Override
	public int selectClientWhiteListOfActNo(String activityNo) {
		return clientWhiteListMapper.selectClientWhiteListOfActNo(activityNo);
	}


	@Override
	public int selectStandardWhiteListOfActNo(String activityNo) {
		return clientWhiteListMapper.selectStandardWhiteListOfActNo(activityNo);
	}
 

	@Override
	public int deleteWhiteImport(String[] strs) {
		return clientWhiteListMapper.deleteWhiteImport(strs);
	}


	@Override
	public Map getDataSource() {
		return clientWhiteListMapper.getDataSource();
	}


	@Override
	public int insertClientWhiteImport(ClientWhiteImport clientWhiteImport) {
		return clientWhiteListMapper.insertClientWhiteImport(clientWhiteImport);
	}


	@Override
	public int updateClientWhiteImportStatus(String whiteImportId) {
		return clientWhiteListMapper.updateClientWhiteImportStatus(whiteImportId);
	}


	@Override
	public String getSeqId() {
		return clientWhiteListMapper.getSeqId();
	}


	@Override
	public int selectWhiteClientNo(ClientWhiteList clientWhite) {
		return clientWhiteListMapper.selectWhiteClientNo(clientWhite);
	}


	@Override
	public void updateClientWhiteImport(ClientWhiteImport clientWhiteImport) {
		clientWhiteListMapper.updateClientWhiteImport(clientWhiteImport);
	}


	@Override
	public ClientWhiteImport selectClientWhiteImportById(String whiteImportId) {
		return clientWhiteListMapper.selectClientWhiteImportById(whiteImportId);
	}


	@Override
	public void deleteClientWhiteList(ClientWhiteImport clientWhiteImport) {
		clientWhiteListMapper.deleteClientWhiteList(clientWhiteImport);		
	}


	@Override
	public int selectClientWhiteListByWhiteImport(ClientWhiteList clientWhiteList) {
		return clientWhiteListMapper.selectClientWhiteListByWhiteImport(clientWhiteList);
	}


	@Override
	public void deleteWhiteImportById(String whiteImportId) {
		clientWhiteListMapper.deleteWhiteImportById(whiteImportId);
	}


	/*@Override
	public String sqlexecute(Map<String, String> mapStr) {
		return clientWhiteListMapper.sqlexecute(mapStr);
	}*/

}
