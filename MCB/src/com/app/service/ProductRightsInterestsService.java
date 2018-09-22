package com.app.service;

import java.util.List;

import com.app.entity.ProductRightsInterests;

/**
 * 产品权益服务接口 create date 2016/1/21 <br/>
 * 
 * @author shiguangting@tansun.com.cn
 * 
 */
public interface ProductRightsInterestsService {
	/**
	 * 查询产品权益,分页
	 * 
	 * @param productRightsInterests
	 * @return
	 */
	public List<ProductRightsInterests> productRightsInterestsPageList(
			ProductRightsInterests productRightsInterests);
}