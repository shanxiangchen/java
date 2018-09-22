package com.app.service;
import java.util.List;
import java.util.Map;

import com.app.entity.BankPrFiledType;
public interface BankPrFiledTypeService {
	 
	public List<BankPrFiledType> bankPrFiledTypePageList(BankPrFiledType bankPrFiledType);
	public List<Map<String,String>> serviceTypeList(); 
	public List<Map<String,String>> selectFiledList();
	public int saveFiledType(BankPrFiledType bankPrFiledType);
	public int deleteFiledType(String[] strs);
	public List<BankPrFiledType> selectBankPrFiledById(String filedTypeId);
	public int updateFiledType(BankPrFiledType bankPrFiledType);
	public int selectExistsTypeId(BankPrFiledType bankPrFiledType);
	public List<Map<String,String>> selectAllFiledById(String filedTypeId);
}
