package com.app.mapper;

import java.util.List;

import com.app.entity.BankStagesWhiteList;


public interface BankStagesWhiteListMapper {
	 
	public List<BankStagesWhiteList> bankStagesWhitePageList(BankStagesWhiteList bankStagesWhiteList);
	public int delectStagingList(String[] strs);
	public int insertStagesWhite(BankStagesWhiteList bankStagesWhiteList);
	public int selectCountByPhone(String listPhone);
	
}
