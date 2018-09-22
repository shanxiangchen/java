package com.app.service;

import java.util.List;

import com.app.entity.ActivityApplyList;

/**
 * 活动报名名单服务接口
 * create date 2016/3/10
 * <ba/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface ActivityApplyListService {
 public List<ActivityApplyList> listActivityApplyList(ActivityApplyList activityApplyList);
 public List<ActivityApplyList> listActivityApplyListExecel(ActivityApplyList activityApplyList);
}
