package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.StagingPlan;
import com.app.entity.State;
import com.app.service.StagingPlanService;

@Controller
@RequestMapping(value="/NotstagingPlan")
public class NotStagingPlanController {
		@Autowired
		private StagingPlanService stagingPlanService;
		@RequestMapping
		public ModelAndView notStagingPlanList(StagingPlan stagingPlan,HttpServletRequest request, ModelMap modelMap){
			ModelAndView mv=new ModelAndView();
			List<StagingPlan> list =stagingPlanService.notStagingPlanPageList(stagingPlan);
			modelMap.put("list",list);
			request.setAttribute("stagingPlan", stagingPlan);
			mv.setViewName("NotstagingPlan/NotstagingPlan");
			return mv;
		}
	
	    //请求分期计划页面
		@RequestMapping(value="/notStagingPlanAdd")
		public ModelAndView stagingPlanAdd(Model model){
			ModelAndView mv=new ModelAndView();
			//初始化数据
			List<State> stateList=stagingPlanService.stateList();
			mv.addObject("stateList", stateList);
			mv.setViewName("NotstagingPlan/addNotStagingPlan");
			return  mv;
		}
	 
		/*// 请求编辑页面
		@RequestMapping(value = "notStagingPlanEdit")
		public ModelAndView stagingPlanById(@RequestParam int planId) {
			ModelAndView mv = new ModelAndView();
			List<State> stateList = stagingPlanService.stateList();
			StagingPlan stagingPlan = stagingPlanService.stagingPalnById(planId);
			mv.addObject("stagingPlan", stagingPlan);
			mv.addObject("stateList", stateList);
			mv.setViewName("NotstagingPlan/updateNotStagingPlan");
			return mv;
		}
		
		// 删除单条数据
		@RequestMapping(value = "/stagingPlan_del")
		public void delStagingPlan(@RequestParam int planId, PrintWriter out) {
			stagingPlanService.stagingPlanDel(planId);
			out.write("success");
			out.close();
		}
		*/
		
}
