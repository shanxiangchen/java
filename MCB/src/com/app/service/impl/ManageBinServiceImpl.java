package com.app.service.impl;

import java.util.List;

import com.app.entity.ManageBin;
import com.app.mapper.ManageBinMapper;
import com.app.service.ManageBinService;

public class ManageBinServiceImpl implements ManageBinService{

	private ManageBinMapper manageBinMapper;

	public ManageBinMapper getManageBinMapper() {
		return manageBinMapper;
	}

	public void setManageBinMapper(ManageBinMapper manageBinMapper) {
		this.manageBinMapper = manageBinMapper;
	}

	@Override
	public List<ManageBin> listPageManageBin(ManageBin manageBin) {
		 
		List<ManageBin> list=manageBinMapper.listPageManageBin(manageBin);
		 
		return list;
	}

	@Override
	public boolean insertManageBin(ManageBin manageBin) {
		 
		manageBinMapper.insertManageBin(manageBin);
		return true;
	}

	@Override
	public void deleteManageBinById(int binProductId) {
		 
		manageBinMapper.deleteManageBinById(binProductId);
	}

	@Override
	public ManageBin getManageBinById(int binProductId) {
		 
		return manageBinMapper.getManageBinById(binProductId);
	}

	@Override
	public void updateManageBinInfo(ManageBin manageBin) {
		 
		manageBinMapper.updateManageBinInfo(manageBin);
	}
	
	
	
}
