package com.app.service;

   
 
 
import java.util.List;
import java.util.Map;

import com.app.entity.BankCardType;
public interface BankCardTypeService {
	 
	public List<BankCardType> bankCardTypePageList(BankCardType bankCardType);
	public List<Map<String,String>> selectBankCodeInfoList();
	public void insertBankCardType(BankCardType bankCardType);
	public List<String> selectBankCardTypePics(String[] strs);
	public void deleteCodeType(String[] strs);
	public BankCardType cardTypeListById(String typeId);
	public void updateCardType(BankCardType bankCardType);
	public int selectBankCardTypeBynum(String typeNum);
	public String selectCardOfName(String[] strs);
	public String selectBankCartTypeId();
	 
}
