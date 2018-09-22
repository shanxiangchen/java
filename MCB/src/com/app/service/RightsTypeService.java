package com.app.service;

import java.util.List;

import com.app.entity.RightsType;

/**
 * 权益类型服务接口
 * create date 2016/1/28
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface RightsTypeService {
	/**
	 * 权益查询，分页
	 * @param rightsType
	 * @return
	 */
	public List<RightsType> rightsTypePageList(RightsType rightsType);
	
	/**
	 * 添加权益类型信息
	 * @param rightsType
	 */
	public void saveRigthsType(RightsType rightsType);
	
	/**
	 * 按Id删除权益类型信息数据
	 * @param rigthsTypeId
	 */
	public void deleteRigthsType(String rigthsTypeId);
	/**
	 *查询权益单条数据 
	 * @param rigthsTypeId
	 * @return
	 */
	public RightsType selectRigthsTypeByid(String rigthsTypeId);
	
	/**
	 * 查询权益类型
	 * @return
	 */
	public List<RightsType>listrigthsSel();
	
	 
	
	public void updateRigthsType(RightsType rightsType);
	
	
	public int getRightsTypeCodeByCode(String rightsTypeCode);
	
}
