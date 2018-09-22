package com.app.service.impl;

import java.util.List;

import com.app.entity.StagingType;
import com.app.mapper.StagingTypeMapper;
import com.app.service.StagingTypeService;

public class StagingTypeServiceImpl implements StagingTypeService {

	private StagingTypeMapper stagingTypeMapper;

	public StagingTypeMapper getStagingTypeMapper() {
		return stagingTypeMapper;
	}

	public void setStagingTypeMapper(StagingTypeMapper stagingTypeMapper) {
		this.stagingTypeMapper = stagingTypeMapper;
	}
	//分期类型分页查询
	@Override
	public List<StagingType> stagingTypePageList(StagingType stagingType) {
		List<StagingType> list=stagingTypeMapper.stagingTypePageList(stagingType);
		return list;
	}
	//添加分期类型信息数据
	@Override
	public boolean insertStagingType(StagingType stagingType) {
		int countNewNo=stagingTypeMapper.getCountNewNo(stagingType);
		if(countNewNo>0){
			return false;
		}
		stagingTypeMapper.insertStagingType(stagingType);
		return true;
	}
	//按分期类型Id查询单条信息数据
	@Override
	public StagingType stagingTypeById(int id) {
		 
		return stagingTypeMapper.stagingTypeById(id);
	}	
	//编辑分期类型
	@Override
	public void updateStagingType(StagingType stagingType) {
		 
		stagingTypeMapper.stagingTypeEdit(stagingType);
	}
	//删除单条数据
	@Override
	public void deleteStagingType(int id) {
		 
		stagingTypeMapper.stagingTypeDel(id);
	}
	//实现删除的时候判断这条数据是否有关联
	@Override
	public int getCountCode(int stagCode) {
		 
		int i=stagingTypeMapper.getCountCode(stagCode);
		return i;
	}

}
