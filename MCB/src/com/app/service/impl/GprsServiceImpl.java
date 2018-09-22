package com.app.service.impl;

import java.util.List;

import com.app.entity.Gprs;
import com.app.mapper.GprsMapper;
import com.app.service.GprsService;

public class GprsServiceImpl implements GprsService{

	
   private GprsMapper gprsMapper;
   
	public GprsMapper getGprsMapper() {
	return gprsMapper;
}

public void setGprsMapper(GprsMapper gprsMapper) {
	this.gprsMapper = gprsMapper;
}

	@Override
	public List<Gprs> findAllGprs() {
		// TODO Auto-generated method stub
		return gprsMapper.findAllGprs();
	}

	@Override
	public List<Gprs> getGprs() {
		// TODO Auto-generated method stub
		return gprsMapper.getGprs();
	}

	@Override
	public Gprs getGprsById(String cityno) {
		// TODO Auto-generated method stub
		return gprsMapper.getGprsById(cityno);
	}

}
