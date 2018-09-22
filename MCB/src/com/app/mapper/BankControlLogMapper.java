package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.BankControlLog;
import com.app.entity.BankControlShow;
 
 
public interface BankControlLogMapper {
	 
	public List<BankControlLog> selectBankControlLogPageList(BankControlLog bankControlLog);
	public BankControlLog selectControlLogById(String controlId);
	public List<String> selectControCauseNo(Map<String,String> map);
	public int selectControlCount(Map<String,String> map);
	public int insertControlShow(BankControlShow bankControlShow);
	public String selectControlConfig(String controlCauseNo);
	
	public List<BankControlShow> selectBankControlShowPageList(BankControlShow bankControlShow);
	public int getShowCount(BankControlShow bankControlShow);
	public int delControlShow(String[] strs);
	public List<Map<String,String>> selectControlConfigAll();
	public int updateControlConfig(Map<String,String> map);
	public int delControlLog(String controlDate);
	 
	 
}
