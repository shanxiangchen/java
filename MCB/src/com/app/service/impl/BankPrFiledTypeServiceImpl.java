package com.app.service.impl;

import java.util.List;
import java.util.Map;

import com.app.entity.BankPrFiledType;
import com.app.mapper.BankPrFiledTypeMapper;
import com.app.service.BankPrFiledTypeService;

public class BankPrFiledTypeServiceImpl implements BankPrFiledTypeService {
	private BankPrFiledTypeMapper bankPrFiledTypeMapper;

	public BankPrFiledTypeMapper getBankPrFiledTypeMapper() {
		return bankPrFiledTypeMapper;
	}

	public void setBankPrFiledTypeMapper(
			BankPrFiledTypeMapper bankPrFiledTypeMapper) {
		this.bankPrFiledTypeMapper = bankPrFiledTypeMapper;
	}

	@Override
	public List<BankPrFiledType> bankPrFiledTypePageList(
			BankPrFiledType bankPrFiledType) {
		List<BankPrFiledType> bankPrFiledTypes =bankPrFiledTypeMapper.bankPrFiledTypePageList(bankPrFiledType);
		return bankPrFiledTypes;
	}

	@Override
	public List<Map<String, String>> serviceTypeList() {

		return bankPrFiledTypeMapper.serviceTypeList();
	}

	@Override
	public List<Map<String, String>> selectFiledList() {

		return bankPrFiledTypeMapper.selectFiledList();
	}

	@Override
	public int saveFiledType(BankPrFiledType bankPrFiledType) {

		return bankPrFiledTypeMapper.saveFiledType(bankPrFiledType);
	}

	@Override
	public int deleteFiledType(String[] strs) {

		return bankPrFiledTypeMapper.delFiledType(strs);
	}

	@Override
	public List<BankPrFiledType> selectBankPrFiledById(String filedTypeId) {

		return bankPrFiledTypeMapper.selectBankPrFiledById(filedTypeId);
	}

	@Override
	public int updateFiledType(BankPrFiledType bankPrFiledType) {

		return bankPrFiledTypeMapper.updateFiledType(bankPrFiledType);
	}

	@Override
	public int selectExistsTypeId(BankPrFiledType bankPrFiledType) {

		return bankPrFiledTypeMapper.selectExistsTypeId(bankPrFiledType);
	}

	@Override
	public List<Map<String, String>> selectAllFiledById(String filedTypeId) {

		return bankPrFiledTypeMapper.selectAllFiledById(filedTypeId);
	}

}
