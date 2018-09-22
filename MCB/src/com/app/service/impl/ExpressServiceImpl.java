package com.app.service.impl;

import java.util.List;

import com.app.entity.Express;
import com.app.mapper.ExpressMapper;
import com.app.service.ExpressService;

public class ExpressServiceImpl implements ExpressService {
	
	private ExpressMapper expressMapper;
	
	public ExpressMapper getExpressMapper() {
		return expressMapper;
	}

	public void setExpressMapper(ExpressMapper expressMapper) {
		this.expressMapper = expressMapper;
	}

	@Override
	public List<Express> listPageExpress(Express express) {
		 
		List<Express> list=expressMapper.listPageExpress(express);
		return list;
	}

	@Override
	public boolean insertExpress(Express express) {
		 
			expressMapper.insertExpress(express);
		return true;
	}

	@Override
	public void deleteExpress(int expressServiceNameId) {
		// TODO Auto-generated method stub
		expressMapper.deleteExpress(expressServiceNameId);
	}

	@Override
	public Express getExpressById(int expressServiceNameId) {
		// TODO Auto-generated method stub
		return expressMapper.getExpressById(expressServiceNameId);
	}

	@Override
	public void updatexpressInfo(Express express) {
		// TODO Auto-generated method stub
		expressMapper.updatexpressInfo(express);
	}
	
	
}
