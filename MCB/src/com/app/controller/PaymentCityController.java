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

import com.app.entity.PaymentCity;
import com.app.service.PaymentCityService;

@Controller
@RequestMapping(value="/PaymentCity")
public class PaymentCityController {
	@Autowired
	private PaymentCityService paymentCityService;
	/**
	 * 实现查询分页,分期城市
	 * @param request
	 * @param modelMap
	 * @param paymentCity
	 * @return
	 */
	@RequestMapping
	public String  paymentCityList(PaymentCity paymentCity,HttpServletRequest request,ModelMap modelMap){
		 
		List<PaymentCity> list=paymentCityService.listPagePaymentCity(paymentCity);
		modelMap.put("list", list);
		request.setAttribute("paymentCity", paymentCity);
		return "paymentCity/paymentCity";
	}
	/**
	 * 跳转新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addPaymentCity")
	public ModelAndView addPaymentCity(Model model){
			ModelAndView mv=new ModelAndView();
			mv.setViewName("paymentCity/addPaymentCity");
		return mv;
	}
	
	@RequestMapping(value="/add_edit_PaymentCity",method=RequestMethod.POST)
	public ModelAndView savePaymentCity(PaymentCity paymentCity){
		ModelAndView mv=new ModelAndView();
		if(paymentCity.getCityId()==null || paymentCity.getCityId().equals("")){
			mv.addObject("paymentCity", paymentCity);
			if(paymentCityService.savePaymentCity(paymentCity)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			paymentCityService.updatePaymentCity(paymentCity);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/getPaymentCityById")
	public ModelAndView getPaymentCityById(@RequestParam int cityId){
			ModelAndView mv=new ModelAndView();
			PaymentCity paymentCity=paymentCityService.getPamentCityByid(cityId);
			mv.addObject("paymentCity", paymentCity);
			mv.setViewName("paymentCity/updatePaymentCity");
		return mv;
	}
	
	@RequestMapping(value="/delPaymentCity")
	public void delPaymentCity(@RequestParam int cityId,PrintWriter out){
		paymentCityService.deletePaymentCity(cityId);
		out.write("success");
		out.close();
	}
}
