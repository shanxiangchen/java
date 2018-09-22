package com.app.mapper;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankPrActivityInfo;
import com.app.entity.BankPrHoliday;

 
public interface BankPrActivityInfoMapper {
	 
	public List<BankPrActivityInfo> prActivityInfoPageList(BankPrActivityInfo bankPrActivityInfo);
	public List<BankCodeInfo> listActivityOfType();
	public void savePrActivityInfo(BankPrActivityInfo bankPrActivityInfo);
	public void deletePrActivityInfo(String[] strs);
	public BankPrActivityInfo prActivityInfoListById(String activityInfoId);
	public int saveBankPrHoliday(BankPrHoliday bankPrHoliday);
	public int updatePrActivityInfo(BankPrActivityInfo bankPrActivityInfo);
	public int updateBankPrHolidayToNo(String year);
	public int getBankPrActivityInfoNum(BankPrActivityInfo bankPrActivityInfo);
}
