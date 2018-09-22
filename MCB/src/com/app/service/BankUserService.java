package com.app.service;

import java.util.List;

import com.app.entity.BankUser;


/**
 * 客户端用户服务接口
 * create date 2016/6/14
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface BankUserService {
	/**
	 * 查询客户端用户，分页
	 * @param bankUser
	 * @return
	 */
	public List<BankUser> selBankUserList(BankUser bankUser);
	
	/**
	 * 编辑客户端用户信息
	 * @param userId
	 */
	public void updateBankUser(int userId);
}
