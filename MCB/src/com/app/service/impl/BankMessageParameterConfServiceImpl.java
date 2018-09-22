package com.app.service.impl;

import java.util.List;

import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageType;
import com.app.mapper.BankMessagePCMapper;
import com.app.service.BankMessageParameterConfService;

public class BankMessageParameterConfServiceImpl implements BankMessageParameterConfService{
	
	private BankMessagePCMapper bankMessagePCMapper;

	@Override
	public List<BankMessageParameterConf> getBankMessageParameterConfPageList(
			BankMessageParameterConf bankMessageParameterConf) {
		return bankMessagePCMapper.selectMessageParameterConfPageList(bankMessageParameterConf);
	}

	@Override
	public int getBankMessageParameterConfCount(
			BankMessageParameterConf bankMessageParameterConf) {
		return bankMessagePCMapper.selectCount(bankMessageParameterConf);
	}

	@Override
	public void updateBankMessageParameterConf(
			BankMessageParameterConf bankMessageParameterConf) {
		bankMessagePCMapper.updateMessageParameterConf(bankMessageParameterConf);
		
	}

	@Override
	public void insertBankMessageParameterConf(
			BankMessageParameterConf bankMessageParameterConf) {
		bankMessagePCMapper.insertMessageParameterConf(bankMessageParameterConf);
	}

	public BankMessagePCMapper getBankMessagePCMapper() {
		return bankMessagePCMapper;
	}

	public void setBankMessagePCMapper(BankMessagePCMapper bankMessagePCMapper) {
		this.bankMessagePCMapper = bankMessagePCMapper;
	}

	@Override
	public List<BankMessageType> selBankMessageType() {
		return bankMessagePCMapper.selBankMessageType();
	}

	@Override
	public BankMessageParameterConf selMessageParameterConfById(String bmpcId) {
		// TODO Auto-generated method stub
		return bankMessagePCMapper.selMessageParameterConfById(bmpcId);
	}
}
