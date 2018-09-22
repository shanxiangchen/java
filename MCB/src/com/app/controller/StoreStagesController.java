package com.app.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.PaymentCity;
import com.app.entity.StoreStages;
import com.app.listener.StuService;
import com.app.service.PaymentCityService;
import com.app.service.StoreStagesService;
 
/**
 * 分期商店信息Controller
 * @author admin
 *
 */

@Controller
@RequestMapping(value="/storeStages")
public class StoreStagesController{

	@Autowired
	private StoreStagesService storeStagesService;
	 
	@Autowired
	private PaymentCityService paymentCityService;
	
	public StoreStagesService getStoreStagesService() {
		return storeStagesService;
	}

	public void setStoreStagesService(StoreStagesService storeStagesService) {
		this.storeStagesService = storeStagesService;
	}

	@RequestMapping
	/**
	 * 分期商店信息显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	public String marketShopList(Model model,StoreStages store, ModelMap modelMap,HttpServletRequest request){
		 
		List<StoreStages> list = storeStagesService.selectStoreStagesListPage(store);
		List<PaymentCity> paymentcitylist = paymentCityService.listPayment(null);
		model.addAttribute("paymentcitylist", paymentcitylist);
		modelMap.put("list", list);
		model.addAttribute("store", store);
		return "StoreStages/storeStages";
		
	}
	
	 
	@RequestMapping(value = "/addmerchantList")
	public ModelAndView bank_book(@RequestParam("files") MultipartFile files,
			HttpServletRequest request, HttpServletResponse response,
			PrintWriter out, ModelMap model){
		ModelAndView mv = new ModelAndView();
		File targetFile = null;
		String ur = null; 
		Date date=new Date(); 
		StringBuffer strs=new StringBuffer();
		if (files != null) {
			long size = files.getSize();
			if(size>1024*1024){
				out.write("1");
				out.close();
				return null; 
			}
			try {
				 
				//获取服务器路径
				String uploadTxt = request.getSession().getServletContext()
						.getRealPath("");
				 
				// 文件名是否为空
				if (StringUtils.hasText(files.getOriginalFilename())) {
					String name = files.getOriginalFilename();
					String body="";
					String ext="";
					
					int pot=name.lastIndexOf(".");   
					if(pot!=-1){   
				       body= date.getTime() +"";   
				       ext=name.substring(pot);   
				    }else{   
				       body=date.getTime()+"";   
				       ext="";   
				    }   
				    String newName=body+ext;
					targetFile = new File(uploadTxt, newName);
				    if(!targetFile.exists()){
				    	targetFile.mkdirs();
				    }
					files.transferTo(targetFile);
			  
					ur = uploadTxt + File.separator+ newName;
				}
			
				//拿到路径
				List<StoreStages> listExcel = StuService.getAllByExcel(ur);
				if(listExcel==null||listExcel.size()==0){
					out.write("2");
					out.close();
					return null;
				}
				List<String> storeNoList=storeStagesService.selectStoreNoList();
				List<PaymentCity> paymentcitylist = paymentCityService.listPayment(null);
				List<String> cityCodeList=new ArrayList<String>();
				for(PaymentCity city:paymentcitylist){
					cityCodeList.add(city.getCityCode());
				}
				List<String> newList=new ArrayList<String>();
				List<StoreStages> insertList=new ArrayList<StoreStages>();
				int count=0;int total=0;int flg=0;
                
				//for (StoreStages stuEntity : listExcel) {
                for (int i=0;i<listExcel.size();i++) {
                	StoreStages stuEntity=listExcel.get(i);
					StoreStages sto = new StoreStages();
					String storeNo=stuEntity.getStoreNo();
					String storeName = stuEntity.getStoreName();
					
					String storeAddr = stuEntity.getStoreAddr();
					String fee3 = "".equals(stuEntity.getFee3().trim())||!stuEntity.getFee3().trim().matches("^[0-9]+([.]{1}[0-9]+){0,1}$")?"-":stuEntity.getFee3().trim();
					String fee6 =  "".equals(stuEntity.getFee6().trim())||!stuEntity.getFee6().trim().matches("^[0-9]+([.]{1}[0-9]+){0,1}$")?"-":stuEntity.getFee6().trim();
					String fee12 =  "".equals(stuEntity.getFee12().trim())||!stuEntity.getFee12().trim().matches("^[0-9]+([.]{1}[0-9]+){0,1}$")?"-":stuEntity.getFee12().trim();
					String fee24 =  "".equals(stuEntity.getFee24().trim())||!stuEntity.getFee24().trim().matches("^[0-9]+([.]{1}[0-9]+){0,1}$")?"-":stuEntity.getFee24().trim();
					String cityNo = stuEntity.getCityNo();
					String tradeName = stuEntity.getTradeName();
					if(storeNo==null||"".equals(storeNo.trim())||storeName==null||"".equals(storeName.trim())||storeAddr==null||"".equals(storeAddr.trim())
							||cityNo==null||"".equals(cityNo.trim())||tradeName==null||"".equals(tradeName.trim())){
						count++;
						strs.append("第"+(i+2)+"行存在空值").append("\r\n");
						break;
				    }
					if(storeNoList.contains(storeNo)){
						total++;
						strs.append("第"+(i+2)+"行商户编号已经存在").append("\r\n");
						continue;
					}
					if(!cityCodeList.contains(cityNo)){
						strs.append("第"+(i+2)+"行城市编号不存在").append("\r\n");
						continue;
					}
					if(newList.contains(storeNo)){
						strs.append("第"+(i+2)+"行商户编号重复").append("\r\n");
						flg++;
						break;
					}
					newList.add(storeNo);
					sto.setStoreNo(storeNo);
					sto.setStoreName(storeName);
					sto.setStoreAddr(storeAddr);
					sto.setFee3(fee3);
					sto.setFee6(fee6);
					sto.setFee12(fee12);
					sto.setFee24(fee24);
					sto.setCityNo(cityNo);
					sto.setTradeName(tradeName);
					insertList.add(sto); 
				}
				if(count>0||listExcel==null||listExcel.size()==0){
					out.write("2");
					out.close();
					return null;
				}
				if(total>0&&(insertList==null||insertList.size()==0)){
					out.write("3");
					out.close();
					return null;
				}
				if(flg>0){
					out.write("4");
					out.close();
					return null;
				}
				
				if(insertList!=null&&insertList.size()!=0){
					storeStagesService.getJdbcCon(insertList);
					out.write("success");
				}else{
					out.write("fail");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				targetFile.delete();
				out.close(); 
			}
		}
		//查询显示
		mv.setViewName("save_result");
	    return mv;
	}
	
	
	/**
	 * <p>导入分期商户错误日志生成txt文档</p>
	 */
	public void printTxt(String strs){
		FileWriter fw=null;
		BufferedWriter bw=null;
		String url="";
		try {
			if(!"".equals(strs)&&strs!=null){
				File[] roots = File.listRoots();
		        if(roots.length>=2){
		        	url=roots[1].toString()+System.currentTimeMillis()+"_log.txt";
		        }else if(roots.length>0){
		        	url=roots[0].toString()+System.currentTimeMillis()+"_log.txt";
		        }
				
				fw=new FileWriter(url);
			    bw=new BufferedWriter(fw);
			    bw.write(strs);
			   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.flush();
					bw.close();
				}
				if(fw!=null){
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 请求新增营销商户页面
	 * @param model
	 * @return
	 */
	 
	@RequestMapping(value="/addStages")
	public String toAdd(Model model, PaymentCity paymentCity){
		List<PaymentCity> paymentcitylist = paymentCityService.listPayment(paymentCity);
		model.addAttribute("paymentcitylist", paymentcitylist);
		return "StoreStages/addStoreStages";
	}
	
	/**
	 * 添加信息
	 * @param shop
	 * @return
	 */
	@RequestMapping(value="/save")
	public void save(StoreStages store,PrintWriter out){
		//ModelAndView mv = new ModelAndView();
		List<String> storeNoList=storeStagesService.selectStoreNoList();
		if(storeNoList.contains(store.getStoreNo())){
			out.write("1");
			out.close();
			 
		}else{
			storeStagesService.savestorestages(store);
			out.write("2");
			out.close();
		}
		 
		 
	}
	
	/**
	 * 请求编辑商户页面
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String toEdit(@RequestParam String storeStagesId, Model model) {
		StoreStages  storestages= storeStagesService.getStoreStagesById(storeStagesId);
		String cityName=storestages.getPaymentcity().getCityName();
		model.addAttribute("storestages", storestages);
		model.addAttribute("cityName", cityName);
		return "StoreStages/updateStoreStages";
	}
	
	
	@RequestMapping(value = "/importout")
	public String importOtu() {
		return "StoreStages/importoutStoreStages";
	}
	
	
	/**
	 * 保存修改信息
	 * @param shop
	 * @return
	 */
	@RequestMapping(value="/updateStoreStages",method=RequestMethod.POST)
	public ModelAndView updateOk(StoreStages storestages){
		ModelAndView mv = new ModelAndView();
		storeStagesService.saverStoreStages(storestages);
		storeStagesService.selectStoreStagesListPage(storestages);
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value = "/delete")
	public void deleteShop(@RequestParam String storeStagesId, PrintWriter out) {
		storeStagesService.deleteStoreStages(storeStagesId);
		out.write("success");
		out.close();
	}
	
	@RequestMapping(value = "/deleteActShop" )
	public void  deleteActShop(@RequestParam String storeStagesId, PrintWriter out){
		 
		   String strs[]=storeStagesId.split(",");
		   storeStagesService.deleteActShop(strs);
		   out.print("success");
		   out.close();
	}
}
