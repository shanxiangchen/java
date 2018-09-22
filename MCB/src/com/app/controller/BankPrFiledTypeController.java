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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankPrFiledType;
import com.app.service.BankPrFiledTypeService;
 
@Controller
@RequestMapping(value="/filedType")
public class BankPrFiledTypeController{

	@Autowired
	private BankPrFiledTypeService bankPrFiledTypeService;
	  
	  
	@RequestMapping
	public ModelAndView selectBankPrFiledTypeList(Model model,BankPrFiledType bankPrFiledType, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankPrFiledType> bankPrFiledTypes=bankPrFiledTypeService.bankPrFiledTypePageList(bankPrFiledType);
		List<Map<String,String>> typeList=bankPrFiledTypeService.serviceTypeList();
		request.setAttribute("typeList",typeList);
		modelMap.put("bankPrFiledTypes", bankPrFiledTypes);
		request.setAttribute("bankPrFiledType", bankPrFiledType); 
		mv.setViewName("subscribe/bankPrFiledTypeList");
		return mv;
		
	}
	
	/**
	 * 跳转到字段新增页面
	 * @author zhao.lei
	 * @date 2016-4-25 上午10:45:30
	 */
	@RequestMapping(value="/addFiledType")
	public ModelAndView addFiledType(HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<Map<String,String>> typeList=bankPrFiledTypeService.serviceTypeList();
		List<Map<String,String>> filedList=bankPrFiledTypeService.selectFiledList();
		List<Map<String,String>> baseList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> moreList=new ArrayList<Map<String,String>>();
		for(int i=0;i<filedList.size();i++){
			String showWhere=filedList.get(i).get("FILEDSHOWWHERE");
			if("1".equals(showWhere)){
				baseList.add(filedList.get(i));
			}else if("2".equals(showWhere)){
				moreList.add(filedList.get(i));
			}
		}
		request.setAttribute("typeList",typeList);
		request.setAttribute("baseList",baseList);
		request.setAttribute("moreList",moreList);
		mv.setViewName("subscribe/addBankPrFiledType");
		return mv;
	}
	
	/**
	 * 保存类型对应字段
	 * @author zhao.lei
	 * @date 2016-4-25 上午11:48:29
	 */
	@RequestMapping(value = "/saveFiledType", method = RequestMethod.POST)
	public void saveFiledType(BankPrFiledType bankPrFiledType, HttpServletRequest request,ModelMap modelMap,PrintWriter out){
	 		
		try {
			int count=bankPrFiledTypeService.selectExistsTypeId(bankPrFiledType);	
		    if(count>0){
		    	out.print("1");
				out.close();
		    }else{
		    	String filedIds=bankPrFiledType.getFiledIds();//字段ID拼接串
				String filedNames=bankPrFiledType.getFiledNames();//字段名称拼接
				String filedAbcNames=bankPrFiledType.getFiledAbcNames();//字段英文名称
				String sortNos=bankPrFiledType.getSortNos();//排序号拼接串
				String isShows=bankPrFiledType.getIsShows();//是否展示拼接字符串
				String maxlengths=bankPrFiledType.getMaxlengths();//最大长度拼接串
				String filedAlertInfo=bankPrFiledType.getFiledAlertInfo();//字段提示信息
				String alertInfoOne=bankPrFiledType.getAlertInfoOne();//备注提示1
				String alertInfoTwo=bankPrFiledType.getAlertInfoTwo();//备注提示2
				String alertInfoThree=bankPrFiledType.getAlertInfoThree();//备注提示3
				String serviceAlertInfo=bankPrFiledType.getServiceAlertInfo();//服务提示
				String[] filedIdArry=filedIds.split(",");
				String[] filedNameArry=filedNames.split(",");
				String[] filedAbcArry=filedAbcNames.split(",");
				String[] sortNoArry=sortNos.split(",");
				String[] maxlengthArry=maxlengths.split(",");
				String[] isShowArry=null;
				int num=0;
				for(int j=0;j<filedAbcArry.length;j++){
					if(filedAbcArry[j].indexOf("YES_AND_NO")!=-1){
						num++;
					}
				}
				//int num=Arrays.binarySearch(filedAbcArry, "YES_AND_NO");
				if(isShows!=null&&num>0){
					 isShowArry=isShows.split(",");
				}
				for(int i=0;i<filedIdArry.length;i++){
					bankPrFiledType.setFiledId(filedIdArry[i]);
					bankPrFiledType.setFiledName(filedNameArry[i]);
					bankPrFiledType.setSortNo(Integer.parseInt(sortNoArry[i]));
					bankPrFiledType.setMaxLength(maxlengthArry[i]);
					if(isShowArry==null){
						bankPrFiledType.setIsShow("0");
					}else{
						bankPrFiledType.setIsShow(isShowArry[i]); 
					}
					if("FILED_ALERT_INFO".equals(filedAbcArry[i])){
						bankPrFiledType.setFiledAlert(filedAlertInfo);
					}else if("ALERT_INFO_ONE".equals(filedAbcArry[i])){
						bankPrFiledType.setFiledAlert(alertInfoOne);
					}else if("ALERT_INFO_TWO".equals(filedAbcArry[i])){
						bankPrFiledType.setFiledAlert(alertInfoTwo);
					}else if("ALERT_INFO_THREE".equals(filedAbcArry[i])){
						bankPrFiledType.setFiledAlert(alertInfoThree);
					}else if("SERVICE_ALERT_INFO".equals(filedAbcArry[i])){
						bankPrFiledType.setFiledAlert(serviceAlertInfo);
					}else{
						bankPrFiledType.setFiledAlert("");
					}
					bankPrFiledTypeService.saveFiledType(bankPrFiledType);
				}
				out.print("success");
				out.close();
		    }
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print("error");
			out.close();
		} 
			
		 
		
		/*int count=bankPrFiledTypeService.selectExistsTypeId(bankPrFiledType);	
		    if(count>0){
		    	out.print("1");
				out.close();
		    }else{
		    	 int countNum=bankPrFiledTypeService.saveFiledType(bankPrFiledType);
					if(countNum>0){
						out.print("success");
						out.close();
					}else{
						out.print("error");
						out.close();
					}
		    }*/
		   
	}
	
