package com.app.mapper;

import java.util.List;

import com.app.entity.ActivityApplyList;

/**
 * 活动报名名单xml配置接口
 * create dtae 2016/3/10
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface ActivityApplyListMapper {
	public List<ActivityApplyList> listActivityApplyPageList(ActivityApplyList activityApplyList);
	public List<ActivityApplyList> listActivityApplyListExecel(ActivityApplyList activityApplyList);
}
