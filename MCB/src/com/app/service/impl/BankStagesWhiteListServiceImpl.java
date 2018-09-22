package com.app.service.impl;
 
import java.util.List;

import com.app.entity.BankStagesWhiteList;
import com.app.mapper.BankStagesWhiteListMapper;
import com.app.service.BankStagesWhiteListService;
 
public class BankStagesWhiteListServiceImpl implements BankStagesWhiteListService{
	
	private BankStagesWhiteListMapper  bankStagesWhiteListMapper;
	 
	  
	public BankStagesWhiteListMapper getBankStagesWhiteListMapper() {
		return bankStagesWhiteListMapper;
	}
 
	public void setBankStagesWhiteListMapper(
			BankStagesWhiteListMapper bankStagesWhiteListMapper) {
		this.bankStagesWhiteListMapper = bankStagesWhiteListMapper;
	}





	@Override
	public List<BankStagesWhiteList> bankStagesWhitePageList(
			BankStagesWhiteList bankStagesWhiteList) {
		 
		return bankStagesWhiteListMapper.bankStagesWhitePageList(bankStagesWhiteList);
	}

	@Override
	public int deleteStagingList(String[] strs) {
		 
		return bankStagesWhiteListMapper.delectStagingList(strs);
	}

	@Override
	public int insertStagesWhite(BankStagesWhiteList bankStagesWhiteList) {
		 
		return bankStagesWhiteListMapper.insertStagesWhite(bankStagesWhiteList);
	}

	@Override
	public int selectCountByPhone(String listPhone) {
		 
		return bankStagesWhiteListMapper.selectCountByPhone(listPhone);
	}
	
	 
	 

	



	 
	 
}
