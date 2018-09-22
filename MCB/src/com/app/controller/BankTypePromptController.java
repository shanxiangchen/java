package com.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankTypePrompt;
import com.app.service.BankTypePromptService;

@Controller
@RequestMapping(value="/cardPrompt")
public class BankTypePromptController {

	@Autowired
	private BankTypePromptService promptService;
	
	/**
	 * 分页
	 * @param map
	 * @param bankTypePrompt
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping
	public ModelAndView listBankTypePrompt(Map<String, Object> map, BankTypePrompt bankTypePrompt, HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<BankTypePrompt> list = promptService.listPageBankTypePrompt(bankTypePrompt);
		modelMap.put("list", list);
		request.setAttribute("bankTypePrompt", bankTypePrompt);
		mv.setViewName("bankCardType/bankTypePrompt");
		return mv;
	}
	
	/**
	 * 编辑页面并查询数据
	 * @param typeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/BankTypePromptupdate")
	public ModelAndView updateBankTypePrompt(String typeId,Model model){
		ModelAndView mv =new ModelAndView();
		BankTypePrompt bankTypePrompt=promptService.getBankTypePromptByid(typeId);
		// 查询过后要返回对象
		model.addAttribute("bankTypePrompt", bankTypePrompt);
		mv.setViewName("bankCardType/updateBankTypePrompt");
		return mv;
	}
	
	/**
	 * 修改保存
	 * @param bankTypePrompt
	 * @return
	 */
	@RequestMapping(value = "/BankTypePromptsave",method=RequestMethod.POST)
	public ModelAndView saveBankTypePrompt(BankTypePrompt bankTypePrompt){
		ModelAndView mv = new ModelAndView();
		promptService.update(bankTypePrompt);
		promptService.listPageBankTypePrompt(bankTypePrompt);
		mv.setViewName("save_result");
		return mv;
	}

}
