package com.app.mapper;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface MarketPictureMapper {
	
	List selectMarketPictureList();
	 /**
	  * 根据图片ID查询图片名称
	  * @param strs
	  * @return
	  */
	
	List selectActPicByPicId(String strs[]);
	
	String selectPicNameByid(String pictureId);
}
