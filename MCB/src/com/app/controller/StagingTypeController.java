package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.StagingType;
import com.app.service.StagingTypeService;
import com.app.service.StateService;

@Controller
@RequestMapping(value="/stagingType")
public class StagingTypeController {
	@Autowired
	private StagingTypeService stagingTypeService;
	@Autowired
	private StateService stateService;
	//分页查询
	@RequestMapping
	public String stagingTypeList(StagingType stagingType,HttpServletRequest request, ModelMap modelMap){
		List<StagingType> list =stagingTypeService.stagingTypePageList(stagingType);
		modelMap.put("list", list);
		request.setAttribute("stagingType",stagingType);
		return "stagingType/stagingType";
	}
	
	//请求分期类型页面
	@RequestMapping(value="/stagingTypeAdd")
	public ModelAndView stagingTypeAdd(Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("stagingType/addStagingType");
		return mv;
	}
	@RequestMapping(value="/add_Edit_StagingType",method=RequestMethod.POST)
	public ModelAndView addStagingType(StagingType stagingType){
		ModelAndView mv=new ModelAndView();
		if(stagingType.getId()== null || stagingType.getId().intValue()==0){
			mv.addObject("stagingType", stagingType);
			if(stagingTypeService.insertStagingType(stagingType)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			stagingTypeService.updateStagingType(stagingType);
		}
		mv.setViewName("save_result");
		return mv;
	}
	//按分期类型Id查询单条信息数据
	@RequestMapping(value="/stagingType_Edit")
	public ModelAndView toEdit(@RequestParam int id){
		ModelAndView mv = new ModelAndView();
		StagingType stagingType= stagingTypeService.stagingTypeById(id);
		mv.setViewName("stagingType/updateStagingType");
		mv.addObject("stagingType", stagingType);
		return mv;
	}
	
	/***
	 *  删除信息的时候判断是否有关联 
	 * @param out
	 */
	@RequestMapping(value="/getCountCode")
	public void  getBoundCount(PrintWriter out,String stagCode,HttpServletResponse response){
		  
		//调用查询总条数方法
		int count=stateService.getCountCode(stagCode);
		if(count>0){
			out.print("1");
			out.close();
		}else{
			out.print("success");
			out.close();
		}
		 
	}
	
	//删除单条数据
	@RequestMapping(value="/stagingType_del")
	public void delStagingType(@RequestParam int id,PrintWriter out){
		stagingTypeService.deleteStagingType(id);
		out.write("success");
		out.close();
	}
}
