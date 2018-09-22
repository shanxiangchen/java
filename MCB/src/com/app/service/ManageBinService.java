package com.app.service;


import java.util.List;

import com.app.entity.ManageBin;

public interface ManageBinService {
	/**
	 * 查询所有卡BIN配置信息数据
	 * @param manageBin
	 * @return
	 */
	public List<ManageBin> listPageManageBin(ManageBin manageBin);
	
	/**
	 * 添加卡BIN配置信息数据
	 * @param manageBin
	 * @return
	 */
	public boolean insertManageBin(ManageBin manageBin);
	
	/**
	 * 删除卡BIN配置信息数据
	 * @param binProductId
	 */
	public void deleteManageBinById(int binProductId);
	
	/**
	 * 查询卡BIN配置单个值信息数据
	 * @param binProductId
	 * @return
	 */
	public ManageBin getManageBinById(int binProductId);
	
	/**
	 * 修改卡BIN配置信息数据
	 * @param manageBin
	 */
	public void updateManageBinInfo(ManageBin manageBin);
	
}
