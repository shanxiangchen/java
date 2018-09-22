package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankUser;
import com.app.service.BankUserService;

@Controller
@RequestMapping(value="buc")
public class BankUserController{
	@Autowired
	private BankUserService bankUserService;
	@RequestMapping()
	public ModelAndView selBankUer(Model model,BankUser bankUser,HttpServletRequest request,ModelMap modelMap){
		ModelAndView mv=new ModelAndView();
		 
		List<BankUser> list = bankUserService.selBankUserList(bankUser);
		modelMap.put("list",list);
		model.addAttribute("bankUser",bankUser);
		mv.setViewName("bankUser/bankUser");
		return mv;
	}
 
	
	@RequestMapping(value="/bankUser_Edit")
	public void editBankUser(@RequestParam int userId,PrintWriter out){
		bankUserService.updateBankUser(userId);
		out.write("success");
		out.close();
	}
}
