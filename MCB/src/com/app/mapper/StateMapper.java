package com.app.mapper;

import java.util.List;

import com.app.entity.StagingType;
import com.app.entity.State;

public interface StateMapper {
	/**
	 * 分页查询
	 * @param state
	 * @return
	 */
	public List<State> statePageList(State state);
	public int getCount(State state);
	/**
	 * 初始化数据
	 * @return
	 */
	public List<StagingType> listStagingType();
	/***
	 * 判断是否唯一
	 * @param state
	 * @return
	 */
	public int getCountNewNo(State state);
	/***
	 * 添加状态
	 * @param state
	 */
	public void stateSave(State state);
	
	/***
	 * 查询状态单条说一句
	 *  @param StateId
	 */
	public State stateById(int stateId);
	
	/**
	 * 编辑状态
	 * @param state
	 */
	public void stateEdit(State state);
	
	/**
	 * 删除状态数据
	 * @param stateId
	 */
	public void stateDel(int stateId);
	
	/***
	 * 删除数据的时候判断此数据是否有关联
	 * @param stateCode
	 * @return
	 */
	public int getCountCode(String stagCode);
}
