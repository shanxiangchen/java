package com.app.service;
import java.util.List;

import com.app.entity.RightsPackagerkMapping;
/**
 * 权益映射服务接口
 * create date 2016/1/20
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface RightsInterestsMappingService {
	/**
	 * 查询权益映射信息,分页
	 * @param rightsInterestsMapping
	 * @return
	 */
 public List<RightsPackagerkMapping> rightsPackagerkMappingPageList(RightsPackagerkMapping rightsPackagerkMapping);
 public int saveRightsMap(RightsPackagerkMapping rightsPackagerkMapping);
 public int deleteRightsMapById(String[] strs);
 public int selectRightsCount(String rightsPackagerkCode);
 public RightsPackagerkMapping editRightsMapById(String rightsPackagerkId);
 public int updateRightsMap(RightsPackagerkMapping rightsPackagerkMapping);

}
