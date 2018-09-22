package com.app.mapper;

import java.util.List;

import com.app.entity.Type;



public interface TypeMapper {
	List<Type> listPagetype(Type type);
	List<Type> listPagetypes(Type type);
	int getCount(Type type);
	Type gettypeById(String oddsshoptypeid);
	int inserttype(Type type);
	int updatetype(Type type);
	int deletetype(String oddsshoptypeid);
	
	int getCountByName(String oddsshoptype);
}
