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

import com.app.entity.BankLinesType;
import com.app.service.BankLinesTypeService;



@Controller
@RequestMapping("/LinesType")
public class BankLinesTypeController {

	@Autowired
	private BankLinesTypeService linesTypeService;

	@RequestMapping
	public ModelAndView listLinesType( BankLinesType type, HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<BankLinesType> linesTypes = linesTypeService.bankLinesTypePageList(type);
		modelMap.put("linesTypes",linesTypes);
		request.setAttribute("type", type);
		mv.setViewName("limit/LinesType");

		return mv;
	}
	
	@RequestMapping("/delete")
	public void deletelimit(@RequestParam String linesTypeId, PrintWriter out){
		linesTypeService.deleteLinesType(linesTypeId);
		out.write("success");
		out.close();
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateview(String linesTypeId , Model model){
		ModelAndView mv=new ModelAndView();
		BankLinesType bankLinesType= linesTypeService.getLinesTypebyid(linesTypeId);
		model.addAttribute("bankLinesType",bankLinesType);
		mv.setViewName("limit/updateType");
		return mv;
	}
	
	@RequestMapping("/updatesave")
	public ModelAndView saveView(BankLinesType linesType){
		ModelAndView mv = new ModelAndView();
		linesTypeService.updateLinesValue(linesType);
		/*linesTypeService.updateLinesMaxValue(linesType);//修改额度权限
		linesTypeService.updateLinesTypes(linesType);*/
		mv.setViewName("save_result");
		return mv;
	}
	
}
