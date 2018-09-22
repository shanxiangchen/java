package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankInnerLink;
import com.app.service.BankInnerLinkService;
 
@Controller
@RequestMapping(value="/bankInnerLink")
public class BankInnerLinkController{

	@Autowired
	private BankInnerLinkService bankInnerLinkService;
	  
	@RequestMapping
	public ModelAndView selectBankLohasAllocationList(Model model,BankInnerLink bankInnerLink, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankInnerLink> bankInnerLinkList = bankInnerLinkService.selectBankInnerLinkPageList(bankInnerLink);
		modelMap.put("bankInnerLinkList", bankInnerLinkList);
		modelMap.put("bankInnerLink", bankInnerLink);
		mv.setViewName("innerLink/innerLink");
		return mv;
	}
	
	/**
	 * 跳转到新增页面
	 * @author Huangcheng
	 * @date 2016-5-4 下午1:53:19
	 */
	@RequestMapping(value="/addBankInnerLink")
	public ModelAndView addLohasAllocation(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("innerLink/addInnerLink");
		return mv;
	}
	
	/**
	 * 测试是否存在
	 * @param bankInnerLink
	 * @param multipartRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/testBankLink",method=RequestMethod.POST)
	public void tesBankLinkNoOnly(BankInnerLink bankInnerLink,HttpServletRequest request,HttpServletResponse response){
		//处理逻辑
		/*
		 * 检验唯一性
		 * @author huangcheng
		 */
		String linkNo = request.getParameter("linkNo");
		bankInnerLink.setLinkNo(linkNo);
		int num=bankInnerLinkService.selectBankInnerLinkNum(bankInnerLink);
		String errorInfo = "";
		if(num > 0){
			errorInfo = "fail";
		}else{
			errorInfo = "success";
		}

		//返回前端数据
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("errorInfo", errorInfo);
		PrintWriter pt = null;
		JSONObject json = JSONObject.fromObject(data);
		String jsonStr = json.toString();
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			pt = response.getWriter();
			pt.write(jsonStr);
			pt.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pt != null){
				pt.close();
			}
		}
	}
	
	@RequestMapping(value = "/saveBankInnerLink", method = RequestMethod.POST)
	public ModelAndView saveLohasAllocation(BankInnerLink bankInnerLink,DefaultMultipartHttpServletRequest multipartRequest,
		HttpServletRequest request){
	    int num=bankInnerLinkService.selectBankInnerLinkNum(bankInnerLink);
	    ModelAndView mv=new ModelAndView();
	    if(num>0){
	    	mv.addObject("msg","failed");
	    }else{
	    	bankInnerLinkService.insertBankInnerLink(bankInnerLink);
	    	mv.addObject("msg","success");
	    }
	    mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 编辑内链
	 * @param bankInnerLink
	 * @param request
	 * @param modelMap
	 * @param out
	 * @return
	 * @author Huangcheng
	 */
	@RequestMapping(value="/editBankInnerLink")
	public ModelAndView editBankInnerLink(@RequestParam(value="linkNo",required=false) String linkNo,BankInnerLink bankInnerLink,HttpServletRequest request,ModelMap modelMap){
		bankInnerLink.setLinkNo(linkNo);
		ModelAndView mv=new ModelAndView();
		bankInnerLink =bankInnerLinkService.selectBankInnerLink(bankInnerLink); 
		modelMap.put("bankInnerLink",bankInnerLink);
		mv.setViewName("innerLink/editInnerLink");
		return mv;
	}
	
	@RequestMapping(value="/updateSaveBankInnerLink")
	public ModelAndView updateSaveBankInnerLink(BankInnerLink bankInnerLink,ModelAndView mv){
		bankInnerLinkService.updateSaveBankInnerLink(bankInnerLink);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 * @param linkId
	 * @param out
	 * @param request
	 * @author Huangcheng
	 */
	@RequestMapping(value = "/delBankInnerLink" )
	public void  delBankInnerLink(@RequestParam(value="linkNo",required=false) String linkNo, PrintWriter out,HttpServletRequest request){
	    int count=bankInnerLinkService.deleteBankInnerLink(linkNo);
	    if(count>0){
	    	out.print("success");
		    out.close();
	    }else{
	    	out.print("error");
		    out.close();
	    }
		    
	}
	
}
