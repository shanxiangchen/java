package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankPrActivityInfo;
import com.app.entity.BankPrHoliday;
import com.app.mapper.BankPrActivityInfoMapper;
import com.app.service.BankPrActivityInfoService;

public class BankPrActivityInfoServiceImpl implements BankPrActivityInfoService {
	private BankPrActivityInfoMapper bankPrActivityInfoMapper;

	public BankPrActivityInfoMapper getBankPrActivityInfoMapper() {
		return bankPrActivityInfoMapper;
	}

	public void setBankPrActivityInfoMapper(
			BankPrActivityInfoMapper bankPrActivityInfoMapper) {
		this.bankPrActivityInfoMapper = bankPrActivityInfoMapper;
	}

	@Override
	public List<BankPrActivityInfo> prActivityInfoPageList(
			BankPrActivityInfo bankPrActivityInfo) {
		List<BankPrActivityInfo> list = bankPrActivityInfoMapper
				.prActivityInfoPageList(bankPrActivityInfo);
		return list;
	}

	@Override
	public List<BankCodeInfo> listActivityOfType() {

		return bankPrActivityInfoMapper.listActivityOfType();
	}

	@Override
	public void savePrActivityInfo(BankPrActivityInfo bankPrActivityInfo) {

		bankPrActivityInfoMapper.savePrActivityInfo(bankPrActivityInfo);
	}

	@Override
	public void deletePrActivityInfo(String[] strs) {

		bankPrActivityInfoMapper.deletePrActivityInfo(strs);
	}

	@Override
	public int saveBankPrHoliday(BankPrHoliday bankPrHoliday) {

		return bankPrActivityInfoMapper.saveBankPrHoliday(bankPrHoliday);
	}

	@Override
	public BankPrActivityInfo prActivityInfoListById(String activityInfoId) {

		return bankPrActivityInfoMapper.prActivityInfoListById(activityInfoId);
	}

	@Override
	public int updatePrActivityInfo(BankPrActivityInfo bankPrActivityInfo) {

		return bankPrActivityInfoMapper
				.updatePrActivityInfo(bankPrActivityInfo);
	}

	@Override
	public int updateBankPrHolidayToNo(String year) {

		return bankPrActivityInfoMapper.updateBankPrHolidayToNo(year);
	}

	@Override
	public int getBankPrActivityInfoNum(BankPrActivityInfo bankPrActivityInfo) {
		return bankPrActivityInfoMapper.getBankPrActivityInfoNum(bankPrActivityInfo);
	}

}
