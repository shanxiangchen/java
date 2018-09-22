package com.app.service;

import java.util.List;

import com.app.entity.BankCarStaging;

/**
 * 汽车分期，经销商，服务接口
 * <br/>
 * create date 2016/06/30
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface BankCarStagingService {
	
	public 	List<BankCarStaging> selbankCarStagingPageList(BankCarStaging bankCarStaging);
	
	/**
	 * 导出数据
	 * @param bankCarStaging
	 * @return
	 */
	public List<BankCarStaging> expCarStaging(BankCarStaging bankCarStaging);
	
	public int  selectStoreNoList(String carStagingCityCode);
	
	void getJdbcCon(List<BankCarStaging> insertList);
	
	public void deleteCarStaging(String carStagingCityCode);
	
	public int getBankCarStagingCityByCode(BankCarStaging carStagingCityCode);
	public BankCarStaging getBankCarStagingId(BankCarStaging tmp);
	public void deleteBankCarStagingById(int bankCarStaingId);
	public int getBankCarStagingByCode(BankCarStaging tmp);
	public void deleteBankCarStagingByCode(String carStagingCode);
	public int getBankcarStagingNum(BankCarStaging sto);
}
