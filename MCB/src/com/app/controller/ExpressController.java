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

import com.app.entity.Express;
import com.app.service.ExpressService;
/**
 * 快递Controllerl类
 * create date 2015/8/24
 * <br/>
 * @author shigaungting@tansun.com.cn
 *
 */
@Controller
@RequestMapping(value="/express")
public class ExpressController {
	@Autowired
	private  ExpressService expressService;
	/**
	 * 显示版本列表
	 * @return
	 */
	@RequestMapping
	public String listBounds(Express express,HttpServletRequest request, ModelMap modelMap){
		 
		List<Express> list = expressService.listPageExpress(express);
		modelMap.put("list", list);
		request.setAttribute("express", express);
		return "express/express";
	}	
	/**
	 * 请求新增快递页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/express_insert")
	public ModelAndView toAdd(Model model){
		ModelAndView mv=new ModelAndView();;
		mv.setViewName("express/addExpress");
		return mv;
	}
	
	@RequestMapping(value="/express_save_edit",method=RequestMethod.POST)
	public ModelAndView saveVersions(Express express){
		ModelAndView mv=new ModelAndView();
		if(express.getExpressServiceNameId()== null || express.getExpressServiceNameId().intValue()==0){
			mv.addObject("express", express);
			if(expressService.insertExpress(express)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			expressService.updatexpressInfo(express);
		}
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 删除某个快递信息
	 * @param expressServiceNameId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteExpress(@RequestParam int expressServiceNameId,PrintWriter out){
		expressService.deleteExpress(expressServiceNameId);
		out.write("success");
		out.close();
	}
	
	/**
	 * 请求编辑版本页面
	 * @param expressServiceNameId
	 * @return
	 */
	@RequestMapping(value="/express_update")
	public ModelAndView toEdit(@RequestParam int expressServiceNameId){
		ModelAndView mv = new ModelAndView();
		Express express=expressService.getExpressById(expressServiceNameId);
		mv.setViewName("express/updateExpress");
		mv.addObject("express", express);
		return mv;
	}
}
