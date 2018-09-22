package com.app.controller;
import java.io.PrintWriter;
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

import com.app.entity.HappyPooll;
import com.app.service.HappyPoollService;

@Controller
@RequestMapping(value = "/happyPooll")
public class HappyPoollController {
	@Autowired
	private HappyPoollService poollService;

	/**
	 * 分页,查询
	 * 
	 * @param model
	 * @param happyPooll
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping
	public ModelAndView listHappyPool(Map<String, Object> map, HappyPooll happyPooll, HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<HappyPooll> list = poollService.listPageHappyPooll(happyPooll);
		modelMap.put("list", list);
		request.setAttribute("happyPooll", happyPooll);
		mv.setViewName("pahhypool/pahhypoolList");
		return mv;
	}

	/**
	 * 新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/happyAdd")
	public ModelAndView happyAdd(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pahhypool/addPahhypool");
		return mv;
	}

/**
 * 编辑
 * @param happyPoollId
 * @param model
 * @return
 */
	@RequestMapping(value = "/happyupdate")
	public ModelAndView happyupdate(String happyPoollId, Model model) {
		ModelAndView mv = new ModelAndView();
		// 查询单条数据
		HappyPooll happyPooll = poollService.getHappyPoollByid(happyPoollId);
		// 查询过后要返回对象
		model.addAttribute("happyPooll", happyPooll);
		mv.setViewName("pahhypool/updatePahhypool");
		return mv;
	}

	/**
	 * 保存.修改
	 * @param happyPooll
	 * @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public ModelAndView savehp(HappyPooll happyPooll) {
		ModelAndView mv = new ModelAndView();
		if (happyPooll.getHappyPoollId() == null || happyPooll.getHappyPoollId().equals("")) {
			mv.addObject("happyPooll", happyPooll);
			if (poollService.saveHappyPoolllorder(happyPooll) == false) {
				mv.addObject("msg", "failed");
			} else {
				mv.addObject("msg", "success");
			}
			poollService.saveHappyPoolllorder(happyPooll);
		} else {
			if(poollService.update(happyPooll)==false){
				mv.addObject("msg", "failed");
			} else {
				mv.addObject("msg", "success");
			}
			poollService.update(happyPooll);
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param happyPoollId
	 * @param out
	 */
	@RequestMapping(value = "/delect")
	public void deletehp(@RequestParam String happyPoollId, PrintWriter out) {
		poollService.delete(happyPoollId);
		out.write("success");
		out.close();
	}
}
