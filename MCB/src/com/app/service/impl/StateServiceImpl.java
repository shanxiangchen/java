package com.app.service.impl;

import java.util.List;

import com.app.entity.StagingType;
import com.app.entity.State;
import com.app.mapper.StateMapper;
import com.app.service.StateService;

public class StateServiceImpl implements StateService{
	private StateMapper stateMapper;

	public StateMapper getStateMapper() {
		return stateMapper;
	}

	public void setStateMapper(StateMapper stateMapper) {
		this.stateMapper = stateMapper;
	}
	//分页查询
	@Override
	public List<State> statePageList(State state) {
		 
		List<State> list=stateMapper.statePageList(state);
		 
		return list;
	}
	//初始化数据
	@Override
	public List<StagingType> listStagingType() {
		return stateMapper.listStagingType();
	}
	//添加状态
	@Override
	public boolean saveState(State state) {
		int countNewNo=stateMapper.getCountNewNo(state);
		if(countNewNo>0){
			return false;
		}
		stateMapper.stateSave(state);
		return true;
	}
	//查询状态单条数据
	@Override
	public State stateById(int stateId) {
		return stateMapper.stateById(stateId);
	}
	//编辑状态
	@Override
	public void updateState(State state) {
		stateMapper.stateEdit(state);
	}
	//删除状态数据
	@Override
	public void deleteState(int stateId) {
		stateMapper.stateDel(stateId);
	}
	//删除数据的时候判断此数据是否有关联
	@Override
	public int getCountCode(String stagCode) {
		int count=stateMapper.getCountCode(stagCode);
		return count;
	}
}
