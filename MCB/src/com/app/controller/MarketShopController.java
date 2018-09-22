package com.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.app.entity.MarketActivityShop;
import com.app.service.MarketShopService;
/**
 * 营销商户Controller
 * @author admin
 *
 */

@Controller
@RequestMapping(value="/marketShop")
public class MarketShopController{
	
	@Autowired
	private MarketShopService marketShopService;
	public MarketShopService getMarketShopService() {
		return marketShopService;
	}
	public void setMarketShopService(MarketShopService marketShopService) {
		this.marketShopService = marketShopService;
	}

	@RequestMapping
	/**
	 * 商户显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	public String marketShopList(Model model,MarketActivityShop shop, ModelMap modelMap,HttpServletRequest request){
		 
		List<MarketActivityShop> list = marketShopService.selectMarketActivityShopListPage(shop);
		modelMap.put("list", list);
		request.setAttribute("shop", shop);
		return "marketShop/marketShop";
	}
	
	/**
	 * 模糊查询商户显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/selectByLike")
	public String selectShopListByLike(Model model,MarketActivityShop shop){
		List shopList = marketShopService.selectShopByLike(shop);
		model.addAttribute("shopList", shopList);
		return "marketShop/marketShop";
	}
	
	/**
	 * 请求新增营销商户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addShop")
	public String toAdd(){
		return "marketShop/addMarketShop";
	}
	
	/**
	 * 添加信息
	 * @param shop
	 * @return
	 */
	 
	@RequestMapping(value="/saveMarketShop",method=RequestMethod.POST)
	public ModelAndView saveUser(MarketActivityShop shop){
		int random1 =(int)((Math.random()*10000000)/10);
		int random2 =(int) ((Math.random()*10000000)/10);
		int random3 =(int) ((Math.random()*10000000)/10);
		String shopId = ""+random1+random2+random3;
		shop.setShopId(shopId);
		ModelAndView mv = new ModelAndView();
		marketShopService.insertMarketActivityShop(shop);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 请求编辑商户页面
	 * @param shopId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/editShop")
	public ModelAndView toEdit(@RequestParam String shopId){
		ModelAndView mv = new ModelAndView();
		List listShop=marketShopService.selectMarketActivityShopByShopId(shopId);
		mv.addObject("listShop", listShop);
		mv.setViewName("marketShop/updateMarketShop");
		return mv;
	}
	
	/**
	 * 保存修改信息
	 * @param shop
	 * @return
	 */
	 
	@RequestMapping(value="/updateOkMarketShop",method=RequestMethod.POST)
	public ModelAndView updateOk(MarketActivityShop shop){
		marketShopService.updateMarketShopById(shop);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除信息
	 * @param 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value="/deleteActShop")
	public void deleteActShop(Model model,@RequestParam String str,PrintWriter out){
		//String actIds="";
		//String shopIds="";
		String shopId=null;
		StringBuffer actIds = new StringBuffer("");
		StringBuffer shopIds = new StringBuffer("");
		List listShopIdDel=new ArrayList(); 
		String strs[]=str.split(",");
		List list=new ArrayList();
	    for (int i = 0; i < strs.length; i++) {
	    	list.add(strs[i]);
		}
	    
	    List listActIdAndShopId=marketShopService.selectActIdByShopId(list);
	    for (int i = 0; i < strs.length; i++) {
	    	 for (int j = 0; j < listActIdAndShopId.size(); j++) {
	    		  MarketActivityShop shop=(MarketActivityShop) listActIdAndShopId.get(j);
	    		  String actId=null;
	    		  if(shop!=null){
		    		  if(shop.getActivityId()!=null){
		    			  actId=shop.getActivityId() ;
		    		  }
		    		  
		    		  shopId=shop.getShopId();
		    		  
		    	  }
	    		  
	    		  if(shopId.equals(strs[i])){
		    		  if(actId!=null){
		    			  actIds.append(actId);
		    			  shopIds.append(shopId);
		    			  //actIds=actIds+actId;
		    			  //shopIds=shopIds+shopId+",";
		    		  }else{
		    			  listShopIdDel.add(strs[i]);
		    		  }
		    	  }
	    		  
		    	  
		    	  
		    }
		}
	    
	   if("".equals(shopIds.toString())){
		   int i= marketShopService.deleteMarketShopById(listShopIdDel);
		   //往页面写入数据类型用print 字符串用write
		   if(i>0){
			   out.write(shopIds.toString());
			   out.close();
		   }
		  
	   }else{
		   //往页面写入数据类型用print 字符串用write
		   out.write(shopIds.toString());
		   out.close();
	   }
	}
}
