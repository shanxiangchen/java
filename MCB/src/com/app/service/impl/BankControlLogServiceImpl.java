package com.app.service.impl;


 
import java.util.List;
import java.util.Map;

import com.app.entity.BankControlLog;
import com.app.entity.BankControlShow;
import com.app.mapper.BankControlLogMapper;
import com.app.service.BankControlLogService;

 
public class BankControlLogServiceImpl implements BankControlLogService{
	 
	private BankControlLogMapper  bankControlLogMapper;
	   

	public BankControlLogMapper getBankControlLogMapper() {
		return bankControlLogMapper;
	}
 
	public void setBankControlLogMapper(BankControlLogMapper bankControlLogMapper) {
		this.bankControlLogMapper = bankControlLogMapper;
	}



	@Override
	public List<BankControlLog> selectBankControlLogList(
			BankControlLog bankControlLog) {
		 
		List<BankControlLog> list=bankControlLogMapper.selectBankControlLogPageList(bankControlLog);
		return list;
	}

	@Override
	public BankControlLog selectControlLogById(String controlId) {
		 
		return bankControlLogMapper.selectControlLogById(controlId);
	}

	@Override
	public List<String> selectControCauseNo(Map<String,String> map) {
		 
		return bankControlLogMapper.selectControCauseNo(map);
	}

	@Override
	public int selectControlCount(Map<String, String> map) {
		 
		return bankControlLogMapper.selectControlCount(map);
	}

	@Override
	public int insertControlShow(BankControlShow bankControlShow) {
		 
		return bankControlLogMapper.insertControlShow(bankControlShow);
	}

	@Override
	public String selectControlConfig(String controlCauseNo) {
		 
		return bankControlLogMapper.selectControlConfig(controlCauseNo);
	}

	@Override
	public List<BankControlShow> selectBankControlShowList(
			BankControlShow bankControlShow) {
		 
		List<BankControlShow> list=bankControlLogMapper.selectBankControlShowPageList(bankControlShow);
		return list;
	}

	@Override
	public int delControlShow(String[] strs) {
		 
		return bankControlLogMapper.delControlShow(strs);
	}

	@Override
	public List<Map<String,String>> selectControlConfigAll() {
		 
		return bankControlLogMapper.selectControlConfigAll();
	}

	@Override
	public int updateControlConfig(Map<String,String> map) {
		 
		return bankControlLogMapper.updateControlConfig(map);
	}

	@Override
	public int delControlLog(String bfDate) {
		 
		return bankControlLogMapper.delControlLog(bfDate);
	}

	 
	
	 
	 

	 
	 
}
