package com.app.mapper;

import java.util.List;

import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageType;

public interface BankMessagePCMapper {
	
	List<BankMessageParameterConf> selectMessageParameterConfPageList(BankMessageParameterConf bankMessageParameterConf);
	
	void insertMessageParameterConf(BankMessageParameterConf bankMessageParameterConf);
	
	void updateMessageParameterConf(BankMessageParameterConf bankMessageParameterConf);
	
	int selectCount(BankMessageParameterConf bankMessageParameterConf);
	
	public List<BankMessageType> selBankMessageType();
	
	public BankMessageParameterConf selMessageParameterConfById(String bmpcId);
	
}
