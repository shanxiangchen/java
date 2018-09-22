package com.app.mapper;

import java.util.List;

import com.app.entity.BankCity;

public interface BankCtyMapper {
	/**
	 * 查询城市所有信息数据
	 * @param bankCity
	 * @return
	 */
	public List<BankCity> listPageBankCty(BankCity bankCity);
	public List<BankCity> findAllCity();
	 
	/**
	 * 删除地区信息数据
	 * @param cardId
	 */
	public void deleteBankCityInfo(int cardId);
	/**
	 * 判断是否唯一
	 */
	public int getCountNewNo(String cityNo);
	/**
	 *新增地区信息数据
	 * @param bankCity
	 */
	public void insertBankCity(BankCity bankCity);
	/**
	 * 查询地区单个值信息数据
	 * @param cardId
	 * @return
	 */
	public BankCity getBankCityById(int cardId);
	/**
	 * 修改地区信息数据
	 * @param bankCity
	 */
	public void updateBankCity(BankCity bankCity);
	/**
	 * 获取城市
	 * @author 黄成
	 * @param carStagingCityCode
	 * @return
	 */
	public BankCity getBankCityByCode(String carStagingCityCode);
	
}
