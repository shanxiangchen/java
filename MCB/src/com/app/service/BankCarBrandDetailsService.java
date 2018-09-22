package com.app.service;


import java.util.List;

import com.app.entity.BankCarBrandDetails;

public interface BankCarBrandDetailsService {

	public List<BankCarBrandDetails> bankCarBrandDetailsPageList(BankCarBrandDetails details);
	
	public BankCarBrandDetails getCarBrandDetailsbyid(String detailsId);
	
	
	public int insertCarBrandDetails(BankCarBrandDetails brandDetails);
	
	void updateCarBrand(BankCarBrandDetails brandDetails);
	
	public List<BankCarBrandDetails> listCarBrandDetailsByIds(String detailsId);
	
	int deleteCarBrandDetails(String detailsId);
	
	public List<BankCarBrandDetails> selectCarBrandDetails();
}
