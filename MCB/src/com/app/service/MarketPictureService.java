package com.app.service;

import java.util.List;

public interface MarketPictureService {
	/**
	 * 查询营销图片
	 * @param page 页数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List  selectMarketPictureList();
	/**
	 * 根据图片ID查询图片名称
	 * @param strs
	 * @return
	 *//*
	List selectActPicByPicId(String[] strs);*/
}
