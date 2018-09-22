package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCity;
import com.app.mapper.BankCtyMapper;
import com.app.service.BankCityService;

public class BankCityServiceImpl implements BankCityService{
	
	private BankCtyMapper bankCtyMapper;

	public BankCtyMapper getBankCtyMapper() {
		return bankCtyMapper;
	}

	public void setBankCtyMapper(BankCtyMapper bankCtyMapper) {
		this.bankCtyMapper = bankCtyMapper;
	}

	@Override
	public List<BankCity> listPageBankCty(BankCity bankCity) {
		 
		List<BankCity> list=bankCtyMapper.listPageBankCty(bankCity);
		return list;
	}

	@Override
	public void deleteBankCityInfo(int cardId) {
		// TODO Auto-generated method stub
		bankCtyMapper.deleteBankCityInfo(cardId);
	}

	@Override
	public boolean insertBankCity(BankCity bankCity) {
		// TODO Auto-generated method stub
		int getCount=bankCtyMapper.getCountNewNo(bankCity.getCityNo());
		if(getCount>0){
			return false;
		}
		bankCtyMapper.insertBankCity(bankCity);
		return true;
	}

	@Override
	public BankCity getBankCityById(int cardId) {
		// TODO Auto-generated method stub
		return  bankCtyMapper.getBankCityById(cardId);
	}

	@Override
	public void updateBankCity(BankCity bankCity) {
		// TODO Auto-generated method stub
		bankCtyMapper.updateBankCity(bankCity);
	}

	@Override
	public List<BankCity> findAllCity() {
		 
		return bankCtyMapper.findAllCity();
	}

	@Override
	public BankCity getBankCityByCode(String carStagingCityCode) {
		return bankCtyMapper.getBankCityByCode(carStagingCityCode);
	}


	

}
