package com.app.service.impl;

import java.util.List;

import com.app.entity.RightsPackagerkMapping;
import com.app.mapper.RightsPackagerkMappingMapper;
import com.app.service.RightsInterestsMappingService;

public class RightsInterestsMappingServiceImpl implements
		RightsInterestsMappingService {
	private RightsPackagerkMappingMapper rightsPackagerkMappingMapper;

	public RightsPackagerkMappingMapper getRightsPackagerkMappingMapper() {
		return rightsPackagerkMappingMapper;
	}

	public void setRightsPackagerkMappingMapper(
			RightsPackagerkMappingMapper rightsPackagerkMappingMapper) {
		this.rightsPackagerkMappingMapper = rightsPackagerkMappingMapper;
	}

	@Override
	public List<RightsPackagerkMapping> rightsPackagerkMappingPageList(
			RightsPackagerkMapping rightsPackagerkMapping) {
		List<RightsPackagerkMapping> list = rightsPackagerkMappingMapper
				.rightsPackagerkMappingPageList(rightsPackagerkMapping);
		return list;
	}

	@Override
	public int saveRightsMap(RightsPackagerkMapping rightsPackagerkMapping) {

		return rightsPackagerkMappingMapper
				.saveRightsMap(rightsPackagerkMapping);
	}

	@Override
	public int deleteRightsMapById(String[] strs) {

		return rightsPackagerkMappingMapper.delRightsMapById(strs);
	}

	@Override
	public int selectRightsCount(String rightsPackagerkCode) {

		return rightsPackagerkMappingMapper
				.selectRightsCount(rightsPackagerkCode);
	}

	@Override
	public RightsPackagerkMapping editRightsMapById(String rightsPackagerkId) {

		return rightsPackagerkMappingMapper
				.editRightsMapById(rightsPackagerkId);
	}

	@Override
	public int updateRightsMap(RightsPackagerkMapping rightsPackagerkMapping) {

		return rightsPackagerkMappingMapper
				.updateRightsMap(rightsPackagerkMapping);
	}

}
