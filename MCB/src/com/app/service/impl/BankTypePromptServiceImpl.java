package com.app.service.impl;

import java.util.List;

import com.app.entity.BankTypePrompt;
import com.app.mapper.BankTypePromptMapper;
import com.app.service.BankTypePromptService;

public class BankTypePromptServiceImpl implements BankTypePromptService{

		private BankTypePromptMapper bankTypePromptMapper;
		
		
	public BankTypePromptMapper getBankTypePromptMapper() {
			return bankTypePromptMapper;
		}

		public void setBankTypePromptMapper(BankTypePromptMapper bankTypePromptMapper) {
			this.bankTypePromptMapper = bankTypePromptMapper;
		}

	@Override
	public List<BankTypePrompt> listPageBankTypePrompt(BankTypePrompt bankTypePrompt) {
		List<BankTypePrompt> list = bankTypePromptMapper.listPageTypePrompt(bankTypePrompt);
		return list;
	}

	@Override
	public void update(BankTypePrompt bankTypePrompt) {
		bankTypePromptMapper.updateTypePrompt(bankTypePrompt);
	}

	@Override
	public BankTypePrompt getBankTypePromptByid(String typeId) {
		return bankTypePromptMapper.getTypePromptByid(typeId);
	}

}
