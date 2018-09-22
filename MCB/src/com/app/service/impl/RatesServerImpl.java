package com.app.service.impl;

import java.util.List;

import com.app.entity.Ratess;
import com.app.mapper.RatesMapper;
import com.app.service.RatesServer;

public class RatesServerImpl implements RatesServer{

	private RatesMapper  ratesMapper;
	
	public RatesMapper getRatesMapper() {
		return ratesMapper;
	}

	public void setRatesMapper(RatesMapper ratesMapper) {
		this.ratesMapper = ratesMapper;
	}

	@Override
	public List<Ratess> listbankrates(Ratess ratess) {
		
		return ratesMapper.listbankrates(ratess);
	}

	@Override
	public Ratess getStatess(int id) {
		return ratesMapper.getStatess(id);
	}

	@Override
	public void updateStatess(Ratess rates) {
		ratesMapper.updateStatess(rates);
		
	}

}
