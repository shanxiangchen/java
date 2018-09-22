package com.app.mapper;

import java.util.List;

import com.app.entity.Ratess;


public interface RatesMapper {

	public List<Ratess> listbankrates(Ratess rates);
	
	public Ratess getStatess(int id);
	
	public void updateStatess(Ratess rates);
	
	
}
