package com.app.service.impl;

import java.util.List;

import com.app.entity.Paytype;
import com.app.entity.Rate;
import com.app.entity.StagingPlan;
import com.app.entity.StagingType;
import com.app.entity.State;
import com.app.mapper.RateMapper;
import com.app.service.RateService;

/**
 * 费率服务接口实现类
 * create date 2015/11/13
 * <br/>
 * @author shiguangting@tansun.com.cn
 */
public class RateServiceImpl implements RateService{
	private RateMapper rateMapper;

	public RateMapper getRateMapper() {
		return rateMapper;
	}

	public void setRateMapper(RateMapper rateMapper) {
		this.rateMapper = rateMapper;
	}
	//初始化分期类型信息
	@Override
	public List<StagingType> listStagingType() {
		 
		List<StagingType> list=rateMapper.listStagingType();
		return list;
	}
	
	//初始化分期计划信息
	@Override
	public List<StagingPlan> listStagingPlan() {
		 
		List<StagingPlan> list=rateMapper.listStagingPlan();
		return list;
	}
	//初始化状态信息数据
	@Override
	public List<State> listState() {
		 
		List<State> list=rateMapper.listState();
		return list;
	}
	
	//查询费率所有信息数据
	@Override
	public List<Rate> listPageRates(Rate rate) {
		 
		List<Rate> list=rateMapper.listPageRates(rate);
		return list;
	}
	
	//查询分期类型
	@Override
	public List<StagingType> getStagingType() {
		 
		return rateMapper.getStagingType();
	}

	//查询状态
	@Override
	public List<State> getState(int stagCode) {
		 
		return rateMapper.getState(stagCode);
	}
	//查询分期计划
	@Override
	public List<StagingPlan> getStagingPlan(int stateCode) {
		 
		return rateMapper.getStagingPlan(stateCode);
	}
	//查询支付方式信息
	@Override
	public List<Paytype> listPaytype() {  
		return rateMapper.listPaytype();
	}
	//添加费率信息
	@Override
	public boolean insertRates(Rate rate) {
		 
		rateMapper.insertRates(rate);
		return true;
	}
	//按Id删除费率信息
	@Override
	public void deleteByid(int rateId) {
		 
		rateMapper.deleteByid(rateId);
	}
	//删除的时候判断是此数据是否有关联
	@Override
	public int getCountCode(int stagingPlanCode) {
	 
		int count=rateMapper.getCountCode(stagingPlanCode);
		return count;
	}

	@Override
	public Rate rateById(int rateId) {
		return rateMapper.rateById(rateId);
	}

	@Override
	public void updateRate(Rate rate) {
		 rateMapper.update(rate);
	}
	
	
	
}
