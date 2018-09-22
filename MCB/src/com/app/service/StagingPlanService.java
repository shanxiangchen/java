package com.app.service;

import java.util.List;

import com.app.entity.StagingPlan;
import com.app.entity.State;

public interface StagingPlanService {
	/**
	 * 分页查询
	 * @return
	 */
	public List<StagingPlan> stagingPlanPageList(StagingPlan stagingPlan);
	
	public List<StagingPlan> notStagingPlanPageList(StagingPlan stagingPlan);
	
	/***
	 * 初始化状态数据
	 * @return
	 */
	public List<State> stateList();
	/**
	 * 添加分期计划
	 * @param stagingPlan
	 * @return
	 */
	public boolean saveStagingPlan(StagingPlan stagingPlan);
	
	/**
	 * 查询单条数据
	 * @param planId
	 * @return
	 */
	public StagingPlan stagingPalnById(int planId);
	
	/***
	 * 编辑分期计划
	 * @param stagingPlan
	 */
	public void updateStagingPlan(StagingPlan stagingPlan);
	
	/***
	 * 删除单条数据
	 * @param planId
	 */
	public void deleteStagingPlan(int planId);
	
	/***
	 * 删除的时候判断是此数据是否有关联
	 * @param stateCode
	 * @return
	 */
	public int getCountCode(int stateCode);
	/**
	 * 查询是否有编码重复
	 * @param stagingPlan
	 * @return
	 */
	public int selectStagingPlanByCode(StagingPlan stagingPlan);
}
