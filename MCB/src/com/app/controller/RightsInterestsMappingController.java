package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.RightsPackagerkMapping;
import com.app.service.RightsInterestsMappingService;

/**
 * 权益映射Controller类 create date 2016/1/20 <br/>
 * 
 * @author shiguangtng@tansun.com.cn
 */
@Controller
@RequestMapping(value = "/rim")
public class RightsInterestsMappingController {
	@Autowired
	private RightsInterestsMappingService rightsInterestsMappingService;

	@RequestMapping()
	public ModelAndView listPageRightsInterestsMapping(
			RightsPackagerkMapping rightsPackagerkMapping,
			HttpServletRequest request, ModelMap modelMap) {
			ModelAndView mv = new ModelAndView();
		List<RightsPackagerkMapping> packagerkMappings = rightsInterestsMappingService
				.rightsPackagerkMappingPageList(rightsPackagerkMapping);
		modelMap.put("packagerkMappings", packagerkMappings);
		request.setAttribute("rightsPackagerkCode", rightsPackagerkMapping);
		mv.setViewName("rightsInterestsMapping/rightsInterestsMapping");
		return mv;
	}

	/*
	 * 跳转到新增页面
	 */
	@RequestMapping(value = "/addRightsMap")
	public ModelAndView addRightsMap(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("rightsInterestsMapping/addRightsMap");
		return mv;
	}

	/*
	 * 新增
	 */
	@RequestMapping(value = "/saveRightsMap", method = RequestMethod.POST)
	public ModelAndView saveRightsMap(
			RightsPackagerkMapping rightsPackagerkMapping,
			HttpServletRequest request, ModelMap modelMap, PrintWriter out) {
		ModelAndView mv = new ModelAndView();
		int i = rightsInterestsMappingService
				.selectRightsCount(rightsPackagerkMapping
						.getRightsPackagerkCode());
		if (i > 0) {
			out.print("1");
			out.close();
			return null;
		}
		rightsInterestsMappingService.saveRightsMap(rightsPackagerkMapping);
		mv.setViewName("save_result");
		return mv;
	}

	/*
	 * 删除
	 */
	@RequestMapping(value = "/delRightsMapById")
	public void delRightsMapById(@RequestParam String rightsIds,
			PrintWriter out, HttpServletRequest request) {

		String strs[] = rightsIds.split(",");
		rightsInterestsMappingService.deleteRightsMapById(strs);
		out.print("success");
		out.close();
	}

	/*
	 * 跳转到编辑页面
	 */
	@RequestMapping(value = "editRightsMapById")
	public ModelAndView editRightsMapById(@RequestParam String rightsId,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		RightsPackagerkMapping rightsMap = rightsInterestsMappingService
				.editRightsMapById(rightsId);

		request.setAttribute("rightsMap", rightsMap);
		mv.setViewName("rightsInterestsMapping/updateRightsMap");
		return mv;
	}

	/*
	 * 修改
	 */
	@RequestMapping(value = "/updateRightsMap", method = RequestMethod.POST)
	public void updateRightsMap(RightsPackagerkMapping rightsPackagerkMapping,
			PrintWriter out, HttpServletRequest request, ModelMap modelMap) {

		int count = rightsInterestsMappingService
				.updateRightsMap(rightsPackagerkMapping);
		if (count > 0) {
			out.print("success");
			out.close();
		} else {
			out.print("error");
			out.close();
		}

	}

}
