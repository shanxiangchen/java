package com.app.mapper;

import java.util.List;

import com.app.entity.BankCodeType;

 
public interface BankCodeTypeMapper {
	 
	public List<BankCodeType> selectBankCodeTypePageList(BankCodeType bankCodeType);
	public int getCount(BankCodeType bankCodeType);

	public int selectCountByCodeTypeId(String bankCodeId);

	public void saveBankCodeType(BankCodeType bankCodeType);
	
	public BankCodeType getBankCodeTypeServiceById(String bankCodeId);
	
	public void updateBankCodeType(BankCodeType bankCodeType);
	
	public void deleteCodeType(String[] strs);
	
	public void deleteCodeTypeInfoById(String[] strs);
	 
	 
}
