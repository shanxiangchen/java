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

import com.app.entity.StagingType;
import com.app.entity.State;
import com.app.service.StagingPlanService;
import com.app.service.StateService;

@Controller
@RequestMapping(value="/state")
public class StateController {
	@Autowired
	private StateService stateService;
	@Autowired
	private StagingPlanService stagingPlanService;
	@RequestMapping()
	public ModelAndView stateList(State state,HttpServletRequest request,ModelMap modelMap){
		 
		ModelAndView mv=new ModelAndView();
		List<State> list = stateService.statePageList(state);
		modelMap.put("list", list);
		request.setAttribute("state",state);
		mv.setViewName("state/state");
		return mv;
	}
	//请求状态页面
	@RequestMapping(value="/stateAdd")
	public ModelAndView stateAdd(Model model){
		ModelAndView mv=new ModelAndView();
		//初始化数据
		List<StagingType>listType=stateService.listStagingType();
		mv.addObject("listType", listType);
		mv.setViewName("state/addState");
		return mv;
	}
	@RequestMapping(value="/add_Edit_State",method=RequestMethod.POST)
	public ModelAndView addState(State state){
		ModelAndView mv=new ModelAndView();
		if(state.getStateId()== null || state.getStateId().intValue()==0){
			mv.addObject("state", state);
			if(stateService.saveState(state)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			stateService.updateState(state);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
		/**
		 * 查询状态单条数据
		 * @param stateId
		 * @return
		 */
		@RequestMapping(value="/state_Edit")
		public ModelAndView toEdit(@RequestParam int stateId){
			ModelAndView mv = new ModelAndView();
			State state= stateService.stateById(stateId);
			List<StagingType>listType=stateService.listStagingType();
			mv.setViewName("state/updateState");
			mv.addObject("state", state);
			mv.addObject("listType", listType);
			return mv;
		}
		
		/**
		 * 删除信息的时候判断是否有关联 
		 * @param out
		 * @param stateCode
		 * @param response
		 */
		@RequestMapping(value="/getCountCode")
		public void  getBoundCount(PrintWriter out,int stateCode,HttpServletResponse response){
			 try {
				//调用查询总条数方法
				int count=stagingPlanService.getCountCode(stateCode);
				//将数据响应给ajax plain 纯文本（文本格式）
				response.setContentType("text/plain;charset=utf-8");
				out = response.getWriter();
				out.print(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 删除单条数据
		 * @param stateId
		 * @param out
		 */
		@RequestMapping(value="/state_del")
		public void delState(@RequestParam int stateId,PrintWriter out){
			stateService.deleteState(stateId);
			out.write("success");
			out.close();
		}
}
