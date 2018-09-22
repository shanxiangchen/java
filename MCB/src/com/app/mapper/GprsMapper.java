package com.app.mapper;

import java.util.List;

import com.app.entity.Gprs;


public interface GprsMapper {
	public List<Gprs> findAllGprs();
	public List<Gprs> getGprs();
	Gprs  getGprsById(String cityno);
}
