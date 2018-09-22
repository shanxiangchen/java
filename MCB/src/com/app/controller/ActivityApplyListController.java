package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ActivityApplyList;
import com.app.listener.ActivitApplyLitstExcelView;
import com.app.service.ActivityApplyListService;

/***
 * 活动报名名单,Controller方法
 * create date 2016/3/10
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
@Controller
@RequestMapping(value="/aalc")
public class ActivityApplyListController {
	@Autowired
	private ActivityApplyListService applyListService;
	@RequestMapping
	public ModelAndView listActicityApplyListController(ModelMap modelMap,ActivityApplyList activityApplyList,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		 
		List<ActivityApplyList> list =applyListService.listActivityApplyList(activityApplyList);
		modelMap.put("list", list);
		request.setAttribute("activityApplyList", activityApplyList);
		mv.setViewName("ActicityApplyList/ActivityApplyList");
		return mv;
	}
	
	@RequestMapping(value = "/excel")
	public ModelAndView export2Excel(ActivityApplyList activityApply,HttpServletRequest request,
			String activityTitle,String activityStartDate,String activityEndDate) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		/*activityApply.setActivityTitle(activityTitle);
		activityApply.setActivityStartDate(activityStartDate);
		activityApply.setActivityEndDate(activityEndDate);*/
		List<String> titles = new ArrayList<String>();
		titles.add("报名编号");
		titles.add("手机号");
		titles.add("活动标题");
		titles.add("开始时间");
		titles.add("结束时间");
		dataMap.put("titles", titles);
		System.out.println(activityApply.getActivityStartDate()+activityApply.getActivityEndDate());
		List<ActivityApplyList> activityApplyList = applyListService.listActivityApplyListExecel(activityApply);
		dataMap.put("activityApplyList", activityApplyList);
		ActivitApplyLitstExcelView erv = new ActivitApplyLitstExcelView();
		ModelAndView mv = new ModelAndView(erv, dataMap);
		return mv;
	}
}
