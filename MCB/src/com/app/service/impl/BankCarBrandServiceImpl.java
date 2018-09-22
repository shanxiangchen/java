package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCarBrand;
import com.app.mapper.BankCarBrandMapper;
import com.app.service.BankCarBrandService;
 

public class BankCarBrandServiceImpl implements BankCarBrandService {
	private BankCarBrandMapper brandMapper;

	public BankCarBrandMapper getBrandMapper() {
		return brandMapper;
	}

	public void setBrandMapper(BankCarBrandMapper brandMapper) {
		this.brandMapper = brandMapper;
	}


	@Override
	public List<BankCarBrand> carBrandPageList(BankCarBrand carBrand) {
		List<BankCarBrand> bankCarBrands = brandMapper.carBrandPageList(carBrand);
		return bankCarBrands;
	}

	@Override
	public BankCarBrand getCarBrandbyid(String brandId) {
		return brandMapper.getCarBrandbyid(brandId);
	}



	@Override
	public int insertCarBrand(BankCarBrand carBrand) {

		return brandMapper.insertCarBrand(carBrand);
	}

	@Override
	public void updateCarBrand(BankCarBrand carBrand) {
		brandMapper.updateCarBrand(carBrand);
	}

	@Override
	public List<BankCarBrand> listCarBrandByIds(String brandId) {
		return  brandMapper.listCarBrandByIds(brandId);
	}

	@Override
	public int deleteCarBrand(String brandId) {
		
		
		return brandMapper.deleteCarBrand(brandId);
	}

	@Override
	public String selectBankCarBrandNextVal() {
		 
		return brandMapper.selectBankCarBrandNextVal();
	}

	@Override
	public List<String> selectDetailsIdById(String brandId) {
		 
		return brandMapper.selectDetailsIdById(brandId);
	}





}
