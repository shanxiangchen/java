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

import com.app.entity.Patch;
import com.app.service.PatchService;
@Controller
@RequestMapping(value="patch")
public class PatchController {
	@Autowired
	private PatchService patchService;

	@RequestMapping
	public ModelAndView listPacth(Model model,HttpServletRequest request,Patch patch,ModelMap modelMap){
		ModelAndView mv=new ModelAndView();
		List<Patch> patchs = patchService.patchPageList(patch);
		modelMap.put("patchs", patchs);
		request.setAttribute("patch", patch);
		mv.setViewName("patcth/patch");
		return mv;
	}
	//实现补件类型删除数据信息
	@RequestMapping(value="/delPatch")
	public void delPatch(@RequestParam int patchTypeId,PrintWriter out){
		patchService.delPatch(patchTypeId);
		out.write("success");
		out.close();
	}
	//跳转对应添加页面
	@RequestMapping(value="addPacth")
	public ModelAndView addPacth(Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("patcth/addPatch");
		return mv;
	}
	
	@RequestMapping(value="/editPacth",method=RequestMethod.POST)
	public ModelAndView editPacth(Patch patch){
			ModelAndView mv=new ModelAndView();
			if(patchService.insertPatch(patch)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		mv.setViewName("save_result");
		return mv;
	}
}
