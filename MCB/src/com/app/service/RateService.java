package com.app.service;
/**
 * 费率服务接口
 * create date 2015/11/13
 * <br/>
 * @author shiguangting@tansun.com.cn
 */
import java.util.List;

import com.app.entity.Paytype;
import com.app.entity.Rate;
import com.app.entity.StagingPlan;
import com.app.entity.StagingType;
import com.app.entity.State;
public interface RateService {
	/**
	 * 初始化分期类型信息
	 * @return
	 */
	public List<StagingType> listStagingType();
	
	/**
	 * 初始化分期计划信息
	 */
	public List<StagingPlan> listStagingPlan();
	
	/***
	 *初始化状态数据信息 
	 */
	public List<State> listState();
	
	public Rate rateById(int rateId);
	/**
	 * 查询费率所有信息
	 * @param rate
	 * @return
	 */
	
	public void updateRate(Rate rate);
	
	public List<Rate> listPageRates(Rate rate);
	
	/**
	 * 查询分期类型
	 * @return
	 */
	public List<StagingType> getStagingType();
	
	/***
	 * 查询状态
	 * @param stagCode
	 * @return
	 */
	public List<State> getState(int stagCode);
	
	/**
	 * 查询分期计划
	 * @param stateCode
	 * @return
	 */
	public List<StagingPlan> getStagingPlan(int stateCode);
	
	/**
	 * 查询支付方式信息
	 * @return
	 */
	public List<Paytype> listPaytype();
	
	/***
	 * 添加费率信息
	 * @param rate
	 */
	public boolean insertRates(Rate rate);
	
	/**
	 * 按Id删除费率信息
	 * @param rateId
	 */
	public void deleteByid(int rateId);
	
	/***
	 * 删除的时候判断是此数据是否有关联
	 * @param stagingPlanCode
	 * @return
	 */
	public int getCountCode(int stagingPlanCode);
}
