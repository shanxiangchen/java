package com.app.service;

import java.util.List;

import com.app.entity.Gprs;

public interface GprsService {
	public List<Gprs> findAllGprs();
	public List<Gprs>getGprs();
	Gprs  getGprsById(String cityno);
}
