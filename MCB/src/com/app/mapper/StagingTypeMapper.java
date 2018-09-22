package com.app.mapper;

import java.util.List;

import com.app.entity.StagingType;

public interface StagingTypeMapper {
	/***
	 * 分页查询
	 * @param stagingType
	 * @return
	 */
	public List<StagingType> stagingTypePageList(StagingType stagingType);
	public int getCount(StagingType stagingType);
	/**
	 * 判断是否唯一
	 * @param stagingType
	 * @return
	 */
	public int getCountNewNo(StagingType stagingType);
	/**
	 * 添加分期类型信息数据
	 * @param stagingType
	 */
	public void insertStagingType(StagingType stagingType);
	/**
	 * 按分期类型Id查询单条信息数据
	 * @param id
	 * @return
	 */
	public StagingType stagingTypeById(int id);
	/***
	 * 编辑分期类型
	 * @param stagingType
	 */
	public void stagingTypeEdit(StagingType stagingType);
	
	/***
	 * 删除单条数据
	 */
	public void stagingTypeDel(int id);
	/***
	 * 实现删除的时候判断这条数据是否有关联
	 * @param stagCode
	 * @return
	 */
	public int getCountCode(int stagCode);
}
