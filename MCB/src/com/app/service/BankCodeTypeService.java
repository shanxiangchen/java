package com.app.service;

   
import java.util.List;

import com.app.entity.BankCodeType;
 
public interface BankCodeTypeService {
	 
	public List<BankCodeType> selectBankCodeTypeList(BankCodeType bankCodeType);
	public int selectCountByCodeTypeId(String bankCodeId);
	public void saveBankCodeType(BankCodeType bankCodeType);
	public BankCodeType getBankCodeTypeServiceById(String bankCodeId);
	public void updateBankCodeType(BankCodeType bankCodeType);
	public void deleteCodeType(String[] strs);
	public void deleteCodeTypeInfoById(String[] strs);
	 
}
