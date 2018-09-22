package com.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.InforMation;
import com.app.service.InforMationService;
import com.app.util.PageBean;

@Controller
@RequestMapping("/inforMationList")
public class InforMationListController {

	@Autowired
	private InforMationService inforMationService;
	
@RequestMapping
	public ModelAndView listinforMation(Map<String, Object> map, InforMation inforMation, HttpServletRequest request, ModelMap modelMap){
	ModelAndView mv = new ModelAndView();
	String custName=request.getParameter("custName");
	if(custName!=null){
		inforMation.setCustName(custName);
	}
	int pageNo = 1;
	int pageSize = 10;
	if (request.getParameter("pageNo") != null) {
		pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	if (request.getParameter("pageSize") != null) {
		pageSize = Integer.parseInt(request.getParameter("pageSize"));
	}
	if (inforMation.getPageNo() == 0) {
		inforMation.setPageNo(pageNo);
	}
	if (inforMation.getPageSize() == 0) {
		inforMation.setPageSize(pageSize);
	}

	PageBean<InforMation> pageBean = inforMationService.listPageinformation(inforMation);
	if (pageNo > pageBean.getEndPage()) {
		inforMation.setPageNo(pageBean.getEndPage());
		pageBean = inforMationService.listPageinformation(inforMation);
	}
	int line[] = { 10, 20, 30, 40, 50, 100 };
	modelMap.put("pageBean", pageBean);
	request.setAttribute("line", line);
	request.setAttribute("custName", custName);
	mv.setViewName("inforMation/inforMationList");
	return mv;	
}
	
	/**
	* 详情页面
 	* @param model
 	* @return
 	*/
	@RequestMapping(value = "/details")
	public ModelAndView infordetails(Model model,String custId) {
	ModelAndView mv = new ModelAndView();
	InforMation mation=inforMationService.getInforMationByid(custId);
	model.addAttribute("mation",mation);
	mv.setViewName("inforMation/infordetails");
	return mv;
}
}