	/**
	 * 跳转到编辑页面
	 * @author zhao.lei
	 * @date 2016-4-26 下午12:17:14
	 */ 
	@RequestMapping(value="updateFiledType")
	public ModelAndView updateFiledType(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<BankPrFiledType> list=bankPrFiledTypeService.selectBankPrFiledById(typeId);
		String typeName="";
		if(list!=null&&list.size()>0){
			typeName=list.get(0).getTypeName();
		}
		List<Map<String,String>> filedList=bankPrFiledTypeService.selectAllFiledById(typeId);
		List<Map<String,String>> baseList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> moreList=new ArrayList<Map<String,String>>();
		for(int i=0;i<filedList.size();i++){
			String showWhere=filedList.get(i).get("FILEDSHOWWHERE");
			if("1".equals(showWhere)){
				baseList.add(filedList.get(i));
			}else if("2".equals(showWhere)){
				moreList.add(filedList.get(i));
			}
		}
		request.setAttribute("baseList",baseList);
		request.setAttribute("moreList",moreList);
		request.setAttribute("typeName",typeName);
		request.setAttribute("typeId",typeId);
		mv.setViewName("subscribe/updateBankPrFiledType");
		return mv;
	}  
	/*
	 * 修改
	 */
	@RequestMapping(value = "/updateFiledType", method = RequestMethod.POST)
	public void updateFiledType(BankPrFiledType bankPrFiledType, HttpServletRequest request,ModelMap modelMap,PrintWriter out){
		 
		String filedIds=bankPrFiledType.getFiledIds();//字段ID拼接串
		String filedNames=bankPrFiledType.getFiledNames();//字段名称拼接
		String filedAbcNames=bankPrFiledType.getFiledAbcNames();//字段英文名称
		String sortNos=bankPrFiledType.getSortNos();//排序号拼接串
		String isShows=bankPrFiledType.getIsShows();//是否展示拼接字符串
		String maxlengths=bankPrFiledType.getMaxlengths();//最大长度拼接串
		String filedAlertInfo=bankPrFiledType.getFiledAlertInfo();//字段提示信息
		String alertInfoOne=bankPrFiledType.getAlertInfoOne();//备注提示1
		String alertInfoTwo=bankPrFiledType.getAlertInfoTwo();//备注提示2
		String alertInfoThree=bankPrFiledType.getAlertInfoThree();//备注提示3
		String serviceAlertInfo=bankPrFiledType.getServiceAlertInfo();//服务提示
		String[] filedIdArry=filedIds.split(",");
		String[] filedNameArry=filedNames.split(",");
		String[] filedAbcArry=filedAbcNames.split(",");
		String[] sortNoArry=sortNos.split(",");
		String[] maxlengthArry=maxlengths.split(",");
		String[] isShowArry=null;
		int num=0;
		for(int j=0;j<filedAbcArry.length;j++){
			if(filedAbcArry[j].indexOf("YES_AND_NO")!=-1){
				num++;
			}
		}
		//int num=Arrays.binarySearch(filedAbcArry, "YES_AND_NO");
		if(isShows!=null&&num>0){
			 isShowArry=isShows.split(",");
		}
		String strs[]={bankPrFiledType.getTypeId()};
		bankPrFiledTypeService.deleteFiledType(strs); 
		for(int i=0;i<filedIdArry.length;i++){
			bankPrFiledType.setFiledId(filedIdArry[i]);
			bankPrFiledType.setFiledName(filedNameArry[i]);
			bankPrFiledType.setSortNo(Integer.parseInt(sortNoArry[i]));
			bankPrFiledType.setMaxLength(maxlengthArry[i]);
			if(isShowArry==null){
				bankPrFiledType.setIsShow("0");
			}else{
				bankPrFiledType.setIsShow(isShowArry[i]); 
			}
			if("FILED_ALERT_INFO".equals(filedAbcArry[i])){
				bankPrFiledType.setFiledAlert(filedAlertInfo);
			}else if("ALERT_INFO_ONE".equals(filedAbcArry[i])){
				bankPrFiledType.setFiledAlert(alertInfoOne);
			}else if("ALERT_INFO_TWO".equals(filedAbcArry[i])){
				bankPrFiledType.setFiledAlert(alertInfoTwo);
			}else if("ALERT_INFO_THREE".equals(filedAbcArry[i])){
				bankPrFiledType.setFiledAlert(alertInfoThree);
			}else if("SERVICE_ALERT_INFO".equals(filedAbcArry[i])){
				bankPrFiledType.setFiledAlert(serviceAlertInfo);
			}else{
				bankPrFiledType.setFiledAlert("");
			}
			
			bankPrFiledTypeService.saveFiledType(bankPrFiledType);
		}
		out.print("success");
		out.close();
	}
	
