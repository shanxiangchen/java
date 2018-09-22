package com.app.service;

import java.util.List;

import com.app.entity.Ratess;

public interface RatesServer {
	public List<Ratess> listbankrates(Ratess rates);
	public Ratess getStatess(int id);
	
	public void updateStatess(Ratess rates);
}
