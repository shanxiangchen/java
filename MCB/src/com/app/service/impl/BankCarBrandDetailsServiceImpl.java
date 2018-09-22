package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCarBrandDetails;
import com.app.mapper.BankCarBrandDetailsMapper;
import com.app.service.BankCarBrandDetailsService;

public class BankCarBrandDetailsServiceImpl implements
		BankCarBrandDetailsService {

	private BankCarBrandDetailsMapper detailsMapper;

	public BankCarBrandDetailsMapper getDetailsMapper() {
		return detailsMapper;
	}

	public void setDetailsMapper(BankCarBrandDetailsMapper detailsMapper) {
		this.detailsMapper = detailsMapper;
	}

	@Override
	public List<BankCarBrandDetails> bankCarBrandDetailsPageList(
			BankCarBrandDetails details) {
		List<BankCarBrandDetails> bankCarBrandDetails = detailsMapper
				.bankCarBrandDetailsPageList(details);
		return bankCarBrandDetails;
	}

	@Override
	public BankCarBrandDetails getCarBrandDetailsbyid(String detailsId) {
		return detailsMapper.getCarBrandDetailsbyid(detailsId);
	}

	@Override
	public int insertCarBrandDetails(BankCarBrandDetails brandDetails) {
		return detailsMapper.insertCarBrandDetails(brandDetails);
	}

	@Override
	public void updateCarBrand(BankCarBrandDetails brandDetails) {
		detailsMapper.updateCarBrand(brandDetails);
	}

	@Override
	public List<BankCarBrandDetails> listCarBrandDetailsByIds(String detailsId) {
		return detailsMapper.listCarBrandDetailsByIds(detailsId);
	}

	@Override
	public int deleteCarBrandDetails(String detailsId) {
		return detailsMapper.deleteCarBrandDetails(detailsId);
	}

	@Override
	public List<BankCarBrandDetails> selectCarBrandDetails() {
		return detailsMapper.selectCarBrandDetails();
	}
}
