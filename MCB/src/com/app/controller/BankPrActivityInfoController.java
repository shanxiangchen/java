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

import com.app.entity.BankPrActivityInfo;
import com.app.entity.BankPrHoliday;
import com.app.entity.RightsType;
import com.app.service.BankPrActivityInfoService;
import com.app.service.RightsTypeService;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping(value = "/prActivityInfo")
public class BankPrActivityInfoController {

	@Autowired
	private BankPrActivityInfoService bankPrActivityInfoService;
	@Autowired
	private RightsTypeService typeService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selectBankPrActivityInfoList(Model model,
			BankPrActivityInfo bankPrActivityInfo, ModelMap modelMap,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<BankPrActivityInfo> bankPrActivityInfos = bankPrActivityInfoService
				.prActivityInfoPageList(bankPrActivityInfo);
		modelMap.put("bankPrActivityInfos", bankPrActivityInfos);
		request.setAttribute("path", path);
		request.setAttribute("bankPrActivityInfo", bankPrActivityInfo);
		mv.setViewName("subscribe/bankPrActivityInfoList");
		return mv;
	}

	/**
	 * 跳转到新增权益活动页面
	 * 
	 * @author zhaolei
	 * @date 2016-3-31 上午11:30:04
	 */
	@RequestMapping(value = "/addPrActivityInfo")
	public ModelAndView addPrActivityInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<RightsType> listRihtsType = typeService.listrigthsSel();
		request.setAttribute("listRihtsType", listRihtsType);
		mv.setViewName("subscribe/addBankPrActivityInfo");
		return mv;
	}

	/**
	 * 新增权益活动
	 * 
	 * @author zhaolei
	 * @date 2016-4-16 下午3:51:24
	 */
	@RequestMapping(value = "/savePrActivityInfo", method = RequestMethod.POST)
	public ModelAndView savePrActivityInfo(
			BankPrActivityInfo bankPrActivityInfo, HttpServletRequest request,
			ModelMap modelMap, PrintWriter out) {
		int bankPrActivityInfoNum = bankPrActivityInfoService.getBankPrActivityInfoNum(bankPrActivityInfo);
		if(bankPrActivityInfoNum>0){
			out.write("1");
			out.close();
			return null;
		}
		String rightsTypeCode = "";
		String[] strs = bankPrActivityInfo.getRightsTypeCode().split(",");
		if (strs.length >= 1) {
			rightsTypeCode = strs[0];
		}
		if (bankPrActivityInfo.getActivityOrder() == null) {
			bankPrActivityInfo.setActivityOrder("0");
		}
		if (bankPrActivityInfo.getActivitySelfUsed() == null) {
			bankPrActivityInfo.setActivitySelfUsed("0");
		}
		if (bankPrActivityInfo.getActivityHeelUsed() == null) {
			bankPrActivityInfo.setActivityHeelUsed("0");
		}
		bankPrActivityInfo.setRightsTypeCode(rightsTypeCode);
		bankPrActivityInfoService.savePrActivityInfo(bankPrActivityInfo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除权益活动
	 * 
	 * @author zhaolei
	 * @date 2016-4-16 下午4:31:17
	 */
	@RequestMapping(value = "/deletePrActivityInfo")
	public void deletePrActivityInfo(@RequestParam String activityInfoIds,
			PrintWriter out, HttpServletRequest request) {

		String strs[] = activityInfoIds.split(",");
		bankPrActivityInfoService.deletePrActivityInfo(strs);
		out.print("success");
		out.close();
	}

	/**
	 * 跳转到权益预约编辑页面
	 * 
	 * @author zhaolei
	 * @date 2016-4-18 上午10:07:06
	 */
	@RequestMapping(value = "prActivityInfoListById")
	public ModelAndView prActivityInfoListById(
			@RequestParam String activityInfoId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		BankPrActivityInfo bankPrActivityInfo = bankPrActivityInfoService
				.prActivityInfoListById(activityInfoId);
		List<RightsType> listRihtsType = typeService.listrigthsSel();
		request.setAttribute("listRihtsType", listRihtsType);
		request.setAttribute("bankPrActivityInfo", bankPrActivityInfo);
		mv.setViewName("subscribe/updateBankPrActivityInfo");
		return mv;
	}

	/**
	 * 保存权益预约修改
	 * 
	 * @author zhaolei
	 * @date 2016-4-18 上午11:07:21
	 */
	@RequestMapping(value = "/updatePrActivityInfo", method = RequestMethod.POST)
	public void updatePrActivityInfo(BankPrActivityInfo bankPrActivityInfo,
			PrintWriter out, HttpServletRequest request, ModelMap modelMap) {
		String rightsTypeCode = "";
		String[] strs = bankPrActivityInfo.getRightsTypeCode().split(",");
		if (strs.length >= 1) {
			rightsTypeCode = strs[0];
		}
		if (bankPrActivityInfo.getActivityOrder() == null) {
			bankPrActivityInfo.setActivityOrder("0");
		}
		if (bankPrActivityInfo.getActivitySelfUsed() == null) {
			bankPrActivityInfo.setActivitySelfUsed("0");
		}
		if (bankPrActivityInfo.getActivityHeelUsed() == null) {
			bankPrActivityInfo.setActivityHeelUsed("0");
		}
		bankPrActivityInfo.setRightsTypeCode(rightsTypeCode);
		int count = bankPrActivityInfoService
				.updatePrActivityInfo(bankPrActivityInfo);
		if (count > 0) {
			out.print("success");
			out.close();
		} else {
			out.print("error");
			out.close();
		}

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
		mv.setViewName("subscribe/addBankPrHoliday");
		return mv;
	}

	/**
	 * 保存假期设置
	 * 
	 * @author zhaolei
	 * @date 2016-4-16 下午5:27:52
	 */
	@RequestMapping(value = "/saveBankPrHoliday", method = RequestMethod.POST)
	public void saveBankPrHoliday(BankPrHoliday bankPrHoliday,
			HttpServletRequest request, ModelMap modelMap, PrintWriter out) {
		String year = bankPrHoliday.getYdStartDate().split("-")[0];
		int num = bankPrActivityInfoService.updateBankPrHolidayToNo(year);
		if (num > 0) {
			int count = bankPrActivityInfoService
					.saveBankPrHoliday(bankPrHoliday);
			if (count > 0) {
				out.print("success");
				out.close();
			} else {
				out.print("error");
				out.close();
			}
		} else {
			out.print("error");
			out.close();
		}

	}

}
