package com.app.service.impl;

import java.util.List;

import com.app.entity.BankInnerLink;
import com.app.mapper.BankInnerLinkMapper;
import com.app.service.BankInnerLinkService;

public class BankInnerLinkServiceImpl implements BankInnerLinkService{

	private BankInnerLinkMapper bankInnerLinkMapper;

	public BankInnerLinkMapper getBankInnerLinkMapper() {
		return bankInnerLinkMapper;
	}

	public void setBankInnerLinkMapper(BankInnerLinkMapper bankInnerLinkMapper) {
		this.bankInnerLinkMapper = bankInnerLinkMapper;
	}

	@Override
	public List<BankInnerLink> selectBankInnerLinkPageList(BankInnerLink bankInnerLink) {
		return bankInnerLinkMapper.selectBankInnerLinkPageList(bankInnerLink);
	}

	@Override
	public int selectBankInnerLinkNum(BankInnerLink bankInnerLink) {
		return bankInnerLinkMapper.selectBankInnerLinkNum(bankInnerLink);
	}

	@Override
	public void insertBankInnerLink(BankInnerLink bankInnerLink) {
		bankInnerLinkMapper.insertBankInnerLink(bankInnerLink);
	}

	@Override
	public BankInnerLink selectBankInnerLink(BankInnerLink bankInnerLink) {
		return bankInnerLinkMapper.selectBankInnerLink(bankInnerLink);
	}

	@Override
	public int deleteBankInnerLink(String linkNo) {
		return bankInnerLinkMapper.deleteBankInnerLink(linkNo);
	}

	@Override
	public void updateSaveBankInnerLink(BankInnerLink bankInnerLink) {
		bankInnerLinkMapper.updateSaveBankInnerLink(bankInnerLink);
	}

	@Override
	public BankInnerLink getBankInnerLinkByNo(String linkNo) {
		return bankInnerLinkMapper.getBankInnerLinkByNo(linkNo);
	}

	@Override
	public BankInnerLink selectBankInnerLinkByLinkNo(BankInnerLink bankInnerLink) {
		return bankInnerLinkMapper.selectBankInnerLinkByLinkNo(bankInnerLink);
	}
	
}