	/**
	 * 跳转到详情页面
	 * @param typeId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="filedTypeInfo")
	public ModelAndView filedTypeInfo(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<BankPrFiledType> list=bankPrFiledTypeService.selectBankPrFiledById(typeId);
		List<BankPrFiledType> listOne=new ArrayList<BankPrFiledType>();
		List<BankPrFiledType> listTwo=new ArrayList<BankPrFiledType>();
		String typeName="";
		if(list!=null&&list.size()>0){
			typeName=list.get(0).getTypeName();
			for(BankPrFiledType bankPrFiledType:list){
				if("1".equals(bankPrFiledType.getShowWhere())){
					listOne.add(bankPrFiledType);
				}else{
					listTwo.add(bankPrFiledType);
				} 
			}
		}
		
		List<Map<String,String>> typeList=bankPrFiledTypeService.serviceTypeList();
		//List<Map<String,String>> filedList=bankPrFiledTypeService.selectFiledList();
		request.setAttribute("listOne",listOne);
		request.setAttribute("listTwo",listTwo);
		request.setAttribute("typeList",typeList);
		request.setAttribute("typeName",typeName);
		request.setAttribute("list", list);
		mv.setViewName("subscribe/bankPrFiledTypeInfo");
		return mv;
	}
	
	/**
	 * 更新类型字段
	 * @author zhaolei
	 * @date 2016-4-26 下午4:56:36
	 */
	/*@RequestMapping(value="updateFiledType")
	public void updateFiledType(BankPrFiledType bankPrFiledType,HttpServletRequest request, ModelMap modelMap,PrintWriter out) {
		 
    	int countNum=bankPrFiledTypeService.updateFiledType(bankPrFiledType);
		if(countNum>0){
			out.print("success");
			out.close();
		}else{
			out.print("success");
			out.close();
		}
	      
		 
	}*/

	
	
	/**
	 * 删除权益预约字段
	 * @author zhao.lei
	 * @date 2016-4-26 下午12:14:50
	 */
	@RequestMapping(value = "/delFiledType" )
	public void  delFiledType(@RequestParam String filedTypeIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=filedTypeIds.split(",");
		   int count=bankPrFiledTypeService.deleteFiledType(strs); 
		   if(count>0){
			   out.print("success");
			   out.close();
		   }else{
			   out.print("error");
			   out.close();
		   }
		   
	}

	
	
	 
	
	 
}
