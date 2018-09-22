package com.app.mapper;

import java.util.List;

import com.app.entity.RightsPackagerkMapping;

/**
 * 权益映射配置接口
 * create date 2016/1/20
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface RightsPackagerkMappingMapper {
	/**
	 * 查询权益映射信息,分页
	 * @param rightsInterestsMapping
	 * @return
	 */
	public List<RightsPackagerkMapping> rightsPackagerkMappingPageList(RightsPackagerkMapping rightsPackagerkMapping);
	public int saveRightsMap(RightsPackagerkMapping rightsPackagerkMapping);
	public int delRightsMapById(String[] strs);
	public int selectRightsCount(String rightsPackagerkCode);
	public RightsPackagerkMapping editRightsMapById(String rightsPackagerkId);
	public int updateRightsMap(RightsPackagerkMapping rightsPackagerkMapping);
}
