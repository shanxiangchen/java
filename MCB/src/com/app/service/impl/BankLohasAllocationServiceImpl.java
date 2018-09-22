package com.app.service.impl;


 
import java.util.List;
import java.util.Map;

import com.app.entity.BankLohasAllocation;
import com.app.mapper.BankLohasAllocationMapper;
import com.app.service.BankLohasAllocationService;

 
public class BankLohasAllocationServiceImpl implements BankLohasAllocationService{
	 
	private BankLohasAllocationMapper  bankLohasAllocationMapper;
	  
	  
	public BankLohasAllocationMapper getBankLohasAllocationMapper() {
		return bankLohasAllocationMapper;
	}

	public void setBankLohasAllocationMapper(
			BankLohasAllocationMapper bankLohasAllocationMapper) {
		this.bankLohasAllocationMapper = bankLohasAllocationMapper;
	}
	@Override
	public List<BankLohasAllocation> selectBankLohasAllocationPageList(
			BankLohasAllocation bankLohasAllocation) {
		List<BankLohasAllocation> allocations=bankLohasAllocationMapper.selectBankLohasAllocationPageList(bankLohasAllocation);
		return allocations;
	}

	@Override
	public List<Map<String, String>> selectServiceName() {
		 
		return bankLohasAllocationMapper.selectServiceName();
	}

	@Override
	public List<Map<String, String>> selectInnerLink() {
		 
		return bankLohasAllocationMapper.selectInnerLink();
	}

	@Override
	public int insertLohasAllocation(BankLohasAllocation bankLohasAllocation) {
		 
		return bankLohasAllocationMapper.insertLohasAllocation(bankLohasAllocation);
	}
	
	public int selectCountByimgWhere(BankLohasAllocation bankLohasAllocation){
		
		return bankLohasAllocationMapper.selectCountByimgWhere(bankLohasAllocation);
	}
	
	public int deleteLohasAllocation(String[] strs){
		
		return bankLohasAllocationMapper.delLohasAllocation(strs);
	}
	public List<String> selectLohasAllocationPics(String[] strs){
		
		return bankLohasAllocationMapper.selectLohasAllocationPics(strs);
	}

	@Override
	public BankLohasAllocation selectBankLohasAllocationById(String lohasId) {
		 
		return bankLohasAllocationMapper.selectBankLohasAllocationById(lohasId);
	}

	public int updateLohasAllocation(BankLohasAllocation bankLohasAllocation) {
		 
		return bankLohasAllocationMapper.updateLohasAllocation(bankLohasAllocation);
	}

	 
	 
	 
}
