package com.app.service;

import java.util.List;

import com.app.entity.BankMessageRecord;

public interface BankMessageRecordService {

	List<BankMessageRecord> selectBankMessageRecordPageList(
			BankMessageRecord bankMessageRecord);

	void deleteMessageRecord(int bankMessageRecordId);
	void updateMessageRecordById(BankMessageRecord bankMessageRecord);
	
}
