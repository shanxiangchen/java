package com.app.service;

     
import com.app.entity.BankPrRightsFiled;
import com.app.entity.PageInfo;
import com.app.util.PageBean;
 
 
public interface BankPrRightsFiledService {
	 
	public PageBean<BankPrRightsFiled> selectBankPrRightsFiledList(BankPrRightsFiled bankPrRightsFiled,PageInfo pageInfo);
	public int saveRightsFiled(BankPrRightsFiled bankPrRightsFiled); 
	public int delRightsFiled(String[] strs);
	public BankPrRightsFiled selectRightsFiledById(String filedId);
	public int updateRightsFiled(BankPrRightsFiled bankPrRightsFiled);
}
