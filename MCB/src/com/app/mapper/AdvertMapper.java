package com.app.mapper;

import java.util.List;

import com.app.entity.Advert;
import com.app.entity.ModuleFunctionName;
@SuppressWarnings("rawtypes")
public interface AdvertMapper {
	List selectAdvertListPage(Advert ad);
	
	int insertAdvert(Advert ad);
	List selectAdvertById(String id);
	
	int updateAdvertById(Advert ad);
	
	int deleteAdvertById(String[] str);
	
	int updateAdvertByIds(Advert ad);
	
	List selectAdvertListPage(ModuleFunctionName moduleFunctionName);
	
	List selectAllModuleFunctionName();
	
	List selectAllLinkName();
	 
	List selectAdvertPicNameByAdvertId(String strs[]);
	
	 
}
