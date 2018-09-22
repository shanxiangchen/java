package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageSmallType;
import com.app.entity.BankMessageTemplate;
import com.app.mapper.BankMessageTemplateMapper;
import com.app.service.BankMessageTemplateService;

public class BankMessageTemplateServiceImpl implements
		BankMessageTemplateService {
	private BankMessageTemplateMapper bankMessageTemplateMapper;

	public BankMessageTemplateMapper getBankMessageTemplateMapper() {
		return bankMessageTemplateMapper;
	}

	public void setBankMessageTemplateMapper(
			BankMessageTemplateMapper bankMessageTemplateMapper) {
		this.bankMessageTemplateMapper = bankMessageTemplateMapper;
	}

	// 消息模板，分页方法
	@Override
	public List<BankCodeInfo> listBankCodeInfo() {
		// TODO Auto-generated method stub
		return bankMessageTemplateMapper.listBankCodeInfo();
	}

	@Override
	public List<BankMessageTemplate> bankMessageTemplatePageList(
			BankMessageTemplate bankMessageTemplate) {
		List<BankMessageTemplate> bankMessageTemplates = bankMessageTemplateMapper
				.bankMessageTemplatePageList(bankMessageTemplate);
		return bankMessageTemplates;
	}

	@Override
	public void saveBankMessageTemplate(BankMessageTemplate bankMessageTemplate) {
		bankMessageTemplateMapper.saveBankMessageTemplate(bankMessageTemplate);
	}

	@Override
	public BankMessageTemplate getMessageTemplateById(int messageTemplateId) {
		return bankMessageTemplateMapper
				.getMessageTemplateById(messageTemplateId);
	}

	@Override
	public void updateMessageTemplate(BankMessageTemplate bankMessageTemplate) {
		
		bankMessageTemplateMapper.editMessageTemplate(bankMessageTemplate);
	}

	@Override
	public void deleteMessageTemplate(int messageTemplateId) {
		bankMessageTemplateMapper.delMessageTemplate(messageTemplateId);
	}

	@Override
	public List<BankMessageParameterConf> bankMessageParameterConfList() {
		// TODO Auto-generated method stub
		return bankMessageTemplateMapper.bankMessageParameterConfList();
	}

	@Override
	public List<BankMessageSmallType> bankMessageSmallTypeList(String typeNo) {
		return bankMessageTemplateMapper.bankMessageSmallTypeList(typeNo);
	}

}
