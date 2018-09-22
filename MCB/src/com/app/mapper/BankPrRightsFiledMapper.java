package com.app.mapper;

import java.util.List;

import com.app.entity.BankPrRightsFiled;

 
public interface BankPrRightsFiledMapper {
	
	public List<BankPrRightsFiled> selectBankPrFiledTypeList(BankPrRightsFiled bankPrRightsFiled);
	public int getCount(BankPrRightsFiled bankPrRightsFiled);
	public int saveRightsFiled(BankPrRightsFiled bankPrRightsFiled);
	public int delRightsFiled(String[] strs);
	public BankPrRightsFiled selectRightsFiledById(String filedId);
	public int updateRightsFiled(BankPrRightsFiled bankPrRightsFiled);
	
	 
	 
}
