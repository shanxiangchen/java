package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.Ratess;
import com.app.service.RatesServer;

@Controller
@RequestMapping(value="/ratess")
public class RatesController {
	@Autowired
	private RatesServer ratesServer;
	
	@RequestMapping
	public String listRates(Ratess rates,HttpServletRequest request, ModelMap modelMap){
		 List<Ratess>  list=ratesServer.listbankrates(rates);
		request.setAttribute("list", list);
		return "ratess/ratess";
	}
	
	
	@RequestMapping(value="/ratess_Edit")
	public ModelAndView toEdit(@RequestParam int id){
		ModelAndView mv = new ModelAndView();
		Ratess lsi= ratesServer.getStatess(id);
		mv.setViewName("ratess/updateRatess");
		mv.addObject("rates", lsi);
		return mv;
	}
	
	
	
	@RequestMapping(value="/update_Edit_Ratess",method=RequestMethod.POST)
	public String toRate(Ratess ratess){
		ratesServer.updateStatess(ratess);
		return "save_result";
	}
}
