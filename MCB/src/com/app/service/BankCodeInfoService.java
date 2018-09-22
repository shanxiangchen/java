package com.app.service;

   
import java.util.List;
import java.util.Map;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankCodeType;
 
public interface BankCodeInfoService {
	 
	public List<BankCodeInfo> selectBankCodeInfoList(BankCodeInfo bankCodeInfo);
	public List<BankCodeType> selectBankCodeTypeList();
	public void saveBankCodeInfo(BankCodeInfo bankCodeInfo);
	public int getCountByValue(Map<String,String> map);
	public void deleteCodeInfo(String[] strs);
	public BankCodeInfo getBankCodeInfoById(String codeInfoId);
	
	public void updateBankCodeInfo(BankCodeInfo bankCodeInfo);
	 
}
