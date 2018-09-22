package com.app.mapper;

import java.util.List;

import com.app.entity.BankMessageRecord;

public interface BankMessageRecordMapper {

	List<BankMessageRecord> selectBankMessageRecordPageList(
			BankMessageRecord bankMessageRecord);

	void deleteMessageRecord(int bankMessageRecordId);
	void updateMessageRecordById(BankMessageRecord bankMessageRecord);
}
