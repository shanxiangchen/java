package com.app.controller;

 
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankPrRightsFiled;
import com.app.entity.PageInfo;
import com.app.service.BankPrRightsFiledService;
import com.app.util.PageBean;
 
@Controller
@RequestMapping(value="/rightsFiled")
public class BankPrRightsFiledController{

	@Autowired
	private BankPrRightsFiledService bankPrRightsFiledService;
	  
	  
	@RequestMapping
	public ModelAndView selectBankPrRightsFiledList(Model model,PageInfo pageInfo,BankPrRightsFiled bankPrRightsFiled, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		String filedName=request.getParameter("filedName");
		if(filedName!=null){
			bankPrRightsFiled.setFiledName(filedName);
		}
		PageBean<BankPrRightsFiled> pageBean=bankPrRightsFiledService.selectBankPrRightsFiledList(bankPrRightsFiled,pageInfo);
		modelMap.put("pageBean", pageBean);
		request.setAttribute("filedName", filedName);
		mv.setViewName("subscribe/bankPrRightsFiledList");
		return mv;
		
	}
	
	/**
	 * 跳转到字段新增页面
	 * @author zhaolei
	 * @date 2016-4-25 上午10:45:30
	 */
	@RequestMapping(value="/addRightsFiled")
	public ModelAndView addRightsFiled(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("subscribe/addBankPrRightsFiled");
		return mv;
	}
	
	/**
	 * 保存新增字段
	 * @author zhaolei
	 * @date 2016-4-26 下午4:01:01
	 */
	@RequestMapping(value = "/saveRightsFiled", method = RequestMethod.POST)
	public void saveRightsFiled(BankPrRightsFiled bankPrRightsFiled, HttpServletRequest request,ModelMap modelMap,PrintWriter out){
			int countNum=bankPrRightsFiledService.saveRightsFiled(bankPrRightsFiled);
			if(countNum>0){
				out.print("success");
				out.close();
			}else{
				out.print("error");
				out.close();
			}
			 
	}
	
	@RequestMapping(value = "/delRightsFiled" )
	public void  delRightsFiled(@RequestParam String filedIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=filedIds.split(",");
		   int count=bankPrRightsFiledService.delRightsFiled(strs); 
		   if(count>0){
			   out.print("success");
			   out.close();
		   }else{
			   out.print("error");
			   out.close();
		   }
		   
	}
	
	/**
	 * 跳转到编辑页面
	 * @author zhaolei
	 * @date 2016-4-26 下午4:32:12
	 */
	@RequestMapping(value="/editRightsFiled")
	public ModelAndView editRightsFiled(@RequestParam String filedId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		BankPrRightsFiled bankPrRightsFiled=bankPrRightsFiledService.selectRightsFiledById(filedId);
		request.setAttribute("bankPrRightsFiled", bankPrRightsFiled);
		mv.setViewName("subscribe/updateBankPrRightsFiled");
		return mv;
	}
	
	@RequestMapping(value="updateRightsFiled")
	public void updateRightsFiled(BankPrRightsFiled bankPrRightsFiled,HttpServletRequest request, ModelMap modelMap,PrintWriter out) {
		int count=bankPrRightsFiledService.updateRightsFiled(bankPrRightsFiled);
		if(count>0){
			out.print("success");
			out.close();
		}else{
			out.print("success");
			out.close();
		}
		 
	}
	
	 
	 
}
