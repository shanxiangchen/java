package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.StagingPlan;
import com.app.entity.State;
import com.app.service.RateService;
import com.app.service.StagingPlanService;

@Controller
@RequestMapping(value = "/stagingPlan")
public class StagingPlanController {
	@Autowired
	private StagingPlanService stagingPlanService;
	@Autowired
	private RateService rateService;

	@RequestMapping
	public ModelAndView stagingPlanList(StagingPlan stagingPlan,
			HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<StagingPlan> list = stagingPlanService.stagingPlanPageList(stagingPlan);
		modelMap.put("list", list);
		request.setAttribute("stagingPlan", stagingPlan);
		mv.setViewName("stagingPlan/stagingPlan");
		return mv;
	}

	// 请求分期计划页面
	@RequestMapping(value = "/stagingPlanAdd")
	public ModelAndView stagingPlanAdd(Model model) {
		ModelAndView mv = new ModelAndView();
		// 初始化数据
		List<State> stateList = stagingPlanService.stateList();
		mv.addObject("stateList", stateList);
		mv.setViewName("stagingPlan/addStagingPlan");
		return mv;
	}

	@RequestMapping(value = "save_edit_stagingPlan", method = RequestMethod.POST)
	public ModelAndView stagongPlanSave(StagingPlan stagingPlan) {
		ModelAndView mv = new ModelAndView();
		if (stagingPlan.getPlanId() == null || stagingPlan.getPlanId().intValue() == 0) {
			mv.addObject("stagingPlan", stagingPlan);
			stagingPlanService.saveStagingPlan(stagingPlan);
			mv.addObject("msg", "success");
		} else {
			stagingPlanService.updateStagingPlan(stagingPlan);
		}
		mv.setViewName("save_result");
		return mv;
	}

	// 请求编辑页面
	@RequestMapping(value = "stagingPlanEdit")
	public ModelAndView stagingPlanById(@RequestParam int planId) {
		ModelAndView mv = new ModelAndView();
		List<State> stateList = stagingPlanService.stateList();
		StagingPlan stagingPlan = stagingPlanService.stagingPalnById(planId);
		mv.addObject("stagingPlan", stagingPlan);
		mv.addObject("stateList", stateList);
		mv.setViewName("stagingPlan/updateStagingPlan");
		return mv;
	}

	// 删除信息的时候判断是否有关联
	@RequestMapping(value = "/getCountCode")
	public void getBoundCount(PrintWriter out, int stagingPlanCode,
			HttpServletResponse response) {
		try {
			// 调用查询总条数方法
			int count = rateService.getCountCode(stagingPlanCode);
			// 将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			out = response.getWriter();
			out.print(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除单条数据
	@RequestMapping(value = "/stagingPlan_del")
	public void delStagingPlan(@RequestParam int planId, PrintWriter out) {
		stagingPlanService.deleteStagingPlan(planId);
		out.write("success");
		out.close();
	}
}
