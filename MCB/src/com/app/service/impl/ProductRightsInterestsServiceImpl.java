package com.app.service.impl;

import java.util.List;

import com.app.entity.ProductRightsInterests;
import com.app.mapper.ProductRightsInterestsMapper;
import com.app.service.ProductRightsInterestsService;

/**
 * 产品权益服务接口实现类
 * create date 2016/1/21
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class ProductRightsInterestsServiceImpl implements ProductRightsInterestsService{
	private ProductRightsInterestsMapper productRightsInterestsMapper;

	public ProductRightsInterestsMapper getProductRightsInterestsMapper() {
		return productRightsInterestsMapper;
	}

	public void setProductRightsInterestsMapper(
			ProductRightsInterestsMapper productRightsInterestsMapper) {
		this.productRightsInterestsMapper = productRightsInterestsMapper;
	}

	@Override
	public List<ProductRightsInterests> productRightsInterestsPageList(
			ProductRightsInterests productRightsInterests) {
		List<ProductRightsInterests> rightsInterests = productRightsInterestsMapper.productRightsInterestsPageList(productRightsInterests);
		return rightsInterests;
	}

		
	
	
}
 