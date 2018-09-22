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

import com.app.entity.Template;
import com.app.service.TemplateService;
 

@Controller
@RequestMapping(value="/template")
public class TemplateController {

	@Autowired
	private TemplateService templateService;
	@RequestMapping
	public String templateSelect(Template template,HttpServletRequest request, ModelMap modelMap){
		 
		List<Template> list = templateService.TemplateList(template);
		modelMap.put("list", list);
		request.setAttribute("template", template);
		return "template/template";
	}	
	
	@RequestMapping(value="template_Add")
	public ModelAndView templateAdd(Model model){
			ModelAndView mv=new ModelAndView();
				mv.setViewName("template/addTemplate");
			return mv;
	}
	
	@RequestMapping(value="/template_Add_Edit",method=RequestMethod.POST)
	public ModelAndView saveTemplate(Template template){
		ModelAndView mv=new ModelAndView();
		if(template.getInfoTenplateId()== null || template.getInfoTenplateId().intValue()==0){
			mv.addObject("template", template);
			if(templateService.saveTemplate(template)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			templateService.updateTemplate(template);
		}
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value="template_Edit")
	public ModelAndView toEdit(@RequestParam int infoTenplateId){
		ModelAndView mv=new ModelAndView();
		Template template=templateService.TemplateById(infoTenplateId);
		mv.setViewName("template/updateTemplate");
		mv.addObject("template", template);
		return mv;
	}
	
	@RequestMapping(value="/template_Del")
	public void deleteTemplate(@RequestParam int infoTenplateId,PrintWriter out){
		templateService.deleteTemplate(infoTenplateId);
		out.write("success");
		out.close();
	}
}
