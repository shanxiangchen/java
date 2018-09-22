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

import com.app.entity.BankCodeType;
import com.app.service.BankCodeTypeService;

/**
 * 代码类别
 * @author zhaolei 
 * @date 2016-3-15 下午8:00:13
 */
@Controller
@RequestMapping(value="/bankCodeType")
public class BankCodeTypeController{

	@Autowired
	private BankCodeTypeService bankCodeTypeService;
	  
	  
	@RequestMapping
	public ModelAndView selectBankCodeTypeList(Model model,BankCodeType bankCodeType, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankCodeType> list = bankCodeTypeService.selectBankCodeTypeList(bankCodeType);
		modelMap.put("list", list);
		request.setAttribute("bankCodeType", bankCodeType);
		mv.setViewName("bankCodeType/bankCodeTypeList");
		return mv;
		
	}
	
	/**
	 * 跳转到新增界面
	 * @author zhaolei
	 * @date 2016-3-15 下午8:29:54
	 */
	@RequestMapping(value="/add")
	public String toAdd(Model model){
		 
		return "bankCodeType/addBankCodeType";
	}
	
	@RequestMapping(value="/saveBankCodeType",method=RequestMethod.POST)
	public ModelAndView saveTemplet(BankCodeType bankCodeType,PrintWriter out){
		ModelAndView mv = new ModelAndView();
		if(bankCodeType!=null){
			String codeTypeId=bankCodeType.getCodeTypeId();
			int count=bankCodeTypeService.selectCountByCodeTypeId(codeTypeId); 
			if(count>0){
				out.write("1");
				out.close();
				return null;
			}
			bankCodeTypeService.saveBankCodeType(bankCodeType);
		}
		
		mv.setViewName("save_result");
		return mv; 
	    
	}
	
	/**
	 * 跳转到编辑页面
	 * @author zhaolei
	 * @date 2016-3-21 下午1:44:08
	 */
	@RequestMapping(value = "/edit")
	public String edtiTemplet(@RequestParam String codeTypeId, Model model) {
		BankCodeType  bankCodeType= bankCodeTypeService.getBankCodeTypeServiceById(codeTypeId);
		 
		model.addAttribute("bankCodeType", bankCodeType);
		 
		return "bankCodeType/updateBankCodeType";
	}
	
	/**
	 * 保存代码类别修改信息
	 * @author zhaolei
	 * @date 2016-3-21 下午2:00:49
	 */
	@RequestMapping(value="/updateBankCodeType",method=RequestMethod.POST)
	public ModelAndView updateBankCodeType(BankCodeType bankCodeType){
		ModelAndView mv = new ModelAndView();
		bankCodeTypeService.updateBankCodeType(bankCodeType);
		
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除代码类别
	 * @author zhaolei
	 * @date 2016-3-21 下午3:01:37
	 */
	@RequestMapping(value = "/deleteCodeType" )
	public void  deleteCodeType(@RequestParam String codeTypeIds, PrintWriter out){
		 
		   String strs[]=codeTypeIds.split(",");
		   bankCodeTypeService.deleteCodeType(strs);//删除代码类别
		   bankCodeTypeService.deleteCodeTypeInfoById(strs); //删除关联的代码信息
		   out.print("success");
		   out.close();
	}
	
	
	 
	 
}
