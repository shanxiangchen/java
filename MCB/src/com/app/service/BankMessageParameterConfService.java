package com.app.service;

import java.util.List;

import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageType;

public interface BankMessageParameterConfService {
	
	/**
	 * 查询消息盒子列表
	 * @param bankMessageParameterConf
	 * @return
	 */
	public List<BankMessageParameterConf> getBankMessageParameterConfPageList(BankMessageParameterConf bankMessageParameterConf);
	
	/**
	 * 查询消息盒子总数
	 * @param bankMessageParameterConf
	 * @return
	 */
	public int getBankMessageParameterConfCount(BankMessageParameterConf bankMessageParameterConf);
	
	/**
	 * 更新消息盒子信息
	 * @param bankMessageParameterConf
	 */
	public void updateBankMessageParameterConf(BankMessageParameterConf bankMessageParameterConf);
	
	/**
	 * 插入消息盒子信息
	 * @param bankMessageParameterConf
	 */
	public void insertBankMessageParameterConf(BankMessageParameterConf bankMessageParameterConf);
	/**
	 * 
	 * @return
	 */
	public List<BankMessageType> selBankMessageType();
	
	public BankMessageParameterConf selMessageParameterConfById(String bmpcId);
}
