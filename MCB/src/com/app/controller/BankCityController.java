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

import com.app.entity.BankCity;
import com.app.service.BankCityService;


@Controller
@RequestMapping(value="/bankCity")
public class BankCityController {
	
	@Autowired
	private BankCityService bankCityService;
	/**
	 * 显示城市列表
	 * @return
	 */
	@RequestMapping
	public String listBankCity(BankCity bankCity,HttpServletRequest request, ModelMap modelMap){
		 
		 
		List<BankCity> list = bankCityService.listPageBankCty(bankCity);
		modelMap.put("list", list);
		request.setAttribute("bankCity", bankCity);
		return "city/city";
	}
	
	/**
	 * 删除某个地区
	 * @param userId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteUser(@RequestParam int cityId,PrintWriter out){
		bankCityService.deleteBankCityInfo(cityId); 
					
		out.write("success");
		out.close();
	}
	/**
	 * 请求新增地区页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bankCity_insert")
	public ModelAndView bankCityAdd(Model model){
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("city/addCity");
		return mv;
	}
	

	@RequestMapping(value="/bankCity_save_edit",method=RequestMethod.POST)
	public ModelAndView saveErrorCode(BankCity bankCity){
		ModelAndView mv=new ModelAndView();
		if(bankCity.getCityId()== null || bankCity.getCityId().intValue()==0){
			mv.addObject("bankCity", bankCity);
			if(bankCityService.insertBankCity(bankCity)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			bankCityService.updateBankCity(bankCity);
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 请求编辑页面
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="/bankCity_update")
	public ModelAndView toEdit(@RequestParam int cityId){
		ModelAndView mv = new ModelAndView();
		BankCity bankCity=bankCityService.getBankCityById(cityId);
		mv.setViewName("city/updateCity");
		mv.addObject("bankCity", bankCity);
		return mv;
	}
	
}
