package com.app.mapper;

import java.util.List;

import com.app.entity.BankMessageAllocation;
import com.app.entity.BankMessageRecord;

public interface BankMessageAllocationMapper {

	List<BankMessageAllocation> selectBankAllocationPageList(BankMessageAllocation bankMessageAllocation);

	int getBankAllocationNumById(BankMessageAllocation bankMessageAllocation);

	int getBankAllocationNumByCode(BankMessageAllocation bankMessageAllocation);

	void saveBankAllocation(BankMessageAllocation bankMessageAllocation);

	void updateBankAllocation(BankMessageAllocation bankMessageAllocation);

	BankMessageAllocation selectMessageALlocation(BankMessageAllocation bankMessageAllocation);

	void deleteMessageAllocaiton(int bankMessageId);
	
	BankMessageAllocation selectMessageAllocationByCode(String messageCode);
	
	List<BankMessageAllocation> selectMessageAllocationPageList(BankMessageRecord bankMessageRecord);
}
