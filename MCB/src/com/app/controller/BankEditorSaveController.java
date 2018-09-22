package com.app.controller;

  
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankEditorSave;
import com.app.entity.WebDavInfo;
import com.app.service.BankEditorSaveService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
 
@Controller
@RequestMapping(value="/editor")
public class BankEditorSaveController{

	@Autowired
	private BankEditorSaveService bankEditorSaveService;
	@Autowired 
	private WebDavService webDavService;   
 
	@RequestMapping
	public ModelAndView selectBankEditor(Model model, HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		
		List<BankEditorSave> list=bankEditorSaveService.selectBankEditorList(); 
		model.addAttribute("list",list);
		 
		mv.setViewName("bankEditor/bankEditor");
		return mv;
		
	}
	@RequestMapping(value = "/selectBankEditorById")
	public ModelAndView selectBankEditorById(Model model, HttpServletRequest request,BankEditorSave bankEditorSave){
		ModelAndView mv=new ModelAndView();
		String editorNum=bankEditorSave.getEditorNum();
		BankEditorSave bankEditor=bankEditorSaveService.selectBankEditorInfoById(editorNum);
		 
		List<BankEditorSave> list=bankEditorSaveService.selectBankEditorList(); 
		model.addAttribute("list",list); 
		model.addAttribute("editorNum", editorNum);
		model.addAttribute("bankEditor", bankEditor);
		mv.setViewName("bankEditor/bankEditor");
		return mv;
	}
	 
	
	/**
	 * 保存或修改文本
	 * @author zhaolei
	 * @date 2016-3-17 下午7:12:13
	 */
	@RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
	public void saveInfo( HttpServletRequest request,BankEditorSave bankEditorSave,PrintWriter out){
		if(bankEditorSave!=null){
			String editorNum=bankEditorSave.getEditorNum();
			BankEditorSave bankEditor=bankEditorSaveService.selectBankEditorInfoById(editorNum);
			if(bankEditor==null){
				int i=bankEditorSaveService.saveEditor(bankEditorSave);
				if(i>0){
					out.write("success");
					 
				}
			}else{
				bankEditorSave.setEditorId(bankEditor.getEditorId());
				int j=bankEditorSaveService.updateEditor(bankEditorSave);
			 
				if(j>0){
					out.write("success");
					 
				}
			}
			
		} 
		 
		out.close();
		 
	} 
	
	/**
	 * 上传图片
	 * @author zhaolei
	 * @date 2016-3-17 下午7:14:11
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadEditor", method = RequestMethod.POST)
	public void saveUser(Model model,DefaultMultipartHttpServletRequest multipartRequest,HttpServletRequest request,HttpServletResponse response,PrintWriter out)  {
		  
		String path = null;
		boolean bool=false;
		long maxSize = 5000000;// 最大文件大小
		List<MultipartFile> files = multipartRequest.getFiles("imgFile");
		  
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (files == null) {
				response.getWriter().println(
						getError("Please choose to upload files!"));
				return;
			}
			if (files.get(0).getSize() > maxSize) {
				response.getWriter().println(
						getError("Upload file size over limit!"));
				return;
			}
			Map<String,String> davMap=webDavService.getWebDavInfo();
			String fileName = files.get(0).getOriginalFilename();

			String hostPath = request.getScheme() + davMap.get("A");

			// 主机上传
			bool=PutFileToWebDAV.putFile(hostPath, "kindEditor", fileName,
					WebDavInfo.hostUserName, WebDavInfo.hostPassword,
					files.get(0), null);
			//备机上传
		    if(!bool){
			   String standPath = request.getScheme() +davMap.get("B");
			   bool=PutFileToWebDAV.putFile(standPath, "kindEditor",fileName,
					   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,files.get(0),null);
		    }
		   
			path = request.getScheme()+webDavService.getWebDavPath() + WebDavInfo.hostLookUrl
					+ "kindEditor/" + PutFileToWebDAV.getName();
            
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", path);
			response.getWriter().println(obj.toJSONString());

		} catch (IOException e) {
			try {
				response.getWriter().println(getError("Upload File Failed!"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return;
		}

	}
	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	 
	
}
