package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.RightsPackagerkMapping;
import com.app.entity.RightsType;
import com.app.entity.WebDavInfo;
import com.app.service.BankEditorSaveService;
import com.app.service.BankPrActivityInfoService;
import com.app.service.BankPrFiledTypeService;
import com.app.service.RightsInterestsMappingService;
import com.app.service.RightsTypeService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping(value="/rt")
public class RightsTypeController {
	@Autowired 
	private RightsTypeService rightsTypeService;
	@Autowired
	private WebDavService webDavService; 
	@Autowired
	private BankEditorSaveService bankEditorSaveService; 
	@Autowired
	private RightsInterestsMappingService rightsInterestsMappingService;
	@Autowired
	private BankPrFiledTypeService bankPrFiledTypeService;
	@Autowired
	private BankPrActivityInfoService bankPrActivityInfoService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping 
	public ModelAndView listPageRightsType(RightsType rightsType,HttpServletRequest request, ModelMap modelMap){
		ModelAndView mv=new ModelAndView();
		List<RightsType> rightsTypes = rightsTypeService.rightsTypePageList(rightsType);
		modelMap.put("rightsTypes", rightsTypes);
		request.setAttribute("rightsType",rightsType);
		request.setAttribute("path",path);
		mv.setViewName("rightsType/listRightsType");
		return mv;
	}
	/**
	 * 跳转添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addRigthsType")
	public ModelAndView addRigthsType(Model model){
			ModelAndView mv=new ModelAndView();
			mv.setViewName("rightsType/saveRigthsType");
		return mv;
	}
	@RequestMapping(value="/rim")
	public ModelAndView listPageRightsInterestsMapping(RightsPackagerkMapping rightsPackagerkMapping,
			HttpServletRequest request,ModelMap modelMap){
		ModelAndView mv=new ModelAndView();
		List<RightsPackagerkMapping> list = rightsInterestsMappingService
				.rightsPackagerkMappingPageList(rightsPackagerkMapping);
		modelMap.put("list", list);
		request.setAttribute("rightsPackagerkMapping", rightsPackagerkMapping);
			mv.setViewName("rightsType/listRightsPackagerk");
		return mv;
	}
	
	/**
	 * @author huangcheng
	 * 此处需要增加rigthsTypeCode的唯一性检验
	 * @RequestParam(value="rigthsTypeCode")String rigthsTypeCode,
	 */
	@RequestMapping(value="tesRightsTypeCodeOnly",method=RequestMethod.POST)
	public void tesRightsTypeCodeOnly(HttpServletRequest request,HttpServletResponse response){
		String rigthsTypeCode = request.getParameter("rigthsTypeCode");
		int rightsNum = rightsTypeService.getRightsTypeCodeByCode(rigthsTypeCode);
		String errorInfo = "";
		if(rightsNum > 0){
			errorInfo = "fail";
		}else{
			errorInfo = "success";
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("errorInfo", errorInfo);
		PrintWriter pt = null;
		JSONObject json = JSONObject.fromObject(data);
		String jsonStr = json.toString();
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			pt = response.getWriter();
			pt.write(jsonStr);
			pt.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pt != null){
				pt.close();
			}
		}
	}
	
	@RequestMapping(value="saveRigthsType",method = RequestMethod.POST)
	public ModelAndView saveRigthsType(RightsType rightsType,DefaultMultipartHttpServletRequest multipartHttpServletRequest,
			HttpServletRequest request, PrintWriter out){
		String rightsMyNum = rightsType.getRightsMyNum();
		ModelAndView mv=new ModelAndView();
		int random1 = (int) (Math.random()*100000000/10);
		int random2 = (int) (Math.random()*100000000/10);
		int random3 = (int) (Math.random()*100000000/10);
		String rightsTypeId = rightsMyNum+random1+random2+random3;
		rightsType.setRigthsTypeId(rightsTypeId);
		
		if(rightsType.getRightsMyNum()==null||"".equals(rightsType.getRightsMyNum())){
			rightsType.setRightsMyNum("1");
		}
		if(rightsType.getRightsTogetherNum()==null||"".equals(rightsType.getRightsTogetherNum())){
			rightsType.setRightsTogetherNum("2");
		}
		//获取系统时间
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		String date=dateFormat.format(new java.util.Date());
		boolean bool=false;
		String strs=rightsType.getRightsInDescribe();
		//String strId=rightsTypeService.getRightsTypeId();
		//rightsType.setRigthsTypeId(strId); 
		// 上传图片
		if (multipartHttpServletRequest != null) {
			Map<String,String> davMap=webDavService.getWebDavInfo(); 
			//获取页面图片属性
			List rigthsTypeUrlFile = multipartHttpServletRequest.getFiles("rigthsTypeUrlFile");
			//获取页面图片属性
			List rigthsTypeIconUrlFile = multipartHttpServletRequest.getFiles("rigthsTypeIconUrlFile");
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			// 权益图片
			if (rigthsTypeUrlFile != null) {
				for (int i = 0; i < rigthsTypeUrlFile.size(); i++) {
					MultipartFile file = (MultipartFile) rigthsTypeUrlFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						//获取图片名称
						String name = file.getOriginalFilename();
						//本机时间和图片名称拼接
						String filename=date+"_"+name;
						Map<String,Object> newMap=new HashMap<String,Object>();
			    		newMap.put("name", filename);
			    		newMap.put("file", file);
			    		list.add(newMap);
					    // 取图片服务路径保存在数据服务器
					    String rigthsTypeUrl =WebDavInfo.hostLookUrl + "rightsImg/"+filename;
						rightsType.setRigthsTypeUrl(rigthsTypeUrl);
						rightsType.setRigthsTypeUrlName(filename);
						 
						}
					}
				}
			// 权益图标
			if (rigthsTypeIconUrlFile != null) {
				for (int i = 0; i < rigthsTypeIconUrlFile.size(); i++) {
					MultipartFile file = (MultipartFile) rigthsTypeIconUrlFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						//获取图片名称
						String names = file.getOriginalFilename();
						if(names.length()>10){
							names=names.substring(0, 10);
						}
						//本机时间和图片名称拼接
						String filenames=date+"_"+names;
						Map<String,Object> map=new HashMap<String,Object>();
			    	    map.put("name", filenames);
			    		map.put("file", file);
			    		list.add(map);
					    // 取图片服务路径保存在数据服务器
					    String rigthsTypeIconUrl =WebDavInfo.hostLookUrl + "rightsImg/" + filenames;
						rightsType.setRigthsTypeIconUrl(rigthsTypeIconUrl);
						rightsType.setRigthsTypeIconUrlNmae(filenames);
						 
						}
					}
			 }
			
			//保存
			String hostPath = request.getScheme() + davMap.get("A");
			//主机上传
			bool = PutFileToWebDAV.putFiles(hostPath, "rightsImg",list,
					WebDavInfo.hostUserName, WebDavInfo.hostPassword,null);
			//备机上传
		   if(!bool){
			   String standPath = request.getScheme() + davMap.get("B");
			   bool=PutFileToWebDAV.putFiles(standPath, "rightsImg",list,
					   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,null);
		   }
		 
		}
		rightsTypeService.saveRigthsType(rightsType);
		String lookUrl="";
	    if(!"".equals(strs)&&strs!=null){
		   String uploadPath = request.getSession().getServletContext()
					.getRealPath("kindEditor");
		   File file = new File(uploadPath+File.separator);
		   //测试文件夹是否存在
		   if(!file.exists()){
			   file.mkdirs();
		   }
		   lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"rightsType",rightsType.getRigthsTypeId());
	    }
	    rightsType.setRigthsDescribe(lookUrl); 
	    rightsTypeService.updateRigthsType(rightsType);
		mv.setViewName("save_result");
		return mv;
	}
	
	//删除权益类型信息数据
	@RequestMapping(value="/delRigthsType")
	public void delRigthsType(@RequestParam String rigthsTypeId,@RequestParam String rigthsTypeCode,PrintWriter out,HttpServletRequest request){
		RightsType rightsType =rightsTypeService.selectRigthsTypeByid(rigthsTypeId);
		List<String> picNameList=new ArrayList<String>();
		List<String> list=new ArrayList<String>();
		list.add(rigthsTypeId);
		if(rightsType!=null){
			picNameList.add(rightsType.getRigthsTypeIconUrlNmae());
			picNameList.add(rightsType.getRigthsTypeUrlName());
			boolean bool=false; 
			Map<String,String> davMap=webDavService.getWebDavInfo();
			String hostPath = request.getScheme() + davMap.get("A")+"rightsImg/";
			
			bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"rightsImg/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);
			}
			
			String htmlPath = request.getScheme() +davMap.get("A")+"rightsType/";
			bool=PutFileToWebDAV.delFile(htmlPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, list);//删除富文本HTML
			//备机删除
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"rightsType/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, list); 
			}
		}
		
		
		rightsTypeService.deleteRigthsType(rigthsTypeId);
		String strs[]={rigthsTypeCode};
		bankPrFiledTypeService.deleteFiledType(strs); //删除权益预约字段
		bankPrActivityInfoService.deletePrActivityInfo(strs);//删除权益活动
		out.write("success");
		out.close();
	}
	//查询权益描述详情
	@RequestMapping(value="/rightsTypeInfo")
	public ModelAndView rightsTypeInfo(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		RightsType rightsType =rightsTypeService.selectRigthsTypeByid(typeId);
		mv.addObject("rightsType", rightsType);
		request.setAttribute("path", path);
		mv.setViewName("rightsType/rightsTypeInfo");
		return mv;
	}
	//查询权益修改页面
	@RequestMapping(value="/selRightsTypeInfo")
	public ModelAndView sel(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		RightsType rightsType =rightsTypeService.selectRigthsTypeByid(typeId);
		mv.addObject("rightsType", rightsType);
		request.setAttribute("path", path);
		mv.setViewName("rightsType/editRigthsType");
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="editRigthsType",method = RequestMethod.POST)
	public ModelAndView editRigthsType(RightsType rightsType,DefaultMultipartHttpServletRequest multipartHttpServletRequest,
			HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		//获取系统时间
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		String date=dateFormat.format(new java.util.Date());
		boolean bool=false;
		String strs=rightsType.getRightsInDescribe();
		String strId=rightsType.getRigthsTypeId();
		rightsType.setRigthsTypeId(strId); 
		if(rightsType.getRightsMyNum()==null||"".equals(rightsType.getRightsMyNum())){
			rightsType.setRightsMyNum("1");
		}
		if(rightsType.getRightsTogetherNum()==null||"".equals(rightsType.getRightsTogetherNum())){
			rightsType.setRightsTogetherNum("2");
		}
		// 上传图片
		if (multipartHttpServletRequest != null) {
			Map<String,String> davMap=webDavService.getWebDavInfo(); 
			//获取页面图片属性
			List rigthsTypeUrlFile = multipartHttpServletRequest.getFiles("rigthsTypeUrlFile");
			//获取页面图片属性
			List rigthsTypeIconUrlFile = multipartHttpServletRequest.getFiles("rigthsTypeIconUrlFile");
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			// 权益图片
			if (rigthsTypeUrlFile != null) {
				for (int i = 0; i < rigthsTypeUrlFile.size(); i++) {
					MultipartFile file = (MultipartFile) rigthsTypeUrlFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						//获取图片名称
						String name = file.getOriginalFilename();
//						if(name.length()>10){
//							name=name.substring(0, 10);
//						}
						//本机时间和图片名称拼接
						String filename=date+"_"+name;
						Map<String,Object> newMap=new HashMap<String,Object>();
			    		newMap.put("name", filename);
			    		newMap.put("file", file);
			    		list.add(newMap);
					    // 取图片服务路径保存在数据服务器
					    String rigthsTypeUrl =WebDavInfo.hostLookUrl + "rightsImg/"+filename;
						rightsType.setRigthsTypeUrl(rigthsTypeUrl);
						rightsType.setRigthsTypeUrlName(filename);
						 
						}
					}
				}
			// 权益图标
			if (rigthsTypeIconUrlFile != null) {
				for (int i = 0; i < rigthsTypeIconUrlFile.size(); i++) {
					MultipartFile file = (MultipartFile) rigthsTypeIconUrlFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						//获取图片名称
						String names = file.getOriginalFilename();
						if(names.length()>10){
							names=names.substring(0, 10);
						}
						//本机时间和图片名称拼接
						String filenames=date+"_"+names;
						Map<String,Object> map=new HashMap<String,Object>();
			    	    map.put("name", filenames);
			    		map.put("file", file);
			    		list.add(map);
					    // 取图片服务路径保存在数据服务器
					    String rigthsTypeIconUrl =WebDavInfo.hostLookUrl + "rightsImg/" + filenames;
						rightsType.setRigthsTypeIconUrl(rigthsTypeIconUrl);
						rightsType.setRigthsTypeIconUrlNmae(filenames);
						 
						}
					}
			 }
			if(list!=null&&list.size()>0){
				//保存
				String hostPath = request.getScheme() + davMap.get("A");
				//主机上传
				bool = PutFileToWebDAV.putFiles(hostPath, "rightsImg",list,
						WebDavInfo.hostUserName, WebDavInfo.hostPassword,null);
				//备机上传
			   if(!bool){
				   String standPath = request.getScheme() + davMap.get("B");
				   bool=PutFileToWebDAV.putFiles(standPath, "rightsImg",list,
						   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,null);
			   }
		    }
			
		 
		}
		
		 String lookUrl="";
	     if(!"".equals(strs)&&strs!=null){
		   String uploadPath = request.getSession().getServletContext()
					.getRealPath("kindEditor");
		   lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"rightsType",strId);
	    }
	    rightsType.setRigthsDescribe(lookUrl); 
		rightsTypeService.updateRigthsType(rightsType);
		mv.setViewName("save_result");
		return mv;
	}
}
