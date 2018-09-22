package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankCodeType;

 
public interface BankCodeInfoMapper {
	 
	public List<BankCodeInfo> selectBankCodeInfoPageList(BankCodeInfo bankCodeInfo);
	
	public int getCount(BankCodeInfo bankCodeInfo);
	
	public int getCountByValue(Map<String,String> map);
	
	public List<BankCodeType> selectBankCodeTypeList();
	
	public void saveBankCodeInfo(BankCodeInfo bankCodeInfo);
	
	public void deleteCodeInfo(String[] strs);
	
	public BankCodeInfo getBankCodeInfoById(String codeInfoId);
	
	public void updateBankCodeInfo(BankCodeInfo bankCodeInfo);

	 

	
	 
	 
}
