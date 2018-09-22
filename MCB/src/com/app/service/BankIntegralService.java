package com.app.service;
import java.util.List;

import com.app.entity.BankIntegral;

/**
 * 积分服务接口
 * create date 2016/4/20
 * <br/>
 * @author shhiguangting@ tansun.cn.com
 *
 */
public interface BankIntegralService {
	/**
	 *积分查询方法
	 * @param bankIntegral
	 * @return
	 */
	public List<BankIntegral> selBankIntegralPageList(BankIntegral bankIntegral);
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
	public void updateBankIntegral(BankIntegral bankIntegral);
	/**
	 * 删除积分信息
	 * @param integralId
	 */
	public void deleteBankIntegral(String integralId);
}
