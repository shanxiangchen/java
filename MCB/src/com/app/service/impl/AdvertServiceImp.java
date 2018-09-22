package com.app.service.impl;

import java.util.List;

import com.app.entity.Advert;
import com.app.entity.ModuleFunctionName;
import com.app.mapper.AdvertMapper;
import com.app.service.AdvertService;

@SuppressWarnings({"rawtypes","unchecked"})
public class AdvertServiceImp implements AdvertService{

	private AdvertMapper  advertMapper;

	public AdvertMapper getAdvertMapper() {
		return advertMapper;
	}


	public void setAdvertMapper(AdvertMapper advertMapper) {
		this.advertMapper = advertMapper;
	}


	@Override
	public List selectAdvertListPage(Advert ad) {
		 
		return advertMapper.selectAdvertListPage(ad);
	}


	@Override
	public int insertAdvert(Advert ad) {
		 
		return advertMapper.insertAdvert(ad);
	}


	@Override
	public List selectAdvertById(String id) {
		 
		return advertMapper.selectAdvertById(id);
	}


	@Override
	public int updateAdvertById(Advert ad) {
		 
		return advertMapper.updateAdvertById(ad);
	}


	@Override
	public int deleteAdvertById(String[] str) {
		 
		return advertMapper.deleteAdvertById(str);
	}


	@Override
	public int updateAdvertByIds(Advert ad) {
		 
		return advertMapper.updateAdvertByIds(ad);
	}


	@Override
	public List<ModuleFunctionName> selectAdvertListPage(ModuleFunctionName moduleFunctionName) {
		 
		List<ModuleFunctionName> list=advertMapper.selectAdvertListPage(moduleFunctionName);
		return list;
	}


	@Override
	public List selectAllModuleFunctionName() {
		 
		return advertMapper.selectAllModuleFunctionName();
	}


	@Override
	public List selectAdvertPicNameByAdvertId(String strs[]) {
		 
		return advertMapper.selectAdvertPicNameByAdvertId(strs);
	}

 
	@Override
	public List selectAllLinkName() {
		 
		return advertMapper.selectAllLinkName();
	}

	
}
