package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 功能模块处理
 * @author HuangCheng
 * 
 */
@Controller
@RequestMapping(value="/moduleFunctionName")
public class ModelFunctionNameController {
/*
	@Autowired
	private ModuleFunctionNameService modelFunctionNameService ;*/
	
	/**
	 * 跳转到moduleFunctionName--list页面
	 * @param moduleFunctionName
	 * @param model
	 * @return
	 */
	/*@RequestMapping
	public String ModuleFunctionNamelistPage(ModuleFunctionName moduleFunctionName,Model model){
		List<ModuleFunctionName> moduleFunctionNameList = modelFunctionNameService.getModuleFunctionNameListPage();
		model.addAttribute("moduleFunctionNameList",moduleFunctionNameList);
		return "moduleFunctionName/moduleFunctionName";
	}
	
	*//**
	 * 添加moduleFunctionName
	 * @return
	 *//*
	@RequestMapping(value="/add")
	public String addModuleFunctionName(){
		return "moduleFunctionName/addModuleFunctionName";
	}
	
	@RequestMapping(value="/save")
	public void saveModuleFunctionName(ModuleFunctionName moduleFunctionName,PrintWriter out){
		String moduleFunctionNameId = moduleFunctionName.getModuleFunctionNameId();
		if(moduleFunctionNameId != ""&&moduleFunctionNameId!= null){
			//update moduleFunctionName

		}else{
			//add moduleFunctionName
			
		}
	}
	
	*//**
	 * 修改moduleFunctionName
	 * @param moduleFunctionName
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value="/update")
	public String updateModuleFunctionName(ModuleFunctionName moduleFunctionName,Model model){
		
		return "moduleFunctionName/updateModuleFunctionName";
	}
	
	*//**
	 * 删除moduleFunctionName
	 * @param model
	 * @param moduleFunctionNameId
	 * @param out
	 *//*
	@RequestMapping(value="/delete")
	public void deleteModuleFunctionName(Model model, @RequestParam String moduleFunctionNameId,PrintWriter out){
		
	}*/
	
}
