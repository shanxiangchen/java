package com.app.mapper;

import java.util.List;

import com.app.entity.BankCarBrandDetails;
public interface BankCarBrandDetailsMapper {
	/**
	 * 查询分页
	 * @param Beauideal
	 * @return
	 */
	
	public List<BankCarBrandDetails> bankCarBrandDetailsPageList(BankCarBrandDetails details);
	public int getCount(BankCarBrandDetails detailsId);
	/**
	 * 根据ID查询
	 * @param beauidealExperienceId
	 * @return
	 */
	public BankCarBrandDetails getCarBrandDetailsbyid(String detailsId);
	
	
	
	public int insertCarBrandDetails(BankCarBrandDetails brandDetails);
	
	public int updateCarBrand(BankCarBrandDetails brandDetails);
	
	
	public List<BankCarBrandDetails> listCarBrandDetailsByIds(String detailsId);
	
	public int deleteCarBrandDetails(String detailsId);
	
	
	/**
	 * 查询新增显示品牌id
	 * @return
	 */
	public List<BankCarBrandDetails> selectCarBrandDetails();
}
