package com.app.mapper;

import java.util.List;

import com.app.entity.StagingPlan;
import com.app.entity.State;

public interface StagingPlanMapper {
	/**
	 * 分页查询
	 * @param stagingPlan
	 * @return
	 */
	public List<StagingPlan> stagingPlanPageList(StagingPlan stagingPlan);
	public int getCount(StagingPlan stagingPlan);
	
	
	
	public List<StagingPlan> notStagingPlanPageList(StagingPlan stagingPlan);
	public int getCountNot(StagingPlan stagingPlan);
	/***
	 * 初始化状态数据
	 * @return
	 */
	public List<State> stateList();
	/***
	 * 判断是否唯一
	 * @param stagingPlan
	 * @return
	 */
	public int getCountNewNo(StagingPlan stagingPlan);
	/***
	 * 添加分期计划
	 * @param stagingPlan
	 */
	public void insertstagingPlanSave(StagingPlan stagingPlan);
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
	public void stagingPlanEdit(StagingPlan stagingPlan);
	
	/***
	 * 删除单条数据
	 * @param planId
	 */
	public void stagingPlanDel(int planId);
	
	/***
	 * 删除的时候判断是此数据是否有关联
	 * @param stateCode
	 * @return
	 */
	public int getCountCode(int stateCode);
	/**
	 * 判断是否有编码重复
	 * @param stagingPlan
	 * @return
	 */
	public int selectStagingPlanByCode(StagingPlan stagingPlan);
}
