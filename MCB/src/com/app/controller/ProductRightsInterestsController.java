package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ProductRightsInterests;
import com.app.service.ProductRightsInterestsService;

/**
 * 产品权益controller类
 * create date 2016/1/21
 * <br/>
 * @author shigaungting@tansun.com.cn
 *
 */
@Controller
@RequestMapping(value="pri")
public class ProductRightsInterestsController {
	@Autowired
	private ProductRightsInterestsService productRightsInterestsService;
	@RequestMapping()
	public ModelAndView listPageProductRightsInterests(ProductRightsInterests productRightsInterests,
			HttpServletRequest request,ModelMap modelMap){
			ModelAndView mv=new ModelAndView();
			List<ProductRightsInterests> rightsInterests=productRightsInterestsService
					.productRightsInterestsPageList(productRightsInterests);
			modelMap.put("rightsInterests", rightsInterests);
			mv.addObject("productRightsInterests", productRightsInterests);
			mv.setViewName("productRightsInterests/productRightsInterests");
		return mv;
	}
}
