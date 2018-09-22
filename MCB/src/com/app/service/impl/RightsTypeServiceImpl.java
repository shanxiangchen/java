package com.app.service.impl;

import java.util.List;

import com.app.entity.RightsType;
import com.app.mapper.RigthsTypeMapper;
import com.app.service.RightsTypeService;

/**
 * 权益类型服务接口实现类 create date 2016/1/28 <br/>
 * 
 * @author shiguangting@tansun.com.cn
 * 
 */
public class RightsTypeServiceImpl implements RightsTypeService {
	private RigthsTypeMapper rigthsTypeMapper;

	public RigthsTypeMapper getRigthsTypeMapper() {
		return rigthsTypeMapper;
	}

	public void setRigthsTypeMapper(RigthsTypeMapper rigthsTypeMapper) {
		this.rigthsTypeMapper = rigthsTypeMapper;
	}

	@Override
	public List<RightsType> rightsTypePageList(RightsType rightsType) {
		List<RightsType> rightsTypes = rigthsTypeMapper
				.rightsTypePageList(rightsType);
		return rightsTypes;
	}

	@Override
	public void saveRigthsType(RightsType rightsType) {
		rigthsTypeMapper.saveRigthsType(rightsType);
	}

	@Override
	public void deleteRigthsType(String rigthsTypeId) {
		rigthsTypeMapper.delRigthsType(rigthsTypeId);
	}

	@Override
	public RightsType selectRigthsTypeByid(String rigthsTypeId) {
		return rigthsTypeMapper.selectRigthsTypeByid(rigthsTypeId);
	}

	@Override
	public List<RightsType> listrigthsSel() {
		return rigthsTypeMapper.listrigthsSel();
	}

	@Override
	public void updateRigthsType(RightsType rightsType) {
		rigthsTypeMapper.editRigthsType(rightsType);
	}

	@Override
	public int getRightsTypeCodeByCode(String rightsTypeCode) {
		return rigthsTypeMapper.getRightsTypeCodeByCode(rightsTypeCode);
	}


}
