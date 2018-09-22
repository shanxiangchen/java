package com.app.service;
import java.util.List;

import com.app.entity.StagingType;
import com.app.entity.State;

public interface StateService {
	/**
	 * 分页查询
	 * @param state
	 * @return
	 */
	public List<State> statePageList(State state);
	
	/**
	 * 初始化数据
	 * @return
	 */
	public List<StagingType> listStagingType();
	/***
	 * 添加状态
	 * @param state
	 * @return
	 */
	public boolean saveState(State state);
	/***
	 * 查询状态单条数据
	 *  @param StateId
	 */
	public State stateById(int stateId);
	
	/**
	 * 编辑状态
	 * @param state
	 */
	public void updateState(State state);
	
	/**
	 * 删除状态数据
	 * @param stateId
	 */
	public void deleteState(int stateId);
	
	/***
	 * 删除数据的时候判断此数据是否有关联
	 * @param stateCode
	 * @return
	 */
	public int getCountCode(String stagCode);
}
