package com.app.service.impl;


 
import java.util.List;
import java.util.Map;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankCodeType;
import com.app.mapper.BankCodeInfoMapper;
import com.app.service.BankCodeInfoService;
 
public class BankCodeInfoServiceImpl implements BankCodeInfoService{
	
	private BankCodeInfoMapper  bankCodeInfoMapper;
	
	 
	public BankCodeInfoMapper getBankCodeInfoMapper() {
		return bankCodeInfoMapper;
	}
 
	public void setBankCodeInfoMapper(BankCodeInfoMapper bankCodeInfoMapper) {
		this.bankCodeInfoMapper = bankCodeInfoMapper;
	}

	@Override
	public List<BankCodeInfo> selectBankCodeInfoList(
			BankCodeInfo bankCodeInfo) {
		 
		List<BankCodeInfo> list=bankCodeInfoMapper.selectBankCodeInfoPageList(bankCodeInfo);
		return list;
		 
	}
	
	@Override
	public List<BankCodeType> selectBankCodeTypeList() {
		 
		return bankCodeInfoMapper.selectBankCodeTypeList();
	}
 

	@Override
	public void saveBankCodeInfo(BankCodeInfo bankCodeInfo) {
		 
		bankCodeInfoMapper.saveBankCodeInfo(bankCodeInfo);
	}

	@Override
	public void deleteCodeInfo(String[] strs) {
		 
		bankCodeInfoMapper.deleteCodeInfo(strs);
		
	}

	@Override
	public BankCodeInfo getBankCodeInfoById(String codeInfoId) {
		 
		return bankCodeInfoMapper.getBankCodeInfoById(codeInfoId);
	}

	@Override
	public void updateBankCodeInfo(BankCodeInfo bankCodeInfo) {
		 
		bankCodeInfoMapper.updateBankCodeInfo(bankCodeInfo);
		
	}

	@Override
	public int getCountByValue(Map<String,String> map) {
		 
		return bankCodeInfoMapper.getCountByValue(map);
	}

	



	 
	 
}
