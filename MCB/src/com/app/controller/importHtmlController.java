package com.app.controller;
 
 
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ImportHtml;
import com.app.entity.User;
import com.app.entity.WebDavInfo;
import com.app.service.ImportHtmlService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
 
  
 
@Controller
@RequestMapping(value="/importHtml")
public class importHtmlController{
	
	@Autowired
	private ImportHtmlService importHtmlService;
	@Autowired
	private WebDavService webDavService; 
	public ImportHtmlService getImportHtmlService() {
		return importHtmlService;
	}

	public void setImportHtmlService(ImportHtmlService importHtmlService) {
		this.importHtmlService = importHtmlService;
	}

	@RequestMapping
	public String importHtmlList(Model model,ImportHtml importHtml, ModelMap modelMap,HttpServletRequest request){
		 		  
		List<ImportHtml> list = importHtmlService.selectNeswPushListPage(importHtml);
		modelMap.put("list", list);
		request.setAttribute("importHtml", importHtml);
		return "importHtml/importHtmlList";
		
	}
	
	//跳转到导入页面
	@RequestMapping(value = "/import")
	public String importOtu() {
		return "importHtml/importHtml";
	}
	
	
	@RequestMapping(value = "/importData")
	public ModelAndView bank_book( DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response,
			PrintWriter out, ModelMap model){
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dateFormat.format(new java.util.Date());
		boolean bool=false; 
		String htmlName=null;
		Map<String,Object> map=new HashMap<String,Object>();
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List importFiles = multipartRequest.getFiles("importFile");
			Map<String,String> davMap=webDavService.getWebDavInfo();
			for (int i = 0; i < importFiles.size(); i++) {
				MultipartFile file = (MultipartFile) importFiles.get(i);
				if (StringUtils.hasText(file.getOriginalFilename())){
					String name=file.getOriginalFilename();
					String flg=name.substring(name.lastIndexOf(".")+1,name.length());
					if("html".equals(flg)){
						 map.put("html", file);
					}else if("css".equals(flg)){
					     map.put("css", file);
					}else{
						map.put("image", file);
					
					}
					
					
				}
		    }
			String hostPath = request.getScheme() + davMap.get("A");
			String standPath = request.getScheme() + davMap.get("B");
			MultipartFile htmlfile=(MultipartFile)map.get("html");
			htmlName=htmlfile.getOriginalFilename();
			MultipartFile cssfile=(MultipartFile)map.get("css");
		    MultipartFile imagefile=(MultipartFile)map.get("image");
		    String cssName=cssfile.getOriginalFilename();
		    String imageName=imagefile.getOriginalFilename();
			// 主机上传页面
			bool=PutFileToWebDAV.putFile(hostPath, date,htmlName,
					   WebDavInfo.hostUserName, WebDavInfo.hostPassword,htmlfile,null);
			//备机上传页面
		   if(!bool){
			   bool=PutFileToWebDAV.putFile(standPath, date,htmlName,
					   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,htmlfile,null);
		   }
			// 主机上传CSS
		   bool=PutFileToWebDAV.putFile(hostPath, date,cssName,
					   WebDavInfo.hostUserName, WebDavInfo.hostPassword,cssfile,null);
		   //备机上传CSS
		   if(!bool){
			   bool=PutFileToWebDAV.putFile(standPath, date,cssName,
					   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,cssfile,null);
		   }
			// 主机上传IMAGE
		   bool=PutFileToWebDAV.putFile(hostPath, date,imageName,
					   WebDavInfo.hostUserName, WebDavInfo.hostPassword,imagefile,null);
		   //备机上传IMAGE
		   if(!bool){
			   bool=PutFileToWebDAV.putFile(standPath, date,imageName,
					   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,imagefile,null);
		   } 
		    
		   
		  ImportHtml importHtml=new ImportHtml();
		  User user=(User)request.getSession().getAttribute("user");
		  SimpleDateFormat sysDate = new SimpleDateFormat("yyyy-MM-dd");
		  String importDate = sysDate.format(new java.util.Date());
		  String htmlUrl=WebDavInfo.hostLookUrl+date+"/"+htmlName;
		  importHtml.setHtmlDate(importDate);
		  importHtml.setHtmlName(htmlName);
		  importHtml.setHtmlUrl(htmlUrl);//http://168.5.16.133:9080/webdav/20160314170342/index1.html
		  importHtml.setHtmlUser(user.getLoginname());
		  importHtmlService.saveImportHtml(importHtml);
			
		}
		mv.setViewName("save_result");
		return mv;
 
	}

	 
}
