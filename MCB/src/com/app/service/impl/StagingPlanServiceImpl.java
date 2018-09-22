package com.app.service.impl;

import java.util.List;

import com.app.entity.StagingPlan;
import com.app.entity.State;
import com.app.mapper.StagingPlanMapper;
import com.app.service.StagingPlanService;

public class StagingPlanServiceImpl implements StagingPlanService {
	private StagingPlanMapper stagingPlanMapper;

	public StagingPlanMapper getStagingPlanMapper() {
		return stagingPlanMapper;
	}

	public void setStagingPlanMapper(StagingPlanMapper stagingPlanMapper) {
		this.stagingPlanMapper = stagingPlanMapper;
	}
	//分页查询
	@Override
	public List<StagingPlan> stagingPlanPageList(StagingPlan stagingPlan) {
		 
		List<StagingPlan> list=stagingPlanMapper.stagingPlanPageList(stagingPlan);
		return list;
	}
	//初始化状态数据
	@Override
	public List<State> stateList() {
		return stagingPlanMapper.stateList();
	}
	//添加分期计划
	@Override
	public boolean saveStagingPlan(StagingPlan stagingPlan) {

		stagingPlanMapper.insertstagingPlanSave(stagingPlan);
		return true;
	}
	//查询单条数据
	@Override
	public StagingPlan stagingPalnById(int planId) {
		return stagingPlanMapper.stagingPalnById(planId);
	}
	//编辑分期计划
	@Override
	public void updateStagingPlan(StagingPlan stagingPlan) {
		stagingPlanMapper.stagingPlanEdit(stagingPlan);
	}
	//删除单条数据
	@Override
	public void deleteStagingPlan(int planId) {
		stagingPlanMapper.stagingPlanDel(planId);
	}
	//删除的时候判断是此数据是否有关联
	@Override
	public int getCountCode(int stateCode) {
		int count =stagingPlanMapper.getCountCode(stateCode);
		return count;
	}

	@Override
	public List<StagingPlan> notStagingPlanPageList(StagingPlan stagingPlan) {
		List<StagingPlan> list=stagingPlanMapper.notStagingPlanPageList(stagingPlan);
		return list;
	}

	@Override
	public int selectStagingPlanByCode(StagingPlan stagingPlan) {
		return stagingPlanMapper.selectStagingPlanByCode(stagingPlan);
	}
}
