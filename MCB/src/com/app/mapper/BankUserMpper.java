package com.app.mapper;

import java.util.List;

import com.app.entity.BankUser;

/**
 * 客户端用户配置接口
 * create date 2016/6/14
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface BankUserMpper {
	/**
	 * 查询客户端用户，分页
	 * @param bankUser
	 * @return
	 */
	public List<BankUser> selBankUserPageList(BankUser bankUser);
	
	 
	/**
	 * 编辑客户端用户信息
	 * @param userId
	 */
	public void updateBankUser(int userId);
	
}
