package com.app.service.impl;

import java.util.List;

import com.app.entity.ActivityApplyList;
import com.app.mapper.ActivityApplyListMapper;
import com.app.service.ActivityApplyListService;

/**
 * 活动报名名单服务接口实现类
 * create date 2016/3/10
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */

public class ActivityApplyListServiceImpl implements ActivityApplyListService {
	private ActivityApplyListMapper activityApplyListMapper;

	public ActivityApplyListMapper getActivityApplyListMapper() {
		return activityApplyListMapper;
	}

	public void setActivityApplyListMapper(
			ActivityApplyListMapper activityApplyListMapper) {
		this.activityApplyListMapper = activityApplyListMapper;
	}

	@Override
	public List<ActivityApplyList> listActivityApplyList(
			ActivityApplyList activityApplyList) {
		 
		List<ActivityApplyList> list=activityApplyListMapper.listActivityApplyPageList(activityApplyList);
		return list;
	}

	@Override
	public List<ActivityApplyList> listActivityApplyListExecel(ActivityApplyList activityApplyList) {
		 
		return activityApplyListMapper.listActivityApplyListExecel(activityApplyList);
	}
	
	
}
