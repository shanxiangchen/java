package com.app.controller;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.MarketActivity;
import com.app.entity.MarketActivityShop;
import com.app.service.MarketActivityService;
import com.app.service.MarketActivityShopService;
import com.app.service.MarketShopService;
/**
 * 营销活动与商户Controller
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/shopAndMarketActivity")
public class ShopAndMarketActivityController {
	@Autowired
	private MarketActivityShopService marketActivityShopService;
	@Autowired
	private MarketActivityService marketActivityService;
	@Autowired
	private MarketShopService marketShopService;
	
	public MarketActivityService getMarketActivityService() {
		return marketActivityService;
	}

	public void setMarketActivityService(MarketActivityService marketActivityService) {
		this.marketActivityService = marketActivityService;
	}

	public MarketActivityShopService getMarketActivityShopService() {
		return marketActivityShopService;
	}

	public void setMarketActivityShopService(
			MarketActivityShopService marketActivityShopService) {
		this.marketActivityShopService = marketActivityShopService;
	}

	@RequestMapping
	/**
	 * 营销活动与商户显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	public String marActivityList(Model model,HttpServletRequest request,MarketActivity market,ModelMap modelMap){
		 
		List<MarketActivity> list = marketActivityService.selectActivityShopListPage(market);
		modelMap.put("list", list);
		model.addAttribute("market", market);
		return "marketActivityAndShop/shopAndMarketActivity";
	}
	/**
	 * 新增信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/get")
	public String toAdd(){
		return "marketActivityAndShop/addMarketActivityAndShop";
	}
	
	@RequestMapping(value="/activity")
	public ModelAndView activity(Model model,ModelMap modelMap,HttpServletRequest request,
			MarketActivity marketActivity){
		 
		List<MarketActivity> list = marketActivityShopService.selectActivityAllId(marketActivity);
		modelMap.put("list", list);
		request.setAttribute("marketActivity", marketActivity);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("marketActivityAndShop/activity");
		return mv;	
	}
	
	
	@RequestMapping(value="/shop")
	public ModelAndView shop(Model model,ModelMap modelMap,HttpServletRequest request,
			MarketActivityShop marketActivityShop){
		 
		List<MarketActivityShop> list = marketShopService.selectActivityShopAllId(marketActivityShop);
		modelMap.put("list", list);
		request.setAttribute("marketActivityShop",marketActivityShop);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("marketActivityAndShop/shop");
		return mv;	
	}
    /**
     * 校验某活动和某商户是否存在关系
     * @param model
     * @param re 
     * @param out
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/selectCount")
	public void selectCount(Model model,HttpServletRequest re, String activityId,String marActivityShop,PrintWriter out){
		 
		String str=activityId;
		String strs=marActivityShop;
		Map newmap=new HashMap();
		newmap.put("shopId", strs);
		newmap.put("activityId", str);
		int count=marketActivityShopService.selectCountByShopIdAndActivityId(newmap);
		 
		int counts=marketActivityShopService.selectCountByShopId(newmap);
		    
		out.write(count+","+counts);
		out.close();
	}
	
	/**
	 * 添加信息
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	@RequestMapping(value="/saveActAndShop",method=RequestMethod.POST)
	public ModelAndView saveActAndShop(HttpServletRequest re,PrintWriter out){
		String str=re.getParameter("activityId");
		String shopId=re.getParameter("shopId");
		MarketActivity mar=new MarketActivity();
		mar.setActivityId(str);
		String strs[]=shopId.split(",");
		List li=new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			MarketActivityShop shop=new MarketActivityShop();
		    shop.setShopId(strs[i]);
			li.add(shop);
		}
		mar.setMarActivityShop(li);
		marketActivityShopService.updateActivityShopOfActivityId(mar);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("save_result");
	    return mv;
	}
	
	/**
	 * 请求编辑用户页面
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/editActivityAndShop")
	public ModelAndView toEditActivityAndShop(@RequestParam String activityId,@RequestParam String shopId,@RequestParam String activityTitle,@RequestParam String shopName,HttpServletRequest req,HttpServletResponse res){
			ModelAndView mv = new ModelAndView();
			//List listActId=marketActivityShopService.selectActivityAllId();
			List shopList=marketActivityService.selectShopNameByShopId(shopId);
			List listShop=null;
			for (int i = 0; i < shopList.size(); i++) {
				MarketActivity mar= (MarketActivity) shopList.get(i);
			    listShop=mar.getMarActivityShop();
			}
			mv.addObject("activityId", activityId);
			mv.addObject("shopId", shopId);
			mv.addObject("activityTitle", activityTitle);
			mv.addObject("listShop", listShop);
			mv.setViewName("marketActivityAndShop/updateMarketActivityAndShop");
			//mv.addObject("listActId", listActId);
		return mv;
	}
	
	/**
	 * 保存信息
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/updateActAndShopOk",method=RequestMethod.POST)
	public ModelAndView updateOk(HttpServletRequest re){
	    String activityId= re.getParameter("activityId");
	    String marActivityShop= re.getParameter("marActivityShop");
	    MarketActivity mar=new MarketActivity();
		mar.setActivityId(activityId);
		MarketActivityShop shop=new MarketActivityShop();
		shop.setShopId(marActivityShop);
		List li=new ArrayList();
		li.add(shop);
		mar.setMarActivityShop(li);
		marketActivityShopService.updateActivityShopOfActivityId(mar);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 解除关系
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/deleteActandShop")
	public void deleteActAndShop(Model model,@RequestParam String str,PrintWriter out){
		MarketActivity mar=new MarketActivity();
	    mar.setActivityId(null);
		String strs[]=str.split(",");
		List li=new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			MarketActivityShop shop=new MarketActivityShop();
		    shop.setShopId(strs[i]);
			li.add(shop);
		}
		mar.setMarActivityShop(li);
		int i=marketActivityShopService.updateActivityShopOfActivityId(mar);
		//往页面写入数据类型用print 字符串用write
		out.print(i);
		out.close();
	}
}
