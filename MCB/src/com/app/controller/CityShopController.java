package com.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.CityShop;
import com.app.entity.Gprs;
import com.app.service.CityShopService;
import com.app.service.GprsService;

@Controller
@RequestMapping(value="CityShop")
public class CityShopController{
	private HttpServletResponse response;
	private HttpServletRequest request;
	@Autowired 
	private CityShopService cityShopService;
	@Autowired
	private GprsService  gprsService;
	
	
	//获取response和request对象
	@ModelAttribute
	public void setReqAndResp(HttpServletResponse response,HttpServletRequest request)
	{
		this.response = response;
		this.request = request;
	}
	@RequestMapping
	public String list(CityShop cityShop,ModelMap modelMap){
		 
		List<CityShop> list = cityShopService.listPageCityShop(cityShop);
		modelMap.put("list", list);
		request.setAttribute("cityShop", cityShop);
		return "cityshop/cityshop";
	}
	@RequestMapping(value="/CityShop_add")
	public ModelAndView cirtShopand(ModelMap map){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("cityshop/addCityshop");
		return mv;
	}
	
 
	/**
	 * 请求编辑页面
	 * @param shopRingId
	 * @return
	 */
	@RequestMapping(value="/cityShop_update")
	public ModelAndView toEdit(@RequestParam int shopRingId){
		ModelAndView mv = new ModelAndView();
		CityShop cityShop = cityShopService.getCityShopById(shopRingId);
		List<Gprs> list=gprsService.getGprs();
		mv.setViewName("cityshop/updateCityshop");
		mv.addObject("list", list);
		mv.addObject("cityShop", cityShop);
		return mv;
	}
	
	@RequestMapping(value="/CityShop_save_edit",method=RequestMethod.POST)
	public ModelAndView saveErrorCode(CityShop cityShop){
		ModelAndView mv=new ModelAndView();
		if(cityShop.getShopRingId()== null || cityShop.getShopRingId().intValue()==0){
			mv.addObject("cityShop", cityShop);
			if(cityShopService.insertCityShop(cityShop)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			cityShopService.updateCityShop(cityShop);
		}
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 删除某个地区商圈
	 * @param userId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteUser(@RequestParam int shopRingId,PrintWriter out){
		cityShopService.deleteById(shopRingId);
		out.write("success");
		out.close();
	}
	
	@RequestMapping(value="/ajax_add")
	public void searchProvince(){
		try {
			//调用查询城市的方法
			List<Gprs> list=gprsService.getGprs();
			//将list集合转换成json格式
			JSONArray array=JSONArray.fromObject(list);
			
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
