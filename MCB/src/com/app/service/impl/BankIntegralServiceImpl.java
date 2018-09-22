package com.app.service.impl;

import java.util.List;

import com.app.entity.BankIntegral;
import com.app.mapper.BankIntegralMapper;
import com.app.service.BankIntegralService;

/**
 * 积分接口实现类 create date 2016/4/20 <br/>
 * 
 * @author shiguangting@tansun.cn.com
 * 
 */
public class BankIntegralServiceImpl implements BankIntegralService {
	private BankIntegralMapper bankIntegralMapper;

	public BankIntegralMapper getBankIntegralMapper() {
		return bankIntegralMapper;
	}

	public void setBankIntegralMapper(BankIntegralMapper bankIntegralMapper) {
		this.bankIntegralMapper = bankIntegralMapper;
	}

	@Override
	public List<BankIntegral> selBankIntegralPageList(BankIntegral bankIntegral) {
		List<BankIntegral> bankIntegrals = bankIntegralMapper
				.selBankIntegralPageList(bankIntegral);
		return bankIntegrals;
	}

	@Override
	public void saveBankIntegral(BankIntegral bankIntegral) {
		bankIntegralMapper.saveBankIntegral(bankIntegral);
	}

	@Override
	public BankIntegral selIntegralDetails(String integralId) {
		return bankIntegralMapper.selIntegralDetails(integralId);
	}

	@Override
	public void updateBankIntegral(BankIntegral bankIntegral) {
		bankIntegralMapper.editBankIntegral(bankIntegral);
	}


	@Override
	public void deleteBankIntegral(String integralId) {
		bankIntegralMapper.dleBankIntegral(integralId);
	}

}
