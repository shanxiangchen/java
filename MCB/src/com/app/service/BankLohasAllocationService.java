package com.app.service;
import java.util.List;
import java.util.Map;

import com.app.entity.BankLohasAllocation;
public interface BankLohasAllocationService {
	 
    public List<BankLohasAllocation> selectBankLohasAllocationPageList(BankLohasAllocation bankLohasAllocation);
    public List<Map<String,String>> selectServiceName();
	public List<Map<String,String>> selectInnerLink();
	public int insertLohasAllocation(BankLohasAllocation bankLohasAllocation);
	public int selectCountByimgWhere(BankLohasAllocation bankLohasAllocation);
	public int deleteLohasAllocation(String[] strs);
	public List<String> selectLohasAllocationPics(String[] strs);
	public BankLohasAllocation selectBankLohasAllocationById(String lohasId);
	//public int selectShowNo(BankLohasAllocation bankLohasAllocation);
	public int updateLohasAllocation(BankLohasAllocation bankLohasAllocation);
	 
}
