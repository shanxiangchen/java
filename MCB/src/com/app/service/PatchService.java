package com.app.service;


import java.util.List;

import com.app.entity.Patch;

/**
 * 补件类型服务接口
 * create date 2016/1/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface PatchService {
	/**
	 * 补件类型查询,分页
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
	 *  实现添加补件类型
	 * @param patch
	 */
	public boolean insertPatch(Patch patch);
}
