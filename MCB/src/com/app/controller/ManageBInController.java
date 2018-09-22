package com.app.controller;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ManageBin;
import com.app.service.ManageBinService;
@Controller
@RequestMapping(value="/managebin")
public class ManageBInController {
	
	@Autowired
	private ManageBinService manageBinService;
	/**
	 * 显示版本列表
	 * @return
	 */
	@RequestMapping
	public String listManageBin(ManageBin manageBin,HttpServletRequest request, ModelMap modelMap){
		 
		List<ManageBin> list = manageBinService.listPageManageBin(manageBin);;
		modelMap.put("list", list);
		request.setAttribute("manageBin", manageBin);
		return "managebin/managebin";
	}
	
	/**
	 * 请求新卡BIN配置页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ManageBin_insert")
	public ModelAndView toAdd(Model model){
		ModelAndView mv=new ModelAndView();;
		mv.setViewName("managebin/addManagebin");
		return mv;
	}
	
	@RequestMapping(value="/managebin_save_edit",method=RequestMethod.POST)
	public ModelAndView saveManageBin(ManageBin manageBin){
		String cardOfType=manageBin.getCardOfType();//1.兴业通 2.立享卡 3.标准公务卡 4.其他
		if("1".equals(cardOfType)||"2".equals(cardOfType)){
			manageBin.setAutoStagFlag("0");
		}else{
			manageBin.setAutoStagFlag("1");
		}
		ModelAndView mv=new ModelAndView();
		if(manageBin.getBinProductId()== null || manageBin.getBinProductId().intValue()==0){
			mv.addObject("manageBin", manageBin);
			if(manageBinService.insertManageBin(manageBin)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			manageBinService.updateManageBinInfo(manageBin);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除某个卡BIN配置信息
	 * @param errorCodeId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteErrorCode(@RequestParam int binProductId,PrintWriter out){
		manageBinService.deleteManageBinById(binProductId);
		out.write("success");
		out.close();
	}
	
	/**
	 * 请求编辑卡BIN配置页面
	 * @param binProductId
	 * @return
	 */
	@RequestMapping(value="/managebin_update")
	public ModelAndView toEdit(@RequestParam int binProductId){
		ModelAndView mv = new ModelAndView();
	    ManageBin manageBin=manageBinService.getManageBinById(binProductId);
		mv.setViewName("managebin/updateManagebin");
		mv.addObject("manageBin", manageBin);
		return mv;
	}
}
