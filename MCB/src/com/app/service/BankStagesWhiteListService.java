package com.app.service;

    
import java.util.List;

import com.app.entity.BankStagesWhiteList;
public interface BankStagesWhiteListService {
	 
	public List<BankStagesWhiteList> bankStagesWhitePageList(BankStagesWhiteList bankStagesWhiteList);
	public int deleteStagingList(String[] strs);
	public int insertStagesWhite(BankStagesWhiteList bankStagesWhiteList);
	public int selectCountByPhone(String listPhone);
	 
}
