package com.app.mapper;
import java.util.List;

import com.app.entity.BankCarBrand;
public interface BankCarBrandMapper {

	

	/**
	 * 查询分页
	 * @param Beauideal
	 * @return
	 */
	public List<BankCarBrand> carBrandPageList(BankCarBrand carBrand);
	/**
	 * 根据ID查询
	 * @param beauidealExperienceId
	 * @return
	 */
	public BankCarBrand getCarBrandbyid(String brandId);
	
	
	
	public int deleteCarBrand(String brandId);
	
	
	public int updateCarBrand(BankCarBrand carBrand);

	
	public int insertCarBrand(BankCarBrand carBrand);
		
	
	public List<BankCarBrand> listCarBrandByIds(String brandId);
	
	public String selectBankCarBrandNextVal();
	
	public List<String> selectDetailsIdById(String brandId);
}
