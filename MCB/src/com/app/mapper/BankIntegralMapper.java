package com.app.mapper;

import java.util.List;

import com.app.entity.BankIntegral;

/**
 * 积分配置XML接口
 * create Date 2016/4/19
 * <br/>
 * @author shiguangting@tansun.cn.com
 *
 */
public interface BankIntegralMapper {
	/**
	 * 积分查询，分页
	 * @param bankIntegral
	 * @return
	 */
	public List<BankIntegral> selBankIntegralPageList(BankIntegral bankIntegral);
	/**
	 * 积分总条数
	 * @param integral
	 * @return
	 */
	public int getCount(BankIntegral integral);
	/**
	 * 添加积分信息
	 * @param bankIntegral
	 */
	public void saveBankIntegral(BankIntegral bankIntegral);
	/**
	 * 查询积分单条信息
	 * @param integralId
	 * @return
	 */
	public BankIntegral selIntegralDetails(String integralId);
	/**
	 *编辑积分信息 
	 * @param bankIntegral
	 */
	public void editBankIntegral(BankIntegral bankIntegral);

	/**
	 * 删除积分信息
	 * @param integralId
	 */
	public void dleBankIntegral(String integralId);

}
