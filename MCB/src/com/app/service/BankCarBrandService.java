package com.app.service;

import java.util.List;

import com.app.entity.BankCarBrand;

public interface BankCarBrandService {
	
	public List<BankCarBrand> carBrandPageList(BankCarBrand carBrand);
	
	public BankCarBrand getCarBrandbyid(String brandId);
		
   
    
    void updateCarBrand(BankCarBrand carBrand);
    
    public int insertCarBrand(BankCarBrand carBrand);
	public String selectBankCarBrandNextVal();
    
	/**
	 * 查询所有的图片名称
	 * @param CardId
	 * @return
	 */
	public List<BankCarBrand> listCarBrandByIds(String brandId);

	int deleteCarBrand(String brandId);
	
	public List<String> selectDetailsIdById(String brandId);
}
