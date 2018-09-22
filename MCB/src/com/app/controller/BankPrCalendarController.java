package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankPrCalendar;
import com.app.service.BankPrCalendarService;

 
 
@Controller
@RequestMapping(value = "/calendar")
public class BankPrCalendarController {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BankPrCalendarService bankPrCalendarService;
	 
	
	 
	@RequestMapping
	public String marketShopList(Model model,
			BankPrCalendar bankPrCalendar, HttpServletRequest request,
			ModelMap modelMap) {
		 
		List<BankPrCalendar> list = bankPrCalendarService.selectCalendarPageList(bankPrCalendar);
		modelMap.put("list", list);
		model.addAttribute("bankPrCalendar", bankPrCalendar);
		return "subscribe/bankPrCalendarList";
	}
	
	/**
	 * 跳转到设置假期页面
	 * 
	 * @author zhaolei
	 * @date 2016-4-16 下午4:31:35
	 */
	@RequestMapping(value = "/addHoliday")
	public ModelAndView addHoliday(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("subscribe/addBankPrCalendar");
		return mv;
	}
	
	@RequestMapping(value = "/editCalendar")
	public ModelAndView editCalendar(@RequestParam String calendarId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		BankPrCalendar bankPrCalendar=bankPrCalendarService.selectCalendarById(calendarId);
		request.setAttribute("bankPrCalendar", bankPrCalendar);
		mv.setViewName("subscribe/updateBankPrCalendar");
		return mv;
	}
	
	//保存假期
	@RequestMapping(value = "/saveCalendar", method = RequestMethod.POST)
	public void saveBankPrHoliday(BankPrCalendar bankPrCalendar,
			HttpServletRequest request, ModelMap modelMap, PrintWriter out) {
		 
		int count=bankPrCalendarService.selectCountExists(bankPrCalendar);
		if(count>0){
			out.print("1");
			out.close();
		}else{
			int num = bankPrCalendarService.insertBankPrCalendar(bankPrCalendar);
			if(num>0){
				out.print("success");
				out.close();
			}else{
				out.print("error");
				out.close();
			} 
		}
		 
	}
	//保存修改内容
	@RequestMapping(value = "/updateCalendar", method = RequestMethod.POST)
	public void updateCalendar(BankPrCalendar bankPrCalendar,
			HttpServletRequest request, ModelMap modelMap, PrintWriter out) {
		String year =bankPrCalendar.getCalendarBeginDate().split("-")[0];
		bankPrCalendar.setCalendarYear(year);
		int num = bankPrCalendarService.updateCalendar(bankPrCalendar);
		if(num>0){
			out.print("success");
			out.close();
		}else{
			out.print("error");
			out.close();
		} 
		/*int count=bankPrCalendarService.selectCountExists(bankPrCalendar);
		if(count>0){
			out.print("1");
			out.close();
		}else{
			int num = bankPrCalendarService.updateCalendar(bankPrCalendar);
			if(num>0){
				out.print("success");
				out.close();
			}else{
				out.print("error");
				out.close();
			} 
		}*/
		 
	}
	
	//删除
	@RequestMapping(value = "/delCalendar")
	public void delCalendar(Model model, @RequestParam String calendarIds,
			PrintWriter out, HttpServletRequest request) {
		String strs[] = calendarIds.split(",");
		bankPrCalendarService.deleteCalendar(strs);
		out.print("success");
		out.close();
	}

	 
}
