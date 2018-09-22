package com.app.service.impl;


import java.util.List;

import com.app.entity.BankCodeType;
import com.app.mapper.BankCodeTypeMapper;
import com.app.service.BankCodeTypeService;

 
public class BankCodeTypeServiceImpl implements BankCodeTypeService{
	
	private BankCodeTypeMapper  bankCodeTypeMapper;
	
	 
	public BankCodeTypeMapper getBankCodeTypeMapper() {
		return bankCodeTypeMapper;
	}
 
	public void setBankCodeTypeMapper(BankCodeTypeMapper bankCodeTypeMapper) {
		this.bankCodeTypeMapper = bankCodeTypeMapper;
	}



	@Override
	public List<BankCodeType> selectBankCodeTypeList(
			BankCodeType bankCodeType) {
		List<BankCodeType> list=bankCodeTypeMapper.selectBankCodeTypePageList(bankCodeType);
		return list;
		 		 
	}

	@Override
	public int selectCountByCodeTypeId(String bankCodeId) {
		 
		return bankCodeTypeMapper.selectCountByCodeTypeId(bankCodeId);
	}

	@Override
	public void saveBankCodeType(BankCodeType bankCodeType) {
		 
		  bankCodeTypeMapper.saveBankCodeType(bankCodeType);
	}

	@Override
	public BankCodeType getBankCodeTypeServiceById(String bankCodeId) {
		 
		return bankCodeTypeMapper.getBankCodeTypeServiceById(bankCodeId);
	}

	@Override
	public void updateBankCodeType(BankCodeType bankCodeType) {
		 
		bankCodeTypeMapper.updateBankCodeType(bankCodeType);
	}

	@Override
	public void deleteCodeType(String[] strs) {
		bankCodeTypeMapper.deleteCodeType(strs);
	}

	@Override
	public void deleteCodeTypeInfoById(String[] strs) {
		 
		bankCodeTypeMapper.deleteCodeTypeInfoById(strs);
	}
    
	 
}
