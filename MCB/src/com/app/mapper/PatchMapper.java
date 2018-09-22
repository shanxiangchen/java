package com.app.mapper;

import java.util.List;

import com.app.entity.Patch;

/**
 *补件类型配置接口
 *create date 2016/1/8
 *<br/>
 *@author shiguangting@tansun.com.cn
 *
 */
public interface PatchMapper {
	/**
	 * 实现补件类型查询,分页
	 * @param patch
	 * @return
	 */
	public List<Patch> patchPageList(Patch patch);
	
	/**
	 * 实现删除补件类型数据
	 * @param patchTypeId
	 */
	public void delPatch(int patchTypeId);
	/**
	 * 实现添加补件类型
	 * @param patch
	 */
	public void insertPatch(Patch patch);
	/**
	 * 判断补件编码是否存在
	 * @param patch
	 * @return
	 */
	public int getCountNewNo(String patchTypeCode);
}
