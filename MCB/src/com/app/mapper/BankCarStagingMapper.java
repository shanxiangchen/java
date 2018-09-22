package com.app.mapper;

import java.util.List;

import com.app.entity.BankCarStaging;

/**
 * 汽车分期,经销商 配置接口
 * create date 2016/06/30
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface BankCarStagingMapper {
	/**
	 * 查询汽车分期，经销商，分页
	 * @param bankCarStaging
	 * @return
	 */
	public List<BankCarStaging>selbankCarStagingPageList(BankCarStaging bankCarStaging);
	/**
	 * 查询汽车分期，经销商总条数
	 * @param bankCarStaging
	 * @return
	 */
	public int getCount(BankCarStaging bankCarStaging);
	/**
	 * 导出数据
	 * @param bankCarStaging
	 * @return
	 */
	public List<BankCarStaging> expCarStaging(BankCarStaging bankCarStaging);
	
	public int  selectStoreNoList(String carStagingCityCode);
	
	public void delCarStaging(String carStagingCityCode);
	
	public int getBankCarStagingCityByCode(BankCarStaging bankCarStaging);
	public BankCarStaging getBankCarStagingId(BankCarStaging tmp);
	public void deleteBankCarStagingById(int bankCarStaingId);
	public int getBankCarStagingByCode(BankCarStaging tmp);
	public void deleteBankCarStagingByCode(String carStagingCode);
	public int getBankcarStagingNum(BankCarStaging sto);
}
