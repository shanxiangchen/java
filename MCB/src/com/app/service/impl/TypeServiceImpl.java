package com.app.service.impl;

import java.util.List;

import com.app.entity.Type;
import com.app.mapper.TypeMapper;
import com.app.service.TypeService;


public  class TypeServiceImpl implements TypeService{
	private TypeMapper typeMapper;

	public List<Type> listPagetype(Type type) {
		return typeMapper.listPagetype(type);
	}
	public List<Type> listPagetypes(Type type) {
		 
		List<Type> list=typeMapper.listPagetypes(type);
		return list;
	}
	
	public Type gettypeById(String oddsshoptypeid) {
		return typeMapper.gettypeById(oddsshoptypeid);
	}
	
	
	public int deletetype(String oddsshoptypeid) {
		int deletet=typeMapper.deletetype(oddsshoptypeid);
		return deletet;
	}
	

	@Override
	public void savertype(Type type) {
		typeMapper.updatetype(type);		
	}


	public TypeMapper getTypeMapper() {
		return typeMapper;
	}


	public void setTypeMapper(TypeMapper typeMapper) {
		this.typeMapper = typeMapper;
	}

	
	@Override
	public boolean savetype(Type type) {
		int count = typeMapper.getCountByName(type.getOddsshoptype());
		//System.out.println(count+"00000000000000");
		if(count>0){
			//System.out.println("++++++++++++++");
			return false;
			
		}else{
			typeMapper.inserttype(type);
		//	System.out.println("---------------");
			return true;
		}
		
	}

	
}
