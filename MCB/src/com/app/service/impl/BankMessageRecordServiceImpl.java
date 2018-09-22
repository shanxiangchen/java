package com.app.service.impl;

import java.util.List;

import com.app.entity.BankMessageRecord;
import com.app.mapper.BankMessageRecordMapper;
import com.app.service.BankMessageRecordService;

public class BankMessageRecordServiceImpl implements BankMessageRecordService {

	private BankMessageRecordMapper bankMessageRecordMapper;

	public BankMessageRecordMapper getBankMessageRecordMapper() {
		return bankMessageRecordMapper;
	}

	public void setBankMessageRecordMapper(
			BankMessageRecordMapper bankMessageRecordMapper) {
		this.bankMessageRecordMapper = bankMessageRecordMapper;
	}

	@Override
	public List<BankMessageRecord> selectBankMessageRecordPageList(
			BankMessageRecord bankMessageRecord) {
		return bankMessageRecordMapper.selectBankMessageRecordPageList(bankMessageRecord);
	}

	@Override
	public void deleteMessageRecord(int bankMessageRecordId) {
		bankMessageRecordMapper.deleteMessageRecord(bankMessageRecordId);
	}

	@Override
	public void updateMessageRecordById(BankMessageRecord bankMessageRecord) {
		bankMessageRecordMapper.updateMessageRecordById(bankMessageRecord);
	}
	
}
