package com.app.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.Paytype;
import com.app.entity.Rate;
import com.app.entity.StagingPlan;
import com.app.entity.StagingType;
import com.app.entity.State;
import com.app.service.RateService;
import com.app.service.StagingTypeService;

@Controller
@RequestMapping(value="/rate")
public class RateController {
	private HttpServletResponse response;
	private HttpServletRequest request;
	@Autowired
	private RateService rateService;
	@Autowired
	private StagingTypeService stagingTypeService;
	
	//获取response和request对象
		@ModelAttribute
		public void setReqAndResp(HttpServletResponse response,HttpServletRequest request)
		{
			this.response = response;
			this.request = request;
		}
	/**
	 *显示费率列表
	 * @return
	 */
	@RequestMapping
	public String listRate(Rate rate,HttpServletRequest request, ModelMap modelMap){
		
		List<StagingType> StagingTypelist=rateService.listStagingType();//分期类型初始化数据
		List<StagingPlan> StagingPlanlist=rateService.listStagingPlan();//分期计划初始化数据
		List<State> Statelist=rateService.listState();//状态初始化数据
		List<Rate> list = rateService.listPageRates(rate);
		modelMap.put("list", list);
		request.setAttribute("StagingTypelist", StagingTypelist);
		request.setAttribute("StagingPlanlist", StagingPlanlist);
		request.setAttribute("Statelist", Statelist);
		request.setAttribute("rate", rate);
		return "rate/rate";
	}
	/**
	 * 请求费率页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView addRate(Model model){
		ModelAndView mv=new ModelAndView();
		List<Paytype>listPaytype=rateService.listPaytype();
		model.addAttribute("listPaytype", listPaytype);
		mv.setViewName("rate/addRate");
		return mv;
	}
	
	@RequestMapping(value="/ajax_StagingTpye")
	public void searchStagingTpye(){
		try {
			//调用查询分期类型的方法
			List<StagingType> list=rateService.getStagingType();
			//将list集合转换成json格式
			JSONArray array=JSONArray.fromObject(list);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 查询状态单条数据
	 * @param stateId
	 * @return
	 */
	@RequestMapping(value="/rate_Edit")
	public ModelAndView toEdit(@RequestParam int rateId){
		ModelAndView mv = new ModelAndView();
		Rate rate= rateService.rateById(rateId);
		List<StagingType> list=rateService.getStagingType();
		List<State> Statelist=rateService.listState();//状态初始化数据
		List<Paytype>listPaytype=rateService.listPaytype();
		mv.setViewName("rate/updateRate");
		mv.addObject("rate", rate);
		mv.addObject("listtype", list);
		mv.addObject("statelist", Statelist);
		mv.addObject("listPaytype", listPaytype);
		return mv;
	}
	
	
	@RequestMapping(value="/update_Edit_Rate",method=RequestMethod.POST)
	public String toRate(Rate rate){
		  rateService.updateRate(rate);
		return "save_result";
	}
	
	@RequestMapping(value="/searchState")
	public void  searchState(){
		
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			
			//调用查询状态方法
			List<State> list=rateService.getState(id);
			
			JSONArray array=JSONArray.fromObject(list);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.print(array.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/StagingPlan")
	public void searchStagingPlan(){
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			
			//调用查询分期计划方法
			List<StagingPlan> list=rateService.getStagingPlan(id);
			
			JSONArray array=JSONArray.fromObject(list);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.print(array.toString());
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/rate_save",method=RequestMethod.POST)
	public ModelAndView saveRate(Rate rate){
		ModelAndView mv=new ModelAndView();
		if(rate.getRateId()== null || rate.getRateId().intValue()==0){
			mv.addObject("rate", rate);
			if(rateService.insertRates(rate)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			//versionsService.updatversionsInfo(versions);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/***
	 *  删除信息的时候判断是否有关联 
	 * @param out
	 */
	@RequestMapping(value="/getCountCode")
	public void  getBoundCount(PrintWriter out,int stagCode,HttpServletResponse response){
		 try {
			//调用查询总条数方法
			int count=stagingTypeService.getCountCode(stagCode);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			out = response.getWriter();
			out.print(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/deleteById")
	public void deleteRate(@RequestParam int rateId,PrintWriter out){
		rateService.deleteByid(rateId);
		out.write("success");
		out.close();
	}
}
