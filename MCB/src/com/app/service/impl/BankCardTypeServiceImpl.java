package com.app.service.impl;


 
import java.util.List;
import java.util.Map;

import com.app.entity.BankCardType;
import com.app.mapper.BankCardTypeMapper;
import com.app.service.BankCardTypeService;

 
public class BankCardTypeServiceImpl implements BankCardTypeService{
	private BankCardTypeMapper  bankCardTypeMapper;
	
	public BankCardTypeMapper getBankCardTypeMapper() {
		return bankCardTypeMapper;
	}
 
	public void setBankCardTypeMapper(BankCardTypeMapper bankCardTypeMapper) {
		this.bankCardTypeMapper = bankCardTypeMapper;
	} 
	@Override
	public List<BankCardType> bankCardTypePageList(BankCardType bankCardType) {
		List<BankCardType> bankCardTypes = bankCardTypeMapper.bankCardTypePageList(bankCardType);
		return bankCardTypes;
	}

	@Override
	public List<Map<String, String>> selectBankCodeInfoList() {
		 
		return bankCardTypeMapper.selectBankCodeInfoList();
	}

	@Override
	public void insertBankCardType(BankCardType bankCardType) {
		bankCardTypeMapper.insertBankCardType(bankCardType);
	}

	@Override
	public List<String> selectBankCardTypePics(String[] strs) {
		 
		return bankCardTypeMapper.selectBankCardTypePics(strs);
	}

	@Override
	public void deleteCodeType(String[] strs) {
		bankCardTypeMapper.deleteCodeType(strs);
	}

	@Override
	public BankCardType cardTypeListById(String typeId) {
		 
		return bankCardTypeMapper.cardTypeListById(typeId);
	}

	@Override
	public void updateCardType(BankCardType bankCardType) {
		bankCardTypeMapper.updateCardType(bankCardType);
	}

	@Override
	public int selectBankCardTypeBynum(String typeNum) {
		 
		return bankCardTypeMapper.selectBankCardTypeBynum(typeNum);
	}

	@Override
	public String selectCardOfName(String[] strs) {
		 
		return bankCardTypeMapper.selectCardOfName(strs);
	}

	@Override
	public String selectBankCartTypeId() {
		return bankCardTypeMapper.selectBankCartTypeId();
	}

	 
	
	 
	 

	 
	 
}
