package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.BankPrSave;

 
public interface BankPrSaveMapper {
	
	public List<BankPrSave> selectBankPrSaveList(BankPrSave bankPrSave);
	public int getCount(BankPrSave bankPrSave);
	public Map<String,String> infoBankPrSaveById(String prSaveId);
	public List<Map<String,String>> selectFiledNameById(String typeId);
	public int delBankPrSave(String[] strs);
	
	 
	 
}
