package com.app.service.impl;

import java.util.List;

import com.app.mapper.MarketPictureMapper;
import com.app.service.MarketPictureService;
@SuppressWarnings({"rawtypes"})
public class MarketPictureServiceImp implements MarketPictureService{
	private MarketPictureMapper marketPictureMapper;
	public MarketPictureMapper getMarketPictureMapper() {
		return marketPictureMapper;
	}
	public void setMarketPictureMapper(MarketPictureMapper marketPictureMapper) {
		this.marketPictureMapper = marketPictureMapper;
	}
	@Override
	/**
	 * 查询营销图片
	 * @param page 页数
	 * @return
	 */
	public List selectMarketPictureList() {
		return marketPictureMapper.selectMarketPictureList();
	}
	/**
	 * 根据图片ID查询图片名称
	 * @param strs
	 * @return
	 */
	/*public List selectActPicByPicId(String[] strs) {
		// TODO Auto-generated method stub
		return marketPictureMapper.selectActPicByPicId(strs);
	}*/

	
	 
}
