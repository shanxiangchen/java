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

import com.app.entity.Type;
import com.app.service.TypeService;


@Controller
@RequestMapping(value="/type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	/**
	 * 显示列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping
	public String type(Type type,HttpServletRequest request, ModelMap modelMap){	
		 
		List<Type> list = typeService.listPagetypes(type);
		modelMap.put("list", list);
		request.setAttribute("type", type);
		return "types/types";
}
	
	/**
	 * 请求新增页面
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String toAdd(Model model, Type type) {
		return "types/addType";
	}

	/**
	 * 请求编辑
	 * @param oddsshoptypeid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String toEdit(@RequestParam String oddsshoptypeid, Model model) {
		Type type = typeService.gettypeById(oddsshoptypeid);
		model.addAttribute("type", type);
		return "types/updateType";
	}

	/**
	 * 保存信息
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(Type type){
		ModelAndView mv = new ModelAndView();
		if(typeService.savetype(type)==false){
			mv.addObject("msg","failed");
		}else{
			mv.addObject("msg","success");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	
	@RequestMapping("/saver")
	public String saver(Model model, Type type) {
		typeService.savertype(type);
		model.addAttribute("msg", "success");
		return "save_result";
	}
	/**
	 * 按ID 删除
	 * @param oddsshoptypeid
	 * @param out
	 */
	@RequestMapping(value = "/delete")
	public void deletetype(@RequestParam String oddsshoptypeid, PrintWriter out) {
		typeService.deletetype(oddsshoptypeid);
		out.write("success");
		out.close();
	}


}