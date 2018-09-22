package com.app.service.impl;

import java.util.List;

import com.app.entity.HappyPooll;
import com.app.mapper.HappyPoollMapper;
import com.app.service.HappyPoollService;

public class HappyPoollServiceImpl implements HappyPoollService {

	private HappyPoollMapper happyPoollMapper;

	public HappyPoollMapper getHappyPoollMapper() {
		return happyPoollMapper;
	}

	public void setHappyPoollMapper(HappyPoollMapper happyPoollMapper) {
		this.happyPoollMapper = happyPoollMapper;
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<HappyPooll> listPageHappyPooll(HappyPooll happyPooll) {
		 
		List<HappyPooll> list = happyPoollMapper.listPageHappyPooll(happyPooll);
		return list;
	}

  
	@Override
	public void delete(String happyPoollId) {
		happyPoollMapper.delete(happyPoollId);

	}
 
	@Override
	public boolean update(HappyPooll happyPooll) {
		int getCount=happyPoollMapper.getHappyPoolllorder(happyPooll);
		if(getCount>0){
			return false;
		}
		happyPoollMapper.update(happyPooll);
		return true;
		
	}

	/**
	 * 按ID查询
	 */
	@Override
	public HappyPooll getHappyPoollByid(String happyPoollId) {
		return happyPoollMapper.getHappyPoollByid(happyPoollId);
	}

	/**
	 * 唯一标识
	 */
	@Override
	public boolean saveHappyPoolllorder(HappyPooll happyPooll) {
		int getCount=happyPoollMapper.getHappyPoolllorder(happyPooll);
		if(getCount>0){
			return false;
		}
		happyPoollMapper.savehp(happyPooll);
		return true;
	}

}
