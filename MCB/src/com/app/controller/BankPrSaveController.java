package com.app.controller;

  
 
 

 

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankPrKeyValue;
import com.app.entity.BankPrSave;
import com.app.entity.PageInfo;
import com.app.service.BankPrFiledTypeService;
import com.app.service.BankPrSaveService;
import com.app.util.PageBean;
 
@Controller
@RequestMapping(value="/bankPrSave")
public class BankPrSaveController{

	@Autowired
	private BankPrSaveService bankPrSaveService;
	@Autowired
	private BankPrFiledTypeService bankPrFiledTypeService;
	  
	  
	@RequestMapping
	public ModelAndView selectBankPrSaveList(Model model,PageInfo pageInfo,BankPrSave bankPrSave, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
	    String typeId=request.getParameter("typeId");
	    if(typeId!=null){
	    	bankPrSave.setTypeId(typeId);
	    }
		PageBean<BankPrSave> pageBean=bankPrSaveService.selectBankPrSaveList(bankPrSave,pageInfo);
		List<Map<String,String>> typeList=bankPrFiledTypeService.serviceTypeList();
		request.setAttribute("typeList",typeList);
		request.setAttribute("typeId", typeId);
		modelMap.put("pageBean", pageBean);
		mv.setViewName("subscribe/bankPrSaveList");
		return mv;
		
	}
	
	/**
	 * 跳转到权益预约详情 
	 * @author zhaolei
	 * @date 2016-4-25 下午5:05:30
	 */
	@RequestMapping(value="infoBankPrSaveById")
	public ModelAndView infoBankPrSaveById(@RequestParam String prSaveId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		Map<String,String> mapOne=bankPrSaveService.infoBankPrSaveById(prSaveId); 
		String typeId=mapOne.get("TYPE_ID");
		List<Map<String,String>> list=bankPrSaveService.selectFiledNameById(typeId);
		List<BankPrKeyValue> listInfo=new ArrayList<BankPrKeyValue>();
		for(int i=0;i<list.size();i++){
			BankPrKeyValue keyValue=new BankPrKeyValue();
			String key=list.get(i).get("FILED_NAME");//字段中文名称
			String keyAbc=list.get(i).get("FILED_ABC_NAME");//字段英文名称
			if("FILED_ALERT_INFO".equals(keyAbc)){
				continue;
			}else if("ALERT_INFO_ONE".equals(keyAbc)){
				continue;
			}else if("ALERT_INFO_TWO".equals(keyAbc)){
				continue;
			}else if("ALERT_INFO_THREE".equals(keyAbc)){
				continue;
			}else if("SERVICE_ALERT_INFO".equals(keyAbc)){
				continue;
			}
			String value=mapOne.get(keyAbc);
			keyValue.setKey(key);
			keyValue.setValue(value);
			listInfo.add(keyValue);
		}
		 
		request.setAttribute("listInfo",listInfo); 
		mv.setViewName("subscribe/infoBankPrSave");
		return mv;
	}
	
	/**
	 * 删除预约信息
	 * @author zhaolei
	 * @date 2016-4-26 上午11:58:45
	 */
	@RequestMapping(value = "/delBankPrSave" )
	public void  delBankPrSave(@RequestParam String prSaveIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=prSaveIds.split(",");
		   int count=bankPrSaveService.delBankPrSave(strs); 
		   if(count>0){
			   out.print("success");
			   out.close();
		   }else{
			   out.print("error");
			   out.close();
		   }
		   
	}

	 
	
	 
	 
}
