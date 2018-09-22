package com.app.service;

   
 
 
import java.util.List;
import java.util.Map;

import com.app.entity.BankPrSave;
import com.app.entity.PageInfo;
import com.app.util.PageBean;
 
 
public interface BankPrSaveService {
	 
	public PageBean<BankPrSave> selectBankPrSaveList(BankPrSave bankPrSave,PageInfo pageInfo);
	public Map<String,String> infoBankPrSaveById(String prSaveId);
	public List<Map<String,String>> selectFiledNameById(String typeId);
	public int delBankPrSave(String[] strs);
 
}
