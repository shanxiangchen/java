package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.BankPrFiledType;

 
public interface BankPrFiledTypeMapper {
	
	public List<BankPrFiledType> bankPrFiledTypePageList(BankPrFiledType bankPrFiledType);
	
	public List<Map<String,String>> serviceTypeList();
	public List<Map<String,String>> selectFiledList();
	public int saveFiledType(BankPrFiledType bankPrFiledType);
	public int delFiledType(String[] strs);
	
	public List<BankPrFiledType> selectBankPrFiledById(String filedTypeId);
	public int updateFiledType(BankPrFiledType bankPrFiledType);
	public int selectExistsTypeId(BankPrFiledType bankPrFiledType);
	public List<Map<String,String>> selectAllFiledById(String filedTypeId);
	 
	 
}
