package com.app.service;

import java.util.List;

import com.app.entity.Advert;
import com.app.entity.ModuleFunctionName;
@SuppressWarnings("rawtypes")
public interface AdvertService {
  List selectAdvertListPage(Advert ad);
  int insertAdvert(Advert ad);
  List selectAdvertById(String id);
  int updateAdvertById(Advert ad);
  int deleteAdvertById(String str[]);
  int updateAdvertByIds(Advert ad);
  List<ModuleFunctionName> selectAdvertListPage(ModuleFunctionName moduleFunctionName);
  List  selectAllModuleFunctionName();
  List selectAllLinkName();
  List selectAdvertPicNameByAdvertId(String strs[]);
  
}
