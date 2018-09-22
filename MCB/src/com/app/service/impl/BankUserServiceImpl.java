package com.app.service.impl;

import java.util.List;

import com.app.entity.BankUser;
import com.app.mapper.BankUserMpper;
import com.app.service.BankUserService;

/**
 * 客户端用户服务接口实现类
 * create date 2016/6/14
 * <br/>
 * @author shiguanging@tansun.com.cn
 *
 */
public class BankUserServiceImpl implements BankUserService{
	private BankUserMpper bankUserMpper;

	public BankUserMpper getBankUserMpper() {
		return bankUserMpper;
	}

	public void setBankUserMpper(BankUserMpper bankUserMpper) {
		this.bankUserMpper = bankUserMpper;
	}
	//实现客户端用户查询方法
	@Override
	public List<BankUser> selBankUserList(BankUser bankUser) {
		 
		List<BankUser> list=bankUserMpper.selBankUserPageList(bankUser);
		return list;
	}
	//编辑客户端用户信息
	@Override
	public void updateBankUser(int userId) {
		 
		bankUserMpper.updateBankUser(userId);
	}	
	
	
}
