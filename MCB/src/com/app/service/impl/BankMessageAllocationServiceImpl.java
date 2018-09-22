package com.app.service.impl;

import java.util.List;

import com.app.entity.BankMessageAllocation;
import com.app.entity.BankMessageRecord;
import com.app.mapper.BankMessageAllocationMapper;
import com.app.service.BankMessageAllocationService;

public class BankMessageAllocationServiceImpl implements BankMessageAllocationService {
	private BankMessageAllocationMapper bankMessageAllocationMapper;

	public BankMessageAllocationMapper getBankMessageAllocationMapper() {
		return bankMessageAllocationMapper;
	}

	public void setBankMessageAllocationMapper(
			BankMessageAllocationMapper bankMessageAllocationMapper) {
		this.bankMessageAllocationMapper = bankMessageAllocationMapper;
	}

	@Override
	public List<BankMessageAllocation> selectBankAllocationPageList(BankMessageAllocation bankMessageAllocation) {
		return bankMessageAllocationMapper.selectBankAllocationPageList(bankMessageAllocation);
	}

	@Override
	public int getBankAllocationNumById(BankMessageAllocation bankMessageAllocation) {
		return bankMessageAllocationMapper.getBankAllocationNumById(bankMessageAllocation);
	}

	@Override
	public int getBankAllocationNumByCode(BankMessageAllocation bankMessageAllocation) {
		return bankMessageAllocationMapper.getBankAllocationNumByCode(bankMessageAllocation);
	}

	@Override
	public void saveBankAllocation(BankMessageAllocation bankMessageAllocation) {
		bankMessageAllocationMapper.saveBankAllocation(bankMessageAllocation);
	}

	@Override
	public void updateBankAllocation(BankMessageAllocation bankMessageAllocation) {
		bankMessageAllocationMapper.updateBankAllocation(bankMessageAllocation);
	}

	@Override
	public BankMessageAllocation selectMessageALlocation(BankMessageAllocation bankMessageAllocation) {
		return bankMessageAllocationMapper.selectMessageALlocation(bankMessageAllocation);
	}

	@Override
	public void deleteMessageAllocaiton(int bankMessageId) {
		bankMessageAllocationMapper.deleteMessageAllocaiton(bankMessageId);
	}

	@Override
	public BankMessageAllocation selectMessageAllocationByCode(String messageCode) {
		return bankMessageAllocationMapper.selectMessageAllocationByCode(messageCode);
	}

	@Override
	public List<BankMessageAllocation> selectMessageAllocationPageList(
			BankMessageRecord bankMessageRecord) {
		return bankMessageAllocationMapper.selectMessageAllocationPageList(bankMessageRecord);
	}
	
}
