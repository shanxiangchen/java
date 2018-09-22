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

import com.app.entity.ErrorCode;
import com.app.service.ErrorCodeService;



@Controller
@RequestMapping(value="/errorCode")
public class ErrorCodeController {

	@Autowired
	private  ErrorCodeService errorCodeService;
	/**
	 * 显示版本列表
	 * @return
	 */
	@RequestMapping
	public String listErrorCode(ErrorCode errorCode,HttpServletRequest request, ModelMap modelMap){
		 
		List<ErrorCode> list = errorCodeService.listPageErrorCode(errorCode);
		modelMap.put("list", list);
		request.setAttribute("errorCode", errorCode);
		return "errorCode/errorCode";
	}
	/**
	 * 请求新增错误码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/errorCode_insert")
	public ModelAndView toAdd(Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("errorCode/addErrorCode");
		return mv;
	}
	
	@RequestMapping(value="/errorCode_save_edit",method=RequestMethod.POST)
	public ModelAndView saveErrorCode(ErrorCode errorCode){
		ModelAndView mv=new ModelAndView();
		if(errorCode.getErrorCodeId()== null || errorCode.getErrorCodeId().intValue()==0){
			mv.addObject("errorCode", errorCode);
			if(errorCodeService.insertErrorCode(errorCode)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
		errorCodeService.updaterrorCodeInfo(errorCode);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除某个错误码信息
	 * @param errorCodeId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteErrorCode(@RequestParam int errorCodeId,PrintWriter out){
		errorCodeService.deleteErrorCode(errorCodeId);
		out.write("success");
		out.close();
	}
	
	/**
	 * 请求编辑版本页面
	 * @param expressServiceNameId
	 * @return
	 */
	@RequestMapping(value="/errorCode_update")
	public ModelAndView toEdit(@RequestParam int errorCodeId){
		ModelAndView mv = new ModelAndView();
		ErrorCode errorCode=errorCodeService.getErrorCodeById(errorCodeId);
		mv.setViewName("errorCode/updateErrorCode");
		mv.addObject("errorCode", errorCode);
		return mv;
	}
}
