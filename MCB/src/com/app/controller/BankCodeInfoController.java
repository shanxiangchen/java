package com.app.controller;

 


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankCodeType;
import com.app.service.BankCodeInfoService;
 
/**
 * 代码信息
 * @author zhaolei 
 * @date 2016-3-15 下午8:00:13
 */
@Controller
@RequestMapping(value="/bankCodeInfo")
public class BankCodeInfoController{

	@Autowired
	private BankCodeInfoService bankCodeInfoService;
	  
	  
	@RequestMapping
	public ModelAndView selectBankCodeInfoList(Model model,BankCodeInfo bankCodeInfo, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankCodeInfo> list = bankCodeInfoService.selectBankCodeInfoList(bankCodeInfo);
		modelMap.put("list", list);
		request.setAttribute("bankCodeInfo", bankCodeInfo);
		mv.setViewName("bankCodeInfo/bankCodeInfoList");
		return mv;
		
	}
	
	/**
	 * 跳转到新增界面
	 * @author zhaolei
	 * @date 2016-3-15 下午8:29:54
	 */
	@RequestMapping(value="/add")
	public String toAdd(Model model){
		List<BankCodeType> typelist=bankCodeInfoService.selectBankCodeTypeList();
		model.addAttribute("typelist", typelist);
		return "bankCodeInfo/addBankCodeInfo";
	}
	
	/**
	 * 保存代码信息
	 * @author zhaolei
	 * @date 2016-3-16 上午10:50:48
	 */
	@RequestMapping(value="/saveBankCodeInfo",method=RequestMethod.POST)
	public ModelAndView saveTemplet(BankCodeInfo bankCodeInfo,PrintWriter out){
		ModelAndView mv = new ModelAndView();
		if(bankCodeInfo==null){
			return null;
		}
		String value = bankCodeInfo.getValue();
		Integer sortNo = bankCodeInfo.getSortNo();
		int random1 =(int)((Math.random()*10000000)/10);
		int random2 =(int) ((Math.random()*10000000)/10);
		int random3 =(int) ((Math.random()*10000000)/10);
		String codeInfoId = value+sortNo+random1+random2+random3;
		bankCodeInfo.setCodeInfoId(codeInfoId);
		Map<String,String> newMap=new HashMap<String,String>();
		newMap.put("value", bankCodeInfo.getValue());
		newMap.put("codeTypeId", bankCodeInfo.getCodeTypeId());
		int count=bankCodeInfoService.getCountByValue(newMap);
		if(count>0){
			out.print("1");
			out.close();
			return null;
		}
		bankCodeInfoService.saveBankCodeInfo(bankCodeInfo);
		mv.setViewName("save_result");
		return mv; 
	    
	}
	
	/**
	 * 删除代码信息
	 * @author zhaolei
	 * @date 2016-3-21 下午3:31:58
	 */
	@RequestMapping(value = "/deleteCodeInfo" )
	public void  deleteCodeType(@RequestParam String codeInfoIds, PrintWriter out){
		 
		   String strs[]=codeInfoIds.split(",");
		   bankCodeInfoService.deleteCodeInfo(strs); 
		  
		   out.print("success");
		   out.close();
	}
	
	/**
	 * 跳转到编辑界面
	 * @author zhaolei
	 * @date 2016-3-21 下午3:40:35
	 */
	@RequestMapping(value = "/edit")
	public String edtiTemplet(@RequestParam String codeInfoId, Model model) {
		BankCodeInfo  bankCodeInfo= bankCodeInfoService.getBankCodeInfoById(codeInfoId);
		 
		model.addAttribute("bankCodeInfo",  bankCodeInfo);
		 
		return "bankCodeInfo/updateBankCodeInfo";
	}
	
	/**
	 * 更新代码信息
	 * @author zhaolei
	 * @date 2016-3-21 下午4:49:24
	 */
	@RequestMapping(value="/updateBankCodeInfo",method=RequestMethod.POST)
	public ModelAndView updateBankCodeInfo(BankCodeInfo bankCodeInfo){
		ModelAndView mv = new ModelAndView();
		bankCodeInfoService.updateBankCodeInfo(bankCodeInfo);
		mv.setViewName("save_result");
		return mv;
	}
	 
	 
}
