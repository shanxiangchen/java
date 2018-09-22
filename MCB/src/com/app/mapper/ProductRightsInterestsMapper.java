package com.app.mapper;

import java.util.List;

import com.app.entity.ProductRightsInterests;

/**
 * 产品权益配置接口
 * create date 2016/1/21 
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface ProductRightsInterestsMapper {
	/**
	 * 查询产品权益,分页
	 * @param productRightsInterests
	 * @return
	 */
	public List<ProductRightsInterests> productRightsInterestsPageList(ProductRightsInterests productRightsInterests);
}
