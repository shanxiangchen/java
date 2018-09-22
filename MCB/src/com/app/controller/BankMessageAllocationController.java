package com.app.controller;
/**
 * 消息配置管理
 * @author 黄成
 */
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankMessageAllocation;
import com.app.service.BankMessageAllocationService;

@Controller
@RequestMapping(value="/messageAllocation")
public class BankMessageAllocationController {
	@Autowired
	private BankMessageAllocationService bankMessageAllocationService;
	
	/**
	 * 查询配置消息
	 * @author 黄成
	 * @param model
	 * @param bankMessageAllocation
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping
	public ModelAndView selectAllMessageAllocation(Model model,BankMessageAllocation bankMessageAllocation, ModelMap modelMap,HttpServletRequest request){
		List<BankMessageAllocation> messageAllocationList = bankMessageAllocationService.selectBankAllocationPageList(bankMessageAllocation);
		request.setAttribute("messageAllocationList", messageAllocationList);
		request.setAttribute("bankMessageAllocation", bankMessageAllocation);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("bankMessageAllocation/bankMessageAllocation");
		return mv;
	}
	
	/**
	 * 跳转到新增页面
	 * @author 黄成
	 * @return
	 */
	@RequestMapping(value="/addMessageAllocation")
	public ModelAndView addMessageAllocaiton(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("bankMessageAllocation/addMessageAllocation");
		return mv;
	}
	
	/**
	 * 保存消息配置信息
	 * @author 黄成
	 * @param bankMessageAllocation
	 * @param out
	 */
	@RequestMapping(value="/save_messageAllocation")
	public void saveMessageAllocation(BankMessageAllocation bankMessageAllocation,PrintWriter out){
		/*int times = bankMessageAllocation.getTimes();
		int intervalTime = bankMessageAllocation.getIntervalTime();*/
		int messageId = bankMessageAllocation.getMessageId();
		int messageNum = 0;
		if(messageId != 0){
			messageNum = bankMessageAllocationService.getBankAllocationNumById(bankMessageAllocation);
		}
//		if(messageCode != null && messageCode !=""){
			if(messageNum==0){
				//添加
				int bankMessageNum = bankMessageAllocationService.getBankAllocationNumByCode(bankMessageAllocation);
				if(bankMessageNum ==0){
					bankMessageAllocationService.saveBankAllocation(bankMessageAllocation);
					out.print("success");
					out.close();
				}else{
					out.print("2");//编码重复
					out.close();
				}
			}else{
				//修改
				bankMessageAllocationService.updateBankAllocation(bankMessageAllocation);
				out.print("success");
				out.close();
			}
//		}
	}
	
	/**
	 * 修改消息配置信息
	 * @author 黄成
	 * @param messageId
	 * @param bankMessageAllocation
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update_messageAllocation")
	public ModelAndView updateAllocation(@RequestParam String messageId,BankMessageAllocation bankMessageAllocation,HttpServletRequest request){
		int bankMessageId = Integer.parseInt(messageId);
		bankMessageAllocation.setMessageId(bankMessageId);
		BankMessageAllocation messageAllocation = new BankMessageAllocation();
		messageAllocation = bankMessageAllocationService.selectMessageALlocation(bankMessageAllocation);
		ModelAndView mv = new ModelAndView();
		request.setAttribute("messageAllocation", messageAllocation);
		mv.setViewName("bankMessageAllocation/updateMessageAllocation");
		return mv;
	}
	
	/**
	 * 删除消息配置信息
	 * @author 黄成
	 * @param request
	 * @param out
	 */
	@RequestMapping(value="/delete_messageAllocation")
	public void deleteAllocation(HttpServletRequest request,PrintWriter out){
		String messageId = request.getParameter("messageId");
		int bankMessageId = Integer.parseInt(messageId);
		bankMessageAllocationService.deleteMessageAllocaiton(bankMessageId);
		out.print("success");
		out.close();
	}

	
}


