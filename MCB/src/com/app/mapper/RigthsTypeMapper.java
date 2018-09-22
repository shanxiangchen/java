package com.app.mapper;

import java.util.List;

import com.app.entity.RightsType;

/**
 * 权益类型配置接口
 * create date 2016/1/28
 * <br/>
 * @author shiguanting@tanusn.com.cn
 *
 */
public interface RigthsTypeMapper {
	/**
	 * 权益类型查询,分页
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
	public void delRigthsType(String rigthsTypeId);
	
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
	 
	public void editRigthsType(RightsType rightsType);
	
	public int getRightsTypeCodeByCode(String rightsTypeCode);
	
}
